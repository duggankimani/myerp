package com.duggankimani.app.client.core;

import com.gwtplatform.mvp.client.PopupViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class PopupFormView extends PopupViewImpl implements
		PopupFormPresenter.IPopupView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, PopupFormView> {
	}

	@UiField HTMLPanel container;
	
	@UiField Anchor aClose;
	
	@Inject
	public PopupFormView(final EventBus eventBus, final Binder binder) {
		super(eventBus);
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		if(slot == PopupFormPresenter.FORM_SLOT){
			container.clear();
			
			if(content!=null){
				container.add(content);
			}
		}else{
			super.setInSlot(slot, content);
		}
	}
	
	public Anchor getCloseButton(){
		return aClose;
	}
}
