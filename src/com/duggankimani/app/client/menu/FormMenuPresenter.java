package com.duggankimani.app.client.menu;

import com.duggankimani.app.client.events.CreateEvent;
import com.duggankimani.app.client.events.NavigateEvent;
import com.duggankimani.app.client.events.CreateEvent.CreateHandler;
import com.duggankimani.app.client.events.NavigationUpdateEvent;
import com.duggankimani.app.client.events.NavigationUpdateEvent.NavigationUpdateHandler;
import com.duggankimani.app.client.events.TabStateChangedEvent;
import com.duggankimani.app.client.events.TabStateChangedEvent.TabStateChangedHandler;
import com.duggankimani.app.client.events.UndoEvent;
import com.duggankimani.app.client.events.UndoEvent.UndoHandler;
import com.duggankimani.app.shared.model.FieldModel;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class FormMenuPresenter extends
		PresenterWidget<FormMenuPresenter.MyView> 
		implements CreateHandler, UndoHandler, NavigationUpdateHandler, TabStateChangedHandler{

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

		void setReadOnly(boolean readOnly);
	}

	int tabNo;
	int windowNo;
	int windowId;
	
	MyView view;
	
	@Inject
	public FormMenuPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
		this.view = view;
		view.setMode(0);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(CreateEvent.TYPE, this);
		addRegisteredHandler(UndoEvent.TYPE, this);
		addRegisteredHandler(NavigationUpdateEvent.TYPE, this);

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
	public void onCreate(CreateEvent event) {
		if(event.getSource()==this){
			view.setMode(1);
		}
	}
	
	public void setContextInfo(int windowId, int windowNo, int tabNo){
//		System.err.println("FormMenu ContextInfo [WindowId : "+windowId+"]" +
//				" [WindowNo :"+windowNo+"] [TabNo : "+tabNo+"]");
		
		this.windowId = windowId;
		this.windowNo = windowNo;
		this.tabNo = tabNo;
	}

	@Override
	public void onUndo(UndoEvent event) {
		if(event.getSource()==this){
			view.setMode(0);
		}
	}

	@Override
	public void onNavigationUpdate(NavigationUpdateEvent event) {
//		System.err.println("--Event Fired!! FormMenu [WindowId : "+windowId+"]" +
//				" [WindowNo :"+windowNo+"] [TabNo : "+tabNo+"]\n");
		if(windowId!=event.getWindowId() || windowNo!=event.getWindowNo() || tabNo!=event.getTabNo()){
			return;
		}
		
		getView().setNavigationState(!event.isfirstRow(), !event.isLastRow());		
	}

	@Override
	public void onTabStateChanged(TabStateChangedEvent event) {
		int tabNo = event.getTabNo();
		int windowId = event.getWindowId();
		int windowNo=event.getWindowNo();
		boolean readOnly = event.isReadOnly();
		
		if(windowId!=event.getWindowId() || windowNo!=event.getWindowNo() || tabNo!=event.getTabNo()){
			return;
		}
		getView().setReadOnly(readOnly);
	}
	
}
