package org.odlabs.wiquery.core.compression;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * <p>
 * Wicket Javascript Compressor implementation which compresses Javascript using the YUI
 * Compressor.
 * </p>
 * 
 * <p>
 * To use this class use:
 * {@code Application.getResourceSettings().setJavaScriptCompressor(new YUIJavaScriptCompressor());}
 * </p>
 * 
 * @author Vincent Heet
 */
public class YUIJavaScriptCompressor implements IJavaScriptCompressor
{
	private static final Logger log = LoggerFactory.getLogger(YUIJavaScriptCompressor.class);

	/** Wrapper to let JavaScript parser report his errors, avoids nullpointers */
	private static final ErrorReporter logWrap = new ErrorReporter()
	{
		public void error(String arg0, String arg1, int arg2, String arg3, int arg4)
		{
			log.error(arg0);
		}

		public EvaluatorException runtimeError(String arg0, String arg1, int arg2, String arg3,
				int arg4)
		{
			return new EvaluatorException(arg0, arg1, arg2, arg3, arg4);
		}

		public void warning(String arg0, String arg1, int arg2, String arg3, int arg4)
		{
			log.warn(arg0);
		}
	};

	public String compress(String original)
	{
		long startMillis = 0;
		long endMillis = 0;
		if (log.isDebugEnabled())
			startMillis = System.currentTimeMillis();

		StringReader originalJsReader = new StringReader(original);
		StringWriter compressedJs = new StringWriter();
		try
		{
			JavaScriptCompressor compressor = new JavaScriptCompressor(originalJsReader, logWrap);
			compressor.compress(compressedJs, -1, true, false, false, false);
			compressedJs.flush();

			if (log.isDebugEnabled())
			{
				endMillis = System.currentTimeMillis();
				log.debug("Compressed JS in " + (endMillis - startMillis) + "ms");
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			return original;
		}

		return compressedJs.toString();
	}
}
