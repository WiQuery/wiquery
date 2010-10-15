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
import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * <p>
 * Compresses the css resource on the fly with YUI Compressor. Then passes it on
 * to the {@link CompressedPackageResource} which gzips it.
 * </p>
 * 
 * @author Hielke Hoeve
 * @since 1.0
 */
public class WiQueryYUICompressedStyleSheetResource extends
		CompressedPackageResource {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(WiQueryYUICompressedStyleSheetResource.class);

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
				CssCompressor compressor;

				compressor = new CssCompressor(new InputStreamReader(stream
						.getInputStream()));
				ByteArrayOutputStream outstream = new ByteArrayOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(outstream,
						"UTF-8");
				compressor.compress(writer, -1);
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

	protected WiQueryYUICompressedStyleSheetResource(Class<?> scope,
			String path, Locale locale, String style) {
		super(scope, path, locale, style);
	}

	@Override
	protected IResourceStream getPackageResourceStream() {
		return new YUICompressedResourceStream() {
			private static final long serialVersionUID = 1L;

			@Override
			protected IResourceStream getOriginalResourceStream() {
				return WiQueryYUICompressedStyleSheetResource.super.getPackageResourceStream();
			}
		};
	}

	public static WiQueryYUICompressedStyleSheetResource newPackageResource(
			Class<?> scope, String name, Locale locale, String style) {
		return new WiQueryYUICompressedStyleSheetResource(scope, name, locale,
				style);
	}
}
