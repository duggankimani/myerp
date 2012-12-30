package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class NumberFieldPresenter extends
		BasePresenterWidget<NumberFieldPresenter.MyView> {

	public interface MyView extends View {
		public void init(FieldModel field);
	}

	@Inject
	public NumberFieldPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, (BaseView) view);	
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
