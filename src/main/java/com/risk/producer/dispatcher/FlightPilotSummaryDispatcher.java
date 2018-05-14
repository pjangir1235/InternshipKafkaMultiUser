package com.risk.producer.dispatcher;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.model.FlightPilotSummary;

@Service
public class FlightPilotSummaryDispatcher {
	private static final Logger log = LoggerFactory.getLogger(FlightPilotSummaryDispatcher.class);

	@Autowired
	private KafkaTemplate<Integer, FlightPilotSummary> kafkaTemplate;

	public boolean dispatch(FlightPilotSummary summary, StoreRecord record) {
		try {
			SendResult<Integer, FlightPilotSummary> sendResult = kafkaTemplate.sendDefault(record.getKey(), summary)
			                .get();
			RecordMetadata recordMetadata = sendResult.getRecordMetadata();

			if (record.getFlightPilotMinOffset() == -1)
				record.setFlightPilotMinOffset((int) (recordMetadata.offset()));
			else
				record.setFlightPilotMaxOffset((int) (recordMetadata.offset()));
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
