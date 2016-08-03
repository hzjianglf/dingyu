package com.ibm.alert;

import javax.mail.*; 

public class MailAuthenticator extends Authenticator { 

    //****************************** 

    //由于发送邮件的地方比较多, 

    //下面统一定义用户名,口令. 

    //****************************** 

    public String MAIL_USER = ""; 
    public String MAIL_PASSWORD = ""; 

 
    public MailAuthenticator(String mail_user, String mail_password) { 
    	this.MAIL_USER = mail_user;
    	this.MAIL_PASSWORD = mail_password;
    } 

  

    protected PasswordAuthentication getPasswordAuthentication() { 
        return new PasswordAuthentication(MAIL_USER, MAIL_PASSWORD); 
    } 

} 

