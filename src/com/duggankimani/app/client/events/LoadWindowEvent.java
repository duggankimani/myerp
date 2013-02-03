package com.duggankimani.app.client.events;

import com.duggankimani.app.shared.model.TabModel;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class LoadWindowEvent extends
		GwtEvent<LoadWindowEvent.LoadWindowHandler> {

	public static Type<LoadWindowHandler> TYPE = new Type<LoadWindowHandler>();
	private TabModel tab;
	
	public interface LoadWindowHandler extends EventHandler {
		void onLoadWindow(LoadWindowEvent event);
	}

	public LoadWindowEvent(TabModel tab) {
		this.tab = tab;
	}

	@Override
	protected void dispatch(LoadWindowHandler handler) {
		handler.onLoadWindow(this);
	}

	@Override
	public Type<LoadWindowHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<LoadWindowHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new LoadWindowEvent(null));
	}

	public TabModel getTab() {
		return tab;
	}

	public void setTab(TabModel tab) {
		this.tab = tab;
	}
}
