/**
 * Copyright (c) 2003 RiseSoft Co.,Ltd $Header:
 * /home/cvsroot/RiseNet/src/net/risesoft/workflow/core/action/UserRouteAction.java,v 1.10
 * 2004/09/27 09:39:56 hongxing Exp $
 */
package net.risesoft.workflow.core.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import net.risesoft.commons.RiseUser;
import net.risesoft.commons.database.Conn;
import net.risesoft.commons.database.DatabaseFactory;
import net.risesoft.commons.database.DatabaseUtil;
import net.risesoft.commons.database.Execute;
import net.risesoft.commons.util.ComparatorIgnoreCase;
import net.risesoft.commons.util.GUID;
import net.risesoft.commons.util.Util;
import net.risesoft.workflow.core.Workflow;
import net.risesoft.workflow.core.WorkflowConst;
import net.risesoft.workflow.core.route.Route;
import net.risesoft.workflow.core.route.filter.RouteFilter;
import net.risesoft.workflow.core.table.UserTable;
import net.risesoft.workflow.core.task.Task;
import net.risesoft.workflow.engine.WorkflowInstance;
import net.risesoft.workflow.engine.action.SendInstanceForm;
import net.risesoft.workflow.engine.event.BaseEvent;
import net.risesoft.workflow.engine.event.SendInstanceExecuteEvent;
/**
 * @author 赵斌 2003-7-6
 * @version $Revision: 1.30 $
 */
