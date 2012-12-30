package com.duggankimani.app.client.components;

import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class ButtonPresenter extends BasePresenterWidget<ButtonPresenter.MyView> {

	public interface MyView extends View {
		void init(FieldModel field);
	}

	@Inject
	public ButtonPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, (BaseView)view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
