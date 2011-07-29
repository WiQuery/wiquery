package org.odlabs.wiquery.core;

import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.dependencies.AbstractResourceDependentResourceReference;
import org.apache.wicket.resource.dependencies.IResourceReferenceDependencyConfigurationService;
import org.apache.wicket.resource.dependencies.ResourceDependentResourceReference;

public class WiQueryResourceReferenceDependencyConfigurationService implements
		IResourceReferenceDependencyConfigurationService {

	public AbstractResourceDependentResourceReference configure(
			ResourceReference reference) {
		if (reference instanceof AbstractResourceDependentResourceReference)
			return (AbstractResourceDependentResourceReference) reference;

		return new ResourceDependentResourceReference(reference.getScope(),
				reference.getName(), reference.getLocale(),
				reference.getStyle(), reference.getVariation(),
				new AbstractResourceDependentResourceReference[0]);
	}
}
