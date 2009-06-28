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

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: DroppableAccept.java
 * <p>
 * Bean for the accept option for the Droppable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DroppableAccept implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private JsScope functionParam;
	private LiteralOption literalParam;
	
	/**Constructor
	 * @param literalParam Literal parameter
	 */
	public DroppableAccept(LiteralOption literalParam) {
		this(null, literalParam);
	}
	
	/**Constructor
	 * @param functionParam Function parameter
	 */
	public DroppableAccept(JsScope functionParam) {
		this(functionParam, null);
	}

	/**Constructor
	 * @param functionParam Function parameter
	 * @param literalParam Literal parameter
	 */
	private DroppableAccept(JsScope functionParam, LiteralOption literalParam) {
		super();
		setParam(functionParam, literalParam);
	}
	
	/**
	 * @return the literalParam
	 */
	public LiteralOption getLiteralParam() {
		return literalParam;
	}

	/**
	 * @return the functionParam
	 */
	public JsScope getFunctionParam() {
		return functionParam;
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptItemOptions()
	 */
	public CharSequence getJavascriptOption() {
		if(literalParam == null && functionParam == null){
			throw new IllegalArgumentException("The DroppableAccept must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(literalParam != null){
			sequence = literalParam.toString();
		}
		else if(functionParam != null){
			sequence = functionParam.render();
		}
		else{
			throw new IllegalArgumentException("The DroppableAccept must have one not null parameter");
		}
		
		return sequence;
	}

	/**Set's the literal parameter
	 * @param literalParam the literal to set
	 */
	public void setHelperEnumParam(LiteralOption literalParam) {
		setParam(null, literalParam);
	}
	
	/**Set's the function parameter
	 * @param functionParam the JsScope to set
	 */
	public void setFunctionParam(JsScope functionParam) {
		setParam(functionParam, null);
	}
	
	/**Method setting the right parameter
	 * @param functionParam Function parameter
	 * @param literalParam Literal parameter
	 */
	private void setParam(JsScope functionParam, LiteralOption literalParam) {
		this.functionParam = functionParam;
		this.literalParam = literalParam;
	}
}
