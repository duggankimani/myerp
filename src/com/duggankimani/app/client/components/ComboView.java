package com.duggankimani.app.client.components;

import com.duggankimani.app.client.images.ICONS;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;
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
	
	@UiField HorizontalPanel container;

	@UiField Image imgMandatory;
	
	@Inject
	public ComboView(final Binder binder) {

		store = new ListStore<LookupValue>(GWT.<LookupProperties>create(LookupProperties.class).key2());
		component = new ComboBox<LookupValue>(store, labelProvider);
		widget = binder.createAndBindUi(this);
		imgMandatory.setResource(ICONS.INSTANCE.mandatory());
		imgMandatory.setTitle("Mandatory Field");
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
		//System.out.println("Val - "+key);
		component.setValue(val);
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
	public void setEditable(boolean isEditable) {
		component.setReadOnly(!isEditable);
	}
	
	@Override
	public void setMandatory(boolean isMandatory) {
		imgMandatory.setVisible(isMandatory);
	}
	
	@Override
	public void setVisible(boolean isVisible) {
		UIObject.setVisible(container.getElement(), isVisible);
	}

}
