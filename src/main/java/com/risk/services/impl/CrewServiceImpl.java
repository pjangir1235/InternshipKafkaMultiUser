package com.risk.services.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.producer.dispatcher.CrewDispatcher;
import com.risk.producer.intefacerepo.CrewRepo;
import com.risk.producer.model.Crew;
import com.risk.services.interfaces.CrewService;

@Service
public class CrewServiceImpl implements CrewService {

  @Autowired CrewRepo craftRepo;
  @Autowired CrewDispatcher craftDispatcher;

  /* (non-Javadoc)
   * @see com.risk.services.impl.CrewService#getCrewData()
   */
  @Override
  public void getCrewData() {
    Iterable<Crew> itr = craftRepo.findAll();
    Iterator<Crew> iter = itr.iterator();
    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
  }
}
