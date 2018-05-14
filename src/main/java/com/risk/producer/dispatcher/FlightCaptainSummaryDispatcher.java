package com.risk.producer.dispatcher;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.model.FlightCaptainSummary;

@Service
public class FlightCaptainSummaryDispatcher {
	private static final Logger log = LoggerFactory.getLogger(FlightCaptainSummaryDispatcher.class);

	@Autowired
	private KafkaTemplate<Integer, FlightCaptainSummary> kafkaTemplate;

	public boolean dispatch(FlightCaptainSummary summary, StoreRecord record) {
		try {
			SendResult<Integer, FlightCaptainSummary> sendResult = kafkaTemplate.sendDefault(record.getKey(), summary)
			                .get();

			RecordMetadata recordMetadata = sendResult.getRecordMetadata();
			if (record.getFlightCaptainMinOffset() == -1)
				record.setFlightCaptainMinOffset((int) (recordMetadata.offset()));
			else
				record.setFlightCaptainMaxOffset((int) (recordMetadata.offset()));
			String metaRecord = "{offset - " + recordMetadata.offset() + " partition - " + recordMetadata.partition()
			                + " TimeStamp - " + recordMetadata.timestamp() + " }";
			log.info(metaRecord);
			return true;
		}
		catch (Exception e) {
			log.error(" " + e);
			return false;
		}
	}
}
