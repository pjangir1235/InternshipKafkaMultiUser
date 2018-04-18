package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crew")
public class Crew {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer crewMemberId;

  private String crewMemberName;
  Long mobile;

  public Crew() {
    super();
  }

  public Crew(Integer crewMemberId, String crewMemberName, Long mobile) {
    super();
    this.crewMemberId = crewMemberId;
    this.crewMemberName = crewMemberName;
    this.mobile = mobile;
  }

  public Integer getCrewMemberId() {
    return crewMemberId;
  }

  public String getCrewMemberName() {
    return crewMemberName;
  }

  public Long getMobile() {
    return mobile;
  }

  public void setCrewMemberId(Integer crewMemberId) {
    this.crewMemberId = crewMemberId;
  }

  public void setCrewMemberName(String crewMemberName) {
    this.crewMemberName = crewMemberName;
  }

  public void setMobile(Long mobile) {
    this.mobile = mobile;
  }

  @Override
  public String toString() {
    return this.getCrewMemberName();
  }
}
