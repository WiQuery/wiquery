package org.odlabs.wiquery.core.commons.compressed;

import java.util.Locale;

import org.apache.wicket.markup.html.CompressedPackageResource;
import org.apache.wicket.util.resource.IResourceStream;

/**
 * $Id: WiQueryYUICompressedStyleSheetResource Julien Roche $
 * 
 * <p>
 * Compresses the css resource on the fly with YUI Compressor. Then passes it on
 * to the {@link CompressedPackageResource} which gzips it.
 * </p>
 * 
 * @author Hielke Hoeve
 * @since 1.1
 */
public class WiQueryYUICompressedStyleSheetResource extends
		CompressedPackageResource {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Hidden constructor.
	 * 
	 * @param scope
	 *            This argument will be used to get the class loader for loading the package
	 *            resource, and to determine what package it is in
	 * @param path
	 *            The path to the resource
	 * @param locale
	 *            The locale of the resource
	 * @param style
	 *            The style of the resource
	 */
	protected WiQueryYUICompressedStyleSheetResource(Class<?> scope,
			String path, Locale locale, String style) {
		super(scope, path, locale, style);
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.CompressedPackageResource#getPackageResourceStream()
	 */
	@Override
	protected IResourceStream getPackageResourceStream() {
		return new WiQueryYUICompressedStyleSheetResourceStream() {
			private static final long serialVersionUID = 1L;

			@Override
			protected IResourceStream getOriginalResourceStream() {
				return WiQueryYUICompressedStyleSheetResource.super.getPackageResourceStream();
			}
		};
	}

	/**
	 * Get a {@link WiQueryYUICompressedStyleSheetResource}
	 * @param scope
	 * @param name
	 * @param locale
	 * @param style
	 * @return
	 */
	public static WiQueryYUICompressedStyleSheetResource newPackageResource(
			Class<?> scope, String name, Locale locale, String style) {
		return new WiQueryYUICompressedStyleSheetResource(scope, name, locale,
				style);
	}
}
