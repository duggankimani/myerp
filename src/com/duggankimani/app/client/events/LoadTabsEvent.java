package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class LoadTabsEvent extends GwtEvent<LoadTabsEvent.LoadTabsHandler> {

	public static Type<LoadTabsHandler> TYPE = new Type<LoadTabsHandler>();
	private Integer windowId;
	private Integer parentTabNo;
	private Integer parentTabLevel;

	public interface LoadTabsHandler extends EventHandler {
		void onLoadTabs(LoadTabsEvent event);
	}

	public LoadTabsEvent(Integer windowId, Integer parentTabNo,
			Integer parentTabLevel) {
		this.windowId = windowId;
		this.parentTabNo = parentTabNo;
		this.parentTabLevel = parentTabLevel;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getParentTabNo() {
		return parentTabNo;
	}

	public Integer getParentTabLevel() {
		return parentTabLevel;
	}

	@Override
	protected void dispatch(LoadTabsHandler handler) {
		handler.onLoadTabs(this);
	}

	@Override
	public Type<LoadTabsHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<LoadTabsHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer windowId,
			Integer parentTabNo, Integer parentTabLevel) {
		source.fireEvent(new LoadTabsEvent(windowId, parentTabNo, parentTabLevel));
	}
}
