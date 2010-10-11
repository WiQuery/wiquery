package org.odlabs.wiquery.core.commons.compressed;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.SoftReference;
import java.util.Locale;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.CompressedPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

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
		JavascriptPackageResource {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(WiQueryYUICompressedJavaScriptResource.class);

	protected abstract class YUICompressedResourceStream implements
			IResourceStream {
		private static final long serialVersionUID = 1L;

		/** Cache for compressed data */
		private transient SoftReference<byte[]> cache = new SoftReference<byte[]>(
				null);

		/** Timestamp of the cache */
		private Time timeStamp = null;

		public void close() throws IOException {
		}

		public String getContentType() {
			return getOriginalResourceStream().getContentType();
		}

		public InputStream getInputStream()
				throws ResourceStreamNotFoundException {
			return new ByteArrayInputStream(getCompressedContent());
		}

		public Locale getLocale() {
			return getOriginalResourceStream().getLocale();
		}

		public Time lastModifiedTime() {
			return getOriginalResourceStream().lastModifiedTime();
		}

		public long length() {
			return getCompressedContent().length;
		}

		public void setLocale(Locale locale) {
			getOriginalResourceStream().setLocale(locale);
		}

		private byte[] getCompressedContent() {
			IResourceStream stream = getOriginalResourceStream();

			try {
				byte ret[];
				if (cache != null) {
					ret = cache.get();
					if (ret != null && timeStamp != null) {
						if (timeStamp.equals(stream.lastModifiedTime())) {
							return ret;
						}
					}
				}
				JavaScriptCompressor compressor;

				compressor = new JavaScriptCompressor(new InputStreamReader(
						stream.getInputStream()), null);
				ByteArrayOutputStream outstream = new ByteArrayOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(outstream,
						"UTF-8");
				compressor.compress(writer, -1, false, false, true, true);
				writer.flush();

				return outstream.toByteArray();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new WicketRuntimeException(e);
			} catch (ResourceStreamNotFoundException e) {
				log.error(e.getMessage(), e);
				throw new WicketRuntimeException(e);
			}
		}

		protected abstract IResourceStream getOriginalResourceStream();
	}

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
	// @Override
	// protected IResourceStream getPackageResourceStream() {
	// return new YUICompressedResourceStream() {
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// protected IResourceStream getOriginalResourceStream() {
	// return WiQueryYUICompressedJavaScriptResource.super
	// .getPackageResourceStream();
	// }
	// };
	// }

	public static WiQueryYUICompressedJavaScriptResource newPackageResource(
			Class<?> scope, String name, Locale locale, String style) {
		return new WiQueryYUICompressedJavaScriptResource(scope, name, locale,
				style);
	}
}
