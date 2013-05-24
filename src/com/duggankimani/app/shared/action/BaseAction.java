package com.duggankimani.app.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import com.gwtplatform.dispatch.shared.Result;

/**
 * 
 * @author duggan
 *
 * @param <T>
 */
public class BaseAction<T extends BaseActionResult> extends ActionImpl<T>{
	
		
	@Override
	public boolean isSecured() {
		return false;
	}

	public BaseActionResult createDefaultActionResponse(){
		return new BaseActionResult();
	}
}
