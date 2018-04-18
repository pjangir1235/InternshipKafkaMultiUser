package com.risk.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.risk.models.Environment;

@Configuration
public class KafkaEnvironmentProducerConfig {

  @Autowired private KafkaProducerCommonProperty property;

  @Value("${kafka.topic-environment}")
  private String topic;

  @Bean
  public KafkaTemplate<Integer, Environment> environmentKafkaTemplate() {

    KafkaTemplate<Integer, Environment> kafkaTemplate = new KafkaTemplate<>(producerFactory());
    kafkaTemplate.setDefaultTopic(topic);
    return kafkaTemplate;
  }

  @SuppressWarnings("unchecked")
  @Bean
  public ProducerFactory<Integer, Environment> producerFactory() {
    return new DefaultKafkaProducerFactory<>(
        property.producerConfigs(),
        property.setIntegerKeySerializer(),
        property.setJsonSerializer());
  }
}
