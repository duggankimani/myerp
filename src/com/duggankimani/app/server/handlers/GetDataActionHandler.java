package com.duggankimani.app.server.handlers;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.action.GetDataActionResult;
import com.duggankimani.app.shared.model.DataModel;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import static com.duggankimani.app.server.handlers.DataReader.*;

public class GetDataActionHandler implements
		ActionHandler<GetDataAction, GetDataActionResult> {

	Log log = LogFactory.getLog(getClass());

	@Inject
	public GetDataActionHandler() {
	}

	@Override
	public GetDataActionResult execute(GetDataAction action,
			ExecutionContext context) throws ActionException {

		WindowStatus windowStatus = WindowStatus.getWindowStatus(action
				.getWindowID());

		GetDataActionResult result = new GetDataActionResult();

		if (action.isMultipleResults()) {
			ArrayList<DataModel> data = getDataList(windowStatus, action);
			result.setDataModel(data);
		} else {
			DataModel data = getData(windowStatus, action);
			result.setDataModel(new ArrayList<>(Arrays.asList(data)));
		}

		return result;
	}

	@Override
	public void undo(GetDataAction action, GetDataActionResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<GetDataAction> getActionType() {
		return GetDataAction.class;
	}

	
}
