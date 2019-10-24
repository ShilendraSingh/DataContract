package com.hcl.iTime.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ShilendraSingh
 *
 */
@Setter
@Getter
public class TimesheetDto {

	private int employeeId;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Integer workingHours;
}