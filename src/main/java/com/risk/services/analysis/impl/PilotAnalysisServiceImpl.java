package com.risk.services.analysis.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.consumer.model.FlightPilotSummaryDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.PilotDetail;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Service
public class PilotAnalysisServiceImpl {

  @Autowired LocalDateString convert;

  @Autowired StoreRecord record;

  @Autowired Calculation calc;

  FlightScheduleDTO data;
  LocalDate scheduleDate;
  PilotDetail resultFinal;
  int durationLastNinty;
  int durationTotal;
  double result;

  public PilotAnalysisServiceImpl() {
    super();
  }

  public void setValues() {

    data = record.getSchedule();
    if (data.getPilots() == null) {
      resultFinal = new PilotDetail();
      setNull(resultFinal);
    } else {
      scheduleDate = convert.stringToLocalDate(data.getDateOfDeparture());
      durationLastNinty = 0;
      durationTotal = 0;
    }
  }

  public void getDataAnalysis(FlightPilotSummaryDTO pilot) {

    LocalDate curDate = convert.stringToLocalDate(pilot.getDateOfDeparture());
    long diff = convert.differnceInDate(curDate, scheduleDate);
    if (diff < 90) durationLastNinty += pilot.getDuration();
    durationTotal += pilot.getDuration();

    record.setFlightPilotSummaryCount(record.getFlightPilotSummaryCount() - 1);
    if (record.getFlightPilotSummaryCount() == 0)
    {
      resultFinal = new PilotDetail();
      double hour;
      hour = calc.getHour(durationLastNinty);
      result = calc.getPercentage(hour, 100, 5);
      resultFinal.setDurLNinty(result);
      resultFinal.setMessDurLNinty(
          "Out of 5 \nLast 90 Days done work aprox " + (int) hour + " in particular type");
      hour = calc.getHour(durationTotal);
      result = calc.getPercentage(hour, 200, 5);
      resultFinal.setTotDur(result);
      resultFinal.setMessTotDur(
          "Out of 3 \n Total Hours done in particular type aprox " + (int) hour);
      resultFinal.setNoPilot(0);
      resultFinal.setMessNoPilot(
          "Out of 5 \n FirstPilot is Available");
      record.setPilotDetail(resultFinal);
      System.out.println(resultFinal);
    }

  }

  void setNull(PilotDetail detail) {
    detail.setDurLNinty(0);
    detail.setMessDurLNinty("No First Pilot");
    detail.setNoPilot(0);
    detail.setMessNoPilot("No First Pilot");
    detail.setTotDur(0);
    detail.setMessTotDur("No First Pilot");
    record.setPilotDetail(detail);
  }
}
