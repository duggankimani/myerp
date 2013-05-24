package com.duggankimani.app.client.components;

import com.duggankimani.app.client.images.ICONS;
import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

public class NumberFieldView extends ViewImpl implements BaseView,
		NumberFieldPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, NumberFieldView> {
	}

	@UiField
	NumberField<Number> component;
	
	@UiField HorizontalPanel container;

	@UiField
	FieldLabel label;

	@UiField(provided = true)
	NumberPropertyEditor<?> propertyEditor = new NumberPropertyEditor.DoublePropertyEditor();

	@UiField(provided = true)
	NumberFormat numberFormat = NumberFormat.getFormat("0.00");
	
	@UiField Image imgMandatory;

	private final Binder binder;
	
	@Inject
	public NumberFieldView(final Binder binder) {
		this.binder = binder;		
	}

	public void init(FieldModel field){
		switch (field.getDisplayType()) {
		case INTEGER:
		case QUANTITY:	
		case ID:
		case ROWID:
			propertyEditor = new NumberPropertyEditor.IntegerPropertyEditor();
			numberFormat = NumberFormat.getFormat("0");
			break;
		case AMOUNT:
		case COSTPRICE: 
			propertyEditor = new NumberPropertyEditor.BigDecimalPropertyEditor();
			numberFormat = NumberFormat.getFormat("0.00 Ksh");
			break;
		case NUMBER:
			propertyEditor = new NumberPropertyEditor.BigDecimalPropertyEditor();
			numberFormat = NumberFormat.getFormat("0.00");
			break;
		default:
			break;
		}
		
		widget = binder.createAndBindUi(this);
		imgMandatory.setResource(ICONS.INSTANCE.mandatory());
		imgMandatory.setTitle("Mandatory Field");
		
	}
	
	@Override
	public Widget asWidget() {
		
		return widget;
	}

	@Override
	public void setName(String name) {
		label.setText(name);
		//component.setTitle(name);
	}

	@Override
	public int getColSpan() {

		return 0;
	}

	@Override
	public void setColSpan(int colSpan) {

	}
	
	public HorizontalPanel getContainer() {
		return container;
	}

	@Override
	public void setValue(Number value) {
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
	
	public NumberField<Number> getComponent(){
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
