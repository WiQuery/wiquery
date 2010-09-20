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
package org.odlabs.wiquery.core.commons;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Manages {@link ResourceReference} declared by components.
 * </p>
 * 
 * @author Benoit Bouchez
 * @author Lionel Armanet
 */
public class WiQueryResourceManager implements Serializable {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6492292431833226643L;

	// Properties
	/**
	 * List of javascript resources to use
	 */
	private ArrayList<ResourceReference> javascriptResources;

	/**
	 * List of css resources to use
	 */
	private ArrayList<ResourceReference> cssResources;

	/**
	 * Constructor.
	 */
	public WiQueryResourceManager() {
		super();
		this.javascriptResources = new ArrayList<ResourceReference>();
		this.cssResources = new ArrayList<ResourceReference>();
	}

	public void initialize(IHeaderResponse response) {
		// Register all javascript
		for (int i = 0; i < this.javascriptResources.size(); i++) {
			response.renderJavascriptReference(this.javascriptResources.get(i));
		}
		// Register all css resources
		for (int i = 0; i < this.cssResources.size(); i++) {
			response.renderCSSReference(this.cssResources.get(i));
		}
	}

	/**
	 * Adds the given {@link JavascriptResourceReference} as a JavaScript file
	 * to import for the underlying component.
	 */
	public void addJavaScriptResource(JavascriptResourceReference reference) {
		this.javascriptResources.add(reference);
	}

	/**
	 * Adds the given file as a JavaScript file to import for the underlying
	 * component.
	 * 
	 * @see ResourceReference
	 */
	public void addJavaScriptResource(Class<?> scope, String path) {
		this.javascriptResources.add(new JavascriptResourceReference(scope, path));
	}
	
	/**
	 * Adds the given {@link ResourceReference} as a Css file
	 * to import for the underlying component.
	 */
	public void addCssResource(ResourceReference reference) {
		this.cssResources.add(reference);
	}

	/**
	 * Adds the given file as a CSS file to import for the underlying component.
	 * 
	 * @see ResourceReference
	 */
	public void addCssResource(Class<?> scope, String path) {
		this.cssResources.add(new CompressedResourceReference(scope, path));
	}

	/**
	 * Method to clear the resources
	 */
	protected void clearResources() {
		this.javascriptResources.clear();
		this.cssResources.clear();
	}
}
