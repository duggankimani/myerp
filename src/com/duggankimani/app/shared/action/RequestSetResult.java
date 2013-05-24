package com.duggankimani.app.shared.action;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author duggan
 *
 */
public class RequestSetResult extends BaseActionResult {

	
	private List<BaseActionResult> responses;

	public RequestSetResult() {
		
	}

	public RequestSetResult(List<BaseActionResult> responses) {
		this.responses = responses;
	}

	public List<BaseActionResult> getResponses() {
		return responses;
	}

	public void add(BaseActionResult result) {
		if(responses==null){
			responses = new ArrayList<BaseActionResult>();
		}
		
		responses.add(result);
	}
}
