package com.duggankimani.app.client.core;

import com.duggankimani.app.client.components.menu.MenuPresenter;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent.ERPRequestProcessingCompletedHandler;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent.ERPRequestProcessingHandler;
import com.duggankimani.app.client.place.NameTokens;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Anchor;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPagePresenter extends
		Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> 
			implements ERPRequestProcessingHandler, ERPRequestProcessingCompletedHandler{

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
	MenuPresenter menu;
	
	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
		eventBus.addHandler(ERPRequestProcessingCompletedEvent.TYPE, this);
		eventBus.addHandler(ERPRequestProcessingEvent.TYPE, this);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	
	@Override
	protected void onBind() {
		super.onBind();
		
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
	
}
