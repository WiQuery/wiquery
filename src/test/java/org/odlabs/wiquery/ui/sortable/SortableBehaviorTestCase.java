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

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.AxisEnum;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.CursorAtEnum;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.ToleranceEnum;
import org.odlabs.wiquery.ui.sortable.SortableContainment.ElementEnum;
import org.odlabs.wiquery.ui.sortable.SortableHelper.HelperEnum;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test {@link SortableBehavior}
 * @author Julien Roche
 *
 */
public class SortableBehaviorTestCase extends TestCase {
	// Properties
	private SortableBehavior sortableBehavior;
	
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
		
		sortableBehavior = new SortableBehavior();
		
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(sortableBehavior);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#cancel()}.
	 */
	@Test
	public void testCancel() {
		Assert.assertNotNull(sortableBehavior.cancel());
		Assert.assertEquals(sortableBehavior.cancel().render().toString(), 
				"$('#anId').sortable('cancel');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(sortableBehavior.destroy());
		Assert.assertEquals(sortableBehavior.destroy().render().toString(), 
				"$('#anId').sortable('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(sortableBehavior.disable());
		Assert.assertEquals(sortableBehavior.disable().render().toString(), 
				"$('#anId').sortable('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(sortableBehavior.enable());
		Assert.assertEquals(sortableBehavior.enable().render().toString(), 
				"$('#anId').sortable('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getAppendTo()}.
	 */
	@Test
	public void testGetAppendTo() {
		Assert.assertEquals(sortableBehavior.getAppendTo(), "parent");
		sortableBehavior.setAppendTo("document");
		Assert.assertEquals(sortableBehavior.getAppendTo(), "document");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getAxis()}.
	 */
	@Test
	public void testGetAxis() {
		Assert.assertNull(sortableBehavior.getAxis());
		sortableBehavior.setAxis(AxisEnum.X);
		Assert.assertEquals(sortableBehavior.getAxis(), AxisEnum.X);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel() {
		Assert.assertEquals(sortableBehavior.getCancel(), "input,button");
		sortableBehavior.setCancel("input");
		Assert.assertEquals(sortableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getConnectWith()}.
	 */
	@Test
	public void testGetConnectWith() {
		Assert.assertNull(sortableBehavior.getConnectWith());
		sortableBehavior.setConnectWith("sortableId");
		Assert.assertEquals(sortableBehavior.getConnectWith(), "sortableId");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getContainmentComplex()}.
	 */
	@Test
	public void testGetContainmentComplex() {
		Assert.assertNull(sortableBehavior.getContainmentComplex());
		sortableBehavior.setContainment(new SortableContainment(ElementEnum.DOCUMENT));
		Assert.assertEquals(sortableBehavior.getContainmentComplex().getJavascriptOption(), 
				"'document'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getCursor()}.
	 */
	@Test
	public void testGetCursor() {
		Assert.assertEquals(sortableBehavior.getCursor(), "auto");
		sortableBehavior.setCursor("crosshair");
		Assert.assertEquals(sortableBehavior.getCursor(), "crosshair");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getCursorAt()}.
	 */
	@Test
	public void testGetCursorAt() {
		Assert.assertNull(sortableBehavior.getCursorAt());
		sortableBehavior.setCursorAt(CursorAtEnum.BOTTOM);
		Assert.assertEquals(sortableBehavior.getCursorAt(), CursorAtEnum.BOTTOM);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay() {
		Assert.assertEquals(sortableBehavior.getDelay(), 0);
		sortableBehavior.setDelay(5);
		Assert.assertEquals(sortableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getDistance()}.
	 */
	@Test
	public void testGetDistance() {
		Assert.assertEquals(sortableBehavior.getDistance(), 1);
		sortableBehavior.setDistance(5);
		Assert.assertEquals(sortableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getGrid()}.
	 */
	@Test
	public void testGetGrid() {
		Assert.assertNull(sortableBehavior.getGrid());
		sortableBehavior.setGrid(5, 5);
		Assert.assertNotNull(sortableBehavior.getGrid());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getHandle()}.
	 */
	@Test
	public void testGetHandle() {
		Assert.assertNull(sortableBehavior.getHandle());
		sortableBehavior.setHandle("handle");
		Assert.assertEquals(sortableBehavior.getHandle(), "handle");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getHelperComplex()}.
	 */
	@Test
	public void testGetHelperComplex() {
		Assert.assertEquals(sortableBehavior.getHelperComplex().getJavascriptOption().toString(), "'original'");
		sortableBehavior.setHelper(new SortableHelper(HelperEnum.CLONE));
		Assert.assertEquals(sortableBehavior.getHelperComplex().getJavascriptOption().toString(), "'clone'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getItems()}.
	 */
	@Test
	public void testGetItems() {
		Assert.assertEquals(sortableBehavior.getItems(), "> *");
		sortableBehavior.setItems("div");
		Assert.assertEquals(sortableBehavior.getItems(), "div");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getOpacity()}.
	 */
	@Test
	public void testGetOpacity() {
		Assert.assertEquals(sortableBehavior.getOpacity(), 0F);
		sortableBehavior.setOpacity(1F);
		Assert.assertEquals(sortableBehavior.getOpacity(), 1F);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(sortableBehavior.getOptions());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getPlaceHolder()}.
	 */
	@Test
	public void testGetPlaceHolder() {
		Assert.assertNull(sortableBehavior.getPlaceHolder());
		sortableBehavior.setPlaceholder("aClass");
		Assert.assertEquals(sortableBehavior.getPlaceHolder(), "aClass");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getRevert()}.
	 */
	@Test
	public void testGetRevert() {
		Assert.assertEquals(sortableBehavior.getRevert().getJavascriptOption().toString(), "false");
		sortableBehavior.setRevert(new SortableRevert(5));
		Assert.assertEquals(sortableBehavior.getRevert().getJavascriptOption().toString(), "5");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getScrollSensitivity()}.
	 */
	@Test
	public void testGetScrollSensitivity() {
		Assert.assertEquals(sortableBehavior.getScrollSensitivity(), 20);
		sortableBehavior.setScrollSensitivity(30);
		Assert.assertEquals(sortableBehavior.getScrollSensitivity(), 30);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getScrollSpeed()}.
	 */
	@Test
	public void testGetScrollSpeed() {
		Assert.assertEquals(sortableBehavior.getScrollSpeed(), 20);
		sortableBehavior.setScrollSpeed(30);
		Assert.assertEquals(sortableBehavior.getScrollSpeed(), 30);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getTolerance()}.
	 */
	@Test
	public void testGetTolerance() {
		Assert.assertEquals(sortableBehavior.getTolerance(), ToleranceEnum.INTERSECT);
		sortableBehavior.setTolerance(ToleranceEnum.POINTER);
		Assert.assertEquals(sortableBehavior.getTolerance(), ToleranceEnum.POINTER);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#getZIndex()}.
	 */
	@Test
	public void testGetZIndex() {
		Assert.assertEquals(sortableBehavior.getZIndex(), 1000);
		sortableBehavior.setZIndex(30);
		Assert.assertEquals(sortableBehavior.getZIndex(), 30);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isDropOnEmpty()}.
	 */
	@Test
	public void testIsDropOnEmpty() {
		Assert.assertTrue(sortableBehavior.isDropOnEmpty());
		sortableBehavior.setDropOnEmpty(false);
		Assert.assertFalse(sortableBehavior.isDropOnEmpty());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isForceHelperSize()}.
	 */
	@Test
	public void testIsForceHelperSize() {
		Assert.assertFalse(sortableBehavior.isForceHelperSize());
		sortableBehavior.setForceHelperSize(true);
		Assert.assertTrue(sortableBehavior.isForceHelperSize());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isForcePlaceholderSize()}.
	 */
	@Test
	public void testIsForcePlaceholderSize() {
		Assert.assertFalse(sortableBehavior.isForcePlaceholderSize());
		sortableBehavior.setForcePlaceholderSize(true);
		Assert.assertTrue(sortableBehavior.isForcePlaceholderSize());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#isScroll()}.
	 */
	@Test
	public void testIsScroll() {
		Assert.assertTrue(sortableBehavior.isScroll());
		sortableBehavior.setScroll(false);
		Assert.assertFalse(sortableBehavior.isScroll());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#refresh()}.
	 */
	@Test
	public void testRefresh() {
		Assert.assertNotNull(sortableBehavior.refresh());
		Assert.assertEquals(sortableBehavior.refresh().render().toString(), 
				"$('#anId').sortable('refresh');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#refreshPositions()}.
	 */
	@Test
	public void testRefreshPositions() {
		Assert.assertNotNull(sortableBehavior.refreshPositions());
		Assert.assertEquals(sortableBehavior.refreshPositions().render().toString(), 
				"$('#anId').sortable('refreshPositions');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#serialize()}.
	 */
	@Test
	public void testSerialize() {
		Assert.assertNotNull(sortableBehavior.serialize());
		Assert.assertEquals(sortableBehavior.serialize().render().toString(), 
				"$('#anId').sortable('serialize');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setActivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetActivateEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setActivateEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({activate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setBeforeStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetBeforeStopEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setBeforeStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({beforeStop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetChangeEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({change: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setDeactivateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDeactivateEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setDeactivateEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({deactivate: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setOutEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetOutEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setOutEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({out: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setOverEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetOverEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setOverEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({over: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setReceiveEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetReceiveEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setReceiveEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({receive: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setRemoveEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetRemoveEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setRemoveEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({remove: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setSortEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetSortEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setSortEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({sort: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStartEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({start: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStopEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({stop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#setUpdateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetUpdateEvent() {
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({});");
		sortableBehavior.setUpdateEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
			"$('#anId').sortable({update: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(sortableBehavior.statement());
		Assert.assertEquals(sortableBehavior.statement().render().toString(), 
				"$('#anId').sortable({});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.sortable.SortableBehavior#toArray()}.
	 */
	@Test
	public void testToArray() {
		Assert.assertNotNull(sortableBehavior.toArray());
		Assert.assertEquals(sortableBehavior.toArray().render().toString(), 
				"$('#anId').sortable('toArray');");
	}
}
