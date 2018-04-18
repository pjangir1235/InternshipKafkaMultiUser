package com.risk.consumer.model;

public class PilotDesignationDTO {

  private String pilotDesignationCode;
  private String pilotDesignationName;

  public PilotDesignationDTO() {
    super();
  }

  public PilotDesignationDTO(String pilotDesignationCode, String pilotDesignationName) {
    super();
    this.pilotDesignationCode = pilotDesignationCode;
    this.pilotDesignationName = pilotDesignationName;
  }

  public String getPilotDesignationCode() {
    return pilotDesignationCode;
  }

  public String getPilotDesignationName() {
    return pilotDesignationName;
  }

  public void setPilotDesignationCode(String pilotDesignationCode) {
    this.pilotDesignationCode = pilotDesignationCode;
  }

  public void setPilotDesignationName(String pilotDesignationName) {
    this.pilotDesignationName = pilotDesignationName;
  }
}
