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
import com.risk.consumer.model.AircraftTypeDTO;
import com.risk.consumer.model.AirportDTO;
import com.risk.consumer.model.CrewDTO;
import com.risk.consumer.model.FlightScheduleCrewDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.consumer.model.FlightSchedulePilotDTO;
import com.risk.consumer.model.PilotDTO;
import com.risk.consumer.model.PilotDesignationDTO;
import com.risk.consumer.model.UserDTO;
import com.risk.models.AirportRecord;
import com.risk.models.Environment;
import com.risk.models.ScheduleRequestDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.FinalAnalysisData;
import com.risk.services.interfaces.MainService;

@RestController
public class Controller {

  private static final Logger logger = LoggerFactory.getLogger(Controller.class);

  @Autowired MainService producer;

  @Autowired StoreRecord record;
  @Autowired AirportRecord recordAirport;

  @PostMapping("analysis")
  public FinalAnalysisData doAnalysis(@RequestBody FlightScheduleDTO schedule) {
    record.destroy();
    record.setSchedule(schedule);
    FinalAnalysisData data = new FinalAnalysisData();
    try {
      producer.getAnalysisData();
      return record.getFinalData();
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return data;
    }
  }

  @GetMapping("/aircraft/{code}")
  public List<AircraftDTO> getaircraft(@PathVariable("code") String aircraftCode) {
    List<AircraftDTO> list = null;
    try {

      producer.getAircraftValues(aircraftCode);
      producer.checkFetchData();
      list = record.getAircraft();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  //  @GetMapping("/aircraftChecklist")
  //  public List<AircraftChecklistDTO> getAircraftChecklist() {
  //    List<AircraftChecklistDTO> list = null;
  //    try {
  //
  //      producer.getAircraftChecklistValues();
  //      //producer.checkFetchData();
  //      list = record.getAircraftChecklist();
  //      return list;
  //    } catch (Exception e) {
  //      logger.error(CommonConstant.ERROR + e);
  //      return list;
  //    }
  //  }

  @GetMapping("/aircraftType")
  public List<AircraftTypeDTO> getAircraftType() {
    List<AircraftTypeDTO> list = null;
    try {

      producer.getAircraftTypeValues();
      //producer.checkFetchData();
      list = record.getAircraftType();
      return list;
    } catch (Exception e) {
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
        producer.checkFetchData();
        recordAirport.setUpdated(true);
      }
      list = recordAirport.getAirport();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  @GetMapping("/crew")
  public List<CrewDTO> getCrew() {
    List<CrewDTO> list = null;
    try {

      producer.getCrewValues();
      //producer.checkFetchData();

      list = record.getCrew();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  @GetMapping("/environment")
  public List<Environment> getEnvironmentDetail() {
    List<Environment> data = null;
    try {

      data = record.getEnv();
      return data;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return data;
    }
  }

  @PostMapping("/flightSchedule")
  public List<FlightScheduleDTO> getFlightSchedule(@RequestBody ScheduleRequestDTO req) {
    record.destroy();
    List<FlightScheduleDTO> list = null;
    try {

      list = producer.getFlightScheduleValues(req);
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  @GetMapping("/flightScheduleCrew")
  public List<FlightScheduleCrewDTO> getFlightScheduleCrew() {
    List<FlightScheduleCrewDTO> list = null;
    try {

      producer.getFlightScheduleCrewValues();
      //producer.checkFetchData();
      list = record.getFlightScheduleCrew();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  @GetMapping("/flightSchedulePilot")
  public List<FlightSchedulePilotDTO> getFlightSchedulePilot() {
    List<FlightSchedulePilotDTO> list = null;
    try {

      producer.getFlightSchedulePilotValues();
      //producer.checkFetchData();
      list = record.getFlightSchedulePilot();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  @GetMapping("/pilot")
  public List<PilotDTO> getPilot() {
    List<PilotDTO> list = null;
    try {

      producer.getPilotValues();
      //producer.checkFetchData();
      list = record.getPilot();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  @GetMapping("/pilotDesignation")
  public List<PilotDesignationDTO> getPilotDesignationData() {
    List<PilotDesignationDTO> list = null;
    try {

      producer.getPilotDesignationValues();
      //producer.checkFetchData();
      list = record.getPilotDesignation();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }

  //  @GetMapping("/restDetail")
  //  public List<RestDetailDTO> getRestDetail() {
  //    List<RestDetailDTO> list = null;
  //    try {
  //
  //      producer.getRestDetailValues();
  //      //producer.checkFetchData();
  //      list = record.getRestDetail();
  //      return list;
  //    } catch (Exception e) {
  //      logger.error(CommonConstant.ERROR + e);
  //      return list;
  //    } finally {
  //
  //    }
  //  }

  @PostMapping("/user")
  public List<UserDTO> getUser(@RequestBody UserDTO user) {
    List<UserDTO> list = null;
    try {
      producer.getUserValues(user.getUserName(), user.getPassword());
      producer.checkFetchData();
      list = record.getUser();
      return list;
    } catch (Exception e) {
      logger.error(CommonConstant.ERROR + e);
      return list;
    }
  }
}
