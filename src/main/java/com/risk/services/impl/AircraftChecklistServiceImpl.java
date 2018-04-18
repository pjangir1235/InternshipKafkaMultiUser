package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.AircraftChecklistDispatcher;
import com.risk.producer.intefacerepo.AircraftChecklistRepo;
import com.risk.producer.model.AircraftChecklist;
import com.risk.services.interfaces.AircraftChecklistService;

@Service
public class AircraftChecklistServiceImpl implements AircraftChecklistService {

  @Autowired AircraftChecklistRepo craftRepo;
  @Autowired AircraftChecklistDispatcher craftDispatcher;

  /* (non-Javadoc)
   * @see com.risk.services.impl.GetAircraftChecklist#getAircraftChecklistData()
   */
  @Override
  public void getAircraftChecklistData() {
    Iterable<AircraftChecklist> itr = craftRepo.findAll();
    Iterator<AircraftChecklist> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
  }

  /* (non-Javadoc)
   * @see com.risk.services.impl.GetAircraftChecklist#getAircraftChecklistOnAircraftCodeData(java.lang.String, java.lang.String)
   */
  @Override
  public void getAircraftChecklistOnAircraftCodeData(String aircraftCode, String dateOfDeparture) {
    Iterable<AircraftChecklist> itr = craftRepo.findAircraft(aircraftCode, dateOfDeparture);
    Iterator<AircraftChecklist> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
  }
}
