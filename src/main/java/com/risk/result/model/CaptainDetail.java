package com.risk.result.model;

public class CaptainDetail {
  double durTot;
  String messDurTot;
  double durLNinty;
  String messDurLNinty;
  double totHour;
  String messTotHour;
  double landing;
  String messLanding;

  public CaptainDetail() {
    super();
  }

  public double getDurTot() {
    return durTot;
  }

  public void setDurTot(double durTot) {
    this.durTot = durTot;
  }

  public String getMessDurTot() {
    return messDurTot;
  }

  public void setMessDurTot(String messDurTot) {
    this.messDurTot = messDurTot;
  }

  public double getDurLNinty() {
    return durLNinty;
  }

  public void setDurLNinty(double durLNinty) {
    this.durLNinty = durLNinty;
  }

  public String getMessDurLNinty() {
    return messDurLNinty;
  }

  public void setMessDurLNinty(String messDurLNinty) {
    this.messDurLNinty = messDurLNinty;
  }

  public double getTotHour() {
    return totHour;
  }

  public void setTotHour(double totHour) {
    this.totHour = totHour;
  }

  public String getMessTotHour() {
    return messTotHour;
  }

  public void setMessTotHour(String messTotHour) {
    this.messTotHour = messTotHour;
  }

  public double getLanding() {
    return landing;
  }

  public void setLanding(double landing) {
    this.landing = landing;
  }

  public String getMessLanding() {
    return messLanding;
  }

  public void setMessLanding(String messLanding) {
    this.messLanding = messLanding;
  }

@Override public String toString(){return "CaptainDetail [durTot=" + durTot + ", messDurTot=" + messDurTot + ", durLNinty=" + durLNinty + ", messDurLNinty=" + messDurLNinty + ", totHour=" + totHour + ", messTotHour=" + messTotHour + ", landing=" + landing + ", messLanding=" + messLanding + "]";}

}
