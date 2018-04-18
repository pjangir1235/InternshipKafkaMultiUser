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

import com.risk.consumer.model.AircraftCodeDTO;

@Configuration
@EnableKafka
public class ConsumerAircraftCodeTypeConfig {

  @Autowired ConsumerCommonConfig config;

  @Bean

  public ConcurrentKafkaListenerContainerFactory<Integer, AircraftCodeDTO>
      aircraftCodeKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Integer, AircraftCodeDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(aircraftConsumerFactory());
    return factory;
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ConsumerFactory<Integer, AircraftCodeDTO> aircraftConsumerFactory() {
    Map<String, Object> props = config.commonProperty();
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "aircraft");
    return new DefaultKafkaConsumerFactory<>(
        props, new IntegerDeserializer(), new JsonDeserializer(AircraftCodeDTO.class));
  }
}
