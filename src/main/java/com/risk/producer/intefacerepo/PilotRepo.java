package com.risk.producer.intefacerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.producer.model.Pilot;

@Repository
public interface PilotRepo extends CrudRepository<Pilot, Long> {


}
