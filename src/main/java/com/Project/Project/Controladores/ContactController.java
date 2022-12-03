package com.Project.Project.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Controller
public class ContactController {
    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/contact")
    public String showContactForm(){
        return "usuario/escribenos";
    }
    @PostMapping("/contact")
    public String submitContact(HttpServletRequest request,
                                @RequestParam("attachment") MultipartFile multipartFile
    ) throws MessagingException, UnsupportedEncodingException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        String mailSubject = fullname + " envió un mensaje";
        String mailContent = "<p><b>Enviado por: </b>"+fullname+"</p>";
        mailContent+= "<p><b>Enviado por: </b>"+email+"</p>";
        mailContent+= "<p><b>Asunto: </b>"+subject+"</p>";
        mailContent+= "<p><b>Contenido: </b>"+content+"</p>";
        //mailContent+= "<hr><img src='cid:EMPRESALOGO'/>";

        helper.setFrom("lacanterapolleria@gmail.com","contacto");
        helper.setTo("lacanterapolleria@gmail.com");
        helper.setSentDate(new Date());
        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        //ClassPathResource resource = new ClassPathResource("/img/logo.jpg");
        //helper.addInline("EMPRESALOGO",resource);

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            InputStreamSource source = new InputStreamSource() {
                @Override
                public InputStream getInputStream() throws IOException {
                    return multipartFile.getInputStream();
                }
            };
            helper.addAttachment(fileName,source);
        }

        emailSender.send(message);

        return "usuario/escribenos";

    }
}
