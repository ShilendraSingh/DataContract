package com.hcl.iTime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ShilendraSingh
 * @since   2019-09-23
 * @version 1.0 
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
	private Integer managerId;
}
