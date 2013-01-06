package com.duggankimani.app.client.components;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class IDView extends ViewImpl implements BaseView, IDPresenter.MyView {

	private Widget widget;

	public interface Binder extends UiBinder<Widget, IDView> {
	}

	@UiField
	Anchor anchor;
	
	@UiField HorizontalPanel container;
	
	@Inject
	public IDView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	
	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setName(String name) {
		anchor.setText(name);		
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
	
	public HorizontalPanel getContainer() {
		return container;
	}


	@Override
	public void setDescription(String description) {
		anchor.setTitle(description);
	}
}
