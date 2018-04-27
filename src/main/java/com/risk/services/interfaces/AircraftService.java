package com.risk.services.interfaces;

import com.risk.models.StoreRecord;

public interface AircraftService {

  void getAircraftData(String aircraftCode, StoreRecord rec);

  void getAllAircraftCodeforType(String aircraftCode);
}
