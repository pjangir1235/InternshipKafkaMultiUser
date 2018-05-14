package com.risk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.risk.models.AirportRecord;
import com.risk.services.interfaces.MainService;

@SpringBootApplication
@EnableJpaRepositories("com.risk.producer.intefacerepo")
@EntityScan("com.risk.producer.model")
public class Main {
  @Autowired MainService producer;
  @Autowired AirportRecord recordAirport;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }
}
