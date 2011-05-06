package org.odlabs.wiquery.core.commons;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * <p>Wicket Javascript Compressor implementation which compresses StyleSheet using the YUI Compressor.</p>
 * 
 * <p>To use this class use: {@code Application.getResourceSettings().setJavaScriptCompressor(new YUIJavaScriptCompressor());}</p>
 * 
 * @author Vincent Heet
 */
public class YUIStyleSheetCompressor implements IJavaScriptCompressor
{
	private static final Logger log = LoggerFactory.getLogger(YUIStyleSheetCompressor.class);

	public String compress(String original)
	{
		StringReader originalJsReader = new StringReader(original);
		StringWriter compressedJs = new StringWriter();
		try
		{
			CssCompressor compressor = new CssCompressor(originalJsReader);
			compressor.compress(compressedJs, -1);
			compressedJs.flush();
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			return original;
		}

		return compressedJs.toString();
	}
}
