package com.risk.consumer.listeners;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.risk.consumer.model.AirportDTO;
import com.risk.models.AirportRecord;

@Component
public class AirportListener {

  private static final Logger log = LoggerFactory.getLogger(AirportListener.class);
  @Autowired AirportRecord record;

  public final CountDownLatch countDownLatch1 = new CountDownLatch(3);

  @KafkaListener(
    topics = "${kafka.topic-airport}",
    containerFactory = "airportKafkaListenerContainerFactory"
  )
  public void airportListener(
      @Payload AirportDTO airport,
      @Header(KafkaHeaders.OFFSET) Integer offset,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    log.info(
        "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
        topic,
        partition,
        offset,
        airport);
    record.setAirport(airport);
    record.setAirportCount(record.getAirportCount() - 1);
    countDownLatch1.countDown();
  }
}
