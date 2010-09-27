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
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.commons.listener.JQueryCoreRenderingListener;
import org.odlabs.wiquery.core.commons.listener.JQueryUICoreRenderingListener;
import org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener;
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
 * @author Richard Wilkinson
 */
public class WiQueryCoreHeaderContributor implements Serializable,
IHeaderContributor {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -347081993448442637L;

	/**
	 * meta data for WiQueryCoreHeaderContributor.
	 */
	private static final MetaDataKey<Boolean> WIQUERY_KEY = new MetaDataKey<Boolean>() {
		private static final long serialVersionUID = 1L;
	};

	/**
	 * Default constructor. Declares a standard configuration for listeners,
	 * e.g. uses jQuery and jQuery UI listeners by default.
	 */
	public WiQueryCoreHeaderContributor() {
		super();
	}

	/**
	 * Renders WiQuery's JavaScript code.
	 */
	public void renderHead(final IHeaderResponse response) {

		Boolean rendered = RequestCycle.get().getMetaData(WIQUERY_KEY);
		if(rendered == null || !rendered){

			WiQuerySettings instanciation = WiQuerySettings.get();

			final List<WiQueryPluginRenderingListener> pluginRenderingListeners = new ArrayList<WiQueryPluginRenderingListener>();

			pluginRenderingListeners.add(new JQueryCoreRenderingListener());
			pluginRenderingListeners.add(new JQueryUICoreRenderingListener());
			// Listeners add by users
			for(Iterator<WiQueryPluginRenderingListener> iterator = instanciation.getListeners(); iterator.hasNext();){
				pluginRenderingListeners.add(iterator.next());
			}


			// the response is a unique statement containing all statements
			// to call
			JsQuery jsq = new JsQuery();
			JsStatement jsStatement = new JsStatement();
			JsStatement tempStatement = null;
			IRequestTarget target = RequestCycle.get().getRequestTarget();
			IWiQueryPlugin plugin = null;

			final List<IWiQueryPlugin> plugins = new ArrayList<IWiQueryPlugin>();
			AjaxRequestTarget ajaxRequestTarget = AjaxRequestTarget.get();

			IVisitor<Component> visitor = new IVisitor<Component>() {

				public Object component(Component component) {
					if(component.determineVisibility()){
						if(component instanceof IWiQueryPlugin){
							plugins.add((IWiQueryPlugin) component);
						}
						for(IBehavior behavior : component.getBehaviors()){
							if(behavior instanceof IWiQueryPlugin && behavior.isEnabled(component)){
								plugins.add((IWiQueryPlugin) behavior);
							}
						}
						return CONTINUE_TRAVERSAL;
					}
					else{
						return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
					}
				}
			};

			if(ajaxRequestTarget == null){
				//is a normal page render
				Page page = RequestCycle.get().getResponsePage();
				if(page != null){
					page.visitChildren(visitor);
					if(page instanceof IWiQueryPlugin){
						plugins.add((IWiQueryPlugin) page);
					}
					for(IBehavior behavior : page.getBehaviors()){
						if(behavior instanceof IWiQueryPlugin && behavior.isEnabled(page)){
							plugins.add((IWiQueryPlugin) behavior);
						}
					}
				}
			}
			else{
				//is an ajax render
				for(Component component : ajaxRequestTarget.getComponents()){
					if(component.determineVisibility()){
						if(component instanceof IWiQueryPlugin){
							plugins.add((IWiQueryPlugin) component);
						}
						for(IBehavior behavior : component.getBehaviors()){
							if(behavior instanceof IWiQueryPlugin && behavior.isEnabled(component)){
								plugins.add((IWiQueryPlugin) behavior);
							}
						}
					}
					if(component instanceof MarkupContainer){
						((MarkupContainer) component).visitChildren(visitor);
					}
				}
			}


			WiQueryResourceManager manager = new WiQueryResourceManager();

			for (Iterator<IWiQueryPlugin> iterator = plugins.iterator(); iterator.hasNext(); ) {
				plugin = iterator.next();

				tempStatement = plugin.statement();

				if(tempStatement != null){
					jsStatement.append("\t" + tempStatement.render() + "\n");
				}

				// calling listeners to compute specific stuff
				for (WiQueryPluginRenderingListener listener : pluginRenderingListeners) {
					listener.onRender(plugin, manager, response);
				}
				plugin.contribute(manager);
			}

			manager.initialize(response);


			jsq.setStatement(jsStatement);
			jsq.renderHead(response, target);
			RequestCycle.get().setMetaData(WIQUERY_KEY, Boolean.TRUE);
		}
	}

}