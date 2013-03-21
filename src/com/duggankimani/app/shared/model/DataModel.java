package com.duggankimani.app.shared.model;

import java.io.Serializable;

import com.sencha.gxt.core.shared.FastMap;

public class DataModel implements Serializable {

	private static final long serialVersionUID = 8758277954851156781L;

	private String _ROWNO="_ROWNO";
	private String _HASNEXT="_HASNEXT";
	private String _HASPREV="_HASPREV";
	
	private FastMap<Serializable> data = new FastMap<Serializable>();
	
	public DataModel() {
		set(_HASPREV, false);
		set(_HASNEXT, false);
		set(_ROWNO, -1);
	}

	public void set(String key, Serializable value){
		data.put(key, value);
	}
	
	public Serializable get(String key){
		
		return data.get(key);
	}
	
	public Boolean hasNext(){
		return (get(_HASNEXT)!=null && (Boolean)get(_HASNEXT))? true: false ;
	}
	
	public Boolean hasPrev(){
		return (get(_HASPREV)!=null && (Boolean)get(_HASPREV))? true: false ;
	}
	
	public Integer getRowNo(){
		return get(_ROWNO)==null? 0: (Integer)get(_ROWNO);
	}
	
	public void setState(boolean hasPrevious, boolean hasNext, int rowNo){
		set(_HASPREV, hasPrevious);
		set(_HASNEXT, hasNext);
		set(_ROWNO, rowNo);
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
