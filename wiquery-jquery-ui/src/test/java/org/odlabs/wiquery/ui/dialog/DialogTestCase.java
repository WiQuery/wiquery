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

import static org.junit.Assert.*;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.dialog.Dialog.WindowPosition;

/**
 * Test on {@link Dialog}
 * 
 * @author Julien Roche
 */
public class DialogTestCase extends WiQueryTestCase
{
	// Properties
	private Dialog dialog;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		Panel panel = new DivTestPanel("panelId");
		dialog = new Dialog("anId");
		dialog.setMarkupId(dialog.getId());
		panel.add(dialog);
		tester.startComponent(panel);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#close()}.
	 */
	@Test
	public void testClose()
	{
		assertNotNull(dialog.close());
		assertEquals(dialog.close().render().toString(), "$('#anId').dialog('close');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(dialog.destroy());
		assertEquals(dialog.destroy().render().toString(), "$('#anId').dialog('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#disable()}.
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(dialog.disable());
		assertEquals(dialog.disable().render().toString(), "$('#anId').dialog('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(dialog.enable());
		assertEquals(dialog.enable().render().toString(), "$('#anId').dialog('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getButtons()}.
	 */
	@Test
	public void testGetButtons()
	{
		assertNull(dialog.getButtons());
		ListItemOptions<DialogButton> list = new ListItemOptions<DialogButton>();
		list.add(new DialogButton("a title", JsScope.quickScope("alert('a test');")));
		dialog.setButtons(list);
		assertNotNull(dialog.getButtons());
		assertEquals(dialog.getButtons().getJavascriptOption().toString(),
			"{'a title':function() {\n\talert('a test');\n}}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getCloseText()}.
	 */
	@Test
	public void testGetCloseText()
	{
		assertEquals(dialog.getCloseText(), "close");
		dialog.setCloseText("a text");
		assertEquals(dialog.getCloseText(), "a text");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getCssClass()} .
	 */
	@Test
	public void testGetCssClass()
	{
		assertEquals(dialog.getCssClass(), "*");
		dialog.setCssClass(".aClass");
		assertEquals(dialog.getCssClass(), ".aClass");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getDialogClass()}.
	 */
	@Test
	public void testGetDialogClass()
	{
		assertEquals(dialog.getDialogClass(), "");
		dialog.setDialogClass("myClass");
		assertEquals(dialog.getDialogClass(), "myClass");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getHeight()}.
	 */
	@Test
	public void testGetHeight()
	{
		assertEquals(dialog.getHeight(), 0);
		dialog.setHeight(5);
		assertEquals(dialog.getHeight(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getHideEffect()}.
	 */
	@Test
	public void testGetHideEffect()
	{
		assertNull(dialog.getHideEffect());
		dialog.setHideEffect("fold");
		assertEquals(dialog.getHideEffect(), "fold");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMaxHeight()}.
	 */
	@Test
	public void testGetMaxHeight()
	{
		assertEquals(dialog.getMaxHeight(), 0);
		dialog.setMaxHeight(5);
		assertEquals(dialog.getMaxHeight(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMaxWidth()} .
	 */
	@Test
	public void testGetMaxWidth()
	{
		assertEquals(dialog.getMaxWidth(), 0);
		dialog.setMaxWidth(5);
		assertEquals(dialog.getMaxWidth(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMinHeight()}.
	 */
	@Test
	public void testGetMinHeight()
	{
		assertEquals(dialog.getMinHeight(), 150);
		dialog.setMinHeight(5);
		assertEquals(dialog.getMinHeight(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getMinWidth()} .
	 */
	@Test
	public void testGetMinWidth()
	{
		assertEquals(dialog.getMinWidth(), 150);
		dialog.setMinWidth(5);
		assertEquals(dialog.getMinWidth(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(dialog.getOptions());
		assertEquals(dialog.getOptions().getJavaScriptOptions().toString(),
			"{autoOpen: false, position: 'center'}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getPosition()} .
	 */
	@Test
	public void testGetPosition()
	{
		assertEquals(dialog.getPosition(), WindowPosition.CENTER);
		dialog.setPosition(WindowPosition.TOP);
		assertEquals(dialog.getPosition(), WindowPosition.TOP);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getShowEffect()}.
	 */
	@Test
	public void testGetShowEffect()
	{
		assertNull(dialog.getShowEffect());
		dialog.setShowEffect("fold");
		assertEquals(dialog.getShowEffect(), "fold");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getTitle()}.
	 */
	@Test
	public void testGetTitle()
	{
		assertEquals(dialog.getTitle(), "");
		dialog.setTitle("a title");
		assertEquals(dialog.getTitle(), "a title");

		dialog.setTitle(new Model<String>("a title2"));
		assertEquals(dialog.getTitle(), "a title2");

		DialogTestPage page = tester.startPage(DialogTestPage.class);
		assertEquals(page.getDialog().getTitle(), "This is a title");
	}

	@Test
	public void testAjaxButton()
	{

		AjaxDialogTestPage page = tester.startPage(AjaxDialogTestPage.class);
		String realValue = page.getOk().getJavascriptOption().toString();
		String expectedOk =
			"'Ok':function (event,ui) {\n"
				+ "var attrs = {\"u\":\"./wicket/page?0-1.IBehaviorListener.0-dialog\","
				+ "\"c\":\"dialog1\"};\nvar params = {'eventName': 'Ok'};\n"
				+ "attrs.ep = params;\nWicket.Ajax.ajax(attrs);\n}\n";
		assertEquals(expectedOk, realValue);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getWidth()}.
	 */
	@Test
	public void testGetWidth()
	{
		assertEquals(dialog.getWidth(), 300);
		dialog.setWidth(5);
		assertEquals(dialog.getWidth(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#getZIndex()}.
	 */
	@Test
	public void testGetZIndex()
	{
		assertEquals(dialog.getZIndex(), 1000);
		dialog.setZIndex(5);
		assertEquals(dialog.getZIndex(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isAutoOpen()}.
	 */
	@Test
	public void testIsAutoOpen()
	{
		assertFalse(dialog.isAutoOpen());
		dialog.setAutoOpen(true);
		assertTrue(dialog.isAutoOpen());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isCloseOnEscape()}.
	 */
	@Test
	public void testIsCloseOnEscape()
	{
		assertTrue(dialog.isCloseOnEscape());
		dialog.setCloseOnEscape(false);
		assertFalse(dialog.isCloseOnEscape());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(dialog.isDisabled());
		dialog.setDisabled(true);
		assertTrue(dialog.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isDraggable()} .
	 */
	@Test
	public void testIsDraggable()
	{
		assertTrue(dialog.isDraggable());
		dialog.setDraggable(false);
		assertFalse(dialog.isDraggable());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isModal()}.
	 */
	@Test
	public void testIsModal()
	{
		assertFalse(dialog.isModal());
		dialog.setModal(true);
		assertTrue(dialog.isModal());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isOpen()}.
	 */
	@Test
	public void testIsOpen()
	{
		assertNotNull(dialog.isOpen());
		assertEquals(dialog.isOpen().render().toString(), "$('#anId').dialog('isOpen');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isResizable()} .
	 */
	@Test
	public void testIsResizable()
	{
		assertTrue(dialog.isResizable());
		dialog.setResizable(false);
		assertFalse(dialog.isResizable());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#isStack()}.
	 */
	@Test
	public void testIsStack()
	{
		assertTrue(dialog.isStack());
		dialog.setStack(false);
		assertFalse(dialog.isStack());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#moveToTop()}.
	 */
	@Test
	public void testMoveToTop()
	{
		assertNotNull(dialog.moveToTop());
		assertEquals(dialog.moveToTop().render().toString(), "$('#anId').dialog('moveToTop');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#open()}.
	 */
	@Test
	public void testOpen()
	{
		assertNotNull(dialog.open());
		assertEquals(dialog.open().render().toString(), "$('#anId').dialog('open');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.dialog.Dialog#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(dialog.widget());
		assertEquals(dialog.widget().render().toString(), "$('#anId').dialog('widget');");
	}
}
