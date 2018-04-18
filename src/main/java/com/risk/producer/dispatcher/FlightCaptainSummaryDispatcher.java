package com.risk.producer.dispatcher;

import java.util.Random;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.producer.model.FlightCaptainSummary;

@Service
public class FlightCaptainSummaryDispatcher {
  private static final Logger log = LoggerFactory.getLogger(FlightCaptainSummaryDispatcher.class);

  @Autowired private KafkaTemplate<Integer, FlightCaptainSummary> kafkaTemplate;
//  @Autowired StoreRecord record;
  Random randon=new Random();

  public boolean dispatch(FlightCaptainSummary summary) {
    try {
      SendResult<Integer, FlightCaptainSummary> sendResult = kafkaTemplate.sendDefault(randon.nextInt(), summary).get();
//      record.setFlightCaptainSummaryCount(record.getFlightCaptainSummaryCount() + 1);
      RecordMetadata recordMetadata = sendResult.getRecordMetadata();
      String metaRecord =
          "{offset - "
              + recordMetadata.offset()
              + " partition - "
              + recordMetadata.partition()
              + " TimeStamp - "
              + recordMetadata.timestamp()
              + " }";
      log.info(metaRecord);
      return true;
    } catch (Exception e) {
      log.error(" " + e);
      return false;
    }
  }
}
