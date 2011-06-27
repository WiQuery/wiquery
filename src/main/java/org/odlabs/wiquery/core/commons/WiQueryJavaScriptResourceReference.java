package org.odlabs.wiquery.core.commons;

import org.apache.wicket.Application;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.util.lang.Packages;

/**
 * <p>
 * {@link ResourceReference} which checks the {@link WiQuerySettings} if the
 * resources needs to fetch the normal (non-minimized) version or the minimized
 * version.
 * </p>
 * 
 * <p>
 * Note that this ResourceReference only loads files and does not minify on the
 * fly.
 * </p>
 * 
 * <p>
 * Always provide the normal (non-minimized) version, wiquery will reference to
 * the minimized version when {@link WiQuerySettings#isCompressedJavascript()}
 * is true.
 * </p>
 * <p>
 * The filename format for the 2 versions is:
 * <ul>
 * <li>Normal version: <i>foo.js</i> / <i>foo.css</i></li>
 * <li>Minimized version: <i>foo.min.js</i> / <i>foo.min.css</i></li>
 * </ul>
 * </p>
 * 
 * @author Hielke Hoeve
 */
public class WiQueryJavaScriptResourceReference extends
		CompressedResourceReference {
	private static final long serialVersionUID = 1L;

	private Boolean minified = null;

	public WiQueryJavaScriptResourceReference(Class<?> scope, String name) {
		super(scope, name, null, null);
	}

	public boolean exists(Class<?> scope, String path) {
		String absolutePath = Packages.absolutePath(scope, path);
		return Application.get().getResourceSettings()
				.getResourceStreamLocator()
				.locate(scope, absolutePath, getStyle(), getLocale(), null) != null;
	}

	public String getWiQueryName() {
		String name = getName();
		String minifiedName = name.substring(0, name.length() - 2) + "min.js";
		if (minified == null)
			minified = isMinifiedJavaScriptResources()
					&& exists(getScope(), minifiedName);
		if (minified)
			return minifiedName;
		return name;
	}

	public static boolean isMinifiedJavaScriptResources() {
		return WiQuerySettings.get().isMinifiedResources();
	}
}
