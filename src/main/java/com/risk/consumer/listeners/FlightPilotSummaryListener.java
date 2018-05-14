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
import com.risk.consumer.model.FlightPilotSummaryDTO;
import com.risk.services.analysis.impl.PilotAnalysisServiceImpl;
import com.risk.util.KakfaConsumerSelection;

@Component
public class FlightPilotSummaryListener {

	@Autowired
	private KakfaConsumerSelection createrKafka;

	@Value("${kafka.topic-pilotDetail}")
	private String topicName;

	private String groupId = "flightPilot";
	private static final Logger log = LoggerFactory.getLogger(FlightPilotSummaryListener.class);

	public void start(long startingOffset, int key, PilotAnalysisServiceImpl service) {

		KafkaConsumer<Integer, JsonNode> kafkaConsumer = createrKafka.setKafka(topicName, groupId.concat(key + ""),
		                startingOffset);
		ObjectMapper mapper = new ObjectMapper();

		try {

			ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);
			for (ConsumerRecord<Integer, JsonNode> record : records) {
				JsonNode jsonNode = record.value();
				FlightPilotSummaryDTO schedule = new FlightPilotSummaryDTO();
				schedule = mapper.treeToValue(jsonNode, FlightPilotSummaryDTO.class);
				if (record.key() == key)
					service.getDataAnalysis(schedule);
			}
		}
		catch (Exception ex) {
			log.error(CommonConstant.ERROR + ex);
		}
		finally {
			kafkaConsumer.close();
		}

	}
}
