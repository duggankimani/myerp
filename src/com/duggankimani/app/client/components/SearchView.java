package com.duggankimani.app.client.components;

import java.util.List;

import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class SearchView extends ViewImpl implements SearchPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, SearchView> {
	}

	interface LookupProperties extends PropertyAccess<LookupValue>{
		ModelKeyProvider<LookupValue> key2();
		
		LabelProvider<LookupValue> value();
	}

	@UiField
	FieldLabel label;
	
	@UiField(provided=true)
	LabelProvider<LookupValue> labelProvider = GWT.<LookupProperties>create(LookupProperties.class).value();
	
	@UiField(provided=true)
	ListStore<LookupValue> store;
	
	@UiField(provided=true)
	final ComboBox<LookupValue> component;
	
	@UiField HorizontalPanel container;
	
	@Inject
	public SearchView(final Binder binder) {
		
		store = new ListStore<LookupValue>(GWT.<LookupProperties>create(LookupProperties.class).key2());
		component = new ComboBox<LookupValue>(store, labelProvider);
		
		component.setTypeAhead(true);
		component.setHideTrigger(true);
		component.setEmptyText(getEmptyText(""));
		
		widget = binder.createAndBindUi(this);
	}

	/**
	 * Called only if the field is generated
	 * from FieldModels
	 */
	public void init(FieldModel field){
		store.clear();
		component.setEmptyText(getEmptyText(field.getColumnName()));
		if(field.getLookupValues()!=null)
			store.addAll(field.getLookupValues());	
	}
	
	private String getEmptyText(String columnName) {
		columnName = columnName.toUpperCase();
		
		String text="Type name or code....";
		
		return text;
	}

	@Override
	public Widget asWidget() {
	
		return widget;
	}
	
	@Override
	public void setName(String name) {
		label.setText(name);
	}
	
	@Override
	public int getColSpan() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setColSpan(int colSpan) {
		// TODO Auto-generated method stub
		
	}
	
	public HorizontalPanel getContainer() {
		return container;
	}

	@Override
	public void setValue(Integer key) {

		setValue(key+"");		
	}

	@Override
	public void setValue(String key) {
		LookupValue val = store.findModelWithKey(key);
		
		component.setValue(val);
	}
	
	@Override
	public void setValue(LookupValue value) {
		store.add(value);
		
		component.setValue(value);
	}
	
	@Override
	public void setDescription(String description) {
		component.setTitle(description);
	}
	
	@Override
	public void clearData() {
		component.clear();
	}
	
	public ComboBox<LookupValue> getComponent(){
		return component;
	}

	@Override
	public void loadList(List<LookupValue> items) {
		store.clear();
		
		store.addAll(items);
		
	}
	
}
