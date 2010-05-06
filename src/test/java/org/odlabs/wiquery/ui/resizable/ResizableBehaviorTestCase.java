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

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.resizable.ResizableContainment.ElementEnum;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link ResizableBehavior}
 * @author Julien Roche
 *
 */
public class ResizableBehaviorTestCase extends TestCase {
	// Properties
	private ResizableBehavior resizableBehavior;

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
		
		resizableBehavior = new ResizableBehavior();
		
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		component.add(resizableBehavior);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(resizableBehavior.destroy());
		Assert.assertEquals(resizableBehavior.destroy().render().toString(), 
				"$('#anId').resizable('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(resizableBehavior.disable());
		Assert.assertEquals(resizableBehavior.disable().render().toString(), 
				"$('#anId').resizable('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(resizableBehavior.enable());
		Assert.assertEquals(resizableBehavior.enable().render().toString(), 
				"$('#anId').resizable('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAlsoResizeComplex()}.
	 */
	@Test
	public void testGetAlsoResizeComplex() {
		Assert.assertNull(resizableBehavior.getAlsoResizeComplex());
		resizableBehavior.setAlsoResize(new ResizableAlsoResize(new LiteralOption("div")));
		Assert.assertNotNull(resizableBehavior.getAlsoResizeComplex());
		Assert.assertEquals(resizableBehavior.getAlsoResizeComplex().getJavascriptOption().toString(), 
				"'div'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAnimateEasing()}.
	 */
	@Test
	public void testGetAnimateEasing() {
		Assert.assertEquals(resizableBehavior.getAnimateEasing(), "swing");
		resizableBehavior.setAnimateEasing("slide");
		Assert.assertEquals(resizableBehavior.getAnimateEasing(), "slide");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAnimateDuration()}.
	 */
	@Test
	public void testGetAnimateDuration() {
		Assert.assertNotNull(resizableBehavior.getAnimateDuration());
		Assert.assertEquals(resizableBehavior.getAnimateDuration().getJavascriptOption().toString(), 
				"'slow'");
		resizableBehavior.setAnimateDuration(new ResizableAnimeDuration(1000));
		Assert.assertEquals(resizableBehavior.getAnimateDuration().getJavascriptOption().toString(), 
			"1000");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getAspectRatio()}.
	 */
	@Test
	public void testGetAspectRatio() {
		Assert.assertNull(resizableBehavior.getAspectRatio());
		resizableBehavior.setAspectRatio(new ResizableAspectRatio(true));
		Assert.assertNotNull(resizableBehavior.getAspectRatio());
		Assert.assertEquals(resizableBehavior.getAspectRatio().getJavascriptOption().toString(), "true");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getCancel()}.
	 */
	@Test
	public void testGetCancel() {
		Assert.assertEquals(resizableBehavior.getCancel(), "input,option");
		resizableBehavior.setCancel("input");
		Assert.assertEquals(resizableBehavior.getCancel(), "input");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getContainment()}.
	 */
	@Test
	public void testGetContainment() {
		Assert.assertNull(resizableBehavior.getContainment());
		resizableBehavior.setContainment(new ResizableContainment(ElementEnum.PARENT));
		Assert.assertNotNull(resizableBehavior.getContainment());
		Assert.assertEquals(resizableBehavior.getContainment().getJavascriptOption().toString(), "'parent'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getDelay()}.
	 */
	@Test
	public void testGetDelay() {
		Assert.assertEquals(resizableBehavior.getDelay(), 0);
		resizableBehavior.setDelay(5);
		Assert.assertEquals(resizableBehavior.getDelay(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getDistance()}.
	 */
	@Test
	public void testGetDistance() {
		Assert.assertEquals(resizableBehavior.getDistance(), 1);
		resizableBehavior.setDistance(5);
		Assert.assertEquals(resizableBehavior.getDistance(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getGrid()}.
	 */
	@Test
	public void testGetGrid() {
		Assert.assertNull(resizableBehavior.getGrid());
		resizableBehavior.setGrid(5, 6);
		Assert.assertNotNull(resizableBehavior.getGrid());
		Assert.assertEquals(resizableBehavior.getGrid().getJavascriptOption().toString(), "[5,6]");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getHandles()}.
	 */
	@Test
	public void testGetHandles() {
		Assert.assertNotNull(resizableBehavior.getHandles());
		Assert.assertEquals(resizableBehavior.getHandles().getJavascriptOption().toString(), "'e,s,se'");
		resizableBehavior.setHandles(new ResizableHandles(new LiteralOption("e,s")));
		Assert.assertEquals(resizableBehavior.getHandles().getJavascriptOption().toString(), "'e,s'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getHelper()}.
	 */
	@Test
	public void testGetHelper() {
		Assert.assertNull(resizableBehavior.getHelper());
		resizableBehavior.setHelper(".aClass");
		Assert.assertEquals(resizableBehavior.getHelper(), ".aClass");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMaxHeight()}.
	 */
	@Test
	public void testGetMaxHeight() {
		Assert.assertEquals(resizableBehavior.getMaxHeight(), 0);
		resizableBehavior.setMaxHeight(100);
		Assert.assertEquals(resizableBehavior.getMaxHeight(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMaxWidth()}.
	 */
	@Test
	public void testGetMaxWidth() {
		Assert.assertEquals(resizableBehavior.getMaxWidth(), 0);
		resizableBehavior.setMaxWidth(100);
		Assert.assertEquals(resizableBehavior.getMaxWidth(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMinHeight()}.
	 */
	@Test
	public void testGetMinHeight() {
		Assert.assertEquals(resizableBehavior.getMinHeight(), 10);
		resizableBehavior.setMinHeight(100);
		Assert.assertEquals(resizableBehavior.getMinHeight(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getMinWidth()}.
	 */
	@Test
	public void testGetMinWidth() {
		Assert.assertEquals(resizableBehavior.getMinWidth(), 10);
		resizableBehavior.setMinWidth(100);
		Assert.assertEquals(resizableBehavior.getMinWidth(), 100);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(resizableBehavior.getOptions());
		Assert.assertEquals(resizableBehavior.getOptions().getJavaScriptOptions().toString(), "{}");
		resizableBehavior.setAnimate(true);
		Assert.assertEquals(resizableBehavior.getOptions().getJavaScriptOptions().toString(), "{animate: true}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isAnimate()}.
	 */
	@Test
	public void testIsAnimate() {
		Assert.assertFalse(resizableBehavior.isAnimate());
		resizableBehavior.setAnimate(true);
		Assert.assertTrue(resizableBehavior.isAnimate());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isAutoHide()}.
	 */
	@Test
	public void testIsAutoHide() {
		Assert.assertFalse(resizableBehavior.isAutoHide());
		resizableBehavior.setAutoHide(true);
		Assert.assertTrue(resizableBehavior.isAutoHide());
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(resizableBehavior.isDisabled());
		resizableBehavior.setDisabled(true);
		Assert.assertTrue(resizableBehavior.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#isGhost()}.
	 */
	@Test
	public void testIsGhost() {
		Assert.assertFalse(resizableBehavior.isGhost());
		resizableBehavior.setGhost(true);
		Assert.assertTrue(resizableBehavior.isGhost());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#setResizeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetResizeEvent() {
		Assert.assertEquals(resizableBehavior.statement().render().toString(), 
			"$('#anId').resizable({});");
		resizableBehavior.setResizeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(resizableBehavior.statement().render().toString(), 
			"$('#anId').resizable({resize: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStartEvent() {
		Assert.assertEquals(resizableBehavior.statement().render().toString(), 
			"$('#anId').resizable({});");
		resizableBehavior.setStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(resizableBehavior.statement().render().toString(), 
			"$('#anId').resizable({start: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStopEvent() {
		Assert.assertEquals(resizableBehavior.statement().render().toString(), 
			"$('#anId').resizable({});");
		resizableBehavior.setStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertEquals(resizableBehavior.statement().render().toString(), 
			"$('#anId').resizable({stop: function(event, ui) {\n\talert('event');\n}});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(resizableBehavior.statement());
		Assert.assertEquals(resizableBehavior.statement().render().toString(), "$('#anId').resizable({});");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.resizable.ResizableBehavior#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(resizableBehavior.widget());
		Assert.assertEquals(resizableBehavior.widget().render().toString(), 
				"$('#anId').resizable('widget');");
	}
}
