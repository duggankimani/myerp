package com.duggankimani.app.server.handlerutils;

import java.util.ArrayList;

import org.compiere.model.MMenu;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.util.Env;

import com.duggankimani.app.shared.model.DisplayType;
import com.duggankimani.app.shared.model.FieldModel;
import com.duggankimani.app.shared.model.ProcessModel;

import static com.duggankimani.app.server.handlerutils.MetaCreator.*;

public class ProcessCreator {

	public static ProcessModel getProcessModel(Integer AD_Menu_ID){
		String trx = null;
		
		MMenu menu = new MMenu(Env.getCtx(), AD_Menu_ID, trx);
		
		int AD_Process_ID = menu.getAD_Process_ID();
		
		MProcess process = new MProcess(Env.getCtx(), AD_Process_ID, trx);
		
		return getProcessModel(process);
	}

	private static ProcessModel getProcessModel(MProcess process) {

		MProcessPara[] params = process.getParameters();
		
		ArrayList<FieldModel> parameters = getFields(params);
		
		ProcessModel model = new ProcessModel();
		model.setName(process.getName());
		model.setProcessId(process.get_ID());
		model.setFields(parameters);
		model.setReport(process.isReport());
		model.setProcess(process.isJavaProcess());
		
		
		return model;
	}

	private static ArrayList<FieldModel> getFields(MProcessPara[] params) {
		ArrayList<FieldModel> fields = new ArrayList<>();
		
		for(MProcessPara para: params){
			FieldModel model = getFields(para);
			fields.add(model);
		}
		
		return fields;
	}

	private static FieldModel getFields(MProcessPara para) {
		para.getColumnName();
		para.get_Table_ID();
		para.get_TableName();
		para.getAD_Element_ID();
		para.getValueMax();
		para.getValueMin();
		para.getAD_Process_ID();
		para.getAD_Reference_ID();//Display Type
		para.getAD_Reference_Value_ID();//
		para.getDescription();
		para.getDefaultValue();
		
		para.getName();
		para.getKeyNamePair();
		
		
		DisplayType type = DisplayType.getDisplayType(para.getAD_Reference_ID());
		FieldModel model = new FieldModel(para.getName(), type);
		model.setDescription(para.getDescription());
		model.setDefaultValue(para.getDefaultValue());
		model.setColumnName(para.getColumnName());
		
		
		boolean mandatory=false;
		boolean onlyActive=true;
		
		if(para.isLookup()){
			model.setLookupValues(getLookupValues(para.getLookup(), mandatory, onlyActive));
		}
		
		
		return model;
	}
	
}
