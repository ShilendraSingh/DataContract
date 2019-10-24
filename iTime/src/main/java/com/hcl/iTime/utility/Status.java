package com.hcl.iTime.utility;

/**
* @author ShilendraSingh
 * @since   2019-09-23
 * @version 1.0 
 *
 */
public enum Status {

	PENDING(0), APPROVED(1), REJECTED(2);

	public final int value;

	private Status(int value) {
		this.value = value;
	}
}
