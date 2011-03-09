package org.odlabs.wiquery.core.commons.compressed;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.SoftReference;
import java.util.Locale;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * $Id: WiQueryYUICompressedStyleSheetResourceStream.java 559 2010-11-17
 * 22:56:57Z roche.jul@gmail.com $
 * <p>
 * Stream to compress the stylesheet with YUI Compressor. Used in
 * {@link WiQueryYUICompressedStyleSheetResource}
 * </p>
 * 
 * @author Hielke Hoeve
 * @since 1.1.2
 */
public abstract class WiQueryYUICompressedStyleSheetResourceStream implements
		IResourceStream {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(WiQueryYUICompressedStyleSheetResourceStream.class);

	/** Cache for compressed data */
	private transient SoftReference<byte[]> cache = new SoftReference<byte[]>(
			null);

	/** Timestamp of the cache */
	private Time timeStamp = null;

	public void close() throws IOException {
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

			compressor = new CssCompressor(new InputStreamReader(
					stream.getInputStream()));
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

	public String getContentType() {
		return getOriginalResourceStream().getContentType();
	}

	public InputStream getInputStream() throws ResourceStreamNotFoundException {
		return new ByteArrayInputStream(getCompressedContent());
	}

	protected abstract IResourceStream getOriginalResourceStream();

	public Time lastModifiedTime() {
		return getOriginalResourceStream().lastModifiedTime();
	}

	public Bytes length() {
		return Bytes.bytes(getCompressedContent().length);
	}

	public Locale getLocale() {
		return getOriginalResourceStream().getLocale();
	}

	public void setLocale(Locale locale) {
		getOriginalResourceStream().setLocale(locale);
	}

	public String getStyle() {
		return getOriginalResourceStream().getStyle();
	}

	public void setStyle(String style) {
		getOriginalResourceStream().setStyle(style);
	}

	public String getVariation() {
		return getOriginalResourceStream().getVariation();
	}

	public void setVariation(String variation) {
		getOriginalResourceStream().setVariation(variation);
	}
}
