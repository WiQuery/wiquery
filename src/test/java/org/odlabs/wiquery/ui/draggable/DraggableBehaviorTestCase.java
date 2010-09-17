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
package org.odlabs.wiquery.ui.draggable;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.AxisEnum;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.CursorAtEnum;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.SnapModeEnum;
import org.odlabs.wiquery.ui.draggable.DraggableContainment.ContainmentEnum;
import org.odlabs.wiquery.ui.draggable.DraggableHelper.HelperEnum;
import org.odlabs.wiquery.ui.draggable.DraggableRevert.RevertEnum;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link DraggableBehavior}
 * @author Julien Roche
 *
 */
public class DraggableBehaviorTestCase extends TestCase {
	// Properties
	private DraggableBehavior draggableBehavior;

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
		
		draggableBehavior = new DraggableBehavior();
		
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(draggableBehavior);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(draggableBehavior.destroy());
		Assert.assertEquals(draggableBehavior.destroy().render().toString(), 
				"$('#anId').draggable('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(draggableBehavior.disable());
		Assert.assertEquals(draggableBehavior.disable().render().toString(), 
				"$('#anId').draggable('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(draggableBehavior.enable());
		Assert.assertEquals(draggableBehavior.enable().render().toString(), 
				"$('#anId').draggable('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getAppendTo()}.
	 */
	@Test
	public void testGetAppendTo() {
		Assert.assertEquals(draggableBehavior.getAppendTo(), "parent");
		draggableBehavior.setAppendTo("document");
		Assert.assertEquals(draggableBehavior.getAppendTo(), "document");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getAxis()}.
	 */
	@Test
	public void testGetAxis() {
		Assert.assertNull(draggableBehavior.getAxis());
		draggableBehavior.setAxis(AxisEnum.X);
		Assert.assertEquals(draggableBehavior.getAxis(), AxisEnum.X);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel() {
		Assert.assertEquals(draggableBehavior.getCancel(), "input,option");
		draggableBehavior.setCancel("input");
		Assert.assertEquals(draggableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getConnectToSortable()}.
	 */
	@Test
	public void testGetConnectToSortable() {
		Assert.assertNull(draggableBehavior.getConnectToSortable());
		draggableBehavior.setConnectToSortable("aSortable");
		Assert.assertEquals(draggableBehavior.getConnectToSortable(), "aSortable");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getContainment()}.
	 */
	@Test
	public void testGetContainment() {
		Assert.assertNull(draggableBehavior.getContainment());
		draggableBehavior.setContainment(new DraggableContainment(ContainmentEnum.DOCUMENT));
		Assert.assertEquals(draggableBehavior.getContainment().getJavascriptOption().toString(), "'document'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getCursor()}.
	 */
	@Test
	public void testGetCursor() {
		Assert.assertEquals(draggableBehavior.getCursor(), "auto");
		draggableBehavior.setCursor("crosshair");
		Assert.assertEquals(draggableBehavior.getCursor(), "crosshair");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getCursorAtComplex()}.
	 */
	@Test
	public void testGetCursorAtComplex() {
		Assert.assertNull(draggableBehavior.getCursorAtComplex());
		ListItemOptions<DraggableCursorAt> array = new ListItemOptions<DraggableCursorAt>();
		array.add(new DraggableCursorAt(CursorAtEnum.TOP, 5));
		draggableBehavior.setCursorAt(array);
		Assert.assertEquals(draggableBehavior.getCursorAtComplex().getJavascriptOption().toString(), 
				"{top:5}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay() {
		Assert.assertEquals(draggableBehavior.getDelay(), 0);
		draggableBehavior.setDelay(5);
		Assert.assertEquals(draggableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getDistance()}.
	 */
	@Test
	public void testGetDistance() {
		Assert.assertEquals(draggableBehavior.getDistance(), 1);
		draggableBehavior.setDistance(5);
		Assert.assertEquals(draggableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getGrid()}.
	 */
	@Test
	public void testGetGrid() {
		Assert.assertNull(draggableBehavior.getGrid());
		draggableBehavior.setGrid(5, 6);
		Assert.assertEquals(draggableBehavior.getGrid().getJavascriptOption().toString(), 
				"[5,6]");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getHandle()}.
	 */
	@Test
	public void testGetHandle() {
		Assert.assertNull(draggableBehavior.getHandle());
		draggableBehavior.setHandle("parent");
		Assert.assertEquals(draggableBehavior.getHandle(), "parent");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getHelper()}.
	 */
	@Test
	public void testGetHelper() {
		Assert.assertNotNull(draggableBehavior.getHelper());
		Assert.assertEquals(draggableBehavior.getHelper().getJavascriptOption().toString(), "'original'");
		draggableBehavior.setHelper(new DraggableHelper(HelperEnum.CLONE));
		Assert.assertEquals(draggableBehavior.getHelper().getJavascriptOption().toString(), "'clone'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getIframeFix()}.
	 */
	@Test
	public void testGetIframeFix() {
		Assert.assertNotNull(draggableBehavior.getIframeFix());
		Assert.assertEquals(draggableBehavior.getIframeFix().getJavascriptOption().toString(), "false");
		draggableBehavior.setIframeFix(new DraggableIframeFix(true));
		Assert.assertEquals(draggableBehavior.getIframeFix().getJavascriptOption().toString(), "true");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getOpacity()}.
	 */
	@Test
	public void testGetOpacity() {
		Assert.assertEquals(draggableBehavior.getOpacity(), 0F);
		draggableBehavior.setOpacity(5F);
		Assert.assertEquals(draggableBehavior.getOpacity(), 5F);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(draggableBehavior.getOptions());
		Assert.assertEquals(draggableBehavior.getOptions().getJavaScriptOptions().toString(), "{}");
		draggableBehavior.setAddClasses(true);
		Assert.assertEquals(draggableBehavior.getOptions().getJavaScriptOptions().toString(), "{addClasses: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getRevert()}.
	 */
	@Test
	public void testGetRevert() {
		Assert.assertNotNull(draggableBehavior.getRevert());
		Assert.assertEquals(draggableBehavior.getRevert().getJavascriptOption().toString(), "false");
		draggableBehavior.setRevert(new DraggableRevert(RevertEnum.INVALID));
		Assert.assertEquals(draggableBehavior.getRevert().getJavascriptOption().toString(), "'invalid'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getRevertDuration()}.
	 */
	@Test
	public void testGetRevertDuration() {
		Assert.assertEquals(draggableBehavior.getRevertDuration(), 500);
		draggableBehavior.setRevertDuration(100);
		Assert.assertEquals(draggableBehavior.getRevertDuration(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getScope()}.
	 */
	@Test
	public void testGetScope() {
		Assert.assertEquals(draggableBehavior.getScope(), "default");
		draggableBehavior.setScope("aValue");
		Assert.assertEquals(draggableBehavior.getScope(), "aValue");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getScrollSensitivity()}.
	 */
	@Test
	public void testGetScrollSensitivity() {
		Assert.assertEquals(draggableBehavior.getScrollSensitivity(), 20);
		draggableBehavior.setScrollSensitivity(100);
		Assert.assertEquals(draggableBehavior.getScrollSensitivity(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getScrollSpeed()}.
	 */
	@Test
	public void testGetScrollSpeed() {
		Assert.assertEquals(draggableBehavior.getScrollSpeed(), 20);
		draggableBehavior.setScrollSpeed(100);
		Assert.assertEquals(draggableBehavior.getScrollSpeed(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getSnap()}.
	 */
	@Test
	public void testGetSnap() {
		Assert.assertNotNull(draggableBehavior.getSnap());
		Assert.assertEquals(draggableBehavior.getSnap().getJavascriptOption().toString(), "false");
		draggableBehavior.setSnap(new DraggableSnap(true));
		Assert.assertEquals(draggableBehavior.getSnap().getJavascriptOption().toString(), "true");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getSnapMode()}.
	 */
	@Test
	public void testGetSnapMode() {
		Assert.assertEquals(draggableBehavior.getSnapMode(), SnapModeEnum.BOTH);
		draggableBehavior.setSnapMode(SnapModeEnum.INNER);
		Assert.assertEquals(draggableBehavior.getSnapMode(), SnapModeEnum.INNER);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getSnapTolerance()}.
	 */
	@Test
	public void testGetSnapTolerance() {
		Assert.assertEquals(draggableBehavior.getSnapTolerance(), 20);
		draggableBehavior.setSnapTolerance(100);
		Assert.assertEquals(draggableBehavior.getSnapTolerance(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getStack()}.
	 */
	@Test
	public void testgetStack() {
		Assert.assertNull(draggableBehavior.getStack());
		draggableBehavior.setStack("group");
		Assert.assertEquals(draggableBehavior.getStack(), "group");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getZIndex()}.
	 */
	@Test
	public void testGetZIndex() {
		Assert.assertEquals(draggableBehavior.getZIndex(), 0);
		draggableBehavior.setZIndex(100);
		Assert.assertEquals(draggableBehavior.getZIndex(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isAddClasses()}.
	 */
	@Test
	public void testIsAddClasses() {
		Assert.assertTrue(draggableBehavior.isAddClasses());
		draggableBehavior.setAddClasses(false);
		Assert.assertFalse(draggableBehavior.isAddClasses());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(draggableBehavior.isDisabled());
		draggableBehavior.setDisabled(true);
		Assert.assertTrue(draggableBehavior.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isRefreshPositions()}.
	 */
	@Test
	public void testIsRefreshPositions() {
		Assert.assertFalse(draggableBehavior.isRefreshPositions());
		draggableBehavior.setRefreshPositions(true);
		Assert.assertTrue(draggableBehavior.isRefreshPositions());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isScroll()}.
	 */
	@Test
	public void testIsScroll() {
		Assert.assertTrue(draggableBehavior.isScroll());
		draggableBehavior.setScroll(false);
		Assert.assertFalse(draggableBehavior.isScroll());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#setDragEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDragEvent() {
		Assert.assertEquals(draggableBehavior.statement().render().toString(),
			"$('#anId').draggable({});");
		draggableBehavior.setDragEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(draggableBehavior.statement().render().toString(), 
			"$('#anId').draggable({drag: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStartEvent() {
		Assert.assertEquals(draggableBehavior.statement().render().toString(),
			"$('#anId').draggable({});");
		draggableBehavior.setStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(draggableBehavior.statement().render().toString(), 
			"$('#anId').draggable({start: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStopEvent() {
		Assert.assertEquals(draggableBehavior.statement().render().toString(),
			"$('#anId').draggable({});");
		draggableBehavior.setStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(draggableBehavior.statement().render().toString(), 
			"$('#anId').draggable({stop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(draggableBehavior.statement());
		Assert.assertEquals(draggableBehavior.statement().render().toString(), "$('#anId').draggable({});");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(draggableBehavior.widget());
		Assert.assertEquals(draggableBehavior.widget().render().toString(), 
				"$('#anId').draggable('widget');");
	}
}
