package com.risk.consumer.listeners;

import org.springframework.stereotype.Component;

@Component
public class FlightScheduleListener {
//
//  private static final Logger log = LoggerFactory.getLogger(FlightScheduleListener.class);
//  @Autowired StoreRecord record;
//
//  public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
//
//  @KafkaListener(
//    topics = "${kafka.topic-flightSchedule}",
//    containerFactory = "flightScheduleKafkaListenerContainerFactory"
//  )
//  public void flightScheduleListner(
//      @Payload FlightScheduleDTO schedule,
//      @Header(KafkaHeaders.OFFSET) Integer offset,
//      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
//      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//    log.info(
//        "Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
//        topic,
//        partition,
//        offset,
//        schedule);
//    record.setFlightSchedule(schedule);
//    record.setFlightScheduleCount(record.getFlightScheduleCount() - 1);
//    countDownLatch1.countDown();
//  }
}
