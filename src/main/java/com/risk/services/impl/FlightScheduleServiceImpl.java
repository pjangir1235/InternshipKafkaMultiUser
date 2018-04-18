package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.dispatcher.FlightCaptainSummaryDispatcher;
import com.risk.producer.dispatcher.FlightPilotSummaryDispatcher;
import com.risk.producer.dispatcher.FlightScheduleDispatcher;
import com.risk.producer.intefacerepo.FlightScheduleRepo;
import com.risk.producer.model.FlightCaptainSummary;
import com.risk.producer.model.FlightPilotSummary;
import com.risk.producer.model.FlightSchedule;
import com.risk.services.interfaces.FlightScheduleService;
import com.risk.util.IteratorSize;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

  @Autowired FlightScheduleRepo craftRepo;
  @Autowired FlightScheduleDispatcher craftDispatcher;
  @Autowired FlightPilotSummaryDispatcher pilotSummaryDispatcher;
  @Autowired FlightCaptainSummaryDispatcher captainSummaryDispatcher;
  @Autowired IteratorSize itrSize;
  @Autowired StoreRecord record;
  @Override
  public void getFlightScheduleData(String location, String date,StoreRecord rec) {
    Iterable<FlightSchedule> itr = craftRepo.findSchedule(location, date);
    Iterator<FlightSchedule> iter = itr.iterator();

    while (iter.hasNext())craftDispatcher.dispatch(iter.next(),rec);
  }

  @Override
  public void getFlightSchedulePilotData(
      int pilotId, String pilotDesignationCode, String dateOfDeparture) {
    if (pilotDesignationCode.equals("PD1")) {
      Iterable<FlightCaptainSummary> itr = craftRepo.getCaptainData(pilotId, dateOfDeparture);
      Iterator<FlightCaptainSummary> iter = itr.iterator();
      record.setFlightCaptainSummaryCount(itrSize.size(itr));
       while(iter.hasNext())
    	captainSummaryDispatcher.dispatch(iter.next());

    } else {
      Iterable<FlightPilotSummary> itr = craftRepo.getPilotData(pilotId, dateOfDeparture);
      Iterator<FlightPilotSummary> iter = itr.iterator();
      record.setFlightPilotSummaryCount(itrSize.size(itr));
      while(iter.hasNext())
    	  pilotSummaryDispatcher.dispatch(iter.next());
    }


  }
}
