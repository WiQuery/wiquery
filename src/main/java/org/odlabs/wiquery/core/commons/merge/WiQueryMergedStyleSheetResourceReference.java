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

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.Application;
import org.apache.wicket.IClusterable;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.util.io.Streams;
import org.apache.wicket.util.lang.Packages;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id$
 * 
 * <p>
 * Merged many stylesheet {@link ResourceReference} into one {@link IResourceStream}
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 *
 */
public class WiQueryMergedStyleSheetResourceReference extends
	ResourceReference implements IClusterable {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 6038498199511603297L;
	
	/** Name of the template for our ResourceReference */
	private static final String TEMPLATE_NAME = "wiquery-merged.css";
	
	/** Encoding format */
	private static final String ENCODING_FORMAT = "UTF-8";
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(WiQueryMergedStyleSheetResourceReference.class);
	
	// Properties
	private PackagedTextTemplate csstemplate;
	private WiQueryHeaderResponse wiQueryHeaderResponse;
	
	/**
	 * Default constructor
	 */
	public WiQueryMergedStyleSheetResourceReference(WiQueryHeaderResponse wiQueryHeaderResponse) {
		super(WiQueryMergedStyleSheetResourceReference.class, 
				TEMPLATE_NAME + "_" + 
				WiQueryHeaderResponse.getMergedResourceName(wiQueryHeaderResponse.getStylesheet()));
		
		this.wiQueryHeaderResponse = wiQueryHeaderResponse;
		csstemplate = new PackagedTextTemplate(
				WiQueryMergedStyleSheetResourceReference.class, 
				TEMPLATE_NAME);
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
	 * @see org.apache.wicket.ResourceReference#newResource()
	 */
	@Override
	protected Resource newResource() {
		return new Resource() {
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 * @see org.apache.wicket.Resource#getResourceStream()
			 */
			public IResourceStream getResourceStream() {
				String temp;
				String cssUrl;
				String name;
				StringBuffer buffer = new StringBuffer();
				HttpServletRequest request = ((WebRequest)RequestCycle.get().getRequest()).getHttpServletRequest();
				String baseHost = request.getRequestURL().toString();
				baseHost = baseHost.substring(0, baseHost.indexOf(request.getRequestURI()))
					+ request.getContextPath() + "/resources/";
				
				for(ResourceReference ref : wiQueryHeaderResponse.getStylesheet()){
					// We bind the resources into the SharedResources
					ref.bind(Application.get());
					
					// We insert the javascript code into the template
					try {
						
						temp = Streams.readString(
								getClass().getResourceAsStream(
										"/" + Packages.absolutePath(
												ref.getScope(),	"") 
												+ "/" + ref.getName()),
								ENCODING_FORMAT);
						
						// Replace of url in the css file
						name = ref.getName();
						cssUrl = baseHost + ref.getScope().getName() + "/"
							+ (name.indexOf("/") < 0 ? "" : name.substring(0, name.lastIndexOf("/")))
							+ "/";
						
						// Remove the simple et double quote
						temp = temp.replace("url('", "url(");
						temp = temp.replace("url(\"", "url(");
						temp = temp.replace("')", ")");
						temp = temp.replace("\")", ")");
						
						// Preserve the url with an http url
						temp = temp.replace("url(http", "wiqueryUrlTemp");
						
						// Change the url
						temp = temp.replace("url(", "url(" + cssUrl);
						
						// Retrieve the preserved url
						temp = temp.replace("wiqueryUrlTemp", "url(http");
						
					} catch (Exception e) {
						temp = null;
						e.printStackTrace();
						LOGGER.error("error in merged processing", e);
					}
					
					if(temp != null){
						buffer.append(temp).append("\r\n");
					}
				}
				
				Map<String, Object> genCss= new HashMap<String, Object>();
				genCss.put("wiqueryresources", buffer);
				csstemplate.interpolate(genCss);
				
				return new StringResourceStream(csstemplate.asString());
			}
		};
	}
}
