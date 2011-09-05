package org.odlabs.wiquery.core.compression;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.wicket.css.ICssCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * <p>
 * Wicket Javascript StyleSheet implementation which compresses StyleSheet using the YUI
 * Compressor.
 * </p>
 * 
 * <p>
 * To use this class use:
 * {@code Application.getResourceSettings().setCssCompressor(new YUIStyleSheetCompressor());}
 * </p>
 * 
 * @author Vincent Heet
 */
public class YUIStyleSheetCompressor implements ICssCompressor
{
	private static final Logger log = LoggerFactory.getLogger(YUIStyleSheetCompressor.class);

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
			CssCompressor compressor = new CssCompressor(originalJsReader);
			compressor.compress(compressedJs, -1);
			compressedJs.flush();

			if (log.isDebugEnabled())
			{
				endMillis = System.currentTimeMillis();
				log.debug("Compressed CSS in " + (endMillis - startMillis) + "ms");
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
