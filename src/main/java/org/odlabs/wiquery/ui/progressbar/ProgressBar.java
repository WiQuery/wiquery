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
package org.odlabs.wiquery.ui.progressbar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.options.UiOptionsRenderer;

/**
 * $Id$
 * <p>
 * Creates a progressBar UI component from this {@link WebMarkupContainer}'s
 * HTML markup.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
@WiQueryUIPlugin
public class ProgressBar extends WebMarkupContainer implements IWiQueryPlugin {

	private static final long serialVersionUID = 8268721447610956664L;

	private Options options;

	/**
	 * Builds a new progress bar.
	 */
	public ProgressBar(String id) {
		super(id);
		this.options = new Options();
		this.options.setRenderer(new UiOptionsRenderer("progressbar", this));
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(ProgressBar.class,
				"ui.progressbar.js");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		JsStatement componentStatement = new JsQuery(this).$().chain(
				"progressbar");
		JsStatement wholeStatement = new JsStatement();
		wholeStatement.append(componentStatement.render());
		wholeStatement.append(options.getJavaScriptOptions());
		return wholeStatement;
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	public JsStatement update() {
		JsStatement wholeStatement = new JsStatement();
		wholeStatement.append(options.getJavaScriptOptions());
		return wholeStatement;
	}

	/*---- Options section ---*/
	
	/**Sets the current value of the progressBar
	 * @param value
	 */
	public void setValue(int value) {
		this.options.put("value", value);
	}

	/**
	 * @return the current value of the progressBar
	 */
	public int getValue() {
		return this.options.getInt("value");
	}

	/*---- Events section ---*/
	
	/**Set's the callback when the value of the progressBar changes.
	 * @param change
	 */
	public void setChangeEvent(JsScopeUiEvent change) {
		this.options.put("change", change);
	}
	
	/*---- Methods section ---*/
	
	/**Method to destroy the progressBar
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("progressbar", "'destroy'");
	}

	/**Method to destroy the progressBar within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable the progressBar
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(this).$().chain("progressbar", "'disable'");
	}

	/**Method to disable the progressBar within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable the progressBar
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(this).$().chain("progressbar", "'enable'");
	}

	/**Method to enable the progressBar within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method to set the current value of the progressBar
	 * @param value
	 * @return the associated JsStatement
	 */
	public JsStatement value(int value) {
		return new JsQuery(this).$().chain("progressbar", "'value'", Integer.toString(value));
	}

	/**Method to set the current value of the progressBar within the ajax request
	 * @param ajaxRequestTarget
	 * @param value
	 */
	public void value(AjaxRequestTarget ajaxRequestTarget, int value) {
		ajaxRequestTarget.appendJavascript(this.value(value).render().toString());
	}
}
