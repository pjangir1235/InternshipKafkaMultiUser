package com.risk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.dispatcher.AircraftChecklistDispatcher;
import com.risk.producer.intefacerepo.AircraftChecklistRepo;
import com.risk.producer.model.AircraftChecklist;
import com.risk.services.interfaces.AircraftChecklistService;

@Service
public class AircraftChecklistServiceImpl implements AircraftChecklistService {

  @Autowired AircraftChecklistRepo craftRepo;
  @Autowired AircraftChecklistDispatcher craftDispatcher;



  @Override
  public void getAircraftChecklistOnAircraftCodeData(String aircraftCode, String dateOfDeparture,StoreRecord rec) {
    AircraftChecklist checkList = craftRepo.findAircraft(aircraftCode, dateOfDeparture);
    craftDispatcher.dispatch(checkList,rec);
  }
}
