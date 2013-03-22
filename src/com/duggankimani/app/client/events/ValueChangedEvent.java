package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Integer;
import java.lang.String;
import com.google.gwt.event.shared.HasHandlers;

public class ValueChangedEvent extends
		GwtEvent<ValueChangedEvent.ValueChangedHandler> {

	public static Type<ValueChangedHandler> TYPE = new Type<ValueChangedHandler>();
	private Integer windowId;
	private Integer tabNo;
	private String columnName;
	private Object newValue;

	public interface ValueChangedHandler extends EventHandler {
		void onValueChanged(ValueChangedEvent event);
	}

	public ValueChangedEvent(Integer windowId, Integer tabNo, String columnName, Object newValue) {
		this.windowId = windowId;
		this.tabNo = tabNo;
		this.columnName = columnName;
		this.newValue = newValue;
	}

	public Integer getWindowId() {
		return windowId;
	}

	public Integer getTabNo() {
		return tabNo;
	}

	public String getColumnName() {
		return columnName;
	}

	@Override
	protected void dispatch(ValueChangedHandler handler) {
		handler.onValueChanged(this);
	}

	@Override
	public Type<ValueChangedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ValueChangedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Integer windowId, Integer tabNo,
			String columnName, Object newValue) {
		source.fireEvent(new ValueChangedEvent(windowId, tabNo, columnName, newValue));
	}

	public Object getNewValue() {
		return newValue;
	}

	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}
}
