package com.risk.consumer.model;

public class AircraftDTO {
  private int aircraftId;
  private String aircraftCode;
  private int aircraftType;
  private String manufactureDate;
  private String registrationNo;

  public String getManufactureDate() {
    return manufactureDate;
  }

  public void setManufactureDate(String manufactureDate) {
    this.manufactureDate = manufactureDate;
  }

  public String getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public AircraftDTO() {
    super();
  }

  public AircraftDTO(int aircraftId, String aircraftCode, int aircraftType) {
    super();
    this.aircraftId = aircraftId;
    this.aircraftCode = aircraftCode;
    this.aircraftType = aircraftType;
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public int getAircraftId() {
    return aircraftId;
  }

  public int getAircraftType() {
    return aircraftType;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  public void setAircraftId(int aircraftId) {
    this.aircraftId = aircraftId;
  }

  public void setAircraftType(int aircraftType) {
    this.aircraftType = aircraftType;
  }
}
