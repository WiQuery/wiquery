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

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.util.visit.Visit;
import org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener;
import org.odlabs.wiquery.core.commons.merge.WiQueryHeaderResponse;
import org.odlabs.wiquery.core.commons.merge.WiQueryMergedJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.merge.WiQueryMergedStyleSheetResourceReference;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;

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
 * <p>
 * You should not add this yourself but instead should let the {@link WiQueryInstantiationListener} handle this for you
 * </p>
 * 
 * @author Benoit Bouchez
 * @author Lionel Armanet
 * @author Hielke Hoeve
 * @author Emond Papegaaij
 */
public class WiQueryCoreHeaderContributor extends Behavior implements Serializable
		 {
	private static class WiQueryPluginCollector implements IVisitor<Component, Void> {
		private List<IWiQueryPlugin> plugins = new ArrayList<IWiQueryPlugin>();

		private WiQueryPluginCollector() {
		}

		public List<IWiQueryPlugin> getPlugins() {
			return plugins;
		}

		public void component(Component component, IVisit<Void> visit) {
			if (component.determineVisibility()) {
				if (component instanceof IWiQueryPlugin) {
					plugins.add((IWiQueryPlugin) component);
				}
				for (Behavior behavior : component.getBehaviors()) {
					if (behavior instanceof IWiQueryPlugin
							&& behavior.isEnabled(component)) {
						plugins.add((IWiQueryPlugin) behavior);
					}
				}
			} else {
				visit.dontGoDeeper();
			}
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -347081993448442637L;

	/**
	 * meta data for WiQueryCoreHeaderContributor.
	 */
	private static final MetaDataKey<Boolean> WIQUERY_KEY = new MetaDataKey<Boolean>() {
		private static final long serialVersionUID = 1L;
	};
	private static final MetaDataKey<Long> WIQUERY_PAGE_KEY = new MetaDataKey<Long>() {
		private static final long serialVersionUID = 1L;
	};

	private Component owner;

	/**
	 * Default constructor. Declares a standard configuration for listeners,
	 * e.g. uses jQuery and jQuery UI listeners by default.
	 */
	public WiQueryCoreHeaderContributor(Component component) {
		this.owner = component;
		this.owner.setMetaData(WiQueryRenderingListener.WI_QUERY_RENDERED, true);
	}

	/**
	 * Renders WiQuery's JavaScript code.
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		AjaxRequestTarget ajaxRequestTarget = AjaxRequestTarget.get();
		if (ajaxRequestTarget == null) {
			renderResponse(response, component.getPage());
		} else {
			renderAjaxResponse(response, ajaxRequestTarget);
		}
	}

	private void renderResponse(final IHeaderResponse response, Page page) {
		Long renderTime = page.getMetaData(WIQUERY_PAGE_KEY);
		Boolean rendered = renderTime != null
				&& renderTime.equals(RequestCycle.get().getStartTime());
		page.setMetaData(WIQUERY_PAGE_KEY, RequestCycle.get().getStartTime());
		RequestCycle.get().setMetaData(WIQUERY_KEY, Boolean.TRUE);
		if (rendered == null || !rendered) {
			WiQuerySettings settings = WiQuerySettings.get();

			final List<WiQueryPluginRenderingListener> pluginRenderingListeners = settings.getListeners();

			WiQueryPluginCollector visitor = new WiQueryPluginCollector();
			if (page != null) {
				page.visitChildren(visitor);
				visitor.component(page, new Visit<Void>());
			}

			WiQueryHeaderResponse wiQueryHeaderResponse = new WiQueryHeaderResponse();
			IHeaderResponse headerResponse;
			if (settings.isEnableResourcesMerging()) {
				wiQueryHeaderResponse.setIHeaderResponse(response);
				headerResponse = wiQueryHeaderResponse;
			} else {
				headerResponse = response;
			}

			WiQueryResourceManager manager = new WiQueryResourceManager();

			JsStatement jsStatement = new JsStatement();
			for (IWiQueryPlugin plugin : visitor.getPlugins()) {
				JsStatement tempStatement = plugin.statement();

				if (tempStatement != null) {
					jsStatement.append("\t").append( tempStatement.render()).append("\n");
				}

				// calling listeners to compute specific stuff
				for (WiQueryPluginRenderingListener listener : pluginRenderingListeners) {
					listener.onRender(plugin, manager, headerResponse);
				}

				plugin.contribute(manager);
			}

			initializeResourceManager(headerResponse, manager);

			mergeResources(response, settings, wiQueryHeaderResponse);

			JsQuery jsq = new JsQuery();
			jsq.setStatement(jsStatement);
			jsq.renderHead(response, RequestCycle.get().getActiveRequestHandler());
		}
	}

	private void initializeResourceManager( IHeaderResponse headerResponse, WiQueryResourceManager manager ) {
		if(WiQuerySettings.get().isEnableWiqueryResourceManagement()) {
			manager.initialize(headerResponse);
		}
	}

	private void renderAjaxResponse(IHeaderResponse response,
			AjaxRequestTarget ajaxRequestTarget) {
		WiQuerySettings settings = WiQuerySettings.get();

		final List<WiQueryPluginRenderingListener> pluginRenderingListeners = settings.getListeners();

		IHeaderResponse headerResponse;
		WiQueryHeaderResponse wiQueryHeaderResponse = new WiQueryHeaderResponse();
		if (settings.isEnableResourcesMerging()) {
			wiQueryHeaderResponse.setIHeaderResponse(response);
			headerResponse = wiQueryHeaderResponse;
		} else {
			headerResponse = response;
		}

		WiQueryResourceManager manager = new WiQueryResourceManager();

		if (owner.determineVisibility()) {
			if (owner instanceof IWiQueryPlugin) {
				renderPlugin(response, ajaxRequestTarget, (IWiQueryPlugin) owner, pluginRenderingListeners,	manager, headerResponse);
			}
			for (Behavior behavior : owner.getBehaviors()) {
				if (behavior instanceof IWiQueryPlugin && behavior.isEnabled(owner)) {
					renderPlugin(response, ajaxRequestTarget, (IWiQueryPlugin) behavior, pluginRenderingListeners, manager, headerResponse);
				}
			}
		}

		initializeResourceManager(headerResponse, manager);

		mergeResources(response, settings, wiQueryHeaderResponse);
	}

	private void renderPlugin(
			IHeaderResponse response,
			AjaxRequestTarget ajaxRequestTarget,
			IWiQueryPlugin plugin,
			final List<WiQueryPluginRenderingListener> pluginRenderingListeners,
			WiQueryResourceManager manager, IHeaderResponse headerResponse) {
		JsStatement statement = plugin.statement();
		if (statement != null) {
			JsQuery jsq = new JsQuery();
			jsq.setStatement(statement.append("\n"));
			jsq.renderHead(response, ajaxRequestTarget);
		}
		for (WiQueryPluginRenderingListener listener : pluginRenderingListeners) {
			listener.onRender(plugin, manager, headerResponse);
		}
		plugin.contribute(manager);
	}

	private void mergeResources(final IHeaderResponse response,
			WiQuerySettings settings,
			WiQueryHeaderResponse wiQueryHeaderResponse) {
		if (settings.isEnableResourcesMerging()) {
			// Merging of stylesheet resources
			if (!wiQueryHeaderResponse.getStylesheet().isEmpty()) {
				response
						.renderCSSReference(new WiQueryMergedStyleSheetResourceReference(
								wiQueryHeaderResponse));
			}

			// Insertion of non mergeable stylesheet
			for (ResourceReference ref : wiQueryHeaderResponse
					.getStylesheetUnmergeable()) {
				response.renderCSSReference(ref);
			}

			// Merging of javascript resources
			if (!wiQueryHeaderResponse.getJavascript().isEmpty()) {
				response
						.renderJavaScriptReference(new WiQueryMergedJavaScriptResourceReference(wiQueryHeaderResponse));
			}

			// Insertion of non mergeable javascript
			for (ResourceReference ref : wiQueryHeaderResponse
					.getJavascriptUnmergeable()) {
				response.renderJavaScriptReference(ref);
			}
		}
	}
}