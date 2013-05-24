package com.duggankimani.app.server.handlers;

import java.util.List;

import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.server.search.GenericSearch;
import com.duggankimani.app.shared.action.BaseActionResult;
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
public class SearchActionHandler extends BaseActionHandler<SearchAction, SearchActionResult>{
	@Inject
	public SearchActionHandler() {
	}

	@Override
	public SearchActionResult execute(SearchAction action,
			BaseActionResult actionResult, ExecutionContext execContext)
			throws ActionException {
		
		SearchActionResult result = (SearchActionResult)actionResult;
		
		FieldModel field = action.getField();
		String query = action.getQuery();
		
		WindowStatus ws = WindowStatus.getWindowStatus(field.getWindowId());
		
		String columnName = field.getColumnName();
		
		List<LookupValue> values = GenericSearch.search(ws, field.getTabNo(), columnName, query);

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
