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

import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.css.ICssCompressor;
import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.apache.wicket.settings.def.ResourceSettings;

/**
 * $Id: WiQuerySettings.java 1050 2011-06-22 11:18:55Z hielke.hoeve@gmail.com $
 * <p>
 * Bean to get the wiQuery settings
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class WiQuerySettings implements Serializable
{
	private static final long serialVersionUID = 4047364411001306905L;

	/**
	 * Get {@link WiQuerySettings} for current thread.
	 * 
	 * @return The settings
	 */
	public static WiQuerySettings get()
	{
		WiQuerySettings instance =
			Application.get().getMetaData(WiQueryInitializer.WIQUERY_INSTANCE_KEY);

		if (instance == null)
		{
			throw new WicketRuntimeException(new StringBuilder()
				.append("There is no WiQueryInstantiationListener attached to the application ")
				.append(Thread.currentThread().getName()).toString());
		}

		return instance;
	}

	private boolean autoImportWiQueryJavaScriptResource;

	private boolean autoImportWiQueryStyleSheetResource;

	private boolean autoImportJQueryUIJavaScriptResource;

	private boolean autoImportJQueryUIStyleSheetResource;

	private boolean enableWiqueryResourceManagement;

	public WiQuerySettings()
	{
		super();

		setEnableWiqueryResourceManagement(true);
	}

	public boolean isAutoImportWiQueryJavaScriptResource()
	{
		return autoImportWiQueryJavaScriptResource;
	}

	/**
	 * If set to <code>false</code>, no JavaScript resources implementing
	 * {@link IWiQueryJavaScriptResourceReference} are contributed by the framework, which
	 * means the user is responsible to add required resources <strong>(javascript
	 * files)</strong> for jQueryUI to work. Useful if one wants to manage resources
	 * globally or use a CDN network to load resources.
	 * <p/>
	 * <b>Warning:</b> If version does not match to the version contributed by the
	 * framework, functionality may be harmed!
	 * 
	 * @param autoImportWiQueryJavaScriptResource
	 *            <code>true</code> to let the framework import required resources.
	 *            <code>false</code> to disable automatic resources contribution by the
	 *            framework.
	 * @see #setEnableWiqueryResourceManagement(boolean)
	 */
	public void setAutoImportWiQueryJavaScriptResource(boolean autoImportWiQueryJavaScriptResource)
	{
		this.autoImportWiQueryJavaScriptResource = autoImportWiQueryJavaScriptResource;
	}

	public boolean isAutoImportWiQueryStyleSheetResource()
	{
		return autoImportWiQueryStyleSheetResource;
	}

	/**
	 * If set to <code>false</code>, no Css resources implementing
	 * {@link IWiQueryCssResourceReference} are contributed by the framework, which means
	 * the user is responsible to add required resources <strong>(css files)</strong> for
	 * jQueryUI to work. Useful if one wants to manage resources globally or use a CDN
	 * network to load resources.
	 * <p/>
	 * <b>Warning:</b> If version does not match to the version contributed by the
	 * framework, functionality may be harmed!
	 * 
	 * @param autoImportWiQueryStyleSheetResource
	 *            <code>true</code> to let the framework import required resources.
	 *            <code>false</code> to disable automatic resources contribution by the
	 *            framework.
	 * @see #setEnableWiqueryResourceManagement(boolean)
	 */
	public void setAutoImportWiQueryStyleSheetResource(boolean autoImportWiQueryStyleSheetResource)
	{
		this.autoImportWiQueryStyleSheetResource = autoImportWiQueryStyleSheetResource;
	}

	public boolean isAutoImportJQueryUIJavaScriptResource()
	{
		return autoImportJQueryUIJavaScriptResource;
	}

	/**
	 * If set to <code>false</code>, no jQueryUI JavaScript resources are contributed by
	 * the framework, which means the user is responsible to add required resources
	 * <strong>(javascript files)</strong> for jQueryUI to work. Useful if one wants to
	 * manage resources globally or use a CDN network to load resources.
	 * <p/>
	 * <b>Warning:</b> If version does not match to the version contributed by the
	 * framework, functionality may be harmed!
	 * 
	 * @param autoImportJQueryUIJavaScriptResource
	 *            <code>true</code> to let the framework import required resources.
	 *            <code>false</code> to disable automatic resources contribution by the
	 *            framework.
	 * @see #setEnableWiqueryResourceManagement(boolean)
	 */
	public void setAutoImportJQueryUIJavaScriptResource(boolean autoImportJQueryUIJavaScriptResource)
	{
		this.autoImportJQueryUIJavaScriptResource = autoImportJQueryUIJavaScriptResource;
	}

	public boolean isAutoImportJQueryUIStyleSheetResource()
	{
		return autoImportJQueryUIStyleSheetResource;
	}

	/**
	 * If set to <code>false</code>, no jQueryUI CSS resources are contributed by the
	 * framework, which means the user is responsible to add required resources
	 * <strong>(css files)</strong> for jQueryUI to work. Useful if one wants to manage
	 * resources globally or use a CDN network to load resources.
	 * <p/>
	 * <b>Warning:</b> If version does not match to the version contributed by the
	 * framework, functionality may be harmed!
	 * 
	 * @param autoImportJQueryUIStyleSheetResource
	 *            <code>true</code> to let the framework import required resources.
	 *            <code>false</code> to disable automatic resources contribution by the
	 *            framework.
	 * @see #setEnableWiqueryResourceManagement(boolean)
	 */
	public void setAutoImportJQueryUIStyleSheetResource(boolean autoImportJQueryUIStyleSheetResource)
	{
		this.autoImportJQueryUIStyleSheetResource = autoImportJQueryUIStyleSheetResource;
	}

	/**
	 * If set to <code>false</code>, <b>all</b> resource contributions by WiQuery are
	 * disabled. No jQuery or jQueryUI resource are contributed by the framework, nor any
	 * resources from plugins. Useful if one wants to manage resources globally or use a
	 * CDN network to load resources.
	 * <p/>
	 * <b>Warning:</b> By setting this to <code>false</code> the frameworks functionality
	 * (or parts of it) is not guaranteed anymore! Activate only if you know what you do
	 * and import required resources manually.
	 */
	public boolean isEnableWiqueryResourceManagement()
	{
		return enableWiqueryResourceManagement;
	}

	/**
	 * If set to <code>false</code>, <b>all</b> resource contributions by Wiquery are
	 * disabled. No jQuery or jQueryUI resource are contributed by the framework, nor any
	 * resources from plugins. Useful if one wants to manage resources globally or use a
	 * CDN network to load resources.
	 * <p/>
	 * <b>Warning:</b> By setting this to <code>false</code> the frameworks functionality
	 * (or parts of it) is not guaranteed anymore! Activate only if you know what you do
	 * and import required resources manually.
	 */
	public void setEnableWiqueryResourceManagement(boolean enableWiqueryResourceManagement)
	{
		this.autoImportWiQueryJavaScriptResource = enableWiqueryResourceManagement;
		this.autoImportWiQueryStyleSheetResource = enableWiqueryResourceManagement;

		this.autoImportJQueryUIJavaScriptResource = enableWiqueryResourceManagement;
		this.autoImportJQueryUIStyleSheetResource = enableWiqueryResourceManagement;

		this.enableWiqueryResourceManagement = enableWiqueryResourceManagement;
	}

	/**
	 * <strike>
	 * <p>
	 * When true wiquery delivers minimized versions js files, when false wiquery delivers
	 * normal (non-minimized) versions. The default value depends on whether an
	 * {@link IJavaScriptCompressor} is used or not.
	 * </p>
	 * <p>
	 * Always provide the normal (non-minimized) version, wiquery will reference to the
	 * minimized version when {@link WiQuerySettings#isMinifiedJavaScriptResources()} is
	 * true.
	 * </p>
	 * <p>
	 * The filename format for the 2 versions is:
	 * <ul>
	 * <li>Normal version: <i>foo.js</i></li>
	 * <li>Minimized version: <i>foo.min.js</i></li>
	 * </ul>
	 * </p>
	 * </strike>
	 * 
	 * @return the value of {@link ResourceSettings#getUseMinifiedResources()}
	 * @deprecated
	 * @see ResourceSettings#getUseMinifiedResources()
	 */
	@Deprecated
	public boolean isMinifiedJavaScriptResources()
	{
		return Application.get().getResourceSettings().getUseMinifiedResources();
	}

	/**
	 * Since wicket 6.0 this interface is no longer needed, nearly all of WiQuery core's
	 * inner workings have been ported to Wicket 6.0. Use
	 * {@link ResourceSettings#setUseMinifiedResources(Boolean)} to use minified
	 * resources.
	 * 
	 * <strike>Sets the minifiedJavaScriptResources option</strike>
	 * 
	 * @param minifiedJavaScriptResources
	 * @see ResourceSettings#getUseMinifiedResources()
	 * @deprecated Since wicket 6.0 this interface is no longer needed, nearly all of
	 *             WiQuery core's inner workings have been ported to Wicket 6.0. Use
	 *             {@link ResourceSettings#setUseMinifiedResources(Boolean)} to use
	 *             minified resources.
	 */
	@Deprecated
	public void setMinifiedJavaScriptResources(boolean minifiedJavaScriptResources)
	{
		Application.get().getResourceSettings()
			.setUseMinifiedResources(minifiedJavaScriptResources);
	}

	/**
	 * <strike>
	 * <p>
	 * When true wiquery delivers minimized versions css files, when false wiquery
	 * delivers normal (non-minimized) versions. The default value depends on whether an
	 * {@link ICssCompressor} is used or not.
	 * </p>
	 * <p>
	 * Always provide the normal (non-minimized) version, wiquery will reference to the
	 * minimized version when {@link WiQuerySettings#isMinifiedStyleSheetResources()} is
	 * true.
	 * </p>
	 * <p>
	 * The filename format for the 2 versions is:
	 * <ul>
	 * <li>Normal version: <i>foo.css</i></li>
	 * <li>Minimized version: <i>foo.min.css</i></li>
	 * </ul>
	 * </p>
	 * </strike>
	 * 
	 * @return the state of the minifiedResources option.
	 * @deprecated
	 * @see ResourceSettings#getUseMinifiedResources()
	 */
	@Deprecated
	public boolean isMinifiedStyleSheetResources()
	{
		return Application.get().getResourceSettings().getUseMinifiedResources();
	}

	/**
	 * Since wicket 6.0 this interface is no longer needed, nearly all of WiQuery core's
	 * inner workings have been ported to Wicket 6.0. Use
	 * {@link ResourceSettings#setUseMinifiedResources(Boolean)} to use minified
	 * resources.
	 * 
	 * <strike>Sets the minifiedStyleSheetResources option</strike>
	 * 
	 * @param minifiedStyleSheetResources
	 * @see ResourceSettings#getUseMinifiedResources()
	 * @deprecated Since wicket 6.0 this interface is no longer needed, nearly all of
	 *             WiQuery core's inner workings have been ported to Wicket 6.0. Use
	 *             {@link ResourceSettings#setUseMinifiedResources(Boolean)} to use
	 *             minified resources.
	 */
	@Deprecated
	public void setMinifiedStyleSheetResources(boolean minifiedStyleSheetResources)
	{
		Application.get().getResourceSettings()
			.setUseMinifiedResources(minifiedStyleSheetResources);
	}
}
