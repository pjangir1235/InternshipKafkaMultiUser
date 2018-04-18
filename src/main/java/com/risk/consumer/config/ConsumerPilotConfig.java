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

import com.risk.consumer.model.PilotDTO;

@Configuration
@EnableKafka
public class ConsumerPilotConfig {

  @Autowired ConsumerCommonConfig config;

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ConsumerFactory<Integer, PilotDTO> pilotConsumerFactory() {
    Map<String, Object> props = config.commonProperty();

    props.put(ConsumerConfig.GROUP_ID_CONFIG, "pilot");
    return new DefaultKafkaConsumerFactory<>(
        props, new IntegerDeserializer(), new JsonDeserializer(PilotDTO.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<Integer, PilotDTO>
      pilotKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Integer, PilotDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(pilotConsumerFactory());
    return factory;
  }
}
