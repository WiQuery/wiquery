/*
 * $Licence$
 */
package org.odlabs.wiquery.core.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.events.WiQueryAjaxEventBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates needed JavaScript requested by a request-response cycle. 
 * 
 * <p>
 * If the request in a non ajax request, the generated JavaScript is wrapped by
 * a "dom ready" statement. Otherwise (in Ajax contexts, the generated
 * JavaScript is directly append to the given {@link AjaxRequestTarget}.
 * </p>
 * 
 * @author Benoit Bouchez, Lionel Armanet
 */
public class WiQueryCoreHeaderContributor implements Serializable, IHeaderContributor
{
	private static final long serialVersionUID = -347081993448442637L;

	protected static final Logger log = LoggerFactory
		.getLogger(WiQueryAjaxEventBehavior.class);

	/** Standard library selector ; usefull in dev mode */
	public static final String LIBMODE_STANDARD = "";
	/** Packed library selector */
	public static final String LIBMODE_PACKED = ".packed";
	/** Minified library selector */
	public static final String LIBMODE_MINIFIED = ".min";

	public static final String DEFAULT_THEME = "fusion";
	
	private static String libraryMode = LIBMODE_STANDARD;

	private List<IWiQueryPlugin> plugins = new ArrayList<IWiQueryPlugin>();
	
	private Map<IWiQueryPlugin, WiQueryResourceManager> resourceManagers 
						= new HashMap<IWiQueryPlugin, WiQueryResourceManager>();
	
	public WiQueryCoreHeaderContributor()
	{
		super();
	}

	void importCoreResource(IHeaderResponse headerResponse)
	{
		headerResponse.renderJavascriptReference(CoreJavaScriptResourceReference.get());
	}

	void importCoreUiResource(IHeaderResponse headerResponse)
	{
		headerResponse.renderCSSReference(new WiQueryCoreThemeResourceReference(DEFAULT_THEME));
		headerResponse.renderJavascriptReference(CoreUIJavaScriptResourceReference.get());
	}
	
	public void addPlugin(IWiQueryPlugin wiqueryPlugin)
	{
		// attaching a plugin resource manager for the given plugin
		WiQueryResourceManager resourceManager 
						= new WiQueryResourceManager();
		this.resourceManagers.put(wiqueryPlugin, resourceManager);
		this.plugins.add(wiqueryPlugin);
	}

	public static String getLibraryMode()
	{
		return libraryMode;
	}

	/**
	 * 
	 * @param libraryMode
	 *            LIBMODE_STANDARD (default), LIBMODE_PACKED, LIBMODE_MINIFIED
	 */
	public static void setLibraryMode(String libraryMode)
	{
		WiQueryCoreHeaderContributor.libraryMode = libraryMode;
	}

	public void renderHead(final IHeaderResponse response) 
	{
		//Component referenceComponent = null;
		this.importCoreResource(response);
		// generating on ready query
		
		JsQuery jsq = new JsQuery();
		JsStatement jsStatement = new JsStatement();
		
		// REFACTOR A WebPage should be used instead ?
		for (IWiQueryPlugin plugin : this.plugins) 
		{
			WiQueryResourceManager manager = resourceManagers.get(plugin);
			jsStatement.append("\t\t" + plugin.statement().render());
			// adding jQuery UI resources
			if (plugin.getClass().isAnnotationPresent(WiQueryUIPlugin.class)) 
			{
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