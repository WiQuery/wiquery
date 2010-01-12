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
package org.odlabs.wiquery.ui.slider;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: SliderAnimate.java
 * <p>
 * Bean for the animate option into the Slider component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class SliderAnimate implements IComplexOption {
	public enum AnimateEnum {
		FAST	(new LiteralOption("fast")),
		NORMAL 	(new LiteralOption("normal")),
		SLOW 	(new LiteralOption("slow"));
		
		// Properties
		private LiteralOption literalParam;
		
		AnimateEnum(LiteralOption literalParam){
			this.literalParam = literalParam;
		}

		/**
		 * {@inheritDoc}
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
	private Boolean booleanParam;
	private AnimateEnum animateEnumParam;
	private Number numberParam;
	
	/**Constructor
	 * @param animateEnumParam AnimateEnum parameter
	 */
	public SliderAnimate(AnimateEnum animateEnumParam) {
		this(null, animateEnumParam, null);
	}

	/**Constructor
	 * @param booleanParam Boolean parameter
	 */
	public SliderAnimate(Boolean booleanParam) {
		this(booleanParam, null, null);
	}
	
	/**Constructor
	 * @param numberParam Number param
	 */
	public SliderAnimate(Number numberParam) {
		this(null, null, numberParam);
	}
	
	/**Constructor
	 * @param booleanParam Boolean parameter
	 * @param animateEnumParam AnimateEnum parameter
	 * @param numberParam Number param
	 */
	private SliderAnimate(Boolean booleanParam, AnimateEnum animateEnumParam, Number numberParam) {
		super();
		setParam(booleanParam, animateEnumParam, numberParam);
	}
	
	/**
	 * @return the animateEnumParam
	 */
	public AnimateEnum getAnimateEnumParam() {
		return animateEnumParam;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if(booleanParam == null && animateEnumParam == null && numberParam == null){
			throw new IllegalArgumentException("The SliderAnimate must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(booleanParam != null){
			sequence = booleanParam.toString();
			
		} else if(numberParam != null){
			sequence = numberParam.toString();
			
		} else if(animateEnumParam != null){
			sequence = animateEnumParam.toString();
		}
		else{
			throw new IllegalArgumentException("The SliderAnimate must have one not null parameter");
		}
		
		return sequence;
	}
	
	/**
	 * @return
	 */
	public Number getNumberParam() {
		return numberParam;
	}

	/**
	 * @return the booleanParam
	 */
	public boolean isBooleanParam() {
		return booleanParam;
	}
	
	/**Set's the boolean parameter
	 * @param booleanParam the booleanParam to set
	 */
	public void setBooleanParam(boolean booleanParam) {
		setParam(booleanParam, null, null);
	}
	
	/**
	 * @param numberParam
	 */
	public void setNumberParam(Number numberParam) {
		setParam(null, null, numberParam);
	}

	/**Method setting the right parameter
	 * @param booleanParam Boolean parameter
	 * @param animateEnumParam AnimateEnum parameter
	 * @param numberParam Number param
	 */
	private void setParam(Boolean booleanParam, AnimateEnum animateEnumParam, Number numberParam) {
		this.booleanParam = booleanParam;
		this.animateEnumParam = animateEnumParam;
		this.numberParam = numberParam;
	}

	/**Set's the AnimateEnum parameter
	 * @param animateEnumParam the AnimateEnum to set
	 */
	public void setAnimateEnumParam(AnimateEnum animateEnumParam) {
		setParam(null, animateEnumParam, null);
	}
}
