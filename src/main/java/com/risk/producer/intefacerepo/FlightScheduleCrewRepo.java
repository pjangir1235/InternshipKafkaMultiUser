package com.risk.producer.intefacerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.producer.model.FlightScheduleCrew;

@Repository
public interface FlightScheduleCrewRepo extends CrudRepository<FlightScheduleCrew, Long> {}
