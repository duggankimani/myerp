package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class ComboView extends ViewImpl implements BaseView, ComboPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, ComboView> {
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
	@Ignore
	ListStore<LookupValue> store;
	
	@UiField(provided=true)
	@Ignore
	ComboBox<LookupValue> component;
	
	@Inject
	public ComboView(final Binder binder) {

		store = new ListStore<LookupValue>(GWT.<LookupProperties>create(LookupProperties.class).key2());
		component = new ComboBox<LookupValue>(store, labelProvider);
		widget = binder.createAndBindUi(this);
	}

	public void init(FieldModel field){
	 	
		store.clear();
		
		if(field.getLookupValues()!=null)
			store.addAll(field.getLookupValues());	
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
}
