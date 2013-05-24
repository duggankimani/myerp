package com.duggankimani.app.client.events;

import com.duggankimani.app.shared.action.GetWindowAction;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class LoadWindowEvent extends
		GwtEvent<LoadWindowEvent.LoadWindowHandler> {

	public static Type<LoadWindowHandler> TYPE = new Type<LoadWindowHandler>();
	private GetWindowAction action;
	
	public interface LoadWindowHandler extends EventHandler {
		void onLoadWindow(LoadWindowEvent event);
	}

	public LoadWindowEvent(GetWindowAction action) {
		this.action = action;
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

	public GetWindowAction getAction() {
		return action;
	}

	public void setAction(GetWindowAction action) {
		this.action = action;
	}
}
