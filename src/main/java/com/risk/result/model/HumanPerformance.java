package com.risk.result.model;

public class HumanPerformance {
  double dutyTime;
  double restTime;
  String msgDutyTime;
  String msgRestTime;

  public double getDutyTime() {
    return dutyTime;
  }

  public void setDutyTime(double dutyTime) {
    this.dutyTime = dutyTime;
  }

  public double getRestTime() {
    return restTime;
  }

  public void setRestTime(double restTime) {
    this.restTime = restTime;
  }

  public String getMsgDutyTime() {
    return msgDutyTime;
  }

  public void setMsgDutyTime(String msgDutyTime) {
    this.msgDutyTime = msgDutyTime;
  }

  public String getMsgRestTime() {
    return msgRestTime;
  }

  public void setMsgRestTime(String msgRestTime) {
    this.msgRestTime = msgRestTime;
  }

  public HumanPerformance() {
    super();
  }
}
