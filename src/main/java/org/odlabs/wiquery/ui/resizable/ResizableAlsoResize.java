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
package org.odlabs.wiquery.ui.resizable;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: ResizableAlsoResize.java
 * <p>
 * Bean for the 'also resize' option for the Resizable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class ResizableAlsoResize implements IComplexOption {	
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private LiteralOption literalParam;
	private String objectParam;
	
	/**Constructor
	 * @param literalParam Literal parameter
	 */
	public ResizableAlsoResize(LiteralOption literalParam) {
		this(literalParam, null);
	}

	/**Constructor
	 * @param objectParam object parameter (jQuery or DOMElement)
	 */
	public ResizableAlsoResize(String objectParam) {
		this(null, objectParam);
	}
	
	/**Constructor
	 * @param literalParam Literal parameter
	 * @param objectParam object parameter
	 */
	private ResizableAlsoResize(LiteralOption literalParam, String objectParam) {
		super();
		setParam(literalParam, objectParam);
	}
	
	/**
	 * @return the literalParam
	 */
	public LiteralOption getLiteralParam() {
		return literalParam;
	}

	/**
	 * @return the objectParam
	 */
	public String getObjectParam() {
		return objectParam;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if(objectParam == null && literalParam == null){
			throw new IllegalArgumentException("The ResizableAlsoResize must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(objectParam != null){
			sequence = objectParam;
		}
		else if(literalParam != null){
			sequence = literalParam.toString();
		}
		else{
			throw new IllegalArgumentException("The ResizableAlsoResize must have one not null parameter");
		}
		
		return sequence;
	}

	/**Set's the literal parameter
	 * @param literalParam the literal to set
	 */
	public void setLiteralParam(LiteralOption literalParam) {
		setParam(literalParam, null);
	}
	
	/**Set's the object (jQuery or DOMElement) parameter
	 * @param objectParam the literal to set
	 */
	public void setObjectParam(String objectParam) {
		setParam(null, objectParam);
	}
	
	/**Method setting the right parameter
	 * @param literalParam Literal parameter
	 * @param objectParam object parameter
	 */
	private void setParam(LiteralOption literalParam, String objectParam) {
		this.literalParam = literalParam;
		this.objectParam = objectParam;
	}
}
