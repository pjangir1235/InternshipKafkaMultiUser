package com.risk.services.interfaces;

import com.risk.models.StoreRecord;

public interface AircraftChecklistService {

  void getAircraftChecklistOnAircraftCodeData(
      String aircraftCode, String dateOfDeparture, StoreRecord rec);
}
