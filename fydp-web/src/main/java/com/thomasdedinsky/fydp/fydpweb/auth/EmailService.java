package com.thomasdedinsky.fydp.fydpweb.auth;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    //private final JavaMailSender javaMailSender;
    public EmailService(/*JavaMailSender javaMailSender*/) {
        super();
        //this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        //javaMailSender.send(email);
    }
}
