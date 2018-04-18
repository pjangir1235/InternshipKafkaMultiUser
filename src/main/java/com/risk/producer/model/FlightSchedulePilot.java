package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight_schedule_pilot")
public class FlightSchedulePilot {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer flightScheduleId;

  private Integer pilotId;

  public FlightSchedulePilot() {
    super();
  }

  public Integer getFlightScheduleId() {
    return flightScheduleId;
  }

  public Integer getPilotId() {
    return pilotId;
  }

  public void setFlightScheduleId(Integer flightScheduleId) {
    this.flightScheduleId = flightScheduleId;
  }

  public void setPilotId(Integer pilotId) {
    this.pilotId = pilotId;
  }
}
