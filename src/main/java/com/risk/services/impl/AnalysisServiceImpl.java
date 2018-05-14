package com.risk.services.impl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.risk.models.AirportRecord;
import com.risk.models.StoreRecord;
import com.risk.result.model.AircraftCheckListDetail;
import com.risk.result.model.CaptainDetail;
import com.risk.result.model.DestinationEnvironment;
import com.risk.result.model.FinalAnalysisData;
import com.risk.result.model.HumanPerformance;
import com.risk.result.model.PilotDetail;
import com.risk.result.model.Result;
import com.risk.result.model.SourceEnvironment;
import com.risk.services.analysis.impl.AircraftAnalysisService;
import com.risk.services.analysis.impl.CaptainAnalysisService;
import com.risk.services.analysis.impl.CrewTotalService;
import com.risk.services.analysis.impl.DestinationEnvironmentService;
import com.risk.services.analysis.impl.PilotAnalysisService;
import com.risk.services.analysis.impl.SourceEnvironmentService;
import com.risk.util.Calculation;

@Scope("prototype")
@Service
public class AnalysisServiceImpl {

  @Autowired private ApplicationContext cfx;
  @Autowired AirportRecord recordAirport;
  private AircraftAnalysisService aircraft;
  private CaptainAnalysisService captain;
  private CrewTotalService crew;
  private DestinationEnvironmentService envDestination;
  private SourceEnvironmentService envSource;
  private PilotAnalysisService pilot;
  private StoreRecord record;

  private AircraftCheckListDetail aircraftCheckList;
  private CaptainDetail captainDetail;
  private HumanPerformance crewTotal;
  private PilotDetail pilotDetail;
  private SourceEnvironment sourceEnv;
  private Result result;
  double value;
  double total;
  double percentageProficiency;
  double percentageSource;
  double percentageDestination;
  double percentageAircraft;
  double percentageHuman;
  double outOf;
  double percentageInHundered;

  @Autowired Calculation calc;

  public AnalysisServiceImpl() {
    super();
  }

  public void setAnalysisServiceImpl(StoreRecord rec) {
    this.record = rec;
    aircraft = cfx.getBean(AircraftAnalysisService.class);
    aircraft.setAircraftAnalysisServiceImpl(record);
    captain = cfx.getBean(CaptainAnalysisService.class);
    captain.setCaptainAnalysisServiceImpl(record);
    crew = cfx.getBean(CrewTotalService.class);
    crew.setCrewTotalServiceImpl(record);
    envDestination = cfx.getBean(DestinationEnvironmentService.class);
    envDestination.setDestinationEnvironmentServiceImpl(record);
    envSource = cfx.getBean(SourceEnvironmentService.class);
    envSource.setSourceEnvironmentServiceImpl(record);
    pilot = cfx.getBean(PilotAnalysisService.class);
    pilot.setPilotAnalysisServiceImpl(record);
  }

  public void startCalculation() {
    result = new Result();
    crew.getAnalysisData();
    captain.finalCalc();
    pilot.finalCalc();

    aircraftCheckList = record.getAircraftCheckList();
    captainDetail = record.getCaptainDetail();
    crewTotal = record.getCrewTotal();
    DestinationEnvironment destEnv = record.getEnvDestination();
    pilotDetail = record.getPilotDetail();
    sourceEnv = record.getEnvSource();

    proficiency();
    sourceEnvironment();
    destinationEnvironmen();
    aircraft();
    humanPerformance();
    finalResult();
    FinalAnalysisData finalData = new FinalAnalysisData();
    finalData.setAircraftCheckList(aircraftCheckList);
    finalData.setCaptainDetail(captainDetail);
    finalData.setCrewTotal(crewTotal);
    finalData.setEnvDestination(destEnv);
    finalData.setPilotDetail(pilotDetail);
    finalData.setEnvSource(sourceEnv);
    finalData.setResult(result);
    record.setFinalData(finalData);
  }

  @Override
  public String toString() {
    return "AnalysisServiceImpl [aircraft="
        + aircraft
        + ", captain="
        + captain
        + ", crew="
        + crew
        + ", envDestination="
        + envDestination
        + ", envSource="
        + envSource
        + ", pilot="
        + pilot
        + ", record="
        + record
        + "]";
  }

