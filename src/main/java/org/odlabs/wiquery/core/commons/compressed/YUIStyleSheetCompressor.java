package org.odlabs.wiquery.core.commons.compressed;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.wicket.javascript.IJavascriptCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;

/**
 * Wicket Javascript Compressor implementation which compresses StyleShee using
 * the YUI Compressor.
 * 
 * @author Hielke Hoeve
 */
public class YUIStyleSheetCompressor implements IJavascriptCompressor {
	private static final Logger log = LoggerFactory
			.getLogger(YUIStyleSheetCompressor.class);

	public String compress(String original) {
		long startTime = System.currentTimeMillis();
		StringReader originalCssReader = new StringReader(original);
		StringWriter compressedCss = new StringWriter();
		try {
			CssCompressor compressor = new CssCompressor(originalCssReader);
			compressor.compress(compressedCss, -1);
			compressedCss.flush();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return original;
		} catch (RuntimeException e) {
			log.error("Failed to compress stylesheet, no reason was given.");
			return original;
		}

		long endTime = System.currentTimeMillis();
		log.debug("Compressed CSS in "+(endTime-startTime)+" ms.");
		return compressedCss.toString();
	}
}
