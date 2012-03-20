package org.odlabs.wiquery.core.commons;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;

/**
 * <p> {@link ResourceReference} which checks the {@link WiQuerySettings} if the resources needs to fetch the normal
 * (non-minimized) version or the minimized version. </p> <p/> <p> Note that this ResourceReference only loads files and
 * does not minify on the fly. </p> <p/> <p> Always provide the normal (non-minimized) version, wiquery will reference
 * to the minimized version when {@link WiQuerySettings#isCompressedJavascript()} is true. </p> <p> The filename format
 * for the 2 versions is: <ul> <li>Normal version: <i>foo.js</i> / <i>foo.css</i></li> <li>Minimized version:
 * <i>foo.min.js</i> / <i>foo.min.css</i></li> </ul> </p>
 *
 * @author Hielke Hoeve
 */
public class WiQueryJavaScriptResourceReference extends
		CompressedResourceReference {
	private static final long serialVersionUID = 1L;

	private static final String JQUERY_UI_VERSION = "1.8.18";

	public WiQueryJavaScriptResourceReference(Class<?> scope, String name) {
		super(scope, processName(scope, getAcutalName(name)), null, null);
	}

	private static String processName(Class<?> scope, String name) {
		if (scope.getName().contains("org.odlabs.wiquery.ui")) {
			return name + "?ui=" + JQUERY_UI_VERSION;
		}
		return name;
	}

	private static String getAcutalName(String name) {
		if (isMinifiedJavascript()) {
			return name.substring(0, name.length() - 2) + "min.js";
		}
		return name;
	}

	public static boolean isMinifiedJavascript() {
		return WiQuerySettings.get().isMinifiedResources();
	}
}
