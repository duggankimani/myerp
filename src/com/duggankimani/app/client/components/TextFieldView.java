package com.duggankimani.app.client.components;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	
	int colSpan=1;
	
	@Inject
	public TextFieldView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		
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
	

}
