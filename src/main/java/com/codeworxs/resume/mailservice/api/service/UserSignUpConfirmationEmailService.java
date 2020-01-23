package com.codeworxs.resume.mailservice.api.service;

import java.nio.charset.StandardCharsets;
import java.util.AbstractMap.SimpleEntry;
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
public class UserSignUpConfirmationEmailService extends EmailService<Map<String, String>>{

  /** The confirmation email freemarker template. */
  @Value("${codeworxs.user.email.confirm.template}")
  private String confirmationEmailTemplate;
  
  @Value("${codeworxs.user.email.confirm.subject}")
  private String moaConfirmEmailSubject;

  @Override
  @Async("threadPoolTaskExecutor")
  public void send(Map<String, String> data) {
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
      Template template = freemarkerConfig.getTemplate(confirmationEmailTemplate);
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,
          Map.ofEntries(
              new SimpleEntry<String,String>("confirmationlink", data.get("confirmPath")),
              new SimpleEntry<String,String>("name", data.get("name")),
              new SimpleEntry<String,String>("user", data.get("user"))
          ));
      helper.setTo(data.get("toEmail"));
      helper.setText(html, true);
      helper.setSubject(moaConfirmEmailSubject);
      helper.setFrom(serviceConfig.getConfirmEmailFromInternetAddress());
      emailSender.send(message);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

}
