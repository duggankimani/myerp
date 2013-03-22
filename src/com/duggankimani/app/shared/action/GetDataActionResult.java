package com.duggankimani.app.shared.action;

import java.util.ArrayList;

import com.duggankimani.app.shared.model.DataModel;

public class GetDataActionResult extends BaseActionResult {

	ArrayList<DataModel> dataModels=null;
	
	public GetDataActionResult() {
	}


	public GetDataActionResult(ArrayList<DataModel> data) {
		this.dataModels= data;
	}
	
	public ArrayList<DataModel> getDataModel() {
		return dataModels;
	}

	public void setDataModel(ArrayList<DataModel> dataModels) {
		this.dataModels = dataModels;
	}
}
