package com.risk.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.AircraftDTO;
import com.risk.consumer.model.AirportDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.AirportRecord;
import com.risk.models.Environment;
import com.risk.models.ScheduleRequestDTO;
import com.risk.models.StoreRecord;
import com.risk.producer.model.User;
import com.risk.result.model.FinalAnalysisData;
import com.risk.services.interfaces.MainService;

@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	MainService producer;

	@Autowired
	StoreRecord record;
	@Autowired
	AirportRecord recordAirport;

	@GetMapping("/aircraft/{code}")
	public List<AircraftDTO> getaircraft(@PathVariable("code") String aircraftCode) {
		List<AircraftDTO> list = null;
		try {
			list = producer.getAircraftValues(aircraftCode);
			return list;
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
			return list;
		}
	}

	@GetMapping("/airport")
	public List<AirportDTO> getAirport() {
		List<AirportDTO> list = null;
		try {
//			if (!recordAirport.isUpdated()) {
//				producer.getAiprotValues();
//				recordAirport.setUpdated(true);
//			}
			list = recordAirport.getAirport();
			return list;
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
			return list;
		}
	}

	@PostMapping("/flightSchedule")
	public List<FlightScheduleDTO> getFlightSchedule(@RequestBody ScheduleRequestDTO req) {
		List<FlightScheduleDTO> list = null;
		try {
			list = producer.getFlightScheduleValues(req);
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(CommonConstant.ERROR + e);
			return list;
		}
	}
	@PostMapping("/user")
	public boolean getUser(@RequestBody User user) {
		boolean IsValid = false;
		try {
			IsValid = producer.getUserValues(user);
			return IsValid;
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
			return IsValid;
		}
	}



	//
	//
	// Working on
	@PostMapping("analysis")
	public FinalAnalysisData doAnalysis(@RequestBody FlightScheduleDTO schedule) {
		FinalAnalysisData data = new FinalAnalysisData();
		System.out.println("pos1");
		try {
			System.out.println("pos2");
			data=producer.getAnalysisData(schedule);
			return data;
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
			return data;
		}
	}

	//
	//
	//
	//
	//
	/// not done

	@GetMapping("/environment/{code}")
	public Environment getEnvironment(@PathVariable("code") String stationCode) {
		Environment data = null;
		try {

			data = producer.getEnvironmentValues(stationCode);
			return data;
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
			return data;
		}
	}

	// @GetMapping("/aircraftChecklist")
	// public List<AircraftChecklistDTO> getAircraftChecklist() {
	// List<AircraftChecklistDTO> list = null;
	// try {
	//
	// producer.getAircraftChecklistValues();
	// //producer.checkFetchData();
	// list = record.getAircraftChecklist();
	// return list;
	// } catch (Exception e) {
	// logger.error(CommonConstant.ERROR + e);
	// return list;
	// }
	// }




//	@GetMapping("/flightScheduleCrew")
//	public List<FlightScheduleCrewDTO> getFlightScheduleCrew() {
//		List<FlightScheduleCrewDTO> list = null;
//		try {
//
//			producer.getFlightScheduleCrewValues();
//			// producer.checkFetchData();
//			list = record.getFlightScheduleCrew();
//			return list;
//		}
//		catch (Exception e) {
//			logger.error(CommonConstant.ERROR + e);
//			return list;
//		}
//	}
//
//	@GetMapping("/flightSchedulePilot")
//	public List<FlightSchedulePilotDTO> getFlightSchedulePilot() {
//		List<FlightSchedulePilotDTO> list = null;
//		try {
//
//			producer.getFlightSchedulePilotValues();
//			// producer.checkFetchData();
//			list = record.getFlightSchedulePilot();
//			return list;
//		}
//		catch (Exception e) {
//			logger.error(CommonConstant.ERROR + e);
//			return list;
//		}
//	}
//
//	@GetMapping("/pilot")
//	public List<PilotDTO> getPilot() {
//		List<PilotDTO> list = null;
//		try {
//
//			producer.getPilotValues();
//			// producer.checkFetchData();
//			list = record.getPilot();
//			return list;
//		}
//		catch (Exception e) {
//			logger.error(CommonConstant.ERROR + e);
//			return list;
//		}
//	}
//
//	@GetMapping("/pilotDesignation")
//	public List<PilotDesignationDTO> getPilotDesignationData() {
//		List<PilotDesignationDTO> list = null;
//		try {
//
//			producer.getPilotDesignationValues();
//			// producer.checkFetchData();
//			list = record.getPilotDesignation();
//			return list;
//		}
//		catch (Exception e) {
//			logger.error(CommonConstant.ERROR + e);
//			return list;
//		}
//	}


}
