package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.duggankimani.app.shared.model.DataModel;
import com.google.gwt.event.shared.HasHandlers;

public class SetValueEvent extends GwtEvent<SetValueEvent.SetValueHandler> {

	public static Type<SetValueHandler> TYPE = new Type<SetValueHandler>();
	private DataModel data;
	private Integer WindowId;
	private Integer tabNo;

	public interface SetValueHandler extends EventHandler {
		void onSetValue(SetValueEvent event);
	}

	public SetValueEvent(DataModel data, Integer windowId, Integer tabNo) {
		this.data = data;
		this.WindowId=windowId;
		this.tabNo=tabNo;
	}

	public DataModel getData() {
		return data;
	}

	@Override
	protected void dispatch(SetValueHandler handler) {
		handler.onSetValue(this);
	}

	@Override
	public Type<SetValueHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<SetValueHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, DataModel data, Integer windowId, Integer tabNo) {
		source.fireEvent(new SetValueEvent(data, windowId, tabNo));
		
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public void setWindowId(Integer windowId) {
		WindowId = windowId;
	}

	public Integer getWindowId() {
		return WindowId;
	}
}
