package com.duggankimani.app.server.handlers;

import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridTab;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.duggankimani.app.server.DataStatusChangedListener;
import com.duggankimani.app.shared.action.BaseAction;
import com.duggankimani.app.shared.action.BaseActionResult;
import com.duggankimani.app.shared.model.ServerStatus;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * 
 * @author duggan
 * 
 * @param <A>
 * @param <B>
 */
public abstract class BaseActionHandler<A extends BaseAction<B>, B extends BaseActionResult>
		implements ActionHandler<A, B> {

	protected CLogger log = CLogger.getCLogger(getClass());
	
	@Override
	public final B execute(A action, ExecutionContext execContext)
			throws ActionException {
		log.info("Executing command "+action.getClass().getName());
		
		B result = execute(action, action.createDefaultActionResponse(),
				execContext);

		postExecute((BaseActionResult) result);

		return result;

	}

	public abstract B execute(A action, BaseActionResult actionResult,
			ExecutionContext execContext) throws ActionException;

	@Override
	public Class<A> getActionType() {

		return null;
	}

	@Override
	public void undo(A action, B actionResult, ExecutionContext arg2)
			throws ActionException {

	}

	/**
	 * Check Messages/ Data Status/ Errors etc
	 * 
	 * 
	 * @param result
	 */
	private void postExecute(BaseActionResult result) {

		DataStatusChangedListener listener = DataStatusChangedListener.get();
		if (listener == null) {
			return;
		}

		DataStatusEvent event = listener.getEvent();
		if (event == null) {
			/**
			 * No DataStatusEvent is fired without
			 * read/write or navigate actions - meaning that 
			 * if for example you refresh the browser, the 
			 * window opens using the existing ServerSide WindowStatus
			 * and the same record is read; - no navigate,
			 * therefore no status object - resulting to
			 * no information about the state of the gridTab 
			 * such as whether the record is the first or last. This
			 * then affects the front-end not providing the previous-next menu states
			 * 
			 *  -We need to recreate this information from the
			 *  tab
			 *  
			 */
			if(listener.getCurTab()!=null){
//				System.err.println("#### generating Status :: "+listener.getCurTab().getName());
				generateServerStatus(listener.getCurTab(),result);
			}else{
//				System.err.println("#### Cannot generate CurTab is null :: "+result.getClass().getName());
			}
			return;
		}
		
		ServerStatus status = new ServerStatus();

//		String AD_Message = event.getAD_Message();
//		String colName = event.getColumnName();

		int loadedRows = event.getLoadedRows();
		status.setLoadedRows(loadedRows);

		String info = event.getInfo();
		status.setInfo(info);

		String msg = event.getMessage();
		status.setMessage(msg);

		int totalRows = event.getTotalRows();
		status.setTotalRows(totalRows);

		status.setWarning(event.isWarning());
		status.setError(event.isError());
		status.setConfirmed(event.isConfirmed());
		status.setInserting(event.isInserting());
		status.setChanged(event.isChanged());
		status.setFirstRow(event.isFirstRow());
		status.setLastRow(event.isLastRow());
		status.setLoading(event.isLoading());

		String statusLine = getStatusLine(event);
		status.setStatusLine(statusLine);

		String dbStatus = "";

		GridTab curTab = listener.getCurTab();
		setTabContextData(curTab, status);
		
		dbStatus = getDBStatus(curTab, event);
		status.setDbStatus(dbStatus);

		Object recordId = event.Record_ID;
		if (recordId != null && recordId instanceof Integer)
			status.setRecordId((Integer) recordId);

		int tableID = event.AD_Table_ID;
		status.setTableId(tableID);

//		System.err.println("Source - " + source.getClass() + " -- AD_Message- "
//				+ AD_Message + " - ColumnName- " + colName
//				+ ", - LoadedRows - " + loadedRows + ", - \n Info - " + info
//				+ ", - msg " + msg + ", - totalRows- " + totalRows);

		result.setServerStatus(status);

		DataStatusChangedListener.clear();
	}

	private void setTabContextData(GridTab curTab, ServerStatus status) {
		status.setWindowId(curTab.getAD_Window_ID());
		status.setWindowNo(curTab.getWindowNo());
		status.setTabNo(curTab.getTabNo());
		status.setReadOnly(curTab.isReadOnly());
	}

	private void generateServerStatus(GridTab curTab, BaseActionResult result) {
		ServerStatus status = new ServerStatus();
		int totalRows=curTab.getRowCount();
		int currentRow = -1;
		if(totalRows>0)
			currentRow = curTab.getCurrentRow();
		
		status.setFirstRow(currentRow==0);
		status.setLastRow(currentRow==totalRows-1);
		status.setTotalRows(totalRows);
		
		//Tab info
		setTabContextData(curTab, status);
		result.setServerStatus(status);
	}

	private String getStatusLine(DataStatusEvent e) {

		String statusLine = "";

		// Set Message / Info
		if (e.getAD_Message() != null || e.getInfo() != null) {
			StringBuffer sb = new StringBuffer();
			String msg = e.getMessage();
			if (msg != null && msg.length() > 0)
				sb.append(Msg.getMsg(Env.getCtx(), e.getAD_Message()));
			String info = e.getInfo();
			if (info != null && info.length() > 0) {
				if (sb.length() > 0 && !sb.toString().trim().endsWith(":"))
					sb.append(": ");
				sb.append(info);
			}
			if (sb.length() > 0) {
				int pos = sb.indexOf("\n");
				if (pos != -1) // replace CR/NL
					sb.replace(pos, pos + 1, " - ");
				statusLine = sb.toString();
			}
		}
		return statusLine;
	}

	private String getDBStatus(GridTab curTab, DataStatusEvent e) {

		String dbInfo = e.getMessage();
		if (curTab != null && curTab.isQueryActive())
			dbInfo = "[ " + dbInfo + " ]";

		return dbInfo;
	}

}
