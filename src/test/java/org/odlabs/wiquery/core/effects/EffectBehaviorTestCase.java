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
package org.odlabs.wiquery.core.effects;

import junit.framework.TestCase;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.effects.basic.Hide;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Test on {@link EffectBehavior}
 * @author Julien Roche
 *
 */
public class EffectBehaviorTestCase extends TestCase {
	// Properties
	private Component component;
	private EffectBehavior effectBehavior;
	
	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
		
		effectBehavior = new EffectBehavior(new Hide());
		
		component = new WebMarkupContainer("aComponent");
		component.setMarkupId(component.getId());
		component.setOutputMarkupId(true);
		component.add(effectBehavior);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.effects.EffectBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertEquals(effectBehavior.statement().render().toString(), "$('#aComponent').hide();");
	}
}
