package com.risk.producer.intefacerepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.constants.Queries;
import com.risk.producer.model.FlightCaptainSummary;
import com.risk.producer.model.FlightPilotSummary;
import com.risk.producer.model.FlightSchedule;

@Repository
public interface FlightScheduleRepo extends CrudRepository<FlightSchedule, Long> {

  @Query(Queries.SCHEDULEBYLOCATION)
  List<FlightSchedule> findSchedule(String location, String date);

  @Query(Queries.SCHEDULEBYPILOT)
  List<FlightPilotSummary> getPilotData(int id, String dateOfDeparture);
  @Query(Queries.SCHEDULEBYCAPTAIN)
  List<FlightCaptainSummary> getCaptainData(int id, String dateOfDeparture);
}
