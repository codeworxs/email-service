package com.codeworxs.resume.mailservice.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * ApplicationConfig.java
 * @author indika munaweera (indika@resume.lk)
 *
 * @since 2018-11-15
 */
@Configuration
@EnableAsync
public class ApplicationConfig{ 

  @Bean(name = "threadPoolTaskExecutor")
  public Executor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(5);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("AsyncMailServices-");
    executor.initialize();
    return executor;
  }
  
  @Bean
  @Primary
  public FreeMarkerConfigurer getFreeMarkerConfiguration() {
    FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer(); 
    freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_28);
    cfg.setClassForTemplateLoading(this.getClass(), "/templates/email");
    cfg.setDefaultEncoding("UTF-8");
    freeMarkerConfigurer.setConfiguration(cfg);
      return freeMarkerConfigurer;
  }
}
