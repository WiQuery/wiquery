package org.odlabs.wiquery.core.commons.listener;

import java.io.Serializable;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.commons.CoreJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;

/**
 * $Id$
 * <p>
 * 	TODO insert comments here
 * </p>
 * @author lionel
 * @since TODO indicate wickext version
 */
public class JQueryCoreRenderingListener implements WiQueryPluginRenderingListener, Serializable {

	private static final long serialVersionUID = 3644333357586234429L;

	/**
	 * Renders needed resources for any jQuery code (e.g. core libraries).
	 */
	public void onRender(IWiQueryPlugin plugin,
			WiQueryResourceManager resourceManager, IHeaderResponse response) {
		response
			.renderJavascriptReference(CoreJavaScriptResourceReference
				.get());		
	}
	
}
