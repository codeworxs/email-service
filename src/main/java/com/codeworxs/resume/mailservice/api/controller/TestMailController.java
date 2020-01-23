/*Copyright 2018-2019 Creative Software.*/
package com.codeworxs.resume.mailservice.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeworxs.resume.mailservice.api.service.TestEmailService;

/**
 * TestMailController.java
 * @auther indika munaweera (indika@resume.lk)
 * @since 1.0.0
 **/
@RestController
@RequestMapping("/v1/email/custom")
public class TestMailController {

    @Autowired
    private TestEmailService testEmailService;

    @PostMapping("/mail")
    public void sendConfirmationEmail(@RequestParam Map<String,String> emailData) {
    	testEmailService.send(emailData);
    }
}
