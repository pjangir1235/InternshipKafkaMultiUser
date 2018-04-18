package com.risk.producer.intefacerepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.constants.Queries;
import com.risk.producer.model.AircraftChecklist;

@Repository
public interface AircraftChecklistRepo extends CrudRepository<AircraftChecklist, Long> {

  @Query(value = Queries.AIRCRAFTCHECKLIST)
  List<AircraftChecklist> findAircraft(String aircraftCode, String dateOfDeparture);
}
