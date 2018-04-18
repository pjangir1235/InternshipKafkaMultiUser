package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.PilotDesignationDispatcher;
import com.risk.producer.intefacerepo.PilotDesignationRepo;
import com.risk.producer.model.PilotDesignation;
import com.risk.services.interfaces.PilotDesignationService;

@Service
public class PilotDesignationServiceImpl implements PilotDesignationService {

  @Autowired PilotDesignationRepo craftRepo;
  @Autowired PilotDesignationDispatcher craftDispatcher;

  /* (non-Javadoc)
   * @see com.risk.services.impl.PilotDesignationService#getPilotDesignationData()
   */
  @Override
  public void getPilotDesignationData() {
    Iterable<PilotDesignation> itr = craftRepo.findAll();
    Iterator<PilotDesignation> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
  }
}
