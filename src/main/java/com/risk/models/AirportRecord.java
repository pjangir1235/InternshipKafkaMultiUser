package com.risk.models;

import java.util.ArrayList;
import java.util.List;

import com.risk.consumer.model.AirportDTO;

public class AirportRecord {
  boolean isUpdated;
  int startOffset;
  int endOffSet;
  List<AirportDTO> airport;
  int airportCount;

  public AirportRecord() {
    super();
    isUpdated = false;
    startOffset = Integer.MAX_VALUE;
    endOffSet = Integer.MAX_VALUE;
    airport = new ArrayList<>();
    airportCount = 0;
  }

  public int getAirportCount() {
    return airportCount;
  }

  public void setAirportCount(int airportCount) {
    this.airportCount = airportCount;
  }

  public boolean isUpdated() {
    return isUpdated;
  }

  public void setUpdated(boolean isUpdated) {
    this.isUpdated = isUpdated;
  }

  public int getStartOffset() {
    return startOffset;
  }

  public void setStartOffset(int startOffset) {
    this.startOffset = startOffset;
  }

  public int getEndOffSet() {
    return endOffSet;
  }

  public void setEndOffSet(int endOffSet) {
    this.endOffSet = endOffSet;
  }

  public List<AirportDTO> getAirport() {
    return airport;
  }

  public void setAirport(AirportDTO airport) {
    this.airport.add(airport);
  }
}
