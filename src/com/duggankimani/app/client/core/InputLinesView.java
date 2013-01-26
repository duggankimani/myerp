package com.duggankimani.app.client.core;

import java.io.Serializable;
import java.util.ArrayList;

import com.duggankimani.app.client.provider.ERPPropertyAccessProvider;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.DisplayType;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.TabModel;
import com.gwtplatform.mvp.client.ViewImpl;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.info.Info;

public class InputLinesView extends ViewImpl implements
		InputLinesPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, InputLinesView> {
	}

	ArrayList<ColumnConfig<DataModel, ?>> list = new ArrayList<ColumnConfig<DataModel, ?>>();
	
	ColumnModel<DataModel> cm = new ColumnModel<DataModel>(list);
	
	ERPPropertyAccessProvider provider = new ERPPropertyAccessProvider();
	
	ListStore<DataModel> store =new ListStore<DataModel>(provider.getKeyProvider("ID"));;

	@UiField
	Grid<DataModel> grid;

	GridView<DataModel> gridView = new GridView<DataModel>();

	TabModel tab;
	
	private final Binder binder;

	RowNumberer<DataModel> rowNumberer = null;
	@Inject
	public InputLinesView(final Binder binder) {
		this.binder = binder;		
	}

	public void bind(TabModel tabModel) {
		this.tab = tabModel;
		list.clear();
		
		rowNumberer = new RowNumberer<DataModel>(new IdentityValueProvider<DataModel>());
		list.add(rowNumberer);
		
		ArrayList<FieldModel> fields = tab.getFields();
		
		String keyColumnName = tabModel.getKeyColumnName();
	
		for (FieldModel model : fields) {
			
			ColumnConfig<DataModel, Serializable> column=null;
			
			if(model.getDisplayType()==DisplayType.DATE || model.getDisplayType()==DisplayType.DATETIME){
				
				column = getDateColumn(model);
				
			}else{				
				column = new ColumnConfig<DataModel, Serializable>(
					provider.getValueProvider(model.getColumnName()),
					model.getWidth(), model.getName());
			}
			
			list.add(column);
		}
		
		
		store = new ListStore<DataModel>(provider.getKeyProvider(keyColumnName));
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
		grid = new Grid<DataModel>(store, cm, gridView);
		grid.getView().setStripeRows(true);		

		return grid;
	}

	@Override
	public void bindData(ArrayList<DataModel> dataModel) {
		store.clear();
		store.addAll(dataModel);
	}
	
	@SuppressWarnings("unchecked")
	private ColumnConfig<DataModel, Serializable> getDateColumn(FieldModel model) {
		
		ColumnConfig<DataModel, Serializable> column = new ColumnConfig<DataModel, Serializable>(
				provider.getValueProvider(model.getColumnName()),
				model.getWidth(), model.getName());

		@SuppressWarnings("rawtypes")
		Cell cell = new DateCell(DateTimeFormat.getFormat("dd-MM-yyyy"));
		
		column.setCell(cell);
		
		return column;
	}

	public Grid<DataModel> getGrid() {
		return grid;
	}
	
	

}
