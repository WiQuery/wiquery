/*
 * Copyright (c) 2010 WiQuery team
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
package org.odlabs.wiquery.tester;

import org.apache.wicket.IClusterable;
import org.apache.wicket.markup.html.form.FormComponentPanel;

public interface TestFormComponentPanelSource extends IClusterable {
	/**
	 * Defines a <code>FormComponentPanel</code> instance source for
	 * <code>WicketTester</code>.
	 * 
	 * @param panelId
	 *            <code>Component</code> id of the test
	 *            <code>FormComponentPanel</code>
	 * @return test <code>Panel</code> instance -- note that the test
	 *         <code>FormComponentPanel</code>'s <code>Component</code> id must
	 *         use the given <code>panelId</code>.
	 */
	FormComponentPanel<?> getTestPanel(final String panelId);
}
