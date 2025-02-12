package com.vam.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MailTestController {
	
	@Autowired
	JavaMailSender mailSender;
	
//	@Test
//	public void sendMailTest2() throws Exception {
//		String subject = "test메일";
//		String content = "스프링 프젝 이메일 전송";
//		String from ="anszl789@naver.com";
//		String to = "anszl789@naver.com";
//		try {
//		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
//			
//			@Override
//			public void prepare(MimeMessage mimeMessage) throws Exception {
//				// TODO Auto-generated method stub
//				final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
//				
//				mailHelper.setFrom(from);
//				mailHelper.setTo(to);
//				mailHelper.setSubject(subject);
//				mailHelper.setText(content,true);
//			}
//		};
//		
//		
//			mailSender.send(preparator);
//		} catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//	}

}
