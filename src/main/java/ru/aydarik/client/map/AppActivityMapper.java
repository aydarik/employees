package ru.aydarik.client.map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.aydarik.client.ClientFactory;
import ru.aydarik.client.activity.AddActivity;
import ru.aydarik.client.activity.DeleteActivity;
import ru.aydarik.client.activity.EditActivity;
import ru.aydarik.client.activity.ReloadActivity;
import ru.aydarik.client.place.GridPlace;
import ru.aydarik.client.place.actions.AddPlace;
import ru.aydarik.client.place.actions.DeletePlace;
import ru.aydarik.client.place.actions.EditPlace;

/**
 * User: agumerbaev
 */
public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof AddPlace) {
			return new AddActivity(clientFactory);
		} else if (place instanceof EditPlace) {
			return new EditActivity(clientFactory, ((EditPlace)place).getEmployeeId());
		} else if (place instanceof DeletePlace) {
			return new DeleteActivity(clientFactory, ((DeletePlace)place).getEmployeeId());
		} else if (place instanceof GridPlace) {
			return new ReloadActivity(clientFactory, ((GridPlace)place).needReload());
		}
		return null;
	}
}
