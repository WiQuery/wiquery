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

import static org.junit.Assert.*;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.AxisEnum;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.CursorAtEnum;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.SnapModeEnum;
import org.odlabs.wiquery.ui.draggable.DraggableContainment.ContainmentEnum;
import org.odlabs.wiquery.ui.draggable.DraggableHelper.HelperEnum;
import org.odlabs.wiquery.ui.draggable.DraggableRevert.RevertEnum;

/**
 * Test on {@link DraggableBehavior}
 * 
 * @author Julien Roche
 */
public class DraggableBehaviorTestCase extends WiQueryTestCase
{
	// Properties
	private DraggableBehavior draggableBehavior;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		draggableBehavior = new DraggableBehavior();

		tester.startPanel(new ITestPanelSource()
		{
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId)
			{
				Panel panel = new DivTestPanel(panelId);
				WebMarkupContainer component = new WebMarkupContainer("anId");
				component.setMarkupId("anId");
				component.add(draggableBehavior);
				panel.add(component);
				return panel;
			}
		});
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#destroy()}
	 * .
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(draggableBehavior.destroy());
		assertEquals(draggableBehavior.destroy().render().toString(),
			"$('#anId').draggable('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#disable()}
	 * .
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(draggableBehavior.disable());
		assertEquals(draggableBehavior.disable().render().toString(),
			"$('#anId').draggable('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(draggableBehavior.enable());
		assertEquals(draggableBehavior.enable().render().toString(),
			"$('#anId').draggable('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getAppendTo()}.
	 */
	@Test
	public void testGetAppendTo()
	{
		assertEquals(draggableBehavior.getAppendTo(), "parent");
		draggableBehavior.setAppendTo("document");
		assertEquals(draggableBehavior.getAppendTo(), "document");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getAxis()}
	 * .
	 */
	@Test
	public void testGetAxis()
	{
		assertNull(draggableBehavior.getAxis());
		draggableBehavior.setAxis(AxisEnum.X);
		assertEquals(draggableBehavior.getAxis(), AxisEnum.X);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel()
	{
		assertEquals(draggableBehavior.getCancel(), "input,option");
		draggableBehavior.setCancel("input");
		assertEquals(draggableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getConnectToSortable()} .
	 */
	@Test
	public void testGetConnectToSortable()
	{
		assertNull(draggableBehavior.getConnectToSortable());
		draggableBehavior.setConnectToSortable("aSortable");
		assertEquals(draggableBehavior.getConnectToSortable(), "aSortable");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getContainment()} .
	 */
	@Test
	public void testGetContainment()
	{
		assertNull(draggableBehavior.getContainment());
		draggableBehavior.setContainment(new DraggableContainment(ContainmentEnum.DOCUMENT));
		assertEquals(draggableBehavior.getContainment().getJavascriptOption().toString(),
			"'document'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getCursor()}.
	 */
	@Test
	public void testGetCursor()
	{
		assertEquals(draggableBehavior.getCursor(), "auto");
		draggableBehavior.setCursor("crosshair");
		assertEquals(draggableBehavior.getCursor(), "crosshair");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getCursorAtComplex()} .
	 */
	@Test
	public void testGetCursorAtComplex()
	{
		assertNull(draggableBehavior.getCursorAtComplex());
		ListItemOptions<DraggableCursorAt> array = new ListItemOptions<DraggableCursorAt>();
		array.add(new DraggableCursorAt(CursorAtEnum.TOP, 5));
		draggableBehavior.setCursorAt(array);
		assertEquals(draggableBehavior.getCursorAtComplex().getJavascriptOption().toString(),
			"{top:5}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay()
	{
		assertEquals(draggableBehavior.getDelay(), 0);
		draggableBehavior.setDelay(5);
		assertEquals(draggableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getDistance()}.
	 */
	@Test
	public void testGetDistance()
	{
		assertEquals(draggableBehavior.getDistance(), 1);
		draggableBehavior.setDistance(5);
		assertEquals(draggableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getGrid()}
	 * .
	 */
	@Test
	public void testGetGrid()
	{
		assertNull(draggableBehavior.getGrid());
		draggableBehavior.setGrid(5, 6);
		assertEquals(draggableBehavior.getGrid().getJavascriptOption().toString(), "[5,6]");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getHandle()}.
	 */
	@Test
	public void testGetHandle()
	{
		assertNull(draggableBehavior.getHandle());
		draggableBehavior.setHandle("parent");
		assertEquals(draggableBehavior.getHandle(), "parent");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getHelper()}.
	 */
	@Test
	public void testGetHelper()
	{
		assertNotNull(draggableBehavior.getHelper());
		assertEquals(draggableBehavior.getHelper().getJavascriptOption().toString(), "'original'");
		draggableBehavior.setHelper(new DraggableHelper(HelperEnum.CLONE));
		assertEquals(draggableBehavior.getHelper().getJavascriptOption().toString(), "'clone'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getIframeFix()}.
	 */
	@Test
	public void testGetIframeFix()
	{
		assertNotNull(draggableBehavior.getIframeFix());
		assertEquals(draggableBehavior.getIframeFix().getJavascriptOption().toString(), "false");
		draggableBehavior.setIframeFix(new DraggableIframeFix(true));
		assertEquals(draggableBehavior.getIframeFix().getJavascriptOption().toString(), "true");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getOpacity()}.
	 */
	@Test
	public void testGetOpacity()
	{
		assertEquals((Object) draggableBehavior.getOpacity(), 0F);
		draggableBehavior.setOpacity(5F);
		assertEquals((Object) draggableBehavior.getOpacity(), 5F);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(draggableBehavior.getOptions());
		assertEquals(draggableBehavior.getOptions().getJavaScriptOptions().toString(), "{}");
		draggableBehavior.setAddClasses(true);
		assertEquals(draggableBehavior.getOptions().getJavaScriptOptions().toString(),
			"{addClasses: true}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getRevert()}.
	 */
	@Test
	public void testGetRevert()
	{
		assertNotNull(draggableBehavior.getRevert());
		assertEquals(draggableBehavior.getRevert().getJavascriptOption().toString(), "false");
		draggableBehavior.setRevert(new DraggableRevert(RevertEnum.INVALID));
		assertEquals(draggableBehavior.getRevert().getJavascriptOption().toString(), "'invalid'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getRevertDuration()} .
	 */
	@Test
	public void testGetRevertDuration()
	{
		assertEquals(draggableBehavior.getRevertDuration(), 500);
		draggableBehavior.setRevertDuration(100);
		assertEquals(draggableBehavior.getRevertDuration(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getScope()}.
	 */
	@Test
	public void testGetScope()
	{
		assertEquals(draggableBehavior.getScope(), "default");
		draggableBehavior.setScope("aValue");
		assertEquals(draggableBehavior.getScope(), "aValue");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getScrollSensitivity()} .
	 */
	@Test
	public void testGetScrollSensitivity()
	{
		assertEquals(draggableBehavior.getScrollSensitivity(), 20);
		draggableBehavior.setScrollSensitivity(100);
		assertEquals(draggableBehavior.getScrollSensitivity(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getScrollSpeed()} .
	 */
	@Test
	public void testGetScrollSpeed()
	{
		assertEquals(draggableBehavior.getScrollSpeed(), 20);
		draggableBehavior.setScrollSpeed(100);
		assertEquals(draggableBehavior.getScrollSpeed(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getSnap()}
	 * .
	 */
	@Test
	public void testGetSnap()
	{
		assertNotNull(draggableBehavior.getSnap());
		assertEquals(draggableBehavior.getSnap().getJavascriptOption().toString(), "false");
		draggableBehavior.setSnap(new DraggableSnap(true));
		assertEquals(draggableBehavior.getSnap().getJavascriptOption().toString(), "true");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getSnapMode()}.
	 */
	@Test
	public void testGetSnapMode()
	{
		assertEquals(draggableBehavior.getSnapMode(), SnapModeEnum.BOTH);
		draggableBehavior.setSnapMode(SnapModeEnum.INNER);
		assertEquals(draggableBehavior.getSnapMode(), SnapModeEnum.INNER);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getSnapTolerance()} .
	 */
	@Test
	public void testGetSnapTolerance()
	{
		assertEquals(draggableBehavior.getSnapTolerance(), 20);
		draggableBehavior.setSnapTolerance(100);
		assertEquals(draggableBehavior.getSnapTolerance(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getStack()}.
	 */
	@Test
	public void testgetStack()
	{
		assertNull(draggableBehavior.getStack());
		draggableBehavior.setStack("group");
		assertEquals(draggableBehavior.getStack(), "group");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#getZIndex()}.
	 */
	@Test
	public void testGetZIndex()
	{
		assertEquals(draggableBehavior.getZIndex(), 0);
		draggableBehavior.setZIndex(100);
		assertEquals(draggableBehavior.getZIndex(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isAddClasses()}.
	 */
	@Test
	public void testIsAddClasses()
	{
		assertTrue(draggableBehavior.isAddClasses());
		draggableBehavior.setAddClasses(false);
		assertFalse(draggableBehavior.isAddClasses());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(draggableBehavior.isDisabled());
		draggableBehavior.setDisabled(true);
		assertTrue(draggableBehavior.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isRefreshPositions()} .
	 */
	@Test
	public void testIsRefreshPositions()
	{
		assertFalse(draggableBehavior.isRefreshPositions());
		draggableBehavior.setRefreshPositions(true);
		assertTrue(draggableBehavior.isRefreshPositions());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#isScroll()}.
	 */
	@Test
	public void testIsScroll()
	{
		assertTrue(draggableBehavior.isScroll());
		draggableBehavior.setScroll(false);
		assertFalse(draggableBehavior.isScroll());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#setDragEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetDragEvent()
	{
		assertEquals(draggableBehavior.statement().render().toString(), "$('#anId').draggable({});");
		draggableBehavior.setDragEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(draggableBehavior.statement().render().toString(),
			"$('#anId').draggable({drag: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStartEvent()
	{
		assertEquals(draggableBehavior.statement().render().toString(), "$('#anId').draggable({});");
		draggableBehavior.setStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(draggableBehavior.statement().render().toString(),
			"$('#anId').draggable({start: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStopEvent()
	{
		assertEquals(draggableBehavior.statement().render().toString(), "$('#anId').draggable({});");
		draggableBehavior.setStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertEquals(draggableBehavior.statement().render().toString(),
			"$('#anId').draggable({stop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#statement()}.
	 */
	@Test
	public void testStatement()
	{
		assertNotNull(draggableBehavior.statement());
		assertEquals(draggableBehavior.statement().render().toString(), "$('#anId').draggable({});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.draggable.DraggableBehavior#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(draggableBehavior.widget());
		assertEquals(draggableBehavior.widget().render().toString(),
			"$('#anId').draggable('widget');");
	}
}
