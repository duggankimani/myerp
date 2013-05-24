package com.duggankimani.app.client.components;

import com.duggankimani.app.client.images.ICONS;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TextFieldView extends ViewImpl implements
		TextFieldPresenter.MyView {

	private final Widget widget;
	
	public interface Binder extends UiBinder<Widget, TextFieldView> {
	}


	@UiField FieldLabel label;
	
	@UiField TextField component;
	
	@UiField HorizontalPanel container;
	
	@UiField Image imgMandatory;
	
	int colSpan=1;
	
	@Inject
	public TextFieldView(final Binder binder) {
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
	public void setValue(String value) {
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

	public TextField getComponent(){
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
