package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.action.SearchActionResult;
import com.duggankimani.app.shared.model.FieldModel;

/**
 * SearchAction
 * 
 * @author duggan
 *
 */
public class SearchAction extends BaseAction<SearchActionResult> {

	private String query;
	private FieldModel field;
	
	public SearchAction(){}
	
	public SearchAction(FieldModel field) {
		this.field = field;
	}
	
	public SearchAction(FieldModel field, String query) {
		this(field);
		this.query = query;
	}
	
	@Override
	public BaseActionResult createDefaultActionResponse() {
		return new SearchActionResult();
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public FieldModel getField() {
		return field;
	}

	public void setField(FieldModel field) {
		this.field = field;
	}
}
