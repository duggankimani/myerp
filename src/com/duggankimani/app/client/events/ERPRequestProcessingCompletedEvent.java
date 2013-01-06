package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class ERPRequestProcessingCompletedEvent
		extends
		GwtEvent<ERPRequestProcessingCompletedEvent.ERPRequestProcessingCompletedHandler> {

	public static Type<ERPRequestProcessingCompletedHandler> TYPE = new Type<ERPRequestProcessingCompletedHandler>();

	public interface ERPRequestProcessingCompletedHandler extends EventHandler {
		void onERPRequestProcessingCompleted(
				ERPRequestProcessingCompletedEvent event);
	}

	public ERPRequestProcessingCompletedEvent() {
	}

	@Override
	protected void dispatch(ERPRequestProcessingCompletedHandler handler) {
		handler.onERPRequestProcessingCompleted(this);
	}

	@Override
	public Type<ERPRequestProcessingCompletedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ERPRequestProcessingCompletedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new ERPRequestProcessingCompletedEvent());
	}
}
