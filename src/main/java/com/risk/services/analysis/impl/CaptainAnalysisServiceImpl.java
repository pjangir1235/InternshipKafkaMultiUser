package com.risk.services.analysis.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.FlightCaptainSummaryDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.CaptainDetail;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Scope("prototype")
@Service
public class CaptainAnalysisServiceImpl {


  @Autowired Calculation calc;

  private StoreRecord record;
  private FlightScheduleDTO data;

  private LocalDate scheduleDate;
  int durationLastNinty;
  int durationTotal;
  int landing;
  double result;

public CaptainAnalysisServiceImpl() {
	super();
}
  public void setCaptainAnalysisServiceImpl(StoreRecord record) {
    this.record=record;
    data = record.getSchedule();
    scheduleDate = LocalDateString.stringToLocalDate(data.getDateOfDeparture());
    durationLastNinty = 0;
    landing = 0;
    durationTotal = 0;
  }

  public void getDataAnalysis(FlightCaptainSummaryDTO captain) {

    LocalDate curDate = LocalDateString.stringToLocalDate(captain.getDateOfDeparture());
    long diff = LocalDateString.differnceInDate(curDate, scheduleDate);
    if (diff < 90) durationLastNinty += captain.getDuration();
    durationTotal += captain.getDuration();
    if (diff <= 15) landing++;
    record.setFlightCaptainSummaryCount(record.getFlightCaptainSummaryCount() - 1);



  }
  public void finalCalc() {
	  CaptainDetail finalResult = new CaptainDetail();
      double hour;
      hour = calc.getHour(durationLastNinty);
      result = calc.getPercentage(hour, 100, 5);
      finalResult.setDurLNinty(result);
      finalResult.setMessDurLNinty(
          CommonConstant.OUTOF5+"Last 90 Days done work aprox " + (int) hour + " in particular type");
      hour = calc.getHour(durationTotal);
      result = calc.getPercentage(hour, 200, 5);
      finalResult.setTotHour(result);
      finalResult.setMessTotHour(
          CommonConstant.OUTOF5+" Total Hours done in particular type aprox " + (int) hour);
      result = calc.getPercentage(landing, 7, 3);
      finalResult.setLanding(result);
      finalResult.setMessLanding(CommonConstant.OUTOF3+" Total Landing in last 15 days is " + landing);
      hour = calc.getHour(data.getDuration());
      if (hour > 8) {
        result = calc.getPercentageGreater(hour - 8, 4, 3);
        finalResult.setDurTot(result);
      } else finalResult.setDurTot(0.0);
      finalResult.setMessDurTot(
          CommonConstant.OUTOF3+" Total duration is aprox "
              + (data.getDuration() / 60)
              + ":"
              + (data.getDuration() % 60));
      record.setCaptainDetail(finalResult);

  }
}
