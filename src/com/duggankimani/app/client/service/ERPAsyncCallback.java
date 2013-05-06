package com.duggankimani.app.client.service;

import java.math.BigDecimal;
import java.util.Date;

import com.duggankimani.app.shared.model.LookupValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.shared.FastMap;
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
		messageBox.setMinWidth(400);
		messageBox.show();
		
	}

	@Override
	public void onSuccess(T result) {
		processResult(result);
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
