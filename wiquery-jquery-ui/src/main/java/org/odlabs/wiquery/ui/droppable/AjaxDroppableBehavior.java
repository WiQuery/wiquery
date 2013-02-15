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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Convenience DroppableBehavior will AJAX functionality already plugged in. 
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70gmail.com)
 */
public abstract class AjaxDroppableBehavior extends DroppableBehavior {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AjaxDroppableBehavior() {
		setDropEvent(new AjaxDropCallback() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void drop(AjaxRequestTarget target, Component source,
					Component dropped) {
				AjaxDroppableBehavior.this.drop(target, source, dropped);
			}
		});
	}

	/**
	 * Called when component is dropped.
	 * @param target
	 * @param source
	 * @param dropped
	 */
	protected abstract void drop(AjaxRequestTarget target, Component source,
			Component dropped);
}
