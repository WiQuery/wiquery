/*
 * Copyright (c) 2008 Benoit Bouchez, Nicolas Giard
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
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * TODO COMMENT
 * @author Benoit Bouchez, Lionel Armanet
 */
public class WiQueryResourceManager implements Serializable
{
	
	private static final long serialVersionUID = 6492292431833226643L;

	/** 
	 * List of javascript resources to use 
	 */
	private ArrayList<ResourceReference> javascriptResources;
	
	/** 
	 * List of css resources to use 
	 */
	private ArrayList<ResourceReference> cssResources;
	
	/** 
	 * List of plugin dependencies 
	 */
	// TODO DEPENDENCIES MANAGEMENT ?
	private ArrayList<WiQueryResourceManager> dependencies;

	public WiQueryResourceManager()
	{
		super();
		this.javascriptResources = new ArrayList<ResourceReference>();
		this.cssResources = new ArrayList<ResourceReference>();
		this.dependencies = new ArrayList<WiQueryResourceManager>();
	}

	public void initialize(IHeaderResponse response)
	{
		// Register dependencies first
		// TODO DEPENDECY MANAGEMENT
		//		for (int i = 0; i < this.dependencies.size(); i++)
//		{
//			dependencies.get(i).initialize(response);
//		}
		// Register all javascript
		for (int i = 0; i < this.javascriptResources.size(); i++)
		{
			response.renderJavascriptReference(this.javascriptResources.get(i));
		}
		// Register all css resources
		for (int i = 0; i < this.cssResources.size(); i++)
		{
			response.renderCSSReference(this.cssResources.get(i));
		}
	}

	public void addJavaScriptResource(JavascriptResourceReference reference)
	{
		this.javascriptResources.add(reference);
	}

	public void addJavaScriptResource(Class<?> scope, String path)
	{
		this.javascriptResources.add(new ResourceReference(scope, path));
	}

	public void addCssResource(Class<?> scope, String path)
	{
		this.cssResources.add(new ResourceReference(scope, path));
	}
	
}
