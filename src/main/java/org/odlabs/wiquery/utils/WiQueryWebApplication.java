/*
 * Copyright (c) 2008 Objet Direct
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
