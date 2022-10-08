package com.figtreelake.multiplechains.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MultipleChainsConfiguration {

  @Bean
  public WebClient createWebClient() {
    System.out.println("Creating webclient");
    return WebClient.create();
  }
}
