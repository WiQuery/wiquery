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

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link ProgressBar}
 * @author Julien Roche
 *
 */
public class ProgressBarTestCase extends TestCase {
	// Properties
	private ProgressBar progressBar;

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WiQueryWebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
		
		progressBar = new ProgressBar("anId");
		progressBar.setMarkupId(progressBar.getId());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#decrement()}.
	 */
	@Test
	public void testDecrement() {
		Assert.assertNotNull(progressBar.decrement());
		Assert.assertEquals(progressBar.decrement().render().toString(), 
				"$('#anId').progressbar('value', $('#anId').progressbar('value') - 1);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#decrement(int)}.
	 */
	@Test
	public void testDecrementInt() {
		Assert.assertNotNull(progressBar.decrement(5));
		Assert.assertEquals(progressBar.decrement(5).render().toString(), 
				"$('#anId').progressbar('value', $('#anId').progressbar('value') - 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(progressBar.destroy());
		Assert.assertEquals(progressBar.destroy().render().toString(), 
				"$('#anId').progressbar('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(progressBar.disable());
		Assert.assertEquals(progressBar.disable().render().toString(), 
				"$('#anId').progressbar('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(progressBar.enable());
		Assert.assertEquals(progressBar.enable().render().toString(), 
				"$('#anId').progressbar('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(progressBar.getOptions());
		Assert.assertEquals(progressBar.getOptions().getJavaScriptOptions().toString(), "");
		progressBar.setValue(5);
		Assert.assertEquals(progressBar.getOptions().getJavaScriptOptions().toString(), "$('#anId').progressbar('option', 'value', 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#getValue()}.
	 */
	@Test
	public void testGetValue() {
		Assert.assertEquals(progressBar.getValue(), 0);
		progressBar.setValue(5);
		Assert.assertEquals(progressBar.getValue(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#increment()}.
	 */
	@Test
	public void testIncrement() {
		Assert.assertNotNull(progressBar.increment());
		Assert.assertEquals(progressBar.increment().render().toString(), 
				"$('#anId').progressbar('value', $('#anId').progressbar('value') + 1);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#increment(int)}.
	 */
	@Test
	public void testIncrementInt() {
		Assert.assertNotNull(progressBar.increment(5));
		Assert.assertEquals(progressBar.increment(5).render().toString(), 
				"$('#anId').progressbar('value', $('#anId').progressbar('value') + 5);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetChangeEvent() {
		Assert.assertEquals(progressBar.statement().render().toString(), 
			"$('#anId').progressbar();");
		progressBar.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(progressBar.statement().render().toString(), 
			"$('#anId').progressbar();$('#anId').progressbar('option', 'change', function(event, ui) {\n\talert('event');\n});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(progressBar.statement());
		Assert.assertEquals(progressBar.statement().render().toString(), "$('#anId').progressbar();");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#update()}.
	 */
	@Test
	public void testUpdate() {
		Assert.assertNotNull(progressBar.update());
		Assert.assertEquals(progressBar.update().render().toString(), "");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#value()}.
	 */
	@Test
	public void testValue() {
		Assert.assertNotNull(progressBar.value());
		Assert.assertEquals(progressBar.value().render().toString(), 
				"$('#anId').progressbar('value');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#value(int)}.
	 */
	@Test
	public void testValueInt() {
		Assert.assertNotNull(progressBar.value(5));
		Assert.assertEquals(progressBar.value(5).render().toString(), 
				"$('#anId').progressbar('value', 5);");
	}
}
