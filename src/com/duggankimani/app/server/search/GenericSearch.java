package com.duggankimani.app.server.search;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

import com.duggankimani.app.server.WindowStatus;
import com.duggankimani.app.shared.model.LookupValue;

public class GenericSearch {

	static CLogger log = CLogger.getCLogger(GenericSearch.class);
	
	public static List<LookupValue> search(WindowStatus ws, int tabNo, String columnName, String query){
		boolean isSOTrx = ws.isSOTrx();
		
		List<LookupValue> results = new ArrayList<LookupValue>();
		
		GridTab curTab = ws.getTab(tabNo);
		GridField field = curTab.getField(columnName);
		
		if(field==null){
			throw new IllegalStateException("Search Field with name- "+columnName+" Not Found");
		}
		
		System.err.println("Column "+columnName+" - AD_Ref_Value_ID= "+field.getAD_Reference_Value_ID()+"" +
				"\n - ColumnSQL = "+field.getColumnSQL(true)+""+" - DynamicValidation - ");
		
		/**
		 * TODO: Column SQL/ Any Filtering information available on the field
		 */
		
		//Target Table from which data will be searched
		Integer AD_Table_ID = getTableID(curTab.getAD_Table_ID(), columnName);
				
		if(AD_Table_ID==null || AD_Table_ID==0){
			throw new IllegalStateException("Table Not Found for column- "+columnName);
		}
		
		String tableName = MTable.getTableName(Env.getCtx(), AD_Table_ID);
		
		List<String> cols = getSearchColumns(AD_Table_ID);
		
		
		/**
		 * Pick 3 main fields
		 */
		
		StringBuffer sql = new StringBuffer("SELECT ");
		
		StringBuffer whereClause = new StringBuffer(" Where ");
		
		if(query==null)
			query="";
		
		String param = DB.TO_STRING("%"+query+"%");
		
		int colCount = 0;
	
		//Key Column - ColumnName is ID field
		sql.append(tableName+"_ID");	
		
		//where (a or b or c) and ad_client=? and ad_org=?
		whereClause.append("(");
		
		if(cols.contains("VALUE")){	
			sql.append(", Value");
			whereClause.append("Value ilike "+param);
		
			++colCount;
		}
		
		if(cols.contains("DOCUMENTNO")){
			sql.append(", DocumentNo ");
			
			if(colCount>0){
				whereClause.append(" or DocumentNo ilike "+param);
			}else{
				
				whereClause.append("DocumentNo ilike "+param);
			}
			
			++colCount;
		}
		
		if(cols.contains("NAME")){
			sql.append(", Name");
			
			if(colCount>0){				
				whereClause.append(" or Name ilike "+param);
			}else{
				whereClause.append("Name ilike "+param);
			}
			
			++colCount;
		}
		
		whereClause.append(")");
						
		sql.append(" FROM "+tableName)
		.append(whereClause.toString())
		.append(" and AD_Client_ID=?")
		.append(" and AD_Org_ID=?");
		//.append(" limit 60");
		
		log.fine("Search Query: "+sql.toString());
		
		System.err.println("Search Query: "+sql.toString());
		
		if(colCount>0){
			
			
			String trxName = "TrxGenericSearch-"+new Random().nextInt(10000000);
			
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				 pstmt= DB.prepareStatement(sql.toString(), trxName);
				 pstmt.setInt(1, Env.getAD_Client_ID(Env.getCtx()));
				 pstmt.setInt(2, Env.getAD_Org_ID(Env.getCtx()));
				 
				 rs = pstmt.executeQuery();
				 
				 while(rs.next()){
					 Integer key = rs.getInt(1);
					 
					 StringBuffer vals = new StringBuffer();
					 vals.append(rs.getString(2));
					 
					 if(colCount>2)
						 vals.append(" | "+rs.getString(3));
					 

					 if(colCount>3)
						 vals.append(" | "+rs.getString(4));
					 
					 results.add(new LookupValue(key, vals.toString()));
				 }
			}catch(SQLException e){
				log.saveError("SQLError", e);
				throw new RuntimeException(e);
			}finally{
				DB.close(rs, pstmt);
			}
			
			
		}
		return results;
	}
	
	private static int getTableID(Integer AD_Table_ID, String columnName) {
		String sql = "select t.ad_table_id" +
				" from ad_column c "+
		" inner join ad_reference r on (c.ad_reference_id=r.ad_reference_id)" +
		" inner join ad_reference r1 on (r1.ad_reference_id=c.ad_reference_value_id) "+
		" inner join ad_ref_table t on (t.ad_reference_id=r1.ad_reference_id)" +
		" where c.ad_table_id=? and columnName=?";

		//source table id
		String trxName = "Trx-getSearchColumns-"+new Random().nextInt(100);
		
		int tableId = DB.getSQLValue(trxName, sql, AD_Table_ID, columnName);
		
		if(tableId<0)
			tableId = getTableId(columnName);

		return tableId;
	}

	private static int getTableId(String columnName) {
		
		int tableId=0;
		
		if(columnName.endsWith("_ID")){
			String tableName = columnName.replaceAll("_ID", "");
			tableId = MTable.getTable_ID(tableName);
		}		
		
		return tableId;
	}

	static List<String> getSearchColumns(int AD_Table_ID){
		List<String> cols = new ArrayList<>();
		
		String sql = "SELECT c.ColumnName, c.AD_Reference_ID, c.IsKey, f.IsDisplayed, c.AD_Reference_Value_ID, c.ColumnSql "
				+ "FROM AD_Column c"
				+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID)"
				+ " INNER JOIN AD_Tab tab ON (t.AD_Window_ID=tab.AD_Window_ID)"
				+ " INNER JOIN AD_Field f ON (tab.AD_Tab_ID=f.AD_Tab_ID AND f.AD_Column_ID=c.AD_Column_ID) "
				+ "WHERE t.AD_Table_ID=? "
				+ " AND (c.IsKey='Y' OR "
				+ " (f.IsEncrypted='N' AND f.ObscureType IS NULL)) and c.AD_Reference_ID in (?,?,?)"
				+ "ORDER BY c.IsKey DESC, f.SeqNo";

		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			pstmt.setInt(2, DisplayType.String);
			pstmt.setInt(3, DisplayType.Text);
			pstmt.setInt(4, DisplayType.TextLong);	
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String columnName = rs.getString(1);
				//System.out.println(columnName);
				cols.add(columnName.toUpperCase());				
			}	
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.saveError("Error",e);
			
		}


		return cols;
	}

}
