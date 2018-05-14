package com.risk.models;

import java.util.ArrayList;
import java.util.List;

import com.risk.consumer.model.AircraftCodeDTO;
import com.risk.consumer.model.AircraftDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.consumer.model.RestDetailDTO;
import com.risk.result.model.FinalAnalysisData;

public class StoreRecord extends FinalAnalysisData {

	private int key = 0;
	private List<FlightScheduleDTO> flightSchedule;
	private int flightMinOffset = -1;
	private int flightMaxOffset = 0;
	private int restCrewMinOffset = -1;
	private int restCrewMaxOffset = 0;
	private int flightCaptainMinOffset = -1;

	private int checklistOffset = 0;

	private int flightCaptainMaxOffset = 0;
	private int flightPilotMinOffset = -1;
	private int flightPilotMaxOffset = 0;
	private List<AircraftDTO> aircraft;
	private int aircraftOffset = 0;

	private int restDetailCount = 0;
	private int restDetailTotal = 0;
	private int envMinOffset = -1;

	private int envMaxOffset = 0;

	public int getEnvMinOffset() {
		return envMinOffset;
	}

	public void setEnvMinOffset(int envMinOffset) {
		this.envMinOffset = envMinOffset;
	}

	public int getEnvMaxOffset() {
		return envMaxOffset;
	}

	public void setEnvMaxOffset(int envMaxOffset) {
		this.envMaxOffset = envMaxOffset;
	}

	public int getChecklistOffset() {
		return checklistOffset;
	}

	public void setChecklistOffset(int checklistOffset) {
		this.checklistOffset = checklistOffset;
	}

	public int getFlightCaptainMinOffset() {
		return flightCaptainMinOffset;
	}

	public void setFlightCaptainMinOffset(int flightCaptainMinOffset) {
		this.flightCaptainMinOffset = flightCaptainMinOffset;
	}

	public int getFlightCaptainMaxOffset() {
		return flightCaptainMaxOffset;
	}

	public void setFlightCaptainMaxOffset(int flightCaptainMaxOffset) {
		this.flightCaptainMaxOffset = flightCaptainMaxOffset;
	}

	public int getFlightPilotMinOffset() {
		return flightPilotMinOffset;
	}

	public void setFlightPilotMinOffset(int flightPilotMinOffset) {
		this.flightPilotMinOffset = flightPilotMinOffset;
	}

	public int getFlightPilotMaxOffset() {
		return flightPilotMaxOffset;
	}

	public void setFlightPilotMaxOffset(int flightPilotMaxOffset) {
		this.flightPilotMaxOffset = flightPilotMaxOffset;
	}

	public int getAircraftOffset() {
		return aircraftOffset;
	}

	public void setAircraftOffset(int aircraftOffset) {
		this.aircraftOffset = aircraftOffset;
	}

	public int getFlightMinOffset() {
		return flightMinOffset;
	}

	public void setFlightMinOffset(int flightMinOffset) {
		this.flightMinOffset = flightMinOffset;
	}

	public int getFlightMaxOffset() {
		return flightMaxOffset;
	}

	public void setFlightMaxOffset(int flightMaxOffset) {
		this.flightMaxOffset = flightMaxOffset;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getRestCrewMaxOffset() {
		return restCrewMaxOffset;
	}

	public void setRestCrewMaxOffset(int restCrewMaxOffset) {
		this.restCrewMaxOffset = restCrewMaxOffset;

	}

	public int getRestCrewMinOffset() {
		return restCrewMinOffset;
	}

	public void setRestCrewMinOffset(int restCrewMinOffset) {
		this.restCrewMinOffset = restCrewMinOffset;
	}

	//
	////////
	////
	//
	//
	//
	//
	//
	//
	////
	//
	//
	//
	//

	private RestDetailDTO restDetail;

	private List<AircraftCodeDTO> craftCode;
	private List<Environment> env;

	private FinalAnalysisData finalData;

	private FlightScheduleDTO schedule;

	private int environmentCount = 0;
	private int flightPilotSummaryCount = 0;
	private int flightCaptainSummaryCount = 0;
	private int aircraftChecklistCount = 0;
	private int aircraftCodeCount = 0;

	public FinalAnalysisData getFinalData() {
		return finalData;
	}

	public void setFinalData(FinalAnalysisData finalData) {
		this.finalData = finalData;
	}

	public int getRestDetailTotal() {
		return restDetailTotal;
	}



	public void setRestDetailTotal(int restDetailTotal) {
		this.restDetailTotal = restDetailTotal;
	}

	public void destroy() {
		init();
	}


	public FlightScheduleDTO getSchedule() {
		return schedule;
	}

	public void setSchedule(FlightScheduleDTO schedule) {
		this.schedule = schedule;
	}

	public int getFlightCaptainSummaryCount() {
		return flightCaptainSummaryCount;
	}

	public void setFlightCaptainSummaryCount(int flightCaptainSummaryCount) {
		this.flightCaptainSummaryCount = flightCaptainSummaryCount;
	}

	public int getFlightPilotSummaryCount() {
		return flightPilotSummaryCount;
	}

	public void setFlightPilotSummaryCount(int flightPilotSummaryCount) {
		this.flightPilotSummaryCount = flightPilotSummaryCount;
	}

	public List<AircraftDTO> getAircraft() {
		return aircraft;
	}

	public int getAircraftChecklistCount() {
		return aircraftChecklistCount;
	}

	public int getAircraftCodeCount() {
		return aircraftCodeCount;
	}

	public List<AircraftCodeDTO> getCraftCode() {
		return craftCode;
	}

	public int getEnvironmentCount() {
		return environmentCount;
	}

	public List<FlightScheduleDTO> getFlightSchedule() {
		return flightSchedule;
	}

	public RestDetailDTO getRestDetail() {
		return restDetail;
	}

	public int getRestDetailCount() {
		return restDetailCount;
	}

	public List<Environment> getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env.add(env);
	}

	private void init() {
		aircraft = new ArrayList<>();
		flightSchedule = new ArrayList<>();
		restDetail = new RestDetailDTO();
		craftCode = new ArrayList<>();
		schedule = new FlightScheduleDTO();
		finalData = new FinalAnalysisData();
		env = new ArrayList<>();
	}

	public void setAircraft(AircraftDTO aircraft) {
		this.aircraft.add(aircraft);
	}

	public void setAircraftChecklistCount(int aircraftChecklistCount) {
		this.aircraftChecklistCount = aircraftChecklistCount;
	}

	public void setAircraftCodeCount(int aircraftCodeCount) {
		this.aircraftCodeCount = aircraftCodeCount;
	}

	public void setCraftCode(AircraftCodeDTO craftCode) {
		this.craftCode.add(craftCode);
	}

	public void setEnvironmentCount(int environmentCount) {
		this.environmentCount = environmentCount;
	}

	public void setFlightSchedule(FlightScheduleDTO flightSchedule) {
		this.flightSchedule.add(flightSchedule);
	}

	public void setRestDetail(RestDetailDTO restDetail) {
		this.restDetail = restDetail;
	}

	public void setRestDetailCount(int restDetailCount) {
		this.restDetailCount = restDetailCount;
	}

}