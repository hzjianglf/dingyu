package com.ibm.alert;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.SMTPTransport;

public class MailTo {
	// 要发送Mail地址
	private String mailTo = null;

	// Mail发送的起始地址
	private String mailFrom = null;

	// SMTP主机地址
	private String smtpHost = null;

	// 是否采用调试方式
	private boolean debug = false;

	private String messageBasePath = null;

	// Mail主题
	private String subject;

	// Mail内容
	private String msgContent;

	private Vector attachedFileList;

	private String mailAccount = null;
	
	private String mailPassword = null;

	private String messageContentMimeType = "text/html; charset=gbk";

	private String mailbccTo = null;

	private String mailccTo = null;

	/**
	 * 
	 * SendMailService 构造子注解。
	 * 
	 */
	public MailTo() {

	}

	private void fillMail(Session session, MimeMessage msg) throws IOException,
			MessagingException {

		String fileName = null;

		Multipart mPart = new MimeMultipart();
		if (mailFrom != null) {
			msg.setFrom(new InternetAddress(mailFrom));
//			System.out.println("发送人Mail地址：" + mailFrom);

		} else {
			System.out.println("没有指定发送人邮件地址！");
			return;
		}

		if (mailTo != null) {
			InternetAddress[] address = InternetAddress.parse(mailTo);
			msg.setRecipients(Message.RecipientType.TO, address);
//			System.out.println("收件人Mail地址：" + mailTo);

		} else {

			System.out.println("没有指定收件人邮件地址！");
			return;

		}

		if (mailccTo != null) {
			InternetAddress[] ccaddress = InternetAddress.parse(mailccTo);
			System.out.println("CCMail地址：" + mailccTo);
			msg.setRecipients(Message.RecipientType.CC, ccaddress);
		}

		if (mailbccTo != null) {
			InternetAddress[] bccaddress = InternetAddress.parse(mailbccTo);
			System.out.println("BCCMail地址：" + mailbccTo);
			msg.setRecipients(Message.RecipientType.BCC, bccaddress);

		}

		msg.setSubject(subject);

		InternetAddress[] replyAddress = { new InternetAddress(mailFrom) };

		msg.setReplyTo(replyAddress);

		// create and fill the first message part

		MimeBodyPart mBodyContent = new MimeBodyPart();

		if (msgContent != null)
			mBodyContent.setText(msgContent);
		else
			mBodyContent.setContent("", messageContentMimeType);

		mPart.addBodyPart(mBodyContent);
		// attach the file to the message
		if (attachedFileList != null) {

			for (Enumeration fileList = attachedFileList.elements(); fileList
					.hasMoreElements();) {

				fileName = (String) fileList.nextElement();

				MimeBodyPart mBodyPart = new MimeBodyPart();

				// attach the file to the message

				FileDataSource fds = new FileDataSource(messageBasePath
						+ fileName);

				System.out.println("Mail发送的附件为：" + messageBasePath + fileName);

				mBodyPart.setDataHandler(new DataHandler(fds));
				mBodyPart.setFileName(fileName);
				mPart.addBodyPart(mBodyPart);
			}
		}

		msg.setContent(mPart);
		msg.setSentDate(new Date());
	}

	/**
	 * 
	 * 发送e_mail，返回类型为int
	 * 
	 * 当返回值为0时，说明邮件发送成功
	 * 
	 * 当返回值为3时，说明邮件发送失败
	 * 
	 */

	public int sendMail() throws IOException, MessagingException {

		Properties props = System.getProperties();
				
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");

		MailAuthenticator auth = new MailAuthenticator(mailAccount, mailPassword);

		Session session = Session.getInstance(props, auth);

		MimeMessage msg = new MimeMessage(session);

		SMTPTransport trans = null;

		try {

			fillMail(session, msg);
			// send the message
			trans = (SMTPTransport)session.getTransport("smtp");

			try {
				trans.connect(smtpHost, mailAccount, mailPassword);

			} catch (AuthenticationFailedException e) {

				e.printStackTrace();
				System.out.println("连接邮件服务器错误,认证失败：");
				return 3;

			} catch (MessagingException e) {
				System.out.println("连接邮件服务器错误：");
				e.printStackTrace();
				return 3;
			}

			trans.send(msg);
			trans.close();

		} catch (MessagingException mex) {

			System.out.println("发送邮件失败：");
			mex.printStackTrace();
			Exception ex = null;

			if ((ex = mex.getNextException()) != null) {

				System.out.println(ex.toString());
				ex.printStackTrace();

			}

			return 3;

		} finally {

			try {

				if (trans != null && trans.isConnected())

					trans.close();

			} catch (Exception e) {

				System.out.println(e.toString());

			}

		}

		//System.out.println("发送邮件成功！");
		return 0;
	}

	public void setAttachedFileList(java.util.Vector filelist){
		attachedFileList = filelist;
	}

	public void setDebug(boolean debugFlag){
		debug = debugFlag;
	}

	public void setMailAccount(String strAccount) {
		mailAccount = strAccount;
	}

	public void setMailbccTo(String bccto) {
		mailbccTo = bccto;
	}

	public void setMailccTo(String ccto) {
		mailccTo = ccto;
	}

	public void setMailFrom(String from) {
		mailFrom = from;
	}

	public void setMailPassword(String strMailPassword) {
		mailPassword = strMailPassword;
	}

	public void setMailTo(String to) {
		mailTo = to;
	}

	public void setMessageBasePath(String basePath){
		messageBasePath = basePath;
	}

	public void setMessageContentMimeType(String mimeType){
		messageContentMimeType = mimeType;
	}

	public void setMsgContent(String content){
		msgContent = content;
	}

	public void setSMTPHost(String host){
		smtpHost = host;
	}

	public void setSubject(String sub){
		subject = sub;
	}

	public static void main(String[] argv) throws Exception {

		for (int i = 0; i < 1; i++) {

			MailTo sm = new MailTo();
			
			sm.setSMTPHost("smtp.163.com");
			sm.setMailAccount("guran_cn@163.com");
			sm.setMailPassword("1234567890");
			sm.setMailFrom("guran_cn@163.com");
			sm.setMailTo("guran.cn@gmail.com");
			
			sm.setMsgContent("文件XXX发送失败");

			sm.setSubject("文件发送提醒");

			sm.sendMail();

		}

	}

}
