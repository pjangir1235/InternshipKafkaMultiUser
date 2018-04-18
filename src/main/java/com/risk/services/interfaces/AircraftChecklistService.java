package com.risk.services.interfaces;

public interface AircraftChecklistService {

  void getAircraftChecklistData();

  void getAircraftChecklistOnAircraftCodeData(String aircraftCode, String dateOfDeparture);
}
