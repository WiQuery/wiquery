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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.javascript.IJavascriptCompressor;
import org.apache.wicket.javascript.NoOpJavascriptCompressor;
import org.apache.wicket.request.resource.ResourceReference;
import org.odlabs.wiquery.core.commons.compressed.old.WiQueryYUICompressedStyleSheetResource;
import org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener;

/**
 * $Id$
 * <p>
 * Bean to get the wiQuery settings
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class WiQuerySettings implements Serializable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 4047364411001306905L;

	/**
	 * Get {@link WiQuerySettings} for current thread.
	 * 
	 * @return The settings
	 */
	public static WiQuerySettings get() {
		WiQuerySettings instance = Application.get().getMetaData(
				WiQueryInitializer.WIQUERY_INSTANCE_KEY);

		if (instance == null) {
			throw new WicketRuntimeException(
					"There is no WiQueryInstantiationListener attached to the application "
							+ Thread.currentThread().getName());
		}

		return instance;
	}

	// Properties
	private boolean autoImportJQueryResource;
	private boolean enableResourcesMerging;
	private List<WiQueryPluginRenderingListener> listeners;
	private ResourceReference jQueryCoreResourceReference;
	private boolean minifiedResources;
	private boolean autoImportJQueryUIResource;
	private boolean enableWiqueryResourceManagement;
	private boolean embedGeneratedStatements;

	/**
	 * Default constructor
	 */
	public WiQuerySettings() {
		super();

		this.autoImportJQueryUIResource = true;
		this.enableWiqueryResourceManagement = true;
		this.embedGeneratedStatements = false;

		setAutoImportJQueryResource(true);
		setEnableResourcesMerging(false);
		setJQueryCoreResourceReference(null);

		listeners = new ArrayList<WiQueryPluginRenderingListener>();

		IJavascriptCompressor compressor = Application.get()
				.getResourceSettings().getJavascriptCompressor();
		setMinifiedResources(compressor != null
				&& !(compressor instanceof NoOpJavascriptCompressor));
	}

	/**
	 * Method adding a {@link WiQueryPluginRenderingListener}
	 * 
	 * @param listener
	 * @return the state
	 */
	public boolean addListener(WiQueryPluginRenderingListener listener) {
		return listeners.add(listener);
	}

	/**
	 * @return the list of listener from the listeners option
	 */
	public ListIterator<WiQueryPluginRenderingListener> getListeners() {
		return listeners.listIterator();
	}

	/**
	 * @return the state of the autoImportJQueryResource option
	 */
	public boolean isAutoImportJQueryResource() {
		return autoImportJQueryResource;
	}

	/**
	 * @return the state of the enableResourcesMerging option
	 */
	public boolean isEnableResourcesMerging() {
		return enableResourcesMerging;
	}

	/**
	 * @return the state of the embedGeneratedStatements option
	 */
	public boolean isEmbedGeneratedStatements() {
		return embedGeneratedStatements;
	}

	/**
	 * <p>
	 * When true wiquery delivers minimized versions js/css files, when false
	 * wiquery delivers normal (non-minimized) versions. The default value
	 * depends on whether an {@link IJavascriptCompressor} is used or not.
	 * </p>
	 * <p>
	 * This setting also enables the
	 * {@link WiQueryYUICompressedStyleSheetResource} to be used.
	 * </p>
	 * <p>
	 * Always provide the normal (non-minimized) version, wiquery will reference
	 * to the minimized version when
	 * {@link WiQuerySettings#isCompressedJavascript()} is true.
	 * </p>
	 * <p>
	 * The filename format for the 2 versions is:
	 * <ul>
	 * <li>Normal version: <i>foo.js</i> / <i>foo.css</i></li>
	 * <li>Minimized version: <i>foo.min.js</i> / <i>foo.min.css</i></li>
	 * </ul>
	 * </p>
	 * 
	 * @return the state of the minifiedResources option.
	 */
	public boolean isMinifiedResources() {
		return minifiedResources;
	}

	/**
	 * Set the autoImportJQueryResource option
	 * 
	 * @param autoImportJQueryResource
	 */
	public void setAutoImportJQueryResource(boolean autoImportJQueryResource) {
		this.autoImportJQueryResource = autoImportJQueryResource;
	}

	/**
	 * Set the enableResourcesMerging option
	 * 
	 * @param enableResourcesMerging
	 */
	public void setEnableResourcesMerging(boolean enableResourcesMerging) {
		this.enableResourcesMerging = enableResourcesMerging;
	}

	/**
	 * If set to <code>false</code> (default), the generated JavaScript
	 * statements will be loaded using a dynamic resource named
	 * xxxxxxxx-wiquery-gen.js. To embed the code in your HTML page, set this
	 * option to <code>true</code>.
	 * 
	 * @param embedGeneratedStatements
	 */
	public void setEmbedGeneratedStatements(boolean embedGeneratedStatements) {
		this.embedGeneratedStatements = embedGeneratedStatements;
	}

	/**
	 * @return the {@link ResourceReference} where we can find the jQuery core
	 */
	public ResourceReference getJQueryCoreResourceReference() {
		return jQueryCoreResourceReference;
	}

	/**
	 * Set the jQuery core to use
	 * 
	 * @param jQueryCoreResourceReference
	 */
	public void setJQueryCoreResourceReference(
			ResourceReference jQueryCoreResourceReference) {
		this.jQueryCoreResourceReference = jQueryCoreResourceReference;
	}

	/**
	 * Sets the minifiedResources option
	 * 
	 * @param minifiedResources
	 * @see #isMinifiedResources()
	 */
	public void setMinifiedResources(boolean minifiedResources) {
		this.minifiedResources = minifiedResources;
	}

	public boolean isAutoImportJQueryUIResource() {
		return autoImportJQueryUIResource;
	}

	/**
	 * If set to <code>false</code>, no jQueryUI resources are contributed by
	 * the framework, which means the user is responsible to add required
	 * resources (javascript and css files) for jQueryUI to work. Useful if one
	 * wants to manage resources globally or use a CDN network to load
	 * resources.
	 * <p/>
	 * <b>Warning:</b> If version does not match to the version contributed by
	 * the framework, functionality may be harmed!
	 * 
	 * @param autoImportJQueryUIResource
	 *            <code>true</code> to let the framework import required
	 *            resources. <code>false</code> to disable automatic resources
	 *            contribution by the framework.
	 * @see #setEnableWiqueryResourceManagement(boolean)
	 */
	public void setAutoImportJQueryUIResource(boolean autoImportJQueryUIResource) {
		this.autoImportJQueryUIResource = autoImportJQueryUIResource;
	}

	public boolean isEnableWiqueryResourceManagement() {
		return enableWiqueryResourceManagement;
	}

	/**
	 * If set to <code>false</code>, <b>all</b> resource contributions by
	 * Wiquery are disabled. No jQuery or jQueryUI resource are contributed by
	 * the framework, nor any resources from plugins. Useful if one wants to
	 * manage resources globally or use a CDN network to load resources.
	 * <p/>
	 * <b>Warning:</b> By setting this to <code>false</code> the frameworks
	 * functionality (or parts of it) is not guaranteed anymore! Activate only
	 * if you know what you do and import required resources manually.
	 * <p/>
	 * Sets internally {@link #setAutoImportJQueryResource(boolean)} and
	 * {@link #setAutoImportJQueryUIResource(boolean)} to the given value.
	 * 
	 * @param enableWiqueryResourceManagement
	 *            <code>false</code> disables all resources contributions by the
	 *            framework. <code>true</code> enables it (default).
	 * @see #setAutoImportJQueryResource(boolean)
	 * @see #setAutoImportJQueryUIResource(boolean)
	 */
	public void setEnableWiqueryResourceManagement(
			boolean enableWiqueryResourceManagement) {
		this.autoImportJQueryUIResource = enableWiqueryResourceManagement;
		this.autoImportJQueryResource = enableWiqueryResourceManagement;
		this.enableWiqueryResourceManagement = enableWiqueryResourceManagement;
	}

}
