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
package org.odlabs.wiquery.core.javascript.helper;

import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import junit.framework.TestCase;

/**
 * Unit test on the {@link EventsHelper}
 * @author Julien Roche
 *
 */
public class EventsHelperTestCase extends TestCase {
	// Constants
	/** Logger */
	protected static final Logger log = LoggerFactory.getLogger(
			EventsHelperTestCase.class);
	
	// Properties
	private JsStatement jsStatement;
	
	/**
	 * Log and assert javascript
	 * @param expectedJavascript
	 * @param generatedJavascript
	 */
	private void assertAndLog(CharSequence expectedJavascript, 
			CharSequence generatedJavascript) {
		log.info(expectedJavascript.toString());
		log.info(generatedJavascript.toString());
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * {@inheritDoc}
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		jsStatement = new JsStatement().$(null, "div");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#bind(org.odlabs.wiquery.core.events.EventLabel, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testBind() {
		assertAndLog(
				"$('div').bind('click', function() {\n\talert('click done');\n});", 
				jsStatement.chain(
						EventsHelper.bind(
								MouseEvent.CLICK,
								JsScope.quickScope("alert('click done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#blur()}.
	 */
	public void testBlur() {
		assertAndLog(
				"$('div').blur();", 
				jsStatement.chain(EventsHelper.blur()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#blur(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testBlurJsScope() {
		assertAndLog(
				"$('div').blur(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.blur(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#change()}.
	 */
	public void testChange() {
		assertAndLog(
				"$('div').change();", 
				jsStatement.chain(EventsHelper.change()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#change(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testChangeJsScope() {
		assertAndLog(
				"$('div').change(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.change(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#click()}.
	 */
	public void testClick() {
		assertAndLog(
				"$('div').click();", 
				jsStatement.chain(EventsHelper.click()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#click(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testClickJsScope() {
		assertAndLog(
				"$('div').click(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.click(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#dblclick()}.
	 */
	public void testDblclick() {
		assertAndLog(
				"$('div').dblclick();", 
				jsStatement.chain(EventsHelper.dblclick()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#dblclick(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testDblclickJsScope() {
		assertAndLog(
				"$('div').dblclick(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.dblclick(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#die(org.odlabs.wiquery.core.events.EventLabel, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testDie() {
		assertAndLog(
				"$('div').die('click', function() {\n\talert('click done');\n});", 
				jsStatement.chain(
						EventsHelper.die(
								MouseEvent.CLICK,
								JsScope.quickScope("alert('click done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#error()}.
	 */
	public void testError() {
		assertAndLog(
				"$('div').error();", 
				jsStatement.chain(EventsHelper.error()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#error(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testErrorJsScope() {
		assertAndLog(
				"$('div').error(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.error(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#focus()}.
	 */
	public void testFocus() {
		assertAndLog(
				"$('div').focus();", 
				jsStatement.chain(EventsHelper.focus()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#focus(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testFocusJsScope() {
		assertAndLog(
				"$('div').focus(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.focus(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#hover(org.odlabs.wiquery.core.javascript.JsScope, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testHover() {
		assertAndLog(
				"$('div').hover(function() {\n\talert('over');\n}, function() {\n\talert('out');\n});", 
				jsStatement.chain(
						EventsHelper.hover(
								JsScope.quickScope("alert('over');"),
								JsScope.quickScope("alert('out');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#keydown()}.
	 */
	public void testKeydown() {
		assertAndLog(
				"$('div').keydown();", 
				jsStatement.chain(EventsHelper.keydown()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#keydown(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testKeydownJsScope() {
		assertAndLog(
				"$('div').keydown(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.keydown(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#keypress()}.
	 */
	public void testKeypress() {
		assertAndLog(
				"$('div').keypress();", 
				jsStatement.chain(EventsHelper.keypress()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#keypress(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testKeypressJsScope() {
		assertAndLog(
				"$('div').keypress(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.keypress(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#keyup()}.
	 */
	public void testKeyup() {
		assertAndLog(
				"$('div').keyup();", 
				jsStatement.chain(EventsHelper.keyup()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#keyup(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testKeyupJsScope() {
		assertAndLog(
				"$('div').keyup(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.keyup(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#live(org.odlabs.wiquery.core.events.EventLabel, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testLive() {
		assertAndLog(
				"$('div').live('click', function() {\n\talert('click done');\n});", 
				jsStatement.chain(
						EventsHelper.live(
								MouseEvent.CLICK,
								JsScope.quickScope("alert('click done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#load(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testLoad() {
		assertAndLog(
				"$('div').load(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.load(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#mousedown(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testMousedown() {
		assertAndLog(
				"$('div').mousedown(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.mousedown(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#mouseenter(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testMouseenter() {
		assertAndLog(
				"$('div').mouseenter(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.mouseenter(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#mouseleave(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testMouseleave() {
		assertAndLog(
				"$('div').mouseleave(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.mouseleave(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#mousemove(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testMousemove() {
		assertAndLog(
				"$('div').mousemove(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.mousemove(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#mouseout(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testMouseout() {
		assertAndLog(
				"$('div').mouseout(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.mouseout(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#mouseover(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testMouseover() {
		assertAndLog(
				"$('div').mouseover(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.mouseover(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#mouseup(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testMouseup() {
		assertAndLog(
				"$('div').mouseup(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.mouseup(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#one(org.odlabs.wiquery.core.events.EventLabel, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testOne() {
		assertAndLog(
				"$('div').one('click', function() {\n\talert('click done');\n});", 
				jsStatement.chain(
						EventsHelper.one(
								MouseEvent.CLICK,
								JsScope.quickScope("alert('click done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#ready(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testReady() {
		assertAndLog(
				"$('div').ready(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.ready(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#scroll(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testScroll() {
		assertAndLog(
				"$('div').scroll(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.scroll(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#select()}.
	 */
	public void testSelect() {
		assertAndLog(
				"$('div').select();", 
				jsStatement.chain(EventsHelper.select()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#select(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testSelectJsScope() {
		assertAndLog(
				"$('div').select(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.select(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#submit()}.
	 */
	public void testSubmit() {
		assertAndLog(
				"$('div').submit();", 
				jsStatement.chain(EventsHelper.submit()).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#submit(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testSubmitJsScope() {
		assertAndLog(
				"$('div').submit(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.submit(
								JsScope.quickScope("alert('done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#toggle(org.odlabs.wiquery.core.javascript.JsScope, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testToggleJsScopeJsScope() {
		assertAndLog(
				"$('div').toggle(function() {\n\talert('a');\n}, function() {\n\talert('b');\n});", 
				jsStatement.chain(
						EventsHelper.toggle(
								JsScope.quickScope("alert('a');"),
								JsScope.quickScope("alert('b');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#toggle(org.odlabs.wiquery.core.javascript.JsScope, org.odlabs.wiquery.core.javascript.JsScope, org.odlabs.wiquery.core.javascript.JsScope[])}.
	 */
	public void testToggleJsScopeJsScopeJsScopeArray() {
		assertAndLog(
				"$('div').toggle(function() {\n\talert('a');\n}, function() {\n\talert('b');\n}, function() {\n\talert('c');\n});", 
				jsStatement.chain(
						EventsHelper.toggle(
								JsScope.quickScope("alert('a');"),
								JsScope.quickScope("alert('b');"),
								JsScope.quickScope("alert('c');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#trigger(org.odlabs.wiquery.core.events.EventLabel)}.
	 */
	public void testTriggerEventLabel() {
		assertAndLog(
				"$('div').trigger('click');", 
				jsStatement.chain(
						EventsHelper.trigger(MouseEvent.CLICK)).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#trigger(org.odlabs.wiquery.core.events.EventLabel, java.lang.CharSequence[])}.
	 */
	public void testTriggerEventLabelCharSequenceArray() {
		assertAndLog(
				"$('div').trigger('click', ['a']);", 
				jsStatement.chain(
						EventsHelper.trigger(MouseEvent.CLICK, "'a'")).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#triggerHandler(org.odlabs.wiquery.core.events.EventLabel)}.
	 */
	public void testTriggerHandlerEventLabel() {
		assertAndLog(
				"$('div').triggerHandler('click');", 
				jsStatement.chain(
						EventsHelper.triggerHandler(MouseEvent.CLICK)).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#triggerHandler(org.odlabs.wiquery.core.events.EventLabel, java.lang.CharSequence[])}.
	 */
	public void testTriggerHandlerEventLabelCharSequenceArray() {
		assertAndLog(
				"$('div').triggerHandler('click', ['a']);", 
				jsStatement.chain(
						EventsHelper.triggerHandler(MouseEvent.CLICK, "'a'")).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#unbind(org.odlabs.wiquery.core.events.EventLabel, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testUnbind() {
		assertAndLog(
				"$('div').unbind('click', function() {\n\talert('click done');\n});", 
				jsStatement.chain(
						EventsHelper.unbind(
								MouseEvent.CLICK,
								JsScope.quickScope("alert('click done');"))).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.helper.EventsHelper#unload(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testUnload() {
		assertAndLog(
				"$('div').unload(function() {\n\talert('done');\n});", 
				jsStatement.chain(
						EventsHelper.unload(
								JsScope.quickScope("alert('done');"))).render());
	}
}
