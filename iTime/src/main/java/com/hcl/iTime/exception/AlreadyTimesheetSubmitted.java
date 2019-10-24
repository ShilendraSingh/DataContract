package com.hcl.iTime.exception;

/**
 * @author ShilendraSingh
 * @since   2019-09-23
 * @version 1.0 
 *
 */
public class AlreadyTimesheetSubmitted extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyTimesheetSubmitted(String message) {
		super(message);
	}

}
