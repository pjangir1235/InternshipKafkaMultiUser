package com.risk.producer.intefacerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.producer.model.Airport;

@Repository
public interface AirportRepo extends CrudRepository<Airport, Long> {}
