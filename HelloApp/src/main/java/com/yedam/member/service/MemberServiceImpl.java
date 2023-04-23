package com.yedam.member.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService{
	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return mapper.loginCheck(vo);
	}

	@Override
	public boolean modifyMember(MemberVO vo) {
		return mapper.updateMember(vo)==1;
	}

	@Override
	public MemberVO getMember(String email) {
		// TODO Auto-generated method stub
		return mapper.getMember(email);
	}
	@Override
	public void gmailSend() {
        String user = "ss411995@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String password = "zkdltkfm50!";   // 패스워드

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        prop.put("mail.smtp.port", 465); 
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("tsd02150@naver.com")); 

            // Subject
            message.setSubject("UI테스트 메세지입니다"); //메일 제목을 입력

            // Text
            message.setText("test");    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
}
