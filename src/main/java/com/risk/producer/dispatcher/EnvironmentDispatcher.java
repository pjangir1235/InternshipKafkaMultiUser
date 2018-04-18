package com.risk.producer.dispatcher;

import java.util.Random;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.Environment;
import com.risk.models.StoreRecord;

@Service
public class EnvironmentDispatcher {
  private static final Logger log = LoggerFactory.getLogger(EnvironmentDispatcher.class);

  @Autowired private KafkaTemplate<Integer, Environment> kafkaTemplate;
  @Autowired StoreRecord record;

  Random random = new Random();

  public boolean dispatch(Environment env) {
    try {
      SendResult<Integer, Environment> sendResult =
          kafkaTemplate.sendDefault(random.nextInt(), env).get();

      record.setEnvironmentCount(record.getEnvironmentCount() + 1);
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
