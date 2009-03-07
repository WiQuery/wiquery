/*
 * Copyright (c) 2008 Objet Direct
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
package org.objetdirect.wiquery.core.commons;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.objetdirect.wiquery.core.javascript.JsQuery;
import org.objetdirect.wiquery.ui.commons.WickextUIPlugin;
import org.objetdirect.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.objetdirect.wiquery.ui.themes.wickext.gray.WickextGrayThemeResourceReference;

/**
 * $Id$
 * <p>
 * Listens to WickeXt components instantiation and automatically imports needed
 * JavaScript libraries and themes.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
public class WickextPluginInstantiationListener implements
		IComponentInstantiationListener, Serializable {

	private static final long serialVersionUID = -7398777039788778234L;

	/**
	 * Defines the theme resource reference to use for all WickeXt UI components
	 */
	private ResourceReference themeResource = new WickextGrayThemeResourceReference();

	
	/**
	 * Imports needed resources and outputs JavaScript code for the 
	 * instantiated component.
	 */
	public void onInstantiation(final Component component) {
		if (component instanceof WickextPlugin) {
			// manages the instantiated plugin (e.g. imports linked
			// resources and appends the JavaScript statements)
			manage(component);
			// if it's a UI component, we import jquery ui core libs
			// and css
			// resource are imported has header contributors
			final WickextPlugin wickextPlugin = (WickextPlugin) component;
			manageUi(component, wickextPlugin);
			component.add(new HeaderContributor((wickextPlugin).getHeaderContribution()));
			final JsQuery jsQuery = new JsQuery(component);
			component.add(new HeaderContributor(new IHeaderContributor() {
			
				private static final long serialVersionUID = 1L;

				public void renderHead(IHeaderResponse response) {
					jsQuery.setStatement(wickextPlugin.statement());
					jsQuery.renderHead(response);
				}
			
			}));
		}
//		List<IBehavior> behaviors = component.getBehaviors();
//		for (IBehavior behavior : behaviors) {
//			if (behavior instanceof WickextPlugin) {
//				manage(component);
//				manageUi(component, (WickextPlugin) behavior);
//				component.add(new HeaderContributor(((WickextPlugin) behavior).getHeaderContribution()));
//				JSQuery componentJs = new JSQuery(component);
//				component.add(new HeaderContributor(componentJs));				
//			}
//		}
	}

	/**
	 * Returns the theme used for UI components as a {@link ResourceReference}.
	 */
	public ResourceReference getThemeResource() {
		return themeResource;
	}

	/**
	 * Sets the theme used for UI components.
	 */
	public void setThemeResource(ResourceReference themeResource) {
		this.themeResource = themeResource;
	}

	// Private methods
	
	private void manageUi(Component component, WickextPlugin wickextPlugin) {
		if (wickextPlugin.getClass().getAnnotation(WickextUIPlugin.class) != null) {
			component.add(new HeaderContributor(new IHeaderContributor() {
				
				private static final long serialVersionUID = -5771330091411079097L;
				
				public void renderHead(IHeaderResponse response) {
					response
					.renderJavascriptReference(CoreUIJavaScriptResourceReference
							.get());
				}
				
			}));
			component.add(new HeaderContributor(new IHeaderContributor() {
				
				private static final long serialVersionUID = 1L;
				
				public void renderHead(IHeaderResponse response) {
					// applying current theme
					response.renderCSSReference(themeResource);
				}
				
			}));
		}		
	}
	
	private void manage(Component component) {
		// import of jquery core libs
		component.add(new HeaderContributor(
				new CoreJavaScriptHeaderContributor()));
		
	}
	
}
