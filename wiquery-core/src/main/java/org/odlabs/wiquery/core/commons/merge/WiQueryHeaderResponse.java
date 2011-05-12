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

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.internal.HeaderResponse;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.ResourceReference;
import org.odlabs.wiquery.core.commons.WiQueryUtil;

/**
 * Custom {@link HeaderResponse} to collect the needed resources
 * 
 * @author Julien Roche
 */
public class WiQueryHeaderResponse extends HeaderResponse implements
		Serializable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Method calculating the names for the merged resources (for the browser's
	 * cache)
	 * 
	 * @param resources
	 * @return the name
	 */
	public static CharSequence getMergedResourceName(
			Collection<ResourceReference> resources) {
		StringBuffer buffer = new StringBuffer();

		for (ResourceReference r : resources) {
            buffer.append(r.getClass().getSimpleName()).append("_");
			buffer.append(r.getName().replace("/", ":"));
		}

		return buffer;
	}

	// Properties
	private final Set<ResourceReference> javascript;
	private final Set<ResourceReference> stylesheet;
	private final Set<ResourceReference> javascriptUnmergeable;
	private final Set<ResourceReference> stylesheetUnmergeable;
	private final Set<Object> objects;
	private transient IHeaderResponse iHeaderResponse;

	/**
	 * Default constructor
	 */
	public WiQueryHeaderResponse() {
		this(null);
	}

	/**
	 * Constructor
	 * 
	 * @param iHeaderResponse
	 */
	public WiQueryHeaderResponse(IHeaderResponse iHeaderResponse) {
		javascript = new LinkedHashSet<ResourceReference>();
		stylesheet = new LinkedHashSet<ResourceReference>();
		javascriptUnmergeable = new LinkedHashSet<ResourceReference>();
		stylesheetUnmergeable = new LinkedHashSet<ResourceReference>();
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

	public Set<ResourceReference> getJavascriptUnmergeable() {
		return javascriptUnmergeable;
	}

	/**
	 * {@inheritDoc}
	 * 
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

	public Set<ResourceReference> getStylesheetUnmergeable() {
		return stylesheetUnmergeable;
	}

	/**
	 * Method return true if the resource can be merged
	 * 
	 * @param resource
	 * @return the state
	 */
	protected Boolean isMergeable(ResourceReference resource) {
		return !resource.getClass().isAnnotationPresent(WiQueryNotMerged.class);
	}

	/**
	 * Mark the {@link ResourceReference}
	 * 
	 * @param object
	 */
	private void markResourceReference(Object object) {
		iHeaderResponse.markRendered(object);
		// markRendered(object);
		objects.add(object);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderCSSReference(org.apache.wicket.ResourceReference)
	 */
	@Override
	public void renderCSSReference(ResourceReference reference) {
		Object object = Arrays.asList(new Object[] { "css",
				RequestCycle.get().urlFor(reference, null), null });

		if (iHeaderResponse != null && !iHeaderResponse.wasRendered(object)
				&& !wasInternalRendered(object)) {

			if (isMergeable(reference)) {
				stylesheet.add(reference);

			} else {
				stylesheetUnmergeable.add(reference);
			}

			markResourceReference(object);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderCSSReference(org.apache.wicket.ResourceReference,
	 *      java.lang.String)
	 */
	@Override
	public void renderCSSReference(ResourceReference reference, String media) {
		Object object = Arrays.asList(new Object[] { "css",
				RequestCycle.get().urlFor(reference, null), media });

		if (iHeaderResponse != null && !iHeaderResponse.wasRendered(object)
				&& !wasInternalRendered(object)) {

			if (isMergeable(reference)) {
				stylesheet.add(reference);

			} else {
				stylesheetUnmergeable.add(reference);
			}

			markResourceReference(object);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderJavascriptReference(org.apache.wicket.ResourceReference)
	 */
	@Override
	public void renderJavaScriptReference(ResourceReference reference) {
		Object object = Arrays.asList(new Object[] { "javascript",
				RequestCycle.get().urlFor(reference, null) });

		if (iHeaderResponse != null && !iHeaderResponse.wasRendered(object)
				&& !wasInternalRendered(object)) {

			if (isMergeable(reference)) {
				javascript.add(reference);

			} else {
				javascriptUnmergeable.add(reference);
			}

			markResourceReference(object);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.apache.wicket.markup.html.internal.HeaderResponse#renderJavascriptReference(org.apache.wicket.ResourceReference,
	 *      java.lang.String)
	 */
	@Override
	public void renderJavaScriptReference(ResourceReference reference, String id) {
		Object object = Arrays.asList(new Object[] { "javascript",
				RequestCycle.get().urlFor(reference, null) });

		if (iHeaderResponse != null && !iHeaderResponse.wasRendered(object)
				&& !wasInternalRendered(object)) {

			if (isMergeable(reference)) {
				javascript.add(reference);

			} else {
				javascriptUnmergeable.add(reference);
			}

			markResourceReference(object);
		}
	}

	/**
	 * Method to change the header response
	 * 
	 * @param iHeaderResponse
	 */
	public void setIHeaderResponse(IHeaderResponse iHeaderResponse) {
		this.iHeaderResponse = iHeaderResponse;

		if (iHeaderResponse != null) {
			if (WiQueryUtil.isCurrentRequestAjax()) {
				for (Object obj : objects) {
					iHeaderResponse.markRendered(obj);
				}

			} else {
				objects.clear(); // We have reloaded the page
			}
		}

		javascript.clear(); // Flush javascript resources already loaded
		stylesheet.clear(); // Flush CSS resources already loaded

		javascriptUnmergeable.clear();
		stylesheetUnmergeable.clear();
	}

	/**
	 * @see org.apache.wicket.markup.html.IHeaderResponse#wasRendered(java.lang.Object)
	 */
	private boolean wasInternalRendered(Object object) {
		return objects.contains(object);
	}
}
