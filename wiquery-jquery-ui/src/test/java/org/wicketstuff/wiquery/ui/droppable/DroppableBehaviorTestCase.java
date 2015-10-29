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
package org.wicketstuff.wiquery.ui.droppable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Before;
import org.junit.Test;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.DivTestPanel;
import org.wicketstuff.wiquery.ui.droppable.DroppableBehavior.ToleranceEnum;

/**
 * Test on {@link DroppableBehavior}
 * 
 * @author Julien Roche
 */
public class DroppableBehaviorTestCase extends WiQueryTestCase
{
	// Properties
	private DroppableBehavior droppableBehavior;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		droppableBehavior = new DroppableBehavior();

		Panel panel = new DivTestPanel("panelId");
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(droppableBehavior);
		panel.add(component);
		tester.startComponentInPage(panel);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#destroy()}
	 * .
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(droppableBehavior.destroy());
		assertEquals(droppableBehavior.destroy().render().toString(),
			"$('#anId').droppable('destroy');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#disable()}
	 * .
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(droppableBehavior.disable());
		assertEquals(droppableBehavior.disable().render().toString(),
			"$('#anId').droppable('disable');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(droppableBehavior.enable());
		assertEquals(droppableBehavior.enable().render().toString(),
			"$('#anId').droppable('enable');");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#getAccept()}.
	 */
	@Test
	public void testGetAccept()
	{
		assertNotNull(droppableBehavior.getAccept());
		assertEquals(droppableBehavior.getAccept().getJavascriptOption().toString(), "'*'");
		droppableBehavior.setAccept(new DroppableAccept("div"));
		assertEquals(droppableBehavior.getAccept().getJavascriptOption().toString(), "'div'");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#getActiveClass()} .
	 */
	@Test
	public void testGetActiveClass()
	{
		assertNull(droppableBehavior.getActiveClass());
		droppableBehavior.setActiveClass(".aClass");
		assertNotNull(droppableBehavior.getActiveClass());
		assertEquals(droppableBehavior.getActiveClass(), ".aClass");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#getHoverClass()} .
	 */
	@Test
	public void testGetHoverClass()
	{
		assertNull(droppableBehavior.getHoverClass());
		droppableBehavior.setHoverClass(".aClass");
		assertNotNull(droppableBehavior.getHoverClass());
		assertEquals(droppableBehavior.getHoverClass(), ".aClass");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(droppableBehavior.getOptions());
		assertEquals(droppableBehavior.getOptions().getJavaScriptOptions().toString(), "{}");
		droppableBehavior.setActiveClass(".aClass");
		assertEquals(droppableBehavior.getOptions().getJavaScriptOptions().toString(),
			"{activeClass: '.aClass'}");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#getScope()}.
	 */
	@Test
	public void testGetScope()
	{
		assertEquals(droppableBehavior.getScope(), "default");
		droppableBehavior.setScope("aValue");
		assertEquals(droppableBehavior.getScope(), "aValue");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#getTolerance()}.
	 */
	@Test
	public void testGetTolerance()
	{
		assertEquals(droppableBehavior.getTolerance(), ToleranceEnum.INTERSECT);
		droppableBehavior.setTolerance(ToleranceEnum.TOUCH);
		assertEquals(droppableBehavior.getTolerance(), ToleranceEnum.TOUCH);
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#isAddClasses()}.
	 */
	@Test
	public void testIsAddClasses()
	{
		assertTrue(droppableBehavior.isAddClasses());
		droppableBehavior.setAddClasses(false);
		assertFalse(droppableBehavior.isAddClasses());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(droppableBehavior.isDisabled());
		droppableBehavior.setDisabled(true);
		assertTrue(droppableBehavior.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#isGreedy()}.
	 */
	@Test
	public void testIsGreedy()
	{
		assertFalse(droppableBehavior.isGreedy());
		droppableBehavior.setGreedy(true);
		assertTrue(droppableBehavior.isGreedy());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.droppable.DroppableBehavior#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(droppableBehavior.widget());
		assertEquals(droppableBehavior.widget().render().toString(),
			"$('#anId').droppable('widget');");
	}
}
