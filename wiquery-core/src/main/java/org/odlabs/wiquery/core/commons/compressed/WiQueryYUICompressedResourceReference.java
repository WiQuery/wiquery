package org.odlabs.wiquery.core.commons.compressed;

import java.util.Locale;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.odlabs.wiquery.core.commons.WiQuerySettings;

public class WiQueryYUICompressedResourceReference extends PackageResourceReference {

	private static final long serialVersionUID = 1L;

	public WiQueryYUICompressedResourceReference(Class<?> scope,
			String name) {
		super(scope, name);
	}

	public WiQueryYUICompressedResourceReference(Class<?> scope,
			String name, Locale locale, String style, String variation) {
		super(scope, name, locale, style, variation);
	}

	/**
	 * @return a special YUI compressed resource when {@link WiQuerySettings#isMinifiedResources()} tells us to.
	 */
	@Override
	public IResource getResource() {
		if (WiQuerySettings.get().isMinifiedResources())
			return new WiQueryYUICompressedResource(getScope(), getName(), getLocale(), getStyle(), getVariation());
		
		return super.getResource();
	}
}
