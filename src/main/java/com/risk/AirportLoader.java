package com.risk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.risk.models.AirportRecord;
import com.risk.services.interfaces.MainService;

@Component
public class AirportLoader implements CommandLineRunner {
  @Autowired MainService producer;
  @Autowired AirportRecord recordAirport;

  @Override
  public void run(String... args) throws Exception {
    producer.getAiprotValues();
    recordAirport.setUpdated(true);
  }
}
