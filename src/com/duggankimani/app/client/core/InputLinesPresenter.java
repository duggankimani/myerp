package com.duggankimani.app.client.core;

import com.duggankimani.app.client.events.LoadLineEvent;
import com.duggankimani.app.client.events.LoadLineEvent.LoadLineHandler;
import com.duggankimani.app.shared.model.TabModel;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class InputLinesPresenter extends
		PresenterWidget<InputLinesPresenter.MyView> implements LoadLineHandler{

	public interface MyView extends View {
		public void bind(TabModel tabModel);
	}

	@Inject
	public InputLinesPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
		eventBus.addHandler(LoadLineEvent.TYPE, this);
	}


	@Override
	protected void onBind() {
		super.onBind();
	}


	@Override
	public void onLoadLine(LoadLineEvent event) {
		
		getView().bind(event.getTab());
	}
}
