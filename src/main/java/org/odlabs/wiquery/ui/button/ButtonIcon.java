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
package org.odlabs.wiquery.ui.button;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;

/**
 * $Id$
 * <p>
 * Bean for the icon option into the Button component
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
public class ButtonIcon  extends Object implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 4723376695783554832L;
	
	// Properties
	private String primary;
	private String secondary;
	
	/**Default constructor
	 * @param primary (can be null)
	 * @param secondary (can be null)
	 */
	public ButtonIcon(String primary, String secondary) {
		super();
		this.primary = primary;
		this.secondary = secondary;
	}

	/**Method retrieving the class for the primary icon
	 * @return the class for the primary icon
	 */
	public String getPrimary() {
		return primary;
	}

	/**Method retrieving the class for the secondary icon
	 * @return the class for the secondary icon
	 */
	public String getSecondary() {
		return secondary;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		
		if(primary != null && secondary != null){
			buffer.append("primary: " + new LiteralOption(primary));
			buffer.append(", secondary: " + new LiteralOption(secondary));
			
		} else if(primary != null){
			buffer.append("primary: " + new LiteralOption(primary));
			
		} else if(primary != null){
			buffer.append("secondary: " + new LiteralOption(secondary));
		}
		
		buffer.append("}");
		
		return buffer;
	}
	
	/**Method setting the class for the primary icon
	 * @param primary
	 */
	public void setHeaderClass(String primary) {
		this.primary = primary;
	}

	/**Method setting the class for the secondary icon
	 * @param secondary
	 */
	public void setHeaderSelectedClass(String secondary) {
		this.secondary = secondary;
	}
}
