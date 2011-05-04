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

import org.odlabs.wiquery.core.options.IListItemOption;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.CursorAtEnum;


/**
 * $Id: DraggableCursorAt.java
 * <p>
 * Bean to hold an option for 'cursorAt'
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DraggableCursorAt extends Object implements IListItemOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -1683433456056445578L;
	
	// Properties
	private CursorAtEnum cursorAtEnum;
	private int gap;
	
	/** Build a new instance
	 * @param cursorAtEnum Position
	 * @param gap
	 */
	public DraggableCursorAt(CursorAtEnum cursorAtEnum, int gap) {
		super();
		setCursorAtEnum(cursorAtEnum);
		setGap(gap);
	}
	
	public CursorAtEnum getCursorAtEnum() {
		return cursorAtEnum;
	}

	public int getGap() {
		return gap;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IListItemOption#getJavascriptOption()
	 */
	public final CharSequence getJavascriptOption() {
		return getCursorAtEnum().toString() + ":" + getGap();
	}

	public void setCursorAtEnum(CursorAtEnum cursorAtEnum) {
		this.cursorAtEnum = cursorAtEnum;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}
}
