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
package org.odlabs.wiquery.ui.dialog;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.dialog.Dialog.WindowPosition;
import org.odlabs.wiquery.utils.WiQueryWebApplication;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test on {@link Dialog}
 * @author Julien Roche
 *
 */
public class DialogTestCase extends TestCase {
	// Properties
	private Dialog dialog;

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
		
		dialog = new Dialog("anId");
		dialog.setMarkupId(dialog.getId());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#close()}.
	 */
	@Test
	public void testClose() {
		Assert.assertNotNull(dialog.close());
		Assert.assertEquals(dialog.close().render().toString(), 
				"$('#anId').dialog('close');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(dialog.destroy());
		Assert.assertEquals(dialog.destroy().render().toString(), 
				"$('#anId').dialog('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(dialog.disable());
		Assert.assertEquals(dialog.disable().render().toString(), 
				"$('#anId').dialog('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(dialog.enable());
		Assert.assertEquals(dialog.enable().render().toString(), 
				"$('#anId').dialog('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getButtons()}.
	 */
	@Test
	public void testGetButtons() {
		Assert.assertNull(dialog.getButtons());
		ListItemOptions<DialogButton> list = new ListItemOptions<DialogButton>();
		list.add(new DialogButton("a title", JsScope.quickScope("alert('a test');")));
		dialog.setButtons(list);
		Assert.assertNotNull(dialog.getButtons());
		Assert.assertEquals(dialog.getButtons().getJavascriptOption().toString(), 
				"{'a title':function() {\n\talert('a test');\n}}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getCloseText()}.
	 */
	@Test
	public void testGetCloseText() {
		Assert.assertEquals(dialog.getCloseText(), "close");
		dialog.setCloseText("a text");
		Assert.assertEquals(dialog.getCloseText(), "a text");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getCssClass()}.
	 */
	@Test
	public void testGetCssClass() {
		Assert.assertEquals(dialog.getCssClass(), "*");
		dialog.setCssClass(".aClass");
		Assert.assertEquals(dialog.getCssClass(), ".aClass");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getHeight()}.
	 */
	@Test
	public void testGetHeight() {
		Assert.assertEquals(dialog.getHeight(), 0);
		dialog.setHeight(5);
		Assert.assertEquals(dialog.getHeight(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getHideEffect()}.
	 */
	@Test
	public void testGetHideEffect() {
		Assert.assertNull(dialog.getHideEffect());
		dialog.setHideEffect("fold");
		Assert.assertEquals(dialog.getHideEffect(), "fold");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMaxHeight()}.
	 */
	@Test
	public void testGetMaxHeight() {
		Assert.assertEquals(dialog.getMaxHeight(), 0);
		dialog.setMaxHeight(5);
		Assert.assertEquals(dialog.getMaxHeight(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMaxWidth()}.
	 */
	@Test
	public void testGetMaxWidth() {
		Assert.assertEquals(dialog.getMaxWidth(), 0);
		dialog.setMaxWidth(5);
		Assert.assertEquals(dialog.getMaxWidth(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMinHeight()}.
	 */
	@Test
	public void testGetMinHeight() {
		Assert.assertEquals(dialog.getMinHeight(), 150);
		dialog.setMinHeight(5);
		Assert.assertEquals(dialog.getMinHeight(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMinWidth()}.
	 */
	@Test
	public void testGetMinWidth() {
		Assert.assertEquals(dialog.getMinWidth(), 150);
		dialog.setMinWidth(5);
		Assert.assertEquals(dialog.getMinWidth(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(dialog.getOptions());
		Assert.assertEquals(dialog.getOptions().getJavaScriptOptions().toString(), "{autoOpen: false, position: 'center'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getPosition()}.
	 */
	@Test
	public void testGetPosition() {
		Assert.assertEquals(dialog.getPosition(), WindowPosition.CENTER);
		dialog.setPosition(WindowPosition.TOP);
		Assert.assertEquals(dialog.getPosition(), WindowPosition.TOP);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getShowEffect()}.
	 */
	@Test
	public void testGetShowEffect() {
		Assert.assertNull(dialog.getShowEffect());
		dialog.setShowEffect("fold");
		Assert.assertEquals(dialog.getShowEffect(), "fold");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		Assert.assertEquals(dialog.getTitle(), "");
		dialog.setTitle("a title");
		Assert.assertEquals(dialog.getTitle(), "a title");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getWidth()}.
	 */
	@Test
	public void testGetWidth() {
		Assert.assertEquals(dialog.getWidth(), 300);
		dialog.setWidth(5);
		Assert.assertEquals(dialog.getWidth(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getZIndex()}.
	 */
	@Test
	public void testGetZIndex() {
		Assert.assertEquals(dialog.getZIndex(), 1000);
		dialog.setZIndex(5);
		Assert.assertEquals(dialog.getZIndex(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isAutoOpen()}.
	 */
	@Test
	public void testIsAutoOpen() {
		Assert.assertFalse(dialog.isAutoOpen());
		dialog.setAutoOpen(true);
		Assert.assertTrue(dialog.isAutoOpen());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isBgiframe()}.
	 */
	@Test
	public void testIsBgiframe() {
		Assert.assertFalse(dialog.isBgiframe());
		dialog.setBgiframe(true);
		Assert.assertTrue(dialog.isBgiframe());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isCloseOnEscape()}.
	 */
	@Test
	public void testIsCloseOnEscape() {
		Assert.assertTrue(dialog.isCloseOnEscape());
		dialog.setCloseOnEscape(false);
		Assert.assertFalse(dialog.isCloseOnEscape());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isDraggable()}.
	 */
	@Test
	public void testIsDraggable() {
		Assert.assertTrue(dialog.isDraggable());
		dialog.setDraggable(false);
		Assert.assertFalse(dialog.isDraggable());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isModal()}.
	 */
	@Test
	public void testIsModal() {
		Assert.assertFalse(dialog.isModal());
		dialog.setModal(true);
		Assert.assertTrue(dialog.isModal());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isOpen()}.
	 */
	@Test
	public void testIsOpen() {
		Assert.assertNotNull(dialog.isOpen());
		Assert.assertEquals(dialog.isOpen().render().toString(), 
				"$('#anId').dialog('isOpen');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isResizable()}.
	 */
	@Test
	public void testIsResizable() {
		Assert.assertTrue(dialog.isResizable());
		dialog.setResizable(false);
		Assert.assertFalse(dialog.isResizable());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isStack()}.
	 */
	@Test
	public void testIsStack() {
		Assert.assertTrue(dialog.isStack());
		dialog.setStack(false);
		Assert.assertFalse(dialog.isStack());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#moveToTop()}.
	 */
	@Test
	public void testMoveToTop() {
		Assert.assertNotNull(dialog.moveToTop());
		Assert.assertEquals(dialog.moveToTop().render().toString(), 
				"$('#anId').dialog('moveToTop');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#open()}.
	 */
	@Test
	public void testOpen() {
		Assert.assertNotNull(dialog.open());
		Assert.assertEquals(dialog.open().render().toString(), 
				"$('#anId').dialog('open');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setBeforeCloseEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetBeforeCloseEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setBeforeCloseEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains( 
			"beforeclose: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setCloseEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetCloseEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setCloseEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains(  
			"close: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setDragEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDragEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setDragEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains(  
			"drag: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setDragStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDragStartEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setDragStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains(  
			"dragStart: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setDragStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetDragStopEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setDragStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains(  
			"dragStop: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setFocusEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetFocusEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setFocusEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains( 
			"focus: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setOpenEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetOpenEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setOpenEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains( 
			"open: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setResizeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetResizeEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setResizeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains( 
			"resize: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setResizeStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetResizeStartEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setResizeStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains(  
			"resizeStart: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#setResizeStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetResizeStopEvent() {
		Assert.assertEquals(dialog.statement().render().toString(), 
			"$('#anId').dialog({autoOpen: false, position: 'center'});");
		dialog.setResizeStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(dialog.statement().render().toString().contains(  
			"resizeStop: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(dialog.statement());
		Assert.assertEquals(dialog.statement().render().toString(), "$('#anId').dialog({autoOpen: false, position: 'center'});");
	}
}
