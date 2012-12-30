package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

public abstract class BasePresenterWidget<V> extends PresenterWidget<BaseView> {

	FieldModel fieldModel;
	
	public BasePresenterWidget(EventBus eventBus, BaseView view) {
		super(eventBus, view);
	}
	
	
	public BasePresenterWidget(boolean autoBind, EventBus eventBus, BaseView view) {
		super(autoBind, eventBus, view);
	}

	public void setName(String name){
		getView().setName(name);
	}
	
	public void setFieldModel(FieldModel field) {
		this.fieldModel = field;
		setName(field.getName());
		getView().setColSpan(field.getColSpan());
		
	}

	public void setDescription(String description){
		
	}
	
	public void setVisible(boolean isVisible){
		
	}
}
