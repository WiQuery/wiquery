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
package org.odlabs.wiquery.ui.resizable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.resizable.ResizableContainment.ElementEnum;

/**
 * Test on {@link ResizableBehavior}
 * 
 * @author Julien Roche
 */
public class ResizableBehaviorTestCase extends WiQueryTestCase {
	// Properties
	private ResizableBehavior resizableBehavior;

	@Override
	@Before
	public void setUp() {
		super.setUp();

		resizableBehavior = new ResizableBehavior();

		tester.startPanel(new ITestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				Panel panel = new DivTestPanel(panelId);
				WebMarkupContainer component = new WebMarkupContainer("anId");
				component.setMarkupId("anId");
				component.add(resizableBehavior);
				panel.add(component);
				return panel;
			}
		});
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		assertNotNull(resizableBehavior.destroy());
		assertEquals(resizableBehavior.destroy().render().toString(),
				"$('#anId').resizable('destroy');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		assertNotNull(resizableBehavior.disable());
		assertEquals(resizableBehavior.disable().render().toString(),
				"$('#anId').resizable('disable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		assertNotNull(resizableBehavior.enable());
		assertEquals(resizableBehavior.enable().render().toString(),
				"$('#anId').resizable('enable');");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAlsoResizeComplex()}
	 * .
	 */
	@Test
	public void testGetAlsoResizeComplex() {
		assertNull(resizableBehavior.getAlsoResizeComplex());
		resizableBehavior.setAlsoResize(new ResizableAlsoResize(
				new LiteralOption("div")));
		assertNotNull(resizableBehavior.getAlsoResizeComplex());
		assertEquals(resizableBehavior.getAlsoResizeComplex()
				.getJavascriptOption().toString(), "'div'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAnimateEasing()}
	 * .
	 */
	@Test
	public void testGetAnimateEasing() {
		assertEquals(resizableBehavior.getAnimateEasing(), "swing");
		resizableBehavior.setAnimateEasing("slide");
		assertEquals(resizableBehavior.getAnimateEasing(), "slide");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAnimateDuration()}
	 * .
	 */
	@Test
	public void testGetAnimateDuration() {
		assertNotNull(resizableBehavior.getAnimateDuration());
		assertEquals(resizableBehavior.getAnimateDuration()
				.getJavascriptOption().toString(), "'slow'");
		resizableBehavior.setAnimateDuration(new ResizableAnimeDuration(1000));
		assertEquals(resizableBehavior.getAnimateDuration()
				.getJavascriptOption().toString(), "1000");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAspectRatio()}
	 * .
	 */
	@Test
	public void testGetAspectRatio() {
		assertNull(resizableBehavior.getAspectRatio());
		resizableBehavior.setAspectRatio(new ResizableAspectRatio(true));
		assertNotNull(resizableBehavior.getAspectRatio());
		assertEquals(resizableBehavior.getAspectRatio().getJavascriptOption()
				.toString(), "true");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel() {
		assertEquals(resizableBehavior.getCancel(), "input,option");
		resizableBehavior.setCancel("input");
		assertEquals(resizableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getContainment()}
	 * .
	 */
	@Test
	public void testGetContainment() {
		assertNull(resizableBehavior.getContainment());
		resizableBehavior.setContainment(new ResizableContainment(
				ElementEnum.PARENT));
		assertNotNull(resizableBehavior.getContainment());
		assertEquals(resizableBehavior.getContainment().getJavascriptOption()
				.toString(), "'parent'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay() {
		assertEquals(resizableBehavior.getDelay(), 0);
		resizableBehavior.setDelay(5);
		assertEquals(resizableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getDistance()}.
	 */
	@Test
	public void testGetDistance() {
		assertEquals(resizableBehavior.getDistance(), 1);
		resizableBehavior.setDistance(5);
		assertEquals(resizableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getGrid()}.
	 */
	@Test
	public void testGetGrid() {
		assertNull(resizableBehavior.getGrid());
		resizableBehavior.setGrid(5, 6);
		assertNotNull(resizableBehavior.getGrid());
		assertEquals(resizableBehavior.getGrid().getJavascriptOption()
				.toString(), "[5,6]");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getHandles()}.
	 */
	@Test
	public void testGetHandles() {
		assertNotNull(resizableBehavior.getHandles());
		assertEquals(resizableBehavior.getHandles().getJavascriptOption()
				.toString(), "'e,s,se'");
		resizableBehavior.setHandles(new ResizableHandles(new LiteralOption(
				"e,s")));
		assertEquals(resizableBehavior.getHandles().getJavascriptOption()
				.toString(), "'e,s'");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getHelper()}.
	 */
	@Test
	public void testGetHelper() {
		assertNull(resizableBehavior.getHelper());
		resizableBehavior.setHelper(".aClass");
		assertEquals(resizableBehavior.getHelper(), ".aClass");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMaxHeight()}.
	 */
	@Test
	public void testGetMaxHeight() {
		assertEquals(resizableBehavior.getMaxHeight(), 0);
		resizableBehavior.setMaxHeight(100);
		assertEquals(resizableBehavior.getMaxHeight(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMaxWidth()}.
	 */
	@Test
	public void testGetMaxWidth() {
		assertEquals(resizableBehavior.getMaxWidth(), 0);
		resizableBehavior.setMaxWidth(100);
		assertEquals(resizableBehavior.getMaxWidth(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMinHeight()}.
	 */
	@Test
	public void testGetMinHeight() {
		assertEquals(resizableBehavior.getMinHeight(), 10);
		resizableBehavior.setMinHeight(100);
		assertEquals(resizableBehavior.getMinHeight(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMinWidth()}.
	 */
	@Test
	public void testGetMinWidth() {
		assertEquals(resizableBehavior.getMinWidth(), 10);
		resizableBehavior.setMinWidth(100);
		assertEquals(resizableBehavior.getMinWidth(), 100);
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		assertNotNull(resizableBehavior.getOptions());
		assertEquals(resizableBehavior.getOptions().getJavaScriptOptions()
				.toString(), "{}");
		resizableBehavior.setAnimate(true);
		assertEquals(resizableBehavior.getOptions().getJavaScriptOptions()
				.toString(), "{animate: true}");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isAnimate()}.
	 */
	@Test
	public void testIsAnimate() {
		assertFalse(resizableBehavior.isAnimate());
		resizableBehavior.setAnimate(true);
		assertTrue(resizableBehavior.isAnimate());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isAutoHide()}.
	 */
	@Test
	public void testIsAutoHide() {
		assertFalse(resizableBehavior.isAutoHide());
		resizableBehavior.setAutoHide(true);
		assertTrue(resizableBehavior.isAutoHide());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		assertFalse(resizableBehavior.isDisabled());
		resizableBehavior.setDisabled(true);
		assertTrue(resizableBehavior.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isGhost()}.
	 */
	@Test
	public void testIsGhost() {
		assertFalse(resizableBehavior.isGhost());
		resizableBehavior.setGhost(true);
		assertTrue(resizableBehavior.isGhost());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#setResizeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetResizeEvent() {
		assertEquals(resizableBehavior.statement().render().toString(),
				"$('#anId').resizable({});");
		resizableBehavior.setResizeEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(resizableBehavior.statement().render().toString(),
				"$('#anId').resizable({resize: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStartEvent() {
		assertEquals(resizableBehavior.statement().render().toString(),
				"$('#anId').resizable({});");
		resizableBehavior.setStartEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(resizableBehavior.statement().render().toString(),
				"$('#anId').resizable({start: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStopEvent() {
		assertEquals(resizableBehavior.statement().render().toString(),
				"$('#anId').resizable({});");
		resizableBehavior.setStopEvent(JsScopeUiEvent
				.quickScope("alert('event');"));
		assertEquals(resizableBehavior.statement().render().toString(),
				"$('#anId').resizable({stop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		assertNotNull(resizableBehavior.statement());
		assertEquals(resizableBehavior.statement().render().toString(),
				"$('#anId').resizable({});");
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		assertNotNull(resizableBehavior.widget());
		assertEquals(resizableBehavior.widget().render().toString(),
				"$('#anId').resizable('widget');");
	}
}
