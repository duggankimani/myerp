package com.duggankimani.app.client.components.menu;

import com.duggankimani.app.client.events.CreateEvent;
import com.duggankimani.app.client.events.NavigateEvent;
import com.duggankimani.app.client.events.SetValueEvent;
import com.duggankimani.app.client.events.CreateEvent.CreateHandler;
import com.duggankimani.app.client.events.SetValueEvent.SetValueHandler;
import com.duggankimani.app.client.events.UndoEvent;
import com.duggankimani.app.client.events.UndoEvent.UndoHandler;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class InputFormMenuPresenter extends
		PresenterWidget<InputFormMenuPresenter.MyView> implements SetValueHandler, CreateHandler, UndoHandler{

	public interface MyView extends View{
		void addField(FieldModel field);

		void clearActions();
		
		TextButton getPrev();
		
		TextButton getNext();

		void setNavigationState(Boolean hasPrev, Boolean hasNext);
		
		public TextButton getNew();
		
		public TextButton getCopy();
		
		public TextButton getUndo();

		void setMode(int i);
	}

	MyView view;
	
	@Inject
	public InputFormMenuPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
		this.view = view;
		view.setMode(0);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(SetValueEvent.TYPE, this);
		addRegisteredHandler(CreateEvent.TYPE, this);
		addRegisteredHandler(UndoEvent.TYPE, this);
		

		view.getUndo().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				fireEvent(new UndoEvent());
			}
		});
		
		view.getNew().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				fireEvent(new CreateEvent());
			}
		});
		
		view.getNext().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				// TODO Auto-generated method stub
				fireEvent(new NavigateEvent(1));
			}
		});
		
		view.getPrev().addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				// TODO Auto-generated method stub
				fireEvent(new NavigateEvent(-1));
			}
		});
	}

	public void navigateNext() {
		fireEvent(new NavigateEvent(1));
		
	}

	public void navigatePrevious() {
		fireEvent(new NavigateEvent(-1));
	}

	@Override
	public void onSetValue(SetValueEvent event) {
		DataModel dataModel = event.getData();
		view.setNavigationState(dataModel.hasPrev(), dataModel.hasNext());
	}

	@Override
	public void onCreate(CreateEvent event) {
		if(event.getSource()==this){
			view.setMode(1);
		}
	}

	@Override
	public void onUndo(UndoEvent event) {
		if(event.getSource()==this){
			view.setMode(0);
		}
	}
	
}
