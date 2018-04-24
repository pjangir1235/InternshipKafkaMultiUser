package com.risk.consumer.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.util.KakfaConsumerSelection;



@Service
public class FlightScheduleListenerSample {

  public List<FlightScheduleDTO> start(String topicName, String groupId, long startingOffset, long size, int key) {


     List<FlightScheduleDTO> flightSchedule = new ArrayList<>();
      KafkaConsumer<Integer, JsonNode> kafkaConsumer=KakfaConsumerSelection.setKafka(topicName, groupId, startingOffset);
      ObjectMapper mapper = new ObjectMapper();

    try {
      while (true) {
        ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(size);
        for (ConsumerRecord<Integer, JsonNode> record : records) {
            JsonNode jsonNode = record.value();
            FlightScheduleDTO schedule=new FlightScheduleDTO();
            schedule=mapper.treeToValue(jsonNode,FlightScheduleDTO.class);
            System.out.println("******************************************" );
            System.out.println("Hiii" );
            System.out.println(record.toString() );
              if (record.key() == key) flightSchedule.add(schedule);}
//        if (startingOffset == -2) kafkaConsumer.commitSync();
        return flightSchedule;
      }

    } catch (Exception ex  ) {
	ex.printStackTrace();

      System.out.println("Exception caught " + ex.getMessage());
      return flightSchedule;
    } finally {
      kafkaConsumer.close();
      System.out.println("After closing KafkaConsumer");
    }
  }

}
