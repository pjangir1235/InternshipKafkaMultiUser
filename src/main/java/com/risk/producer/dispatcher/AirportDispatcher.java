package com.risk.producer.dispatcher;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.AirportRecord;
import com.risk.producer.model.Airport;

@Service
public class AirportDispatcher {
  private static final Logger log = LoggerFactory.getLogger(AirportDispatcher.class);

  @Autowired private KafkaTemplate<Integer, Airport> kafkaTemplate;
  @Autowired AirportRecord record;

  public boolean dispatch(Airport craft) {
    try {
      SendResult<Integer, Airport> sendResult =
          kafkaTemplate.sendDefault(craft.getAirportId(), craft).get();
      record.setAirportCount(record.getAirportCount() + 1);
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
