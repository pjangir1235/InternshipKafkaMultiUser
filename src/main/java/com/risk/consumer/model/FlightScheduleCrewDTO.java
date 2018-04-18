package com.risk.consumer.model;

public class FlightScheduleCrewDTO {

  private int crewMemberId;
  private int flightScheduleId;

  public FlightScheduleCrewDTO() {
    super();
  }

  public FlightScheduleCrewDTO(int crewMemberId, int flightScheduleId) {
    super();
    this.crewMemberId = crewMemberId;
    this.flightScheduleId = flightScheduleId;
  }

  public int getCrewMemberId() {
    return crewMemberId;
  }

  public int getFlightScheduleId() {
    return flightScheduleId;
  }

  public void setCrewMemberId(int crewMemberId) {
    this.crewMemberId = crewMemberId;
  }

  public void setFlightScheduleId(int flightScheduleId) {
    this.flightScheduleId = flightScheduleId;
  }
}
