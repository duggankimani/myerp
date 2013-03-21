package com.duggankimani.app.client.core;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class InputFormView extends ViewImpl implements InputFormPresenter.MyView {

	private final Widget widget;

	@UiField HTMLPanel container;
	
	public interface Binder extends UiBinder<Widget, InputFormView> {
	}

	@Inject
	public InputFormView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
		
	@Override
	public void setInSlot(Object slot, Widget content) {
		if(slot==InputFormPresenter.FORM_SLOT){
			container.clear();
			
			if(content!=null){
				container.add(content);
			}
		}
	}

}
