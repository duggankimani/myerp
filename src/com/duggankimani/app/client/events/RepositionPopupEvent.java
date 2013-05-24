package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class RepositionPopupEvent extends
		GwtEvent<RepositionPopupEvent.RepositionPopupHandler> {

	public static Type<RepositionPopupHandler> TYPE = new Type<RepositionPopupHandler>();

	public interface RepositionPopupHandler extends EventHandler {
		void onRepositionPopup(RepositionPopupEvent event);
	}

	public RepositionPopupEvent() {
	}

	@Override
	protected void dispatch(RepositionPopupHandler handler) {
		handler.onRepositionPopup(this);
	}

	@Override
	public Type<RepositionPopupHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<RepositionPopupHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new RepositionPopupEvent());
	}
}
