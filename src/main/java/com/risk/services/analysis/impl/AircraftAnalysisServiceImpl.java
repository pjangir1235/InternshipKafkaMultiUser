package com.risk.services.analysis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.AircraftChecklistDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.AircraftCheckListDetail;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Service
public class AircraftAnalysisServiceImpl {

  @Autowired LocalDateString convert;

  @Autowired StoreRecord record;

  @Autowired Calculation calc;

  FlightScheduleDTO data;
  AircraftCheckListDetail finalResult;

  public AircraftAnalysisServiceImpl() {
    super();
  }

  public void setValues() {
    data = record.getSchedule();
    finalResult = new AircraftCheckListDetail();
    setInit();
  }

  public void setInit() {
    finalResult.setDeIce(1.5);
    finalResult.setWetherRadar(1.5);
    finalResult.setStormScope(1.5);
    finalResult.setAutoPilot(2.5);
    finalResult.setMsgDeIce("Out of 3\n" + CommonConstant.RECORDNOTFOUND);
    finalResult.setMsgWetherRadar("Out of 3\n" + CommonConstant.RECORDNOTFOUND);
    finalResult.setMsgStormScope("Out of 3\n" + CommonConstant.RECORDNOTFOUND);
    finalResult.setMsgAutoPilot("Out of 3\n" + CommonConstant.RECORDNOTFOUND);
    record.setAircraftCheckList(finalResult);
  }

  public void getDataAnalysis(AircraftChecklistDTO checklist) {
    if (checklist.isDeIce()) {
      finalResult.setDeIce(0);
      finalResult.setMsgDeIce("Out of 3\n" + "De Ice done ");
    } else {
      finalResult.setDeIce(3);
      finalResult.setMsgDeIce("Out of 3\n" + " Ice is there");
    }
    if (checklist.isWeatherRadar()) {
      finalResult.setWetherRadar(0);
      finalResult.setMsgWetherRadar("Out of 3\n" + "Weather Radar is Available");
    } else {
      finalResult.setWetherRadar(3);
      finalResult.setMsgWetherRadar("Out of 3\n" + "Weather Radar is not Available");
    }
    if (checklist.isStormScope()) {
      finalResult.setStormScope(0);
      finalResult.setMsgStormScope("Out of 3\n" + "Storm Scope is Available");
    } else {
      finalResult.setStormScope(3);
      finalResult.setMsgStormScope("Out of 3\n" + "Storm Scope is not Available");
    }
    if (checklist.isAutoPilot()) {
      finalResult.setAutoPilot(0);
      finalResult.setMsgAutoPilot("Out of 3\n" + "Auto Pilot is in Flight");
    } else {
      finalResult.setAutoPilot(5);
      finalResult.setMsgAutoPilot("Out of 3\n" + "Auto Pilot is not in Flight");
    }
    record.setAircraftCheckList(finalResult);
  }
}
