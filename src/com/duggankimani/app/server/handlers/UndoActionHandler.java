package com.duggankimani.app.server.handlers;

import org.compiere.model.GridTab;

import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.action.UndoAction;
import com.duggankimani.app.shared.action.UndoActionResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlerutils.DataReader.*;

/**
 * 
 * @author duggan
 *
 */
public class UndoActionHandler extends BaseActionHandler<UndoAction, UndoActionResult>{

	@Inject
	public UndoActionHandler() {
	}


	@Override
	public UndoActionResult execute(UndoAction action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {
		
		UndoActionResult result = (UndoActionResult)actionResult;
		
		WindowStatus ws = WindowStatus.getWindowStatus(action.getWindowId());
		
		GridTab curTab = ws.getTab(action.getTabNo());
		curTab.dataIgnore();
		if(action.getPreviousRowNo()!=null)
			curTab.navigate(action.getPreviousRowNo());
		
		if(curTab.getRowCount()>0){
			result.setData(getRowData(curTab, false));
		}
		
		return result;
	}
	
	@Override
	public void undo(UndoAction action, UndoActionResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<UndoAction> getActionType() {
		return UndoAction.class;
	}
}
