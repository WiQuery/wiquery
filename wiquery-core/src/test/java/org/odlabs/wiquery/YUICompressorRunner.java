package org.odlabs.wiquery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.odlabs.wiquery.core.commons.YUIJavaScriptCompressor;

/**
 * @author Hielke Hoeve
 * 
 *         Main class to simplify minimizing javascript.
 */
public class YUICompressorRunner {

	public static void main(String[] args) throws IOException {

		InputStream stream = YUICompressorRunner.class.getResourceAsStream("");
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		while (stream.read(buf) > 0)
			outstream.write(buf);

		YUIJavaScriptCompressor compressor = new YUIJavaScriptCompressor();
		String compressed = compressor.compress(new String(outstream
				.toByteArray()));
		System.out.print(compressed);
	}
}
