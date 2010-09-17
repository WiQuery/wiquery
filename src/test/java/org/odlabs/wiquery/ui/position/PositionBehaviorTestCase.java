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
package org.odlabs.wiquery.ui.position;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.position.PositionOptions.Collision;
import org.odlabs.wiquery.ui.position.PositionOptions.Position;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test for {@link PositionBehavior}
 * @author Julien Roche
 *
 */
public class PositionBehaviorTestCase extends TestCase {
	// Properties
	private PositionBehavior positionBehavior;
	
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
		
		positionBehavior = new PositionBehavior();
		
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(positionBehavior);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#getAt()}.
	 */
	@Test
	public void testGetAt() {
		Assert.assertNull(positionBehavior.getAt());
		positionBehavior.setAt(Position.CENTER_TOP);
		Assert.assertEquals(positionBehavior.getAt(), Position.CENTER_TOP);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#getCollision()}.
	 */
	@Test
	public void testGetCollision() {
		Assert.assertNull(positionBehavior.getCollision());
		positionBehavior.setCollision(Collision.FIT_NONE);
		Assert.assertEquals(positionBehavior.getCollision(), Collision.FIT_NONE);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#getMy()}.
	 */
	@Test
	public void testGetMy() {
		Assert.assertNull(positionBehavior.getMy());
		positionBehavior.setMy(Position.CENTER_TOP);
		Assert.assertEquals(positionBehavior.getMy(), Position.CENTER_TOP);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#getOf()}.
	 */
	@Test
	public void testGetOf() {
		Assert.assertNull(positionBehavior.getOf());
		positionBehavior.setOf("#anElement");
		Assert.assertEquals(positionBehavior.getOf(), "#anElement");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#getOffset()}.
	 */
	@Test
	public void testGetOffset() {
		Assert.assertNull(positionBehavior.getOffset());
		positionBehavior.setOffset(new PositionOffset(5));
		Assert.assertNotNull(positionBehavior.getOffset());
		Assert.assertEquals(positionBehavior.getOffset().getJavascriptOption().toString(), "'5 5'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(positionBehavior.getOptions());
		Assert.assertEquals(positionBehavior.getOptions().getJavaScriptOptions().toString(), "{}");
		positionBehavior.setBgiframe(false);
		Assert.assertEquals(positionBehavior.getOptions().getJavaScriptOptions().toString(), "{bgiframe: false}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#isBgiframe()}.
	 */
	@Test
	public void testIsBgiframe() {
		Assert.assertTrue(positionBehavior.isBgiframe());
		positionBehavior.setBgiframe(false);
		Assert.assertFalse(positionBehavior.isBgiframe());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#setBy(org.odlabs.wiquery.ui.position.JsScopePositionEvent)}.
	 */
	@Test
	public void testSetBy() {
		Assert.assertEquals(positionBehavior.statement().render().toString(),
			"$('#anId').position({});");
		positionBehavior.setBy(JsScopePositionEvent.quickScope("alert('event');"));
		Assert.assertEquals(positionBehavior.statement().render().toString(), 
			"$('#anId').position({by: function(params) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.position.PositionBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(positionBehavior.statement());
		Assert.assertEquals(positionBehavior.statement().render().toString(), "$('#anId').position({});");
	}
}
