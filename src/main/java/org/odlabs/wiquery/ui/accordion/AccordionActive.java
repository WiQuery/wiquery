package org.odlabs.wiquery.ui.accordion;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

public class AccordionActive implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 3404088696595137949L;
	
	// Properties
	private Boolean booleanParam;
	private Integer intParam;
	private LiteralOption selectorOrElementParam;
	private String jQueryParam;
	
	public AccordionActive(Boolean booleanParam) {
		this(booleanParam, null, null, null);
	}
	
	public AccordionActive(Integer intParam) {
		this(null, intParam, null, null);
	}

	public AccordionActive(LiteralOption selectorOrElementParam) {
		this(null, null, selectorOrElementParam, null);
	}
	
	public AccordionActive(String jQueryParam) {
		this(null, null, null, jQueryParam);
	}
	
	public AccordionActive(Boolean booleanParam, Integer intParam,
			LiteralOption selectorOrElementParam, String jQueryParam) {
		super();
		setParam(booleanParam, intParam, selectorOrElementParam, jQueryParam);
	}
	
	/**
	 * @return the booleanParam
	 */
	public boolean isBooleanParam() {
		return booleanParam;
	}

	/**
	 * @return the intParam
	 */
	public int getIntParam() {
		return intParam;
	}

	/**
	 * @return the selectorOrElementParam
	 */
	public LiteralOption getSelectorOrElementParam() {
		return selectorOrElementParam;
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptItemOptions()
	 */
	public CharSequence getJavascriptOption() {
		if(booleanParam == null && intParam == null && jQueryParam == null &&
				selectorOrElementParam == null){
			throw new IllegalArgumentException("The AccordionActive must have one not null parameter");
		}
		
		CharSequence sequence = null;
		
		if(booleanParam != null){
			sequence = booleanParam.toString();
		}
		else if(intParam != null){
			sequence = intParam.toString();
		}
		else if(jQueryParam != null){
			sequence = jQueryParam;
		}
		else if(selectorOrElementParam != null){
			sequence = selectorOrElementParam.toString();
		}
		else{
			throw new IllegalArgumentException("The AccordionActive must have one not null parameter");
		}
		
		return sequence;
	}

	/**
	 * @return the jQueryParam
	 */
	public String getJQueryParam() {
		return jQueryParam;
	}

	/**Set's the boolean parameter
	 * @param booleanParam the booleanParam to set
	 */
	public void setBooleanParam(boolean booleanParam) {
		setParam(booleanParam, null, null, null);
	}

	/**Set's the int parameter
	 * @param intParam the intParam to set
	 */
	public void setIntParam(int intParam) {
		setParam(null, intParam, null, null);
	}
	
	/**Set's the jQuery parameter
	 * @param queryParam the jQueryParam to set
	 */
	public void setJQueryParam(String queryParam) {
		setParam(null, null, null, queryParam);
	}
	
	/**Method setting the right parameter
	 * @param booleanParam Boolean parameter
	 * @param intParam Integer parameter
	 * @param selectorOrElementParam Selector or element parameter
	 * @param jQueryParam JQuery parameter
	 */
	private void setParam(Boolean booleanParam, Integer intParam,
			LiteralOption selectorOrElementParam, String jQueryParam) {
		this.booleanParam = booleanParam;
		this.intParam = intParam;
		this.selectorOrElementParam = selectorOrElementParam;
		this.jQueryParam = jQueryParam;
	}

	/**Set's the selector or element parameter
	 * @param selectorOrElementParam the selectorOrElementParam to set
	 */
	public void setSelectorOrElementParam(LiteralOption selectorOrElementParam) {
		setParam(null, null, selectorOrElementParam, null);
	}
}
