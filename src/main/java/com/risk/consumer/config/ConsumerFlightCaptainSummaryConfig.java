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

import com.risk.consumer.model.FlightCaptainSummaryDTO;

@Configuration
@EnableKafka
public class ConsumerFlightCaptainSummaryConfig {

  @Autowired ConsumerCommonConfig config;

  @Bean
  public ConcurrentKafkaListenerContainerFactory<Integer, FlightCaptainSummaryDTO>
      flightCaptainSummaryKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Integer, FlightCaptainSummaryDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(flightCaptainSummaryConsumerFactory());
    return factory;
  }

  public ConsumerFactory<Integer, FlightCaptainSummaryDTO> flightCaptainSummaryConsumerFactory() {
    Map<String, Object> props = config.commonProperty();
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "flightCaptainSummary");
    return new DefaultKafkaConsumerFactory<>(
        props, new IntegerDeserializer(), new JsonDeserializer<>(FlightCaptainSummaryDTO.class));
  }
}
