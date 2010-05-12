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

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.wicket.Application;
import org.odlabs.wiquery.core.commons.listener.WiQueryPluginRenderingListener;

/**
 * $Id$
 * <p>
 * Annotates a {@link Application} to be enable / disable and specify some options
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Inherited
public @interface WiQueryOptions {
	/**
	 * Shall we automatically import the jQuery resource via {@link CoreJavaScriptResourceReference}
	 * @return the state
	 */
	boolean autoImportJQueryResource() default true;
	
	/**
	 * Shall we merged the javascript and stylesheet resources ?
	 * @return the state
	 */
	boolean enableResourcesMerging() default false;
	
	/**
	 * List of listeners to use for the {@link WiQueryCoreHeaderContributor}
	 */
	Class<? extends WiQueryPluginRenderingListener>[] listeners() default {};
}
