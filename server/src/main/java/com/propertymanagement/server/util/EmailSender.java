package com.propertymanagement.server.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailSender {
    private final JavaMailSender mailSender;

    public void sendEmail(String email, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("propertymanagementwaa@gmail.com");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hi,</p>"
                + "<p>Please click this link to reset your password.</p>"
                + "<a href=\"" + link + "\">Reset my password</a>"
                + "</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
