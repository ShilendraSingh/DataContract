package com.hcl.iTime.service;



import org.springframework.stereotype.Service;

import com.hcl.iTime.dto.ApproveDto;
import com.hcl.iTime.dto.ResponseDto;
import com.hcl.iTime.dto.TimesheetDto;
import com.hcl.iTime.exception.RejectMessageEmptyException;

@Service
public interface TimesheetService {

	public ResponseDto submitTimesheet(TimesheetDto timesheetDto);

	public ResponseDto approveOrRejectTimesheet(ApproveDto approveDto, Integer managerId) throws RejectMessageEmptyException;

}
