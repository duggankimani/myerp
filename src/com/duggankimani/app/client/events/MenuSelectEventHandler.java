package com.duggankimani.app.client.events;

import com.duggankimani.app.client.place.NameTokens;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.sencha.gxt.widget.core.client.menu.Item;

public class MenuSelectEventHandler implements SelectionHandler<Item> {

	PlaceManager placeManager;
	
	public MenuSelectEventHandler(PlaceManager placeManager){
		this.placeManager = placeManager;
	}
	
	@Override
	public void onSelection(SelectionEvent<Item> event) {

		Item item = event.getSelectedItem();
		
		PlaceRequest request = new PlaceRequest(NameTokens.inputfrm);
		
		//AD_Menu_ID=m
		//AD_Window_ID=w
		//TabNo=t
		//
		request = request.with("m", item.getId());
		placeManager.revealPlace(request);
	}	
}
