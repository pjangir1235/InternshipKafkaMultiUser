package com.risk.consumer.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.util.KakfaConsumerSelection;

@Component
public class FlightScheduleListener {

	@Autowired
	private KakfaConsumerSelection craterKafka;
    @Value("${kafka.topic-flightSchedule}")
    private String topicName;

    private String groupId="flightSchedule";
  public List<FlightScheduleDTO> start(long startingOffset, long size, int key) {

    List<FlightScheduleDTO> flightSchedule = new ArrayList<>();
    System.out.println("startingOffset + "+startingOffset);
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
    				craterKafka.setKafka(topicName, groupId.concat(key+""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {
      while (true) {
        ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);
        for (ConsumerRecord<Integer, JsonNode> record : records) {
        System.out.println(record.toString());
          JsonNode jsonNode = record.value();
          FlightScheduleDTO schedule = new FlightScheduleDTO();
          schedule = mapper.treeToValue(jsonNode, FlightScheduleDTO.class);
          if (record.key() == key) flightSchedule.add(schedule);
        }
        //	        if (startingOffset == -2) kafkaConsumer.commitSync();
        return flightSchedule;
      }

    } catch (Exception ex) {
      ex.printStackTrace();

      System.out.println("Exception caught " + ex.getMessage());
      return flightSchedule;
    } finally {
      kafkaConsumer.close();
      System.out.println("After closing KafkaConsumer");
    }
  }

}
