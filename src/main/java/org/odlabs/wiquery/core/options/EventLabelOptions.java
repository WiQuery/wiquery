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
package org.odlabs.wiquery.core.options;

import org.odlabs.wiquery.core.events.EventLabel;

/**
 * Representation of an EventLabel into an ItemOptions
 * @author Julien Roche
 *
 */
public class EventLabelOptions extends Object implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -1683433456056445577L;
	
	// Properties
	private EventLabel eventLabel;
	
	/**Default constructor
	 * @param eventLabel
	 */
	public EventLabelOptions(EventLabel eventLabel) {
		super();
		this.eventLabel = eventLabel;
	}
	
	/**Method retrieving the eventLabel value
	 * @return the eventLabel
	 */
	public EventLabel getEventLabel() {
		return eventLabel;
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.options.IListItemOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return new LiteralOption(eventLabel.getEventLabel()).toString();
	}

	/**Methode setting the eventLabel value
	 * @param eventLabel EventLabel
	 */
	public void setInteger(EventLabel eventLabel) {
		this.eventLabel = eventLabel;
	}
}
