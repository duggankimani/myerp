package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class WindowChangedEvent extends
		GwtEvent<WindowChangedEvent.WindowChangedHandler> {

	public static Type<WindowChangedHandler> TYPE = new Type<WindowChangedHandler>();

	public interface WindowChangedHandler extends EventHandler {
		void onWindowChanged(WindowChangedEvent event);
	}

	public WindowChangedEvent() {
	}

	@Override
	protected void dispatch(WindowChangedHandler handler) {
		handler.onWindowChanged(this);
	}

	@Override
	public Type<WindowChangedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<WindowChangedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new WindowChangedEvent());
	}
}
