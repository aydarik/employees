package ru.aydarik.client.place.actions;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * User: agumerbaev
 */
public class AddPlace extends Place {

	public AddPlace() {
	}

	@Prefix(value = "add")
	public static class Tokenizer implements PlaceTokenizer<AddPlace> {

		@Override
		public String getToken(AddPlace place) {
			return "0";
		}

		@Override
		public AddPlace getPlace(String token) {
			return new AddPlace();
		}
	}
}