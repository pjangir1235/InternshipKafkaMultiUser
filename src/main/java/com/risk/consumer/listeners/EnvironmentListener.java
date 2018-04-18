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

import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.Environment;
import com.risk.models.StoreRecord;
import com.risk.services.analysis.impl.DestinationEnvironmentServiceImpl;
import com.risk.services.analysis.impl.SourceEnvironmentServiceImpl;

@Component
public class EnvironmentListener {

  private static final Logger log = LoggerFactory.getLogger(EnvironmentListener.class);
  @Autowired StoreRecord record;
  @Autowired SourceEnvironmentServiceImpl serviceSource;
  @Autowired DestinationEnvironmentServiceImpl serviceDestination;
  FlightScheduleDTO data;
  public final CountDownLatch countDownLatch1 = new CountDownLatch(3);

  @KafkaListener(
    topics = "${kafka.topic-environment}",
    containerFactory = "environmentKafkaListenerContainerFactory"
  )
  public void userListner(
      @Payload Environment env,
      @Header(KafkaHeaders.OFFSET) Integer offset,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    log.info(
        "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
        topic,
        partition,
        offset,
        env);
    data=record.getSchedule();
   record.setEnv(env);
    if(env.getStation().equals("K"+data.getSourceAirportCode()))
	serviceSource.getValue(env);
    else serviceDestination.getValue(env);
    record.setEnvironmentCount(record.getEnvironmentCount() - 1);
    countDownLatch1.countDown();
  }
}
