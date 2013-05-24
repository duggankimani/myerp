package com.duggankimani.app.client.core;

import com.duggankimani.app.client.events.RepositionPopupEvent;
import com.duggankimani.app.client.events.RepositionPopupEvent.RepositionPopupHandler;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetWindowAction;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Anchor;

public class PopupFormPresenter extends
		PresenterWidget<PopupFormPresenter.IPopupView> implements RepositionPopupHandler{

	public interface IPopupView extends PopupView {
		Anchor getCloseButton();
	}

	@Inject DispatchAsync dispatchAsync;
	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> FORM_SLOT = new Type<RevealContentHandler<?>>();
	
	IndirectProvider<FormPresenter> formPresenterFactory;
	
	@Inject
	public PopupFormPresenter(final EventBus eventBus, final IPopupView view, Provider<FormPresenter> frmProvider) {
		super(eventBus, view);
		
		formPresenterFactory = new StandardProvider<FormPresenter>(frmProvider);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		addRegisteredHandler(RepositionPopupEvent.TYPE, this);
		
		getView().getCloseButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PopupFormPresenter.this.getView().hide();				
			}
		});
	}

		
	@Override
	protected void onReveal() {
		super.onReveal();
		formPresenterFactory.get(new ERPAsyncCallback<FormPresenter>() {
			@Override
			public void processResult(FormPresenter result) {
				result.setAction(requestWindowAction);
				result.setViewMode(1);
				PopupFormPresenter.this.setInSlot(FORM_SLOT, result);
			}
		});
	}
	
	@Override
	protected void onReset() {
		super.onReset();
	}

	GetWindowAction requestWindowAction;
	public void setAction(GetWindowAction action) {
		this.requestWindowAction = action;
	}
	
	@Override
	public void onRepositionPopup(RepositionPopupEvent event) {
		getView().center();
	}
}
