package com.risk.services.analysis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.consumer.model.RestDetailDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.HumanPerformance;
import com.risk.util.Calculation;

@Scope("prototype")
@Service
public class CrewTotalService {

  public CrewTotalService() {
    super();
  }

  @Autowired Calculation calc;

  StoreRecord record;
  int duration;
  double result;
  HumanPerformance crew;
  FlightScheduleDTO data;

  public void setCrewTotalServiceImpl(StoreRecord record) {
    this.record = record;
    crew = new HumanPerformance();
    duration = 0;
    data = record.getSchedule();
    record.setCrewTotal(crew);
  }

  public void addDuration(RestDetailDTO rest) {
    duration += rest.getRestMinutes();
  }

  public void getAnalysisData() {
    int totalCrew = record.getRestDetailTotal();
    duration = duration / totalCrew;
    if (duration > 720) crew.setRestTime(0);
    else {
      result = calc.getPercentage(duration, 600, 5);
      crew.setRestTime(result);
    }
    crew.setMsgRestTime(
        CommonConstant.OUTOF5 + "Crew Averagly rest aprox " + duration + " minutes");
    int totTime = data.getDuration();
    if (totTime > 720) {
      result = calc.getPercentageGreater(totTime, 1300, 3);
      crew.setDutyTime(result);
    } else crew.setDutyTime(0);
    crew.setMsgDutyTime(CommonConstant.OUTOF3 + "Crew Averagly Duty aprox " + totTime + " minutes");
    record.setCrewTotal(crew);
  }
}
