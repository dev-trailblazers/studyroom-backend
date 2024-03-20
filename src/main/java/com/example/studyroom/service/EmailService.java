package com.example.studyroom.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final String SENDER_EMAIL;

    public EmailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String SENDER_EMAIL) {
        this.javaMailSender = javaMailSender;
        this.SENDER_EMAIL = SENDER_EMAIL;
    }


    public String sendAuthCode(String mail) {
        String verificationCode = generateAuthCode();
        MimeMessage message = createMail(mail, verificationCode);
        javaMailSender.send(message);

        return verificationCode;
    }

    private MimeMessage createMail(String mail, String verificationCode) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(SENDER_EMAIL);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("StudyRoom 이메일 인증을 요청하셨습니다.");
            String body = String.format("""
                    <body>
                      <h2 style="color: #587FA7; padding: 10px; text-align: left;">StudyRoom</h2>
                      <div style="width:500px; margin-left: 10px; border: 0.5px solid gray; border-radius: 10px;">
                        <h2 style="text-align: center;">StudyRoom 이메일 인증</h2>
                        <div style="padding: 10px; margin: 10px; line-height: 1.5;">
                          <strong>StudyRoom 회원가입을 위한 이메일 인증을 요청하였습니다.</strong>
                          <br><br>
                          StudyRoom 회원 가입을 하기 위해 사용됩니다.<br>
                          아래 코드를 회원가입 창으로 돌아가 이메일 인증을 진행해주세요.<br>
                        </div>
                        <div style="padding: 10px; margin: 10px; border: 1px solid lightgray; border-radius: 10px;">
                          <span style="font-weight: bold;">인증번호:</span>
                          <span style="color:#587FA7; font-weight: bold;">%s</span>
                        </div>
                      </div>
                    </body>
                    """, verificationCode);
            message.setText(body, "UTF-8", "html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private String generateAuthCode() {
        return String.format("%06d", (int) (Math.random() * 1_000_000));
    }

}
