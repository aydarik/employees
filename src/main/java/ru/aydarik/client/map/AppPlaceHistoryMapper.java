package ru.aydarik.client.map;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import ru.aydarik.client.place.GridPlace;
import ru.aydarik.client.place.actions.AddPlace;
import ru.aydarik.client.place.actions.DeletePlace;
import ru.aydarik.client.place.actions.EditPlace;

/**
 * User: agumerbaev
 */
@WithTokenizers(
		{AddPlace.Tokenizer.class, EditPlace.Tokenizer.class, DeletePlace.Tokenizer.class, GridPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
