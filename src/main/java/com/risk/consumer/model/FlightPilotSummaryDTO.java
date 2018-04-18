package com.risk.consumer.model;

public class FlightPilotSummaryDTO {

  int duration;
  String aircraftCode;
  String dateOfDeparture;

  public int getDuration() {
    return duration;
  }

  public FlightPilotSummaryDTO(int duration, String aircraftCode, String dateOfDeparture) {
    super();
    this.duration = duration;
    this.aircraftCode = aircraftCode;
    this.dateOfDeparture = dateOfDeparture;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  public String getDateOfDeparture() {
    return dateOfDeparture;
  }

  public void setDateOfDeparture(String dateOfDeparture) {
    this.dateOfDeparture = dateOfDeparture;
  }

  public FlightPilotSummaryDTO() {
    super();
  }

  @Override
  public String toString() {
    return "FlightPilotSummaryDTO [duration="
        + duration
        + ", aircraftCode="
        + aircraftCode
        + ", dateOfDeparture="
        + dateOfDeparture
        + "]";
  }
}
