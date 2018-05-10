package com.risk.consumer.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	public void start(long startingOffset, int key, PilotAnalysisServiceImpl service) {

		System.out.println("startingOffset + " + startingOffset);
		KafkaConsumer<Integer, JsonNode> kafkaConsumer = createrKafka.setKafka(topicName, groupId.concat(key + ""),
		                startingOffset);
		ObjectMapper mapper = new ObjectMapper();

		try {
			while (true) {
				ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);
				for (ConsumerRecord<Integer, JsonNode> record : records) {
					System.out.println(record.toString());
					JsonNode jsonNode = record.value();
					FlightPilotSummaryDTO schedule = new FlightPilotSummaryDTO();
					schedule = mapper.treeToValue(jsonNode, FlightPilotSummaryDTO.class);
					if (record.key() == key)
						service.getDataAnalysis(schedule);
				}
				// if (startingOffset == -2) kafkaConsumer.commitSync();

			}

		}
		catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("Exception caught " + ex.getMessage());
		}
		finally {
			kafkaConsumer.close();
			System.out.println("After closing KafkaConsumer");
		}

		// private static final Logger log =
		// LoggerFactory.getLogger(FlightPilotSummaryListener.class);
		// @Autowired StoreRecord record;
		// @Autowired PilotAnalysisServiceImpl pilot;
		//
		// public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
		//
		// @KafkaListener(
		// topics = "${kafka.topic-pilotDetail}",
		// containerFactory = "flightPilotSummaryKafkaListenerContainerFactory"
		// )
		// public void pilotListner(
		// @Payload FlightPilotSummaryDTO summary,
		// @Header(KafkaHeaders.OFFSET) Integer offset,
		// @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
		// @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		// log.info(
		// "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
		// topic,
		// partition,
		// offset,
		// summary);
		// pilot.getDataAnalysis(summary);
		// countDownLatch1.countDown();
		// }
	}
}
