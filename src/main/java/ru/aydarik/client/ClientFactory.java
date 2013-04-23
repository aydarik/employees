package ru.aydarik.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import ru.aydarik.client.view.AddFormView;
import ru.aydarik.client.view.GridView;

/**
 * User: agumerbaev
 */
public interface ClientFactory {

	public EventBus getEventBus();

	public PlaceController getPlaceController();

	public AddFormView getAddFormView();

	public GridView getGridView();
}
