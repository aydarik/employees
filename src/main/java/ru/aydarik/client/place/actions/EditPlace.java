package ru.aydarik.client.place.actions;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * User: agumerbaev
 */
public class EditPlace extends Place {

	private String employeeId;

	public EditPlace(String token) {
		this.employeeId = token;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	@Prefix(value = "edit")
	public static class Tokenizer implements PlaceTokenizer<EditPlace> {

		@Override
		public String getToken(EditPlace place) {
			return place.getEmployeeId();
		}

		@Override
		public EditPlace getPlace(String employeeId) {
			return new EditPlace(employeeId);
		}
	}
}