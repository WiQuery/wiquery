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
 * $Id: ResizableAnimeDuration.java
 * <p>
 * Bean for the animeDuration option for the Resizable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class ResizableAnimeDuration implements IComplexOption {
	public enum DurationEnum {
		FAST		(new LiteralOption("fast")),
		NORMAL 		(new LiteralOption("normal")),
		SLOW 		(new LiteralOption("slow"));
		
		// Properties
		private LiteralOption literalParam;
		
		DurationEnum(LiteralOption literalParam){
			this.literalParam = literalParam;
		}

		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return literalParam.toString();
		}
	}
	
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private Integer integerParam;
	private DurationEnum durationEnumParam;
	
	/**Constructor
	 * @param integerParam Integer parameter
	 */
	public ResizableAnimeDuration(Integer integerParam) {
		this(integerParam, null);
	}

	/**Constructor
	 * @param durationEnumParam DurationEnum parameter
	 */
	public ResizableAnimeDuration(DurationEnum durationEnumParam) {
		this(null, durationEnumParam);
	}
	
	/**Constructor
	 * @param integerParam Integer parameter
	 * @param durationEnumParam DurationEnum parameter
	 */
	private ResizableAnimeDuration(Integer integerParam, DurationEnum durationEnumParam) {
		super();
		setParam(integerParam, durationEnumParam);
	}

	/**
	 * @return the durationEnumParam
	 */
	public DurationEnum getDurationEnumParam() {
		return durationEnumParam;
	}
	
	/**
	 * @return the integerParam
	 */
	public Integer getIntegerParam() {
		return integerParam;
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptItemOptions()
	 */
	public CharSequence getJavascriptOption() {
		if(integerParam == null && durationEnumParam == null){
			throw new IllegalArgumentException("The ResizableAnimeDuration must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(integerParam != null){
			sequence = integerParam.toString();
		}
		else if(durationEnumParam != null){
			sequence = durationEnumParam.toString();
		}
		else{
			throw new IllegalArgumentException("The ResizableAnimeDuration must have one not null parameter");
		}
		
		return sequence;
	}
	
	/**Set's the DurationEnum parameter
	 * @param durationEnumParam the DurationEnum to set
	 */
	public void setDurationEnumParam(DurationEnum durationEnumParam) {
		setParam(null, durationEnumParam);
	}
	
	/**Set's the integer parameter
	 * @param integerParam Integer parameter
	 */
	public void setIntegerParam(Integer integerParam) {
		setParam(integerParam, null);
	}
	
	/**Method setting the right parameter
	 * @param integerParam integer parameter
	 * @param durationEnumParam DurationEnum parameter
	 */
	private void setParam(Integer integerParam, DurationEnum durationEnumParam) {
		this.integerParam = integerParam;
		this.durationEnumParam = durationEnumParam;
	}
}
