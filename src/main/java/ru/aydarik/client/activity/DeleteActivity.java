package ru.aydarik.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ru.aydarik.client.AppConstants;
import ru.aydarik.client.ClientFactory;
import ru.aydarik.client.place.GridPlace;

/**
 * User: agumerbaev
 */
public class DeleteActivity extends AbstractActivity {

	private ClientFactory clientFactory;
	private Long id;

	public DeleteActivity(ClientFactory clientFactory, String id) {
		this.clientFactory = clientFactory;
		this.id = Long.parseLong(id);
	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		AppConstants.employeeService.removeEmployee(id, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(AppConstants.messages.serverError());
			}

			@Override
			public void onSuccess(Void result) {
				clientFactory.getPlaceController().goTo(new GridPlace(AppConstants.YES));
			}
		});
	}
}