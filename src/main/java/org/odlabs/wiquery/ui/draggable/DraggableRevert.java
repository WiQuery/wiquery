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
package org.odlabs.wiquery.ui.draggable;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id: DraggableRevert.java
 * <p>
 * Bean for the revert option for the Draggable behavior
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DraggableRevert implements IComplexOption {
	public enum RevertEnum {
		VALID		(new LiteralOption("valid")),
		INVALID 	(new LiteralOption("invalid"));
		
		// Properties
		private LiteralOption literalParam;
		
		RevertEnum(LiteralOption literalParam){
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
	private Boolean booleanParam;
	private RevertEnum revertEnumParameter;
	
	/**Constructor
	 * @param booleanParam Boolean parameter
	 */
	public DraggableRevert(Boolean booleanParam) {
		this(booleanParam, null);
	}

	/**Constructor
	 * @param revertEnumParameter RevertEnum parameter
	 */
	public DraggableRevert(RevertEnum revertEnumParameter) {
		this(null, revertEnumParameter);
	}
	
	/**Constructor
	 * @param booleanParam Boolean parameter
	 * @param revertEnumParameter RevertEnum parameter
	 */
	private DraggableRevert(Boolean booleanParam, RevertEnum revertEnumParameter) {
		super();
		setParam(booleanParam, revertEnumParameter);
	}
	
	/**
	 * @return the booleanParam
	 */
	public Boolean getBooleanParam() {
		return booleanParam;
	}

	/**
	 * @return the stringParam
	 */
	public RevertEnum getRevertEnumParam() {
		return revertEnumParameter;
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptItemOptions()
	 */
	public CharSequence getJavascriptOption() {
		if(booleanParam == null && revertEnumParameter == null){
			throw new IllegalArgumentException("The DraggableRevert must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(booleanParam != null){
			sequence = booleanParam.toString();
		}
		else if(revertEnumParameter != null){
			sequence = revertEnumParameter.toString();
		}
		else{
			throw new IllegalArgumentException("The DraggableRevert must have one not null parameter");
		}
		
		return sequence;
	}

	/**Set's the boolean parameter
	 * @param booleanParam the boolean to set
	 */
	public void setBooleanParam(Boolean booleanParam) {
		setParam(booleanParam, null);
	}
	
	/**Set's the RevertEnum parameter
	 * @param revertEnumParameter the RevertEnum to set
	 */
	public void setRevertEnumParam(RevertEnum revertEnumParameter) {
		setParam(null, revertEnumParameter);
	}
	
	/**Method setting the right parameter
	 * @param booleanParam Boolean parameter
	 * @param revertEnumParameter RevertEnum parameter
	 */
	private void setParam(Boolean booleanParam, RevertEnum revertEnumParameter) {
		this.booleanParam = booleanParam;
		this.revertEnumParameter = revertEnumParameter;
	}
}
