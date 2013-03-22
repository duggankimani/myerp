package com.duggankimani.app.client.components;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.CheckBox;

public class CheckboxView extends ViewImpl implements BaseView, CheckboxPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, CheckboxView> {
	}

	@UiField
	CheckBox component;
	
	@UiField HorizontalPanel container;

	
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
	
	public HorizontalPanel getContainer() {
		return container;
	}

	@Override
	public void setValue(Boolean value) {
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
