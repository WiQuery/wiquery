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
package org.odlabs.wiquery.ui.options;

import org.apache.wicket.Component;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.IOptionsRenderer;

/**
 * $Id$
 * <p>
 * Renders UI options as getters/setters statements.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public class UiOptionsRenderer implements IOptionsRenderer {

	private static final long serialVersionUID = 6564869405941048495L;

	private String statement;

	private Component component;

	public UiOptionsRenderer(String statement, Component component) {
		super();
		this.statement = statement;
		this.component = component;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.odlabs.wiquery.core.options.IOptionsRenderer#renderAfter(java.lang.StringBuilder)
	 */
	public void renderAfter(StringBuilder stringBuilder) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.odlabs.wiquery.core.options.IOptionsRenderer#renderBefore(java.lang.StringBuilder)
	 */
	public void renderBefore(StringBuilder stringBuilder) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.odlabs.wiquery.core.options.IOptionsRenderer#renderOption(java.lang.String,
	 *      java.lang.Object, boolean)
	 */
	public CharSequence renderOption(String name, Object value, boolean isLast) {
		JsStatement jsStatement = new JsQuery(this.component).$().chain(
				this.statement, "'option'", "'" + name + "'", value.toString());
		return jsStatement.render();
	}

}
