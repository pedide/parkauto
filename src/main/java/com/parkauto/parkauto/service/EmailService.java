package com.parkauto.parkauto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.parkauto.parkauto.constant.EmailConstant.EMAIL_SUBJECT;
import static com.parkauto.parkauto.constant.EmailConstant.FROM_EMAIL;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String toEmail, String subject, String message){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(FROM_EMAIL);

        javaMailSender.send(mailMessage);

    }

    public void sendConfirmRegister(String email, String firstName, String password){
        String subject = EMAIL_SUBJECT;
        String message= "Salut"+firstName+", \n\nVoici votre mot de passe :"+password+"";
        sendEmail(email,subject,message);

    }
    public void sendResetPassword(String email, String fisrtName, String lien){
        String subject ="Réinitilisation du mot de passe";
        String message ="Salut "+fisrtName+ ",\n\nVoici le lien pour réinitialiser votre mot de passe"+lien+"";
        sendEmail(email,subject,message);

    }
}
