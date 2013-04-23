package ru.aydarik.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.AsyncDataProvider;
import ru.aydarik.client.AppConstants;
import ru.aydarik.client.ClientFactory;
import ru.aydarik.client.view.GridView;
import ru.aydarik.client.widget.InfoDialogBox;
import ru.aydarik.shared.EmployeeBean;

import java.util.Collections;
import java.util.List;

/**
 * User: agumerbaev
 */
public class ReloadActivity extends AbstractActivity implements GridView.Presenter {

	private ClientFactory clientFactory;
	private boolean needReload;

	public ReloadActivity(ClientFactory clientFactory, String needReload) {
		this.clientFactory = clientFactory;
		this.needReload = AppConstants.YES.equals(needReload);
	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		final GridView view = clientFactory.getGridView();
		view.setPresenter(this);
		container.setWidget(view.asWidget());

		InfoDialogBox.getInstance().hide();

		if (needReload) {
			reload(view);
		}
	}

	@Override
	public void reload(GridView view) {
		final AsyncDataProvider<EmployeeBean> provider = view.getDataProvider();
		AppConstants.employeeService.getEmployees(0, view.getPageSize(), new AsyncCallback<List<EmployeeBean>>() {
			public void onFailure(Throwable throwable) {
				provider.updateRowData(0, Collections.<EmployeeBean>emptyList());
				provider.updateRowCount(0, true);
				Window.alert(AppConstants.messages.serverError());
			}

			public void onSuccess(List<EmployeeBean> employeeBeans) {
				provider.updateRowData(0, employeeBeans);

				// Reload pager counter
				AppConstants.employeeService.getSize(new AsyncCallback<Number>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(AppConstants.messages.serverError());
					}

					@Override
					public void onSuccess(Number result) {
						provider.updateRowCount(result.intValue(), true);
					}
				});
			}
		});
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}