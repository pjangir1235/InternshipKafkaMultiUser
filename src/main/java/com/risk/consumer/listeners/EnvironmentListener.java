package com.risk.consumer.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.constants.CommonConstant;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.Environment;
import com.risk.services.analysis.impl.DestinationEnvironmentService;
import com.risk.services.analysis.impl.SourceEnvironmentService;
import com.risk.util.KakfaConsumerSelection;

@Component
public class EnvironmentListener {
  @Autowired private KakfaConsumerSelection craterKafka;

  @Value("${kafka.topic-environment}")
  String topicName;

  String groupId = "aircraft";
  private static final Logger log = LoggerFactory.getLogger(EnvironmentListener.class);

  public void start(
      long startingOffset,
      int key,
      SourceEnvironmentService serviceSource,
      DestinationEnvironmentService serviceDestination,
      FlightScheduleDTO data) {
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
        craterKafka.setKafka(topicName, groupId.concat(key + ""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {
      ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);

      for (ConsumerRecord<Integer, JsonNode> record : records) {
        JsonNode jsonNode = record.value();
        Environment env = new Environment();
        env = mapper.treeToValue(jsonNode, Environment.class);
        if (record.key() == key)
          if (env.getStation().equals("K" + data.getSourceAirportCode()))
            serviceSource.getValue(env);
          else serviceDestination.getValue(env);
      }

    } catch (Exception ex) {
      log.error(CommonConstant.ERROR + ex);
    } finally {
      kafkaConsumer.close();
    }
  }
}
