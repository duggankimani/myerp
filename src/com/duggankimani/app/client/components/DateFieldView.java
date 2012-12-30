package com.duggankimani.app.client.components;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class DateFieldView extends ViewImpl implements
		DateFieldPresenter.MyView {

	private final Widget widget;

	@UiField FieldLabel label;
	
	@UiField DateField component;
	
	int colSpan=1;
	
	public interface Binder extends UiBinder<Widget, DateFieldView> {
	}

	@Inject
	public DateFieldView(final Binder binder) {
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
}
