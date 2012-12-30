package com.duggankimani.app.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ERPIMAGES extends ClientBundle{

	public static final ERPIMAGES INSTANCE = GWT.create(ERPIMAGES.class);
	
	@Source("report.png")
	public ImageResource get_ReportIcon();
	
	@Source("geers_16.png")
	public ImageResource get_ProcessIcon();
	
	@Source("window.png")
	public ImageResource get_WindowIcon();
	
	@Source("Shovel.png")
	public ImageResource get_ShovelIcon();
}
