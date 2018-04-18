package com.risk.consumer.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.risk.models.Environment;

@Configuration
@EnableKafka
public class ConsumerEnvironmentConfig {

  @Autowired ConsumerCommonConfig config;

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ConsumerFactory<Integer, Environment> environmentConsumerFactory() {
    Map<String, Object> props = config.commonProperty();

    props.put(ConsumerConfig.GROUP_ID_CONFIG, "environment");
    return new DefaultKafkaConsumerFactory<>(
        props, new IntegerDeserializer(), new JsonDeserializer(Environment.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<Integer, Environment>
      environmentKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Integer, Environment> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(environmentConsumerFactory());
    return factory;
  }
}
