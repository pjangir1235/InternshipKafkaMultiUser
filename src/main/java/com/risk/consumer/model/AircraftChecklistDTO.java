package com.risk.consumer.model;

public class AircraftChecklistDTO {
  private int checklistId;
  private String aircraftCode;
  private String date;
  private boolean autoPilot;
  private boolean stormScope;
  private boolean weatherRadar;
  private boolean deIce;

  public AircraftChecklistDTO() {
    super();
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public int getChecklistId() {
    return checklistId;
  }

  public String getDate() {
    return date;
  }

  public boolean isAutoPilot() {
    return autoPilot;
  }

  public boolean isDeIce() {
    return deIce;
  }

  public boolean isStormScope() {
    return stormScope;
  }

  public boolean isWeatherRadar() {
    return weatherRadar;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  public void setAutoPilot(boolean autoPilot) {
    this.autoPilot = autoPilot;
  }

  public void setChecklistId(int checklistId) {
    this.checklistId = checklistId;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setDeIce(boolean deIce) {
    this.deIce = deIce;
  }

  public void setStormScope(boolean stormScope) {
    this.stormScope = stormScope;
  }

  public void setWeatherRadar(boolean weatherRadar) {
    this.weatherRadar = weatherRadar;
  }
}
