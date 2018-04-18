package com.risk.producer.intefacerepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.constants.Queries;
import com.risk.producer.model.Aircraft;
import com.risk.producer.model.AircraftCodeType;

@Repository
public interface AircraftRepo extends CrudRepository<Aircraft, Long> {

  @Query(value = Queries.AIRCRAFTBYTYPE)
  List<AircraftCodeType> findAllCodeforType(String aircraftCode);

  @Query(value = Queries.AIRCRAFTBYCode)
  List<Aircraft> findData(String aircraftCode);
}
