package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class NavigateEvent extends GwtEvent<NavigateEvent.NavigateHandler> {

	public static Type<NavigateHandler> TYPE = new Type<NavigateHandler>();
	private Integer rows;
	
	public interface NavigateHandler extends EventHandler {
		void onNavigate(NavigateEvent event);
	}

	public NavigateEvent(Integer rows) {
		this.rows = rows;
	}

	public Integer getRows() {
		return rows;
	}

	@Override
	protected void dispatch(NavigateHandler handler) {
		handler.onNavigate(this);
	}

	@Override
	public Type<NavigateHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<NavigateHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer direction) {
		source.fireEvent(new NavigateEvent(direction));
	}
}
