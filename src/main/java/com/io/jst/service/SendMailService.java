package com.io.jst.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SendMailService {

    private final JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "slekffl67@naver.com";

    public int mailSend(String email){

        Random random = new Random();

        System.out.println("이멜 전송 완료!");

        int num = random.nextInt(888888)+111111;
        String content ="인증번호는 "+num+" 입니다";

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(FROM_ADDRESS);
            helper.setTo(email);
            helper.setSubject("인증번호");
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText(content);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        javaMailSender.send(message);
        return num;

    }


}
