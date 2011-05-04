package org.odlabs.wiquery.core.commons.compressed;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.resource.PackageResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class WiQueryYUICompressedResourceStream extends PackageResourceStream {

	private static final long serialVersionUID = 1L;

	protected static final Logger log = LoggerFactory
			.getLogger(WiQueryYUICompressedResourceStream.class);

	private static final ErrorReporter logWrap = new ErrorReporter() {
		public void error(String arg0, String arg1, int arg2, String arg3,
				int arg4) {
			log.error(arg0);
		}

		public EvaluatorException runtimeError(String arg0, String arg1,
				int arg2, String arg3, int arg4) {
			return new EvaluatorException(arg0, arg1, arg2, arg3, arg4);
		}

		public void warning(String arg0, String arg1, int arg2, String arg3,
				int arg4) {
			log.warn(arg0);
		}
	};

	/** Soft-references compressed data, this heavily increases performance */
	private ThreadLocal<InputStream> stream = new ThreadLocal<InputStream>();

	/** Timestamp of the cache */
	private Time timeStamp = null;

	public WiQueryYUICompressedResourceStream(Class<?> scope, String path) {
		super(scope, path);
	}

	public InputStream getOriginalInputStream()
			throws ResourceStreamNotFoundException {
		return super.getInputStream();
	}

	@Override
	public InputStream getInputStream() throws ResourceStreamNotFoundException {
		InputStream ret;

		// Cached version available?
		if (stream.get() != null) {
			ret = stream.get();
			if (ret != null && timeStamp != null) {
				if (timeStamp.equals(lastModifiedTime())) {
					return ret;
				}
			}
		}

		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		OutputStreamWriter writer = null;
		InputStream origStream = getOriginalInputStream();

		try {
			writer = new OutputStreamWriter(outstream, "UTF-8");
			compress(origStream, writer);

			ret = new ByteArrayInputStream(outstream.toByteArray());

			stream.set(ret);
			timeStamp = lastModifiedTime();

		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new WicketRuntimeException(e);
		} finally {
			try {
				origStream.close();
			} catch (IOException e) {
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
			try {
				outstream.close();
			} catch (IOException e) {
			}
		}

		return ret;
	}

	private void compress(InputStream origStream, OutputStreamWriter writer)
			throws EvaluatorException, IOException {
		if (getContentType().contains("javascript")) {
			JavaScriptCompressor compressor = new JavaScriptCompressor(
					new InputStreamReader(origStream), logWrap);
			compressor.compress(writer, -1, true, false, false, false);
			writer.flush();
		} else if (getContentType().contains("css")) {
			CssCompressor compressor = new CssCompressor(new InputStreamReader(
					origStream));
			compressor.compress(writer, -1);
			writer.flush();
		}
	}

	public void close() throws IOException {
		IOException ex = null;

		InputStream outStream = stream.get();
		if (outStream != null) {
			try {
				outStream.close();
			} catch (IOException e) {
				ex = e;
			}
			stream.remove();
			timeStamp = null;
		}

		if (ex != null)
			throw ex;
	}

	/**
	 * @return the number of bytes of the compressed stream
	 */
	@Override
	public Bytes length() {
		return super.length();
	}
}
