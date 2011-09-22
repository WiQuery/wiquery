/*
 * Copyright (c) 2010 WiQuery team
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

import java.io.Serializable;

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;

/**
 * $Id: IWiQueryInitializer.java 563 2010-11-21 20:50:37Z roche.jul@gmail.com $
 * 
 * <p>
 * The goal of the {@link IWiQueryInitializer} is to put some configurations for your
 * wiquery API at the startupt of the wicket application
 * </p>
 * 
 * <p>
 * Initializers can be configured by having a wiquery.properties file in the class path
 * root, with property 'initializer=${initializer class name}'. You can have one such
 * properties per jar file, but the initializer that property denotes can delegate to
 * other initializers of that library.
 * </p>
 * 
 * <p>
 * This mecanism is the same that the {@link IInitializer}
 * </p>
 * 
 * @author Julien Roche
 * @since 1.2
 */
public interface IWiQueryInitializer extends Serializable
{
	/**
	 * @param application
	 *            The application loading the component
	 * 
	 * @param wiQuerySettings
	 *            Settings for wiQuery into the current application
	 */
	void init(Application application, WiQuerySettings wiQuerySettings);
}
