package com.hcl.iTime.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.iTime.dto.ApproveDto;
import com.hcl.iTime.dto.ResponseDto;
import com.hcl.iTime.dto.TimesheetDto;
import com.hcl.iTime.exception.RejectMessageEmptyException;
import com.hcl.iTime.service.TimesheetService;
import com.hcl.iTime.service.impl.TimesheetServiceImpl;
import com.hcl.iTime.utility.TimesheetUtility;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TimesheetControllerTest {

	@Mock
	TimesheetService timesheetService;

	@InjectMocks
	TimesheetController timesheetController;

	@Mock
	TimesheetServiceImpl timesheetServiceImpl;

	private MockMvc mockMvc;
	ApproveDto approveDto = null;
	TimesheetDto timeSheet = null;
	TimesheetDto timeSheet1 = null;
	List<Integer> timeSheetList = null;
	ResponseDto response = null;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(timesheetController).build();

		timeSheet = new TimesheetDto();
		timeSheet.setEmployeeId(1);
		timeSheet.setFromDate(LocalDate.of(2019, 10, 21));
		timeSheet.setToDate(LocalDate.of(2019, 10, 21));
		timeSheet.setWorkingHours(9);

		timeSheet1 = new TimesheetDto();
		timeSheet1.setEmployeeId(1);
		timeSheet1.setFromDate(LocalDate.of(2019, 10, 21));
		timeSheet1.setToDate(LocalDate.of(2019, 10, 21));
		timeSheet1.setWorkingHours(9);

		timeSheetList = new ArrayList<Integer>();
		timeSheetList.add(1);
		timeSheetList.add(2);

		approveDto = new ApproveDto();
		approveDto.setEmployeeId(1);
		approveDto.setRejectMessage("test");
		approveDto.setStatus(1);
		approveDto.setTimesheetIds(timeSheetList);

		response = new ResponseDto();
		response.setMessage(TimesheetUtility.STATUS_SUCCCESS);
		response.setStatus("Success");
	}

	@Test
	public void testsubmitTimesheet() throws RejectMessageEmptyException {

		Mockito.when(timesheetServiceImpl.approveOrRejectTimesheet(approveDto, 12)).thenReturn(response);
		ResponseEntity<ResponseDto> timeSheetResponse = timesheetController.approveOrRejectTimesheet(approveDto, 12);
		assertEquals(200, timeSheetResponse.getStatusCodeValue());

	}

	
}
