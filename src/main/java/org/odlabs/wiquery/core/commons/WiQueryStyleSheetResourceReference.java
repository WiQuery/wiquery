package org.odlabs.wiquery.core.commons;

import java.util.Locale;

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
 * the minimized version when {@link WiQuerySettings#isMinifiedResources()} is
 * true.
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
public class WiQueryStyleSheetResourceReference extends
		CompressedResourceReference {
	private static final long serialVersionUID = 1L;

	public WiQueryStyleSheetResourceReference(Class<?> scope, String name) {
		super(scope, getWiQueryName(scope, name, null, null), null, null);
	}

	public static boolean exists(Class<?> scope, String name, String style,
			Locale locale) {
		String absolutePath = Packages.absolutePath(scope, name);
		return Application.get().getResourceSettings()
				.getResourceStreamLocator()
				.locate(scope, absolutePath, style, locale, null) != null;
	}

	public static String getWiQueryName(Class<?> scope, String name,
			String style, Locale locale) {
		String minifiedName = name.substring(0, name.length() - 3) + "min.css";
		if (isMinifiedStyleSheetResources()
				&& exists(scope, minifiedName, style, locale))
			return minifiedName;
		return name;
	}

	public static boolean isMinifiedStyleSheetResources() {
		return WiQuerySettings.get().isMinifiedResources();
	}
}
