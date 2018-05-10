package com.risk.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class LocalDateString {
	public static String localDateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return (date).format(formatter);
	}

	public static LocalDate stringToLocalDate(String date) {
		return LocalDate.parse(date);
	}

	public static long differnceInDate(LocalDate dateFrom, LocalDate dateTo) {
		return ChronoUnit.DAYS.between(dateFrom, dateTo);
	}

	public static LocalTime stringtoLocalTime(String time) {
		return LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static void main(String[] args) {
		System.out.println(LocalDateString.stringToLocalDate("2015-05-10"));
	}
}
