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
	
	public LookupValue(String key1, String value){

		this.key2=key1;
		this.value = value;
	}

	public LookupValue(Integer key1, String value){
		this(""+key1, value);
		this.key=key1;		
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
