package org.odlabs.wiquery.core.commons.listener;

import java.io.Serializable;

import org.apache.wicket.Application;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.themes.IThemableApplication;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;

public class JQueryUICoreRenderingListener implements
		WiQueryPluginRenderingListener, Serializable {

	private static final long serialVersionUID = -6556629662991246699L;
	
	/**
	 * The default theme.
	 */
	private static final ResourceReference DEFAULT_THEME = new WiQueryCoreThemeResourceReference(
			"fusion");

	public void onRender(IWiQueryPlugin plugin,
			WiQueryResourceManager resourceManager, IHeaderResponse response) {
		if (plugin.getClass().isAnnotationPresent(WiQueryUIPlugin.class)) {
			Application application = Application.get();
			if (application instanceof IThemableApplication) {
				// if application is themable, imports the given theme
				response.renderCSSReference(((IThemableApplication)application).getTheme(Session.get()));
			} else {				
				// application is not themed, imports default theme
				response.renderCSSReference(DEFAULT_THEME);
			}
			response
					.renderJavascriptReference(CoreUIJavaScriptResourceReference
							.get());
		}
	}
	
}
