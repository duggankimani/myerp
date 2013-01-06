package com.duggankimani.app.shared.model;

import java.io.Serializable;

import com.sencha.gxt.core.shared.FastMap;

public class DataModel implements Serializable {

	private static final long serialVersionUID = 8758277954851156781L;
	//private HashMap<String, Object> data = new HashMap<String, Object>();
	private FastMap<Serializable> data = new FastMap<Serializable>();
	
	public DataModel() {
	}

	public void set(String key, Serializable value){
		data.put(key, value);
	}
	
	public Serializable get(String key){
		
		return data.get(key);
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
