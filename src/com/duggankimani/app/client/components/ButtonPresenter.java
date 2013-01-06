package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class ButtonPresenter extends BasePresenterWidget<ButtonPresenter.MyView> {

	public interface MyView extends BaseView {
		void init(FieldModel field);
	}

	@Inject
	public ButtonPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void setValue(Object value) {
		
	}

	@Override
	public void setFieldModel(FieldModel field) {
		((MyView)getView()).init(field);
		super.setFieldModel(field);
	}
}
