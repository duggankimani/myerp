package com.duggankimani.app.shared.action;

import com.duggankimani.app.shared.model.DataModel;

public class GetDataActionResult extends BaseERPActionResult {

	DataModel dataModel=null;
	
	public GetDataActionResult() {
	}


	public GetDataActionResult(DataModel data) {
		this.dataModel= data;
	}
	
	public DataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}
}
