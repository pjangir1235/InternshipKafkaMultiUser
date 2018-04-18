package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.PilotDispatcher;
import com.risk.producer.intefacerepo.PilotRepo;
import com.risk.producer.model.Pilot;
import com.risk.services.interfaces.PilotService;

@Service
public class PilotServiceImpl implements PilotService {

  @Autowired PilotRepo craftRepo;
  @Autowired PilotDispatcher craftDispatcher;

  @Override
  public void getPilotData() {

     Iterable<Pilot> itr = craftRepo.findAll();
      Iterator<Pilot> iter = itr.iterator();
      while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
    }

}
