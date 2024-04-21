package yeonji.jsp_submit;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public static void main(String[] args) {
        String user = "Gmail 계정"; // Gmail 계정
        String password = "발신자 계정 비밀번호, 구글 앱 비밀번호"; // 보안 앱 비밀번호 또는 Gmail 비밀번호

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        session.setDebug(true);  // Debug 모드 활성화

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));  // 발신자 이메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("수신자 이메일 주소"));  // 수신자 이메일 주소
            message.setSubject("제목을 입력하세요");  // 메일 제목
            message.setText("내용을 입력하세요");     // 메일 내용

            Transport.send(message);  // 전송
            System.out.println("메시지 전송 완료...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}

