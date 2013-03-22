package com.duggankimani.app.client.core;

import com.duggankimani.app.client.components.menu.ApplicationMenuPresenter;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent.ERPRequestProcessingCompletedHandler;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent.ERPRequestProcessingHandler;
import com.duggankimani.app.client.events.LoadWindowEvent;
import com.duggankimani.app.client.events.LoadWindowEvent.LoadWindowHandler;
import com.duggankimani.app.client.events.PopUpCloseEvent;
import com.duggankimani.app.client.events.PopUpCloseEvent.PopUpCloseHandler;
import com.duggankimani.app.client.place.NameTokens;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Anchor;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPagePresenter extends
		Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> 
			implements ERPRequestProcessingHandler, ERPRequestProcessingCompletedHandler, 
			LoadWindowHandler, PopUpCloseHandler{

	public interface MyView extends View {
		public Anchor getAnchorHome();
		public Anchor getCreateNew();
		public void showLoadingMessage(String message);
		public void hideLoadingMessage();
	}

	@ProxyCodeSplit
	public interface MyProxy extends Proxy<MainPagePresenter> {
	}

	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> MENU_SLOT = new Type<RevealContentHandler<?>>();
	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_content = new Type<RevealContentHandler<?>>();

	@Inject
	PlaceManager placeManager;
	
	@Inject
	ApplicationMenuPresenter menu;
	
	@Inject DispatchAsync dispatchAsync;
	
	IndirectProvider<PopupFormPresenter> popPresenterFactory;
	
	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, Provider<PopupFormPresenter> popupProvider) {
		super(eventBus, view, proxy);
		popPresenterFactory = new StandardProvider<PopupFormPresenter>(popupProvider);

	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	
	@Override
	protected void onBind() {
		super.onBind();
		
		addRegisteredHandler(ERPRequestProcessingCompletedEvent.TYPE, this);
		addRegisteredHandler(ERPRequestProcessingEvent.TYPE, this);
		addRegisteredHandler(LoadWindowEvent.TYPE, this);
		
		getView().getAnchorHome().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.financeworkbench);
				
				placeManager.revealPlace(request);
								
			}
		});
		
		getView().getCreateNew().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
//				PlaceRequest request = new PlaceRequest(NameTokens.createinvoice);
//				placeManager.revealPlace(request);				
			}
		});
	}
	
	@Override
	protected void onReset() {
		super.onReset();
		this.setInSlot(MENU_SLOT, menu);
	}

	@Override
	public void onERPRequestProcessing(ERPRequestProcessingEvent event) {
		getView().showLoadingMessage(event.getDescription());		
	}

	@Override
	public void onERPRequestProcessingCompleted(
			ERPRequestProcessingCompletedEvent event) {
		getView().hideLoadingMessage();
	}

	@Inject PopupFormPresenter popupPresenter;
	
	@Override
	public void onLoadWindow(final LoadWindowEvent event) {
		popupPresenter.setAction(event.getAction());
		addToPopupSlot(popupPresenter, true);
	}

	@Override
	public void onPopUpClose(PopUpCloseEvent event) {
		//popPresenter.getView().hide();
	}
}
