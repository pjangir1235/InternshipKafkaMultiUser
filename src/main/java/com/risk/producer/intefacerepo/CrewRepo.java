package com.risk.producer.intefacerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.producer.model.Crew;

@Repository
public interface CrewRepo extends CrudRepository<Crew, Long> {}
