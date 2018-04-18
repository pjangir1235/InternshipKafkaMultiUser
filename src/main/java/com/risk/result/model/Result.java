package com.risk.result.model;

public class Result {
  double proficiency;
  double sourceEnvironment;
  double destinationEnvironment;
  double aircraft;
  double human;
  double finalPercent;
  String Conclusion;

  public double getProficiency() {
    return proficiency;
  }

  public void setProficiency(double proficiency) {
    this.proficiency = proficiency;
  }

  public double getSourceEnvironment() {
    return sourceEnvironment;
  }

  public void setSourceEnvironment(double sourceEnvironment) {
    this.sourceEnvironment = sourceEnvironment;
  }

  public double getDestinationEnvironment() {
    return destinationEnvironment;
  }

  public void setDestinationEnvironment(double destinationEnvironment) {
    this.destinationEnvironment = destinationEnvironment;
  }

  public double getAircraft() {
    return aircraft;
  }

  public void setAircraft(double aircraft) {
    this.aircraft = aircraft;
  }

  public double getHuman() {
    return human;
  }

  public void setHuman(double human) {
    this.human = human;
  }

  public double getFinalPercent() {
    return finalPercent;
  }

  public void setFinalPercent(double finalPercent) {
    this.finalPercent = finalPercent;
  }

  public String getConclusion() {
    return Conclusion;
  }

  public void setConclusion(String conclusion) {
    Conclusion = conclusion;
  }

  public Result() {
    super();
  }

@Override public String toString(){return "Result [proficiency=" + proficiency + ", sourceEnvironment=" + sourceEnvironment + ", destinationEnvironment=" + destinationEnvironment + ", aircraft=" + aircraft + ", human=" + human + ", finalPercent=" + finalPercent + ", Conclusion=" + Conclusion + "]";}

}
