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
package org.odlabs.wiquery.ui.dialog;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.IListItemOption;
import org.odlabs.wiquery.core.options.LiteralOption;


/**
 * $Id: DialogButton.java
 * <p>
 * Bean to represent a dialog button
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DialogButton extends Object implements IListItemOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -1683433456056445578L;
	
	// Properties
	private String title;
	private JsScope jsScope;
	
	/** Build a new instance of a dialog button
	 * @param title Title of the button
	 * @param jsScope JsScope of the button
	 */
	public DialogButton(String title, JsScope jsScope) {
		super();
		setTitle(title);
		setJsScope(jsScope);
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IListItemOption#getJavascriptOption()
	 */
	public final CharSequence getJavascriptOption() {
		return new LiteralOption(getTitle()) + ":" + getJsScope().render();
	}
	
	/**Method retrieving the JsScope of the button
	 * @return the JsScope
	 */
	public JsScope getJsScope() {
		return jsScope;
	}
	
	/**Method retrieving the title of the button
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**Method setting the JsScope of the button
	 * @param jsScope
	 */
	public void setJsScope(JsScope jsScope) {
		this.jsScope = jsScope;
	}
	
	/**Method setting the title of the button
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
