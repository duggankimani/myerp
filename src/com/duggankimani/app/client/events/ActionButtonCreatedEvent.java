package com.duggankimani.app.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.gwt.event.shared.HasHandlers;

public class ActionButtonCreatedEvent extends
		GwtEvent<ActionButtonCreatedEvent.ActionButtonCreatedHandler> {

	public static Type<ActionButtonCreatedHandler> TYPE = new Type<ActionButtonCreatedHandler>();
	private FieldModel fieldModel;

	public interface ActionButtonCreatedHandler extends EventHandler {
		void onActionButtonCreated(ActionButtonCreatedEvent event);
	}

	public ActionButtonCreatedEvent(FieldModel fieldModel) {
		this.fieldModel = fieldModel;
	}

	public FieldModel getFieldModel() {
		return fieldModel;
	}

	@Override
	protected void dispatch(ActionButtonCreatedHandler handler) {
		handler.onActionButtonCreated(this);
	}

	@Override
	public Type<ActionButtonCreatedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ActionButtonCreatedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, FieldModel fieldModel) {
		source.fireEvent(new ActionButtonCreatedEvent(fieldModel));
	}
}
