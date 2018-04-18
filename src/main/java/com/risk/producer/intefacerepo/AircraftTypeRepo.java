package com.risk.producer.intefacerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.producer.model.AircraftTypeValue;

@Repository
public interface AircraftTypeRepo extends CrudRepository<AircraftTypeValue, Long> {}
