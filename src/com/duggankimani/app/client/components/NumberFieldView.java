package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

	@UiField
	FieldLabel label;

	@UiField(provided = true)
	NumberPropertyEditor<?> propertyEditor = new NumberPropertyEditor.DoublePropertyEditor();

	@UiField(provided = true)
	NumberFormat numberFormat = NumberFormat.getFormat("0.00");

	private final Binder binder;
	
	@Inject
	public NumberFieldView(final Binder binder) {
		this.binder = binder;		
	}

	public void init(FieldModel field){
		switch (field.getDisplayType()) {
		case INTEGER:
		case QUANTITY:	
			propertyEditor = new NumberPropertyEditor.IntegerPropertyEditor();
			numberFormat = NumberFormat.getFormat("0");
			break;

		case AMOUNT:
			propertyEditor = new NumberPropertyEditor.DoublePropertyEditor();
			numberFormat = NumberFormat.getFormat("0.00 Ksh");
			break;
		default:
			break;
		}
		
		widget = binder.createAndBindUi(this);
		
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

}
