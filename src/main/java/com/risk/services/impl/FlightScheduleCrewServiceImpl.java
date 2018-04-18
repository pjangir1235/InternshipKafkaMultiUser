package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.FlightScheduleCrewDispatcher;
import com.risk.producer.intefacerepo.FlightScheduleCrewRepo;
import com.risk.producer.model.FlightScheduleCrew;
import com.risk.services.interfaces.FlightScheduleCrewService;

@Service
public class FlightScheduleCrewServiceImpl implements FlightScheduleCrewService {

  @Autowired FlightScheduleCrewRepo craftRepo;
  @Autowired FlightScheduleCrewDispatcher craftDispatcher;

  /* (non-Javadoc)
   * @see com.risk.services.impl.FlightScheduleCrewService#getFlightScheduleCrewData()
   */
  @Override
  public void getFlightScheduleCrewData() {
    Iterable<FlightScheduleCrew> itr = craftRepo.findAll();
    Iterator<FlightScheduleCrew> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
  }
}
