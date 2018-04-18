package com.risk.producer.dispatcher;

import java.util.Random;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.producer.model.FlightPilotSummary;

@Service
public class FlightPilotSummaryDispatcher {
  private static final Logger log = LoggerFactory.getLogger(FlightPilotSummaryDispatcher.class);


  @Autowired private KafkaTemplate<Integer, FlightPilotSummary> kafkaTemplate;
  //  @Autowired StoreRecord record;
  Random randon = new Random();

  public boolean dispatch(FlightPilotSummary summary) {
    try {
      SendResult<Integer, FlightPilotSummary> sendResult =
          kafkaTemplate.sendDefault(randon.nextInt(), summary).get();

      //      record.setFlightPilotSummaryCount(record.getFlightPilotSummaryCount() + 1);
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
