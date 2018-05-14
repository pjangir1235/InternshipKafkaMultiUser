package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.dispatcher.AircraftDispatcher;
import com.risk.producer.intefacerepo.AircraftRepo;
import com.risk.producer.model.Aircraft;
import com.risk.services.interfaces.AircraftService;

@Service
public class AircraftServiceImpl implements AircraftService {

  @Autowired AircraftRepo craftRepo;
  @Autowired AircraftDispatcher craftDispatcher;

  @Override
  public void getAircraftData(String aircraftCode, StoreRecord rec) {

    Iterable<Aircraft> itr = craftRepo.findData(aircraftCode);
    Iterator<Aircraft> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next(), rec);
  }
}
