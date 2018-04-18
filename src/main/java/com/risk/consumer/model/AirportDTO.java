package com.risk.consumer.model;

public class AirportDTO {
  private int airportId;
  private String airportName;
  private String airportCode;
  private boolean isMountain;

  public AirportDTO() {
    super();
  }

  public AirportDTO(int airportId, String airportName, String airportCode) {
    super();
    this.airportId = airportId;
    this.airportName = airportName;
    this.airportCode = airportCode;
  }

  public String getAirportCode() {
    return airportCode;
  }

  public int getAirportId() {
    return airportId;
  }

  public String getAirportName() {
    return airportName;
  }

  public boolean getIsMountain() {
    return isMountain;
  }

  public void setAirportCode(String airportCode) {
    this.airportCode = airportCode;
  }

  public void setAirportId(int airportId) {
    this.airportId = airportId;
  }

  public void setAirportName(String airportName) {
    this.airportName = airportName;
  }

  public void setMountain(boolean isMountain) {
    this.isMountain = isMountain;

  }

@Override public String toString(){return "AirportDTO [airportId=" + airportId + ", airportName=" + airportName + ", airportCode=" + airportCode + ", isMountain=" + isMountain + "]";}
}
