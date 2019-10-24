package com.hcl.employee.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Laxman
 *
 */
@Setter
@Getter
@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String name;
	private Long phone;
	private String email;
	private String password;
	private Integer roleId;
	private Integer managerId;
	private LocalDate CreatedDate;
	private LocalDate LoastModifiedDate;
}
