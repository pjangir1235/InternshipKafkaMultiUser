package com.risk.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerCommonProperty {

  @Value("${kafka.bootstrap}")
  private String bootstrap;

  @Value("${producer-acks}")
  private String ackConfig;

  @Value("${producer-retries}")
  private Integer retries;

  @Value("${producer-batch-size}")
  private Integer batchSize;

  @Value("${producer-linger}")
  private Integer lingerMsConfig;

  @Value("${producer-buffer-memory}")
  private Integer bufferMemory;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
    props.put(ProducerConfig.ACKS_CONFIG, ackConfig);
    props.put(ProducerConfig.RETRIES_CONFIG, retries);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
    props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMsConfig);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
    return props;
  }

  @SuppressWarnings("rawtypes")
  @Bean
  public Serializer setIntegerKeySerializer() {
    return new IntegerSerializer();
  }

  @SuppressWarnings({"rawtypes"})
  @Bean
  public Serializer setJsonSerializer() {
    return new JsonSerializer();
  }

  @SuppressWarnings({"rawtypes"})
  @Bean
  public Serializer setStringSerializer() {
    return new StringSerializer();
  }
}
