package ru.aydarik.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import ru.aydarik.client.AppConstants;
import ru.aydarik.client.ClientFactory;
import ru.aydarik.client.view.AddFormView;
import ru.aydarik.client.widget.InfoDialogBox;
import ru.aydarik.shared.EmployeeBean;

/**
 * User: agumerbaev
 */
public class EditActivity extends AbstractActivity implements AddFormView.Presenter {

	private ClientFactory clientFactory;
	private Long id;

	public EditActivity(ClientFactory clientFactory, String id) {
		this.clientFactory = clientFactory;
		this.id = Long.parseLong(id);
	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		final AddFormView view = clientFactory.getAddFormView();
		view.setPresenter(this);
		prepare(view);

		DialogBox dialogBox = InfoDialogBox.getInstance();
		dialogBox.setWidget(view.asWidget());
		dialogBox.center();
	}

	@Override
	public void prepare(final AddFormView view) {
		if (id != null) {
			AppConstants.employeeService.getEmployee(id, new AsyncCallback<EmployeeBean>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(AppConstants.messages.serverError());
				}

				@Override
				public void onSuccess(EmployeeBean result) {
					if (result != null) {
						view.prepare(result);
					} else {
						Window.alert(AppConstants.messages.noFind());
					}
				}
			});
		} else {
			Window.alert(AppConstants.messages.noFind());
		}
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
