package com.risk.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.risk.models.AirportRecord;
import com.risk.models.StoreRecord;

@Configuration
public class CommonConfig {
//  @Value("${url.connection.timeout}")
//  int timeout;

  @Bean
  public StoreRecord store() {
    return new StoreRecord();
  }
  @Bean
  public RestTemplate restTemplate(
          RestTemplateBuilder restTemplateBuilder) {

      return restTemplateBuilder
              .setConnectTimeout(2000)
              .setReadTimeout(2000)
              .build();
  }
  @Bean
  public AirportRecord airportRecord() {
    return new AirportRecord();
  }
}
