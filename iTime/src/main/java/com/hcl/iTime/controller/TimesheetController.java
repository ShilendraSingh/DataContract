package com.hcl.iTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.iTime.dto.ApproveDto;
import com.hcl.iTime.dto.ResponseDto;
import com.hcl.iTime.dto.TimesheetDto;
import com.hcl.iTime.exception.RejectMessageEmptyException;
import com.hcl.iTime.service.TimesheetService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller is for Submit,Reject And ApproveTimeSheet
* @author ShilendraSingh
 * @since   2019-09-23
 * @version 1.0 
 * 
 *
 */
@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class TimesheetController {

	@Autowired
	private TimesheetService timesheetService;

	@PostMapping("/timesheets")
	public ResponseEntity<ResponseDto> submitTimesheet(@RequestBody TimesheetDto timesheetDto) {

		log.info(" submitTimesheet --- ");
		return new ResponseEntity<>(timesheetService.submitTimesheet(timesheetDto), HttpStatus.CREATED);
	}

	@PutMapping("/timesheets/{managerId}")
	public ResponseEntity<ResponseDto> approveOrRejectTimesheet(@RequestBody ApproveDto approveDto,
			@PathVariable Integer managerId) throws RejectMessageEmptyException {

		log.info("TimesheetController :: approveTimesheet --- ");
		return new ResponseEntity<>(timesheetService.approveOrRejectTimesheet(approveDto, managerId), HttpStatus.OK);
	}
}
