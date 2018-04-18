package com.risk.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.risk.producer.model.FlightSchedule;

@Configuration
public class KafkaFlightScheduleProducerConfig {
  @Autowired private KafkaProducerCommonProperty property;

  @Value("${kafka.topic-flightSchedule}")
  private String topic;

  @Bean
  public KafkaTemplate<Integer, FlightSchedule> flightScheduleKafkaTemplate() {
     System.out.println("Piyush Jangir");
    KafkaTemplate<Integer, FlightSchedule> kafkaTemplate = new KafkaTemplate<>(producerFactory());
    kafkaTemplate.setDefaultTopic(topic);
    return kafkaTemplate;
  }

  @SuppressWarnings("unchecked")
  @Bean
  public ProducerFactory<Integer, FlightSchedule> producerFactory() {
    return new DefaultKafkaProducerFactory<>(
        property.producerConfigs(),
        property.setIntegerKeySerializer(),
        property.setJsonSerializer());
  }
}
