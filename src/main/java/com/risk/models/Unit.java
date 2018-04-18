package com.risk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.risk.constants.CommonConstant;

public class Unit {
  @JsonProperty(CommonConstant.TEMPERATURE)
  protected String temperature;

  @JsonProperty(CommonConstant.WINDSPEED)
  protected String windSpeed;

  @JsonProperty(CommonConstant.VISIBILITY)
  protected String visibility;

  public Unit() {
    super();
  }

  public String getTemperature() {
    return temperature;
  }

  public String getVisibility() {
    return visibility;
  }

  public String getWindSpeed() {
    return windSpeed;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public void setWindSpeed(String windSpeed) {
    this.windSpeed = windSpeed;
  }
}
