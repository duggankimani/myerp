package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import java.lang.Boolean;
import com.google.gwt.event.shared.HasHandlers;

public class TabStateChangedEvent extends
		GwtEvent<TabStateChangedEvent.TabStateChangedHandler> {

	public static Type<TabStateChangedHandler> TYPE = new Type<TabStateChangedHandler>();
	private Integer windowId;
	private Integer windowNo;
	private Integer tabNo;
	private Boolean isReadOnly;

	public interface TabStateChangedHandler extends EventHandler {
		void onTabStateChanged(TabStateChangedEvent event);
	}

	public TabStateChangedEvent(Integer windowId, Integer windowNo, Integer tabNo,
			Boolean isReadOnly) {
		this.windowId = windowId;
		this.windowNo = windowNo;
		this.tabNo = tabNo;
		this.isReadOnly = isReadOnly;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getWindowNo() {
		return windowNo;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public Boolean isReadOnly() {
		return isReadOnly;
	}

	@Override
	protected void dispatch(TabStateChangedHandler handler) {
		handler.onTabStateChanged(this);
	}

	@Override
	public Type<TabStateChangedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<TabStateChangedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer windowId, Integer windowNo,
			Integer tabNo, Boolean isReadOnly) {
		source.fireEvent(new TabStateChangedEvent(windowId, windowNo, tabNo,
				isReadOnly));
	}
}
