package com.pmp.server.utils.mail.service.impl;

import com.pmp.server.utils.mail.EmailDetails;
import com.pmp.server.utils.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender javaMailSender;

  @Value("pms.waa@gmail.com")
  private String sender;

  public EmailServiceImpl(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }


  @Override
  public String sendSimpleMail(EmailDetails details) {
    try {

      // Creating a simple mail message
      SimpleMailMessage mailMessage
        = new SimpleMailMessage();

      // Setting up necessary details
      mailMessage.setFrom(sender);
      mailMessage.setTo(details.getRecipient());
      mailMessage.setText(details.getMsgBody());
      mailMessage.setSubject(details.getSubject());

      // Sending the mail
      javaMailSender.send(mailMessage);
      return "Mail Sent Successfully...";
    }

    // Catch block to handle the exceptions
    catch (Exception e) {
      System.out.println(e.getMessage());
      return "Error while Sending Mail";
    }
  }

  @Override
  public String sendMailWithAttachment(EmailDetails details) {
    // Creating a mime message
    MimeMessage mimeMessage
      = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper;

    try {

      // Setting multipart as true for attachments to
      // be send
      mimeMessageHelper
        = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setFrom(sender);
      mimeMessageHelper.setTo(details.getRecipient());
      mimeMessageHelper.setText(details.getMsgBody());
      mimeMessageHelper.setSubject(
        details.getSubject());

      // Adding the attachment
      FileSystemResource file
        = new FileSystemResource(
        new File(details.getAttachment()));

      mimeMessageHelper.addAttachment(
        file.getFilename(), file);

      // Sending the mail
      javaMailSender.send(mimeMessage);
      return "Mail sent Successfully";
    }

    // Catch block to handle MessagingException
    catch (MessagingException e) {

      // Display message when exception occurred
      return "Error while sending mail!!!";
    }
  }
}
