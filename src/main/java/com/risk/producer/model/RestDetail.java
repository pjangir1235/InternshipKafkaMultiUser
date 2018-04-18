package com.risk.producer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rest_detail")
public class RestDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer memberId;

  private String date;
  private Integer restMinutes;

  public RestDetail() {
    super();
  }

  public String getDate() {
    return date;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public Integer getRestMinutes() {
    return restMinutes;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }

  public void setRestMinutes(Integer restMinutes) {
    this.restMinutes = restMinutes;
  }

  @Override
  public String toString() {
    return "RestDetail [memberId="
        + memberId
        + ", date="
        + date
        + ", restMinutes="
        + restMinutes
        + "]";
  }
}
