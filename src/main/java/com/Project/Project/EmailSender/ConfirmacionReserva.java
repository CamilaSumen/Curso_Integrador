/*package com.Project.Project.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ConfirmacionReserva {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("camilatasu@icloud.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setText(subject);

        mailSender.send(message);

        System.out.println("Mail sent successfully");
    }
}
*/