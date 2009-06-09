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
package org.odlabs.wiquery.ui.core;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;


/**
 * This class represent a JsScope event for the JQuery UI components
 * The javascript representation will be like this:
 * <p>
 * 	function(event, ui) { ... }
 * </p>
 * @author Julien Roche
 *
 */
public class JsScopeUiEvent extends JsScope {
	//Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	//Properties
	private CharSequence javascriptCode;
	
	/**Default constructor
	 * @param javascriptCode
	 */
	public JsScopeUiEvent(CharSequence javascriptCode) {
		super("event", "ui");
		this.javascriptCode = javascriptCode;
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
	 */
	@Override
	protected void execute(JsScopeContext scopeContext) {
		scopeContext.append(javascriptCode);
	}
}
