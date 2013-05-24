package com.duggankimani.app.client.events;

import com.duggankimani.app.client.place.NameTokens;
import com.duggankimani.app.shared.model.MenuFolder;
import com.duggankimani.app.shared.model.MenuType;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.sencha.gxt.widget.core.client.menu.Item;

public class MenuSelectEventHandler implements SelectionHandler<Item> {

	PlaceManager placeManager;
	MenuFolder folder;
	public MenuSelectEventHandler(PlaceManager manager, MenuFolder folder){
		this.placeManager=manager;
		this.folder=folder;
	}
	
	@Override
	public void onSelection(SelectionEvent<Item> event) {
		Item item = event.getSelectedItem();
		
			PlaceRequest request = new PlaceRequest(NameTokens.inputfrm);
			request = request.with("m", item.getId());		
			placeManager.revealPlace(request);
	}	
}
