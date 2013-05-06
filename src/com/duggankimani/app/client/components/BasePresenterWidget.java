package com.duggankimani.app.client.components;

import com.duggankimani.app.client.events.CalloutEvent;
import com.duggankimani.app.client.events.ClearFieldsEvent;
import com.duggankimani.app.client.events.ClearFieldsEvent.ClearFieldsHandler;
import com.duggankimani.app.client.events.SetValueEvent;
import com.duggankimani.app.client.events.SetValueEvent.SetValueHandler;
import com.duggankimani.app.client.events.ValueChangedEvent;
import com.duggankimani.app.client.events.ValueChangedEvent.ValueChangedHandler;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.UIObject;
import com.gwtplatform.mvp.client.PresenterWidget;

public abstract class BasePresenterWidget<V extends BaseView> extends
		PresenterWidget<V> implements SetValueHandler, ClearFieldsHandler,
		ValueChangedHandler {

	FieldModel fieldModel;

	public BasePresenterWidget(EventBus eventBus, V view) {
		super(eventBus, view);
	}

	public BasePresenterWidget(boolean autoBind, EventBus eventBus, V view) {
		super(autoBind, eventBus, view);
	}

	public void setName(String name) {
		getView().setName(name);
	}

	@Override
	protected void onBind() {
		// TODO Auto-generated method stub
		super.onBind();
		addRegisteredHandler(SetValueEvent.TYPE, this);
		addRegisteredHandler(ClearFieldsEvent.TYPE, this);
		addRegisteredHandler(ValueChangedEvent.TYPE, this);
	}

	/**
	 * 
	 * @param field
	 */
	public void setFieldModel(FieldModel field) {
		this.fieldModel = field;

		setName(field.getName());

		setDescription(field.getDescription());

		getView().setColSpan(field.getColSpan());

		setVisible(field.isDisplayed());

	}

	/**
	 * Title/ Tool tip
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		getView().setDescription(description);
	}

	public void setVisible(boolean isVisible) {
		UIObject.setVisible(getView().getContainer().getElement(), isVisible);
	}

	/**
	 * To be implemented by each component
	 * 
	 * @param isEditable
	 */
	public void setEditable(boolean isEditable) {

	}

	@Override
	public void onSetValue(SetValueEvent event) {
		if (fieldModel == null) {
			// System.out.println("FieldModel is null!!!-----!!! -- Menu");
			return;
		}

		if (!(event.getWindowId().equals(fieldModel.getWindowId()) && event
				.getTabNo().equals(fieldModel.getTabNo()))) {
			return;
		}

		DataModel model = event.getData();
		if (model == null)
			return;

		Object value = model.get(fieldModel.getColumnName());
		
		if (value == null)
			return;
		try {
			setValue(value);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage() + " "
					+ fieldModel.getName() + " - "
					+ fieldModel.getDisplayType().name(), e);
		}
		// evaluate display logic if any
	}

	public abstract void setValue(Object value);

	@Override
	public void onClearFields(ClearFieldsEvent event) {
		if (!(event.getWindowId().equals(fieldModel.getWindowId()) && event
				.getTabNo().equals(fieldModel.getTabNo()))) {
			return;
		}

		if (this instanceof ButtonPresenter) {
			// disable
		} else {
			this.clearData();
		}

	}

	public void clearData() {
		getView().clearData();
	}

	void valueChanged(Object newValue) {
		fireEvent(new ValueChangedEvent(fieldModel.getWindowId(),
				fieldModel.getTabNo(), fieldModel.getColumnName(), newValue));
		if (fieldModel.hasCallout()) {
			// fire callout
			fireEvent(new CalloutEvent(fieldModel.getWindowId(),
					fieldModel.getTabNo(), fieldModel.getColumnName()));
		}
	}

	@Override
	public void onValueChanged(ValueChangedEvent event) {
		if (event.getSource() == this)
			return;

		if (!(event.getWindowId().equals(fieldModel.getWindowId()) && event
				.getTabNo().equals(fieldModel.getTabNo()))) {
			return;
		}

		// handle Dynamic Validation
	}
}
