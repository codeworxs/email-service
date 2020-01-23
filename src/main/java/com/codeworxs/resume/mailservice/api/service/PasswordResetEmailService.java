package com.codeworxs.resume.mailservice.api.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.codeworxs.resume.mailservice.api.email.EmailService;

import freemarker.template.Template;
import lombok.extern.log4j.Log4j2;


/**
 * ConfirmationEmailService.java
 * @author Indika Munaweera {indika@resume.lk}
 * @since Dec 20, 2018
 */
@Log4j2
@Service
public class PasswordResetEmailService extends EmailService<Map<String, String>> {

  /** The confirmation email freemarker template. */
  @Value("${codeworxs.user.password.reset.email.template}")
  private String passwordResetEmailTemplate;
  
  @Value("${codeworxs.user.password.reset.email.subject}")
  private String passwordResetEmailSubject;

  @Override
  @Async("threadPoolTaskExecutor")
  public void send(Map<String, String> data) {
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
      Template template = freemarkerConfig.getTemplate(passwordResetEmailTemplate);
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
      helper.setTo(data.get("toEmail"));
      helper.setText(html, true);
      helper.setSubject(passwordResetEmailSubject);
      helper.setFrom(serviceConfig.getConfirmEmailFromInternetAddress());
      emailSender.send(message);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

  }

}
