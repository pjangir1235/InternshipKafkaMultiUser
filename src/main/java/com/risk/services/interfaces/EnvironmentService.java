package com.risk.services.interfaces;

import com.risk.models.StoreRecord;

public interface EnvironmentService {

  void getEnvironmentData(String stationCode, StoreRecord rec);
}
