package com.duggankimani.app.client.components;

import java.util.Date;

import com.duggankimani.app.client.images.ICONS;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class DateFieldView extends ViewImpl implements
		DateFieldPresenter.MyView {

	private final Widget widget;
	
	public interface Binder extends UiBinder<Widget, DateFieldView> {
	}


	@UiField FieldLabel label;
	
	@UiField DateField component;
	
	@UiField HorizontalPanel container;
	
	@UiField Image imgMandatory;

	int colSpan=1;
	
	@Inject
	public DateFieldView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		component.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy"));
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
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}
	
	public HorizontalPanel getContainer() {
		return container;
	}

	@Override
	public void setValue(Date value) {
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
	
	public DateField getComponent(){
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
