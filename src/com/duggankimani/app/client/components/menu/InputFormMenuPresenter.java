package com.duggankimani.app.client.components.menu;

import com.duggankimani.app.client.components.BaseView;
import com.duggankimani.app.client.events.NavigateEvent;
import com.duggankimani.app.client.events.SetValueEvent;
import com.duggankimani.app.client.events.SetValueEvent.SetValueHandler;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class InputFormMenuPresenter extends
		PresenterWidget<InputFormMenuPresenter.MyView> implements SetValueHandler{

	public interface MyView extends BaseView {
		void addField(FieldModel field);

		void clearActions();
		
		TextButton getPrev();
		
		TextButton getNext();

		void setNavigationState(Boolean hasPrev, Boolean hasNext);
		
	}

	MyView view;
	
	@Inject
	public InputFormMenuPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
		this.view = view;
		
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(SetValueEvent.TYPE, this);
		
		view.getNext().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				// TODO Auto-generated method stub
				fireEvent(new NavigateEvent(1, InputFormMenuPresenter.this));
			}
		});
		
		view.getPrev().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				// TODO Auto-generated method stub
				fireEvent(new NavigateEvent(-1, InputFormMenuPresenter.this));
			}
		});
	}

	public void navigateNext() {
		
		fireEvent(new NavigateEvent(1, InputFormMenuPresenter.this));
		
	}

	public void navigatePrevious() {
		fireEvent(new NavigateEvent(-1, InputFormMenuPresenter.this));
	}

	@Override
	public void onSetValue(SetValueEvent event) {
		DataModel dataModel = event.getData();
		view.setNavigationState(dataModel.hasPrev(), dataModel.hasNext());
	}
	
}
