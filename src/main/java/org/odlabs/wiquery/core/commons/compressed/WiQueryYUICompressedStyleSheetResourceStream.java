package org.odlabs.wiquery.core.commons.compressed;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

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
public abstract class WiQueryYUICompressedStyleSheetResourceStream extends WiQueryYUICompressedResourceStream {
    
	private static final long serialVersionUID = 1L;
	
	@Override
	protected byte[] getCompressedContent(IResourceStream stream) {
		OutputStreamWriter writer = null;
		ByteArrayOutputStream outstream = null;
		byte[] res;

		try {
			CssCompressor compressor = new CssCompressor(new InputStreamReader(stream.getInputStream()));
			outstream = new ByteArrayOutputStream();
			
			writer = new OutputStreamWriter(outstream, "UTF-8");
			compressor.compress(writer, -1);
			writer.flush();
			
			res = outstream.toByteArray();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new WicketRuntimeException(e);
		} catch (ResourceStreamNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new WicketRuntimeException(e);
		} finally {
		    try {
                stream.close();
            } catch (IOException e) {}
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {}
            }
            if (outstream != null) {
                try {
                    outstream.close();
                } catch (IOException e) {}
            }
		}
		
        return res;
	}

}