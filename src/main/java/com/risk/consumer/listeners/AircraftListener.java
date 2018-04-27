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
import com.risk.consumer.model.AircraftDTO;
import com.risk.util.KakfaConsumerSelection;

@Component
public class AircraftListener {

	@Autowired
	private KakfaConsumerSelection craterKafka;
	@Value("${kafka.topic-aircraft}")
	String topicName;
	String groupId = "aircraft";

	public List<AircraftDTO> start(long startingOffset, int key) {
		System.out.println("mai aaya thaa ayah 1 ");
		List<AircraftDTO> aircraft = new ArrayList<>();

		KafkaConsumer<Integer, JsonNode> kafkaConsumer = craterKafka.setKafka(topicName, groupId.concat(key+""),
		                startingOffset);
		ObjectMapper mapper = new ObjectMapper();

		try {
			while (true) {
				ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);

				System.out.println("mai aaya thaa ayah4 " + records.count());
				for (ConsumerRecord<Integer, JsonNode> record : records) {
					System.out.println("valuessss areee"+record.offset()+" " + record.value().toString());
					JsonNode jsonNode = record.value();
					AircraftDTO craft = new AircraftDTO();
					craft = mapper.treeToValue(jsonNode, AircraftDTO.class);
					System.out.println("mai aaya thaa ayah ");
					if (record.key() == key)
						aircraft.add(craft);
				}
				return aircraft;
			}

		}
		catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("Exception caught " + ex.getMessage());
			return aircraft;
		}
		finally {
			kafkaConsumer.close();
		}
	}

	// private static final Logger log =
	// LoggerFactory.getLogger(AircraftListener.class);
	// @Autowired StoreRecord record;
	//
	// public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
	//
	// @KafkaListener(
	// topics = "${kafka.topic-aircraft}",
	// containerFactory = "aircraftKafkaListenerContainerFactory"
	// )
	// public void aircraftListner(
	// @Payload AircraftDTO schedule,
	// @Header(KafkaHeaders.OFFSET) Integer offset,
	// @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	// @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
	// log.info(
	// "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
	// topic,
	// partition,
	// offset,
	// schedule);
	// record.setAircraftCount(record.getAircraftCount() - 1);
	// record.setAircraft(schedule);
	//
	// countDownLatch1.countDown();
	// }
}
