package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.dispatcher.AircraftCodeDispatcher;
import com.risk.producer.dispatcher.AircraftDispatcher;
import com.risk.producer.intefacerepo.AircraftRepo;
import com.risk.producer.model.Aircraft;
import com.risk.producer.model.AircraftCodeType;
import com.risk.services.interfaces.AircraftService;

@Service
public class AircraftServiceImpl implements AircraftService {

  @Autowired AircraftRepo craftRepo;
  @Autowired AircraftDispatcher craftDispatcher;
  @Autowired AircraftCodeDispatcher codeDispatcher;

  @Override
  public void getAircraftData(String aircraftCode, StoreRecord rec) {
	  System.out.println("i come to class key "+rec.getKey());
    Iterable<Aircraft> itr = craftRepo.findData(aircraftCode);
    Iterator<Aircraft> iter = itr.iterator();
    while (iter.hasNext()) {
    	System.out.println("i am here with key "+rec.getKey());
    	craftDispatcher.dispatch(iter.next(),rec);
    }
  }

  @Override
  public void getAllAircraftCodeforType(String aircraftCode) {
    Iterable<AircraftCodeType> itr = craftRepo.findAllCodeforType(aircraftCode);
    Iterator<AircraftCodeType> iter = itr.iterator();
    while (iter.hasNext()) codeDispatcher.dispatch(iter.next());
  }
}
