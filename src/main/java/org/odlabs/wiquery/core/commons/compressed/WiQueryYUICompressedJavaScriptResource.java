package org.odlabs.wiquery.core.commons.compressed;

import java.util.Locale;

import org.apache.wicket.markup.html.CompressedPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.util.resource.IResourceStream;

/**
 * <p>
 * Compresses the js resource on the fly with YUI Compressor. Then passes it on
 * to the {@link CompressedPackageResource} which gzips it.
 * </p>
 * 
 * @author Hielke Hoeve
 * @since 1.0
 */
public class WiQueryYUICompressedJavaScriptResource extends
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
	protected WiQueryYUICompressedJavaScriptResource(Class<?> scope,
			String path, Locale locale, String style) {
		super(scope, path, locale, style);
	}

	/**
	 * TODO uncomment once the YUI Compressor dependency is working correctly.
	 * Once enabled make sure that we subclass {@link CompressedPackageResource}
	 * and no longer subclass {@link JavascriptPackageResource}.
	 * 
	 * @param scope
	 * @param name
	 * @param locale
	 * @param style
	 * @return
	 */
	@Override
	protected IResourceStream getPackageResourceStream() {
		return new WiQueryYUICompressedJavascriptResourceStream() {
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 * @see org.odlabs.wiquery.core.commons.compressed.WiQueryYUICompressedJavascriptResourceStream#getOriginalResourceStream()
			 */
			@Override
			protected IResourceStream getOriginalResourceStream() {
				return WiQueryYUICompressedJavaScriptResource.super
						.getPackageResourceStream();
			}
		};
	}

	/**
	 * @param scope
	 * @param name
	 * @param locale
	 * @param style
	 * @return
	 */
	public static WiQueryYUICompressedJavaScriptResource newPackageResource(
			Class<?> scope, String name, Locale locale, String style) {
		return new WiQueryYUICompressedJavaScriptResource(scope, name, locale,
				style);
	}
}
