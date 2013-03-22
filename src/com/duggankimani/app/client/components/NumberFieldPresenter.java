
package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.form.NumberField;

public class NumberFieldPresenter extends
		BasePresenterWidget<NumberFieldPresenter.MyView> {

	public interface MyView extends BaseView {
		void init(FieldModel field);
		
		void setValue(Number number);
		
		NumberField<Number> getComponent();
	}

	@Inject
	public NumberFieldPresenter(final EventBus eventBus, final MyView view) {
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
		
		assert getView()!=null;
		assert getView().getComponent()!=null;
		
		getView().getComponent().addValueChangeHandler(new ValueChangeHandler<Number>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Number> event) {
				valueChanged(event.getValue());
			}
		});
		
	}

	@Override
	public void setValue(Object value) {
		Number number = (Number)value;
		
		getView().setValue(number);
	}

}
