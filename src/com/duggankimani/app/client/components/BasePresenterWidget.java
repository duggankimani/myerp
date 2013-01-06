package com.duggankimani.app.client.components;

import com.duggankimani.app.client.events.SetValueEvent;
import com.duggankimani.app.client.events.SetValueEvent.SetValueHandler;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;

public abstract class BasePresenterWidget<V> extends PresenterWidget<BaseView> implements SetValueHandler{

	FieldModel fieldModel;
	
	public BasePresenterWidget(EventBus eventBus, BaseView view) {
		super(eventBus, view);
		eventBus.addHandler(SetValueEvent.TYPE, this);
	}
	
	
	public BasePresenterWidget(boolean autoBind, EventBus eventBus, BaseView view) {
		super(autoBind, eventBus, view);
	}

	public void setName(String name){
		getView().setName(name);
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
	 * @param description
	 */
	public void setDescription(String description){
		getView().setDescription(description);
	}
	
	public void setVisible(boolean isVisible){
		getView().getContainer().setVisible(isVisible);
	}
	
	/**
	 * To be implemented by each component
	 *  
	 * @param isEditable
	 */
	public void setEditable(boolean isEditable){
		
	}
	
	@Override
	public void onSetValue(SetValueEvent event) {
		if(fieldModel==null){
			//System.out.println("FieldModel is null!!!-----!!! -- Menu");
			return;
		}
		
		DataModel model = event.getData();
		if(model==null)
			return;
					
		Object value = model.get(fieldModel.getColumnName());
		if(value==null)
			return;
		setValue(value);
		
		//evaluate display logic if any
	}

	public abstract void setValue(Object value);
	
}
