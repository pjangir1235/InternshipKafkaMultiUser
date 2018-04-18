package com.risk.services.analysis.impl;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.AirportDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.AirportRecord;
import com.risk.models.Environment;
import com.risk.models.StoreRecord;
import com.risk.result.model.DestinationEnvironment;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Service
public class DestinationEnvironmentServiceImpl {

  @Autowired LocalDateString convert;

  @Autowired StoreRecord record;

  @Autowired Calculation calc;

  @Autowired AirportRecord airportRecord;

  FlightScheduleDTO data;
  DestinationEnvironment env;
  AirportDTO airport;
  double result;

  public void setValue() {
    data = record.getSchedule();
    env = new DestinationEnvironment();
    setInit();
  }

  public void getValue(Environment envData) {
    env.setNoWeather(0);
    env.setMsgNoWeather("Out of 5\n Got Weather Report");
    if (Integer.parseInt(envData.getWindSpeed()) > 30) {
      result = calc.getPercentageGreater(Integer.parseInt(envData.getWindSpeed()) - 30, 50, 4);
      env.setWindSpeed(result);
    } else env.setWindSpeed(0);
    env.setMsgWindSpeed(
        "Out of 4\n Wind Speed is "
            + envData.getWindSpeed()
            + " "
            + envData.getUnit().getWindSpeed());

    if (Integer.parseInt(envData.getVisibility()) < 15) {
      result = calc.getPercentage(Double.parseDouble(envData.getVisibility()), 15, 5);
      env.setVisibility(result);
    } else env.setVisibility(0);
    env.setMsgVisibility(
        "Out of 5\n"
            + "The Visibility of Airport is "
            + envData.getVisibility()
            + " "
            + envData.getUnit().getVisibility());
    if (Integer.parseInt(envData.getTemperature()) < 10) {
      result = calc.getPercentage(Double.parseDouble(envData.getTemperature()), 15, 4);
      env.setWinterOper(result);
    } else env.setWinterOper(0);
    env.setMsgWinterOper(
        "Out of 4\n"
            + "The Temperature of the Location is "
            + envData.getTemperature()
            + " "
            + envData.getUnit().getTemperature());
    record.setEnvDestination(env);
  }

  public void setInit() {
    List<AirportDTO> airportList = airportRecord.getAirport();
    Iterable<AirportDTO> itr = airportList;
    Iterator<AirportDTO> iter = itr.iterator();
    while (iter.hasNext()) {
      AirportDTO airportData = iter.next();
       if (airportData.getAirportCode().equals(data.getDestinationAirportCode()))
        if (airportData.getIsMountain()) {
          env.setMountain(4);
          env.setMsgMountain("Mountain Area");
          break;
        } else {
          env.setMountain(0);
          env.setMsgMountain("No Mountain Area");
          break;
        }
    }

    data.getTimeDeparture();
    LocalTime departureTime = convert.stringtoLocalTime(data.getTimeDeparture());
    int timeOfFlight = departureTime.getHour();
    if (timeOfFlight < 19 && timeOfFlight >= 5) env.setNightOperation(0);
    else if (timeOfFlight >= 19 && timeOfFlight <= 21) env.setNightOperation(1);
    else if (timeOfFlight >= 2 && timeOfFlight <= 4) env.setNightOperation(2);
    else env.setNightOperation(3);
    env.setMsgNightOper("Out of 3\n Opeartion at aprox " + timeOfFlight);
    env.setNoWeather(5);
    env.setMsgNoWeather("Out of 5\n" + CommonConstant.NOWEATHER);
    env.setWindSpeed(0);
    env.setMsgWindSpeed("Out of 4\n" + CommonConstant.NOWEATHER);
    env.setVisibility(0);
    env.setMsgVisibility("Out of 5\n" + CommonConstant.NOWEATHER);
    env.setWinterOper(0);
    env.setMsgWinterOper("Out of 4\n" + CommonConstant.NOWEATHER);
    record.setEnvDestination(env);
  }
}
