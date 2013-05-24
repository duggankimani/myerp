package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class LoadProcessEvent extends
		GwtEvent<LoadProcessEvent.LoadProcessHandler> {

	public static Type<LoadProcessHandler> TYPE = new Type<LoadProcessHandler>();
	private Integer menuId;
	private Integer windowId;
	private Integer windowNo;
	private Integer tabNo;

	public interface LoadProcessHandler extends EventHandler {
		void onLoadProcess(LoadProcessEvent event);
	}

	public LoadProcessEvent(Integer menuId, Integer windowId, Integer windowNo,
			Integer tabNo) {
		this.menuId = menuId;
		this.windowId = windowId;
		this.windowNo = windowNo;
		this.tabNo = tabNo;
	}

	public Integer getMenuId() {
		return menuId;
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

	@Override
	protected void dispatch(LoadProcessHandler handler) {
		handler.onLoadProcess(this);
	}

	@Override
	public Type<LoadProcessHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<LoadProcessHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer menuId, Integer windowId,
			Integer windowNo, Integer tabNo) {
		source.fireEvent(new LoadProcessEvent(menuId, windowId, windowNo, tabNo));
	}
}
