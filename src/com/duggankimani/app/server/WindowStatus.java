package com.duggankimani.app.server;

import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;

public class WindowStatus {

	public GridWindowVO gridWindowV0;
	public GridWindow gridWindow;

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
			

		}else
			windowStatus = new WindowStatus(vo);


		if (init)
			windowStatus.init();

		return windowStatus;
	}
	
	public static WindowStatus getWindowStatus(int AD_Window_ID){
		return getWindowStatus(windowStatus.gridWindowV0, windowStatus.gridWindow==null);
	}
}
