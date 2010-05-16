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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.commons.listener.JQueryCoreRenderingListener;
import org.odlabs.wiquery.core.commons.listener.JQueryUICoreRenderingListener;
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
 * @author Benoit Bouchez
 * @author Lionel Armanet
 */
public class WiQueryCoreHeaderContributor implements Serializable,
		IHeaderContributor {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -347081993448442637L;

	/** 
	 * meta data for WiQueryCoreHeaderContributor. 
	 */
	private static final MetaDataKey<WiQueryCoreHeaderContributor> WIQUERY_KEY = new MetaDataKey<WiQueryCoreHeaderContributor>() {
		private static final long serialVersionUID = 1L;
	};
	
	/**
	 * Returns the instance of the current request cycle.
	 */
	public static WiQueryCoreHeaderContributor bindToRequestCycle() {
		WiQueryCoreHeaderContributor wickeryHeaderContributor = RequestCycle
				.get().getMetaData(WIQUERY_KEY);
		if (wickeryHeaderContributor == null) {
			wickeryHeaderContributor = new WiQueryCoreHeaderContributor();
			RequestCycle.get().setMetaData(WIQUERY_KEY,
					wickeryHeaderContributor);
		}
		return wickeryHeaderContributor;
	}

	// Properties
	/**
	 * plugins is the list of plugins to render.
	 */
	private List<IWiQueryPlugin> plugins = new ArrayList<IWiQueryPlugin>();

	/**
	 * Maps a resource manager for a given plugin.
	 */
	private Map<IWiQueryPlugin, WiQueryResourceManager> resourceManagers = new HashMap<IWiQueryPlugin, WiQueryResourceManager>();
	
	/**
	 * The list of listeners that will be notified while a plugin is rendering.
	 */
	private List<WiQueryPluginRenderingListener> pluginRenderingListeners = new ArrayList<WiQueryPluginRenderingListener>();	
	
	/**
	 * Shall we merged wiQuery resources ?
	 */
	private final boolean enableResourcesMerging;
	
	/**
	 * To merge resources
	 */
	private WiQueryHeaderResponse wiQueryHeaderResponse;
	
	/**
	 * Default constructor. Declares a standard configuration for listeners,
	 * e.g. uses jQuery and jQuery UI listeners by default.
	 */
	public WiQueryCoreHeaderContributor() {
		super();
		
		// Default listeners
		this.pluginRenderingListeners.add(new JQueryCoreRenderingListener());
		this.pluginRenderingListeners.add(new JQueryUICoreRenderingListener());
		
		WiQueryInstantiationListener instanciation = WiQueryInstantiationListener.get();
		
		// Listeners add by users
		for(Iterator<WiQueryPluginRenderingListener> iterator = instanciation.getListeners(); iterator.hasNext();){
			this.pluginRenderingListeners.add(iterator.next());
		}
		
		// Shall we merge ?
		enableResourcesMerging = instanciation.isEnableResourcesMerging();
		
		if(enableResourcesMerging){
			wiQueryHeaderResponse = new WiQueryHeaderResponse();
		}
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
	
	/**
	 * Checks whether the given {@link IWiQueryPlugin} is attached to the
	 * given target.
	 */
	public boolean isPluginAttachedToTarget(IWiQueryPlugin plugin, IRequestTarget target) {
		if (target instanceof AjaxRequestTarget) {
			AjaxRequestTarget ajaxRequestTarget = (AjaxRequestTarget) target;
			if (ajaxRequestTarget.getComponents().contains(plugin)) {
				// the component is directly added to the target
				return true;
			}
			// looking at the target's components to check if any plugin
			// is a behavior or a child of request target's components
			for (Component component : ajaxRequestTarget.getComponents()) {
				if (component.getBehaviors().contains(plugin)) {
					// looking at component behaviors to check if the plugin is used
					return true;
				}
				// visiting children if the given component is a container
				if (component instanceof MarkupContainer) {
					Object result = ((MarkupContainer) component).visitChildren(
							new WiQueryPluginVisitor(plugin)
					);
					if (Boolean.TRUE.equals(result)) {
						return true;
					}
				}
			}
			// plugin wasn't added
			return false;
		}
		
		return true; 
	}
	
	/**
	 * Check if the plugin is visible
	 * @param plugin Plugin to check
	 * @param target The current request target
	 * @return the state (default true)
	 */
	public boolean isPluginVisible(IWiQueryPlugin plugin, IRequestTarget target) {
		if(plugin instanceof Component){
			Component component = ((Component) plugin);
			return component.isVisibleInHierarchy() && component.isRenderAllowed();
			
		} else if(plugin instanceof IBehavior && !(target instanceof AjaxRequestTarget)){
			// In a classic request, if the behavior's component isn't visible,
			// the behavior will not be executed
			Page page = RequestCycle.get().getResponsePage();
			
			if(page != null){
				final IBehavior iBehavior = (IBehavior) plugin;
				
				if(page.getBehaviors().contains(iBehavior)){
					return true;
				}
				
				Object result = page.visitChildren(new IVisitor<Component>() {
					
					/**
					 * {@inheritDoc}
					 * @see org.apache.wicket.Component.IVisitor#component(org.apache.wicket.Component)
					 */
					public Object component(Component component) {
						if(component.getBehaviors().contains(iBehavior)){
							return component.isVisibleInHierarchy() && component.isRenderAllowed();
						}
						
						return IVisitor.CONTINUE_TRAVERSAL;
					}
				});
				
				return Boolean.TRUE.equals(result);
			}
			
		} else if(plugin instanceof IBehavior && target instanceof AjaxRequestTarget){
			// In an ajax request target, we shall test the visibility of the component
			AjaxRequestTarget ajaxRequestTarget = (AjaxRequestTarget) target;
			IBehavior iBehavior = (IBehavior) plugin;
			
			for(Component component : ajaxRequestTarget.getComponents()){
				if(component.getBehaviors().contains(iBehavior)){
					return component.isVisibleInHierarchy() && component.isRenderAllowed();
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Renders WiQuery's JavaScript code.
	 */
	public void renderHead(final IHeaderResponse response) {
		// the response is a unique statement containing all statements
		// to call
		JsQuery jsq = new JsQuery();
		JsStatement jsStatement = new JsStatement();
		JsStatement tempStatement = null;
		WiQueryResourceManager manager = null;
		IRequestTarget target = RequestCycle.get().getRequestTarget();
		IHeaderResponse headerResponse = null;
		IWiQueryPlugin plugin = null;
		
		if(enableResourcesMerging){
			wiQueryHeaderResponse.setIHeaderResponse(response);
			headerResponse = wiQueryHeaderResponse;
			
		} else {
			headerResponse = response;
		}
		
		for (Iterator<IWiQueryPlugin> iterator = this.plugins.iterator(); iterator.hasNext(); ) {
			plugin = iterator.next();
			
			if(isPluginAttachedToTarget(plugin, target) && isPluginVisible(plugin, target)) {
				tempStatement = plugin.statement();
				
				if(tempStatement != null){
					jsStatement.append("\t" + tempStatement.render() + "\n");
				}
				
				manager = resourceManagers.get(plugin);
				
				// calling listeners to compute specific stuff
				for (WiQueryPluginRenderingListener listener : pluginRenderingListeners) {
					listener.onRender(plugin, manager, headerResponse);
				}
				plugin.contribute(manager);
				manager.initialize(headerResponse);
				manager.clearResources();
			}
		}
		
		if(enableResourcesMerging){
			// Merging of stylesheet resources
			if(!wiQueryHeaderResponse.getStylesheet().isEmpty()){
				response.renderCSSReference(
						new WiQueryMergedStyleSheetResourceReference(wiQueryHeaderResponse));
			}
			
			// Insertion of non mergeable stylesheet
			for(ResourceReference ref : wiQueryHeaderResponse.getStylesheetUnmergeable()){
				response.renderCSSReference(ref);
			}
			
			// Merging of javascript resources
			if(!wiQueryHeaderResponse.getJavascript().isEmpty()){
				response.renderJavascriptReference(
						new WiQueryMergedJavaScriptResourceReference(wiQueryHeaderResponse));
			}
			
			// Insertion of non mergeable javascript
			for(ResourceReference ref : wiQueryHeaderResponse.getJavascriptUnmergeable()){
				response.renderJavascriptReference(ref);
			}
		}

		jsq.setStatement(jsStatement);
		jsq.renderHead(response, target);
	}

}