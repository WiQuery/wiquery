package org.odlabs.wiquery.core.commons;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;

/**
 * {@link ResourceReference} which checks the {@link WiQuerySettings} if the
 * resources needs to fetch the normal (non-minimized) version or the minimized version.
 * 
 * @author Hielke Hoeve
 */
public class WiQueryJavaScriptResourceReference extends
		CompressedResourceReference {
	private static final long serialVersionUID = 1L;

	public WiQueryJavaScriptResourceReference(Class<?> scope, String name) {
		super(scope, processName(name), null, null);
	}

	private static String processName(String name) {
		if (isMinifiedJavascript())
			return name.substring(0, name.length() - 2) + "min.js";

		return name;
	}

	public static boolean isMinifiedJavascript() {
		return WiQuerySettings.get().isMinifiedJavascript();
	}
}
