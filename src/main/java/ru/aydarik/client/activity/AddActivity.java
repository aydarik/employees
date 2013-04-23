package ru.aydarik.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import ru.aydarik.client.ClientFactory;
import ru.aydarik.client.view.AddFormView;
import ru.aydarik.client.widget.InfoDialogBox;
import ru.aydarik.shared.EmployeeBean;

/**
 * User: agumerbaev
 */
public class AddActivity extends AbstractActivity implements AddFormView.Presenter {

	private ClientFactory clientFactory;

	public AddActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
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
	public void prepare(AddFormView view) {
		view.prepare(new EmployeeBean());
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}