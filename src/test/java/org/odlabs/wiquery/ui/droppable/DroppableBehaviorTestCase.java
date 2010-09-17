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
package org.odlabs.wiquery.ui.droppable;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.droppable.DroppableBehavior.ToleranceEnum;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link DroppableBehavior}
 * @author Julien Roche
 *
 */
public class DroppableBehaviorTestCase extends TestCase {
	// Properties
	private DroppableBehavior droppableBehavior;

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
		
		droppableBehavior = new DroppableBehavior();
		
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(droppableBehavior);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(droppableBehavior.destroy());
		Assert.assertEquals(droppableBehavior.destroy().render().toString(), 
				"$('#anId').droppable('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(droppableBehavior.disable());
		Assert.assertEquals(droppableBehavior.disable().render().toString(), 
				"$('#anId').droppable('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(droppableBehavior.enable());
		Assert.assertEquals(droppableBehavior.enable().render().toString(), 
				"$('#anId').droppable('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#getAccept()}.
	 */
	@Test
	public void testGetAccept() {
		Assert.assertNotNull(droppableBehavior.getAccept());
		Assert.assertEquals(droppableBehavior.getAccept().getJavascriptOption().toString(), "'*'");
		droppableBehavior.setAccept(new DroppableAccept("div"));
		Assert.assertEquals(droppableBehavior.getAccept().getJavascriptOption().toString(), "'div'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#getActiveClass()}.
	 */
	@Test
	public void testGetActiveClass() {
		Assert.assertNull(droppableBehavior.getActiveClass());
		droppableBehavior.setActiveClass(".aClass");
		Assert.assertNotNull(droppableBehavior.getActiveClass());
		Assert.assertEquals(droppableBehavior.getActiveClass(), ".aClass");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#getHoverClass()}.
	 */
	@Test
	public void testGetHoverClass() {
		Assert.assertNull(droppableBehavior.getHoverClass());
		droppableBehavior.setHoverClass(".aClass");
		Assert.assertNotNull(droppableBehavior.getHoverClass());
		Assert.assertEquals(droppableBehavior.getHoverClass(), ".aClass");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(droppableBehavior.getOptions());
		Assert.assertEquals(droppableBehavior.getOptions().getJavaScriptOptions().toString(), 
				"{}");
		droppableBehavior.setActiveClass(".aClass");
		Assert.assertEquals(droppableBehavior.getOptions().getJavaScriptOptions().toString(), 
			"{activeClass: '.aClass'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#getScope()}.
	 */
	@Test
	public void testGetScope() {
		Assert.assertEquals(droppableBehavior.getScope(), "default");
		droppableBehavior.setScope("aValue");
		Assert.assertEquals(droppableBehavior.getScope(), "aValue");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#getTolerance()}.
	 */
	@Test
	public void testGetTolerance() {
		Assert.assertEquals(droppableBehavior.getTolerance(), ToleranceEnum.INTERSECT);
		droppableBehavior.setTolerance(ToleranceEnum.TOUCH);
		Assert.assertEquals(droppableBehavior.getTolerance(), ToleranceEnum.TOUCH);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#isAddClasses()}.
	 */
	@Test
	public void testIsAddClasses() {
		Assert.assertTrue(droppableBehavior.isAddClasses());
		droppableBehavior.setAddClasses(false);
		Assert.assertFalse(droppableBehavior.isAddClasses());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(droppableBehavior.isDisabled());
		droppableBehavior.setDisabled(true);
		Assert.assertTrue(droppableBehavior.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#isGreedy()}.
	 */
	@Test
	public void testIsGreedy() {
		Assert.assertFalse(droppableBehavior.isGreedy());
		droppableBehavior.setGreedy(true);
		Assert.assertTrue(droppableBehavior.isGreedy());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#setActivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetActivateEvent() {
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({});");
		droppableBehavior.setActivateEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({activate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#setDeactivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDeactivateEvent() {
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({});");
		droppableBehavior.setDeactivateEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({deactivate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#setDropEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDropEvent() {
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({});");
		droppableBehavior.setDropEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({drop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#setOutEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetOutEvent() {
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({});");
		droppableBehavior.setOutEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({out: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#setOverEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetOverEvent() {
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({});");
		droppableBehavior.setOverEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(droppableBehavior.statement().render().toString(), 
			"$('#anId').droppable({over: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(droppableBehavior.statement());
		Assert.assertEquals(droppableBehavior.statement().render().toString(), "$('#anId').droppable({});");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.droppable.DroppableBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(droppableBehavior.widget());
		Assert.assertEquals(droppableBehavior.widget().render().toString(), 
				"$('#anId').droppable('widget');");
	}
}
