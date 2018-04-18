package com.risk.consumer.model;

import java.util.List;

public class FlightScheduleDTO {
  private int flightScheduleId;
  private String aircraftCode;
  private String sourceAirportCode;
  private String destinationAirportCode;
  private String dateOfDeparture;
  private String timeDeparture;
  private String timeArrival;
  private List<PilotDTO> pilots;
  private List<CrewDTO> crews;

  int duration;

  public FlightScheduleDTO() {
    super();
  }

  public String getAircraftCode() {
    return aircraftCode;
  }

  public List<CrewDTO> getCrews() {
    return crews;
  }

  public String getDateOfDeparture() {
    return dateOfDeparture;
  }

  public String getDestinationAirportCode() {
    return destinationAirportCode;
  }

  public int getDuration() {
    return duration;
  }

  public int getFlightScheduleId() {
    return flightScheduleId;
  }

  public List<PilotDTO> getPilots() {
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

  public void setCrews(List<CrewDTO> crews) {
    this.crews = crews;
  }

  public void setDateOfDeparture(String dateOfDeparture) {
    this.dateOfDeparture = dateOfDeparture;
  }

  public void setDestinationAirportCode(String destinationAirportCode) {
    this.destinationAirportCode = destinationAirportCode;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public void setFlightScheduleId(int flightScheduleId) {
    this.flightScheduleId = flightScheduleId;
  }

  public void setPilots(List<PilotDTO> pilots) {
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
}
