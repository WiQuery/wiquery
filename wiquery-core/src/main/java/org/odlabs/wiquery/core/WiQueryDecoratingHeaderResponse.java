package org.odlabs.wiquery.core;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.IPageRequestHandler;
import org.apache.wicket.resource.aggregation.ResourceReferenceAndStringData;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.util.visit.Visit;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * An implementation of AbstractDependencyRespectingResourceAggregatingHeaderResponse that
 * renders uses {@link AbstractWiQueryDecoratingHeaderResponse} to render references in
 * the correct order.
 * </p>
 * 
 * <p>
 * Just before the response is closed we visit all implementations of
 * {@link IWiQueryPlugin} that are present on the page or in the {@link AjaxRequestTarget}
 * and render their {@link IWiQueryPlugin#statement()} results in a ondomready jquery
 * statement.
 * </p>
 * 
 * @see AbstractWiQueryDecoratingHeaderResponse
 * @see WiQuerySettings#getResourceGroupingKeys()
 * @see WiQuerySettings#findResourceGroupingKey(String)
 * 
 * @author Hielke Hoeve
 */
public class WiQueryDecoratingHeaderResponse extends AbstractWiQueryDecoratingHeaderResponse
{
	private static final Logger log = LoggerFactory
		.getLogger(WiQueryDecoratingHeaderResponse.class);

	private static final MetaDataKey<Boolean> WIQUERY_KEY = new MetaDataKey<Boolean>()
	{
		private static final long serialVersionUID = 1L;
	};

	private static final MetaDataKey<Long> WIQUERY_PAGE_KEY = new MetaDataKey<Long>()
	{
		private static final long serialVersionUID = 1L;
	};

	JsQuery jsq = new JsQuery();

	public WiQueryDecoratingHeaderResponse(IHeaderResponse real)
	{
		super(real);
	}

	/**
	 * Perform a scan over all components so all components and listeners have contributed
	 * their resources and WiQuery has generated the ondomready statement.
	 */
	@Override
	public void close()
	{
		long startTime = System.currentTimeMillis();
		log.debug("WiQuery contribution starts!");

		AjaxRequestTarget ajaxRequestTarget = AjaxRequestTarget.get();
		if (ajaxRequestTarget != null)
		{
			renderAjaxResponse(ajaxRequestTarget);
		}
		else if (RequestCycle.get().getActiveRequestHandler() instanceof IPageRequestHandler)
		{
			renderResponse();
		}

		log.debug("WiQuery plugin contribution finished in "
			+ (System.currentTimeMillis() - startTime) + "ms!");

		super.close();

		log.debug("WiQuery contribution finished in " + (System.currentTimeMillis() - startTime)
			+ "ms!");
	}

	/**
	 * Contribute the ondomready statement to the response.
	 */
	@Override
	protected void onAllCollectionsRendered(
			List<ResourceReferenceAndStringData> allTopLevelReferences)
	{
		jsq.renderHead(this, RequestCycle.get().getActiveRequestHandler());
		jsq = new JsQuery();

		super.onAllCollectionsRendered(allTopLevelReferences);
	}

	private void renderResponse()
	{
		Page page =
			(Page) ((IPageRequestHandler) RequestCycle.get().getActiveRequestHandler()).getPage();
		Long renderTime = page.getMetaData(WIQUERY_PAGE_KEY);
		Boolean rendered =
			renderTime != null && renderTime.equals(RequestCycle.get().getStartTime());
		page.setMetaData(WIQUERY_PAGE_KEY, RequestCycle.get().getStartTime());
		RequestCycle.get().setMetaData(WIQUERY_KEY, Boolean.TRUE);

		// return when response has already been rendered.
		if (rendered)
			return;

		final List<WiQueryPluginRenderingListener> pluginRenderingListeners =
			settings.getListeners();

		WiQueryPluginCollector visitor = new WiQueryPluginCollector();
		visitor.component(page, new Visit<Void>());
		page.visitChildren(visitor);

		JsStatement jsStatement = new JsStatement();
		for (IWiQueryPlugin plugin : visitor.getPlugins())
		{
			JsStatement tempStatement = plugin.statement();

			if (tempStatement != null)
			{
				jsStatement.append("\t").append(tempStatement.render()).append("\n");
			}

			// calling listeners to compute specific stuff
			for (WiQueryPluginRenderingListener listener : pluginRenderingListeners)
			{
				listener.onRender(plugin, this);
			}
		}

		jsq.setStatement(jsStatement);
	}

	private void renderAjaxResponse(AjaxRequestTarget ajaxRequestTarget)
	{
		final List<WiQueryPluginRenderingListener> pluginRenderingListeners =
			settings.getListeners();

		for (Component owner : ajaxRequestTarget.getComponents())
		{
			WiQueryPluginCollector visitor = new WiQueryPluginCollector();
			visitor.component(owner, new Visit<Void>());

			if (owner instanceof WebMarkupContainer)
			{
				((WebMarkupContainer) owner).visitChildren(visitor);
			}

			for (IWiQueryPlugin plugin : visitor.getPlugins())
			{
				renderPlugin(ajaxRequestTarget, plugin, pluginRenderingListeners, this);
			}
		}
	}

	private void renderPlugin(AjaxRequestTarget ajaxRequestTarget, IWiQueryPlugin plugin,
			final List<WiQueryPluginRenderingListener> pluginRenderingListeners,
			IHeaderResponse headerResponse)
	{
		JsStatement statement = plugin.statement();
		if (statement != null)
		{
			JsQuery jsq = new JsQuery();
			jsq.setStatement(statement.append("\n"));
			jsq.renderHead(this, ajaxRequestTarget);
		}
		for (WiQueryPluginRenderingListener listener : pluginRenderingListeners)
		{
			listener.onRender(plugin, headerResponse);
		}
	}

	private static class WiQueryPluginCollector implements IVisitor<Component, Void>
	{
		private final LinkedList<IWiQueryPlugin> plugins = new LinkedList<IWiQueryPlugin>();

		private WiQueryPluginCollector()
		{
		}

		public LinkedList<IWiQueryPlugin> getPlugins()
		{
			return plugins;
		}

		public void component(Component component, IVisit<Void> visit)
		{
			if (component.determineVisibility())
			{
				if (component instanceof IWiQueryPlugin)
				{
					plugins.add(0, (IWiQueryPlugin) component);
				}

				for (Behavior behavior : component.getBehaviors())
				{
					if (behavior instanceof IWiQueryPlugin && behavior.isEnabled(component))
					{
						plugins.add(0, (IWiQueryPlugin) behavior);
					}
				}
			}
			else
			{
				visit.dontGoDeeper();
			}
		}
	}
}