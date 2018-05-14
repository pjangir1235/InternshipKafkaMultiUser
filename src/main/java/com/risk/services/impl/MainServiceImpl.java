package com.risk.services.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.risk.constants.CommonConstant;
import com.risk.constants.Urls;
import com.risk.consumer.listeners.AircraftChecklistListener;
import com.risk.consumer.listeners.AircraftListener;
import com.risk.consumer.listeners.EnvironmentListener;
import com.risk.consumer.listeners.FlightCaptainSummaryListener;
import com.risk.consumer.listeners.FlightPilotSummaryListener;
import com.risk.consumer.listeners.FlightScheduleListener;
import com.risk.consumer.listeners.RestDetailListener;
import com.risk.consumer.model.AircraftChecklistDTO;
import com.risk.consumer.model.AircraftDTO;
import com.risk.consumer.model.CrewDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.consumer.model.PilotDTO;
import com.risk.consumer.model.RestDetailDTO;
import com.risk.controllers.Controller;
import com.risk.models.AirportRecord;
import com.risk.models.Environment;
import com.risk.models.ScheduleRequestDTO;
import com.risk.models.StoreRecord;
import com.risk.producer.model.User;
import com.risk.result.model.FinalAnalysisData;
import com.risk.services.analysis.impl.AircraftAnalysisServiceImpl;
import com.risk.services.analysis.impl.CaptainAnalysisServiceImpl;
import com.risk.services.analysis.impl.CrewTotalServiceImpl;
import com.risk.services.analysis.impl.DestinationEnvironmentServiceImpl;
import com.risk.services.analysis.impl.PilotAnalysisServiceImpl;
import com.risk.services.analysis.impl.SourceEnvironmentServiceImpl;
import com.risk.services.interfaces.AircraftChecklistService;
import com.risk.services.interfaces.AircraftService;
import com.risk.services.interfaces.AirportService;
import com.risk.services.interfaces.EnvironmentService;
import com.risk.services.interfaces.FlightScheduleService;
import com.risk.services.interfaces.MainService;
import com.risk.services.interfaces.RestDetailService;
import com.risk.services.interfaces.UserService;
import com.risk.util.LocalDateString;

@Service
public class MainServiceImpl implements MainService {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private AirportService airportData;

	@Autowired
	private AircraftService aircraftData;

	@Autowired
	private ApplicationContext cfx;
	@Autowired
	private AircraftChecklistService aircraftChecklistData;

	@Autowired
	private FlightScheduleService flightScheduleData;

	@Autowired
	private RestDetailService restDetailData;

	@Autowired
	private UserService userData;

	@Autowired
	private EnvironmentService environmentData;

	@Autowired
	AirportRecord recordAirport;

	@Autowired
	FlightScheduleListener flightSchedule;
	@Autowired
	AircraftListener aircraft;

	@Autowired
	RestDetailListener restDetail;

	@Autowired
	FlightCaptainSummaryListener flightCaptain;

	@Autowired
	FlightPilotSummaryListener flightPilot;

	@Autowired
	AircraftChecklistListener aircraftChecklist;

	@Autowired
	EnvironmentListener environment;

	@Override
	public void getAiprotValues() {
		airportData.getAirportData();
	}

	@Override
	public List<AircraftDTO> getAircraftValues(String aircraftCode) {
		StoreRecord rec = new StoreRecord();
		setKey(rec);
		aircraftData.getAircraftData(aircraftCode, rec);
		return aircraft.start(rec.getAircraftOffset(), rec.getKey());
	}


	public void restOfEachCrewData(StoreRecord rec,CrewDTO crew,LocalDate date)
	{
		try {
			restDetailData.getCrewRestDetail(crew.getCrewMemberId(), LocalDateString.localDateToString(date),
			                rec);
		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
		}
	}

