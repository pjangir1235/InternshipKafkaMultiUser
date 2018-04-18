package com.risk.consumer.model;

public class CrewDTO {

  private int crewMemberId;
  private String crewMemberName;
  private long mobile;

  public CrewDTO() {
    super();
  }

  public int getCrewMemberId() {
    return crewMemberId;
  }

  public String getCrewMemberName() {
    return crewMemberName;
  }

  public long getMobile() {
    return mobile;
  }

  public void setCrewMemberId(int crewMemberId) {
    this.crewMemberId = crewMemberId;
  }

  public void setCrewMemberName(String crewMemberName) {
    this.crewMemberName = crewMemberName;
  }

  public void setMobile(long mobile) {
    this.mobile = mobile;
  }

  @Override
  public String toString() {
    return "CrewData [crewMemberId="
        + crewMemberId
        + ", crewMemberName="
        + crewMemberName
        + ", mobile="
        + mobile
        + "]";
  }
}
