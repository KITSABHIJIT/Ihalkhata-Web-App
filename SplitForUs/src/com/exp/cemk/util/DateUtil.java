package com.exp.cemk.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static long getTimeDiffFromCurrentTime(int hour, int min) {
		Calendar calCurrent = Calendar.getInstance();
		Calendar calDesired = Calendar.getInstance();
		long millisecondsCurrent = 0;
		long millisecondsDesired = 0;
		long diff = 0;
		calDesired.set(calCurrent.get(Calendar.YEAR),
				calCurrent.get(Calendar.MONTH), calCurrent.get(Calendar.DATE),
				hour, min, 0);
		millisecondsCurrent = calCurrent.getTimeInMillis();
		millisecondsDesired = calDesired.getTimeInMillis();

		if (millisecondsDesired < millisecondsCurrent) {
			calDesired.set(calCurrent.get(Calendar.YEAR),
					calCurrent.get(Calendar.MONTH),
					calCurrent.get(Calendar.DATE) + 1, hour, min, 0);
			diff = calDesired.getTimeInMillis() - millisecondsCurrent;
		} else {
			diff = millisecondsDesired - millisecondsCurrent;
		}
		return diff;

	}

	public static String getHourMinSecond(long milliseconds) {
		long millSeconds = (long) (milliseconds) % 1000;
		long seconds = (long) (milliseconds / 1000) % 60;
		long minutes = (long) ((milliseconds / (1000 * 60)) % 60);
		long hours = (long) ((milliseconds / (1000 * 60 * 60)) % 24);
		return hours + " hrs " + minutes + " mins " + seconds + " sec "
				+ millSeconds + " millis";
	}
	public static String getFormatedCurrentDate(String format){
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date (month/day/year)
		//DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		DateFormat df = new SimpleDateFormat(format);
		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);

		// Print what date is today!
		//System.out.println("Report Date: " + reportDate);
		return reportDate;
	}
}
