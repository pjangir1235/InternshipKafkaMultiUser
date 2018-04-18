package com.risk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.risk.constants.CommonConstant;

public class Environment extends Unit {

  @JsonProperty(CommonConstant.STATION)
  private String station;

  @JsonProperty(CommonConstant.UNITS)
  private Unit unit;

  @JsonProperty(CommonConstant.TRANSLATIONS)
  private Translations translation;

  public Environment() {
    super();
  }

  public String getStation() {
    return station;
  }

  public Translations getTranslation() {
    return translation;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setStation(String station) {
    this.station = station;
  }

  public void setTranslation(Translations translation) {
    this.translation = translation;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    return "Environment [station="
        + station
        + ", unit="
        + unit
        + ", translation="
        + translation
        + "]";
  }
}
