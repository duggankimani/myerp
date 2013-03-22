package com.duggankimani.app.client.components;

import java.util.Date;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
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

	int colSpan=1;
	
	@Inject
	public DateFieldView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		component.setPropertyEditor(new DateTimePropertyEditor("dd-MM-yyyy"));
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
	
}
