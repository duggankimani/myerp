package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.google.inject.Inject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.form.ComboBox;

public class ComboPresenter extends BasePresenterWidget<ComboPresenter.MyView> {

	public interface MyView extends BaseView {
		void init(FieldModel field);

		void setValue(Integer value);

		void setValue(String value);
		
		ComboBox<LookupValue> getComponent();
	}

	@Inject
	public ComboPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void setFieldModel(FieldModel field) {
		getView().init(field);
		super.setFieldModel(field);
		
		bindEvents();
	}
	
	private void bindEvents() {
		getView().getComponent().addValueChangeHandler(new ValueChangeHandler<LookupValue>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<LookupValue> event) {
				//valueChanged(event.getValue());
				
				LookupValue lookup = event.getValue();
				
				valueChanged(lookup==null? null : lookup.getKey()==null? lookup.getKey2(): lookup.getKey());
			}
		});
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Integer)
			getView().setValue((Integer)value);
		
		if(value instanceof String)
			getView().setValue((String)value);
	}

}
