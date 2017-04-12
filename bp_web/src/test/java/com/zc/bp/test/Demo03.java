package com.zc.bp.test;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Demo03 {

	@Test
	public void test01() throws Exception{
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-mail.xml");
		JavaMailSender  mailSender = (JavaMailSender ) ac.getBean("mailSender");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
		messageHelper.setFrom("john@zc.com");
		messageHelper.setTo("mark@zc.com");
		messageHelper.setSubject("test05");
		messageHelper.setText("<html><head></head><body><h1>hello!!spring image html mail</h1><a href=http://www.baidu.com>baidu</a><br/><img src='cid:image' /></body></html>", true);
		FileSystemResource imResource = new FileSystemResource(new File("E:/aaa.jpg"));
		messageHelper.addInline("image", imResource);
		FileSystemResource imResource01 = new FileSystemResource(new File("D:/bssndrpt.lg"));
		messageHelper.addAttachment("下载", imResource01);
		mailSender.send(message);
	}
}
