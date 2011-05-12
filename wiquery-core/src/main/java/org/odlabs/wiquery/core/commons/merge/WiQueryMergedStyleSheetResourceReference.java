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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.IClusterable;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.ResourceStreamResource;
import org.apache.wicket.util.io.Streams;
import org.apache.wicket.util.lang.Packages;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id: WiQueryMergedStyleSheetResourceReference.java 752 2011-04-06 14:19:21Z
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
		ResourceReference implements IClusterable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6038498199511603297L;

	/** Name of the template for our ResourceReference */
	private static final String TEMPLATE_NAME = "wiquery-merged.css";

	/** Content-type */
	private static final String CONTENT_TYPE = "text/css";

	/** Regular expression to find the url */
	private static final String REGEX = "url\\(.*?\\)";

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WiQueryMergedStyleSheetResourceReference.class);

	/**
	 * Convert local URL for the merging stylesheet( the url will be broken, so
	 * we have to rewrite it !!)
	 * 
	 * @param url
	 * @param baseUrl
	 * @return
	 */
	protected static String getCssUrl(String url, String baseUrl) {
		String cleaned = url.replace(" ", "").replace("'", "")
				.replace("\"", "");
		cleaned = cleaned.substring(4); // remove '('
		cleaned = cleaned.substring(0, cleaned.length() - 1); // remove ')'

		if (cleaned.startsWith("http:") || cleaned.startsWith("https:")
				|| cleaned.startsWith("ftp:") || cleaned.startsWith("file:")) {
			return "url(\"" + cleaned + "\")"; // Quotes are important for the
												// merging process
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("url(\"");
		buffer.append(baseUrl);
		buffer.append(cleaned);
		buffer.append("\")");
		return buffer.toString();
	}

	// Properties
	private PackageTextTemplate csstemplate;
	private WiQueryHeaderResponse wiQueryHeaderResponse;

	public WiQueryMergedStyleSheetResourceReference(
			WiQueryHeaderResponse wiQueryHeaderResponse) {
		super(WiQueryMergedStyleSheetResourceReference.class, TEMPLATE_NAME
				+ "_"
				+ WiQueryHeaderResponse
						.getMergedResourceName(wiQueryHeaderResponse
								.getStylesheet()));

		this.wiQueryHeaderResponse = wiQueryHeaderResponse;
		csstemplate = new PackageTextTemplate(
				WiQueryMergedStyleSheetResourceReference.class, TEMPLATE_NAME);
	}

	/**
	 * Returns the last modified time of the {@link PackageTextTemplate} itself.
	 * 
	 * @return the last modified time of the {@link PackageTextTemplate} itself
	 */
	public Time lastModifiedTime() {
		return csstemplate.lastModifiedTime();
	}

	@Override
	public IResource getResource() {
			return new ResourceStreamResource(newResourceStream());
	}

	private IResourceStream newResourceStream() {
		String temp;
		StringBuilder cssUrl = new StringBuilder();
		String name;
		String old;
		String match;
		StringBuffer buffer = new StringBuffer();

		HttpServletRequest request = ((HttpServletRequest) RequestCycle.get()
				.getRequest());
		StringBuilder baseHost = new StringBuilder(); 
		baseHost.append(request.getRequestURL().toString().substring(0,
				request.getRequestURL().toString().indexOf(request.getRequestURI())));
		baseHost.append(request.getContextPath()).append("/resources/");

		for (ResourceReference ref : wiQueryHeaderResponse.getStylesheet()) {

			// We insert the javascript code into the template
			StringBuilder resourceName = new StringBuilder(); 
			try {
				resourceName.append("/")
						.append(Packages.absolutePath(ref.getScope(), ""))
						.append("/").append(ref.getName());
				temp = Streams.readString(getClass().getResourceAsStream(resourceName.toString()));

				// Replace of url in the css file (regexp: url\(.*?\) )
				name = ref.getName();
				cssUrl.append(baseHost)
						.append( ref.getScope().getName())
						.append( "/")
						.append( name.indexOf("/") < 0 ? "" : name.substring(0,
								name.lastIndexOf("/"))) .append("/");

				Pattern p = Pattern.compile(REGEX);
				Matcher m = p.matcher(temp); // get a matcher object
				int count = 0;
				while (m.find()) {
					count++;
					match = m.group();
					old = getCssUrl(match, cssUrl.toString());

					if (!old.equals(match)) {
						temp = temp.replace(match, old);
					}
				}

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
