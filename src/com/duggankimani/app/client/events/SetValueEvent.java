package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.duggankimani.app.shared.model.DataModel;
import com.google.gwt.event.shared.HasHandlers;

public class SetValueEvent extends GwtEvent<SetValueEvent.SetValueHandler> {

	public static Type<SetValueHandler> TYPE = new Type<SetValueHandler>();
	private DataModel data;

	public interface SetValueHandler extends EventHandler {
		void onSetValue(SetValueEvent event);
	}

	public SetValueEvent(DataModel data) {
		this.data = data;
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

	public static void fire(HasHandlers source, DataModel data) {
		source.fireEvent(new SetValueEvent(data));
	}
}