	@Override
	public FinalAnalysisData getAnalysisData(FlightScheduleDTO flightData) throws ParseException {

		StoreRecord rec = new StoreRecord();
		setKey(rec);
		rec.setSchedule(flightData);
		AnalysisServiceImpl analysisService = cfx.getBean(AnalysisServiceImpl.class);
		analysisService.setAnalysisServiceImpl(rec);
		LocalDate date;
		try {
			CrewDTO crew = null;
			List<CrewDTO> crews = null;
			crews = flightData.getCrews();

			rec.setRestDetailCount(crews.size());
			rec.setRestDetailTotal(crews.size());
			Iterable<CrewDTO> itr = crews;
			Iterator<CrewDTO> iter = itr.iterator();
			while (iter.hasNext()) {
				rec.setRestDetailCount(rec.getRestDetailCount() - 1);
				date = LocalDateString.stringToLocalDate(flightData.getDateOfDeparture());
				date = date.minusDays(1);
				crew = iter.next();
				restOfEachCrewData(rec,crew,date);
			}
			List<RestDetailDTO> detail = restDetail.start(rec.getRestCrewMinOffset(), rec.getKey());
			CrewTotalServiceImpl restService = analysisService.getCrewTotalObject();
			for (int j = 0; j < detail.size(); j++)
				restService.addDuration(detail.get(j));

		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
		}
		// done

		try {

			PilotDTO pilot = null;
			List<PilotDTO> pilotdetails = flightData.getPilots();
			Iterable<PilotDTO> itr = pilotdetails;
			Iterator<PilotDTO> iter = itr.iterator();
			// Get Pilot Working Detail

			while (iter.hasNext()) {
				pilot = iter.next();
				flightScheduleData.getFlightSchedulePilotData(pilot.getPilotId(), pilot.getPilotDesignationCode(),
				                flightData.getDateOfDeparture(), rec);
			}

			CaptainAnalysisServiceImpl flightCaptainService = analysisService.getCaptainAnalysisObject();
			flightCaptain.start(rec.getFlightCaptainMinOffset(), rec.getKey(), flightCaptainService);
			PilotAnalysisServiceImpl flightPilotService = analysisService.getPilotAnalysisObject();
			flightPilot.start(rec.getFlightCaptainMinOffset(), rec.getKey(), flightPilotService);

		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
		}
		// Aircraft Data
		try {
			aircraftChecklistData.getAircraftChecklistOnAircraftCodeData(flightData.getAircraftCode(),
			                flightData.getDateOfDeparture(), rec);
			AircraftChecklistDTO checklistData = aircraftChecklist.start(rec.getChecklistOffset(), rec.getKey());
			AircraftAnalysisServiceImpl service = analysisService.getAircraftAnalysisObject();
			service.getDataAnalysis(checklistData);

		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
		}
		// Get Environment Source
		SourceEnvironmentServiceImpl serviceSource;
		DestinationEnvironmentServiceImpl serviceDestination;
		serviceSource = analysisService.getSourceEnvObject();
		serviceDestination = analysisService.getDestinationEnvObject();
		try {
			environmentData.getEnvironmentData(flightData.getSourceAirportCode(), rec);

		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
		}
		// Get Environment Destination
		try {
			environmentData.getEnvironmentData(flightData.getDestinationAirportCode(), rec);

		}
		catch (Exception e) {
			logger.error(CommonConstant.ERROR + e);
		}

		environment.start(rec.getEnvMinOffset(), rec.getKey(), serviceSource, serviceDestination, flightData);
		analysisService.startCalculation();

		return rec.getFinalData();

	}

	@Override
	public Environment getEnvironmentValues(String stationCode) {
		RestTemplate rest = new RestTemplate();
		return rest.getForObject(Urls.ENVURLSTART + stationCode + Urls.ENVURLEND, Environment.class);
	}

	@Override
	public List<FlightScheduleDTO> getFlightScheduleValues(ScheduleRequestDTO req) {

		List<FlightScheduleDTO> flight ;
		StoreRecord rec = new StoreRecord();
		setKey(rec);
		flightScheduleData.getFlightScheduleData(req.getLocation(), req.getDate(), rec);
		flight = flightSchedule.start(rec.getFlightMinOffset(), rec.getKey());

		return flight;
	}

	@Override
	public boolean getUserValues(User user) {
		boolean isValid = false;
		isValid = userData.getUserData(user.getUserName(), user.getPassword());
		return isValid;
	}

	@Override
	public void setKey(StoreRecord rec) {
		Random r1 = new Random();
		rec.setKey(r1.nextInt());
	}
}
