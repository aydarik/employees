package ru.aydarik.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * User: agumerbaev
 */
public class GridPlace extends Place {

	private String needReload;

	public GridPlace(String needReload) {
		this.needReload = needReload;
	}

	public String needReload() {
		return needReload;
	}

	@Prefix(value = "all")
	public static class Tokenizer implements PlaceTokenizer<GridPlace> {

		@Override
		public String getToken(GridPlace place) {
			return place.needReload();
		}

		@Override
		public GridPlace getPlace(String withCounter) {
			return new GridPlace(withCounter);
		}
	}
}
