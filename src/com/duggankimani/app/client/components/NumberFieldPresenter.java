package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class NumberFieldPresenter extends
		BasePresenterWidget<NumberFieldPresenter.MyView> {

	public interface MyView extends BaseView {
		public void init(FieldModel field);
		
		public void setValue(Number number);
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
				
		((MyView) getView()).init(field);
		super.setFieldModel(field);
	}
	
	@Override
	public void setValue(Object value) {
		Number number = (Number)value;
		
		((MyView)getView()).setValue(number);
	}

}
