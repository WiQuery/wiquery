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
package org.wicketstuff.wiquery.ui.selectable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Before;
import org.junit.Test;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.DivTestPanel;
import org.wicketstuff.wiquery.ui.selectable.SelectableBehavior.ToleranceEnum;

/**
 * Test on {@link SelectableBehavior}
 * 
 * @author Julien Roche
 */
public class SelectableBehaviorTestCase extends WiQueryTestCase
{
	// Properties
	private SelectableBehavior selectableBehavior;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		selectableBehavior = new SelectableBehavior();
		Panel panel = new DivTestPanel("panelId");
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(selectableBehavior);
		panel.add(component);
		tester.startComponentInPage(panel);
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(selectableBehavior.destroy());
		assertEquals(selectableBehavior.destroy().render().toString(),
			"$('#anId').selectable('destroy');");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#disable()}.
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(selectableBehavior.disable());
		assertEquals(selectableBehavior.disable().render().toString(),
			"$('#anId').selectable('disable');");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(selectableBehavior.enable());
		assertEquals(selectableBehavior.enable().render().toString(),
			"$('#anId').selectable('enable');");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel()
	{
		assertEquals(selectableBehavior.getCancel(), "input,option");
		selectableBehavior.setCancel("input");
		assertEquals(selectableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay()
	{
		assertEquals(selectableBehavior.getDelay(), 0);
		selectableBehavior.setDelay(5);
		assertEquals(selectableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#getDistance()} .
	 */
	@Test
	public void testGetDistance()
	{
		assertEquals(selectableBehavior.getDistance(), 0);
		selectableBehavior.setDistance(5);
		assertEquals(selectableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#getFilter()}.
	 */
	@Test
	public void testGetFilter()
	{
		assertEquals(selectableBehavior.getFilter(), "*");
		selectableBehavior.setFilter("input");
		assertEquals(selectableBehavior.getFilter(), "input");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(selectableBehavior.getOptions());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#getTolerance()} .
	 */
	@Test
	public void testGetTolerance()
	{
		assertEquals(selectableBehavior.getTolerance(), ToleranceEnum.TOUCH);
		selectableBehavior.setTolerance(ToleranceEnum.FIT);
		assertEquals(selectableBehavior.getTolerance(), ToleranceEnum.FIT);
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#isAutoRefresh()} .
	 */
	@Test
	public void testIsAutoRefresh()
	{
		assertTrue(selectableBehavior.isAutoRefresh());
		selectableBehavior.setAutoRefresh(false);
		assertFalse(selectableBehavior.isAutoRefresh());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(selectableBehavior.isDisabled());
		selectableBehavior.setDisabled(true);
		assertTrue(selectableBehavior.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#refresh()}.
	 */
	@Test
	public void testRefresh()
	{
		assertNotNull(selectableBehavior.refresh());
		assertEquals(selectableBehavior.refresh().render().toString(),
			"$('#anId').selectable('refresh');");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.selectable.SelectableBehavior#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(selectableBehavior.widget());
		assertEquals(selectableBehavior.widget().render().toString(),
			"$('#anId').selectable('widget');");
	}
}
