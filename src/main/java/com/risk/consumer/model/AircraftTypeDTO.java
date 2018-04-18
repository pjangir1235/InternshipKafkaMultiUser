package com.risk.consumer.model;

public class AircraftTypeDTO {
  private int aircraftTypeId;
  private String aircraftType;

  public AircraftTypeDTO() {
    super();
  }

  public AircraftTypeDTO(int aircraftTypeId, String aircraftType) {
    super();
    this.aircraftTypeId = aircraftTypeId;
    this.aircraftType = aircraftType;
  }

  public String getAircraftType() {
    return aircraftType;
  }

  public int getAircraftTypeId() {
    return aircraftTypeId;
  }

  public void setAircraftType(String aircraftType) {
    this.aircraftType = aircraftType;
  }

  public void setAircraftTypeId(int aircraftTypeId) {
    this.aircraftTypeId = aircraftTypeId;
  }
}
