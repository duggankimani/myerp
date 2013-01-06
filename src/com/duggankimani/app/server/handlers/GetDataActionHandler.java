package com.duggankimani.app.server.handlers;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.action.GetDataActionResult;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetDataActionHandler implements
		ActionHandler<GetDataAction, GetDataActionResult> {

	Log log = LogFactory.getLog(getClass());
	
	@Inject
	public GetDataActionHandler() {
	}

	@Override
	public GetDataActionResult execute(GetDataAction action, ExecutionContext context)
			throws ActionException {
		
		WindowStatus windowStatus = WindowStatus.getWindowStatus(action.getWindowID());
		
		
		DataModel data = getData(windowStatus, action);
		
		
		return new GetDataActionResult(data);
	}


	@Override
	public void undo(GetDataAction action, GetDataActionResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<GetDataAction> getActionType() {
		return GetDataAction.class;
	}
	
	private DataModel getData(WindowStatus windowStatus, GetDataAction action) {

		GridTab tab = windowStatus.gridWindow.getTab(action.getTabNo());
		
		tab.query(false);
		if(tab.getRowCount()==0){
			log.info("DataModel - No data to present - 0 row count");
			return new DataModel();			
		}
		
		tab.navigate(0);
		
		GridField[] fields = tab.getFields();

		DataModel dataModel = new DataModel();
		
		for(GridField field: fields){
			Object value = tab.getValue(field);
			String columnName=field.getColumnName();
			int displayType = field.getDisplayType();
			
			if(value==null)
				continue;
			
			if(field.isLookup()){
				dataModel.set(columnName, getLookupValue(value));
			}else if(displayType == DisplayType.Date || displayType==DisplayType.DateTime){
				dataModel.set(columnName, getDate(value));
			}else if(displayType==DisplayType.YesNo){
				dataModel.set(columnName, getBoolean(value));
			}else if(displayType==DisplayType.Binary){
				throw new RuntimeException("Binary -- Unsupported data type!!!!!");
			}else if(displayType == DisplayType.PAttribute){
				throw new RuntimeException("PAttribute -- Unsupported data type!!!!!");
			}else{
				dataModel.set(columnName, (Serializable)value);
			}
			
			
		}
		return dataModel;
	}

	private Serializable getBoolean(Object value) {
		
		String yesno = value.toString();
		
		return yesno.equals("Y") || yesno.equals("T") || yesno.equals("true");
		
	}

	private Serializable getDate(Object value) {
		if(value instanceof Timestamp)
			return new Date(((Timestamp)value).getTime());
		
		return (Date)value;
	}

	private Serializable getLookupValue(Object lookup) {
		
		LookupValue lookupValue = new LookupValue();
		
		if(lookup instanceof ValueNamePair){
			ValueNamePair lookp = (ValueNamePair)lookup;
			lookupValue = new LookupValue(lookp.getValue(), lookp.getName());
			
		}else if(lookup instanceof KeyNamePair){
			KeyNamePair keyname = (KeyNamePair)lookup;
			lookupValue = new LookupValue(keyname.getKey(), keyname.getName());
		}else if(lookup instanceof Integer || lookup instanceof String){
			//return key
			return (Serializable)lookup;
		}else{
			//locations, locator
			throw new RuntimeException("Unsupported Lookup type -- "+lookup.getClass());
		}
		
		return lookupValue;
	}

}