package com.duggankimani.app.server.handlerutils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MLookup;
import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.LookupValue;

/**
 * 
 * @author duggan
 *
 */
public class DataReader {

	/**
	 * getSingle Result (Navigate right, left)
	 * 
	 * @param windowStatus
	 * @param action
	 * @return
	 */
	public static DataModel getData(WindowStatus windowStatus, GetDataAction action) {

		GridTab tab = windowStatus.getTab(action.getTabNo());

		//find correct logic for this
		boolean isLoaded = tab.getRowCount()>0;
		
		if (!isLoaded) {
			
			tab.initTab(false);
			
			// had been init.d
			
			tab.query(false);
		}
		
		if (tab.getRowCount() == 0) {
			//log.info("DataModel - No data to present - 0 row count");
			return new DataModel();
		}

		int row = tab.getCurrentRow();

		if(action.getRowNo()!=null && action.getRowNo()!=-1){
			//specific row requested
			row = action.getRowNo();
		}
		else if (action.getRows() != null) {
			//skip rows provided (-ve and +ve values)
			row = row + action.getRows();
		}

		tab.navigate(row);

		DataModel dataModel = getRowData(tab, false);

		return dataModel;
	}

	public static DataModel getRowData(GridTab tab, boolean useValue) {
		int currentRow=tab.getCurrentRow();
		
		GridField[] fields = tab.getFields();

		ArrayList<String> strs = tab.getParentColumnNames();

		DataModel dataModel = new DataModel();
		dataModel.setRowNo(currentRow);
		
		for (GridField field : fields) {
			Object value = tab.getValue(field);
			String columnName = field.getColumnName();
			int displayType = field.getDisplayType();

			if (value == null)
				continue;

			if(displayType==DisplayType.Search){
				if(field.getLookup()==null){
					field.loadLookup();
				}
				
				if(field.getLookup()!=null && value instanceof Integer){
					MLookup m_lookup = (MLookup)field.getLookup();
					value = m_lookup.get(value);
				}
				
				
				dataModel.set(columnName, getLookupValue(value));
				
			}else if (field.isLookup()) {
					
				if (useValue && !field.isKey() && field.getLookup() != null) {
					dataModel.set(columnName,
							field.getLookup().getDisplay(value));
				} else {
					dataModel.set(columnName, getLookupValue(value));
				}

			} else if (displayType == DisplayType.Date
					|| displayType == DisplayType.DateTime) {
				dataModel.set(columnName, getDate(value));
			} else if (displayType == DisplayType.YesNo) {
				dataModel.set(columnName, getBoolean(value));
			} else if (displayType == DisplayType.Binary) {
				throw new RuntimeException(
						"Binary -- Unsupported data type!!!!!");
			} else if (displayType == DisplayType.PAttribute) {
				throw new RuntimeException(
						"PAttribute -- Unsupported data type!!!!!");
			} else {
				dataModel.set(columnName, (Serializable) value);
			}

		}

		if (tab.getKeyColumnName() == null || tab.getKeyColumnName().isEmpty()) {
			String str = strs.get(0);
			String str2 = "";
			if(strs.size()==2)
				str2=strs.get(1);

			dataModel.set(str + "-" + str2,
					tab.getValue(str) + "-" +(str2.isEmpty()? "" : tab.getValue(str2)));
		}

		return dataModel;
	}

	public static Serializable getBoolean(Object value) {

		String yesno = value.toString();

		return yesno.equals("Y") || yesno.equals("T") || yesno.equals("true");

	}

	public static Serializable getDate(Object value) {
		if (value instanceof Timestamp)
			return new Date(((Timestamp) value).getTime());

		return (Date) value;
	}

	public static Serializable getLookupValue(Object lookup) {
		
		if(lookup==null){
			return null;
		}

		LookupValue lookupValue = new LookupValue();

		if (lookup instanceof ValueNamePair) {
			ValueNamePair lookp = (ValueNamePair) lookup;
			lookupValue = new LookupValue(lookp.getValue(), lookp.getName());

		} else if (lookup instanceof KeyNamePair) {
			KeyNamePair keyname = (KeyNamePair) lookup;
			lookupValue = new LookupValue(keyname.getKey(), keyname.getName());
		}	
		else if (lookup instanceof Integer || lookup instanceof String) {
			// return key
			return (Serializable) lookup;
		} else {
			// locations, locator
			throw new RuntimeException("Unsupported Lookup type -- "
					+ lookup.getClass());
		}

		return lookupValue;
	}

	/**
	 * Load list of data 
	 * 
	 * @param windowStatus
	 * @param action
	 * @return
	 */
	public static ArrayList<DataModel> getDataList(WindowStatus windowStatus,
			GetDataAction action) {
				
		ArrayList<DataModel> data = new ArrayList<>();

		GridTab tab = windowStatus.getTab(action.getTabNo());
		//System.err.println("###Tab - "+action.getTabNo()+" :: Win no - "+tab.getWindowNo());
		
		tab.query(false, 0, 0);	
		if(!tab.isCurrent()){		
			tab.dataRefreshAll();
		}
		
		int row = 0;
		while (row < tab.getRowCount()) {
			tab.navigate(row);
			data.add(getRowData(tab, true));
			++row;
		}

		//reset pointer to zero as opposed to leaving it at the end -- why?
		tab.navigate(0);
		
		return data;
	}

}
