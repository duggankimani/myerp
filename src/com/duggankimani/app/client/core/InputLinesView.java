package com.duggankimani.app.client.core;

import java.io.Serializable;
import java.util.ArrayList;

import com.duggankimani.app.client.provider.ERPPropertyAccessProvider;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.DisplayType;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.TabModel;
import com.gwtplatform.mvp.client.ViewImpl;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

public class InputLinesView extends ViewImpl implements
		InputLinesPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, InputLinesView> {
	}

	ColumnModel<DataModel> cm;
	
	ListStore<DataModel> store;

	@UiField
	Grid<DataModel> grid;

	GridView<DataModel> gridView;

	ERPPropertyAccessProvider provider;
	
	TabModel tab;

	ArrayList<ColumnConfig<DataModel, ?>> list = new ArrayList<ColumnConfig<DataModel, ?>>();
	
	private final Binder binder;

	@Inject
	public InputLinesView(final Binder binder) {
		this.binder = binder;
		provider = new ERPPropertyAccessProvider();
		gridView = new GridView<DataModel>();

		cm = new ColumnModel<DataModel>(list);
		store = new ListStore<DataModel>(provider.getKeyProvider("ID"));

	}

	public void bind(TabModel tabModel) {
		this.tab = tabModel;
		
		ArrayList<FieldModel> fields = tab.getFields();
		
		FieldModel keyField = FieldModel.get("InvoiceID", "InvoiceID",
				DisplayType.ID, false, 100);

		for (FieldModel model : fields) {
			if(model.isKeyColumn()){
				keyField = model;
			}
			
			ColumnConfig<DataModel, Serializable> column = new ColumnConfig<DataModel, Serializable>(
					provider.getValueProvider(model.getColumnName()),
					model.getWidth(), model.getName());
			list.add(column);
		}
		
		cm = new ColumnModel<DataModel>(list);
		store = new ListStore<DataModel>(provider.getKeyProvider(keyField.getColumnName()));
		
		if(grid!=null)
			grid.reconfigure(store, cm);
	}

	@Override
	public Widget asWidget() {
		
		widget = binder.createAndBindUi(this);

		return widget;
	}

	@UiFactory
	Grid<DataModel> createGrid() {
		return new Grid<DataModel>(store, cm, gridView);
	}
}
