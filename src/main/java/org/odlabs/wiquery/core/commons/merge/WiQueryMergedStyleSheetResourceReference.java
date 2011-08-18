/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.core.commons.merge;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.Application;
import org.apache.wicket.IClusterable;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CompressedPackageResource;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.util.io.Streams;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.string.UrlUtils;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id: WiQueryMergedStyleSheetResourceReference.java 1156 2011-08-17 08:04:07Z
 * hielke.hoeve@gmail.com $
 * 
 * <p>
 * Merged many stylesheet {@link ResourceReference} into one
 * {@link IResourceStream}
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 * 
 */
public class WiQueryMergedStyleSheetResourceReference extends
		CompressedResourceReference implements IClusterable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6038498199511603297L;

	/** Name of the template for our ResourceReference */
	private static final String TEMPLATE_NAME = "wiquery-merged.css";

	/** Content-type */
	private static final String CONTENT_TYPE = "text/css";

	/** Regular expression to find the url */
	private static final String REGEX = "url\\s*?\\(.*?\\)";

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WiQueryMergedStyleSheetResourceReference.class);

	/**
	 * Convert local URL for the merging stylesheet( the url will be broken, so
	 * we have to rewrite it !!)
	 * 
	 * @param url
	 * @param baseReference
	 *            the reference to the parent CSS file (containing the
	 *            url("...") stuff)
	 * @return
	 */
	protected static String getCssUrl(String url,
			ResourceReference baseReference) {
		String cleaned = url.replace("'", "").replace("\"", "").trim();
		cleaned = cleaned.substring(3).trim(); // remove 'url'
		cleaned = cleaned.substring(1).trim(); // remove '('
		cleaned = cleaned.substring(0, cleaned.length() - 1).trim(); // remove ')'

		StringBuilder buffer = new StringBuilder();
		buffer.append("url(\"");

		if (UrlUtils.isRelative(cleaned)) {
			// the url is relative to the parent CSS, so we build its path from
			// the parent
			String urlPath = new File(
					new File(baseReference.getName()).getParentFile(), cleaned)
					.getPath();
			
			// we build a very new reference, so Wicket could give us the
			// correct URL for the url("...") stuff
			ResourceReference urlReference = new ResourceReference(
					baseReference.getScope(), urlPath,
					baseReference.getLocale(), baseReference.getStyle());
			buffer.append(RequestCycle.get().urlFor(urlReference));

		} else {
			buffer.append(cleaned);
		}

		buffer.append("\")");
		return buffer.toString();
	}

	public static String fixCssUrls(String temp, ResourceReference ref) {
		String old;
		String match;

		// Replace of url in the css file (regexp: url\(.*?\) )
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(temp); // get a matcher object
		int count = 0;
		while (m.find()) {
			count++;
			match = m.group();
			old = getCssUrl(match, ref).trim();

			if (!old.equals(match)) {
				temp = temp.replace(match, old);
			}
		}

		return temp;
	}

	// Properties
	private PackagedTextTemplate csstemplate;
	private WiQueryHeaderResponse wiQueryHeaderResponse;

	/**
	 * Default constructor
	 */
	public WiQueryMergedStyleSheetResourceReference(
			WiQueryHeaderResponse wiQueryHeaderResponse) {
		super(WiQueryMergedStyleSheetResourceReference.class, TEMPLATE_NAME
				+ "_"
				+ WiQueryHeaderResponse
						.getMergedResourceName(wiQueryHeaderResponse
								.getStylesheet()));

		this.wiQueryHeaderResponse = wiQueryHeaderResponse;
		csstemplate = new PackagedTextTemplate(
				WiQueryMergedStyleSheetResourceReference.class, TEMPLATE_NAME);
	}

	/**
	 * Returns the last modified time of the {@link PackagedTextTemplate}
	 * itself.
	 * 
	 * @return the last modified time of the {@link PackagedTextTemplate} itself
	 */
	public Time lastModifiedTime() {
		return csstemplate.lastModifiedTime();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.wicket.ResourceReference#newResource()
	 */
	@Override
	protected Resource newResource() {
		return new Resource() {
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.apache.wicket.Resource#getResourceStream()
			 */
			public IResourceStream getResourceStream() {
				return newResourceStream();
			}
		};
	}

	private IResourceStream newResourceStream() {
		String temp = null;
		StringBuffer buffer = new StringBuffer();

		HttpServletRequest request = ((WebRequest) RequestCycle.get()
				.getRequest()).getHttpServletRequest();
		String baseHost = request.getRequestURL().toString();
		baseHost = baseHost.substring(0,
				baseHost.indexOf(request.getRequestURI()))
				+ request.getContextPath() + "/resources/";

		for (ResourceReference ref : wiQueryHeaderResponse.getStylesheet()) {
			// We bind the resources into the SharedResources
			ref.bind(Application.get());

			// We insert the javascript code into the template
			try {

				InputStream resourceStream = ref.getResource()
						.getResourceStream().getInputStream();
				// hum, not good to uncompress, but how to achieve this since
				// the CompressingResourceStream is protected ?
				if (ref.getResource() instanceof CompressedPackageResource) {
					resourceStream = new GZIPInputStream(resourceStream);
				}

				temp = Streams.readString(resourceStream, Application.get()
						.getMarkupSettings().getDefaultMarkupEncoding());
				temp = fixCssUrls(temp, ref);

			} catch (Exception e) {
				temp = null;
				e.printStackTrace();
				LOGGER.error("error in merged processing", e);
			}

			if (temp != null) {
				buffer.append(temp).append("\r\n");
			}
		}

		Map<String, Object> genCss = new HashMap<String, Object>();
		genCss.put("wiqueryresources", buffer);
		csstemplate.interpolate(genCss);

		return new StringResourceStream(csstemplate.asString(), CONTENT_TYPE);
	}
}
