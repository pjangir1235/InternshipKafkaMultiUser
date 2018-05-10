package com.risk.services.interfaces;

import java.text.ParseException;
import java.util.List;

import com.risk.consumer.model.AircraftDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.Environment;
import com.risk.models.ScheduleRequestDTO;
import com.risk.models.StoreRecord;
import com.risk.producer.model.User;
import com.risk.result.model.FinalAnalysisData;

public interface MainService {

	void getAiprotValues();

	List<AircraftDTO> getAircraftValues(String aircraftCode);

	FinalAnalysisData getAnalysisData(FlightScheduleDTO flightData) throws ParseException;

	Environment getEnvironmentValues(String stationCode);

	List<FlightScheduleDTO> getFlightScheduleValues(ScheduleRequestDTO req);

	boolean getUserValues(User user);

	void setKey(StoreRecord rec);

}
