package com.risk.services.interfaces;

import com.risk.models.StoreRecord;

public interface FlightScheduleService {

  void getFlightScheduleData(String location, String date, StoreRecord rec);

  void getFlightSchedulePilotData(
      int pilotId, String pilotDesignationCode, String dateOfDeparture, StoreRecord rec);
}
