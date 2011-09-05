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
package org.odlabs.wiquery.core.behavior;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.odlabs.wiquery.core.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id: WiQueryAbstractAjaxBehavior.java 1143 2011-07-29 11:51:49Z hielke.hoeve@gmail.com
 * $
 * <p>
 * Link between {@link IWiQueryPlugin} and {@link AbstractDefaultAjaxBehavior}
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public abstract class WiQueryAbstractAjaxBehavior extends AbstractDefaultAjaxBehavior implements
		IWiQueryPlugin
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6498661892490365888L;

	@Override
	protected void onBind()
	{
		super.onBind();
	}

	public abstract JsStatement statement();
}