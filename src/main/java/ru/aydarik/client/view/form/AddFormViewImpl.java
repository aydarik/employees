package ru.aydarik.client.view.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import ru.aydarik.client.AppConstants;
import ru.aydarik.client.place.GridPlace;
import ru.aydarik.client.view.AddFormView;
import ru.aydarik.shared.EmployeeBean;

/**
 * User: agumerbaev
 */
public class AddFormViewImpl extends Composite implements AddFormView {

	private static AddFormViewUiBinder uiBinder = GWT.create(AddFormViewUiBinder.class);

	interface AddFormViewUiBinder extends UiBinder<Widget, AddFormViewImpl> {

	}

	@UiField
	TextBox nameBox;

	@UiField
	TextBox surnameBox;

	@UiField
	DateBox bdateBox;

	@UiField
	PushButton okButton;

	@UiField
	PushButton cancelButton;

	private Presenter presenter;

	private EmployeeBean employeeBean;

	public AddFormViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		// Date format of employee's birthday box
		bdateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(AppConstants.messages.dateFormat())));

		// Building buttons
		buildButtons();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void prepare(EmployeeBean employeeBean) {
        this.employeeBean = employeeBean;

		nameBox.setText(employeeBean.getName());
		surnameBox.setText(employeeBean.getSurname());
		bdateBox.setValue(employeeBean.getBdate());

		if (employeeBean.getId() == null) {
			okButton.setText(AppConstants.messages.add());
		} else {
			okButton.setText(AppConstants.messages.edit());
		}

        nameBox.setFocus(true);
	}

	private void buildButtons() {
		okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (employeeBean == null) {
					Window.alert(AppConstants.messages.noEmployee());
					return;
				}

				if (verifyFields()) {
					employeeBean.setName(nameBox.getText());
					employeeBean.setSurname(surnameBox.getText());
					employeeBean.setBdate(bdateBox.getValue());

					AppConstants.employeeService.addEmployee(employeeBean, new AsyncCallback<Void>() {
						public void onFailure(Throwable caught) {
							Window.alert(AppConstants.messages.serverError());
						}

						public void onSuccess(Void result) {
							presenter.goTo(new GridPlace(AppConstants.YES));
						}
					});
				} else {
					Window.alert(AppConstants.messages.fieldEmpty());
				}
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.goTo(new GridPlace(AppConstants.NO));
			}
		});
	}

	private boolean verifyFields() {
		return !nameBox.getText().isEmpty() && !surnameBox.getText().isEmpty() && bdateBox.getValue() != null;
	}
}