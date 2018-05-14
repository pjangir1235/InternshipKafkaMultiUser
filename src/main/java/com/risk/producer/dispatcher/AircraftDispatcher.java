package com.risk.producer.dispatcher;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.model.Aircraft;

@Service
public class AircraftDispatcher {
  private static final Logger log = LoggerFactory.getLogger(AircraftDispatcher.class);

  @Autowired private KafkaTemplate<Integer, Aircraft> kafkaTemplate;

  public boolean dispatch(Aircraft craft, StoreRecord rec) {
    try {
      SendResult<Integer, Aircraft> sendResult =
          kafkaTemplate.sendDefault(rec.getKey(), craft).get();

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
      rec.setAircraftOffset((int) recordMetadata.offset());
      return true;
    } catch (Exception e) {
      log.error(" " + e);
      return false;
    }
  }
}
