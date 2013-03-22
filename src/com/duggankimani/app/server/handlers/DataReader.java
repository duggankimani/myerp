package com.duggankimani.app.server.handlers;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;

import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.action.GetDataAction;
import com.duggankimani.app.shared.model.DataModel;
import com.duggankimani.app.shared.model.LookupValue;

public class DataReader {

	/**
	 * getSingle Result (Navigate right, left)
	 * 
	 * @param windowStatus
	 * @param action
	 * @return
	 */
	public static DataModel getData(WindowStatus windowStatus, GetDataAction action) {

		GridTab tab = windowStatus.gridWindow.getTab(action.getTabNo());

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
			row = action.getRowNo();
		}
		else if (action.getRows() != null) {
			row = row + action.getRows();
		}

		tab.navigate(row);

		DataModel dataModel = getRowData(tab, false);

		return dataModel;
	}

	public static DataModel getRowData(GridTab tab, boolean useValue) {

		int rows = tab.getRowCount();
		int currentRow=tab.getCurrentRow();
		
		GridField[] fields = tab.getFields();

		ArrayList<String> strs = tab.getParentColumnNames();

		DataModel dataModel = new DataModel();
		dataModel.setState(currentRow!=0, currentRow!=rows-1, currentRow);
		
		for (GridField field : fields) {
			Object value = tab.getValue(field);
			String columnName = field.getColumnName();
			int displayType = field.getDisplayType();

			if (value == null)
				continue;

			if (field.isLookup()) {
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

		LookupValue lookupValue = new LookupValue();

		if (lookup instanceof ValueNamePair) {
			ValueNamePair lookp = (ValueNamePair) lookup;
			lookupValue = new LookupValue(lookp.getValue(), lookp.getName());

		} else if (lookup instanceof KeyNamePair) {
			KeyNamePair keyname = (KeyNamePair) lookup;
			lookupValue = new LookupValue(keyname.getKey(), keyname.getName());
		} else if (lookup instanceof Integer || lookup instanceof String) {
			// return key
			return (Serializable) lookup;
		} else {
			// locations, locator
			throw new RuntimeException("Unsupported Lookup type -- "
					+ lookup.getClass());
		}

		return lookupValue;
	}

	public static ArrayList<DataModel> getDataList(WindowStatus windowStatus,
			GetDataAction action) {

		ArrayList<DataModel> data = new ArrayList<>();

		windowStatus.gridWindow.initTab(action.getTabNo());
		GridTab tab = windowStatus.gridWindow.getTab(action.getTabNo());

		if (!tab.isOpen())
			tab.query(true, 0, 0);

		int row = 0;
		while (row < tab.getRowCount()) {
			tab.navigate(row);
			data.add(getRowData(tab, true));
			++row;
		}

		tab.navigate(0);
		
		return data;
	}

}