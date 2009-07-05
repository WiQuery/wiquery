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
package org.odlabs.wiquery.ui.tabs;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

/**
 * $Id$
 * <p>
 * Creats a tab panel.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.9
 */
@WiQueryUIPlugin
public class Tabs extends WebMarkupContainer implements IWiQueryPlugin {

	private static final long serialVersionUID = 1L;

	/**
	 * Options are used to customize this component.
	 */
	private Options options;

	/**
	 * Builds a new tabs container with the given wicket id.
	 */
	public Tabs(String id) {
		super(id);
		options = new Options();
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(TabsJavaScriptResourceReference.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("tabs",
				options.getJavaScriptOptions());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	/**
	 * Returns the {@link JsStatement} to add the given component in the tab
	 * panel.
	 * 
	 * @param index
	 *            the insertion index.
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(int index, String title, Component contentToAdd) {
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'",
				"'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'",
				"" + index);
	}

	/**
	 * Returns the {@link JsStatement} to add the given component at the end of
	 * the tab panel.
	 * 
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(String title, Component contentToAdd) {
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'",
				"'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'");
	}

	/**
	 * Returns the {@link JsStatement} to remove the tab at the given index.
	 * 
	 * @param index
	 *            the remove index.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement remove(int index) {
		return new JsQuery(this).$().chain("tabs", "'remove'", "" + index);
	}

	/**
	 * Sets which tab is displayed.
	 */
	public void setDefaultSelectedTabIndex(int selectedTabIndex) {
		this.options.put("selected", selectedTabIndex);
	}

	/**
	 * Returns the which tab is selected by default.
	 */
	public int getDefaultSelectedTabIndex() {
		return this.options.getInt("selected");
	}

}
