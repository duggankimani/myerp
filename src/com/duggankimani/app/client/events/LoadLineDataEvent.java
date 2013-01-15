package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import com.google.gwt.event.shared.HasHandlers;

public class LoadLineDataEvent extends
		GwtEvent<LoadLineDataEvent.LoadLineDataHandler> {

	public static Type<LoadLineDataHandler> TYPE = new Type<LoadLineDataHandler>();
	private Integer tabNo;
	private Integer windowNo;
	private Integer windowID;

	public interface LoadLineDataHandler extends EventHandler {
		void onLoadLineData(LoadLineDataEvent event);
	}

	public LoadLineDataEvent(Integer tabNo, Integer windowNo, Integer windowID) {
		this.tabNo = tabNo;
		this.windowNo = windowNo;
		this.windowID = windowID;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public Integer getWindowNo() {
		return windowNo;
	}

	public Integer getWindowID() {
		return windowID;
	}

	@Override
	protected void dispatch(LoadLineDataHandler handler) {
		handler.onLoadLineData(this);
	}

	@Override
	public Type<LoadLineDataHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<LoadLineDataHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer tabNo, Integer windowNo,
			Integer windowID) {
		source.fireEvent(new LoadLineDataEvent(tabNo, windowNo, windowID));
	}
}
