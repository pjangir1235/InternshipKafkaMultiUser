package com.risk.result.model;

public class FinalAnalysisData {
  private AircraftCheckListDetail aircraftCheckList;
  private CaptainDetail captainDetail;
  private HumanPerformance crewTotal;
  private DestinationEnvironment envDestination;
  private PilotDetail pilotDetail;
  private SourceEnvironment envSource;
  private Result result;

  public FinalAnalysisData() {
    super();
    aircraftCheckList = new AircraftCheckListDetail();
    captainDetail = new CaptainDetail();
    crewTotal = new HumanPerformance();
    envDestination = new DestinationEnvironment();
    pilotDetail = new PilotDetail();
    envSource = new SourceEnvironment();
    result = new Result();
  }

  public AircraftCheckListDetail getAircraftCheckList() {
    return aircraftCheckList;
  }

  public void setAircraftCheckList(AircraftCheckListDetail aircraftCheckList) {
    this.aircraftCheckList = aircraftCheckList;
  }

  public CaptainDetail getCaptainDetail() {
    return captainDetail;
  }

  public void setCaptainDetail(CaptainDetail captainDetail) {
    this.captainDetail = captainDetail;
  }

  public HumanPerformance getCrewTotal() {
    return crewTotal;
  }

  public void setCrewTotal(HumanPerformance crewTotal) {
    this.crewTotal = crewTotal;
  }

  public DestinationEnvironment getEnvDestination() {
    return envDestination;
  }

  public void setEnvDestination(DestinationEnvironment envDestination) {
    this.envDestination = envDestination;
  }

  public PilotDetail getPilotDetail() {
    return pilotDetail;
  }

  public void setPilotDetail(PilotDetail pilotDetail) {
    this.pilotDetail = pilotDetail;
  }

  public SourceEnvironment getEnvSource() {
    return envSource;
  }

  public void setEnvSource(SourceEnvironment envSource) {
    this.envSource = envSource;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
}
