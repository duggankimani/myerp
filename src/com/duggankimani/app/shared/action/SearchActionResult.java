package com.duggankimani.app.shared.action;

import java.util.ArrayList;
import java.util.List;

import com.duggankimani.app.shared.model.LookupValue;
import com.gwtplatform.dispatch.shared.Result;

/**
 * Load Search Values
 * 
 * @author duggan
 *
 */
public class SearchActionResult extends BaseActionResult {


	private List<LookupValue> lookup= new ArrayList<LookupValue>();
	
	public SearchActionResult() {
	}

	public List<LookupValue> getLookup() {
		return lookup;
	}

	public void setLookup(List<LookupValue> lookup) {
		this.lookup = lookup;
	}
}
