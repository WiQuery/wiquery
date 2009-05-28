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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Handles core JavaScript generation process of WiQuery.
 * </p>
 * <p>
 * If the request in a non ajax request, the generated JavaScript is wrapped by
 * a "dom ready" statement. Otherwise (in Ajax contexts, the generated
 * JavaScript is directly append to the given {@link AjaxRequestTarget}.
 * </p>
 * 
 * @author Benoit Bouchez
 * @author Lionel Armanet
 */
public class WiQueryCoreHeaderContributor implements Serializable,
		IHeaderContributor {

	private static final long serialVersionUID = -347081993448442637L;

	/*
	 * TODO Refactor constants below, use enum instead
	 */

	/** Standard library selector ; usefull in dev mode */
	public static final String LIBMODE_STANDARD = "";

	/** Packed library selector */
	public static final String LIBMODE_PACKED = ".packed";

	/** Minified library selector */
	public static final String LIBMODE_MINIFIED = ".min";

	private static String libraryMode = LIBMODE_STANDARD;

	/**
	 * plugins is the list of plugins to render.
	 */
	private List<IWiQueryPlugin> plugins = new ArrayList<IWiQueryPlugin>();

	/**
	 * Maps a resource manager for a given plugin.
	 */
	private Map<IWiQueryPlugin, WiQueryResourceManager> resourceManagers = new HashMap<IWiQueryPlugin, WiQueryResourceManager>();

	/**
	 * The theme used to display UI components.
	 */
	private ResourceReference themeResource;

	/**
	 * Imports core jQuery resources (CSS / JavaScript).
	 * 
	 * @param headerResponse
	 *            The {@link IHeaderResponse} to contribute to
	 */
	void importCoreResource(IHeaderResponse headerResponse) {
		headerResponse
				.renderJavascriptReference(CoreJavaScriptResourceReference
						.get());
	}

	/**
	 * Imports core jQuery UI resources (CSS / JavaScript).
	 * 
	 * @param headerResponse
	 *            The {@link IHeaderResponse} to contribute to
	 */
	void importCoreUiResource(IHeaderResponse headerResponse) {
		headerResponse.renderCSSReference(this.themeResource);
		headerResponse
				.renderJavascriptReference(CoreUIJavaScriptResourceReference
						.get());
	}

	/**
	 * Sets the {@link ResourceReference} to theme the WiQuery application.
	 * 
	 * @param themeResourceReference
	 *            A {@link ResourceReference} to define the theme to use
	 */
	public void setTheme(ResourceReference themeResourceReference) {
		this.themeResource = themeResourceReference;
	}

	/**
	 * Schedules a {@link IWiQueryPlugin} to be rendered in the current
	 * rendering phase.
	 * 
	 * @param wiqueryPlugin
	 *            The {@link IWiQueryPlugin} to attach.
	 */
	public void addPlugin(IWiQueryPlugin wiqueryPlugin) {
		// attaching a plugin resource manager for the given plugin
		WiQueryResourceManager resourceManager = new WiQueryResourceManager();
		this.resourceManagers.put(wiqueryPlugin, resourceManager);
		this.plugins.add(wiqueryPlugin);
	}

	// TODO Find a better way to manage packed, min versions
	public static String getLibraryMode() {
		return libraryMode;
	}

	// TODO Find a better way to manage packed, min versions
	public static void setLibraryMode(String libraryMode) {
		WiQueryCoreHeaderContributor.libraryMode = libraryMode;
	}

	/**
	 * Renders WiQuery's JavaScript code.
	 */
	public void renderHead(final IHeaderResponse response) {
		// Component referenceComponent = null;
		this.importCoreResource(response);
		// generating on ready query

		JsQuery jsq = new JsQuery();
		JsStatement jsStatement = new JsStatement();

		// REFACTOR A WebPage should be used instead ?
		for (IWiQueryPlugin plugin : this.plugins) {
			WiQueryResourceManager manager = resourceManagers.get(plugin);
			jsStatement.append("\t\t" + plugin.statement().render());
			// adding jQuery UI resources
			if (plugin.getClass().isAnnotationPresent(WiQueryUIPlugin.class)) {
				this.importCoreUiResource(response);
			}
			plugin.contribute(manager);
			manager.initialize(response);
		}

		jsq.setStatement(jsStatement);
		jsq.renderHead(response, RequestCycle.get().getRequestTarget());
		// after rendering, this contributor is re initialized
		plugins.clear();
		resourceManagers.clear();
	}

}