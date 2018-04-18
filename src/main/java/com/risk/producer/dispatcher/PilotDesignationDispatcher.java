package com.risk.producer.dispatcher;

import javax.validation.constraints.Null;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.model.PilotDesignation;

@Service
public class PilotDesignationDispatcher {
  private static final Logger log = LoggerFactory.getLogger(PilotDesignationDispatcher.class);

  @Autowired private KafkaTemplate<Null, PilotDesignation> kafkaTemplate;
  @Autowired StoreRecord record;

  public boolean dispatch(PilotDesignation craft) {
    try {
      SendResult<Null, PilotDesignation> sendResult = kafkaTemplate.sendDefault(null, craft).get();
      record.setPilotDesignationCount(record.getPilotDesignationCount() + 1);
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
