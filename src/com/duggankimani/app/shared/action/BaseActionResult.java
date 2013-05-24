package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.ServerStatus;
import com.gwtplatform.dispatch.shared.Result;

/**
 * Base Class For All responses
 * 
 * @author duggan
 *
 */
public class BaseActionResult implements Result{

	ServerStatus serverStatus;

	public ServerStatus getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(ServerStatus serverStatus) {
		this.serverStatus = serverStatus;
	}
	
}
