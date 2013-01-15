package com.duggankimani.app.client.core;

import java.util.ArrayList;

import com.duggankimani.app.client.events.ERPRequestProcessingCompletedEvent;
import com.duggankimani.app.client.events.ERPRequestProcessingEvent;
import com.duggankimani.app.client.events.LoadLineDataEvent;
import com.duggankimani.app.client.events.LoadLineEvent;
import com.duggankimani.app.client.events.LoadLineDataEvent.LoadLineDataHandler;
import com.duggankimani.app.client.events.LoadLineEvent.LoadLineHandler;
import com.duggankimani.app.client.service.ERPAsyncCallback;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.action.GetDataActionResult;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.TabModel;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class InputLinesPresenter extends
		PresenterWidget<InputLinesPresenter.MyView> implements LoadLineHandler, LoadLineDataHandler{

	public interface MyView extends View {
		public void bind(TabModel tabModel);

		void bindData(ArrayList<DataModel> dataModel);
	}

	@Inject DispatchAsync dispatcher;
	
	@Inject
	public InputLinesPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
		eventBus.addHandler(LoadLineEvent.TYPE, this);
		eventBus.addHandler(LoadLineDataEvent.TYPE, this);
	}


	@Override
	protected void onBind() {
		super.onBind();
	}

	TabModel tab = null;


	protected void setData(ArrayList<DataModel> dataModel) {
		getView().bindData(dataModel);		
	}

	private void loadData() {
		getEventBus().fireEvent(new ERPRequestProcessingEvent("Tab data"));
		System.out.println("Lines Loading..."+tab.getName());
		//check if data already loaded
		dispatcher.execute(new GetDataAction(tab.getTabNo(), 0, tab.getWindowID(), 0, true), new ERPAsyncCallback<GetDataActionResult>() {
			@Override
			public void processResult(GetDataActionResult result) {
				setData(result.getDataModel());
				System.out.println("Lines Loaded..."+result.getDataModel().size());
				getEventBus().fireEvent(new ERPRequestProcessingCompletedEvent());
			}
		});
	
	}
	
	@Override
	public void onLoadLine(LoadLineEvent event) {
		
	}

	@Override
	public void onLoadLineData(LoadLineDataEvent event) {
		if(tab==null)
			return;
		
		int tabNo = event.getTabNo();
		int windowId = event.getWindowID();
		
		if(tab.getTabNo()==tabNo && tab.getWindowID()==windowId){
			loadData();
		}
	}

	public void bind(TabModel tabModel) {
		this.tab = tabModel;
		getView().bind(tab);
	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub
		super.onUnbind();
		System.out.println("Unbind called!!!!");
	}

}
