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

/**
 * $Id: ResizableAspectRatio.java
 * <p>
 * Bean for the aspectRatio option for the Resizable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class ResizableAspectRatio implements IComplexOption {	
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private Boolean booleanParam;
	private Float floatParam;
	
	/**Constructor
	 * @param booleanParam Boolean parameter
	 */
	public ResizableAspectRatio(Boolean booleanParam) {
		this(booleanParam, null);
	}

	/**Constructor
	 * @param floatParam Float parameter
	 */
	public ResizableAspectRatio(Float floatParam) {
		this(null, floatParam);
	}
	
	/**Constructor
	 * @param booleanParam Boolean parameter
	 * @param floatParam Float parameter
	 */
	private ResizableAspectRatio(Boolean booleanParam, Float floatParam) {
		super();
		setParam(booleanParam, floatParam);
	}
	
	/**
	 * @return the booleanParam
	 */
	public Boolean getBooleanParam() {
		return booleanParam;
	}

	/**
	 * @return the floatParam
	 */
	public Float getFloatParam() {
		return floatParam;
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptItemOptions()
	 */
	public CharSequence getJavascriptOption() {
		if(booleanParam == null && floatParam == null){
			throw new IllegalArgumentException("The ResizableAspectRatio must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(booleanParam != null){
			sequence = booleanParam.toString();
		}
		else if(floatParam != null){
			sequence = floatParam.toString();
		}
		else{
			throw new IllegalArgumentException("The ResizableAspectRatio must have one not null parameter");
		}
		
		return sequence;
	}

	/**Set's the boolean parameter
	 * @param booleanParam the boolean to set
	 */
	public void setBooleanParam(Boolean booleanParam) {
		setParam(booleanParam, null);
	}
	
	/**Set's the float parameter
	 * @param floatParam the float to set
	 */
	public void setFloatParam(Float floatParam) {
		setParam(null, floatParam);
	}
	
	/**Method setting the right parameter
	 * @param booleanParam Boolean parameter
	 * @param floatParam Float parameter
	 */
	private void setParam(Boolean booleanParam, Float floatParam) {
		this.booleanParam = booleanParam;
		this.floatParam = floatParam;
	}
}
