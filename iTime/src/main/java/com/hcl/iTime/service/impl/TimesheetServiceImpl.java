package com.hcl.iTime.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.iTime.Entity.Employee;
import com.hcl.iTime.Entity.Timesheet;
import com.hcl.iTime.dto.ApproveDto;
import com.hcl.iTime.dto.ResponseDto;
import com.hcl.iTime.dto.TimesheetDto;
import com.hcl.iTime.exception.AlreadyTimesheetSubmitted;
import com.hcl.iTime.exception.InvalidDateException;
import com.hcl.iTime.exception.RecordNotFoundException;
import com.hcl.iTime.exception.RejectMessageEmptyException;
import com.hcl.iTime.repository.EmployeeRepository;
import com.hcl.iTime.repository.HolidayRepository;
import com.hcl.iTime.repository.TimesheetRepository;
import com.hcl.iTime.service.TimesheetService;
import com.hcl.iTime.utility.Status;
import com.hcl.iTime.utility.TimesheetUtility;
import com.hcl.iTime.utility.WeekEnd;

import lombok.extern.slf4j.Slf4j;

/**
 * This Service is for Submit,Reject And ApproveTimeSheet
 * @author ShilendraSingh
 * @since 2019-09-23
 * @version 1.0
 *
 */
@Slf4j
@Service
public class TimesheetServiceImpl implements TimesheetService {

	@Autowired
	private TimesheetRepository timesheetRepository;

	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ResponseDto submitTimesheet(TimesheetDto timesheetDto) {

		//Validation 
		List<LocalDate> holidaysDto = validateSubmission(timesheetDto);

		//Checking for timesheet submited or not for particul date for employee
		
		Optional<Timesheet> timesheet = timesheetRepository.findByForDateAndEmployeeId(timesheetDto.getFromDate(),
				timesheetDto.getEmployeeId());
		if (timesheet.isPresent()) {
			throw new AlreadyTimesheetSubmitted(TimesheetUtility.TIMESHEET_ALREADY_SUBMITED_MESSAGE);
		}

		Optional<Employee> employeeOptional = employeeRepository.findById(timesheetDto.getEmployeeId());
		if (!employeeOptional.isPresent()) {
			throw new RecordNotFoundException(TimesheetUtility.EMPLOYEE_RECORD_NOT_FOUND);
		}

		List<Timesheet> timesheets = new ArrayList<>();
		LocalDate submittedDate = LocalDate.now();

		for (LocalDate date = timesheetDto.getFromDate(); date
				.isBefore(timesheetDto.getToDate().plusDays(1)); date = date.plusDays(1)) {
			if (date.getDayOfWeek().name().equals(WeekEnd.SATURDAY.name())
					|| date.getDayOfWeek().name().equals(WeekEnd.SUNDAY.name()) || holidaysDto.contains(date)) {
				continue;
			}
			timesheets.add(Timesheet.builder().employeeId(timesheetDto.getEmployeeId())
					.reportingManagerId(employeeOptional.get().getManagerId()).forDate(date)
					.workingHours(timesheetDto.getWorkingHours()).submittedDate(submittedDate)
					.status(Status.PENDING.value).build());
		}
		timesheetRepository.saveAll(timesheets);
		return ResponseDto.builder().status(TimesheetUtility.STATUS_SUCCCESS)
				.message(TimesheetUtility.TIMESHEET_SUCCESS_MESSAGE).build();
	}

	/**
	 * @param timesheetDto
	 * @return Integer
	 * validateSubmission
	 * 
	 */
	public List<LocalDate> validateSubmission(TimesheetDto timesheetDto) {
		if (timesheetDto.getFromDate().compareTo(LocalDate.now()) > 0
				|| timesheetDto.getToDate().compareTo(LocalDate.now()) > 0) {
			throw new InvalidDateException(TimesheetUtility.CANNT_SUBMIT_FUTURE_DATE_MESSAGE);
		} else if (timesheetDto.getToDate().isBefore(timesheetDto.getFromDate())) {
			throw new InvalidDateException(TimesheetUtility.TODATE_ISBEFORE_FROMDATE);
		}

		List<LocalDate> timesheetDates = new ArrayList<>();
		for (LocalDate date = timesheetDto.getFromDate(); date
				.isBefore(timesheetDto.getToDate().plusDays(1)); date = date.plusDays(1)) {
			timesheetDates.add(date);
		}

		List<LocalDate> holidays = holidayRepository.findHolidayDateIn(timesheetDates);
		return holidays;
		
		
	}

	/**
	 *This is method for approve or reject time sheet
	 * @author ShilendraSingh
	 * @param ApproveDto
	 * @param managerId is not null 
	 * @return responseDto
	 */
	@Override
	public ResponseDto approveOrRejectTimesheet(ApproveDto approveDto, Integer managerId) throws RejectMessageEmptyException {

		ResponseDto responseDto = new ResponseDto();
		List<Timesheet> timesheets = timesheetRepository.findByEmployeeIdAndReportingManagerIdAndTimesheetIdIn(
				approveDto.getEmployeeId(), managerId, approveDto.getTimesheetIds());

		if (timesheets.isEmpty()) {
			//Record not found
			throw new RecordNotFoundException(TimesheetUtility.RECORD_NOT_FOUND);
		}

		if (approveDto.getStatus() == Status.APPROVED.value) {
			timesheets.stream().forEach(timesheet -> {
				timesheet.setStatus(Status.APPROVED.value);
			});
			responseDto.setMessage(TimesheetUtility.VERIFED_SUCCESS_MESSAGE);
		} else if ((approveDto.getStatus() == Status.REJECTED.value)
				&& (approveDto.getRejectMessage() == null || approveDto.getRejectMessage().isEmpty())) {
			throw new RejectMessageEmptyException(TimesheetUtility.REJECT_EMPTY_MESSAGE);
		} else {
			timesheets.stream().forEach(timesheet -> {
				timesheet.setStatus(Status.REJECTED.value);
				timesheet.setRemarks(approveDto.getRejectMessage());
			});
			responseDto.setMessage(TimesheetUtility.REJECT_SUCCESS_MESSAGE);

		}
		responseDto.setStatus(TimesheetUtility.STATUS_SUCCCESS);
		timesheetRepository.saveAll(timesheets);
		return responseDto;
	}

}
