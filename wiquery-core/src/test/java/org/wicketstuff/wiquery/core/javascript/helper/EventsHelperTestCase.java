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
package org.wicketstuff.wiquery.core.javascript.helper;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.events.MouseEvent;
import org.wicketstuff.wiquery.core.javascript.JsScope;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Unit test on the {@link EventsHelper}
 * 
 * @author Julien Roche
 */
public class EventsHelperTestCase extends WiQueryTestCase
{
	// Constants
	/** Logger */
	protected static final Logger log = LoggerFactory.getLogger(EventsHelperTestCase.class);

	// Properties
	private JsStatement jsStatement;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		jsStatement = new JsStatement().$(null, "div");
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#bind(org.wicketstuff.wiquery.core.events.EventLabel, org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testBind()
	{
		assertAndLog("$('div').bind('click', function() {\n\talert('click done');\n});",
			jsStatement
				.chain(
					EventsHelper.bind(MouseEvent.CLICK, JsScope.quickScope("alert('click done');")))
				.render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#blur()}.
	 */
	@Test
	public void testBlur()
	{
		assertAndLog("$('div').blur();", jsStatement.chain(EventsHelper.blur()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#blur(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testBlurJsScope()
	{
		assertAndLog("$('div').blur(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.blur(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#change()}.
	 */
	@Test
	public void testChange()
	{
		assertAndLog("$('div').change();", jsStatement.chain(EventsHelper.change()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#change(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testChangeJsScope()
	{
		assertAndLog("$('div').change(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.change(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#click()}.
	 */
	@Test
	public void testClick()
	{
		assertAndLog("$('div').click();", jsStatement.chain(EventsHelper.click()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#click(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testClickJsScope()
	{
		assertAndLog("$('div').click(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.click(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#dblclick()} .
	 */
	@Test
	public void testDblclick()
	{
		assertAndLog("$('div').dblclick();", jsStatement.chain(EventsHelper.dblclick()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#dblclick(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testDblclickJsScope()
	{
		assertAndLog("$('div').dblclick(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.dblclick(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#die(org.wicketstuff.wiquery.core.events.EventLabel, org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testDie()
	{
		assertAndLog("$('div').die('click', function() {\n\talert('click done');\n});",
			jsStatement
				.chain(
					EventsHelper.die(MouseEvent.CLICK, JsScope.quickScope("alert('click done');")))
				.render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#error()}.
	 */
	@Test
	public void testError()
	{
		assertAndLog("$('div').error();", jsStatement.chain(EventsHelper.error()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#error(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testErrorJsScope()
	{
		assertAndLog("$('div').error(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.error(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#focus()}.
	 */
	@Test
	public void testFocus()
	{
		assertAndLog("$('div').focus();", jsStatement.chain(EventsHelper.focus()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#focus(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testFocusJsScope()
	{
		assertAndLog("$('div').focus(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.focus(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#hover(org.wicketstuff.wiquery.core.javascript.JsScope, org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testHover()
	{
		assertAndLog(
			"$('div').hover(function() {\n\talert('over');\n}, function() {\n\talert('out');\n});",
			jsStatement.chain(EventsHelper.hover(JsScope.quickScope("alert('over');"),
				JsScope.quickScope("alert('out');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#keydown()}.
	 */
	@Test
	public void testKeydown()
	{
		assertAndLog("$('div').keydown();", jsStatement.chain(EventsHelper.keydown()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#keydown(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testKeydownJsScope()
	{
		assertAndLog("$('div').keydown(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.keydown(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#keypress()} .
	 */
	@Test
	public void testKeypress()
	{
		assertAndLog("$('div').keypress();", jsStatement.chain(EventsHelper.keypress()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#keypress(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testKeypressJsScope()
	{
		assertAndLog("$('div').keypress(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.keypress(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#keyup()}.
	 */
	@Test
	public void testKeyup()
	{
		assertAndLog("$('div').keyup();", jsStatement.chain(EventsHelper.keyup()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#keyup(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testKeyupJsScope()
	{
		assertAndLog("$('div').keyup(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.keyup(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#live(org.wicketstuff.wiquery.core.events.EventLabel, org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testLive()
	{
		assertAndLog("$('div').live('click', function() {\n\talert('click done');\n});",
			jsStatement
				.chain(
					EventsHelper.live(MouseEvent.CLICK, JsScope.quickScope("alert('click done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#load(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testLoad()
	{
		assertAndLog("$('div').load(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.load(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#mousedown(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testMousedown()
	{
		assertAndLog("$('div').mousedown(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.mousedown(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#mouseenter(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testMouseenter()
	{
		assertAndLog("$('div').mouseenter(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.mouseenter(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#mouseleave(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testMouseleave()
	{
		assertAndLog("$('div').mouseleave(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.mouseleave(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#mousemove(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testMousemove()
	{
		assertAndLog("$('div').mousemove(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.mousemove(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#mouseout(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testMouseout()
	{
		assertAndLog("$('div').mouseout(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.mouseout(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#mouseover(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testMouseover()
	{
		assertAndLog("$('div').mouseover(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.mouseover(JsScope.quickScope("alert('done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#mouseup(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testMouseup()
	{
		assertAndLog("$('div').mouseup(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.mouseup(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#one(org.wicketstuff.wiquery.core.events.EventLabel, org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testOne()
	{
		assertAndLog("$('div').one('click', function() {\n\talert('click done');\n});",
			jsStatement
				.chain(
					EventsHelper.one(MouseEvent.CLICK, JsScope.quickScope("alert('click done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#ready(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testReady()
	{
		assertAndLog("$('div').ready(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.ready(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#scroll(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testScroll()
	{
		assertAndLog("$('div').scroll(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.scroll(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#select()}.
	 */
	@Test
	public void testSelect()
	{
		assertAndLog("$('div').select();", jsStatement.chain(EventsHelper.select()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#select(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testSelectJsScope()
	{
		assertAndLog("$('div').select(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.select(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#submit()}.
	 */
	@Test
	public void testSubmit()
	{
		assertAndLog("$('div').submit();", jsStatement.chain(EventsHelper.submit()).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#submit(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testSubmitJsScope()
	{
		assertAndLog("$('div').submit(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.submit(JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#toggle(org.wicketstuff.wiquery.core.javascript.JsScope, org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testToggleJsScopeJsScope()
	{
		assertAndLog(
			"$('div').toggle(function() {\n\talert('a');\n}, function() {\n\talert('b');\n});",
			jsStatement.chain(EventsHelper.toggle(JsScope.quickScope("alert('a');"),
				JsScope.quickScope("alert('b');"))).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#toggle(org.wicketstuff.wiquery.core.javascript.JsScope, org.wicketstuff.wiquery.core.javascript.JsScope, org.wicketstuff.wiquery.core.javascript.JsScope[])}
	 * .
	 */
	@Test
	public void testToggleJsScopeJsScopeJsScopeArray()
	{
		assertAndLog(
			"$('div').toggle(function() {\n\talert('a');\n}, function() {\n\talert('b');\n}, function() {\n\talert('c');\n});",
			jsStatement
				.chain(EventsHelper.toggle(JsScope.quickScope("alert('a');"),
					JsScope.quickScope("alert('b');"), JsScope.quickScope("alert('c');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#trigger(org.wicketstuff.wiquery.core.events.EventLabel)}
	 * .
	 */
	@Test
	public void testTriggerEventLabel()
	{
		assertAndLog("$('div').trigger('click');",
			jsStatement.chain(EventsHelper.trigger(MouseEvent.CLICK)).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#trigger(org.wicketstuff.wiquery.core.events.EventLabel, java.lang.CharSequence[])}
	 * .
	 */
	@Test
	public void testTriggerEventLabelCharSequenceArray()
	{
		assertAndLog("$('div').trigger('click', ['a']);",
			jsStatement.chain(EventsHelper.trigger(MouseEvent.CLICK, "'a'")).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#triggerHandler(org.wicketstuff.wiquery.core.events.EventLabel)}
	 * .
	 */
	@Test
	public void testTriggerHandlerEventLabel()
	{
		assertAndLog("$('div').triggerHandler('click');",
			jsStatement.chain(EventsHelper.triggerHandler(MouseEvent.CLICK)).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#triggerHandler(org.wicketstuff.wiquery.core.events.EventLabel, java.lang.CharSequence[])}
	 * .
	 */
	@Test
	public void testTriggerHandlerEventLabelCharSequenceArray()
	{
		assertAndLog("$('div').triggerHandler('click', ['a']);",
			jsStatement.chain(EventsHelper.triggerHandler(MouseEvent.CLICK, "'a'")).render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#unbind(org.wicketstuff.wiquery.core.events.EventLabel, org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testUnbind()
	{
		assertAndLog("$('div').unbind('click', function() {\n\talert('click done');\n});",
			jsStatement
				.chain(EventsHelper.unbind(MouseEvent.CLICK,
					JsScope.quickScope("alert('click done');")))
				.render());
	}

	/**
	 * Test method for
	 * {@link org.wicketstuff.wiquery.core.javascript.helper.EventsHelper#unload(org.wicketstuff.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testUnload()
	{
		assertAndLog("$('div').unload(function() {\n\talert('done');\n});",
			jsStatement.chain(EventsHelper.unload(JsScope.quickScope("alert('done');"))).render());
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}
