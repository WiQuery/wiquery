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
package org.odlabs.wiquery.ui.selectable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.selectable.SelectableBehavior.ToleranceEnum;

/**
 * Test on {@link SelectableBehavior}
 * 
 * @author Julien Roche
 */
public class SelectableBehaviorTestCase extends WiQueryTestCase {
	// Properties
	private SelectableBehavior selectableBehavior;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		selectableBehavior = new SelectableBehavior();

		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(selectableBehavior);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		assertNotNull(selectableBehavior.destroy());
		assertEquals(selectableBehavior.destroy().render().toString(),
				"$('#anId').selectable('destroy');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		assertNotNull(selectableBehavior.disable());
		assertEquals(selectableBehavior.disable().render().toString(),
				"$('#anId').selectable('disable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		assertNotNull(selectableBehavior.enable());
		assertEquals(selectableBehavior.enable().render().toString(),
				"$('#anId').selectable('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel() {
		assertEquals(selectableBehavior.getCancel(), "input,option");
		selectableBehavior.setCancel("input");
		assertEquals(selectableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay() {
		assertEquals(selectableBehavior.getDelay(), 0);
		selectableBehavior.setDelay(5);
		assertEquals(selectableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#getDistance()}
	 * .
	 */
	@Test
	public void testGetDistance() {
		assertEquals(selectableBehavior.getDistance(), 0);
		selectableBehavior.setDistance(5);
		assertEquals(selectableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#getFilter()}.
	 */
	@Test
	public void testGetFilter() {
		assertEquals(selectableBehavior.getFilter(), "*");
		selectableBehavior.setFilter("input");
		assertEquals(selectableBehavior.getFilter(), "input");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		assertNotNull(selectableBehavior.getOptions());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#getTolerance()}
	 * .
	 */
	@Test
	public void testGetTolerance() {
		assertEquals(selectableBehavior.getTolerance(), ToleranceEnum.TOUCH);
		selectableBehavior.setTolerance(ToleranceEnum.FIT);
		assertEquals(selectableBehavior.getTolerance(), ToleranceEnum.FIT);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#isAutoRefresh()}
	 * .
	 */
	@Test
	public void testIsAutoRefresh() {
		assertTrue(selectableBehavior.isAutoRefresh());
		selectableBehavior.setAutoRefresh(false);
		assertFalse(selectableBehavior.isAutoRefresh());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		assertFalse(selectableBehavior.isDisabled());
		selectableBehavior.setDisabled(true);
		assertTrue(selectableBehavior.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#refresh()}.
	 */
	@Test
	public void testRefresh() {
		assertNotNull(selectableBehavior.refresh());
		assertEquals(selectableBehavior.refresh().render().toString(),
				"$('#anId').selectable('refresh');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#setSelectedEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetSelectedEvent() {
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({});");
		selectableBehavior.setSelectedEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(
				selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({selected: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#setSelectingEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetSelectingEvent() {
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({});");
		selectableBehavior.setSelectingEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(
				selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({selecting: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStartEvent() {
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({});");
		selectableBehavior.setStartEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({start: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStopEvent() {
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({});");
		selectableBehavior.setStopEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({stop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#setUnselectedEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetUnselectedEvent() {
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({});");
		selectableBehavior.setUnselectedEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(
				selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({unselected: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#setUnselectingEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetUnselectingEvent() {
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({});");
		selectableBehavior.setUnselectingEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(
				selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({unselecting: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		assertNotNull(selectableBehavior.statement());
		assertEquals(selectableBehavior.statement().render().toString(),
				"$('#anId').selectable({});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.selectable.SelectableBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		assertNotNull(selectableBehavior.widget());
		assertEquals(selectableBehavior.widget().render().toString(),
				"$('#anId').selectable('widget');");
	}
}
