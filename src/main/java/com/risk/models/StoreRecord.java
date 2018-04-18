package com.risk.models;

import java.util.ArrayList;
import java.util.List;

import com.risk.consumer.model.AircraftCodeDTO;
import com.risk.consumer.model.AircraftDTO;
import com.risk.consumer.model.AircraftTypeDTO;
import com.risk.consumer.model.CrewDTO;
import com.risk.consumer.model.FlightScheduleCrewDTO;
import com.risk.consumer.model.FlightScheduleDTO;
import com.risk.consumer.model.FlightSchedulePilotDTO;
import com.risk.consumer.model.PilotDTO;
import com.risk.consumer.model.PilotDesignationDTO;
import com.risk.consumer.model.RestDetailDTO;
import com.risk.consumer.model.UserDTO;
import com.risk.result.model.AircraftCheckListDetail;
import com.risk.result.model.CaptainDetail;
import com.risk.result.model.DestinationEnvironment;
import com.risk.result.model.FinalAnalysisData;
import com.risk.result.model.HumanPerformance;
import com.risk.result.model.PilotDetail;
import com.risk.result.model.SourceEnvironment;

public class StoreRecord {


    private List<FlightScheduleDTO> flightSchedule;
    private int flightMinOffset = -1;
    private int flightMaxOffset = 0;
    private int key = 0;

    public int getFlightMinOffset() {
      return flightMinOffset;
    }

    public void setFlightMinOffset(int flightMinOffset) {
      this.flightMinOffset = flightMinOffset;
    }

    public int getFlightMaxOffset() {
      return flightMaxOffset;
    }

    public void setFlightMaxOffset(int flightMaxOffset) {
      this.flightMaxOffset = flightMaxOffset;
    }

    public int getKey() {
      return key;
    }

    public void setKey(int key) {
      this.key = key;
    }


  private List<AircraftDTO> aircraft;
  private List<AircraftTypeDTO> aircraftType;
  private List<CrewDTO> crew;

  private List<FlightScheduleCrewDTO> flightScheduleCrew;
  private List<FlightSchedulePilotDTO> flightSchedulePilot;
  private List<PilotDesignationDTO> pilotDesignation;
  private RestDetailDTO restDetail;
  private List<PilotDTO> pilot;
  private List<UserDTO> user;
  private List<AircraftCodeDTO> craftCode;
  private List<Environment> env;
  private AircraftCheckListDetail aircraftCheckList;
  private CaptainDetail captainDetail;
  private HumanPerformance crewTotal;
  private DestinationEnvironment envDestination;
  private PilotDetail pilotDetail;

  private SourceEnvironment envSource;
  private FinalAnalysisData finalData;

  private FlightScheduleDTO schedule;

  private int environmentCount = 0;
  private int flightPilotSummaryCount = 0;
  private int flightCaptainSummaryCount = 0;

  private int aircraftCount = 0;
  private int aircraftChecklistCount = 0;
  private int aircraftCodeCount = 0;
  private int userCount = 0;
  private int aircraftTypeCount = 0;
  private int crewCount = 0;
  private int flightScheduleCount = 0;
  private int flightScheduleCrewCount = 0;
  private int flightSchedulePilotCount = 0;
  private int pilotCount = 0;
  private int pilotDesignationCount = 0;
  private int restDetailCount = 0;
  private int restDetailTotal = 0;

  public FinalAnalysisData getFinalData() {
    return finalData;
  }

  public void setFinalData(FinalAnalysisData finalData) {
    this.finalData = finalData;
  }

  public int getRestDetailTotal() {
    return restDetailTotal;
  }

  public void setPilotDetail(PilotDetail pilotDetail) {
    this.pilotDetail = pilotDetail;
  }

  public void setRestDetailTotal(int restDetailTotal) {
    this.restDetailTotal = restDetailTotal;
  }

  public StoreRecord() {
    super();
    init();
    user = new ArrayList<>();
  }