public class UserRouteAction extends RouteAction {
	private static final long serialVersionUID = -7627222296940748971L;
	private List routes;
	// 保存当前instance中的所有办理人信息，仅用于发散
	private List instanceActors = null;
	public UserRouteAction(String actionGUID) {
		super(actionGUID);
	}
	public UserRouteAction(String actionGUID, Task task, ResultSet rs) throws SQLException {
		super(actionGUID, task, rs);
		this.routes = task.getWorkflow().lookupRouteListByAction(actionGUID);
	}
	/*
	 * 从本action到指定task的路由对象，如果不存在，返回null。
	 */
	public Route getRoute(String taskGUID) {
		// 如果routes中只有一个路由，说明这个action对象仅对应一个task，所以，不需要判断，直接返回。
		if (routes.size() == 1) {
			return (Route) routes.get(0);
		}
		// 开始时，每个RouteAction仅对应一个route，所以，可以直接返回route对象。现在，一个RouteAction可以发散到多个 Task，所以要根据目标task的GUID来确定route。
		for (Iterator iter = routes.iterator(); iter.hasNext();) {
			Route route = (Route) iter.next();
			if (isReverseDirection()) {
				if (taskGUID.equals(route.getTaskGUID())) {
					return route;
				}
			} else {
				if (taskGUID.equals(route.getRouteToTaskGUID())) {
					return route;
				}
			}
		}
		return null;
	}
	public Object clone() {
		UserRouteAction ra = (UserRouteAction) super.clone();
		ra.routes = this.routes;
		return ra;
	}
	public void perform(BaseEvent event) throws SQLException, IOException {
//		try {
//			if (!((CheckLicense) RiseClassLoader.getInstance().loadClass("CheckLicenseImpl").newInstance()).check("Workflow")) {
//				return;
//			}
//		} catch (Exception e) {
//			log.error("Workflow license error!!!");
//			return;
//		}
		SendInstanceExecuteEvent e = (SendInstanceExecuteEvent) event;
		WorkflowInstance instance = e.getWorkflowInstance();
		String instanceGUID = instance.getWorkflowInstanceGUID();
		Workflow workflow = instance.getWorkflow();
		Connection conn = e.getConnection();
		SendInstanceForm f = e.getInstanceSendForm();
		List lstRecipientsGUIDs = f.getRecipientsGUIDs();
		List lstRouteTypes = f.getRouteTypes();
		List lstToTaskGUIDs = f.getToTaskGUIDs();
		List exhaleInstanceGUIDs = new ArrayList();
		// 王屹修正 20061011 发散 instanceActors == null
		instanceActors = createInstanceActorsList(instanceGUID, conn);
		// 发送给多个任务，先进行发散处理，然后对每一个发散后的实例执行发送动作
		if (lstRecipientsGUIDs.size() > 1) {
			// 先将当前实例设为停止
			TreeMap tmInstance = new TreeMap(new ComparatorIgnoreCase());
			tmInstance.put("WorkflowInstance_GUID", instance.getWorkflowInstanceGUID());
			tmInstance.put("InstanceStatus", new Integer(WorkflowConst.INSTANCE_STATUS_TERMINATED));
			DatabaseFactory.createUpdate(conn, "Office_WorkflowInstance", tmInstance).execute();
			// 清除当前实例在todo中的信息
			removeToDoForInstance(conn, instance.getWorkflowInstanceGUID());
			tmInstance = instance.getInstanceTreeMap();
			tmInstance.put("ParentWorkflowInstance_GUID", instanceGUID);
			tmInstance.put("InstanceStatus", new Integer(WorkflowConst.INSTANCE_STATUS_RUNNING));
			for (int i = 0; i < lstRecipientsGUIDs.size(); i++) {
				String newInstanceGUID = new GUID().toString();
				exhaleInstanceGUIDs.add(newInstanceGUID); // 保存起来，以便于后面复制人员
				// 复制Office_WorkflowInstance表
				tmInstance.put("WorkflowInstance_GUID", newInstanceGUID);
				DatabaseFactory.createInsert(conn, "Office_WorkflowInstance", tmInstance).execute();
				// 复制所有用户表数据包括主表、细表
				for (Iterator iter = workflow.iteratorUserTable(); iter.hasNext();) {
					UserTable userTable = (UserTable) iter.next();
					if (userTable.getTableType() == UserTable.USERTABLE_DETAIL) {
						copyTableRowSet(conn, userTable.getTableName(), instanceGUID, newInstanceGUID, "Row_GUID");
					} else {
						copyTableRowSet(conn, userTable.getTableName(), instanceGUID, newInstanceGUID);
					}
				}
				// 复制正文、附件、意见表
				copyTableRowSet(conn, "Office_WorkflowDocument", instanceGUID, newInstanceGUID, "DOCUMENTROW_GUID");
				copyTableRowSet(conn, "Office_WorkflowAttachment", instanceGUID, newInstanceGUID, "AttachmentRow_GUID");
				copyTableRowSet(conn, "Office_WorkflowComment", instanceGUID, newInstanceGUID, "Row_GUID");
			}
			// 这里实际上是复制，将所有人员数据发散到每一个实例，所以不用检查人员重复问题
			for (int i = 0, m = exhaleInstanceGUIDs.size(); i < m; i++) {
				String newInstanceGUID = (String) exhaleInstanceGUIDs.get(i);
				for (int j = 0; j < instanceActors.size(); j++) {
					TreeMap tm = (TreeMap) instanceActors.get(j);
					tm.put("WorkflowInstance_GUID", newInstanceGUID);
					tm.put("Actors_GUID", (new GUID()).toString()); // Primary
					// Key
					DatabaseFactory.createInsert(conn, "OFFICE_WorkflowInstanceActors", tm).execute();
				}
			}
			// 将所有收件人加入停止的父实例的RECIPIENTS中，以便显示历程
			int step = instanceActors.size();
			removeParentInstanceRecipients(conn, instance);
			addMultiPerson2Recipients(conn, lstRecipientsGUIDs, instance, step);
		} else {
			exhaleInstanceGUIDs.add(instanceGUID);
		}
		// 一定要先处理抄送，否则实例上标记的上一步操作为抄送，并且会导致实例实际上“当前任务没有变”。
		// 虽然performImpl()方法中调用了removeToDoForInstance()，但不会删除加入的抄送todo信息
		String[] copyToRecipientsGUIDs = f.getCopyToRecipientsGUIDs();
		if (copyToRecipientsGUIDs != null && copyToRecipientsGUIDs.length > 0) {
			SystemCopyToAction copyToAction = new SystemCopyToAction(ActionConst.ACTION_COPYTO_GUID);
			copyToAction.perform(e);
		}
		for (int idx = 0; idx < exhaleInstanceGUIDs.size(); idx++) {
			String[] recipientsGUIDs = (String[]) lstRecipientsGUIDs.get(idx);
			// 至少要有一个收件人
			if (recipientsGUIDs == null || recipientsGUIDs.length == 0) {
				throw new IllegalArgumentException("无法获取收件人信息，至少要有一个收件人。");
			}
			int routeType = ((Integer) lstRouteTypes.get(idx)).intValue();
			// 如果只有一个接收人，全部认为是串行
			if (recipientsGUIDs.length == 1) {
				routeType = Route.ROUTE_TYPE_SINGLE;
			}
			String toTaskGUID = (String) lstToTaskGUIDs.get(idx);
			this.performImpl(event, (String) exhaleInstanceGUIDs.get(idx), toTaskGUID, routeType, recipientsGUIDs);
		}
		instance.refresh(conn);
	}
	private List createInstanceActorsList(String instanceGUID, Connection conn) throws SQLException, IOException {
		List lst = new ArrayList();
		String sql = "SELECT * FROM OFFICE_WorkflowInstanceActors WHERE WorkflowInstance_GUID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, instanceGUID);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			lst.add(DatabaseUtil.getTreeMap(rs));
		}
		rs.close();
		ps.close();
		return lst;
	}
	private void performImpl(BaseEvent event, String instanceGUID, String toTaskGUID, int routeType, String[] recipientsGUIDs) throws SQLException, IOException {
		SendInstanceExecuteEvent e = (SendInstanceExecuteEvent) event;
		WorkflowInstance instance = e.getWorkflowInstance();
		int preRouteType = instance.getPreRouteType();
		Workflow workflow = instance.getWorkflow();
		RiseUser user = e.getRiseUser();
		Connection conn = e.getConnection();
		SendInstanceForm f = e.getInstanceSendForm();
		// 对于发散情况，instanceGUID不等于instance.getWorkflowInstanceGUID()
		super.instance2Tracking(conn, instance, instanceGUID); // 复制instance纪录到Tracking表，包括Actors表
		// 先处理发给一个任务，可以有多人接收
		Task toTask = workflow.lookupTask(toTaskGUID);
		Date now = new Date();
		Date remindDate = f.getRemindDateWrapper();
		Date taskExpireDate = f.getTaskExpiredDateWrapper();
		Date instanceExpiredDate = f.getInstanceExpiredDateWrapper();
		// 更新instance表，将路由信息等写入，因为要更改，所以一定要clone，否则将影响内存中instance的instanceTreeMap
		TreeMap tmInstance = (TreeMap) instance.getInstanceTreeMap().clone();
		// tmInstance.put("Workflow_GUID", );
		tmInstance.put("WorkflowInstance_GUID", instanceGUID);
		// tmInstance.put("ParentWorkflowInstance_GUID", );
		tmInstance.put("Step", new Integer(instance.getStep() + 1));
		tmInstance.put("CurrentTask_GUID", toTask.getTaskGUID());
		tmInstance.put("CurrentTask_Name", toTask.getTaskName());
		tmInstance.put("PreTask_GUID", instance.getCurrentTask().getTaskGUID());
		tmInstance.put("PreTask_Name", instance.getCurrentTask().getTaskName());
		Route r = this.getRoute(toTask.getTaskGUID());
		if (isReverseDirection()) { // 反向
			tmInstance.put("PreAction_GUID", this.getActionGUID());
			tmInstance.put("PreAction_Name", r.getRouteBackCaption());
			tmInstance.put("PreRoute_GUID", r.getRouteGUID());
			tmInstance.put("PreRoute_Name", r.getRouteBackCaption());
		} else { // 正向
			tmInstance.put("PreAction_GUID", this.getActionGUID());
			tmInstance.put("PreAction_Name", this.getActionName());
			tmInstance.put("PreRoute_GUID", r.getRouteGUID());
			tmInstance.put("PreRoute_Name", r.getRouteCaption());
		}
		tmInstance.put("PreRoute_Type", new Integer(routeType));
		if (routeType == Route.ROUTE_TYPE_EXHALE) { // 除非是中止、结束，此值不变，如果是发散，将此实例结束
			tmInstance.put("InstanceStatus", new Integer(WorkflowConst.INSTANCE_STATUS_TERMINATED));
		}
		if (isReverseDirection()) {
			tmInstance.put("ActionStatus", new Integer(ACTION_SENDBACK_STATUS)); // 应该由此Action决定，退回
		} else {
			tmInstance.put("ActionStatus", new Integer(ACTION_NORMAL_STATUS)); // 正常
		}
		tmInstance.put("UpdateDate", now);
		tmInstance.put("PreSubmitDate", now);
		tmInstance.put("RemindDate", remindDate);
		tmInstance.put("Task_ExpireDate", taskExpireDate);
		tmInstance.put("WorkflowInstance_ExpireDate", instanceExpiredDate);
		DatabaseFactory.createUpdate(conn, "Office_WorkflowInstance", tmInstance).execute();
		
		List exhaleInstanceGUIDs = null;
		// 如果是发散，需要放入所有的值，以便复制Office_WorkflowInstance记录
		if (routeType == Route.ROUTE_TYPE_EXHALE) {
			exhaleInstanceGUIDs = new ArrayList();
			tmInstance.put("ParentWorkflowInstance_GUID", instanceGUID);
			for (int i = 0; i < recipientsGUIDs.length; i++) {
				String newInstanceGUID = new GUID().toString();
				exhaleInstanceGUIDs.add(newInstanceGUID); // 保存起来，以便于后面复制人员
				// 复制Office_WorkflowInstance表
				tmInstance.put("WorkflowInstance_GUID", newInstanceGUID);
				tmInstance.put("InstanceStatus", new Integer(WorkflowConst.INSTANCE_STATUS_RUNNING));
				DatabaseFactory.createInsert(conn, "Office_WorkflowInstance", tmInstance).execute();
				// 复制所有用户表数据包括主表、细表
				for (Iterator iter = workflow.iteratorUserTable(); iter.hasNext();) {
					UserTable userTable = (UserTable) iter.next();
					if (userTable.getTableType() == UserTable.USERTABLE_DETAIL) {
						copyTableRowSet(conn, userTable.getTableName(), instanceGUID, newInstanceGUID, "Row_GUID");
					} else {
						copyTableRowSet(conn, userTable.getTableName(), instanceGUID, newInstanceGUID);
					}
				}
				// 复制正文、附件、意见表
				copyTableRowSet(conn, "Office_WorkflowDocument", instanceGUID, newInstanceGUID, "DOCUMENTROW_GUID");
				copyTableRowSet(conn, "Office_WorkflowAttachment", instanceGUID, newInstanceGUID, "AttachmentRow_GUID");
				copyTableRowSet(conn, "Office_WorkflowComment", instanceGUID, newInstanceGUID, "Row_GUID");
			}
		}
		removeToDoForInstance(conn, instanceGUID);
		// 人员的step从0开始，工作流的step从1开始，所以需要 -1
		int step = instance.getStep();
		/**
		 * 至此，已经完成了对instance表的操作，下面根据路由分析的5种情况，处理人员信息 详见《工作流路由处理规则》文档
		 * 这里不处理任务内路由，因为首先instance记录的处理方式也有不同，
		 * 也包括任务内路由不是recipients放入previous，因为前一步处理人没有变。
		 */
		String sql;
		Execute execute = DatabaseFactory.createExecute(conn);
		// CHANGE Hong Xing: 清除instance中的previous、recipients、next(2004-8-17
		// 15:28:19)
		sql = "DELETE FROM OFFICE_WorkflowInstanceActors WHERE WorkflowInstance_GUID='" + instanceGUID + "'" + " AND ( Actors_Classify=" + ACTORS_CLASSIFY_PREVIOUS + " OR Actors_Classify="+ ACTORS_CLASSIFY_RECIPIENTS + " OR Actors_Classify=" + ACTORS_CLASSIFY_NEXT + ")";
		execute.execute(sql);
		// CHANGE Hong Xing: 加入对前任务为抢占式路由的处理：将所有的current改为done, 同时将自己加入前执行人
		// (2004-8-26 17:24:22)
		if (preRouteType == ROUTE_TYPE_APPLY) {
			sql = "UPDATE OFFICE_WorkflowInstanceActors SET Actors_Classify=" + ACTORS_CLASSIFY_DONE + " WHERE WorkflowInstance_GUID='" + instanceGUID + "'" + " AND Actors_Classify=" + ACTORS_CLASSIFY_CURRENT;
			execute.execute(sql);
			addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, user.getUserGUID(), ACTORS_CLASSIFY_PREVIOUS, HANDEL_STATUS_READ, 0);
		} else {
			sql = "UPDATE OFFICE_WorkflowInstanceActors SET Actors_Classify=" + ACTORS_CLASSIFY_PREVIOUS + " WHERE WorkflowInstance_GUID='" + instanceGUID + "'" + " AND Actors_Classify=" + ACTORS_CLASSIFY_CURRENT;
			execute.execute(sql);
		}
		// done加入自己， 人员的step从0开始，工作流的step从1开始，所以需要 -1
		addActorWithSearch(conn, instance.getWorkflowGUID(), instanceGUID, step, user.getUserGUID(), ACTORS_CLASSIFY_DONE, HANDEL_STATUS_DONE, 0);
		
			
		// ===================================== start ======并联审批用====孙明20130513===============
		List list = toTask.getInRoutes();
		String  parentinsguid = instance.getParentInstanceGUID();
		int totaskcount=0;
		if(parentinsguid!=null&&list.size()>1){
			try {
				Statement stmt = conn.createStatement();
			    sql = "select count(*) from  office_workflowinstance where ParentWorkflowInstance_GUID='"+parentinsguid+"' and currenttask_guid='"+toTask.getTaskGUID()+"'";
				System.out.println(">>>00000000>>>>>"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					totaskcount= rs.getInt(1);
				}
				rs.close();
				stmt.close();
			} catch (Exception ex) {
				log.error(ex);
			} finally {
			//	try {
			//		if (conn != null){conn.close();}	
			//	} catch (Exception ee) {}
			}
		    System.out.println(">>>111111111>>>>>"+totaskcount+">>>>>>>>"+list.size());
		    updateInstanceActorsStep(conn, instanceGUID, instance.getStep() + 1);
			if(totaskcount!=list.size()){
			   for (int i = 1; i < recipientsGUIDs.length; i++) {
					addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_NEXT, HANDEL_STATUS_TODO, i);
					addToDo(conn, user, recipientsGUIDs[0], instance, instanceGUID);
			   }
			   for (int i = 0; i < recipientsGUIDs.length; i++) {
					addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_RECIPIENTS,HANDEL_STATUS_TODO, i);// 此处的9
			   } 
			   updateInstanceActorsStep(conn, instanceGUID, instance.getStep() + 1);
			   return;
			}
			//System.out.println(">>>222222222>>>>>"+totaskcount+">>>>>>>>"+list.size());
		} 
		//System.out.println(">>>333333333>>>>>"+totaskcount+">>>>>>>>"+list.size());
		//==============================end=======并联审批用====孙明20130513===========================
		
		
		// 顺序串行，包括了 1->1、1->多串、多->1 三种
		if (routeType == Route.ROUTE_TYPE_SINGLE || routeType == Route.ROUTE_TYPE_SERIAL) {
			// 当前执行人 CURRENT ：（清除原有数据）保存第一个收件人
			addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, recipientsGUIDs[0], ACTORS_CLASSIFY_CURRENT, HANDEL_STATUS_TODO, 0);
			addToDo(conn, user, recipientsGUIDs[0], instance, instanceGUID);
			// 待办人 NEXT ：（清除原有数据）保存剩下的收件人，如果有的话
			for (int i = 1; i < recipientsGUIDs.length; i++) {
				addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_NEXT, HANDEL_STATUS_TODO, i);
			}
			// 将所有收件人放入recipients，仅对于串并有效，发散时recipients中只放一个人
			for (int i = 0; i < recipientsGUIDs.length; i++) {
				addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_RECIPIENTS, HANDEL_STATUS_TODO, i);
			}
		}
		// 无序串行，包括了 1->1、1->多串、多->1 三种 并行， 1->多并 抢占式
		// 其特点是 当前执行人CURRENT保存所有收件人，以便同时在多个人的待办列表中显示
		else if (routeType == Route.ROUTE_TYPE_NOSERIAL || routeType == Route.ROUTE_TYPE_PARALLEL || routeType == Route.ROUTE_TYPE_APPLY) {
			// 当前执行人 CURRENT ：（清除原有数据）保存所有收件人
			for (int i = 0; i < recipientsGUIDs.length; i++) {
				addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_CURRENT, HANDEL_STATUS_TODO, i);
				addToDo(conn, user, recipientsGUIDs[i], instance, instanceGUID);
			}
			// 将所有收件人放入recipients，仅对于串并有效，发散时recipients中只放一个人
			for (int i = 0; i < recipientsGUIDs.length; i++) {
				addActor(conn, instance.getWorkflowGUID(), instanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_RECIPIENTS, HANDEL_STATUS_TODO, i);
			}
		} else if (routeType == Route.ROUTE_TYPE_EXHALE) {// 发散， 1->多发散 多->多发散
			// 复制人员纪录，先将人员信息全部取出来，放在vctInstanceActors
			// 一定要在前面先执行stmtBatch.executeBatch()，将更改写入数据库，这样读出来的才是新数据，至少对于本连接是这样的
			// instanceActors 更新，再取一次
			instanceActors = createInstanceActorsList(instanceGUID, conn);
			// 这里实际上是复制，将所有人员数据发散到每一个实例，所以不用检查人员重复问题
			for (int i = 0, m = exhaleInstanceGUIDs.size(); i < m; i++) {
				String newInstanceGUID = (String) exhaleInstanceGUIDs.get(i);
				for (int j = 0; j < this.instanceActors.size(); j++) { // 注意：this.instanceActors仅对于发散需要，已经赋了值
					TreeMap tm = (TreeMap) this.instanceActors.get(j);
					tm.put("WorkflowInstance_GUID", newInstanceGUID);
					tm.put("Actors_GUID", (new GUID()).toString()); // Primary
					// Key
					// tm.put("step", new Integer(step+1));
					DatabaseFactory.createInsert(conn, "OFFICE_WorkflowInstanceActors", tm).execute();
				}
				// current和recipients各放一个收件人，办理状态为TODO
				addActor(conn, workflow.getWorkflowGUID(), newInstanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_CURRENT, HANDEL_STATUS_TODO, 0);
				//王屹修改，传入参数instanceGUID为老instanceGUID，此处发散应传入新生成newInstanceGUID。 20070917
				//addToDo(conn, user, recipientsGUIDs[i], instance, instanceGUID);
				addToDo(conn, user, recipientsGUIDs[i], instance, newInstanceGUID);
				addActor(conn, workflow.getWorkflowGUID(), newInstanceGUID, step, recipientsGUIDs[i], ACTORS_CLASSIFY_RECIPIENTS, HANDEL_STATUS_TODO, 0);
				updateInstanceActorsStep(conn, newInstanceGUID, instance.getStep() + 1);
			}
			removeParentInstanceRecipients(conn, instance);
			addMultiPerson2Recipients(conn, recipientsGUIDs, instance, step);
		}
		updateInstanceActorsStep(conn, instanceGUID, instance.getStep() + 1);
	}
	/**
	 * 发散时，将所有的收件人加入父实例的RECIPIENTS中，以便显示历程
	 * 
	 * @param conn
	 * @param lstRecipientsGUIDs
	 * @param instance
	 * @param step
	 * @throws SQLException
	 * @throws IOException
	 */
	private void addMultiPerson2Recipients(Connection conn, List lstRecipientsGUIDs, WorkflowInstance instance, int step) throws SQLException, IOException {
		// 将所有收件人加入停止的父实例的RECIPIENTS中，以便显示历程
		for (int idx = 0; idx < lstRecipientsGUIDs.size(); idx++) {
			String[] recipientsGUIDs = (String[]) lstRecipientsGUIDs.get(idx);
			addMultiPerson2Recipients(conn, recipientsGUIDs, instance, step);
		}
	}
	/**
	 * 发散时，将所有的收件人加入父实例的RECIPIENTS中，以便显示历程
	 * 
	 * @param conn
	 * @param recipientsGUIDs
	 * @param instance
	 * @param step
	 * @throws SQLException
	 * @throws IOException
	 */
	private void addMultiPerson2Recipients(Connection conn, String[] recipientsGUIDs, WorkflowInstance instance, int step) throws SQLException, IOException {
		if (recipientsGUIDs == null){
			return;
		}
		for (int j = 0; j < recipientsGUIDs.length; j++) {
			addActor(conn, instance, recipientsGUIDs[j], ACTORS_CLASSIFY_RECIPIENTS, HANDEL_STATUS_TODO, step);
			step++;
		}
	}
	/**
	 * 发散时，删除instance的收件人，然后加入发散的新的收件人
	 * @param conn
	 * @param instance
	 * @throws SQLException
	 */
	private void removeParentInstanceRecipients(Connection conn, WorkflowInstance instance) throws SQLException {
		String sql = "DELETE FROM OFFICE_WorkflowInstanceActors WHERE WorkflowInstance_GUID='" + instance.getWorkflowInstanceGUID() + "' AND Actors_Classify=" + ACTORS_CLASSIFY_RECIPIENTS;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.execute();
		pstmt.close();
	}
	public void copyTableRowSet(Connection conn, String tableName, String instanceGUID, String newInstanceGUID) throws SQLException, IOException {
		copyTableRowSet(conn, tableName, instanceGUID, newInstanceGUID, null, null);
	}
	public void copyTableRowSet(Connection conn, String tableName, String instanceGUID, String newInstanceGUID, String pkField) throws SQLException, IOException {
		copyTableRowSet(conn, tableName, instanceGUID, newInstanceGUID, pkField, null);
	}
	/**
	 * 复制一个表的一些记录，也就是复制RowSet 对于一个工作流实例只有一条记录的主表来说，不需要后面这2个参数：pkField和pkValue
	 * 
	 * @param conn
	 * @param tableName
	 *            表名
	 * @param instanceGUID
	 *            工作流实例的GUID
	 * @param newInstanceGUID
	 *            新的工作流实例的GUID
	 * @param pkField
	 *            细表的字段名，如：Row_GUID
	 * @param pkValue
	 *            如果为null，则自动生成GUID，否则就是pkField的值
	 * @throws SQLException
	 * @throws IOException
	 */
	public void copyTableRowSet(Connection conn, String tableName, String instanceGUID, String newInstanceGUID, String pkField, String pkValue) throws SQLException, IOException {
		String sql = "SELECT * FROM " + tableName + " WHERE WorkflowInstance_GUID='" + instanceGUID + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			TreeMap tmRowData = DatabaseUtil.getTreeMap(rs);
			tmRowData.put("WorkflowInstance_GUID", newInstanceGUID);
			if (pkField != null) { // 如果是细表
				tmRowData.put(pkField, pkValue == null ? new GUID().toString() : pkValue);
			}
			DatabaseFactory.createInsert(conn, tableName, tmRowData).execute();
		}
		rs.close();
		stmt.close();
	}
	public List getCandidates(WorkflowInstance instance, RiseUser user) {
		List lst = new ArrayList(1);
		Task toTask;
		if (isReverseDirection()) {
			toTask = getTask(); // 反向,就是此action所属的task
			String[] guids = Util.list2Array(toTask.getRoleMembers(instance.getWorkflowInstanceGUID(), user));
			lst.add(guids);
			return lst;
		}
		// 正向，可能指向多个Task
		for (Iterator iter = this.routes.iterator(); iter.hasNext();) {
			 Route route = (Route) iter.next();
			 toTask = getTask().getWorkflow().lookupTask(route.getRouteToTaskGUID());
			 String[] guids = Util.list2Array(toTask.getRoleMembers(instance.getWorkflowInstanceGUID(), user));
			 lst.add(guids);
		}
		return lst;
	}
	// 覆写getRecipients(WorkflowInstance instance,RiseUser user)方法 hyc
	// 功能:返回时直接将人员添加到收件人
	public List getRecipients(WorkflowInstance instance, RiseUser user) {
		List lst = new ArrayList(1);
		if (isReverseDirection()) {
			String str = "where i.workflowinstance_guid = a.workflowinstance_guid AND i.step = a.step " + "AND i.workflowinstance_guid='" + instance.getWorkflowInstanceGUID()
				+ "' AND i.currenttask_guid='" + instance.getCurrentTask().getTaskGUID() + "'  and i.pretask_guid='" + this.getTask().getTaskGUID() + "'  and a.actors_classify="
				+ ACTORS_CLASSIFY_PREVIOUS + " order by i.step desc";
			String empGUID = null;
			Connection conn = null;
			try {
				conn = Conn.getConnection();
				Statement stmt = conn.createStatement();
				// 如果这个实例的路由状态是并行阅办=3，无序串行=2，顺序串行=1，将取得上一步骤中的发送人，作为要返回的人。
				int routeType = instance.getPreRouteType();
				if (routeType == 1 || routeType == 2 || routeType == 3) {
					String sql = "select person_guid from office_workflowtrackingactors  " + "where workflowinstance_guid='" + instance.getWorkflowInstanceGUID() + "'and actors_classify=4"+ "order by step desc ";
					ResultSet rs1 = stmt.executeQuery(sql);
					if (rs1.next()) {
						empGUID = rs1.getString("person_guid");
					}
					rs1.close();
					stmt.close();
				} else {
					String sql = "SELECT * FROM Office_WorkflowInstance i, OFFICE_WorkflowInstanceActors a " + str;
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next()) {
						empGUID = rs.getString("Person_GUID");
					}
					rs.close();
					if (empGUID == null) {
						sql = "SELECT * FROM Office_WorkflowTracking i, OFFICE_WorkflowTrackingActors a " + str;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							empGUID = rs.getString("Person_GUID");
						}
						rs.close();
					}
					stmt.close();
				}
			} catch (SQLException ex) {
				log.error("当前处于返回状态，PREVIOUS就是发送人，获取发送人错误！", ex);
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception ex) {}
				}
			}
			if (empGUID != null) {
				lst.add(new String[] { empGUID });
				return lst;
			}
		}
		return new ArrayList(0);
	}
	// 孙明添加  2013-03-25 
	private Object createObjectInstance(String fullClassName) {
        if (fullClassName == null || fullClassName.trim().length() == 0) {
              return null;
        }
        try {
           return Class.forName(fullClassName).newInstance();
        }catch (Exception ex) {
           log.error("实例化对象时发生错误！fullClassName=" + fullClassName, ex);
           return null;
        }
    }
	public List getRoutes() {
		return routes;
	}
}