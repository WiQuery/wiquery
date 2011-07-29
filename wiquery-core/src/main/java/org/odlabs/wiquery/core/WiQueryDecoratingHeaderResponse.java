package org.odlabs.wiquery.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.IPageRequestHandler;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.dependencies.ResourceReferenceDependencyInjectingHeaderResponse;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.util.visit.Visit;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.merge.WiQueryHeaderResponse;
import org.odlabs.wiquery.core.merge.WiQueryMergedJavaScriptResourceReference;
import org.odlabs.wiquery.core.merge.WiQueryMergedStyleSheetResourceReference;

public class WiQueryDecoratingHeaderResponse extends
		ResourceReferenceDependencyInjectingHeaderResponse {
	private static final MetaDataKey<Boolean> WIQUERY_KEY = new MetaDataKey<Boolean>() {
		private static final long serialVersionUID = 1L;
	};

	private static final MetaDataKey<Long> WIQUERY_PAGE_KEY = new MetaDataKey<Long>() {
		private static final long serialVersionUID = 1L;
	};

	public WiQueryDecoratingHeaderResponse(IHeaderResponse real) {
		super(real,
				new WiQueryResourceReferenceDependencyConfigurationService());
	}

	@Override
	public void close() {

		super.close();

		AjaxRequestTarget ajaxRequestTarget = AjaxRequestTarget.get();
		if (ajaxRequestTarget != null) {
			renderAjaxResponse(ajaxRequestTarget);
		} else if (RequestCycle.get().getActiveRequestHandler() instanceof IPageRequestHandler) {
			renderResponse();
		}

	}

	private void renderResponse() {
		Page page = (Page) ((IPageRequestHandler) RequestCycle.get()
				.getActiveRequestHandler()).getPage();
		Long renderTime = page.getMetaData(WIQUERY_PAGE_KEY);
		Boolean rendered = renderTime != null
				&& renderTime.equals(RequestCycle.get().getStartTime());
		page.setMetaData(WIQUERY_PAGE_KEY, RequestCycle.get().getStartTime());
		RequestCycle.get().setMetaData(WIQUERY_KEY, Boolean.TRUE);

		if (rendered == null || !rendered) {
			WiQuerySettings settings = WiQuerySettings.get();

			final List<WiQueryPluginRenderingListener> pluginRenderingListeners = settings
					.getListeners();

			WiQueryPluginCollector visitor = new WiQueryPluginCollector();
			if (page != null) {
				page.visitChildren(visitor);
				visitor.component(page, new Visit<Void>());
			}

			// gather all
			WiQueryHeaderResponse wiQueryHeaderResponse = new WiQueryHeaderResponse();
			IHeaderResponse headerResponse = this;
			if (settings.isEnableResourcesMerging()) {
				wiQueryHeaderResponse.setIHeaderResponse(this);
				headerResponse = wiQueryHeaderResponse;
			}

			JsStatement jsStatement = new JsStatement();
			for (IWiQueryPlugin plugin : visitor.getPlugins()) {
				JsStatement tempStatement = plugin.statement();

				if (tempStatement != null) {
					jsStatement.append("\t").append(tempStatement.render())
							.append("\n");
				}

				// calling listeners to compute specific stuff
				for (WiQueryPluginRenderingListener listener : pluginRenderingListeners) {
					listener.onRender(plugin, headerResponse);
				}
			}

			mergeResources(settings, wiQueryHeaderResponse);

			JsQuery jsq = new JsQuery();
			jsq.setStatement(jsStatement);
			jsq.renderHead(this, RequestCycle.get().getActiveRequestHandler());
		}
	}

	private void renderAjaxResponse(AjaxRequestTarget ajaxRequestTarget) {
		WiQuerySettings settings = WiQuerySettings.get();

		final List<WiQueryPluginRenderingListener> pluginRenderingListeners = settings
				.getListeners();

		WiQueryHeaderResponse wiQueryHeaderResponse = new WiQueryHeaderResponse();
		IHeaderResponse headerResponse = this;
		if (settings.isEnableResourcesMerging()) {
			wiQueryHeaderResponse.setIHeaderResponse(this);
			headerResponse = wiQueryHeaderResponse;
		}

		for (Component owner : ajaxRequestTarget.getComponents()) {
			if (owner.determineVisibility()) {
				if (owner instanceof IWiQueryPlugin) {
					renderPlugin(ajaxRequestTarget, (IWiQueryPlugin) owner,
							pluginRenderingListeners, headerResponse);
				}
				for (Behavior behavior : owner.getBehaviors()) {
					if (behavior instanceof IWiQueryPlugin
							&& behavior.isEnabled(owner)) {
						renderPlugin(ajaxRequestTarget,
								(IWiQueryPlugin) behavior,
								pluginRenderingListeners, headerResponse);
					}
				}
			}
		}

		mergeResources(settings, wiQueryHeaderResponse);
	}

	private void renderPlugin(
			AjaxRequestTarget ajaxRequestTarget,
			IWiQueryPlugin plugin,
			final List<WiQueryPluginRenderingListener> pluginRenderingListeners,
			IHeaderResponse headerResponse) {
		JsStatement statement = plugin.statement();
		if (statement != null) {
			JsQuery jsq = new JsQuery();
			jsq.setStatement(statement.append("\n"));
			jsq.renderHead(this, ajaxRequestTarget);
		}
		for (WiQueryPluginRenderingListener listener : pluginRenderingListeners) {
			listener.onRender(plugin, headerResponse);
		}
	}

	private void mergeResources(WiQuerySettings settings,
			WiQueryHeaderResponse wiQueryHeaderResponse) {
		if (settings.isEnableResourcesMerging()) {
			// Merging of stylesheet resources
			if (!wiQueryHeaderResponse.getStylesheet().isEmpty()) {
				renderCSSReference(new WiQueryMergedStyleSheetResourceReference(
						wiQueryHeaderResponse));
			}

			// Insertion of non mergeable stylesheet
			for (ResourceReference ref : wiQueryHeaderResponse
					.getStylesheetUnmergeable()) {
				renderCSSReference(ref);
			}

			// Merging of javascript resources
			if (!wiQueryHeaderResponse.getJavascript().isEmpty()) {
				renderJavaScriptReference(new WiQueryMergedJavaScriptResourceReference(
						wiQueryHeaderResponse));
			}

			// Insertion of non mergeable javascript
			for (ResourceReference ref : wiQueryHeaderResponse
					.getJavascriptUnmergeable()) {
				renderJavaScriptReference(ref);
			}
		}
	}

	private static class WiQueryPluginCollector implements
			IVisitor<Component, Void> {
		private final List<IWiQueryPlugin> plugins = new ArrayList<IWiQueryPlugin>();

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
}