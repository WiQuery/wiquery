package org.odlabs.wiquery.core.commons.compressed;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.wicket.javascript.IJavascriptCompressor;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * Wicket Javascript Compressor implementation which compresses Javascript using
 * the YUI Compressor.
 * 
 * @author Vincent Heet
 */
public class YUIJavaScriptCompressor implements IJavascriptCompressor {
	private static final Logger log = LoggerFactory
			.getLogger(YUIJavaScriptCompressor.class);

	/** Wrapper to let JavaScript parser report his errors, avoids nullpointers */
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

	public String compress(String original) {
		StringReader originalJsReader = new StringReader(original);
		StringWriter compressedJs = new StringWriter();
		StringWriter mappedJs = new StringWriter();
		try {
			JavaScriptCompressor compressor = new JavaScriptCompressor(
					originalJsReader, logWrap);
			compressor.compress(compressedJs, mappedJs, -1, true, false, false, false);
			compressedJs.flush();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return original;
		} catch (RuntimeException e) {
			if (e.getMessage() == null || e.getMessage().trim().isEmpty())
				log.error("Failed to compress javascript, no reason was given.");
			else
				log.error(e.getMessage(), e);

			return original;
		}

		return compressedJs.toString();
	}
}
