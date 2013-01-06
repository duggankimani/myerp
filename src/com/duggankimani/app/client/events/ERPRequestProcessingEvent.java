package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.String;
import com.google.gwt.event.shared.HasHandlers;

public class ERPRequestProcessingEvent extends
		GwtEvent<ERPRequestProcessingEvent.ERPRequestProcessingHandler> {

	public static Type<ERPRequestProcessingHandler> TYPE = new Type<ERPRequestProcessingHandler>();
	private String description;

	public interface ERPRequestProcessingHandler extends EventHandler {
		void onERPRequestProcessing(ERPRequestProcessingEvent event);
	}

	public ERPRequestProcessingEvent(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	protected void dispatch(ERPRequestProcessingHandler handler) {
		handler.onERPRequestProcessing(this);
	}

	@Override
	public Type<ERPRequestProcessingHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ERPRequestProcessingHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, String description) {
		source.fireEvent(new ERPRequestProcessingEvent(description));
	}
}
