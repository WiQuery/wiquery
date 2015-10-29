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
package org.wicketstuff.wiquery.core.effects;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Before;
import org.junit.Test;
import org.wicketstuff.wiquery.core.commons.DivTestPanel;
import org.wicketstuff.wiquery.core.effects.EffectBehavior;
import org.wicketstuff.wiquery.core.effects.basic.Hide;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Test on {@link EffectBehavior}
 * 
 * @author Julien Roche
 */
public class EffectBehaviorTestCase extends WiQueryTestCase
{
	// Properties
	private Component component;

	private EffectBehavior effectBehavior;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		effectBehavior = new EffectBehavior(new Hide());

		Panel panel = new DivTestPanel("panelId");
		component = new WebMarkupContainer("anId");
		component.setMarkupId(component.getId());
		component.setOutputMarkupId(true);
		component.add(effectBehavior);
		panel.add(component);

		tester.startComponentInPage(panel);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.effects.EffectBehavior#statement()}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testStatement()
	{
		assertEquals(effectBehavior.statement().render().toString(), "$('#anId').hide();");
	}
}
