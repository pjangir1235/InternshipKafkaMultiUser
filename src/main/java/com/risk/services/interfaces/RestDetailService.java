package com.risk.services.interfaces;

import com.risk.models.StoreRecord;

public interface RestDetailService {

  void getCrewRestDetail(int crewMemberId, String date, StoreRecord record);

}
