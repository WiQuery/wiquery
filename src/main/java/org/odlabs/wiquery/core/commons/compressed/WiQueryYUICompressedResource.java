package org.odlabs.wiquery.core.commons.compressed;

import java.util.Locale;

import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.util.resource.IResourceStream;

public class WiQueryYUICompressedResource extends PackageResource {

	private static final long serialVersionUID = 1L;

	private String path;
	
	public WiQueryYUICompressedResource(Class<?> scope, String path,
			Locale locale, String style, String variation) {
		super(scope, path, locale, style, variation);
		this.path=path;
	}

	@Override
	protected IResourceStream getResourceStream() {
		return new WiQueryYUICompressedResourceStream(getClass(), getPath());
	}

	public String getPath() {
		return path;
	}
}
