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
package org.odlabs.wiquery.core;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id: IWiQueryPlugin.java 412 2010-09-17 21:23:25Z lionel.armanet $
 * <p>
 * The {@link IWiQueryPlugin} interface defines all WiQuery plugins, e.g. all
 * components/behaviors outputting jQuery statements to function.
 * </p>
 * <p>
 * WiQuery plugins are components or behaviors generating JavaScript jQuery code. WiQuery
 * manages the component or behavior, outputs the JavaScript needed to function and imports
 * all JavaScript resources needed by the component or behavior.
 * </p>
 * <p>
 * The contribute method is not needed since wicket 1.5 as Component and or Behaviors themselves
 * are contributers. Simply override
 * {@link Component#renderHead(IHeaderResponse)} or {@link Behavior#renderHead(Component, IHeaderResponse)).
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 * @see WiQueryInstantiationListener
 * @see JsStatement
 */
public interface IWiQueryPlugin {

	/**
	 * Returns the main {@link JsStatement} used to create the plugin.
	 * @return The {@link JsStatement} corresponding to this component.
	 */
	JsStatement statement();
	
}
