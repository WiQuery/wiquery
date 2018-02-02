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
package org.wicketstuff.wiquery.ui.dialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.junit.Before;
import org.junit.Test;
import org.wicketstuff.wiquery.core.javascript.JsScope;
import org.wicketstuff.wiquery.core.options.ArrayItemOptions;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;
import org.wicketstuff.wiquery.ui.DivTestPanel;
import org.wicketstuff.wiquery.ui.options.EffectOptionObject;
import org.wicketstuff.wiquery.ui.position.PositionAlignmentOptions;
import org.wicketstuff.wiquery.ui.position.PositionOptions;
import org.wicketstuff.wiquery.ui.position.PositionRelation;

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
		tester.startComponentInPage(panel);
	}
	
	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getAppendTo()}.
	 */
	@Test
	public void testGetAppendTo()
	{
		assertEquals(dialog.getAppendTo(), "body");
		dialog.setAppendTo("html > div.myClasss");
		assertEquals(dialog.getAppendTo(), "html > div.myClasss");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getButtons()}.
	 */
	@Test
	public void testGetButtons()
	{
		assertNull(dialog.getButtons());
		ArrayItemOptions<DialogButton> array = new ArrayItemOptions<DialogButton>();
		array.add(new DialogButton("a title", JsScope.quickScope("alert('a test');")));
		dialog.setButtons(array);
		assertNotNull(dialog.getButtons());
		assertEquals(dialog.getButtons().getJavascriptOption().toString(),
			"[{text: 'a title', click: function() {\n\talert('a test');\n}}]");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getCloseText()}.
	 */
	@Test
	public void testGetCloseText()
	{
		assertEquals(dialog.getCloseText(), "close");
		dialog.setCloseText("a text");
		assertEquals(dialog.getCloseText(), "a text");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getDialogClass()}.
	 */
	@Test
	public void testGetDialogClass()
	{
		assertEquals(dialog.getDialogClass(), "");
		dialog.setDialogClass("myClass");
		assertEquals(dialog.getDialogClass(), "myClass");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getHeight()}.
	 */
	@Test
	public void testGetHeight()
	{
		assertEquals(dialog.getHeight(), 0);
		dialog.setHeight(5);
		assertEquals(dialog.getHeight(), 5);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getHideEffect()}.
	 */
	@Test
	public void testGetHide()
	{
		assertNull(dialog.getHide());
		dialog.setHide(new DialogAnimateOption(200));
		assertEquals(dialog.getHide().getJavascriptOption().toString(), "200");
		dialog.setHide(new DialogAnimateOption("fold"));
		assertEquals(dialog.getHide().getJavascriptOption().toString(), "'fold'");
		dialog.setHide(new DialogAnimateOption(new EffectOptionObject()
				.setEffect("blind")
				.setDuration(200)
				.setEasing("linear")
		));
		assertEquals(dialog.getHide().getJavascriptOption().toString(),
				"{effect: 'blind', duration: 200, easing: 'linear'}");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getMaxHeight()}.
	 */
	@Test
	public void testGetMaxHeight()
	{
		assertEquals(dialog.getMaxHeight(), 0);
		dialog.setMaxHeight(5);
		assertEquals(dialog.getMaxHeight(), 5);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getMaxWidth()} .
	 */
	@Test
	public void testGetMaxWidth()
	{
		assertEquals(dialog.getMaxWidth(), 0);
		dialog.setMaxWidth(5);
		assertEquals(dialog.getMaxWidth(), 5);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getMinHeight()}.
	 */
	@Test
	public void testGetMinHeight()
	{
		assertEquals(dialog.getMinHeight(), 150);
		dialog.setMinHeight(5);
		assertEquals(dialog.getMinHeight(), 5);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getMinWidth()} .
	 */
	@Test
	public void testGetMinWidth()
	{
		assertEquals(dialog.getMinWidth(), 150);
		dialog.setMinWidth(5);
		assertEquals(dialog.getMinWidth(), 5);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(dialog.getOptions());
		assertEquals(dialog.getOptions().getJavaScriptOptions().toString(),
			"{autoOpen: false}");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getPosition()} .
	 */
	@Test
	public void testGetPosition()
	{
		assertEquals(dialog.getPosition().getJavascriptOption().toString(),
				"{my: 'center', at: 'center', of: 'window'}");
		dialog.setPosition(new PositionOptions()
				.setMy(new PositionAlignmentOptions(PositionRelation.RIGHT))
				.setAt(new PositionAlignmentOptions(PositionRelation.CENTER, 10, PositionRelation.BOTTOM, -10))
				.setOf("#someId")
		);
		assertEquals(dialog.getPosition().getJavascriptOption().toString(),
				"{my: 'right', at: 'center+10 bottom-10', of: '#someId'}");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getShowEffect()}.
	 */
	@Test
	public void testGetShow()
	{
		assertNull(dialog.getShow());
		dialog.setShow(new DialogAnimateOption(200));
		assertEquals(dialog.getShow().getJavascriptOption().toString(), "200");
		dialog.setShow(new DialogAnimateOption("fold"));
		assertEquals(dialog.getShow().getJavascriptOption().toString(), "'fold'");
		dialog.setShow(new DialogAnimateOption(new EffectOptionObject()
				.setEffect("blind")
				.setDuration(200)
				.setEasing("linear")
		));
		assertEquals(dialog.getShow().getJavascriptOption().toString(),
				"{effect: 'blind', duration: 200, easing: 'linear'}");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getTitle()}.
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
			"{text: 'Ok', click: function (event,ui) {\n"
					+ "var attrs = {\"u\":\"./wicket/bookmarkable/org.wicketstuff.wiquery.ui.dialog.AjaxDialogTestPage?1-1.0-dialog\",\"c\":\"dialog1\"};\n"
					+ "var params = [{\"name\":\"eventName\",\"value\":'Ok'}];\n" + "attrs.ep = params.concat(attrs.ep || []);\n"
					+ "Wicket.Ajax.ajax(attrs);\n" + "}\n" + "}";
		assertEquals(expectedOk, realValue);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#getWidth()}.
	 */
	@Test
	public void testGetWidth()
	{
		assertEquals(dialog.getWidth(), 300);
		dialog.setWidth(5);
		assertEquals(dialog.getWidth(), 5);
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#isAutoOpen()}.
	 */
	@Test
	public void testIsAutoOpen()
	{
		assertFalse(dialog.isAutoOpen());
		dialog.setAutoOpen(true);
		assertTrue(dialog.isAutoOpen());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#isCloseOnEscape()}.
	 */
	@Test
	public void testIsCloseOnEscape()
	{
		assertTrue(dialog.isCloseOnEscape());
		dialog.setCloseOnEscape(false);
		assertFalse(dialog.isCloseOnEscape());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(dialog.isDisabled());
		dialog.setDisabled(true);
		assertTrue(dialog.isDisabled());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#isDraggable()} .
	 */
	@Test
	public void testIsDraggable()
	{
		assertTrue(dialog.isDraggable());
		dialog.setDraggable(false);
		assertFalse(dialog.isDraggable());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#isModal()}.
	 */
	@Test
	public void testIsModal()
	{
		assertFalse(dialog.isModal());
		dialog.setModal(true);
		assertTrue(dialog.isModal());
	}
	
	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#isResizable()} .
	 */
	@Test
	public void testIsResizable()
	{
		assertTrue(dialog.isResizable());
		dialog.setResizable(false);
		assertFalse(dialog.isResizable());
	}
	

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#close()}.
	 */
	@Test
	public void testClose()
	{
		assertNotNull(dialog.close());
		assertEquals(dialog.close().render().toString(), "$('#anId').dialog('close');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(dialog.destroy());
		assertEquals(dialog.destroy().render().toString(), "$('#anId').dialog('destroy');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#isOpen()}.
	 */
	@Test
	public void testIsOpen()
	{
		assertNotNull(dialog.isOpen());
		assertEquals(dialog.isOpen().render().toString(), "$('#anId').dialog('isOpen');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#moveToTop()}.
	 */
	@Test
	public void testMoveToTop()
	{
		assertNotNull(dialog.moveToTop());
		assertEquals(dialog.moveToTop().render().toString(), "$('#anId').dialog('moveToTop');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#open()}.
	 */
	@Test
	public void testOpen()
	{
		assertNotNull(dialog.open());
		assertEquals(dialog.open().render().toString(), "$('#anId').dialog('open');");
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.ui.dialog.Dialog#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(dialog.widget());
		assertEquals(dialog.widget().render().toString(), "$('#anId').dialog('widget');");
	}
}
