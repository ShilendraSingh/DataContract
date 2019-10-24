package com.hcl.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.employee.dto.EmployeeDto;
import com.hcl.employee.entity.Employee;
import com.hcl.employee.exception.RecordNotFoundException;
import com.hcl.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Value("${employee.not.found.exception}")
	private String employeeNotFound;

	@Override
	public EmployeeDto getManager(Integer employeeId) {

		log.info("EmployeeServiceImpl :: getManager -- {}", employeeId);
		Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
		if (!employeeOptional.isPresent()) {
			throw new RecordNotFoundException(employeeNotFound);
		}

		EmployeeDto employeeDto = EmployeeDto.builder().employeeId(employeeId)
				.managerId(employeeOptional.get().getManagerId()).build();
		return employeeDto;
	}

}
