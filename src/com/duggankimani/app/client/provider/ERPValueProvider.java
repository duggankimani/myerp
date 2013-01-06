package com.duggankimani.app.client.provider;

import java.io.Serializable;

import com.duggankimani.app.shared.model.DataModel;
import com.sencha.gxt.core.client.ValueProvider;

class ERPValueProvider implements ValueProvider<DataModel, Serializable>{

	private String path;
	
	public ERPValueProvider() {
	}
	
	ERPValueProvider(String path){
		this.path = path;	
	}
	
	@Override
	public Serializable getValue(DataModel object) {
		return (Serializable) object.get(path);
	}

	@Override
	public void setValue(DataModel data, Serializable value) {
		data.set(path, value);
	}

	@Override
	public String getPath() {
		return path;
	}

}
