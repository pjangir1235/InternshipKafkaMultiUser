package com.risk.services.impl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.risk.services.analysis.impl.AircraftAnalysisServiceImpl;
import com.risk.services.analysis.impl.CaptainAnalysisServiceImpl;
import com.risk.services.analysis.impl.CrewTotalServiceImpl;
import com.risk.services.analysis.impl.DestinationEnvironmentServiceImpl;
import com.risk.services.analysis.impl.PilotAnalysisServiceImpl;
import com.risk.services.analysis.impl.SourceEnvironmentServiceImpl;
import com.risk.util.Calculation;

@Service
public class AnalysisServiceImpl {

	@Autowired
	AirportRecord recordAirport;
	private AircraftAnalysisServiceImpl aircraft;
	private CaptainAnalysisServiceImpl captain;
	private CrewTotalServiceImpl crew;
	private DestinationEnvironmentServiceImpl envDestination;
	private SourceEnvironmentServiceImpl envSource;
	private PilotAnalysisServiceImpl pilot;
	private StoreRecord record;

	private AircraftCheckListDetail aircraftCheckList;
	private CaptainDetail captainDetail;
	private HumanPerformance crewTotal;
	private DestinationEnvironment destEnv;
	private PilotDetail pilotDetail;
	private SourceEnvironment sourceEnv;
	private FinalAnalysisData finalData;
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

	@Autowired
	Calculation calc;

	public AnalysisServiceImpl() {
		super();
	}

	public AnalysisServiceImpl(StoreRecord rec) {
		System.out.println("Analysis service constructor");
		this.record = rec;
		System.out.println(recordAirport.toString());
		aircraft = new AircraftAnalysisServiceImpl(record);
		captain = new CaptainAnalysisServiceImpl(record);
		System.out.println(recordAirport.toString());
		crew = new CrewTotalServiceImpl(record);
		System.out.println(recordAirport.toString());
		envDestination = new DestinationEnvironmentServiceImpl(record);
		System.out.println("rest pos4");
		envSource = new SourceEnvironmentServiceImpl(record);
		System.out.println("rest pos5");
		pilot = new PilotAnalysisServiceImpl(record);
		System.out.println("rest pos6");
	}
	// public void initializeAnalysis(StoreRecord rec) {

	//
	// aircraft.setValues();
	// captain.setValues();
	// crew.setValue();
	// envDestination.setValue();
	// envSource.setValue();
	// pilot.setValues();

	// }

	public void startCalculation() {
		result = new Result();
		crew.getAnalysisData();
		captain.finalCalc();
		pilot.finalCalc();

		// Some Code Missing

		aircraftCheckList = record.getAircraftCheckList();
		captainDetail = record.getCaptainDetail();
		crewTotal = record.getCrewTotal();
		destEnv = record.getEnvDestination();
		pilotDetail = record.getPilotDetail();
		sourceEnv = record.getEnvSource();

		// Proficiency
		proficiency();
		SourceEnvironment();
		DestinationEnvironment();
		Aircraft();
		HumanPerformance();
		FinalResult();
		finalData = new FinalAnalysisData();
		finalData.setAircraftCheckList(aircraftCheckList);
		finalData.setCaptainDetail(captainDetail);
		finalData.setCrewTotal(crewTotal);
		finalData.setDestEnv(destEnv);
		finalData.setPilotDetail(pilotDetail);
		finalData.setSourceEnv(sourceEnv);
		finalData.setResult(result);
		record.setFinalData(finalData);

	}

	@Override
	public String toString() {
		return "AnalysisServiceImpl [aircraft=" + aircraft + ", captain=" + captain + ", crew=" + crew
		                + ", envDestination=" + envDestination + ", envSource=" + envSource + ", pilot=" + pilot
		                + ", record=" + record + "]";
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

	public void SourceEnvironment() {
		environment();
		percentageSource = calc.getFinalPercentage(total, outOf, 9);
		percentageInHundered = calc.getFinalPercentage(percentageSource, 9, 100);
		result.setSourceEnvironment(percentageInHundered);
	}

	public void DestinationEnvironment() {
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

	public void Aircraft() {
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

	public void HumanPerformance() {
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

	public void FinalResult() {
		double finalPercentage = percentageAircraft + percentageDestination + percentageHuman + percentageProficiency
		                + percentageSource;
		result.setFinalPercent(DoubleRounder.round(finalPercentage, 2));
		if (finalPercentage <= 33)
			result.setConclusion("CLEAR");
		else if (finalPercentage > 33 && finalPercentage <= 66)
			result.setConclusion("NEED TO CHECK");
		else
			result.setConclusion("RISKY");
	}

	public CrewTotalServiceImpl getCrewTotalObject() {
		return crew;
	}

	public CaptainAnalysisServiceImpl getCaptainAnalysisObject() {
		return captain;
	}

	public PilotAnalysisServiceImpl getPilotAnalysisObject() {
		return pilot;
	}

	public AircraftAnalysisServiceImpl getAircraftAnalysisObject() {
		return aircraft;
	}

	public SourceEnvironmentServiceImpl getSourceEnvObject() {
		return envSource;
	}

	public DestinationEnvironmentServiceImpl getDestinationEnvObject() {
		return envDestination;
	}
}
