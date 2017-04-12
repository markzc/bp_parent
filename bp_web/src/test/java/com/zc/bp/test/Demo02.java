package com.zc.bp.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Demo02 {
	
	@Test
	public void test01(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-mail.xml");
		SimpleMailMessage message = (SimpleMailMessage) ac.getBean("mailMessage");
		message.setTo("mark@zc.com");
		message.setSubject("test02");
		message.setText("sssssssssssssssss");
		JavaMailSender javaMailSender=(JavaMailSender) ac.getBean("mailSender");
		javaMailSender.send(message);
	}

}
