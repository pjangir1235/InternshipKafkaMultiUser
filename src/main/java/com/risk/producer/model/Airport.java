package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer airportId;

  private String airportCode;
  private String airportName;
  private Boolean isMountain;

  public Airport() {
    super();
  }

  public Airport(Integer airportId, String airportCode, String airportName) {
    super();
    this.airportId = airportId;
    this.airportCode = airportCode;
    this.airportName = airportName;
  }

  public String getAirportCode() {
    return airportCode;
  }

  public Integer getAirportId() {
    return airportId;
  }

  public String getAirportName() {
    return airportName;
  }

  public Boolean getIsMountain() {
    return isMountain;
  }

  public void setAirportCode(String airportCode) {
    this.airportCode = airportCode;
  }

  public void setAirportId(Integer airportId) {
    this.airportId = airportId;
  }

  public void setAirportName(String airportName) {
    this.airportName = airportName;
  }

  public void setIsMountain(Boolean isMountain) {
    this.isMountain = isMountain;
  }
}
