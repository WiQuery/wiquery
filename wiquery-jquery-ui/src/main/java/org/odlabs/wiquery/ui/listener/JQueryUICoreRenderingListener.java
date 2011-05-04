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
package org.odlabs.wiquery.ui.listener;

import java.io.Serializable;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.resource.ResourceReference;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.commons.WiQuerySettings;
import org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.themes.IThemableApplication;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;

/**
 * $Id: JQueryUICoreRenderingListener.java 412 2010-09-17 21:23:25Z
 * lionel.armanet $
 * <p>
 * Listens to wiquery plugins rendering and imports resources for UI components
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0-m2
 */
public class JQueryUICoreRenderingListener implements
		WiQueryPluginRenderingListener, Serializable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -6556629662991246699L;

	/**
	 * The default theme.
	 */
	private static final ResourceReference DEFAULT_THEME = new WiQueryCoreThemeResourceReference(
			"uilightness");

	/**
	 * @return the default theme
	 */
	public static ResourceReference getDefaultTheme() {
		return DEFAULT_THEME;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener#onRender(org.odlabs.wiquery.core.commons.IWiQueryPlugin,
	 *      org.odlabs.wiquery.core.commons.WiQueryResourceManager,
	 *      org.apache.wicket.markup.html.IHeaderResponse)
	 */
	public void onRender(IWiQueryPlugin plugin,
			WiQueryResourceManager resourceManager, IHeaderResponse response) {
		if (WiQuerySettings.get().isAutoImportJQueryUIResource()) {
			if (plugin.getClass().isAnnotationPresent(WiQueryUIPlugin.class)) {
				Application application = Application.get();
				if (application instanceof IThemableApplication) {
					// if application is themable, imports the given theme
					response
							.renderCSSReference(((IThemableApplication) application)
									.getTheme(Session.get()));
				} else {
					// application is not themed, imports default theme
					response.renderCSSReference(DEFAULT_THEME);
				}
				response
						.renderJavaScriptReference(CoreUIJavaScriptResourceReference
								.get());
			}
		}
	}

}
