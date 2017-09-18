package com.cemk.exp.date.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSQLDate {

	public static Date toSQLDate(String stringDate) throws ParseException {
		java.util.Date utilDate = new java.util.Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		utilDate = df.parse(stringDate);
		Date sqlDate = new Date(utilDate.getTime());
		return sqlDate;
	}
}
