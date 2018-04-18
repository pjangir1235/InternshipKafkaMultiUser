package com.risk.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.risk.producer.model.User;

@Configuration
public class KafkaUserProducerConfig {

  @Autowired private KafkaProducerCommonProperty property;

  @Value("${kafka.topic-user}")
  private String topic;

  @SuppressWarnings("unchecked")
  @Bean
  public ProducerFactory<Integer, User> producerFactory() {
    return new DefaultKafkaProducerFactory<>(
        property.producerConfigs(),
        property.setIntegerKeySerializer(),
        property.setJsonSerializer());
  }

  @Bean
  public KafkaTemplate<Integer, User> userKafkaTemplate() {

    KafkaTemplate<Integer, User> kafkaTemplate = new KafkaTemplate<>(producerFactory());
    kafkaTemplate.setDefaultTopic(topic);
    return kafkaTemplate;
  }
}
