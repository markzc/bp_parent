package com.zc.bp.test;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;

public class Demo01 {

	@Test
	public void test01() throws Exception{
		Properties properties = new Properties();
		properties.put("mail.smtp.host","127.0.0.1");
		properties.put("mail.smtp.auth","true");
		
		Session session = Session.getInstance(properties);
		MimeMessage message = new MimeMessage(session);
		
		Address fromAddr = new InternetAddress("john@zc.com");
		message.setFrom(fromAddr);
		
		Address toAddress = new InternetAddress("mark@zc.com");
		message.setRecipient(RecipientType.TO,toAddress);
		
		message.setSubject("项目进程！");
		message.setText("aaaaaaaaaaaa.aaaaaaaaaaaaa");
		message.saveChanges();
		
		Transport transport=session.getTransport("smtp");
		transport.connect("localhost","john@zc.com","123456");
		transport.sendMessage(message, message.getAllRecipients());
		
		transport.close();
	}
}
