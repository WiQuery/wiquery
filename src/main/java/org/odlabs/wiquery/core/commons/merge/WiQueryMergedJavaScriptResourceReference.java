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

import org.apache.wicket.Application;
import org.apache.wicket.IClusterable;
import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.io.Streams;
import org.apache.wicket.util.lang.Packages;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id: WiQueryMergedJavaScriptResourceReference.java 412 2010-09-17 21:23:25Z
 * lionel.armanet $
 * <p>
 * Merged many {@link JavascriptResourceReference} into one
 * {@link IResourceStream}
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class WiQueryMergedJavaScriptResourceReference extends
		PackageResourceReference implements IClusterable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6038498199511603297L;

	/** Name of the template for our ResourceReference */
	private static final String TEMPLATE_NAME = "wiquery-merged.js";

	/** Content-type */
	private static final String CONTENT_TYPE = "text/javascript";

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WiQueryMergedJavaScriptResourceReference.class);

	// Properties
	private PackageTextTemplate jstemplate;
	private WiQueryHeaderResponse wiQueryHeaderResponse;

	public WiQueryMergedJavaScriptResourceReference(
			WiQueryHeaderResponse wiQueryHeaderResponse) {
		super(WiQueryMergedJavaScriptResourceReference.class, TEMPLATE_NAME
				+ "_"
				+ WiQueryHeaderResponse
						.getMergedResourceName(wiQueryHeaderResponse
								.getJavascript()));

		this.wiQueryHeaderResponse = wiQueryHeaderResponse;
		jstemplate = new PackageTextTemplate(
				WiQueryMergedJavaScriptResourceReference.class, TEMPLATE_NAME);
	}

	/**
	 * Returns the last modified time of the {@link PackagedTextTemplate}
	 * itself.
	 * 
	 * @return the last modified time of the {@link PackagedTextTemplate} itself
	 */
	public Time lastModifiedTime() {
		return jstemplate.lastModifiedTime();
	}

	@Override
	public IResource getResource() {
		return new PackageResource(getScope(), getName(),
				getLocale(), getStyle(), getVariation()) {

			private static final long serialVersionUID = 1L;

			public IResourceStream getResourceStream() {
				String temp;
				Application application = Application.get();
				StringBuffer buffer = new StringBuffer();
				IJavaScriptCompressor compressor = application
						.getResourceSettings().getJavaScriptCompressor();

				for (ResourceReference ref : wiQueryHeaderResponse
						.getJavascript()) {

					// We insert the javascript code into the template
					try {
						temp = Streams.readString(getClass()
								.getResourceAsStream(
										"/"
												+ Packages.absolutePath(
														ref.getScope(), "")
												+ "/" + ref.getName()));
					} catch (Exception e) {
						temp = null;
						e.printStackTrace();
						LOGGER.error("error in merged processing", e);
					}

					if (compressor != null && temp != null) {
						temp = compressor.compress(temp);
					}

					if (temp != null) {
						buffer.append(temp).append("\r\n");
					}
				}

				Map<String, Object> genJs = new HashMap<String, Object>();
				genJs.put("wiqueryresources", buffer);
				jstemplate.interpolate(genJs);

				return new StringResourceStream(jstemplate.asString(),
						CONTENT_TYPE);
			}
		};
	}
}
