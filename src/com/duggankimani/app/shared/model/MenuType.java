package com.duggankimani.app.shared.model;

import java.io.Serializable;

/**
 * 
 * @author duggan
 *
 */
public enum MenuType implements Serializable{

	WINDOW,
	REPORT,
	PROCESS,
	UNSUPPORTED;
	
	private MenuType(){
		
	}
}
