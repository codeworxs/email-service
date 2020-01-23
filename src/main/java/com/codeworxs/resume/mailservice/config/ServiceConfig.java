/**
 * PropertyConfig.java
 * @author Indika Munaweera {indika@resume.lk}
 * @since May 7, 2019
 */
package com.codeworxs.resume.mailservice.config;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
@Primary
@Scope("singleton")
public class ServiceConfig {
  
  @Value("${codeworxs.user.email.confirm.address}")
  private String confirmEmailFrom;
  
  @Value("${codeworxs.user.password.reset.confirm.uri}")
  private String passwordResetConfirmUri;
  
  @Value("${codeworxs.user.password.reset.form.uri}")
  private String passwordResetFormUri;
  
  @Value("${codeworxs.company.name}")
  private String companyName;
  
  public InternetAddress getConfirmEmailFromInternetAddress() throws UnsupportedEncodingException {
    return new InternetAddress(confirmEmailFrom, companyName);
  }

}
