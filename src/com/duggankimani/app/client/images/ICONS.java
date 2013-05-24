package com.duggankimani.app.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ICONS extends ClientBundle{

	public static ICONS INSTANCE = GWT.create(ICONS.class);
	
	@Source("cb_partner.png")
	public ImageResource partner();
	
	@Source("info_product.png")
	public ImageResource product();
	
	@Source("search.png")
	public ImageResource search();
	
	@Source("required.gif")
	public ImageResource mandatory();
}
