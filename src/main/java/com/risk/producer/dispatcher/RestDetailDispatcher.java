package com.risk.producer.dispatcher;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.model.RestDetail;

@Service
public class RestDetailDispatcher {
  private static final Logger log = LoggerFactory.getLogger(RestDetailDispatcher.class);

  @Autowired private KafkaTemplate<Integer, RestDetail> kafkaTemplate;

  public boolean dispatch(RestDetail craft, StoreRecord record) {
    try {
      SendResult<Integer, RestDetail> sendResult =
          kafkaTemplate.sendDefault(Integer.valueOf(record.getKey()), craft).get();
      RecordMetadata recordMetadata = sendResult.getRecordMetadata();
      String metaRecord =
          "{offset - "
              + recordMetadata.offset()
              + " partition - "
              + recordMetadata.partition()
              + " TimeStamp - "
              + recordMetadata.timestamp()
              + " }";

      if (record.getRestCrewMinOffset() == -1)
        record.setRestCrewMinOffset((int) (recordMetadata.offset()));
      else record.setRestCrewMaxOffset((int) (recordMetadata.offset()));
      log.info(metaRecord);
      return true;
    } catch (Exception e) {
      log.error(" " + e);
      return false;
    }
  }
}
