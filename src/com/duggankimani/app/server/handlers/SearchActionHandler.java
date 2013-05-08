package com.duggankimani.app.server.handlers;

import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.server.search.GenericSearch;
import com.duggankimani.app.shared.action.SearchAction;
import com.duggankimani.app.shared.action.SearchActionResult;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.LookupValue;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * 
 * @author duggan
 *
 */
public class SearchActionHandler implements ActionHandler<SearchAction, SearchActionResult> {

	@Inject
	public SearchActionHandler() {
	}

	@Override
	public SearchActionResult execute(SearchAction action, ExecutionContext context)
			throws ActionException {
		
		FieldModel field = action.getField();
		String query = action.getQuery();
		
		WindowStatus ws = WindowStatus.getWindowStatus(field.getWindowId());
		
		String columnName = field.getColumnName();
		
		List<LookupValue> values = GenericSearch.search(ws.gridWindow, field.getTabNo(), columnName, query);

		SearchActionResult result = new SearchActionResult();
		result.setLookup(values);
		
		return result;
	}

	@Override
	public void undo(SearchAction action, SearchActionResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<SearchAction> getActionType() {
		return SearchAction.class;
	}
}
