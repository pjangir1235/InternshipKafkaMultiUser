package com.risk.result.model;



public class FinalAnalysisData {
  private AircraftCheckListDetail aircraftCheckList;
  private CaptainDetail captainDetail;
  private HumanPerformance crewTotal;
  private DestinationEnvironment destEnv;
  private PilotDetail pilotDetail;
  private SourceEnvironment sourceEnv;
  private Result result;

  public FinalAnalysisData() {
    super();
   aircraftCheckList=new AircraftCheckListDetail();
   captainDetail=new CaptainDetail();
    crewTotal=new HumanPerformance();
   destEnv=new DestinationEnvironment();
    pilotDetail=new PilotDetail();
    sourceEnv=new SourceEnvironment();
    result=new Result();
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

  public DestinationEnvironment getDestEnv() {
    return destEnv;
  }

  public void setDestEnv(DestinationEnvironment destEnv) {
    this.destEnv = destEnv;
  }

  public PilotDetail getPilotDetail() {
    return pilotDetail;
  }

  public void setPilotDetail(PilotDetail pilotDetail) {
    this.pilotDetail = pilotDetail;
  }

  public SourceEnvironment getSourceEnv() {
    return sourceEnv;
  }

  public void setSourceEnv(SourceEnvironment sourceEnv) {
    this.sourceEnv = sourceEnv;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
}
