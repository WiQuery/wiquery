package org.odlabs.wiquery.core.commons.compressed;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Locale;

import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract parent class of both the JavaScript and CSS compressing resource streams,
 * {@link WiQueryYUICompressedJavascriptResourceStream} and {@link WiQueryYUICompressedStyleSheetResourceStream}.
 * @author Hielke Hoeve 
 * @author Pepijn de Geus <pepijn@service2media.com>
 */
public abstract class WiQueryYUICompressedResourceStream implements IResourceStream {

    private static final long serialVersionUID = 1L;

    /** Logger */
    protected static final Logger log = LoggerFactory.getLogger(WiQueryYUICompressedResourceStream.class);

    /** Cache for compressed data */
    private transient SoftReference<byte[]> cache;
    
    private transient ByteArrayInputStream stream;
    private transient IResourceStream originalStream;

    /** Timestamp of the cache */
    private Time timeStamp = null;
    
    private final byte[] innerGetCompressedContent() {
        IResourceStream stream = innerGetOriginalResourceStream();
        
        byte[] ret;
        
        //Cached version available?
        if (cache != null) {
            ret = cache.get();
            if (ret != null && timeStamp != null) {
                if (timeStamp.equals(stream.lastModifiedTime())) {
                    return ret;
                }
            }
        }
        
        //No cached version; get fresh one 
        ret = getCompressedContent(stream);
        
        //Store in cache
        cache = new SoftReference<byte[]>(ret);
        timeStamp = stream.lastModifiedTime();
        
        return ret;
    }
    
    /**
     * Stores the result of getOriginalResourceStream() internally, so implementations creating
     * a new IResourceStream on every call won't be inefficient.
     * @return The {@link IResourceStream} to compress data for.
     */
    private IResourceStream innerGetOriginalResourceStream() {
        if (originalStream == null) {
            originalStream = getOriginalResourceStream();
        }
        return originalStream;
    }
    
    /**
     * Reads data from the given {@link IResourceStream} and return it in compressed
     * form as a byte array.
     * @param stream The stream to read data from.
     * @return The compressed data. 
     */
    protected abstract byte[] getCompressedContent(IResourceStream stream);
    
    /**
     * @return The {@link IResourceStream} to compress data for.
     */
    protected abstract IResourceStream getOriginalResourceStream();

    /**
     * {@inheritDoc}
     * @see org.apache.wicket.util.resource.IResourceStream#close()
     */
    public void close() throws IOException {
        IOException ex = null;
        
        try {
            if (originalStream != null) originalStream.close();
        } catch (IOException e) {
            ex = e;
        }
        originalStream = null;
        
        try {
            if (stream != null) stream.close();
        } catch (IOException e) {
            ex = e;
        }

        stream = null;
        if (ex != null) throw ex;
    }

    /**
     * {@inheritDoc}
     * @see org.apache.wicket.util.resource.IResourceStream#getInputStream()
     */
    public InputStream getInputStream() throws ResourceStreamNotFoundException {
        if (stream == null) {
            stream = new ByteArrayInputStream(innerGetCompressedContent());
        }
        return stream;
    }
    
    /**
     * {@inheritDoc}
     * @see org.apache.wicket.util.resource.IResourceStream#getContentType()
     */
    public String getContentType() {
        return innerGetOriginalResourceStream().getContentType();
    }

    /**
     * {@inheritDoc}
     * @see org.apache.wicket.util.resource.IResourceStream#getLocale()
     */
    public Locale getLocale() {
        return innerGetOriginalResourceStream().getLocale();
    }

    /**
     * {@inheritDoc}
     * @see org.apache.wicket.util.watch.IModifiable#lastModifiedTime()
     */
    public Time lastModifiedTime() {
        return innerGetOriginalResourceStream().lastModifiedTime();
    }

    /**
     * {@inheritDoc}
     * @see org.apache.wicket.util.resource.IResourceStream#length()
     */
    public long length() {
        return innerGetCompressedContent().length;
    }

    /**
     * {@inheritDoc}
     * @see org.apache.wicket.util.resource.IResourceStream#setLocale(java.util.Locale)
     */
    public void setLocale(Locale locale) {
        innerGetOriginalResourceStream().setLocale(locale);
    }

}