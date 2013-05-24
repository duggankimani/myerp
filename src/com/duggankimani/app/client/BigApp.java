package com.duggankimani.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.duggankimani.app.client.gin.ClientGinjector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class BigApp implements EntryPoint {

	private static final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

	@Override
	public void onModuleLoad() {
		// This is required for Gwt-Platform proxy's generator
		DelayedBindRegistry.bind(ginjector);

		ginjector.getPlaceManager().revealCurrentPlace();
	}
	
	public static EventBus getEventBus(){
		return ginjector.getEventBus();
	}
}
