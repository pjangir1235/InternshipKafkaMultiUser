package com.risk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.models.StoreRecord;
import com.risk.producer.dispatcher.RestDetailDispatcher;
import com.risk.producer.intefacerepo.RestDetailRepo;
import com.risk.producer.model.RestDetail;
import com.risk.services.interfaces.RestDetailService;
import com.risk.util.IteratorSize;

@Service
public class RestDetailServiceImpl implements RestDetailService {

  @Autowired RestDetailRepo craftRepo;
  @Autowired RestDetailDispatcher craftDispatcher;
  @Autowired IteratorSize itrSize;
  @Autowired StoreRecord record;

  @Override
  public void getCrewRestDetail(int crewMemberId, String date,StoreRecord record) {

    RestDetail detail= craftRepo.findById(crewMemberId, date);
    craftDispatcher.dispatch(detail, record);
  }

//  @Override
//  public void getRestDetailData() {
//    Iterable<RestDetail> itr = craftRepo.findAll();
//    Iterator<RestDetail> iter = itr.iterator();
//    record.setRestDetailCount(itrSize.size(itr));
//    while (iter.hasNext()) craftDispatcher.dispatch(iter.next());
//  }
}
