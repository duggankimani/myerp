package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.gwtplatform.mvp.client.Presenter;
import com.google.gwt.event.shared.HasHandlers;

public class CreateEvent extends GwtEvent<CreateEvent.CreateHandler> {

	public static Type<CreateHandler> TYPE = new Type<CreateHandler>();
	
	public interface CreateHandler extends EventHandler {
		void onCreate(CreateEvent event);
	}

	public CreateEvent() {
	}

	@Override
	protected void dispatch(CreateHandler handler) {
		handler.onCreate(this);
	}

	@Override
	public Type<CreateHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<CreateHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new CreateEvent());
	}
}
