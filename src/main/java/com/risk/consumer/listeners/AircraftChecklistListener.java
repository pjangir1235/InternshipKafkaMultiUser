package com.risk.consumer.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.consumer.model.AircraftChecklistDTO;
import com.risk.util.KakfaConsumerSelection;

@Component
public class AircraftChecklistListener {

	@Autowired
	private KakfaConsumerSelection craterKafka;
	@Value("${kafka.topic-aircraftChecklist}")
	String topicName;
	String groupId = "aircraftChecklist";

	public AircraftChecklistDTO start(long startingOffset, int key) {
		AircraftChecklistDTO craft = new AircraftChecklistDTO();
		KafkaConsumer<Integer, JsonNode> kafkaConsumer = craterKafka.setKafka(topicName, groupId.concat(key + ""),
		                startingOffset);
		ObjectMapper mapper = new ObjectMapper();

		try {
			while (true) {
				ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);

				for (ConsumerRecord<Integer, JsonNode> record : records) {
					JsonNode jsonNode = record.value();

					craft = mapper.treeToValue(jsonNode, AircraftChecklistDTO.class);
					if (record.key() == key)
						return craft;
				}
			}

		}
		catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("Exception caught " + ex.getMessage());
			return craft;
		}
		finally {
			kafkaConsumer.close();
		}
	}

	// private static final Logger log =
	// LoggerFactory.getLogger(AircraftChecklistListener.class);
	// @Autowired StoreRecord record;
	//
	// @Autowired AircraftAnalysisServiceImpl service;
	// public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
	//
	// @KafkaListener(
	// topics = "${kafka.topic-aircraftChecklist}",
	// containerFactory = "aircraftChecklistKafkaListenerContainerFactory"
	// )
	// public void aircraftChecklistListner(
	// @Payload AircraftChecklistDTO checkList,
	// @Header(KafkaHeaders.OFFSET) Integer offset,
	// @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	// @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
	// log.info(
	// "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
	// topic,
	// partition,
	// offset,
	// checkList);
	//
	// record.setAircraftChecklistCount(record.getAircraftChecklistCount() - 1);
	// countDownLatch1.countDown();
	// }
}
