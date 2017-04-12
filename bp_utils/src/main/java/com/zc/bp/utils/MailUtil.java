package com.zc.bp.utils;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 电子邮件工具类
 * @author Administrator
 *
 */
public class MailUtil {
	
	
	/**
	 * 发送电子邮件
	 * @param addr 收件人地址
	 * @param subject 主题
	 * @param text 内容
	 * @throws MessagingException
	 */
	public static void sendMail(String addr,String subject,String text) throws MessagingException{
		Properties props=new Properties();
		props.put("mail.smtp.host","127.0.0.1");
		props.put("mail.smtp.auth","true");
		Session session=Session.getInstance(props);
		//构造信息体 
		MimeMessage message =new MimeMessage(session);
		 //发件地址 
		Address address = new InternetAddress("john@zc.com"); 
		message.setFrom(address);
		//收件地址 
		Address toAddress = new InternetAddress(addr); 
		message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
		//主题 
		message.setSubject(subject);
		//正文 
		message.setText(text);
		message.saveChanges();
		Transport transport = session.getTransport("smtp"); 
		transport.connect("localhost", "john@zc.com", "123456"); //发送 
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
	}
	
}
