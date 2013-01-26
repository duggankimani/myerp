package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class LoadWindowEvent extends
		GwtEvent<LoadWindowEvent.LoadWindowHandler> {

	public static Type<LoadWindowHandler> TYPE = new Type<LoadWindowHandler>();
	private Integer tabNo;
	private String name;
	private Integer windowId;

	public interface LoadWindowHandler extends EventHandler {
		void onLoadWindow(LoadWindowEvent event);
	}

	public LoadWindowEvent(Integer tabNo, Integer windowId, String name) {
		this.tabNo = tabNo;
		this.name = name;
		this.windowId = windowId;
	}

	public Integer getTabNo() {
		return tabNo;
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

	public static void fire(HasHandlers source, Integer tabNo, Integer windowId, String name) {
		source.fireEvent(new LoadWindowEvent(tabNo, windowId, name));
	}

	public String getName() {
		return name;
	}

	public Integer getWindowId() {
		return windowId;
	}
}