  public void destroy() {
    init();
  }

  public AircraftCheckListDetail getAircraftCheckList() {
    return aircraftCheckList;
  }

  public void setAircraftCheckList(AircraftCheckListDetail aircraftCheckList) {
    this.aircraftCheckList = aircraftCheckList;
  }

  public CaptainDetail getCaptainDetail() {
    return captainDetail;
  }

  public void setCaptainDetail(CaptainDetail captainDetail) {
    this.captainDetail = captainDetail;
  }

  public HumanPerformance getCrewTotal() {
    return crewTotal;
  }

  public void setCrewTotal(HumanPerformance crewTotal) {
    this.crewTotal = crewTotal;
  }

  public DestinationEnvironment getEnvDestination() {
    return envDestination;
  }

  public void setEnvDestination(DestinationEnvironment envDestination) {
    this.envDestination = envDestination;
  }

  public PilotDetail getPilotDetail() {
    return pilotDetail;
  }

  public void resultFinal(PilotDetail pilotDetail) {
    this.pilotDetail = pilotDetail;
  }

  public SourceEnvironment getEnvSource() {
    return envSource;
  }

  public void setEnvSource(SourceEnvironment envSource) {
    this.envSource = envSource;
  }

  public FlightScheduleDTO getSchedule() {
    return schedule;
  }

  public void setSchedule(FlightScheduleDTO schedule) {
    this.schedule = schedule;
  }

  public int getFlightCaptainSummaryCount() {
    return flightCaptainSummaryCount;
  }

  public void setFlightCaptainSummaryCount(int flightCaptainSummaryCount) {
    this.flightCaptainSummaryCount = flightCaptainSummaryCount;
  }

  public int getFlightPilotSummaryCount() {
    return flightPilotSummaryCount;
  }

  public void setFlightPilotSummaryCount(int flightPilotSummaryCount) {
    this.flightPilotSummaryCount = flightPilotSummaryCount;
  }

  public void destroyUser() {
    user = new ArrayList<>();
  }

  public List<AircraftDTO> getAircraft() {
    return aircraft;
  }

  public int getAircraftChecklistCount() {
    return aircraftChecklistCount;
  }

  public int getAircraftCodeCount() {
    return aircraftCodeCount;
  }

  public int getAircraftCount() {
    return aircraftCount;
  }

  public List<AircraftTypeDTO> getAircraftType() {
    return aircraftType;
  }

  public int getAircraftTypeCount() {
    return aircraftTypeCount;
  }

  public List<AircraftCodeDTO> getCraftCode() {
    return craftCode;
  }

  public List<CrewDTO> getCrew() {
    return crew;
  }

  public int getCrewCount() {
    return crewCount;
  }

  public int getEnvironmentCount() {
    return environmentCount;
  }

  public List<FlightScheduleDTO> getFlightSchedule() {
    return flightSchedule;
  }

  public int getFlightScheduleCount() {
    return flightScheduleCount;
  }

  public List<FlightScheduleCrewDTO> getFlightScheduleCrew() {
    return flightScheduleCrew;
  }

  public int getFlightScheduleCrewCount() {
    return flightScheduleCrewCount;
  }

  public List<FlightSchedulePilotDTO> getFlightSchedulePilot() {
    return flightSchedulePilot;
  }

  public int getFlightSchedulePilotCount() {
    return flightSchedulePilotCount;
  }

  public List<PilotDTO> getPilot() {
    return pilot;
  }

  public int getPilotCount() {
    return pilotCount;
  }

  public List<PilotDesignationDTO> getPilotDesignation() {
    return pilotDesignation;
  }

  public int getPilotDesignationCount() {
    return pilotDesignationCount;
  }

  public RestDetailDTO getRestDetail() {
    return restDetail;
  }

  public int getRestDetailCount() {
    return restDetailCount;
  }

  public List<UserDTO> getUser() {
    return user;
  }

  public int getUserCount() {
    return userCount;
  }

