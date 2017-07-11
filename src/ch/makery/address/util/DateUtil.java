package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
	// date pattern that is used for conversion. change as you wished
	private static final String DATE_PATTERN = "dd.MM.yyyy";

	// date formatter
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	// returns the given date as a well formatted string. above defined
	// DATE_PATTERN is used
	// @param data the date to be returned as a string

	public static String format(LocalDate date) {
		if (date == null) {
			return null;
		}

		return DATE_FORMATTER.format(date);
	}

	// converts string in the format of the DATE_PATTERN
	// returns null if string not converted
	// @param dateString the date as a String
	// return the date object or null if not converted

	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);

		} catch (DateTimeParseException ex) {
			return null;
		}
	}

	//checks the string whether it is valid date

	//@param dateString
	//return true if the string is valid date

	public static boolean validDate(String dateString){
		//try to parse string
		return DateUtil.parse(dateString) != null;
	}

}
