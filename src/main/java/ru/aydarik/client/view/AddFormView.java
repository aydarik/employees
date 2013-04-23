package ru.aydarik.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import ru.aydarik.shared.EmployeeBean;

/**
 * User: agumerbaev
 */
public interface AddFormView extends IsWidget {

	void setPresenter(Presenter presenter);

	void prepare(EmployeeBean employeeBean);

	public interface Presenter {

		void prepare(AddFormView view);

		void goTo(Place place);
	}
}
