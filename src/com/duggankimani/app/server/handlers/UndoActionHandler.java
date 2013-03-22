package com.duggankimani.app.server.handlers;

import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.UndoAction;
import com.duggankimani.app.shared.action.UndoActionResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlers.DataReader.*;

public class UndoActionHandler implements ActionHandler<UndoAction, UndoActionResult> {

	@Inject
	public UndoActionHandler() {
	}

	@Override
	public UndoActionResult execute(UndoAction action, ExecutionContext context)
			throws ActionException {
		
		WindowStatus ws = WindowStatus.getWindowStatus(action.getWindowId());
		
		GridWindow window = ws.gridWindow;
		GridTab curTab = window.getTab(action.getTabNo());
		curTab.dataIgnore();
		if(action.getPreviousRowNo()!=null)
			curTab.navigate(action.getPreviousRowNo());
		
		if(curTab.getRowCount()>0){
			return new UndoActionResult(getRowData(curTab, false));
		}else{
			return new UndoActionResult(null);
		}
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
