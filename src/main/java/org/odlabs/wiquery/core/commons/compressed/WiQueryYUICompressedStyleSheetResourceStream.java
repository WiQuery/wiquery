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
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * $Id$
 * 
 * <p>
 * 	Stream to compress the stylesheet with YUI Compressor. Used in {@link WiQueryYUICompressedStyleSheetResource}
 * </p>
 *
 * @author Hielke Hoeve
 * @since 1.1.2
 */
public abstract class WiQueryYUICompressedStyleSheetResourceStream implements IResourceStream {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static final Logger log = LoggerFactory
	.getLogger(WiQueryYUICompressedStyleSheetResourceStream.class);

	/** Cache for compressed data */
	private transient SoftReference<byte[]> cache = new SoftReference<byte[]>(
			null);

	/** Timestamp of the cache */
	private Time timeStamp = null;

	public void close() throws IOException {
	}

	/**
	 * @return
	 */
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

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.util.resource.IResourceStream#getContentType()
	 */
	public String getContentType() {
		return getOriginalResourceStream().getContentType();
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.util.resource.IResourceStream#getInputStream()
	 */
	public InputStream getInputStream()
			throws ResourceStreamNotFoundException {
		return new ByteArrayInputStream(getCompressedContent());
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.util.resource.IResourceStream#getLocale()
	 */
	public Locale getLocale() {
		return getOriginalResourceStream().getLocale();
	}

	/**
	 * @return the {@link IResourceStream} to use
	 */
	protected abstract IResourceStream getOriginalResourceStream();

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.util.watch.IModifiable#lastModifiedTime()
	 */
	public Time lastModifiedTime() {
		return getOriginalResourceStream().lastModifiedTime();
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.util.resource.IResourceStream#length()
	 */
	public long length() {
		return getCompressedContent().length;
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.util.resource.IResourceStream#setLocale(java.util.Locale)
	 */
	public void setLocale(Locale locale) {
		getOriginalResourceStream().setLocale(locale);
	}

}
