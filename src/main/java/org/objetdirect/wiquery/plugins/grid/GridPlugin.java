/*
 * Copyright (c) 2008 Objet Direct
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
package org.objetdirect.wiquery.plugins.grid;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.objetdirect.wiquery.core.commons.WickextPlugin;
import org.objetdirect.wiquery.core.javascript.JsQuery;
import org.objetdirect.wiquery.core.javascript.JsStatement;

/**
 * $Id$
 * <p>
 * Builds a data grid from this container's markup.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public class GridPlugin extends WebMarkupContainer implements WickextPlugin {

	private static final long serialVersionUID = 8764581335554628538L;

	/**
	 * Builds a new instance of {@link GridPlugin} for the given wicket id.
	 */
	public GridPlugin(String id) {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("flexigrid");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.WickextPlugin#getHeaderContribution()
	 */
	public IHeaderContributor getHeaderContribution() {
		return new IHeaderContributor() {

			private static final long serialVersionUID = 1L;

			public void renderHead(IHeaderResponse response) {
				response
						.renderJavascriptReference(new JavascriptResourceReference(
								GridPlugin.class, "flexigrid/flexigrid.js"));
				response.renderCSSReference(new ResourceReference(
						GridPlugin.class,
						"flexigrid/css/flexigrid/flexigrid.css"));
			}

		};
	}

}
