package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aircraft_checklist")
public class AircraftChecklist {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer checklistId;

  private String aircraftCode;
  private String date;
  private Boolean autoPilot;
  private Boolean stormScope;
  private Boolean weatherRadar;
  private Boolean deIce;

  public AircraftChecklist() {
    super();
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public Boolean getAutoPilot() {
    return autoPilot;
  }

  public Integer getChecklistId() {
    return checklistId;
  }

  public String getDate() {
    return date;
  }

  public Boolean getDeIce() {
    return deIce;
  }

  public Boolean getStormScope() {
    return stormScope;
  }

  public Boolean getWeatherRadar() {
    return weatherRadar;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  public void setAutoPilot(Boolean autoPilot) {
    this.autoPilot = autoPilot;
  }

  public void setChecklistId(Integer checklistId) {
    this.checklistId = checklistId;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setDeIce(Boolean deIce) {
    this.deIce = deIce;
  }

  public void setStormScope(Boolean stormScope) {
    this.stormScope = stormScope;
  }

  public void setWeatherRadar(Boolean weatherRadar) {
    this.weatherRadar = weatherRadar;
  }
}
