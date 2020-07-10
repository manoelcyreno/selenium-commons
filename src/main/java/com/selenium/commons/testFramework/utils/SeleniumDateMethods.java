package com.selenium.commons.testFramework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Manoel Cyreno
 */
public class SeleniumDateMethods {

	public String dateFutureYear() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = 365;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

	public String dateFutureDay() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = 1;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

	public String dateCurrent() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = 0;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

	public String datePastDay() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		int day = -1;
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(calendar.getTime());
	}

}
