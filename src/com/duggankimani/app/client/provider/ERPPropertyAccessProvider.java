package com.duggankimani.app.client.provider;

import java.io.Serializable;

import com.duggankimani.app.shared.model.DataModel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class ERPPropertyAccessProvider implements PropertyAccess<DataModel>{

	public ERPPropertyAccessProvider(){
		
	}
	
	public ModelKeyProvider<DataModel> getKeyProvider(final String keyField){
		return new ModelKeyProvider<DataModel>() {
			@Override
			public String getKey(DataModel item) {
				return item.get(keyField).toString();
			}
		};
	}
	
	public ValueProvider<DataModel, Serializable> getValueProvider(String path){
		return new ERPValueProvider(path);
	}
	
}
