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

import com.risk.consumer.model.FlightScheduleCrewDTO;

@Configuration
@EnableKafka
public class ConsumerFlightScheduleCrewConfig {

  @Autowired ConsumerCommonConfig config;

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ConsumerFactory<Integer, FlightScheduleCrewDTO> flightScheduleCrewConsumerFactory() {
    Map<String, Object> props = config.commonProperty();
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "flightScheduleCrewData");
    return new DefaultKafkaConsumerFactory<>(
        props, new IntegerDeserializer(), new JsonDeserializer(FlightScheduleCrewDTO.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<Integer, FlightScheduleCrewDTO>
      flightScheduleCrewKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Integer, FlightScheduleCrewDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(flightScheduleCrewConsumerFactory());
    return factory;
  }
}
