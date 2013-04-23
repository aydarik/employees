package ru.aydarik.client.view.grid;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SingleSelectionModel;
import ru.aydarik.client.AppConstants;
import ru.aydarik.client.place.actions.AddPlace;
import ru.aydarik.client.place.actions.DeletePlace;
import ru.aydarik.client.place.actions.EditPlace;
import ru.aydarik.client.view.GridView;
import ru.aydarik.shared.EmployeeBean;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: agumerbaev
 */
public class GridViewImpl extends Composite implements GridView {

    private static GridViewUiBinder uiBinder = GWT.create(GridViewUiBinder.class);

    interface GridViewUiBinder extends UiBinder<Widget, GridViewImpl> {

    }

    @UiField
    CellTable<EmployeeBean> table;

    @UiField
    SimplePager pager;

    @UiField
    PushButton addButton;

    @UiField
    PushButton editButton;

    @UiField
    PushButton deleteButton;

    private Presenter presenter;

    private AsyncDataProvider<EmployeeBean> provider;

    public GridViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        // Name column
        TextColumn<EmployeeBean> nameColumn = new TextColumn<EmployeeBean>() {
            @Override
            public String getValue(EmployeeBean employee) {
                return employee.toString();
            }
        };
        table.addColumn(nameColumn, AppConstants.messages.empName());

        // Birthday column
        DateCell dateCell = new DateCell(DateTimeFormat.getFormat(AppConstants.messages.dateFormat()),
                SimpleSafeHtmlRenderer.getInstance(), null);
        Column<EmployeeBean, Date> dateColumn = new Column<EmployeeBean, Date>(dateCell) {
            @Override
            public Date getValue(EmployeeBean employee) {
                return employee.getBdate();
            }
        };
        table.addColumn(dateColumn, AppConstants.messages.empBdate());


        // Selection model
        final SingleSelectionModel<EmployeeBean> selectionModel = new SingleSelectionModel<EmployeeBean>();
        table.setSelectionModel(selectionModel);

        // Data provider
        provider = new AsyncDataProvider<EmployeeBean>() {
            @Override
            protected void onRangeChanged(final HasData<EmployeeBean> display) {
                AppConstants.employeeService
                        .getEmployees(display.getVisibleRange().getStart(), display.getVisibleRange().getLength(),
                                new AsyncCallback<List<EmployeeBean>>() {
                                    public void onFailure(Throwable throwable) {
                                        updateRowData(0, Collections.<EmployeeBean>emptyList());
                                        updateRowCount(0, true);
                                        Window.alert(AppConstants.messages.serverError());
                                    }

                                    public void onSuccess(List<EmployeeBean> employeeBeans) {
                                        updateRowData(display.getVisibleRange().getStart(), employeeBeans);
                                        AppConstants.employeeService.getSize(new AsyncCallback<Number>() {
                                            @Override
                                            public void onFailure(Throwable caught) {
                                                Window.alert(AppConstants.messages.serverError());
                                            }

                                            @Override
                                            public void onSuccess(Number result) {
                                                updateRowCount(result.intValue(), true);
                                            }
                                        });
                                    }
                                });
            }
        };
        provider.addDataDisplay(table);

        // Pager
        pager.setDisplay(table);

        // Buttons
        buildButtons(selectionModel);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public AsyncDataProvider<EmployeeBean> getDataProvider() {
        return provider;
    }

    @Override
    public int getPageSize() {
        return table.getPageSize();
    }

    private void buildButtons(final SingleSelectionModel<EmployeeBean> selectionModel) {
        addButton.getUpFace().setImage(new Image(AppConstants.resources.add()));
        editButton.getUpFace().setImage(new Image(AppConstants.resources.edit()));
        deleteButton.getUpFace().setImage(new Image(AppConstants.resources.delete()));

        addButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                presenter.goTo(new AddPlace());
            }
        });

        editButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                if (selectionModel.getSelectedObject() == null) {
                    Window.alert(AppConstants.messages.noSelect());
                    return;
                }
                presenter.goTo(new EditPlace(selectionModel.getSelectedObject().getId().toString()));
            }
        });

        deleteButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                if (selectionModel.getSelectedObject() == null) {
                    Window.alert(AppConstants.messages.noSelect());
                    return;
                }
                presenter.goTo(new DeletePlace(selectionModel.getSelectedObject().getId().toString()));
            }
        });
    }
}