package com.risk.result.model;

public class PilotDetail {

  double noPilot;
  String messNoPilot;
  double durLNinty;
  String messDurLastNinty;
  double totDur;
  String messTotDur;

  public PilotDetail() {
    super();
  }

  public double getNoPilot() {
    return noPilot;
  }

  public void setNoPilot(double noPilot) {
    this.noPilot = noPilot;
  }

  public String getMessNoPilot() {
    return messNoPilot;
  }

  public void setMessNoPilot(String messNoPilot) {
    this.messNoPilot = messNoPilot;
  }

  public double getDurLNinty() {
    return durLNinty;
  }

  public void setDurLNinty(double durLNinty) {
    this.durLNinty = durLNinty;
  }

  public String getMessDurLNinty() {
    return messDurLastNinty;
  }

  public void setMessDurLNinty(String messDurLNinty) {
    this.messDurLastNinty = messDurLNinty;
  }

  public double getTotDur() {
    return totDur;
  }

  public void setTotDur(double totDur) {
    this.totDur = totDur;
  }

  public String getMessTotDur() {
    return messTotDur;
  }

  public void setMessTotDur(String messTotDur) {
    this.messTotDur = messTotDur;
  }

  @Override
  public String toString() {
    return "PilotDetail [noPilot="
        + noPilot
        + ", messNoPilot="
        + messNoPilot
        + ", durLNinty="
        + durLNinty
        + ", messDurLastNinty="
        + messDurLastNinty
        + ", totDur="
        + totDur
        + ", messTotDur="
        + messTotDur
        + "]";
  }
}
