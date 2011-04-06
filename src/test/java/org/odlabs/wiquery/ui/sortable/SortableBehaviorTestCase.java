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
package org.odlabs.wiquery.ui.sortable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.AxisEnum;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.CursorAtEnum;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.ToleranceEnum;
import org.odlabs.wiquery.ui.sortable.SortableContainment.ElementEnum;
import org.odlabs.wiquery.ui.sortable.SortableHelper.HelperEnum;

/**
 * Test {@link SortableBehavior}
 * 
 * @author Julien Roche
 */
public class SortableBehaviorTestCase extends WiQueryTestCase {
	// Properties
	private SortableBehavior sortableBehavior;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		sortableBehavior = new SortableBehavior();

		tester.startPanel(new TestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				Panel panel = new DivTestPanel(panelId);
				WebMarkupContainer component = new WebMarkupContainer("anId");
				component.setMarkupId("anId");
				component.add(sortableBehavior);
				panel.add(component);
				return panel;
			}
		});
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#cancel()}.
	 */
	@Test
	public void testCancel() {
		assertNotNull(sortableBehavior.cancel());
		assertEquals(sortableBehavior.cancel().render().toString(),
				"$('#anId').sortable('cancel');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		assertNotNull(sortableBehavior.destroy());
		assertEquals(sortableBehavior.destroy().render().toString(),
				"$('#anId').sortable('destroy');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		assertNotNull(sortableBehavior.disable());
		assertEquals(sortableBehavior.disable().render().toString(),
				"$('#anId').sortable('disable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		assertNotNull(sortableBehavior.enable());
		assertEquals(sortableBehavior.enable().render().toString(),
				"$('#anId').sortable('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getAppendTo()}.
	 */
	@Test
	public void testGetAppendTo() {
		assertEquals(sortableBehavior.getAppendTo(), "parent");
		sortableBehavior.setAppendTo("document");
		assertEquals(sortableBehavior.getAppendTo(), "document");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getAxis()}.
	 */
	@Test
	public void testGetAxis() {
		assertNull(sortableBehavior.getAxis());
		sortableBehavior.setAxis(AxisEnum.X);
		assertEquals(sortableBehavior.getAxis(), AxisEnum.X);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel() {
		assertEquals(sortableBehavior.getCancel(), "input,button");
		sortableBehavior.setCancel("input");
		assertEquals(sortableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getConnectWith()}.
	 */
	@Test
	public void testGetConnectWith() {
		assertNull(sortableBehavior.getConnectWith());
		sortableBehavior.setConnectWith("sortableId");
		assertEquals(sortableBehavior.getConnectWith(), "sortableId");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getContainmentComplex()}
	 * .
	 */
	@Test
	public void testGetContainmentComplex() {
		assertNull(sortableBehavior.getContainmentComplex());
		sortableBehavior.setContainment(new SortableContainment(
				ElementEnum.DOCUMENT));
		assertEquals(sortableBehavior.getContainmentComplex()
				.getJavascriptOption(), "'document'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getCursor()}.
	 */
	@Test
	public void testGetCursor() {
		assertEquals(sortableBehavior.getCursor(), "auto");
		sortableBehavior.setCursor("crosshair");
		assertEquals(sortableBehavior.getCursor(), "crosshair");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getCursorAt()}.
	 */
	@Test
	public void testGetCursorAt() {
		assertNull(sortableBehavior.getCursorAt());
		sortableBehavior.setCursorAt(CursorAtEnum.BOTTOM);
		assertEquals(sortableBehavior.getCursorAt(), CursorAtEnum.BOTTOM);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay() {
		assertEquals(sortableBehavior.getDelay(), 0);
		sortableBehavior.setDelay(5);
		assertEquals(sortableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getDistance()}.
	 */
	@Test
	public void testGetDistance() {
		assertEquals(sortableBehavior.getDistance(), 1);
		sortableBehavior.setDistance(5);
		assertEquals(sortableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getGrid()}.
	 */
	@Test
	public void testGetGrid() {
		assertNull(sortableBehavior.getGrid());
		sortableBehavior.setGrid(5, 5);
		assertNotNull(sortableBehavior.getGrid());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getHandle()}.
	 */
	@Test
	public void testGetHandle() {
		assertNull(sortableBehavior.getHandle());
		sortableBehavior.setHandle("handle");
		assertEquals(sortableBehavior.getHandle(), "handle");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getHelperComplex()}
	 * .
	 */
	@Test
	public void testGetHelperComplex() {
		assertEquals(sortableBehavior.getHelperComplex().getJavascriptOption()
				.toString(), "'original'");
		sortableBehavior.setHelper(new SortableHelper(HelperEnum.CLONE));
		assertEquals(sortableBehavior.getHelperComplex().getJavascriptOption()
				.toString(), "'clone'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getItems()}.
	 */
	@Test
	public void testGetItems() {
		assertEquals(sortableBehavior.getItems(), "> *");
		sortableBehavior.setItems("div");
		assertEquals(sortableBehavior.getItems(), "div");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getOpacity()}.
	 */
	@Test
	public void testGetOpacity() {
		assertEquals((Object) sortableBehavior.getOpacity(), 0F);
		sortableBehavior.setOpacity(1F);
		assertEquals((Object) sortableBehavior.getOpacity(), 1F);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		assertNotNull(sortableBehavior.getOptions());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getPlaceHolder()}.
	 */
	@Test
	public void testGetPlaceHolder() {
		assertNull(sortableBehavior.getPlaceHolder());
		sortableBehavior.setPlaceholder("aClass");
		assertEquals(sortableBehavior.getPlaceHolder(), "aClass");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getRevert()}.
	 */
	@Test
	public void testGetRevert() {
		assertEquals(sortableBehavior.getRevert().getJavascriptOption()
				.toString(), "false");
		sortableBehavior.setRevert(new SortableRevert(5));
		assertEquals(sortableBehavior.getRevert().getJavascriptOption()
				.toString(), "5");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getScrollSensitivity()}
	 * .
	 */
	@Test
	public void testGetScrollSensitivity() {
		assertEquals(sortableBehavior.getScrollSensitivity(), 20);
		sortableBehavior.setScrollSensitivity(30);
		assertEquals(sortableBehavior.getScrollSensitivity(), 30);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getScrollSpeed()}.
	 */
	@Test
	public void testGetScrollSpeed() {
		assertEquals(sortableBehavior.getScrollSpeed(), 20);
		sortableBehavior.setScrollSpeed(30);
		assertEquals(sortableBehavior.getScrollSpeed(), 30);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getTolerance()}.
	 */
	@Test
	public void testGetTolerance() {
		assertEquals(sortableBehavior.getTolerance(), ToleranceEnum.INTERSECT);
		sortableBehavior.setTolerance(ToleranceEnum.POINTER);
		assertEquals(sortableBehavior.getTolerance(), ToleranceEnum.POINTER);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getZIndex()}.
	 */
	@Test
	public void testGetZIndex() {
		assertEquals(sortableBehavior.getZIndex(), 1000);
		sortableBehavior.setZIndex(30);
		assertEquals(sortableBehavior.getZIndex(), 30);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		assertFalse(sortableBehavior.isDisabled());
		sortableBehavior.setDisabled(true);
		assertTrue(sortableBehavior.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isDropOnEmpty()}.
	 */
	@Test
	public void testIsDropOnEmpty() {
		assertTrue(sortableBehavior.isDropOnEmpty());
		sortableBehavior.setDropOnEmpty(false);
		assertFalse(sortableBehavior.isDropOnEmpty());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isForceHelperSize()}
	 * .
	 */
	@Test
	public void testIsForceHelperSize() {
		assertFalse(sortableBehavior.isForceHelperSize());
		sortableBehavior.setForceHelperSize(true);
		assertTrue(sortableBehavior.isForceHelperSize());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isForcePlaceholderSize()}
	 * .
	 */
	@Test
	public void testIsForcePlaceholderSize() {
		assertFalse(sortableBehavior.isForcePlaceholderSize());
		sortableBehavior.setForcePlaceholderSize(true);
		assertTrue(sortableBehavior.isForcePlaceholderSize());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isScroll()}.
	 */
	@Test
	public void testIsScroll() {
		assertTrue(sortableBehavior.isScroll());
		sortableBehavior.setScroll(false);
		assertFalse(sortableBehavior.isScroll());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#refresh()}.
	 */
	@Test
	public void testRefresh() {
		assertNotNull(sortableBehavior.refresh());
		assertEquals(sortableBehavior.refresh().render().toString(),
				"$('#anId').sortable('refresh');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#refreshPositions()}
	 * .
	 */
	@Test
	public void testRefreshPositions() {
		assertNotNull(sortableBehavior.refreshPositions());
		assertEquals(sortableBehavior.refreshPositions().render().toString(),
				"$('#anId').sortable('refreshPositions');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#serialize()}.
	 */
	@Test
	public void testSerialize() {
		assertNotNull(sortableBehavior.serialize());
		assertEquals(sortableBehavior.serialize().render().toString(),
				"$('#anId').sortable('serialize');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setActivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetActivateEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setActivateEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({activate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setBeforeStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetBeforeStopEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setBeforeStopEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(
				sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({beforeStop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setChangeEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({change: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setDeactivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetDeactivateEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setDeactivateEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(
				sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({deactivate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setOutEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetOutEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setOutEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({out: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setOverEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetOverEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setOverEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({over: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setReceiveEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetReceiveEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setReceiveEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({receive: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setRemoveEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetRemoveEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setRemoveEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({remove: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setSortEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetSortEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setSortEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({sort: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStartEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setStartEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({start: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStopEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setStopEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({stop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setUpdateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetUpdateEvent() {
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
		sortableBehavior.setUpdateEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({update: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		assertNotNull(sortableBehavior.statement());
		assertEquals(sortableBehavior.statement().render().toString(),
				"$('#anId').sortable({});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#toArray()}.
	 */
	@Test
	public void testToArray() {
		assertNotNull(sortableBehavior.toArray());
		assertEquals(sortableBehavior.toArray().render().toString(),
				"$('#anId').sortable('toArray');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		assertNotNull(sortableBehavior.widget());
		assertEquals(sortableBehavior.widget().render().toString(),
				"$('#anId').sortable('widget');");
	}
}
