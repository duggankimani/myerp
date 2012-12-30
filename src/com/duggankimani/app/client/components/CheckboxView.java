package com.duggankimani.app.client.components;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class CheckboxView extends ViewImpl implements BaseView, CheckboxPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, CheckboxView> {
	}

	@UiField
	CheckBox component;
	
	@Inject
	public CheckboxView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setName(String name) {
		component.setBoxLabel(name);
	}

	@Override
	public int getColSpan() {
		return 0;
	}

	@Override
	public void setColSpan(int colSpan) {
		// TODO Auto-generated method stub
		
	}
}
