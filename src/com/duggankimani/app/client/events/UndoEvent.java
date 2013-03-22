package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class UndoEvent extends GwtEvent<UndoEvent.UndoHandler> {

	public static Type<UndoHandler> TYPE = new Type<UndoHandler>();

	public interface UndoHandler extends EventHandler {
		void onUndo(UndoEvent event);
	}

	public UndoEvent() {
	}

	@Override
	protected void dispatch(UndoHandler handler) {
		handler.onUndo(this);
	}

	@Override
	public Type<UndoHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<UndoHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new UndoEvent());
	}
}
