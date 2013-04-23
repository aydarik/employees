package ru.aydarik.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import ru.aydarik.client.map.AppActivityMapper;
import ru.aydarik.client.map.AppPlaceHistoryMapper;
import ru.aydarik.client.place.GridPlace;

public class EmployeesProjectEntryPoint implements EntryPoint {

	private Place defaultPlace = new GridPlace(AppConstants.NO);
	private SimplePanel appWidget = new SimplePanel();

	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appWidget);

		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		// CssResource
		AppConstants.resources.cssTable().ensureInjected();

		RootPanel.get().add(appWidget);
		historyHandler.handleCurrentHistory();
	}
}
