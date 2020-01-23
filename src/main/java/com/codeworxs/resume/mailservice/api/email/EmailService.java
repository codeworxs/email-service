package com.codeworxs.resume.mailservice.api.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.codeworxs.resume.mailservice.config.ServiceConfig;

import freemarker.template.Configuration;

/**
 * EmailService.java
 * @author Indika Munaweera {indika@resume.lk}
 * @since Dec 20, 2018
 */
public abstract class EmailService<T> {
  /** The email sender. */
  @Autowired
  protected JavaMailSender emailSender;

  /** The freemarker config. */
  @Autowired
  protected Configuration freemarkerConfig;
  
  @Autowired
  protected ServiceConfig serviceConfig;
  
  public abstract void send(T t);
}
