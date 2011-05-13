package org.odlabs.wiquery.core.commons.compressed;

import java.util.Locale;

import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.PackageResource;

/**
 * Static resource reference for stylesheet resources. The resources are
 * filtered (stripped comments and whitespace) and gzipped.
 * 
 * @author Matej Knopp
 */
public class StyleSheetResourceReference extends ResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new stylesheet resource reference.
	 * 
	 * @param scope
	 * @param name
	 * @param locale
	 * @param style
	 */
	public StyleSheetResourceReference(Class<?> scope, String name,
			Locale locale, String style) {
		super(scope, name, locale, style);
		setStateless(true);
	}

	/**
	 * Creates a new stylesheet resource reference.
	 * 
	 * @param scope
	 * @param name
	 */
	public StyleSheetResourceReference(Class<?> scope, String name) {
		super(scope, name);
		setStateless(true);
	}

	/**
	 * Creates a new stylesheet resource reference.
	 * 
	 * @param name
	 */
	public StyleSheetResourceReference(String name) {
		super(name);
		setStateless(true);
	}

	@Override
	protected Resource newResource() {
		PackageResource packageResource = JavascriptPackageResource
				.newPackageResource(getScope(), getName(), getLocale(),
						getStyle());
		if (packageResource != null) {
			locale = packageResource.getLocale();
		} else {
			throw new IllegalArgumentException("package resource [scope="
					+ getScope() + ",name=" + getName() + ",locale="
					+ getLocale() + "style=" + getStyle() + "] not found");
		}
		return packageResource;
	}
}
