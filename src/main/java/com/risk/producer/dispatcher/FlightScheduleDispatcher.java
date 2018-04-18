package com.risk.producer.dispatcher;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.model.FlightSchedule;

@Service
public class FlightScheduleDispatcher {
  private static final Logger log = LoggerFactory.getLogger(FlightScheduleDispatcher.class);

  @Autowired private KafkaTemplate<Integer, FlightSchedule> kafkaTemplate;
  @Autowired StoreRecord record;

  public boolean dispatch(FlightSchedule craft, StoreRecord rec) {

    try {
      System.out.println("kafkaTemplate : " + kafkaTemplate);
      SendResult<Integer, FlightSchedule> sendResult =
          kafkaTemplate.sendDefault(new Integer(rec.getKey()), craft).get();

      RecordMetadata recordMetadata = sendResult.getRecordMetadata();
      if (rec.getFlightMinOffset() == -1) rec.setFlightMinOffset((int) recordMetadata.offset());

      if(rec.getFlightMaxOffset()<(int)recordMetadata.offset())
	  rec.setFlightMaxOffset((int)recordMetadata.offset());
      String metaRecord =
          "{offset - "
              + recordMetadata.offset()
              + " partition - "
              + recordMetadata.partition()
              + " TimeStamp - "
              + recordMetadata.timestamp()
              + " }";
      System.out.println("MetaData : " + metaRecord);
      log.info(metaRecord);
      return true;
    } catch (Exception e) {
      log.error(" " + e);
      return false;
    }
  }
}
