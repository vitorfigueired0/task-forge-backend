package com.vitorfigueired0.taskforge.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String hostMail;

  @Async
  public void sendVerificationCode(MailDTO mailDTO) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

    simpleMailMessage.setFrom(hostMail);
    simpleMailMessage.setTo(mailDTO.getDestinyMail());
    simpleMailMessage.setSubject(mailDTO.getTitle());
    simpleMailMessage.setText(mailDTO.getText());

    mailSender.send(simpleMailMessage);
  }


}
