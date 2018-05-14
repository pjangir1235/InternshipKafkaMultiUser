package com.risk.services.analysis.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.risk.constants.CommonConstant;
import com.risk.consumer.model.FlightPilotSummaryDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.models.StoreRecord;
import com.risk.result.model.PilotDetail;
import com.risk.util.Calculation;
import com.risk.util.LocalDateString;

@Scope("prototype")
@Service
public class PilotAnalysisServiceImpl {

	public PilotAnalysisServiceImpl() {
		super();
	}
	@Autowired
	Calculation calc;
	public static final String NOPILOT="No First Pilot";
	private StoreRecord record;

	private LocalDate scheduleDate;
	private PilotDetail resultFinal;

	int durationLastNinty;
	int durationTotal;

	double result;

	public void setPilotAnalysisServiceImpl(StoreRecord record) {
		this.record = record;
		FlightScheduleDTO data = record.getSchedule();
		if (data.getPilots() == null) {
			resultFinal = new PilotDetail();
			setNull(resultFinal);
		}
		else {
			scheduleDate = LocalDateString.stringToLocalDate(data.getDateOfDeparture());
			durationLastNinty = 0;
			durationTotal = 0;
		}
	}

	public void getDataAnalysis(FlightPilotSummaryDTO pilot) {

		LocalDate curDate = LocalDateString.stringToLocalDate(pilot.getDateOfDeparture());
		long diff = LocalDateString.differnceInDate(curDate, scheduleDate);
		if (diff < 90)
			durationLastNinty += pilot.getDuration();
		durationTotal += pilot.getDuration();

	}
	public void finalCalc() {
		resultFinal = new PilotDetail();
		double hour;
		hour = calc.getHour(durationLastNinty);
		result = calc.getPercentage(hour, 100, 5);
		resultFinal.setDurLNinty(result);
		resultFinal.setMessDurLNinty(
		                CommonConstant.OUTOF5+"Last 90 Days done work aprox " + (int) hour + " in particular type");
		hour = calc.getHour(durationTotal);
		result = calc.getPercentage(hour, 200, 5);
		resultFinal.setTotDur(result);
		resultFinal.setMessTotDur(CommonConstant.OUTOF3+" Total Hours done in particular type aprox " + (int) hour);
		resultFinal.setNoPilot(0);
		resultFinal.setMessNoPilot(CommonConstant.OUTOF5+" FirstPilot is Available");
		record.setPilotDetail(resultFinal);
	}

	void setNull(PilotDetail detail) {
		detail.setDurLNinty(0);
		detail.setMessDurLNinty(NOPILOT);
		detail.setNoPilot(0);
		detail.setMessNoPilot(NOPILOT);
		detail.setTotDur(0);
		detail.setMessTotDur(NOPILOT);
		record.setPilotDetail(detail);
	}
}
