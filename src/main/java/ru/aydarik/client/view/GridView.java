package ru.aydarik.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.AsyncDataProvider;
import ru.aydarik.shared.EmployeeBean;

/**
 * User: agumerbaev
 */
public interface GridView extends IsWidget {

	void setPresenter(Presenter presenter);

	AsyncDataProvider<EmployeeBean> getDataProvider();

	int getPageSize();

	public interface Presenter {

		void reload(GridView view);

		void goTo(Place place);
	}
}
