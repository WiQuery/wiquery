package org.odlabs.wiquery.core.commons.compressed;

import java.util.Locale;

import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.util.resource.IResourceStream;

/**
 * $Id: WiQueryYUICompressedStyleSheetResource Julien Roche $
 * <p>
 * Compresses the css resource on the fly with YUI Compressor. Then passes it on
 * to the {@link CompressedPackageResource} which gzips it.
 * </p>
 * 
 * @author Hielke Hoeve
 * @since 1.1
 */
public class WiQueryYUICompressedStyleSheetResource extends
		PackageResource {
	private static final long serialVersionUID = 1L;

	/**
	 * Hidden constructor, use
	 * {@link WiQueryYUICompressedJavaScriptResourceReference}.
	 */
	protected WiQueryYUICompressedStyleSheetResource(Class<?> scope,
			String path, Locale locale, String style, String variation) {
		super(scope, path, locale, style, variation);
	}

	/**
	 * @return a YUI compressed resource. When serving a YUI compressed resource
	 *         we get the resource from the underlying resource (
	 *         {@link PackageResource}) and compress it. In essence we
	 *         circumvent the CompressedPackageResource level.
	 */
	@Override
	protected IResourceStream getResourceStream() {
		return new WiQueryYUICompressedStyleSheetResourceStream() {
			private static final long serialVersionUID = 1L;

			@Override
			protected IResourceStream getOriginalResourceStream() {
				return WiQueryYUICompressedStyleSheetResource.super
						.getResourceStream();
			}
		};
	}
}
