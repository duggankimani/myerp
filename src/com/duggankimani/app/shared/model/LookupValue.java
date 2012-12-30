package com.duggankimani.app.shared.model;

import java.io.Serializable;

public class LookupValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer key;
	private String key2;
	private String value;
	
	public LookupValue() {
		
	}
	
	public LookupValue(String key, String value){

		this.key2=key;
		this.value = value;
	}

	public LookupValue(Integer key, String value){
		this(""+key, value);
		this.key=key;		
	}
	
	public Integer getKey() {
		return key;
	}
	
	public String getKeyAsString(){
		return key2;
	}
	
	public void setKey(Integer key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
