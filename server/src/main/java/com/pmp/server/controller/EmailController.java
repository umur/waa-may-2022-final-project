package com.pmp.server.controller;

import com.pmp.server.utils.mail.EmailDetails;
import com.pmp.server.utils.mail.service.impl.EmailServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
public class EmailController {

  private final EmailServiceImpl emailService;

  public EmailController(EmailServiceImpl emailService) {
    this.emailService = emailService;
  }

  // Sending a simple Email
  @PostMapping("/sendMail")
  public String
  sendMail(@RequestBody EmailDetails details)
  {
    String status
      = emailService.sendSimpleMail(details);

    return status;
  }

  // Sending email with attachment
  @PostMapping("/sendMailWithAttachment")
  public String sendMailWithAttachment(
    @RequestBody EmailDetails details)
  {
    String status
      = emailService.sendMailWithAttachment(details);

    return status;
  }
}
