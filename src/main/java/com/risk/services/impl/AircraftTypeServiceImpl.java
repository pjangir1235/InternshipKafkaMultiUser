package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.AircraftTypeDispatcher;
import com.risk.producer.intefacerepo.AircraftTypeRepo;
import com.risk.producer.model.AircraftTypeValue;
import com.risk.services.interfaces.AircraftTypeService;

@Service
public class AircraftTypeServiceImpl implements AircraftTypeService {

  @Autowired AircraftTypeRepo craftRepo;
  @Autowired AircraftTypeDispatcher craftDispatcher;

  /* (non-Javadoc)
   * @see com.risk.services.impl.AircraftTypeService#getAircraftTypeData()
   */
  @Override
  public void getAircraftTypeData() {
    Iterable<AircraftTypeValue> itr = craftRepo.findAll();
    Iterator<AircraftTypeValue> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
  }
}
