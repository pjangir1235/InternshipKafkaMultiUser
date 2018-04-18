package com.risk.result.model;

public class DestinationEnvironment extends SourceEnvironment {

  public DestinationEnvironment() {
    super();
    rain = 0;
    msgRain = "Out of 5 \nNo Rain on Location";
    frozen = 0;
    msgFrozen = "Out of 3 \nNo Frozen on Location";
    thunderStorm = 0;
    msgThunderStorm = "Out of 5 \nNo Thunderstorm on Location";
  }
}
