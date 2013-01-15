package com.duggankimani.app.client.components.menu;

import com.duggankimani.app.client.components.BasePresenterWidget;
import com.duggankimani.app.client.components.BaseView;
import com.duggankimani.app.client.events.NavigateEvent;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;

public class InputFormMenuPresenter extends
		BasePresenterWidget<InputFormMenuPresenter.MyView> {

	public interface MyView extends BaseView {
		void addField(FieldModel field);

		void clearActions();
		
		Button getPrev();
		
		Button getNext();
		
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
		
		view.getNext().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				fireEvent(new NavigateEvent(1));				
			}
		});
		
		view.getPrev().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				fireEvent(new NavigateEvent(-1));
				
			}
		});
	}

	@Override
	public void setValue(Object value) {
		
	}

	public void navigateNext() {
		
		fireEvent(new NavigateEvent(1));
		
	}

	public void navigatePrevious() {
		fireEvent(new NavigateEvent(-1));
	}
	
}
