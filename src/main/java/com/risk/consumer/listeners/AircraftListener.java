package com.risk.consumer.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.constants.CommonConstant;
import com.risk.consumer.model.AircraftDTO;
import com.risk.util.KakfaConsumerSelection;

@Service
public class AircraftListener {

  @Autowired private KakfaConsumerSelection craterKafka;

  @Value("${kafka.topic-aircraft}")
  String topicName;

  String groupId = "aircraft";
  private static final Logger log = LoggerFactory.getLogger(AircraftListener.class);

  public List<AircraftDTO> start(long startingOffset, int key) {
    List<AircraftDTO> aircraft = new ArrayList<>();

    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
        craterKafka.setKafka(topicName, groupId.concat(key + ""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {
      while (true) {
        ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);

        for (ConsumerRecord<Integer, JsonNode> record : records) {
          JsonNode jsonNode = record.value();
          AircraftDTO craft = new AircraftDTO();
          craft = mapper.treeToValue(jsonNode, AircraftDTO.class);
          if (record.key() == key) aircraft.add(craft);
        }
        return aircraft;
      }

    } catch (Exception ex) {
      log.error(CommonConstant.ERROR + ex);
      return aircraft;
    } finally {
      kafkaConsumer.close();
    }
  }
}
