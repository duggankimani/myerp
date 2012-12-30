package com.duggankimani.app.client.components;

import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class IDPresenter extends BasePresenterWidget<IDPresenter.MyView> {

	public interface MyView extends View {
	}

	@Inject
	public IDPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, (BaseView)view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
