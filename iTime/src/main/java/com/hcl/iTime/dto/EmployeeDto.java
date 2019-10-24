package com.hcl.iTime.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EmployeeDto {

	private Integer employeeId;
	private Integer managerId;
}