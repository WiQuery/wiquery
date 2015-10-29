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
package org.wicketstuff.wiquery.ui.position;

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

/**
 * Test for {@link PositionBehavior}
 * 
 * @author Julien Roche
 */
public class PositionBehaviorTestCase extends WiQueryTestCase
{
	// Properties
	private PositionBehavior positionBehavior;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		positionBehavior = new PositionBehavior();

		Panel panel = new DivTestPanel("panelId");
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(positionBehavior);
		panel.add(component);
		tester.startComponentInPage(panel);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.position.PositionBehavior#getAt()}.
	 */
	@Test
	public void testGetAt()
	{
		assertNull(positionBehavior.getAt());
		positionBehavior.setAt(new PositionAlignmentOptions(PositionRelation.CENTER, PositionRelation.TOP));
		assertEquals(positionBehavior.getAt().getJavascriptOption().toString(), "'center top'");
		positionBehavior.setAt(new PositionAlignmentOptions(PositionRelation.RIGHT, 20, PositionRelation.BOTTOM, -20));
		assertEquals(positionBehavior.getAt().getJavascriptOption().toString(), "'right+20 bottom-20'");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.position.PositionBehavior#getCollision()}.
	 */
	@Test
	public void testGetCollision()
	{
		assertNull(positionBehavior.getCollision());
		positionBehavior.setCollision(PositionCollision.FIT_NONE);
		assertEquals(positionBehavior.getCollision(), PositionCollision.FIT_NONE);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.position.PositionBehavior#getMy()}.
	 */
	@Test
	public void testGetMy()
	{
		assertNull(positionBehavior.getMy());
		positionBehavior.setMy(new PositionAlignmentOptions(PositionRelation.CENTER, PositionRelation.TOP));
		assertEquals(positionBehavior.getMy().getJavascriptOption().toString(), "'center top'");
		positionBehavior.setMy(new PositionAlignmentOptions(PositionRelation.LEFT, 0, PositionRelation.CENTER, -20));
		assertEquals(positionBehavior.getMy().getJavascriptOption().toString(), "'left center-20'");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.position.PositionBehavior#getOf()}.
	 */
	@Test
	public void testGetOf()
	{
		assertNull(positionBehavior.getOf());
		positionBehavior.setOf("#anElement");
		assertEquals(positionBehavior.getOf(), "#anElement");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.position.PositionBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(positionBehavior.getOptions());
		assertEquals(positionBehavior.getOptions().getJavaScriptOptions().toString(), "{}");
		positionBehavior.setCollision(PositionCollision.FIT);
		assertEquals(positionBehavior.getOptions().getJavaScriptOptions().toString(),
			"{collision: 'fit'}");
	}
	
	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.ui.position.PositionBehavior#isBgiframe()}.
	 */
	@Test
	public void testIsBgiframe()
	{
		assertTrue(positionBehavior.isBgiframe());
		positionBehavior.setBgiframe(false);
		assertFalse(positionBehavior.isBgiframe());
	}
}
