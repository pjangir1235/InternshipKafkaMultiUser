package com.risk.consumer.model;

public class FlightCaptainSummaryDTO extends FlightPilotSummaryDTO {



  public FlightCaptainSummaryDTO() {
    super();
  }

  @Override
  public String toString() {
    return "FlightCaptainSummaryDTO [duration="
        + duration
        + ", aircraftCode="
        + aircraftCode
        + ", dateOfDeparture="
        + dateOfDeparture
        + "]";
  }
}
