package com.hcl.iTime.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

/**
 * @author ShilendraSingh
 * @since   2019-09-23
 * @version 1.0 
 *
 */
@Component
public class TimesheetUtility {

	public static final String EMPLOYEE_DOES_NOT_EXIST_EXCEPTION = "Invalid employee";

	public static final String INVALID_DATE_EEXCEPTION = "Invalid leave date";

	public static final String SUBMITTED_STATUS_PENDING = "PENDING";

	public static final String STATUS_SUCCCESS = "Success";

	public static final String STATUS_FAIL = "Fail";

	public static final String TIMESHEET_SUCCESS_MESSAGE = "Timesheet submitted successfully.";

	public static final String VERIFED_SUCCESS_MESSAGE = "Timesheet approved successsfully.";
	public static final String REJECT_SUCCESS_MESSAGE = "Timesheet has been rejected.";

	public static final String AND = "&";
	public static final String HOLIDAY_DATE = "holidayDate=";
	public static final String TIMESHEET_ALREADY_SUBMITED_MESSAGE = "Timesheet Already submitted";

	public static final String CANNT_SUBMIT_FUTURE_DATE_MESSAGE = "Cann't Submit for future date.";
	public static final String TODATE_ISBEFORE_FROMDATE = "To Date should be after from Date.";
	public static final Integer WORKING_HOUR = 9;
	public static final String EMPLOYEE_RECORD_NOT_FOUND = "Employee Record not found.";
	public static final String HOLIDAY_OCCURED = "Its Holidays on :";
	public static final String RECORD_NOT_FOUND = "record not found :";
	
	public static final String REJECT_EMPTY_MESSAGE = "Please enter reason for reject.";

	public LocalDate getDateInFormat(String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}

	public int calculateDays(String fromDate, String toDate) {
		long noOfDaysBetween = ChronoUnit.DAYS.between(getDateInFormat(fromDate), getDateInFormat(toDate));

		return (int) noOfDaysBetween + 1;
	}

	public int calculateDays(LocalDate fromDate, LocalDate toDate) {
		long noOfDaysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
		return (int) noOfDaysBetween;
	}
}
