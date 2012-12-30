package com.duggankimani.app.client.components;

import com.gwtplatform.mvp.client.View;

/**
 * Interface to declare all 
 * @author duggan
 *
 */
public interface BaseView extends View {

	public void setName(String name);

	public int getColSpan();
	
	public void setColSpan(int colSpan);
}
