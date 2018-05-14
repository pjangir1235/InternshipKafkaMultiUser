package com.risk.consumer.listeners;

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
import com.risk.consumer.model.FlightCaptainSummaryDTO;
import com.risk.services.analysis.impl.CaptainAnalysisServiceImpl;
import com.risk.util.KakfaConsumerSelection;
@Service
public class FlightCaptainSummaryListener {

	@Autowired
	private KakfaConsumerSelection createrKafka;

    @Value("${kafka.topic-captainDetail}")
    private String topicName;

    private static final Logger log = LoggerFactory.getLogger(FlightCaptainSummaryListener.class);
    private String groupId="flightCaptain";
  public void start(long startingOffset,  int key,CaptainAnalysisServiceImpl service) {

    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
    				createrKafka.setKafka(topicName, groupId.concat(key+""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {

        ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);
        for (ConsumerRecord<Integer, JsonNode> record : records) {
          JsonNode jsonNode = record.value();
          FlightCaptainSummaryDTO schedule = new FlightCaptainSummaryDTO();
          schedule = mapper.treeToValue(jsonNode, FlightCaptainSummaryDTO.class);
          if (record.key() == key)  service.getDataAnalysis(schedule);
        }



    } catch (Exception ex) {
    	log.error(CommonConstant.ERROR+ex);
      } finally {
      kafkaConsumer.close();
    }
  }
}
