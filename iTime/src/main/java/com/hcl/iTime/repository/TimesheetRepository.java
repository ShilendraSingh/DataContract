package com.hcl.iTime.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.iTime.Entity.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

	Optional<Timesheet> findByForDateAndEmployeeId(LocalDate forDate, Integer employeeId);

	List<Timesheet> findByEmployeeIdAndReportingManagerIdAndTimesheetIdIn(Integer employeeId,
			Integer reportingManagerId, List<Integer> timesheetIds);
}
