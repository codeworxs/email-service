/**
 * AuthorizationServiceMailController.java
 * @author Indika Munaweera {indika@resume.lk}
 * @since May 7, 2019
 */
package com.codeworxs.resume.mailservice.api.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeworxs.resume.mailservice.api.service.PasswordResetEmailService;
import com.codeworxs.resume.mailservice.api.service.UserSignUpConfirmationEmailService;

@RestController
@RequestMapping("/v1/email/authorization")
public class AuthorizationServiceMailController {
  
  @Autowired
  private UserSignUpConfirmationEmailService confirmationEmailService;
  
  @Autowired
  private PasswordResetEmailService passwordResetEmailService;
  

  @PostMapping("/user-signup-confirmation")
  public void sendConfirmationEmail(@RequestParam Map<String,String> emailData) {
    confirmationEmailService.send(emailData);
  }
  
  @PostMapping("/password-reset-email")
  public void sendPasswordResetEmailService(@RequestParam Map<String,String> emailData) {
    passwordResetEmailService.send(emailData);
  }

}
