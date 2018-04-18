package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aircraft_type_detail")
public class AircraftTypeValue {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer aircraftTypeId;

  private String aircraftType;

  public AircraftTypeValue() {
    super();
  }

  public String getAircraftType() {
    return aircraftType;
  }

  public Integer getAircraftTypeId() {
    return aircraftTypeId;
  }

  public void setAircraftType(String aircraftType) {
    this.aircraftType = aircraftType;
  }

  public void setAircraftTypeId(Integer aircraftTypeId) {
    this.aircraftTypeId = aircraftTypeId;
  }
}
