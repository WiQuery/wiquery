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

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * Creates an ajax button UI component from this {@link WebMarkupContainer}'s
 * HTML markup.
 * </p>
 * @author Julien Roche
 * @since 1.1
 */
public abstract class ButtonAjax extends Button {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -8600606996339679404L;
	
	// Properties
	private AbstractDefaultAjaxBehavior ajaxBehavior;

	/**
	 * Constructor
	 * @param id Wicket identifiant
	 */
	public ButtonAjax(String id) {
		super(id);
		initialization();
	}
	
	/**
	 * Constructor
	 * @param id Wicket identifiant
	 * @param label Button label
	 */
	public ButtonAjax(String id, String label) {
		super(id, label);
		initialization();
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.button.Button#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
	}

	/**
	 * Initialization
	 */
	protected void initialization() {
		ajaxBehavior = new AbstractDefaultAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			@Override
			protected void respond(AjaxRequestTarget target) {
				onClick(target);
			}
		};
		add(ajaxBehavior);
	}
	
	/**
	 * A click was done on the button and send an ajax request
	 * @param target
	 */
	public abstract void onClick(AjaxRequestTarget target);

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.button.Button#setClickEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
	 */
	@Override
	public Button setClickEvent(JsScopeUiEvent click) {
		throw new UnsupportedOperationException(
			"You can't call this method into the ButtonAjax");
	}
	
	/**
	 * For framework use only
	 */
	private Button setInnerClickEvent(JsScopeUiEvent click) {
		return super.setClickEvent(click);
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.button.Button#statement()
	 */
	@Override
	public JsStatement statement() {
		setInnerClickEvent(JsScopeUiEvent.quickScope("wicketAjaxGet(" 
				+ JsUtils.quotes(ajaxBehavior.getCallbackUrl(true))
				+ ", null, null, function() {return true;});"));
		
		return super.statement();
	}
}
