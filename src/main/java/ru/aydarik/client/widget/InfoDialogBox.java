package ru.aydarik.client.widget;

import com.google.gwt.user.client.ui.DialogBox;
import ru.aydarik.client.AppConstants;

/**
 * User: agumerbaev
 */
public class InfoDialogBox extends DialogBox {

	private static InfoDialogBox instance;

	public static InfoDialogBox getInstance() {
		if (instance == null) {
			instance = new InfoDialogBox();
		}
		return instance;
	}

	private InfoDialogBox() {
		super(false, true);
		setWidth("400px");
		getCaption().setText(AppConstants.messages.empInfo());
	}
}
