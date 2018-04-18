package com.risk.services.analysis.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.consumer.model.FlightCaptainSummaryDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.CaptainDetail;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Service
public class CaptainAnalysisServiceImpl {

  @Autowired LocalDateString convert;

  @Autowired StoreRecord record;

  @Autowired Calculation calc;


  FlightScheduleDTO data;
  CaptainDetail finalResult;
  LocalDate scheduleDate;
  int durationLastNinty;
  int durationTotal;
  int landing;
  double result;


  public CaptainAnalysisServiceImpl() {
    super();
  }

  public void setValues() {
    data = record.getSchedule();
    scheduleDate = convert.stringToLocalDate(data.getDateOfDeparture());
    durationLastNinty = 0;
    landing = 0;
    durationTotal = 0;
  }

  public void getDataAnalysis(FlightCaptainSummaryDTO captain) {

    LocalDate curDate = convert.stringToLocalDate(captain.getDateOfDeparture());
    long diff = convert.differnceInDate(curDate, scheduleDate);
    if (diff < 90) durationLastNinty += captain.getDuration();
    durationTotal += captain.getDuration();
    if (diff <= 15) landing++;
    record.setFlightCaptainSummaryCount(record.getFlightCaptainSummaryCount() - 1);

    if (record.getFlightCaptainSummaryCount() == 0)
    {
      finalResult = new CaptainDetail();
      double hour;
      hour = calc.getHour(durationLastNinty);
      result = calc.getPercentage(hour, 100, 5);
      finalResult.setDurLNinty(result);
      finalResult.setMessDurLNinty(
          "Out of 5 \nLast 90 Days done work aprox " + (int) hour + " in particular type");
      hour = calc.getHour(durationTotal);
      result = calc.getPercentage(hour, 200, 5);
      finalResult.setTotHour(result);
      finalResult.setMessTotHour(
          "Out of 5 \n Total Hours done in particular type aprox " + (int) hour);
      result = calc.getPercentage(landing, 7, 3);
      finalResult.setLanding(result);
      finalResult.setMessLanding("Out of 3 \n Total Landing in last 15 days is " + landing);
      hour = calc.getHour(data.getDuration());
      if (hour > 8) {
        result = calc.getPercentageGreater(hour - 8, 4, 3);
        finalResult.setDurTot(result);
      } else finalResult.setDurTot(0.0);
      finalResult.setMessDurTot(
          "Out of 3 \n Total duration is aprox "
              + (data.getDuration() / 60)
              + ":"
              + (data.getDuration() % 60));
      System.out.println(finalResult.toString());
      record.setCaptainDetail(finalResult);
    }
  }
}
