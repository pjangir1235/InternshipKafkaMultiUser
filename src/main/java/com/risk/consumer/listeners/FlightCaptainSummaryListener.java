package com.risk.consumer.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.consumer.model.FlightCaptainSummaryDTO;
import com.risk.services.analysis.impl.CaptainAnalysisServiceImpl;
import com.risk.util.KakfaConsumerSelection;
@Service
public class FlightCaptainSummaryListener {

	@Autowired
	private KakfaConsumerSelection createrKafka;

    @Value("${kafka.topic-captainDetail}")
    private String topicName;

    private String groupId="flightCaptain";
  public void start(long startingOffset,  int key,CaptainAnalysisServiceImpl service) {

    System.out.println("startingOffset + "+startingOffset);
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
    				createrKafka.setKafka(topicName, groupId.concat(key+""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {
      while (true) {
        ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);
        for (ConsumerRecord<Integer, JsonNode> record : records) {
        System.out.println(record.toString());
          JsonNode jsonNode = record.value();
          FlightCaptainSummaryDTO schedule = new FlightCaptainSummaryDTO();
          schedule = mapper.treeToValue(jsonNode, FlightCaptainSummaryDTO.class);
          if (record.key() == key)  service.getDataAnalysis(schedule);
        }
        //	        if (startingOffset == -2) kafkaConsumer.commitSync();

      }

    } catch (Exception ex) {
      ex.printStackTrace();

      System.out.println("Exception caught " + ex.getMessage());
    } finally {
      kafkaConsumer.close();
      System.out.println("After closing KafkaConsumer");
    }
  }
//  private static final Logger log = LoggerFactory.getLogger(FlightCaptainSummaryListener.class);
//  @Autowired StoreRecord record;
//
//  @Autowired CaptainAnalysisServiceImpl detail;
//  public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
//
//  @KafkaListener(
//    topics = "${kafka.topic-captainDetail}",
//    containerFactory = "flightCaptainSummaryKafkaListenerContainerFactory"
//  )
//  public void captainListner(
//      @Payload FlightCaptainSummaryDTO summary,
//      @Header(KafkaHeaders.OFFSET) Integer offset,
//      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
//      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//    log.info(
//        "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
//        topic,
//        offset,
//        summary);
//    detail.getDataAnalysis(summary);
//    countDownLatch1.countDown();
//  }
}
