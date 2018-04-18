package com.risk.constants;

public class Queries {
  public static final String USERBYID =
      "SELECT user FROM User user WHERE user.userName=?1 AND user.passwrd=?2";

  public static final String SCHEDULEBYLOCATION =
      "SELECT flight FROM FlightSchedule flight WHERE ( flight.sourceAirportCode=?1 OR flight.destinationAirportCode=?1 ) AND flight.dateOfDeparture=?2";

  public static final String SCHEDULEBYPILOT =
      "SELECT NEW com.risk.producer.model.FlightPilotSummary ( flight.duration, flight.aircraftCode, flight.dateOfDeparture ) FROM FlightSchedule as flight WHERE flight.dateOfDeparture<=?2 AND flight.flightScheduleId IN ( SELECT pilot.flightScheduleId FROM FlightSchedulePilot as pilot WHERE pilot.pilotId=?1 )";
  public static final String SCHEDULEBYCAPTAIN =
      "SELECT NEW com.risk.producer.model.FlightCaptainSummary ( flight.duration, flight.aircraftCode, flight.dateOfDeparture ) FROM FlightSchedule as flight WHERE flight.dateOfDeparture<=?2 AND flight.flightScheduleId IN ( SELECT pilot.flightScheduleId FROM FlightSchedulePilot as pilot WHERE pilot.pilotId=?1 )";

  public static final String AIRCRAFTCHECKLIST =
      "SELECT checkList FROM AircraftChecklist checkList WHERE checkList.aircraftCode=?1 AND checkList.date=?2";

  public static final String AIRCRAFTBYTYPE =
      "SELECT NEW com.risk.producer.model.AircraftCodeType ( aircraft.aircraftCode ) FROM Aircraft as aircraft WHERE aircraft.aircraftType= ( SELECT craft.aircraftType FROM aircraft as craft WHERE craft.aircraftCode=?1)";

  public static final String RESTDETAILBYID =
      "SELECT rest FROM RestDetail rest WHERE rest.memberId=?1 AND date=?2";

  public static final String AIRCRAFTBYCode =
	      "SELECT aircraft from Aircraft aircraft WHERE aircraft.aircraftCode=?1";



  private Queries() {}
}