  public void proficiency() {
    value = captainDetail.getDurLNinty();
    value = calc.convertInFive(value, 4);
    total = value;
    outOf = 5;
    value = captainDetail.getTotHour();
    total += value;
    outOf += 5;
    value = captainDetail.getDurTot();
    value = calc.convertInFive(value, 3);
    total += value;
    outOf += 5;
    value = captainDetail.getLanding();
    value = calc.convertInFive(value, 3);
    total += value;
    outOf += 5;
    value = pilotDetail.getDurLNinty();
    total += value;
    outOf += 5;
    value = pilotDetail.getNoPilot();
    total += value;
    outOf += 5;
    value = pilotDetail.getTotDur();
    value = calc.convertInFive(value, 3);
    total += value;
    outOf += 5;
    percentageProficiency = calc.getFinalPercentage(total, outOf, 25);
    percentageInHundered = calc.getFinalPercentage(percentageProficiency, 25, 100);
    result.setProficiency(percentageInHundered);
  }

  public void sourceEnvironment() {
    environment();
    percentageSource = calc.getFinalPercentage(total, outOf, 9);
    percentageInHundered = calc.getFinalPercentage(percentageSource, 9, 100);
    result.setSourceEnvironment(percentageInHundered);
  }

  public void destinationEnvironmen() {
    environment();
    percentageDestination = calc.getFinalPercentage(total, outOf, 21);
    percentageInHundered = calc.getFinalPercentage(percentageDestination, 21, 100);
    result.setDestinationEnvironment(percentageInHundered);
  }

  public void environment() {
    value = sourceEnv.getMountain();
    value = calc.convertInFive(value, 4);
    total = value;
    outOf = 5;

    value = sourceEnv.getNightOperation();
    value = calc.convertInFive(value, 3);
    total += value;
    outOf += 5;

    value = sourceEnv.getWinterOper();
    value = calc.convertInFive(value, 4);
    total += value;
    outOf += 5;

    value = sourceEnv.getThunderStorm();
    total += value;
    outOf += 5;

    value = sourceEnv.getRain();
    total += value;
    outOf += 5;

    value = sourceEnv.getFrozen();
    value = calc.convertInFive(value, 3);
    total += value;
    outOf += 5;

    value = sourceEnv.getNoWeather();
    total += value;
    outOf += 5;

    value = sourceEnv.getWindSpeed();
    value = calc.convertInFive(value, 4);
    total += value;
    outOf += 5;

    value = sourceEnv.getVisibility();
    total += value;
    outOf += 5;
  }

  public void aircraft() {
    value = aircraftCheckList.getDeIce();
    value = calc.convertInFive(value, 3);
    total = value;
    outOf = 5;

    value = aircraftCheckList.getWetherRadar();
    value = calc.convertInFive(value, 3);
    total += value;
    outOf += 5;

    value = aircraftCheckList.getStormScope();
    total += value;
    outOf += 5;

    value = aircraftCheckList.getAutoPilot();
    total += value;
    outOf += 5;

    percentageAircraft = calc.getFinalPercentage(total, outOf, 20);
    percentageInHundered = calc.getFinalPercentage(percentageDestination, 20, 100);
    result.setAircraft(percentageInHundered);
  }

  public void humanPerformance() {
    value = crewTotal.getDutyTime();
    value = calc.convertInFive(value, 3);
    total = value;
    outOf = 5;

    value = crewTotal.getRestTime();
    total += value;
    outOf += 5;
    percentageHuman = calc.getFinalPercentage(total, outOf, 25);
    percentageInHundered = calc.getFinalPercentage(percentageHuman, 25, 100);
    result.setHuman(percentageInHundered);
  }

  public void finalResult() {
    double finalPercentage =
        percentageAircraft
            + percentageDestination
            + percentageHuman
            + percentageProficiency
            + percentageSource;
    result.setFinalPercent(DoubleRounder.round(finalPercentage, 2));
    if (finalPercentage <= 33) result.setFinalConclusion("CLEAR");
    else if (finalPercentage > 33 && finalPercentage <= 66)
      result.setFinalConclusion("NEED TO CHECK");
    else result.setFinalConclusion("RISKY");
  }

  public CrewTotalService getCrewTotalObject() {
    return crew;
  }

  public CaptainAnalysisService getCaptainAnalysisObject() {
    return captain;
  }

  public PilotAnalysisService getPilotAnalysisObject() {
    return pilot;
  }

  public AircraftAnalysisService getAircraftAnalysisObject() {
    return aircraft;
  }

  public SourceEnvironmentService getSourceEnvObject() {
    return envSource;
  }

  public DestinationEnvironmentService getDestinationEnvObject() {
    return envDestination;
  }
}
