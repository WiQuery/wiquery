/*
 * Copyright (c) 2009 WiQuery team
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
package org.odlabs.wiquery.core.commons;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.behavior.HeaderContributor;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;

/**
 * $Id$
 * <p>
 * 	Listens to WiQuery components instantiation and automatically binds a
 * 	{@link WiQueryCoreHeaderContributor} to these components.
 * </p>
 * <p>
 * 	The added header contributor will generated the needed JavaScript code
 *  and will import all needed resources (e.g. CSS/JavaScript files).
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
public class WiQueryInstantiationListener implements IComponentInstantiationListener, Serializable 
{

	private static final long serialVersionUID = -7398777039788778234L;

	/**
	 * Defines the global header contributor user to append all generated JS
	 */
	private WiQueryCoreHeaderContributor wickeryCoreHeaderContributor = null;
	
	/**
	 * The current theme.
	 */
	private ResourceReference themeResourceReference = new WiQueryCoreThemeResourceReference("fusion");
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.application.IComponentInstantiationListener#onInstantiation(org.apache.wicket.Component)
	 */
	public void onInstantiation(final Component component) 
	{
		if (component instanceof IWiQueryPlugin)
		{
			if (wickeryCoreHeaderContributor == null) {
				wickeryCoreHeaderContributor = new WiQueryCoreHeaderContributor();
				wickeryCoreHeaderContributor.setTheme(this.themeResourceReference);
			}
			// binding component as a plugin
			wickeryCoreHeaderContributor.addPlugin((IWiQueryPlugin) component);
			component.add(new HeaderContributor(wickeryCoreHeaderContributor));
		}
	}
	
	/**
	 * Sets the theme to use.
	 * @param themeResourceReference
	 * 			The theme as a {@link ResourceReference}
	 */
	public void setTheme(ResourceReference themeResourceReference) {
		this.themeResourceReference = themeResourceReference;
	}

}
