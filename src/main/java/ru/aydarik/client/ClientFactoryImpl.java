package ru.aydarik.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import ru.aydarik.client.view.AddFormView;
import ru.aydarik.client.view.GridView;
import ru.aydarik.client.view.form.AddFormViewImpl;
import ru.aydarik.client.view.grid.GridViewImpl;

/**
 * User: agumerbaev
 */
public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);

	private final AddFormView addFormView = new AddFormViewImpl();
	private final GridView gridView = new GridViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public AddFormView getAddFormView() {
		return addFormView;
	}

	@Override
	public GridView getGridView() {
		return gridView;
	}
}
