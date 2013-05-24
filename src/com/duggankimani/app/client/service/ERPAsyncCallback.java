package com.duggankimani.app.client.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.duggankimani.app.client.BigApp;
import com.duggankimani.app.client.events.NavigationUpdateEvent;
import com.duggankimani.app.client.events.TabStateChangedEvent;
import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.RequestSetResult;
import com.duggankimani.app.shared.model.LookupValue;
import com.duggankimani.app.shared.model.ServerStatus;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;

public abstract class ERPAsyncCallback<T> implements AsyncCallback<T>{

	@Override
	public void onFailure(Throwable caught) {
		
		StringBuffer error = new StringBuffer();
		for(StackTraceElement el : caught.getStackTrace()){
			error.append(el.toString()+"<br/>");
		}
		
		AlertMessageBox messageBox= new AlertMessageBox("Server Error", 
				"Exception: <br/>"+caught.getClass().toString()+" <br/> "+caught.getMessage());
		
		caught.printStackTrace();
		messageBox.setWidth(400);
		messageBox.show();
		
	}

	@Override
	public void onSuccess(T result) {
		
		if(result instanceof RequestSetResult){
			
			List<BaseActionResult> results = ((RequestSetResult) result).getResponses();
			if(results!=null)
			for(BaseActionResult r: results){
				handleDataStatusChange(r);
			}
			
		}else if(result instanceof BaseActionResult){
			handleDataStatusChange((BaseActionResult)result);
		}
		processResult(result);
	}
	
	private void handleDataStatusChange(BaseActionResult result) {
		EventBus eventBus = BigApp.getEventBus(); 		
		
		ServerStatus status =  result.getServerStatus();
		if(status==null){
			//System.err.println("Status is null ["+result.getClass().getName()+"]");
			return;
		}else{
			//System.err.println("Status Found ["+result.getClass().getName()+"]");
		}
		
		int windowId = status.getWindowId();
		int windowNo = status.getWindowNo();
		int tabNo = status.getTabNo();
		
		
		//navigation state
		eventBus.fireEvent(new NavigationUpdateEvent(
				windowId,
				windowNo, 
				tabNo, 
				status.isFirstRow(), 
				status.isLastRow()));
		
		eventBus.fireEvent(new TabStateChangedEvent(windowId, windowNo, tabNo, status.isReadOnly()));
		
//		System.err.println("Status [WindowId : "+status.getWindowId()+"]" +
//				" [WindowNo :"+status.getWindowNo()+"] [TabNo : "+status.getTabNo()+"]\n");
		
		//status line
		status.getStatusLine();
		status.getDbStatus();
		
		if(status.isError()){
			//show error popup
			
		}
		
		if(status.isWarning()){
			
		}
		
		//status.get
		
	}

	public abstract void processResult(T result);
	
	
	private Date getDate(){
		return new Date();//transactionDate;
	}
	
	private BigDecimal number;
	
	private Number no;

	private Double doublle;

	public BigDecimal getNumber(BigDecimal m) {
		return number;
	}
	
	public Number getNo(Number n) {
		return no;
	}

	public Double getDoublle(Double d) {
		return doublle;
	}
	
	LookupValue value;
}
