package ru.aydarik.client;

import com.google.gwt.core.client.GWT;

/**
 * User: agumerbaev
 */
public final class AppConstants {

	public static final String YES = "y";
	public static final String NO = "n";

	public static final EmployeeServiceAsync employeeService = GWT.create(EmployeeService.class);
	public static final Messages messages = GWT.create(Messages.class);
	public static final Resources resources = GWT.create(Resources.class);
}
