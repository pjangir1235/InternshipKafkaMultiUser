package com.risk.consumer.model;

public class RestDetailDTO {
  private int memberId;
  private String date;
  private int restMinutes;

  public RestDetailDTO() {
    super();
  }

  public RestDetailDTO(int memberId, String date, int restMinutes) {
    super();
    this.memberId = memberId;
    this.date = date;
    this.restMinutes = restMinutes;
  }

  public String getDate() {

    return date;
  }

  public int getMemberId() {
    return memberId;
  }

  public int getRestMinutes() {
    return restMinutes;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setMemberId(int memberId) {
    this.memberId = memberId;
  }

  public void setRestMinutes(int restMinutes) {
    this.restMinutes = restMinutes;
  }

  @Override
  public String toString() {
    return "RestDetailData [memberId="
        + memberId
        + ", date="
        + date
        + ", restMinutes="
        + restMinutes
        + "]";
  }
}
