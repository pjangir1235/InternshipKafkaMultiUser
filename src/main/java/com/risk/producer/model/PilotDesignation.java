package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pilot_designation")
public class PilotDesignation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String pilotDesignationCode;

  private String pilotDesignationName;

  public PilotDesignation() {
    super();
  }

  public String getPilotDesignationCode() {
    return pilotDesignationCode;
  }

  public String getPilotDesignationName() {
    return pilotDesignationName;
  }

  public void setPilotDesignationCode(String pilotDesignationCode) {
    this.pilotDesignationCode = pilotDesignationCode;
  }

  public void setPilotDesignationName(String pilotDesignationName) {
    this.pilotDesignationName = pilotDesignationName;
  }
}
