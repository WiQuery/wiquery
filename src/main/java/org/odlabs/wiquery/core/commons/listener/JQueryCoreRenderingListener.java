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
package org.odlabs.wiquery.core.commons.listener;

import java.io.Serializable;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.commons.CoreJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryInstantiationListener;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;

/**
 * $Id$
 * <p>
 * 	Implementation of the WiQueryPluginRenderingListener to render the jQuery bse framework
 * </p>
 * @author lionel
 * @since 1.0
 */
public class JQueryCoreRenderingListener implements WiQueryPluginRenderingListener, Serializable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3644333357586234429L;

	/**
	 * Renders needed resources for any jQuery code (e.g. core libraries).
	 * 
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener#onRender(org.odlabs.wiquery.core.commons.IWiQueryPlugin, org.odlabs.wiquery.core.commons.WiQueryResourceManager, org.apache.wicket.markup.html.IHeaderResponse)
	 */
	public void onRender(IWiQueryPlugin plugin,
			WiQueryResourceManager resourceManager, IHeaderResponse response) {
		
		if(WiQueryInstantiationListener.get().isAutoImportJQueryResource()){
			response.renderJavascriptReference(CoreJavaScriptResourceReference.get());	
		}
	}
}
