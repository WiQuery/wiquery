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
package org.odlabs.wiquery.ui.dialog.util;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.ui.dialog.DialogJavaScriptResourceReference;
import org.odlabs.wiquery.ui.dialog.util.DialogUtils.DialogUtilsLanguages;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.resizable.ResizableJavaScriptResourceReference;

/**
 * $Id: DialogResourcesBehavior.java
 * 
 * Behavior to load all needed resources for the {@link Dialog}
 * @author Julien Roche
 * @since 1.1
 */
@WiQueryUIPlugin
public class DialogResourcesBehavior extends WiQueryAbstractBehavior {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2337318416689834710L;
	
	/** Constant of wiQuery Dialog resource */
	public static final JavascriptResourceReference wiQueryDialogJs = 
		new JavascriptResourceReference(
				DialogResourcesBehavior.class, 
				"wiquery-dialog.min.js");
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		
		wiQueryResourceManager.addJavaScriptResource(DialogJavaScriptResourceReference.get());
		wiQueryResourceManager
				.addJavaScriptResource(DraggableJavaScriptResourceReference.get());
		wiQueryResourceManager
				.addJavaScriptResource(ResizableJavaScriptResourceReference.get());
		
		wiQueryResourceManager.addJavaScriptResource(wiQueryDialogJs);
		wiQueryResourceManager.addJavaScriptResource(
				DialogUtilsLanguages.getDialogUtilsResource(
						DialogUtilsLanguages.getDialogUtilsLanguages(getComponent().getLocale())));
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsStatement();
	}
}
