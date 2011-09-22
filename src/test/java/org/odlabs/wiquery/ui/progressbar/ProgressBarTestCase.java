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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * Test on {@link ProgressBar}
 * 
 * @author Julien Roche
 */
public class ProgressBarTestCase extends WiQueryTestCase {
	// Properties
	private ProgressBar progressBar;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		progressBar = new ProgressBar("anId");
		progressBar.setMarkupId(progressBar.getId());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#decrement()}.
	 */
	@Test
	public void testDecrement() {
		assertNotNull(progressBar.decrement());
		assertEquals(progressBar.decrement().render().toString(),
				"$('#anId').progressbar('value', $('#anId').progressbar('value') - 1);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#decrement(int)}.
	 */
	@Test
	public void testDecrementInt() {
		assertNotNull(progressBar.decrement(5));
		assertEquals(progressBar.decrement(5).render().toString(),
				"$('#anId').progressbar('value', $('#anId').progressbar('value') - 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#destroy()}.
	 */
	@Test
	public void testDestroy() {
		assertNotNull(progressBar.destroy());
		assertEquals(progressBar.destroy().render().toString(),
				"$('#anId').progressbar('destroy');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#disable()}.
	 */
	@Test
	public void testDisable() {
		assertNotNull(progressBar.disable());
		assertEquals(progressBar.disable().render().toString(),
				"$('#anId').progressbar('disable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#enable()}.
	 */
	@Test
	public void testEnable() {
		assertNotNull(progressBar.enable());
		assertEquals(progressBar.enable().render().toString(),
				"$('#anId').progressbar('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		assertNotNull(progressBar.getOptions());
		assertEquals(
				progressBar.getOptions().getJavaScriptOptions().toString(), "");
		progressBar.setValue(5);
		assertEquals(
				progressBar.getOptions().getJavaScriptOptions().toString(),
				"$('#anId').progressbar('option', 'value', 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#getValue()}.
	 */
	@Test
	public void testGetValue() {
		assertEquals(progressBar.getValue(), 0);
		progressBar.setValue(5);
		assertEquals(progressBar.getValue(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#increment()}.
	 */
	@Test
	public void testIncrement() {
		assertNotNull(progressBar.increment());
		assertEquals(progressBar.increment().render().toString(),
				"$('#anId').progressbar('value', $('#anId').progressbar('value') + 1);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#increment(int)}.
	 */
	@Test
	public void testIncrementInt() {
		assertNotNull(progressBar.increment(5));
		assertEquals(progressBar.increment(5).render().toString(),
				"$('#anId').progressbar('value', $('#anId').progressbar('value') + 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		assertFalse(progressBar.isDisabled());
		progressBar.setDisabled(true);
		assertTrue(progressBar.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeEvent() {
		assertEquals(progressBar.statement().render().toString(),
				"$('#anId').progressbar();");
		progressBar
				.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(
				progressBar.statement().render().toString(),
				"$('#anId').progressbar();$('#anId').progressbar('option', 'change', function(event, ui) {\n\talert('event');\n});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#statement()}.
	 */
	@Test
	public void testStatement() {
		assertNotNull(progressBar.statement());
		assertEquals(progressBar.statement().render().toString(),
				"$('#anId').progressbar();");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#update()}.
	 */
	@Test
	public void testUpdate() {
		assertNotNull(progressBar.update());
		assertEquals(progressBar.update().render().toString(), "");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#value()}.
	 */
	@Test
	public void testValue() {
		assertNotNull(progressBar.value());
		assertEquals(progressBar.value().render().toString(),
				"$('#anId').progressbar('value');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#value(int)}.
	 */
	@Test
	public void testValueInt() {
		assertNotNull(progressBar.value(5));
		assertEquals(progressBar.value(5).render().toString(),
				"$('#anId').progressbar('value', 5);");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.progressbar.ProgressBar#widget()}.
	 */
	@Test
	public void testWidget() {
		assertNotNull(progressBar.widget());
		assertEquals(progressBar.widget().render().toString(),
				"$('#anId').progressbar('widget');");
	}
}
