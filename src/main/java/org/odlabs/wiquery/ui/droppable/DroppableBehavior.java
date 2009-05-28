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

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Sets the attached component droppable, e.g. it can accept draggable elements.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public class DroppableBehavior extends WiQueryAbstractBehavior {

	private static final long serialVersionUID = 1L;

	private Options options = new Options();

	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager
				.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager
				.addJavaScriptResource(new DroppableJavaScriptResourceLocator());
	}

	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("droppable",
				this.options.getJavaScriptOptions());
	}

	public void setAccept(String accept) {
		this.options.putLiteral("accept", accept);
	}

	public void setOnDrop(JsScope onDrop) {
		this.options.put("drop", onDrop);
	}

}
