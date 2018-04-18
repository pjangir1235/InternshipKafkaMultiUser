package com.risk.consumer.model;

public class FlightSchedulePilotDTO {

  private int flightScheduleId;
  private int pilotId;

  public FlightSchedulePilotDTO() {
    super();
  }

  public FlightSchedulePilotDTO(int flightScheduleId, int pilotId) {
    super();
    this.flightScheduleId = flightScheduleId;
    this.pilotId = pilotId;
  }

  public int getFlightScheduleId() {
    return flightScheduleId;
  }

  public int getPilotId() {
    return pilotId;
  }

  public void setFlightScheduleId(int flightScheduleId) {
    this.flightScheduleId = flightScheduleId;
  }

  public void setPilotId(int pilotId) {
    this.pilotId = pilotId;
  }
}
