package org.odlabs.wiquery.core.commons.compressed;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.ByteArrayOutputStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * $Id$
 * 
 * <p>
 * 	Stream to compress the Javascript with YUI Compressor. Used in {@link WiQueryYUICompressedJavaScriptResource}
 * </p>
 *
 * @author Hielke Hoeve
 * @author Pepijn de Geus <pepijn@service2media.com>
 * @since 1.1.2
 */
public abstract class WiQueryYUICompressedJavascriptResourceStream extends WiQueryYUICompressedResourceStream {
    
	private static final long serialVersionUID = 1L;
	
	/** Wrapper to let JavaScript parser report his errors, avoids nullpointers */
    private static final ErrorReporter logWrap = new ErrorReporter() {
        public void error(String arg0, String arg1, int arg2, String arg3, int arg4) {
            log.error(arg0);
        }

        public EvaluatorException runtimeError(String arg0, String arg1, int arg2, String arg3, int arg4) {
            return new EvaluatorException(arg0, arg1, arg2, arg3, arg4);
        }

        public void warning(String arg0, String arg1, int arg2, String arg3, int arg4) {
            log.warn(arg0);
        }
    };

	@Override
	protected byte[] getCompressedContent(IResourceStream stream) {
		OutputStreamWriter writer = null;
		ByteArrayOutputStream outstream = null;
		byte[] res;
		
		try {
		    JavaScriptCompressor compressor = new JavaScriptCompressor(new InputStreamReader(stream.getInputStream()), logWrap);
			outstream = new ByteArrayOutputStream();

			writer = new OutputStreamWriter(outstream, "UTF-8");
			compressor.compress(writer, -1, true, false, false, false);
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