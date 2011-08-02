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
package org.odlabs.wiquery.core;

import java.io.Serializable;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.resources.CoreJavaScriptResourceReference;

/**
 * $Id: JQueryCoreRenderingListener.java 1050 2011-06-22 11:18:55Z hielke.hoeve@gmail.com
 * $
 * <p>
 * Implementation of the WiQueryPluginRenderingListener to render the jQuery base library,
 * needed for any {@link IWiQueryPlugin} to work.
 * </p>
 * 
 * @author lionel
 * @since 1.0
 */
public class JQueryCoreRenderingListener implements WiQueryPluginRenderingListener, Serializable
{
	private static final long serialVersionUID = 3644333357586234429L;

	private static JQueryCoreRenderingListener INSTANCE = new JQueryCoreRenderingListener();

	private JQueryCoreRenderingListener()
	{
	}

	public static JQueryCoreRenderingListener getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Renders needed resources for any jQuery code (e.g. core libraries).
	 */
	public void onRender(IWiQueryPlugin plugin, IHeaderResponse response)
	{
		response.renderJavaScriptReference(CoreJavaScriptResourceReference.get());
	}
}
