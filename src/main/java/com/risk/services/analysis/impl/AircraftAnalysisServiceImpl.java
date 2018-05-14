package com.risk.services.analysis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.AircraftChecklistDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.AircraftCheckListDetail;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Scope("prototype")
@Service
public class AircraftAnalysisServiceImpl {

  public AircraftAnalysisServiceImpl() {
		super();
	}

@Autowired LocalDateString convert;



  @Autowired Calculation calc;

  private StoreRecord record;
  FlightScheduleDTO data;
  AircraftCheckListDetail finalResult;

  public void setAircraftAnalysisServiceImpl(StoreRecord record) {
    this.record=record;
    data = record.getSchedule();
    finalResult = new AircraftCheckListDetail();
    setInit();
  }

  public void setInit() {
    finalResult.setDeIce(1.5);
    finalResult.setWetherRadar(1.5);
    finalResult.setStormScope(1.5);
    finalResult.setAutoPilot(2.5);
    finalResult.setMsgDeIce(CommonConstant.OUTOF3 + CommonConstant.RECORDNOTFOUND);
    finalResult.setMsgWetherRadar(CommonConstant.OUTOF3 + CommonConstant.RECORDNOTFOUND);
    finalResult.setMsgStormScope(CommonConstant.OUTOF5 + CommonConstant.RECORDNOTFOUND);
    finalResult.setMsgAutoPilot(CommonConstant.OUTOF5 + CommonConstant.RECORDNOTFOUND);
    record.setAircraftCheckList(finalResult);
  }

  public void getDataAnalysis(AircraftChecklistDTO checklist) {
	   if (checklist.isDeIce()) {
      finalResult.setDeIce(0);
      finalResult.setMsgDeIce(CommonConstant.OUTOF3 + "De Ice done ");
    } else {
      finalResult.setDeIce(3);
      finalResult.setMsgDeIce(CommonConstant.OUTOF3 + " Ice is there");
    }
    if (checklist.isWeatherRadar()) {
      finalResult.setWetherRadar(0);
      finalResult.setMsgWetherRadar(CommonConstant.OUTOF3 + "Weather Radar is Available");
    } else {
      finalResult.setWetherRadar(3);
      finalResult.setMsgWetherRadar(CommonConstant.OUTOF3 + "Weather Radar is not Available");
    }
    if (checklist.isStormScope()) {
      finalResult.setStormScope(0);
      finalResult.setMsgStormScope(CommonConstant.OUTOF5 + "Storm Scope is Available");
    } else {
      finalResult.setStormScope(5);
      finalResult.setMsgStormScope(CommonConstant.OUTOF5 + "Storm Scope is not Available");
    }
    if (checklist.isAutoPilot()) {
      finalResult.setAutoPilot(0);
      finalResult.setMsgAutoPilot(CommonConstant.OUTOF5 + "Auto Pilot is in Flight");
    } else {
      finalResult.setAutoPilot(5);
      finalResult.setMsgAutoPilot(CommonConstant.OUTOF5 + "Auto Pilot is not in Flight");
    }
     record.setAircraftCheckList(finalResult);
    }
}
