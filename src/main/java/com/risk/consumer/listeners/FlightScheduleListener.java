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
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.util.KakfaConsumerSelection;

@Service
public class FlightScheduleListener {

  @Autowired private KakfaConsumerSelection craterKafka;

  @Value("${kafka.topic-flightSchedule}")
  private String topicName;

  private String groupId = "flightSchedule";
  private static final Logger log = LoggerFactory.getLogger(FlightScheduleListener.class);

  public List<FlightScheduleDTO> start(long startingOffset, int key) {
    List<FlightScheduleDTO> flightSchedule = new ArrayList<>();
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
        craterKafka.setKafka(topicName, groupId.concat(key + ""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {
      while (true) {
        ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);
        for (ConsumerRecord<Integer, JsonNode> record : records) {
          JsonNode jsonNode = record.value();
          FlightScheduleDTO schedule = new FlightScheduleDTO();
          schedule = mapper.treeToValue(jsonNode, FlightScheduleDTO.class);
          if (record.key() == key) flightSchedule.add(schedule);
        }
        return flightSchedule;
      }

    } catch (Exception ex) {
      log.error(CommonConstant.ERROR + ex);
      return flightSchedule;
    } finally {
      kafkaConsumer.close();
    }
  }
}
