package com.risk.consumer.model;

public class AircraftCodeDTO {
  String aircraftCode;

  public AircraftCodeDTO() {
    super();
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  @Override
  public String toString() {
    return "AircraftCodeData [aircraftCode=" + aircraftCode + "]";
  }
}
