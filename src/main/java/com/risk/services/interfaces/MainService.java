package com.risk.services.interfaces;

import java.text.ParseException;
import java.util.List;

import com.risk.consumer.model.AircraftDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.ScheduleRequestDTO;
import com.risk.models.StoreRecord;

public interface MainService {
  void checkFetchData();

  void getAiprotValues();

  void getAircraftChecklistValues();

  void getAircraftTypeValues();

  List<AircraftDTO> getAircraftValues(String aircraftCode);

  void getAnalysisData() throws ParseException;

  void getCrewValues();

  void getEnvironmentValues(String stationCode);

  void getFlightScheduleCrewValues();

  void getFlightSchedulePilotValues();

  List<FlightScheduleDTO> getFlightScheduleValues(ScheduleRequestDTO req);

  void getPilotDesignationValues();

  void getPilotValues();

  void getUserValues(String userName, String password);
  void setKey(StoreRecord rec);
}
