package com.ticket.master.common;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ticket.master.dao.impl.InterfacelogDaoImpl;
import com.ticket.master.dao.impl.ParentDaoImpl;
import com.ticket.master.dao.impl.UserDaoImpl;
import com.ticket.master.entity.User;
import com.ticket.master.entity.UserInterface;

@SuppressWarnings("static-access")
public class CheckInterface {
	UserDaoImpl userDaoImpl = new UserDaoImpl();
	ParentDaoImpl pdi = new ParentDaoImpl();
	TimeUtil time = new TimeUtil();

	public String checkInterface(String account, String md5, String xml,
			String methodName) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String str = "";
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			String responseDataType = root.elementText("ResponseDataType");
			try {
				xml = xml.replace(root.elementText("LoginUserId"), account);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			if (user == null) {
				if (responseDataType.equals(2)) {
					str = "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
							+ "不存在\"}}";
				} else {
					str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ "<Response>"
							+ "<Status>-1</Status>"
							+ "<Error>用户"
							+ account
							+ "不存在</Error>"
							+ "</Response>";
				}
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
						+ "("
						+ 0
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime() + "','" + xml + "','" + str + "')";
				pdi.allSql(insertSql);
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				if (responseDataType.equals(2)) {
					str = "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";
				} else {
					str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ "<Response>"
							+ "<Status>-1</Status>"
							+ "<Error>校验出错，密钥验证的失败</Error>" + "</Response>";
				}
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime() + "','" + xml + "','" + str + "')";
				pdi.allSql(insertSql);
			} else {
				InterfacelogDaoImpl interfacelogDaoImpl = new InterfacelogDaoImpl();
				UserInterface userInterface = interfacelogDaoImpl
						.getInterfaceByInterfaceName(account, interfaceId);
				int count = interfacelogDaoImpl
						.getInterfaceCountByInterfaceName(account, interfaceId);
				if (userInterface == null) {

					String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
							+ "("
							+ 0
							+ ","
							+ interfaceId
							+ ",'"
							+ time.getSysTime()
							+ "','"
							+ xml
							+ "','"
							+ this.getResultXmlOrJson(responseDataType,
									"你没有使用该接口的权限") + "')";
					pdi.allSql(insertSql);
					return this.getResultXmlOrJson(responseDataType,
							"你没有使用该接口的权限");
				} else if (userInterface.getInterfaceState() == 0) {
					String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
							+ "("
							+ 0
							+ ","
							+ interfaceId
							+ ",'"
							+ time.getSysTime()
							+ "','"
							+ xml
							+ "','"
							+ this.getResultXmlOrJson(responseDataType,
									"你没有使用该接口的权限") + "')";
					pdi.allSql(insertSql);
					return this.getResultXmlOrJson(responseDataType, "该接口已关闭");
				} else if (count > userInterface.getInterfaceAccount()) {
					String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
							+ "("
							+ 0
							+ ","
							+ interfaceId
							+ ",'"
							+ time.getSysTime()
							+ "','"
							+ xml
							+ "','"
							+ this.getResultXmlOrJson(responseDataType,
									"你没有使用该接口的权限") + "')";
					pdi.allSql(insertSql);
					return this.getResultXmlOrJson(
							responseDataType,
							"你已超出使用该接口"
									+ (count - userInterface
											.getInterfaceAccount()));
				} else {
					str = "ok";
				}
			}
		} catch (Exception e) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Status>-1</Status>"
					+ "<Error>XML格式错误</Error>" + "</Response>";
		}
		return str;
	}

	public String getResultXmlOrJson(String responseDataType, String content) {
		if (responseDataType.equals("2")) {
			String str = "{\"Res\": {\"Status\": -1,\"Error\": \"" + content
					+ "\"}}";
			return str;
		} else {
			String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Status>-1</Status>"
					+ "<Error>"
					+ content + "</Error>" + "</Response>";
			return str;
		}

	}
}
