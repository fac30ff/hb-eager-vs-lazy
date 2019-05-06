package com.github.fac30ff.hibernate.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	//The date formatter
	// - dd: day in month (number)
	// - MM: month in year (number)
	// - yyyy: year
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	//read a date string and parse/convert to a date
	public static Date parseDate(String dateStr) throws ParseException {
		return formatter.parse(dateStr);
	}
	//read a date and format/convert to a string
	public static String formatDate(Date theDate) {
		return theDate != null ? formatter.format(theDate) : "";
	}
}
