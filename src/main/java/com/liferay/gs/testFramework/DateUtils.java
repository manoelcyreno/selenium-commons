package com.liferay.gs.testFramework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String dateFutureYear() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = 365;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

	public static String dateFutureDay() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = 1;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

	public static String dateCurrent() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = 0;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

	public static String datePastDay() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = -1;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

}
