package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class ClearFieldsEvent extends
		GwtEvent<ClearFieldsEvent.ClearFieldsHandler> {

	public static Type<ClearFieldsHandler> TYPE = new Type<ClearFieldsHandler>();
	private Integer WindowId;
	private Integer tabNo;

	public interface ClearFieldsHandler extends EventHandler {
		void onClearFields(ClearFieldsEvent event);
	}

	public ClearFieldsEvent(Integer WindowId, Integer tabNo) {
		this.WindowId = WindowId;
		this.tabNo = tabNo;
	}

	public Integer getWindowId() {
		return WindowId;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	@Override
	protected void dispatch(ClearFieldsHandler handler) {
		handler.onClearFields(this);
	}

	@Override
	public Type<ClearFieldsHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ClearFieldsHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer WindowId, Integer tabNo) {
		source.fireEvent(new ClearFieldsEvent(WindowId, tabNo));
	}
}
