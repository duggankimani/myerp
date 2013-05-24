package com.duggankimani.app.server.handlerutils;

import java.util.Set;

import org.compiere.model.GridTab;

import com.duggankimani.app.shared.model.DataModel;

public class DataWriter {

	public static void bind(DataModel data, GridTab curTab){
		Set<String> keys = data.getData().keySet();
		for(String key: keys){
			if(curTab.getField(key) !=null){
				//?? wat abt callouts?
				curTab.setValue(key, data.get(key));
			}
		}
	}
}
