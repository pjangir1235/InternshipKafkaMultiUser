package com.risk.result.model;

public class SourceEnvironment {

  double mountain;
  String msgMountain;
  double nightOperation;
  String msgNightOper;
  double winterOper;
  String msgWinterOper;
  double rain;
  String msgRain;
  double frozen;
  String msgFrozen;
  double thunderStorm;
  String msgThunderStorm;
  double noWeather;
  String msgNoWeather;
  double windSpeed;
  String msgWindSpeed;
  double visibility;
  String msgVisibility;

  public SourceEnvironment() {
    super();
    rain=0;
    msgRain="Out of 5 \nNo Rain on Location";
    frozen=0;
    msgFrozen="Out of 3 \nNo Frozen on Location";
    thunderStorm=0;
    msgThunderStorm="Out of 5 \nNo Thunderstorm on Location";
  }

  public double getMountain() {
    return mountain;
  }

  public void setMountain(double mountain) {
    this.mountain = mountain;
  }

  public String getMsgMountain() {
    return msgMountain;
  }

  public void setMsgMountain(String msgMountain) {
    this.msgMountain = msgMountain;
  }

  public double getNightOperation() {
    return nightOperation;
  }

  public void setNightOperation(double nightOperation) {
    this.nightOperation = nightOperation;
  }

  public String getMsgNightOper() {
    return msgNightOper;
  }

  public void setMsgNightOper(String msgNightOper) {
    this.msgNightOper = msgNightOper;
  }

  public double getWinterOper() {
    return winterOper;
  }

  public void setWinterOper(double winterOper) {
    this.winterOper = winterOper;
  }

  public String getMsgWinterOper() {
    return msgWinterOper;
  }

  public void setMsgWinterOper(String msgWinterOper) {
    this.msgWinterOper = msgWinterOper;
  }

  public double getRain() {
    return rain;
  }

  public void setRain(double rain) {
    this.rain = rain;
  }

  public String getMsgRain() {
    return msgRain;
  }

  public void setMsgRain(String msgRain) {
    this.msgRain = msgRain;
  }

  public double getFrozen() {
    return frozen;
  }

  public void setFrozen(double frozen) {
    this.frozen = frozen;
  }

  public String getMsgFrozen() {
    return msgFrozen;
  }

  public void setMsgFrozen(String msgFrozen) {
    this.msgFrozen = msgFrozen;
  }

  public double getThunderStorm() {
    return thunderStorm;
  }

  public void setThunderStorm(double thunderStorm) {
    this.thunderStorm = thunderStorm;
  }

  public String getMsgThunderStorm() {
    return msgThunderStorm;
  }

  public void setMsgThunderStorm(String msgThunderStorm) {
    this.msgThunderStorm = msgThunderStorm;
  }

  public double getNoWeather() {
    return noWeather;
  }

  public void setNoWeather(double noWeather) {
    this.noWeather = noWeather;
  }

  public String getMsgNoWeather() {
    return msgNoWeather;
  }

  public void setMsgNoWeather(String msgNoWeather) {
    this.msgNoWeather = msgNoWeather;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public String getMsgWindSpeed() {
    return msgWindSpeed;
  }

  public void setMsgWindSpeed(String msgWindSpeed) {
    this.msgWindSpeed = msgWindSpeed;
  }

  public double getVisibility() {
    return visibility;
  }

  public void setVisibility(double visibility) {
    this.visibility = visibility;
  }

  public String getMsgVisibility() {
    return msgVisibility;
  }

  public void setMsgVisibility(String msgVisibility) {
    this.msgVisibility = msgVisibility;
  }
}
