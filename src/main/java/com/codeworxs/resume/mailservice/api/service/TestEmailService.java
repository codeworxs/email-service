package com.codeworxs.resume.mailservice.api.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.codeworxs.resume.mailservice.api.email.EmailService;

import lombok.extern.log4j.Log4j2;


/**
 * ConfirmationEmailService.java
 * @author Indika Munaweera {indika@resume.lk}
 * @since Dec 20, 2018
 */
@Log4j2
@Service
public class TestEmailService extends EmailService<Map<String, String>> {

  @Override
  @Async("threadPoolTaskExecutor")
  public void send(Map<String, String> data) {
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
      helper.setTo(data.get("toEmail"));
      helper.setText(data.get("body"), true);
      helper.setSubject(data.get("subject"));
      helper.setFrom(data.get("from"));
      emailSender.send(message);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

  }

}
