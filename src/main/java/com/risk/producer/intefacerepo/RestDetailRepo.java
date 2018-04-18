package com.risk.producer.intefacerepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.constants.Queries;
import com.risk.producer.model.RestDetail;

@Repository
public interface RestDetailRepo extends CrudRepository<RestDetail, Long> {
  @Query(value = Queries.RESTDETAILBYID)
  List<RestDetail> findById(int crewMemberId, String date);
}
