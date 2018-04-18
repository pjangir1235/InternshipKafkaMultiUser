package com.risk.producer.intefacerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.producer.model.FlightSchedulePilot;

@Repository
public interface FlightSchedulePilotRepo extends CrudRepository<FlightSchedulePilot, Long> {}
