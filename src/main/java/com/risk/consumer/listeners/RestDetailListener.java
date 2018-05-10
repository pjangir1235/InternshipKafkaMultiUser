package com.risk.consumer.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.risk.consumer.model.RestDetailDTO;
import com.risk.util.KakfaConsumerSelection;
@Service
public class RestDetailListener {
	@Autowired
	private KakfaConsumerSelection craterKafka;
    @Value("${kafka.topic-restDetail}")
    private String topicName;

    private String groupId="restDetail";
  public List<RestDetailDTO> start(long startingOffset, int key) {

    List<RestDetailDTO> restDetail = new ArrayList<>();
    System.out.println("startingOffset of RestDetail + "+startingOffset);
    KafkaConsumer<Integer, JsonNode> kafkaConsumer =
    				craterKafka.setKafka(topicName, groupId.concat(key+""), startingOffset);
    ObjectMapper mapper = new ObjectMapper();

    try {
      while (true) {
        ConsumerRecords<Integer, JsonNode> records = kafkaConsumer.poll(1000);
        for (ConsumerRecord<Integer, JsonNode> record : records) {
        System.out.println(record.toString());
          JsonNode jsonNode = record.value();
          RestDetailDTO schedule = new RestDetailDTO();
          schedule = mapper.treeToValue(jsonNode, RestDetailDTO.class);
          if (record.key() == key) restDetail.add(schedule);
        }
           return restDetail;
      }

    } catch (Exception ex) {
      ex.printStackTrace();

      System.out.println("Exception caught " + ex.getMessage());
      return restDetail;
    } finally {
      kafkaConsumer.close();
      System.out.println("After closing KafkaConsumer");
    }
  }

//  private static final Logger log = LoggerFactory.getLogger(RestDetailListener.class);
//  @Autowired StoreRecord record;
//  @Autowired CrewTotalServiceImpl service;
//  public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
//
//  @KafkaListener(
//    topics = "${kafka.topic-restDetail}",
//    containerFactory = "restDetailKafkaListenerContainerFactory"
//  )
//  public void restDetailListner(
//      @Payload RestDetailDTO detail,
//      @Header(KafkaHeaders.OFFSET) Integer offset,
//      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
//      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//    log.info(
//        "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
//        topic,
//        partition,
//        offset,
//        detail);
//    service.addDuration(detail);
//   countDownLatch1.countDown();
//  }
}
