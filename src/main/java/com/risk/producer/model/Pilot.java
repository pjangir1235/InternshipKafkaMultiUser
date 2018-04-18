package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pilot")
public class Pilot {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer pilotId;

  private String pilotName;

  private String pilotDesignationCode;

  private String mobile;

  public Pilot() {
    super();
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPilotDesignationCode() {
    return pilotDesignationCode;
  }

  public Integer getPilotId() {
    return pilotId;
  }

  public String getPilotName() {
    return pilotName;
  }

  public void setPilotDesignationCode(String pilotDesignationCode) {
    this.pilotDesignationCode = pilotDesignationCode;
  }

  public void setPilotId(Integer pilotId) {
    this.pilotId = pilotId;
  }

  public void setPilotName(String pilotName) {
    this.pilotName = pilotName;
  }

  @Override
  public String toString() {
    return getPilotName();
  }
}
