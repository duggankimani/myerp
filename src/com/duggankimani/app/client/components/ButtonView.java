package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ButtonView extends ViewImpl implements BaseView,ButtonPresenter.MyView {

	private final Widget widget;
	
	public interface Binder extends UiBinder<Widget, ButtonView> {
	}

	@UiField Anchor component;
	
	@Inject
	public ButtonView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	public void init(FieldModel field){
		setName(field.getName());
		component.setHref("#test");
	}
	
	
	public void setName(String name){
		component.setText(name);
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
}
