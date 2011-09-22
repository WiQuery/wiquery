package org.odlabs.wiquery.core.resources;

import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptPackageResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.dependencies.AbstractResourceDependentResourceReference;
import org.apache.wicket.util.lang.Packages;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.locator.IResourceStreamLocator;
import org.odlabs.wiquery.core.WiQuerySettings;

/**
 * <p>
 * {@link ResourceReference} which checks the {@link WiQuerySettings} if the resources
 * needs to fetch the normal (non-minimized) version or the minimized version.
 * </p>
 * <p>
 * Note that this ResourceReference only loads files and does not minify on the fly.
 * </p>
 * <p>
 * Always provide the normal (non-minimized) version, wiquery will reference to the
 * minimized version when {@link WiQuerySettings#isMinifiedJavaScriptResources()} is true.
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
public class WiQueryJavaScriptResourceReference extends AbstractResourceDependentResourceReference
{
	private static final long serialVersionUID = 1L;

	private Boolean minified = null;

	/**
	 * Construct.
	 * 
	 * @param scope
	 *            mandatory parameter
	 * @param name
	 *            mandatory parameter
	 * @param locale
	 *            resource locale
	 * @param style
	 *            resource style
	 * @param variation
	 *            resource variation
	 */
	public WiQueryJavaScriptResourceReference(Class< ? > scope, String name, Locale locale,
			String style, String variation)
	{
		super(scope, name, locale, style, variation);
	}

	/**
	 * Construct.
	 * 
	 * @param scope
	 *            mandatory parameter
	 * @param name
	 *            mandatory parameter
	 */
	public WiQueryJavaScriptResourceReference(Class< ? > scope, String name)
	{
		super(scope, name);
	}

	private boolean exists(Class< ? > scope, String name)
	{
		IResourceStreamLocator locator =
			Application.get().getResourceSettings().getResourceStreamLocator();
		String absolutePath = Packages.absolutePath(scope, name);
		IResourceStream stream =
			locator
				.locate(scope, absolutePath, getStyle(), getVariation(), getLocale(), null, true);
		return stream != null;
	}

	@Override
	public String getName()
	{
		String name = super.getName();
		String minifiedName = name.substring(0, name.length() - 2) + "min.js";
		if (minified == null)
			minified = isMinifiedJavaScriptResources() && exists(getScope(), minifiedName);
		if (minified)
			return minifiedName;
		return name;
	}

	public static boolean isMinifiedJavaScriptResources()
	{
		return WiQuerySettings.get().isMinifiedJavaScriptResources();
	}

	/**
	 * @return default an empty list as all subclasses must implement this to fit their
	 *         own needs.
	 */
	@Override
	public AbstractResourceDependentResourceReference[] getDependentResourceReferences()
	{
		return new AbstractResourceDependentResourceReference[0];
	}

	@Override
	public IResource getResource()
	{
		return new JavaScriptPackageResource(getScope(), getName(), getLocale(), getStyle(),
			getVariation());
	}

	public boolean isWiQuery()
	{
		return getClass().getPackage().getName().startsWith("org.odlabs.wiquery");
	}
}
