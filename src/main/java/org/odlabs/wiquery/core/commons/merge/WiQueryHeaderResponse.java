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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Response;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.internal.HeaderResponse;

/**
 * Custom {@link HeaderResponse} to collect the needed resources
 * 
 * @author Julien Roche
 *
 */
public class WiQueryHeaderResponse extends HeaderResponse implements Serializable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private final Set<ResourceReference> javascript;
	private final Set<ResourceReference> stylesheet;
	private final Set<Object> objects;
	private transient IHeaderResponse iHeaderResponse;
	
	/**
	 * Default constructor
	 */
	public WiQueryHeaderResponse() {
		this(null);
	}
	
	/**
	 * Method calculating the names for the merged resources (for the browser's cache)
	 * @param resources
	 * @return the name
	 */
	public static CharSequence getMergedResourceName(Collection<ResourceReference> resources) {
		StringBuffer buffer = new StringBuffer();
		
		for(ResourceReference r : resources) {
			buffer.append(r.getClass().getSimpleName() + "_");
			buffer.append((r.getName().indexOf('/') >= 0) ? 
					r.getName().substring(r.getName().lastIndexOf('/') + 1) : 
						r.getName() + "_");
		}
		
		return buffer;
	}
	
	/**
	 * Constructor
	 * @param iHeaderResponse
	 */
	public WiQueryHeaderResponse(IHeaderResponse iHeaderResponse) {
		javascript = new LinkedHashSet<ResourceReference>();
		stylesheet = new LinkedHashSet<ResourceReference>();
		objects = new HashSet<Object>();
		this.iHeaderResponse = iHeaderResponse;
	}

	/**
	 * @return the current header response
	 */
	public IHeaderResponse getIHeaderResponse() {
		return iHeaderResponse;
	}

	/**
	 * @return the javascript from wiQuery framework and plugins
	 */
	public Set<ResourceReference> getJavascript() {
		return javascript;
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#getRealResponse()
	 */
	@Override
	protected Response getRealResponse() {
		return null;
	}

	/**
	 * @return the stylesheets from wiQuery framework and plugins
	 */
	public Set<ResourceReference> getStylesheet() {
		return stylesheet;
	}

	/**
	 * Mark the {@link ResourceReference}
	 * @param object
	 */
	private void markResourceReference(Object object) {
		iHeaderResponse.markRendered(object);
		markRendered(object);
		objects.add(object);
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderCSSReference(org.apache.wicket.ResourceReference)
	 */
	@Override
	public void renderCSSReference(ResourceReference reference) {
		Object object = Arrays.asList(new Object[] { "css", RequestCycle.get().urlFor(reference), null });
		
		if(iHeaderResponse != null && !iHeaderResponse.wasRendered(object) && !wasRendered(object)){
			stylesheet.add(reference);
			markResourceReference(object);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderCSSReference(org.apache.wicket.ResourceReference, java.lang.String)
	 */
	@Override
	public void renderCSSReference(ResourceReference reference, String media) {
		Object object = Arrays.asList(new Object[] { "css", RequestCycle.get().urlFor(reference), media });
		
		if(iHeaderResponse != null && !iHeaderResponse.wasRendered(object) && !wasRendered(object)){
			stylesheet.add(reference);
			markResourceReference(object);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderJavascriptReference(org.apache.wicket.ResourceReference)
	 */
	@Override
	public void renderJavascriptReference(ResourceReference reference) {
		Object object = Arrays.asList(new Object[] { "javascript", RequestCycle.get().urlFor(reference) });
		
		if(iHeaderResponse != null && !iHeaderResponse.wasRendered(object) && !wasRendered(object)){
			javascript.add(reference);
			markResourceReference(object);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderJavascriptReference(org.apache.wicket.ResourceReference, java.lang.String)
	 */
	@Override
	public void renderJavascriptReference(ResourceReference reference, String id) {
		Object object = Arrays.asList(new Object[] { "javascript", RequestCycle.get().urlFor(reference) });
		
		if(iHeaderResponse != null && !iHeaderResponse.wasRendered(object) && !wasRendered(object)){
			javascript.add(reference);
			markResourceReference(object);
		}
	}

	/**
	 * Method to change the header response
	 * @param iHeaderResponse
	 */
	public void setIHeaderResponse(IHeaderResponse iHeaderResponse) {
		this.iHeaderResponse = iHeaderResponse;
		
		if(iHeaderResponse != null){
			for(Object obj : objects){
				iHeaderResponse.markRendered(obj);
			}
		}
		
		javascript.clear(); // Flush javascript resources already loaded
		stylesheet.clear(); // Flush CSS resources already loaded
	}
}
