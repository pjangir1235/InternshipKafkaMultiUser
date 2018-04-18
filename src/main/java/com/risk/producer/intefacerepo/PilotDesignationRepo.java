package com.risk.producer.intefacerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.risk.producer.model.PilotDesignation;

@Repository
public interface PilotDesignationRepo extends CrudRepository<PilotDesignation, Long> {}
