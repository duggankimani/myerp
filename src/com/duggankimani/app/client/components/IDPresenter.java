package com.duggankimani.app.client.components;

import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class IDPresenter extends BasePresenterWidget<IDPresenter.MyView> {

	public interface MyView extends BaseView {
	}

	@Inject
	public IDPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void setValue(Object value) {
		
	}

}
