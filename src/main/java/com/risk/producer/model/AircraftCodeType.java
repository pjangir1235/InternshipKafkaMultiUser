package com.risk.producer.model;

public class AircraftCodeType {
  String aircraftCode;

  public AircraftCodeType() {
    super();
  }

  public AircraftCodeType(String aircraftCode) {
    super();
    this.aircraftCode = aircraftCode;
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  @Override
  public String toString() {
    return "AircraftCodeType [aircraftCode=" + aircraftCode + "]";
  }
}
