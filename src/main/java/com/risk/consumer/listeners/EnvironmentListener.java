package com.risk.consumer.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.Environment;
import com.risk.services.analysis.impl.DestinationEnvironmentServiceImpl;
import com.risk.services.analysis.impl.SourceEnvironmentServiceImpl;
import com.risk.util.KakfaConsumerSelection;

@Component
public class EnvironmentListener {
	@Autowired
	private KakfaConsumerSelection craterKafka;
	@Value("${kafka.topic-aircraft}")
	String topicName;
	String groupId = "aircraft";

	public void start(long startingOffset, int key, SourceEnvironmentServiceImpl serviceSource,
	                DestinationEnvironmentServiceImpl serviceDestination,FlightScheduleDTO data) {
		KafkaConsumer<Integer, JsonNode> kafkaConsumer = craterKafka.setKafka(topicName, groupId.concat(key + ""),
		                startingOffset);
		ObjectMapper mapper = new ObjectMapper();

		try {
			while (true) {
				ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);

				for (ConsumerRecord<Integer, JsonNode> record : records) {
					JsonNode jsonNode = record.value();
					Environment env = new Environment();
					env = mapper.treeToValue(jsonNode, Environment.class);
					if (record.key() == key)
						if (env.getStation().equals("K" + data.getSourceAirportCode()))
							serviceSource.getValue(env);
						else
							serviceDestination.getValue(env);
				}

			}

		}
		catch (Exception ex) {
			ex.printStackTrace();

			System.out.println("Exception caught " + ex.getMessage());

		}
		finally {
			kafkaConsumer.close();
		}
	}

	//
	// private static final Logger log =
	// LoggerFactory.getLogger(EnvironmentListener.class);
	// @Autowired StoreRecord record;
	// @Autowired SourceEnvironmentServiceImpl serviceSource;
	// @Autowired DestinationEnvironmentServiceImpl serviceDestination;
	// FlightScheduleDTO data;
	// public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
	//
	// @KafkaListener(
	// topics = "${kafka.topic-environment}",
	// containerFactory = "environmentKafkaListenerContainerFactory"
	// )
	// public void userListner(
	// @Payload Environment env,
	// @Header(KafkaHeaders.OFFSET) Integer offset,
	// @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	// @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
	// log.info(
	// "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
	// topic,
	// partition,
	// offset,
	// env);
	// data=record.getSchedule();
	// record.setEnv(env);
	// if(env.getStation().equals("K"+data.getSourceAirportCode()))
	// serviceSource.getValue(env);
	// else serviceDestination.getValue(env);
	// record.setEnvironmentCount(record.getEnvironmentCount() - 1);
	// countDownLatch1.countDown();
	// }
}
