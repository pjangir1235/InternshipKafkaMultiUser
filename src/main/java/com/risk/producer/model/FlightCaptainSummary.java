package com.risk.producer.model;

public class FlightCaptainSummary extends FlightPilotSummary {

  public FlightCaptainSummary() {
    super();
  }

  public FlightCaptainSummary(Integer duration, String aircraftCode, String dateOfDeparture) {
    super();
    this.duration = duration;
    this.aircraftCode = aircraftCode;
    this.dateOfDeparture = dateOfDeparture;
  }

  @Override
  public String toString() {
    return "FlightCaptainSummary [duration="
        + duration
        + ", aircraftCode="
        + aircraftCode
        + ", dateOfDeparture="
        + dateOfDeparture
        + "]";
  }
}