  public List<Environment> getEnv() {
    return env;
  }

  public void setEnv(Environment env) {
    this.env.add(env);
  }

  private void init() {
    aircraft = new ArrayList<>();
    aircraftType = new ArrayList<>();
    crew = new ArrayList<>();
    flightSchedule = new ArrayList<>();
    flightScheduleCrew = new ArrayList<>();
    flightSchedulePilot = new ArrayList<>();
    pilotDesignation = new ArrayList<>();
    restDetail = new RestDetailDTO();
    pilot = new ArrayList<>();
    craftCode = new ArrayList<>();
    schedule = new FlightScheduleDTO();
    aircraftCheckList = new AircraftCheckListDetail();
    captainDetail = new CaptainDetail();
    crewTotal = new HumanPerformance();
    envDestination = new DestinationEnvironment();
    pilotDetail = new PilotDetail();
    envSource = new SourceEnvironment();
    finalData = new FinalAnalysisData();
    env = new ArrayList<>();
  }

  public void setAircraft(AircraftDTO aircraft) {
    this.aircraft.add(aircraft);
  }

  public void setAircraftChecklistCount(int aircraftChecklistCount) {
    this.aircraftChecklistCount = aircraftChecklistCount;
  }

  public void setAircraftCodeCount(int aircraftCodeCount) {
    this.aircraftCodeCount = aircraftCodeCount;
  }

  public void setAircraftCount(int aircraftCount) {
    this.aircraftCount = aircraftCount;
  }

  public void setAircraftType(AircraftTypeDTO aircraftType) {
    this.aircraftType.add(aircraftType);
  }

  public void setAircraftTypeCount(int aircraftTypeCount) {
    this.aircraftTypeCount = aircraftTypeCount;
  }

  public void setCraftCode(AircraftCodeDTO craftCode) {
    this.craftCode.add(craftCode);
  }

  public void setCrew(CrewDTO crew) {
    this.crew.add(crew);
  }

  public void setCrewCount(int crewCount) {
    this.crewCount = crewCount;
  }

  public void setEnvironmentCount(int environmentCount) {
    this.environmentCount = environmentCount;
  }

  public void setFlightSchedule(FlightScheduleDTO flightSchedule) {
    this.flightSchedule.add(flightSchedule);
  }

  public void setFlightScheduleCount(int flightScheduleCount) {
    this.flightScheduleCount = flightScheduleCount;
  }

  public void setFlightScheduleCrew(FlightScheduleCrewDTO flightScheduleCrew) {
    this.flightScheduleCrew.add(flightScheduleCrew);
  }

  public void setFlightScheduleCrewCount(int flightScheduleCrewCount) {
    this.flightScheduleCrewCount = flightScheduleCrewCount;
  }

  public void setFlightSchedulePilot(FlightSchedulePilotDTO flightSchedulePilot) {
    this.flightSchedulePilot.add(flightSchedulePilot);
  }

  public void setFlightSchedulePilotCount(int flightSchedulePilotCount) {
    this.flightSchedulePilotCount = flightSchedulePilotCount;
  }

  public void setPilot(PilotDTO pilot) {
    this.pilot.add(pilot);
  }

  public void setPilotCount(int pilotCount) {
    this.pilotCount = pilotCount;
  }

  public void setPilotDesignation(PilotDesignationDTO pilotDesignation) {
    this.pilotDesignation.add(pilotDesignation);
  }

  public void setPilotDesignationCount(int pilotDesignationCount) {
    this.pilotDesignationCount = pilotDesignationCount;
  }

  public void setRestDetail(RestDetailDTO restDetail) {
    this.restDetail = restDetail;
  }

  public void setRestDetailCount(int restDetailCount) {
    this.restDetailCount = restDetailCount;
  }

  public void setUser(UserDTO user) {
    this.user.add(user);
  }

  public void setUserCount(int userCount) {
    this.userCount = userCount;
  }
}
