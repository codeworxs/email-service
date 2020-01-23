package com.codeworxs.resume.mailservice.api.email;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * Email.java
 * @author indika munaweera (indika@resume.lk)
 *
 * @since 2018-11-15
 */
@Data
@Builder
public class Email {
  
  /** The from. */
  private String from;
  
  /** The to. */
  private String to;
  
  /** The subject. */
  private String subject;
  
  /** The attachments. */
  private List<Object> attachments;
  
  /** The model. */
  private Map<String, Object> model;
  
}
