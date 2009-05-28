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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.options.UiOptionsRenderer;

/**
 * $Id$
 * <p>
 * TODO insert comments here
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

	public JsStatement update() {
		JsStatement wholeStatement = new JsStatement();
		wholeStatement.append(options.getJavaScriptOptions());
		return wholeStatement;
	}

	public void setValue(int value) {
		this.options.put("value", value);
	}

	public int getValue() {
		return this.options.getInt("value");
	}

}
