package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.AirportDispatcher;
import com.risk.producer.intefacerepo.AirportRepo;
import com.risk.producer.model.Airport;
import com.risk.services.interfaces.AirportService;

@Service
public class AirportServiceImpl implements AirportService {
  @Autowired AirportRepo airport;
  @Autowired AirportDispatcher dispatcher;

  /* (non-Javadoc)
   * @see com.risk.services.impl.AirportService#getAirportData()
   */
  @Override
  public void getAirportData() {
    Iterable<Airport> itr = airport.findAll();
    Iterator<Airport> iter = itr.iterator();
    while (iter.hasNext()) dispatcher.dispatch(iter.next());
  }
}
