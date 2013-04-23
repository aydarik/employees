package ru.aydarik.client.place.actions;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * User: agumerbaev
 */
public class DeletePlace extends Place {

	private String employeeId;

	public DeletePlace(String token) {
		this.employeeId = token;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	@Prefix(value = "delete")
	public static class Tokenizer implements PlaceTokenizer<DeletePlace> {

		@Override
		public String getToken(DeletePlace place) {
			return place.getEmployeeId();
		}

		@Override
		public DeletePlace getPlace(String employeeId) {
			return new DeletePlace(employeeId);
		}
	}
}