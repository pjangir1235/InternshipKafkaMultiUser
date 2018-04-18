package com.risk.models;

public class ScheduleRequestDTO {
  private String location;
  private String date;

  public ScheduleRequestDTO() {
    super();
  }

  public ScheduleRequestDTO(String location, String date) {
    super();
    this.location = location;
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public String getLocation() {
    return location;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
