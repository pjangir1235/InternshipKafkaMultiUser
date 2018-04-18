package com.risk.producer.model;

public class FlightPilotSummary {

  protected Integer duration;
  protected String aircraftCode;
  protected String dateOfDeparture;

  public FlightPilotSummary() {
    super();
  }

  public FlightPilotSummary(Integer duration, String aircraftCode, String dateOfDeparture) {
    super();
    this.duration = duration;
    this.aircraftCode = aircraftCode;
    this.dateOfDeparture = dateOfDeparture;
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public String getDateOfDeparture() {
    return dateOfDeparture;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  public void setDateOfDeparture(String dateOfDeparture) {
    this.dateOfDeparture = dateOfDeparture;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "FlightPilotSummary [duration="
        + duration
        + ", aircraftCode="
        + aircraftCode
        + ", dateOfDeparture="
        + dateOfDeparture
        + "]";
  }
}
