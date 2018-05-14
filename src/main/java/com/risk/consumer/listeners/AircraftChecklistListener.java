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
import com.risk.consumer.model.AircraftChecklistDTO;
import com.risk.util.KakfaConsumerSelection;

@Component
public class AircraftChecklistListener {

  @Autowired private KakfaConsumerSelection craterKafka;

  @Value("${kafka.topic-aircraftChecklist}")
  String topicName;

  String groupId = "aircraftChecklist";
  private static final Logger log = LoggerFactory.getLogger(AircraftChecklistListener.class);

  public AircraftChecklistDTO start(long startingOffset, int key) {
    AircraftChecklistDTO craft = new AircraftChecklistDTO();
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
        craterKafka.setKafka(topicName, groupId.concat(key + ""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {

      ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);

      for (ConsumerRecord<Integer, JsonNode> record : records) {
        JsonNode jsonNode = record.value();

        craft = mapper.treeToValue(jsonNode, AircraftChecklistDTO.class);
        if (record.key() == key) return craft;
      }
      return null;

    } catch (Exception ex) {
      log.error(CommonConstant.ERROR + ex);
      return craft;
    } finally {
      kafkaConsumer.close();
    }
  }
}
