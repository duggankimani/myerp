package com.duggankimani.app.client.core;

import com.duggankimani.app.client.events.ActionButtonCreatedEvent;
import com.duggankimani.app.client.events.ActionButtonCreatedEvent.ActionButtonCreatedHandler;
import com.duggankimani.app.client.events.WindowChangedEvent;
import com.duggankimani.app.client.events.WindowChangedEvent.WindowChangedHandler;
import com.duggankimani.app.shared.model.FieldModel;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

/**
 * 
 * @author duggan
 *
 */
public class ActionsPresenter extends PresenterWidget<ActionsPresenter.IActionsView> 
		implements ActionButtonCreatedHandler,WindowChangedHandler{

	public interface IActionsView extends View {
		void addAction(String url, String text, String title);
		void clearAll();
	}

	@Inject
	public ActionsPresenter(final EventBus eventBus, final IActionsView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		this.addRegisteredHandler(ActionButtonCreatedEvent.TYPE, this);
		this.addRegisteredHandler(WindowChangedEvent.TYPE, this);
	}

	@Override
	public void onActionButtonCreated(ActionButtonCreatedEvent event) {
		FieldModel model = event.getFieldModel();
		
		//PlaceRequest url = URLUtils.getURL(model);
		
		getView().addAction("#", model.getName(), model.getDescription());
	}

	@Override
	public void onWindowChanged(WindowChangedEvent event) {
		getView().clearAll();
	}

}
