package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.QuestionTishi;
import com.wxpt.site.entity.Vote;
import com.wxpt.site.entity.pojo.VotePojo;
import com.wxpt.site.service.QuestionService;
import com.wxpt.site.service.UserService;

@Results({ @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name="successs" ,location="/WEB-INF/content/site/successs.jsp")
})
@ParentPackage("json-default")
public class ManageVoteAction extends ParentAction {
	final HttpServletRequest final_request = ServletActionContext.getRequest();
	final HttpServletResponse final_response = ServletActionContext
			.getResponse();
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	String name = cookies.getCookieName();
	UserService userService = null;
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;
	List<Vote> list = new ArrayList<Vote>();
	List<VotePojo> voteList = new ArrayList<VotePojo>();
	private String huodong;
	private String userIds;

	public void saveVote() throws IOException {
		int data;
		HttpServletResponse hs = ServletActionContext.getResponse();
		hs.setContentType("text/html;charset=utf-8");
		PrintWriter out = hs.getWriter();
		System.out.println(huodong);
		String sql = "SELECT * FROM wxpt" + enterId
				+ ".vote WHERE `voteUser`='" + huodong + "' and `state`=0";
		Vote vote = userService.getByVoteId(sql);
		if (vote == null) {
			data = 1;

			userService.saveVote(enterId, huodong);
			out.print(data);
		} else {
			data = 2;
			out.print(data);
		}

	}

	public String delete() {
		String ids[] = userIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			try {
				String sql = "DELETE FROM wxpt" + enterId
						+ ".vote WHERE `voteId`=" + Integer.parseInt(ids[i]);
				userService.deleteVote(sql);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
		return "success";
	}

	public String getVoteList() {
		// this.pageList(page, rows);
		String sql = "SELECT * FROM wxpt" + enterId + ".vote";

		System.out.println(rows + ":" + page);
		// List<EnterInfor>list=enterService.getAll( sql,page, rows);
		list = userService.getAll(sql, page, rows);
		count = userService.getAll(sql).size();

		for (int i = 0; i < list.size(); i++) {
			VotePojo pojo = new VotePojo();
			pojo.setVoteStartTime(list.get(i).getVoteStartTime());
			pojo.setVoteEndTime(list.get(i).getVoteEndTime());
			pojo.setVoteUser(list.get(i).getVoteUser());
			pojo.setVoteId(list.get(i).getVoteId());
			if (list.get(i).getState() == 0) {
				pojo.setState("开启");
			} else {
				pojo.setState("关闭");
			}
			voteList.add(pojo);
		}

		try {
			JsonConfig jsonConfig = new JsonConfig

			();
			jsonls = JSONArray.fromObject(voteList);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		// count = enterService.enterCount(sql);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	public void update() {
		System.out.println(userIds);
		String[] voteId = userIds.split(",");
		for (int i = 0; i < voteId.length; i++) {
			String sql = "SELECT * FROM wxpt" + enterId
					+ ".vote WHERE `voteId`=" + voteId[i];
			Vote vote = userService.getByVoteId(sql);
			vote.setVoteEndTime(TimeUtil.getTime());
			vote.setState(1);
			userService.updateVote(enterId, vote);
		}

	}

	private String tpc;
	private String ytp;
	private String tpId;
	@Autowired
	QuestionService questionService;
	JSONArray jsonArrayFromList;
	private int state;
	List<QuestionTishi> tishiList = new ArrayList<QuestionTishi>();

	// 规则设置 添加
	public void addVoteSheZhi() {
		String sql = "INSERT INTO wxpt"
				+ super.getCookieByEnterID()
				+ ".question_tishi( `mei_jiang`, `choujiang`, `type`) VALUES ('"
				+ tpc + "','" + ytp + "',2)";
		try {
			questionService.addRule(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取规则
	public void getVoteSz() {
		String sql = "SELECT * FROM wxpt" + super.getCookieByEnterID()
				+ ".question_tishi where type=2";
		try {
			tishiList = questionService.getAllTishi(sql);
			JsonConfig jsonConfig = new JsonConfig();
			jsonArrayFromList = JSONArray.fromObject(tishiList);
			super.out.print("{\"total\":1,\"rows\":" + jsonArrayFromList + "}");

			super.out.flush();
			super.out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 修改
	public void updateTishi() {
		String sql = "UPDATE wxpt" + super.getCookieByEnterID()
				+ ".question_tishi SET `mei_jiang`='" + tpc + "',`choujiang`='"
				+ ytp + "' WHERE `question_tishi_id`=" + tpId + "";
		try {
			questionService.updateRule(sql, super.getCookieByEnterID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String panduan() {
		String sql = "SELECT * FROM wxpt" + super.getCookieByEnterID()
				+ ".question_tishi where type=2";
		tishiList = questionService.getAllTishi(sql);
		state = tishiList.size();
		return "successs";
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public GetCookie getCookies() {
		return cookies;
	}

	public void setCookies(GetCookie cookies) {
		this.cookies = cookies;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public JSONArray getJsonls() {
		return jsonls;
	}

	public void setJsonls(JSONArray jsonls) {
		this.jsonls = jsonls;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Vote> getList() {
		return list;
	}

	public void setList(List<Vote> list) {
		this.list = list;
	}

	public String getHuodong() {
		return huodong;
	}

	public void setHuodong(String huodong) {
		this.huodong = huodong;
	}

	public void setVoteList(List<VotePojo> voteList) {
		this.voteList = voteList;
	}

	public String getTpc() {
		return tpc;
	}

	public void setTpc(String tpc) {
		this.tpc = tpc;
	}

	public String getYtp() {
		return ytp;
	}

	public void setYtp(String ytp) {
		this.ytp = ytp;
	}

	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
