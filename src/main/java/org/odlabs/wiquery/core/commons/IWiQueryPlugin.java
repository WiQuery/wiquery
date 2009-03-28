/*
 * Copyright (c) 2008 Objet Direct
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

import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id$
 * <p>
 * WickeXt plugins are components generating JavaScript jQuery code. WickeXt
 * hosts the component, outputs the JavaScript needed to function and imports
 * all JavaScript resources needed by the component.
 * </p>
 * <p>
 * To create a WickeXt plugin, you must implements the two methods below:
 * <ul>
 * <li> {@link #getHeaderContribution()} is used to declare the resources needed
 * for the component. </li>
 * <li> {@link #statement()} returns the {@link JsStatement} used by the
 * component to function. </li>
 * </ul>
 * </p>
 * <p>
 * A WickeXt plugin is automatically managed by the
 * {@link WiQueryInstantiationListener}
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 * @see WiQueryInstantiationListener
 * @see JsStatement
 */
public interface IWiQueryPlugin {

	void contribute(WiQueryResourceManager wiQueryResourceManager);
	
	/**
	 * Returns the main {@link JsStatement} used to create the plugin.
	 * @return
	 */
	JsStatement statement();
}
