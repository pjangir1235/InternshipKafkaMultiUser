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
import com.risk.producer.model.User;
import com.risk.result.model.FinalAnalysisData;
import com.risk.services.interfaces.MainService;

@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	MainService producer;

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
			if (!recordAirport.isUpdated()) {
				producer.getAiprotValues();
				recordAirport.setUpdated(true);
			}
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
				logger.error(CommonConstant.ERROR + e);
			return list;
		}
	}
	@PostMapping("/user")
	public boolean getUser(@RequestBody User user) {
		boolean isValid = false;
		try {
			isValid = producer.getUserValues(user);
			return isValid;
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
			return isValid;
		}
	}
	@PostMapping("analysis")
	public FinalAnalysisData doAnalysis(@RequestBody FlightScheduleDTO schedule) {
		FinalAnalysisData data = new FinalAnalysisData();
		try {
			data=producer.getAnalysisData(schedule);
			return data;
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
			return data;
		}
	}
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

}
