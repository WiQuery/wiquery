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
package org.odlabs.wiquery.ui.droppable;

import org.apache.wicket.resource.dependencies.AbstractResourceDependentResourceReference;
import org.odlabs.wiquery.core.resources.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.mouse.MouseJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * References the resource to apply draggable behavior on HTML components.
 * </p>
 * 
 * TODO RENAME
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class DroppableJavaScriptResourceReference extends WiQueryJavaScriptResourceReference
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3704373328245392715L;

	/**
	 * Singleton instance.
	 */
	private static DroppableJavaScriptResourceReference instance =
		new DroppableJavaScriptResourceReference();

	/**
	 * Builds a new instance of {@link DroppableJavaScriptResourceReference}.
	 */
	private DroppableJavaScriptResourceReference()
	{
		super(DroppableJavaScriptResourceReference.class, "jquery.ui.droppable.js");
	}

	/**
	 * Returns the {@link DroppableJavaScriptResourceReference} instance.
	 */
	public static DroppableJavaScriptResourceReference get()
	{
		return instance;
	}

	@Override
	public AbstractResourceDependentResourceReference[] getDependentResourceReferences()
	{
		AbstractResourceDependentResourceReference[] list =
			new AbstractResourceDependentResourceReference[4];
		list[0] = CoreUIJavaScriptResourceReference.get();
		list[1] = WidgetJavaScriptResourceReference.get();
		list[2] = MouseJavaScriptResourceReference.get();
		list[3] = DraggableJavaScriptResourceReference.get();

		return list;
	}
}
