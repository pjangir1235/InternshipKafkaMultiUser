package com.risk.consumer.model;

public class PilotDTO {
  private int pilotId;
  private String pilotName;
  private String pilotDesignationCode;
  private String mobile;

  public PilotDTO() {
    super();
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPilotDesignationCode() {
    return pilotDesignationCode;
  }

  public int getPilotId() {
    return pilotId;
  }

  public String getPilotName() {
    return pilotName;
  }

  public void setPilotDesignationCode(String pilotDesignationCode) {
    this.pilotDesignationCode = pilotDesignationCode;
  }

  public void setPilotId(int pilotId) {
    this.pilotId = pilotId;
  }

  public void setPilotName(String pilotName) {
    this.pilotName = pilotName;
  }
}
