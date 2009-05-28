/*
 * $license$
 */
package org.odlabs.wiquery.utils;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.protocol.http.WebApplication;
import org.odlabs.wiquery.core.commons.WiQueryInstantiationListener;

/**
 * $Id$
 * <p>
 * Defines the main entry point of any WickeXt application. Any application
 * using WickeXt must use this class.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public abstract class WiQueryWebApplication extends WebApplication {

	private WiQueryInstantiationListener wickextPluginInstantiationListener;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.protocol.http.WebApplication#init()
	 */
	@Override
	protected void init() {
		// we add a component instantiation listener to create plugin managers
		// each time a plugin is created
		wickextPluginInstantiationListener = new WiQueryInstantiationListener();
		addComponentInstantiationListener(wickextPluginInstantiationListener);
		super.init();
	}
	
	public void setTheme(ResourceReference themeResourceReference) {
		this.wickextPluginInstantiationListener.setTheme(themeResourceReference);
	}
	
//	public void setThemeResource(ResourceReference themeResource) {
//		this.wickextPluginInstantiationListener.setThemeResource(themeResource);
//	}
//	
//	public ResourceReference getThemeResource() {
//		return this.wickextPluginInstantiationListener.getThemeResource();
//	}
	
}
