package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.Proxy;

public class PopUpCloseEvent extends
		GwtEvent<PopUpCloseEvent.PopUpCloseHandler> {

	public static Type<PopUpCloseHandler> TYPE = new Type<PopUpCloseHandler>();

	public interface PopUpCloseHandler extends EventHandler {
		void onPopUpClose(PopUpCloseEvent event);
	}
	
	Presenter<View, Proxy<?>> presenter;

	PopUpCloseEvent(){
		
	}
	
	public PopUpCloseEvent(Presenter<View, Proxy<?>> presenter) {
		this.presenter = presenter;
	}

	@Override
	protected void dispatch(PopUpCloseHandler handler) {
		handler.onPopUpClose(this);
	}

	@Override
	public Type<PopUpCloseHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<PopUpCloseHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new PopUpCloseEvent());
	}
}
