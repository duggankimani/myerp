package com.duggankimani.app.server;

import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.util.Env;

/**
 * 
 * @author duggan
 *
 */
public class WindowStatus {

	public GridWindowVO gridWindowV0;
	private GridWindow gridWindow;
	private GridTab curTab;
	
	public WindowStatus() {
	}

	private WindowStatus(GridWindowVO gridWindowVO) {
		this.gridWindowV0 = gridWindowVO;
	}

	public void init() {
		
		gridWindow = new GridWindow(gridWindowV0);

	}

	static WindowStatus windowStatus;

	public static WindowStatus getWindowStatus(GridWindowVO vo, boolean init) {

		if (windowStatus != null) {
			
			if (windowStatus.gridWindowV0 != null
					&& vo.AD_Window_ID != windowStatus.gridWindowV0.AD_Window_ID)
				windowStatus = new WindowStatus(vo);
			

		}else{
			//System.err.println("##New Window status - "+vo.AD_Window_ID);
			windowStatus = new WindowStatus(vo);
		}


		if (init)
			windowStatus.init();

		return windowStatus;
	}
	
	/**
	 * 
	 * @param AD_Window_ID
	 * @return
	 */
	public static WindowStatus getWindowStatus(int AD_Window_ID){
		if(WindowStatus.windowStatus==null
				|| WindowStatus.windowStatus.gridWindowV0==null
				|| WindowStatus.windowStatus.gridWindowV0.AD_Window_ID!=AD_Window_ID){
			createWindowV0(AD_Window_ID);
		}
			
		//System.err.println("WindowID ="+AD_Window_ID);
		return getWindowStatus(windowStatus.gridWindowV0, windowStatus.gridWindow==null);
	}
	
	private static void createWindowV0(int AD_Window_ID) {
		GridWindowVO gridWindowVO = GridWindowVO.create(Env.getCtx(),
				ERPSessionManager.generateWindowNo(), AD_Window_ID);

		if (gridWindowVO == null)
			throw new RuntimeException("Could not create GridWindowVO is null");

		getWindowStatus(gridWindowVO, false);		
	}

	/**
	 * 
	 * @param tabNo
	 * @return
	 */
	public GridTab getTab(int tabNo){
		if(gridWindow==null){
			gridWindow = new GridWindow(gridWindowV0);
		}
		
		gridWindow.initTab(tabNo);
		
		int curTabNo = -1;
		if(curTab!=null){
			curTabNo = curTab.getTabNo();
		}
		
		// tab has not changed
		if(curTabNo==tabNo){
			//System.err.println(curTab.getName()+" tab already exists - No need to register listener!!");
			
			//DSE removed after every request
			//check if DSE curTab is equal to this tab
			if(DataStatusChangedListener.get().getCurTab()==curTab){
				return curTab;
			}else{
				DataStatusChangedListener.get().setCurTab(curTab);
			}
		}
		
		if(curTab!=null){
			curTab.removeDataStatusListener(DataStatusChangedListener.get());
		}
		
		curTab = gridWindow.getTab(tabNo);
		
		//System.err.println("###"+curTab.getName()+" - Registering listener!!");
		curTab.addDataStatusListener(DataStatusChangedListener.get());
		DataStatusChangedListener.get().setCurTab(curTab);
		
		return curTab;
	}
	
	public boolean isSOTrx(){
		return gridWindow.isSOTrx();
	}
}
