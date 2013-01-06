package com.duggankimani.app.client.components.menu;

import com.duggankimani.app.client.components.BasePresenterWidget;
import com.duggankimani.app.client.components.BaseView;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class InputFormMenuPresenter extends
		BasePresenterWidget<InputFormMenuPresenter.MyView> {

	public interface MyView extends BaseView {
		void addField(FieldModel field);

		void clearActions();
		
	}

	@Inject
	public InputFormMenuPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}
	
}
