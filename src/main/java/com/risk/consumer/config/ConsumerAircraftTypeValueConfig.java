package com.risk.consumer.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.risk.consumer.model.AircraftTypeDTO;

@Configuration
@EnableKafka
public class ConsumerAircraftTypeValueConfig {

  @Autowired ConsumerCommonConfig config;

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ConsumerFactory<Integer, AircraftTypeDTO> aircraftTypeDataConsumerFactory() {
    Map<String, Object> props = config.commonProperty();
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "aircraftTypeData");
    return new DefaultKafkaConsumerFactory<>(
        props, new IntegerDeserializer(), new JsonDeserializer(AircraftTypeDTO.class));
  }

  @Bean
  @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
  public ConcurrentKafkaListenerContainerFactory<Integer, AircraftTypeDTO>
      aircraftTypeDataKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Integer, AircraftTypeDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(aircraftTypeDataConsumerFactory());
    return factory;
  }
}
