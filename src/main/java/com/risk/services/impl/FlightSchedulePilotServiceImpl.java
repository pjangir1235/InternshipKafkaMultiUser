package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.FlightSchedulePilotDispatcher;
import com.risk.producer.intefacerepo.FlightSchedulePilotRepo;
import com.risk.producer.model.FlightSchedulePilot;
import com.risk.services.interfaces.FlightSchedulePilotService;

@Service
public class FlightSchedulePilotServiceImpl implements FlightSchedulePilotService {

  @Autowired FlightSchedulePilotRepo craftRepo;
  @Autowired FlightSchedulePilotDispatcher craftDispatcher;

  @Override
  public void getFlightSchedulePilotData() {
    Iterable<FlightSchedulePilot> itr = craftRepo.findAll();
    Iterator<FlightSchedulePilot> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
  }
}
