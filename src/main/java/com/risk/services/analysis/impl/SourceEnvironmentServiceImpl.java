package com.risk.services.analysis.impl;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.AirportDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.AirportRecord;
import com.risk.models.Environment;
import com.risk.models.StoreRecord;
import com.risk.result.model.SourceEnvironment;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Scope("prototype")
@Service
public class SourceEnvironmentServiceImpl {
	@Autowired
	Calculation calc;
	@Autowired
	AirportRecord airportRecord;

	private	StoreRecord record;
	private	FlightScheduleDTO data;
	private	SourceEnvironment env;
	private	AirportDTO airportData;
	double result;


	public SourceEnvironmentServiceImpl() {
		super();
	}
	public void setSourceEnvironmentServiceImpl(StoreRecord rec) {
		this.record=rec;
		data = record.getSchedule();
		env = new SourceEnvironment();
		airportData=new AirportDTO();
		setInit();
	}

	public void getValue(Environment envData) {
		env.setNoWeather(0);
		env.setMsgNoWeather(CommonConstant.OUTOF5+"Got Weather Report");
		if (Integer.parseInt(envData.getWindSpeed()) > 30) {
			result = calc.getPercentageGreater(Integer.parseInt(envData.getWindSpeed()) - 30.0, 50, 4);
			env.setWindSpeed(result);
		}
		else
			env.setWindSpeed(0);
		env.setMsgWindSpeed(
		                CommonConstant.OUTOF4+"Wind Speed is " + envData.getWindSpeed() + " " + envData.getUnit().getWindSpeed());

		if (Integer.parseInt(envData.getVisibility()) < 15) {
			result = calc.getPercentage(Double.parseDouble(envData.getVisibility()), 15, 5);
			env.setVisibility(result);
		}
		else
			env.setVisibility(0);
		env.setMsgVisibility(CommonConstant.OUTOF5+ "The Visibility of Airport is " + envData.getVisibility() + " "
		                + envData.getUnit().getVisibility());
		if (Integer.parseInt(envData.getTemperature()) < 10) {
			result = calc.getPercentage(Double.parseDouble(envData.getTemperature()), 15, 4);
			env.setWinterOper(result);
		}
		else
			env.setWinterOper(0);
		env.setMsgWinterOper(CommonConstant.OUTOF4+ "The Temperature of the Location is " + envData.getTemperature() + " "
		                + envData.getUnit().getTemperature());
		record.setEnvSource(env);
	}

	public void setInit() {
		List<AirportDTO> airportList = airportRecord.getAirport();
		Iterable<AirportDTO> itr = airportList;
		Iterator<AirportDTO> iter = itr.iterator();
		while (iter.hasNext()) {
			airportData = iter.next();
			if (airportData.getAirportCode().equals(data.getDestinationAirportCode()))
				if (airportData.getIsMountain()) {
					env.setMountain(4);
					env.setMsgMountain("Mountain Area");

				}
				else {
					env.setMountain(0);
					env.setMsgMountain("No Mountain Area");

				}
			break;
		}
		data.getTimeDeparture();
		LocalTime departureTime = LocalDateString.stringtoLocalTime(data.getTimeDeparture());
		int timeOfFlight = departureTime.getHour();
		if (timeOfFlight < 19 && timeOfFlight >= 5)
			env.setNightOperation(0);
		else if (timeOfFlight >= 19 && timeOfFlight <= 21)
			env.setNightOperation(1);
		else if (timeOfFlight >= 2 && timeOfFlight <= 4)
			env.setNightOperation(2);
		else
			env.setNightOperation(3);
		env.setMsgNightOper("Out of 3\n Opeartion at aprox " + timeOfFlight);
		env.setNoWeather(5);
		env.setMsgNoWeather(CommonConstant.OUTOF5+ CommonConstant.NOWEATHER);
		env.setWindSpeed(0);
		env.setMsgWindSpeed(CommonConstant.OUTOF4+ CommonConstant.NOWEATHER);
		env.setVisibility(0);
		env.setMsgVisibility(CommonConstant.OUTOF5+ CommonConstant.NOWEATHER);
		env.setWinterOper(0);
		env.setMsgWinterOper(CommonConstant.OUTOF4+ CommonConstant.NOWEATHER);
		record.setEnvSource(env);
	}
}
