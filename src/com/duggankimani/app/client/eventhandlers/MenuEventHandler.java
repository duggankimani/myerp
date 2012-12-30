package com.duggankimani.app.client.eventhandlers;

import com.duggankimani.app.client.place.NameTokens;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MenuEventHandler implements SelectionHandler<Item> {

	PlaceManager placeManager;
	
	public MenuEventHandler(PlaceManager placeManager){
		this.placeManager = placeManager;
	}
	
	@Override
	public void onSelection(SelectionEvent<Item> event) {

		Item item = event.getSelectedItem();
		
		PlaceRequest request = new PlaceRequest(NameTokens.inputfrm);
		request = request.with("AD_Menu_ID", item.getId());
		placeManager.revealPlace(request);
	}	
}
