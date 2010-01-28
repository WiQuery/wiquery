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
package org.odlabs.wiquery.ui.dialog.util;

import java.io.Serializable;

import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id: WaitDialogStatements.java
 * 
 * Bean with the statement to open the waiting dialog and to close it
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class WaitDialogStatements implements Serializable {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 5953108291737593348L;

	// Properties
	private JsStatement close;
	private JsStatement open;
	
	/**
	 * Default constructor
	 */
	public WaitDialogStatements() {
		super();
		close = new JsStatement();
		open = new JsStatement();
	}
	
	/**
	 * Constructor
	 * @param open Open statement
	 * @param close Close statement
	 */
	public WaitDialogStatements(JsStatement open, JsStatement close) {
		this();
		this.open = open;
		this.close = close;
	}
	
	/**
	 * @return the close statement
	 */
	public JsStatement getClose() {
		return close;
	}
	
	/** the open statement
	 * @return
	 */
	public JsStatement getOpen() {
		return open;
	}
	
	/**
	 * Set the close statement
	 * @param close
	 */
	public void setClose(JsStatement close) {
		this.close = close;
	}
	
	/**
	 * Set the open statement
	 * @param open
	 */
	public void setOpen(JsStatement open) {
		this.open = open;
	}
}
