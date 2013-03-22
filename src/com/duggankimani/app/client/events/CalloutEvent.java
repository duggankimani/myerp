package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class CalloutEvent extends GwtEvent<CalloutEvent.CalloutHandler> {

	public static Type<CalloutHandler> TYPE = new Type<CalloutHandler>();
	private Integer windowId;
	private Integer tabNo;
	private String columnName;

	public interface CalloutHandler extends EventHandler {
		void onCallout(CalloutEvent event);
	}

	public CalloutEvent(Integer windowId, Integer tabNo, String columnName) {
		this.windowId = windowId;
		this.tabNo = tabNo;
		this.columnName=columnName;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	@Override
	protected void dispatch(CalloutHandler handler) {
		handler.onCallout(this);
	}

	@Override
	public Type<CalloutHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<CalloutHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer windowId, Integer tabNo, String columnName) {
		source.fireEvent(new CalloutEvent(windowId, tabNo, columnName));
	}

	public String getColumnName() {
		return columnName;
	}
}
