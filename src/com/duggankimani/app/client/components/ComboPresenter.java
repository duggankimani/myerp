package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class ComboPresenter extends BasePresenterWidget<ComboPresenter.MyView> {

	public interface MyView extends BaseView {
		void init(FieldModel field);

		void setValue(Integer value);

		void setValue(String value);
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
		((MyView)getView()).init(field);
		super.setFieldModel(field);
	}
	
	@Override
	public void setValue(Object value) {
		if(value instanceof Integer)
			((MyView)getView()).setValue((Integer)value);
		
		if(value instanceof String)
			((MyView)getView()).setValue((String)value);
	}

}
