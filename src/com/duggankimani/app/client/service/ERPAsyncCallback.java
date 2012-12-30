package com.duggankimani.app.client.service;

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
		messageBox.setMinWidth(400);
		messageBox.show();
		
	}

	@Override
	public void onSuccess(T result) {
		processResult(result);
	}
	
	public abstract void processResult(T result);

}
