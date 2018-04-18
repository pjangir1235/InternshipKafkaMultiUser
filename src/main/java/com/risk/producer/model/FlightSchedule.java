package com.risk.producer.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer flightScheduleId;

  private String aircraftCode;
  private String sourceAirportCode;
  private String destinationAirportCode;
  private String dateOfDeparture;
  private String timeDeparture;
  private String timeArrival;
  private Integer duration;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "flight_schedule_pilot",
    joinColumns = {@JoinColumn(name = "flightScheduleId")},
    inverseJoinColumns = {@JoinColumn(name = "pilotId")}
  )
  List<Pilot> pilots;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "flight_schedule_crew",
    joinColumns = {@JoinColumn(name = "flightScheduleId")},
    inverseJoinColumns = {@JoinColumn(name = "crewMemberId")}
  )
  List<Crew> crews;

  public FlightSchedule() {
    super();
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public List<Crew> getCrews() {
    return crews;
  }

  public String getDateOfDeparture() {
    return dateOfDeparture;
  }

  public String getDestinationAirportCode() {
    return destinationAirportCode;
  }

  public Integer getDuration() {
    return duration;
  }

  public Integer getFlightScheduleId() {
    return flightScheduleId;
  }

  public List<Pilot> getPilots() {
    return pilots;
  }

  public String getSourceAirportCode() {
    return sourceAirportCode;
  }

  public String getTimeArrival() {
    return timeArrival;
  }

  public String getTimeDeparture() {
    return timeDeparture;
  }

  public void setAircraftCode(String aircraftCode) {
    this.aircraftCode = aircraftCode;
  }

  public void setCrews(List<Crew> crews) {
    this.crews = crews;
  }

  public void setDateOfDeparture(String dateOfDeparture) {
    this.dateOfDeparture = dateOfDeparture;
  }

  public void setDestinationAirportCode(String destinationAirportCode) {
    this.destinationAirportCode = destinationAirportCode;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public void setFlightScheduleId(Integer flightScheduleId) {
    this.flightScheduleId = flightScheduleId;
  }

  public void setPilots(List<Pilot> pilots) {
    this.pilots = pilots;
  }

  public void setSourceAirportCode(String sourceAirportCode) {
    this.sourceAirportCode = sourceAirportCode;
  }

  public void setTimeArrival(String timeArrival) {
    this.timeArrival = timeArrival;
  }

  public void setTimeDeparture(String timeDeparture) {
    this.timeDeparture = timeDeparture;
  }

  @Override
  public String toString() {
    return this.sourceAirportCode
        + "  "
        + this.flightScheduleId
        + " "
        + this.getPilots()
        + " "
        + this.getCrews();
  }
}
