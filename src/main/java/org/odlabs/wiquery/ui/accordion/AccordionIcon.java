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
package org.odlabs.wiquery.ui.accordion;

import org.codehaus.jackson.annotate.JsonValue;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.ui.themes.UiIcon;

/**
 * $Id$
 * <p>
 * Bean for the icon option into the Accordion component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class AccordionIcon  extends Object implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 4723376695783554832L;
	
	// Properties
	private String headerClass;
	private String headerSelectedClass;
	
	private Boolean wantIcons;
	
	/**
	 * Constructor
	 * 
	 * @param header
	 * @param headerSelected
	 */
	public AccordionIcon(UiIcon header, UiIcon  headerSelected) {
		super();
		if(header != null)
			this.headerClass = header.getCssClass();
		if(headerSelected != null)
			this.headerSelectedClass = headerSelected.getCssClass();
		this.wantIcons = null;
	}
	
	/**
	 * Constructor
	 * 
	 * @param headerClass
	 * @param headerSelectedClass
	 */
	public AccordionIcon(String headerClass, String headerSelectedClass) {
		super();
		this.headerClass = headerClass;
		this.headerSelectedClass = headerSelectedClass;
		this.wantIcons = null;
	}

	/**
	 * Constructor.
	 * @param wantIcons
	 */
	public AccordionIcon(Boolean wantIcons) {
		super();
		this.wantIcons = wantIcons;
	}

	/**Method retrieving the class for the header
	 * @return the class for the header
	 */
	public String getHeaderClass() {
		return headerClass;
	}

	/**Method retrieving the class for the headerSelected
	 * @return the class for the headerSelected
	 */
	public String getHeaderSelectedClass() {
		return headerSelectedClass;
	}
	
	@JsonValue
	public CharSequence getJavascriptOption() {
		if(wantIcons != null) 
			return wantIcons.toString();
		return "{'header': " + new LiteralOption(headerClass) + ", 'headerSelected': " 
			+ new LiteralOption(headerSelectedClass) + "}";
	}
	
	/**Method setting the class for the header
	 * @param headerClass
	 */
	public void setHeaderClass(String headerClass) {
		this.headerClass = headerClass;
	}

	/**Method setting the class for the headerSelected
	 * @param headerSelectedClass
	 */
	public void setHeaderSelectedClass(String headerSelectedClass) {
		this.headerSelectedClass = headerSelectedClass;
	}

	public Boolean getWantIcons() {
		return wantIcons;
	}
}
