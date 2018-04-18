package com.risk.services.analysis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.consumer.model.RestDetailDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.HumanPerformance;
import com.risk.util.Calculation;

@Service
public class CrewTotalServiceImpl {
  @Autowired StoreRecord record;
  @Autowired Calculation calc;
  int duration;
  double result;
  HumanPerformance crew;
  FlightScheduleDTO data;

  public void setValue() {

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
    crew.setMsgRestTime("Out of 5\nCrew Averagly rest aprox " + duration + " minutes");
    int totTime = data.getDuration();
    if (totTime > 720) {
      result = calc.getPercentageGreater(totTime, 1300, 3);
      crew.setDutyTime(result);
    } else crew.setDutyTime(0);
    crew.setMsgDutyTime("Out of 3\nCrew Averagly Duty aprox " + totTime + " minutes");
    record.setCrewTotal(crew);
  }
}
