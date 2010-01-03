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
package org.odlabs.wiquery.core.javascript;

import junit.framework.TestCase;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.javascript.helper.CssHelper;
import org.odlabs.wiquery.core.options.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Test {@link JsStatement}
 * @author Julien Roche
 *
 */
public class JsStatementTestCase extends TestCase {
	// Constants
	/** Logger */
	protected static final Logger log = LoggerFactory.getLogger(
			JsStatementTestCase.class);
	
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
		jsStatement = new JsStatement();
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#$()}.
	 */
	public void test$() {
		assertAndLog("$;", jsStatement.$().render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#$(org.apache.wicket.Component)}.
	 */
	public void test$Component() {
		new WicketTester();
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		assertAndLog("$('#anId');", jsStatement.$(component).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#$(org.apache.wicket.Component, java.lang.String)}.
	 */
	public void test$ComponentString() {
		assertAndLog("$('span');", jsStatement.$(null, "span").render());
		jsStatement = new JsStatement();
		
		new WicketTester();
		WebMarkupContainer component = new WebMarkupContainer("anId");
		component.setMarkupId("anId");
		assertAndLog("$('#anId span');", jsStatement.$(component, "span").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#addClass(java.lang.String)}.
	 */
	public void testAddClass() {
		assertAndLog("$('span').addClass('myClass');", 
				jsStatement.$(null, "span").addClass("myClass").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#after(java.lang.CharSequence)}.
	 */
	public void testAfter() {
		assertAndLog("$('span').after('<div>some text</div>');", 
				jsStatement.$(null, "span").after("<div>some text</div>").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#append(java.lang.CharSequence)}.
	 */
	public void testAppend() {
		assertAndLog("$('div').click();", jsStatement.append("$('div').click()").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#attr(java.lang.String, org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testAttrStringJsScope() {
		assertAndLog("$('span').attr('click', function() {\n\talert('click done');\n});", 
				jsStatement.$(null, "span").attr(
						"click",
						JsScope.quickScope("alert('click done');")).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#attr(java.lang.String, java.lang.String)}.
	 */
	public void testAttrStringString() {
		assertAndLog("$('span').attr('title', 'a title');", 
				jsStatement.$(null, "span").attr(
						"title", "a title").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#before(java.lang.CharSequence)}.
	 */
	public void testBefore() {
		assertAndLog("$('span').before('<div>some text</div>');", 
				jsStatement.$(null, "span").before("<div>some text</div>").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#chain(org.odlabs.wiquery.core.javascript.ChainableStatement)}.
	 */
	public void testChainChainableStatement() {
		assertAndLog("$('span').css('font-weight', 'bold');", 
				jsStatement.$(null, "span").chain(CssHelper.css("font-weight", "bold")).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#chain(java.lang.CharSequence, java.lang.CharSequence[])}.
	 */
	public void testChainCharSequenceCharSequenceArray() {
		assertAndLog("$('span').a(b, c);", 
				jsStatement.$(null, "span").chain("a", "b", "c").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#css(org.odlabs.wiquery.core.options.Options)}.
	 */
	public void testCssOptions() {
		Options options = new Options();
		options.putLiteral("font-weight", "bold");
		assertAndLog("$('span').css({font-weight: 'bold'});", 
				jsStatement.$(null, "span").css(options).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#css(java.lang.String, java.lang.String)}.
	 */
	public void testCssStringString() {
		assertAndLog("$('span').css('font-weight', 'bold');", 
				jsStatement.$(null, "span").css("font-weight", "bold").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#document()}.
	 */
	public void testDocument() {
		assertAndLog("$(document);", jsStatement.document().render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#each(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testEach() {
		assertAndLog("$('span').each(function() {\n\talert('done');\n});", 
				jsStatement.$(null, "span").each(
						JsScope.quickScope("alert('done');")).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#html(java.lang.CharSequence)}.
	 */
	public void testHtml() {
		assertAndLog("$('span').html('some text');", 
				jsStatement.$(null, "span").html("some text").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#insertAfter(java.lang.String)}.
	 */
	public void testInsertAfter() {
		assertAndLog("$('span').insertAfter('<div>some text</div>');", 
				jsStatement.$(null, "span").insertAfter("<div>some text</div>").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#insertBefore(java.lang.String)}.
	 */
	public void testInsertBefore() {
		assertAndLog("$('span').insertBefore('<div>some text</div>');", 
				jsStatement.$(null, "span").insertBefore("<div>some text</div>").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#ready(org.odlabs.wiquery.core.javascript.JsScope)}.
	 */
	public void testReady() {
		assertAndLog("$('span').ready(function() {\n\talert('done');\n});", 
				jsStatement.$(null, "span").ready(
						JsScope.quickScope("alert('done');")).render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#removeAttr(java.lang.String)}.
	 */
	public void testRemoveAttr() {
		assertAndLog("$('span').removeAttr('title');", 
				jsStatement.$(null, "span").removeAttr("title").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#removeClass(java.lang.String)}.
	 */
	public void testRemoveClass() {
		assertAndLog("$('span').removeClass('myClass');", 
				jsStatement.$(null, "span").removeClass("myClass").render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#render()}.
	 */
	public void testRender() {
		assertAndLog("$;", jsStatement.$().render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#render(boolean)}.
	 */
	public void testRenderBoolean() {
		assertAndLog("$;", jsStatement.$().render(true));
		jsStatement = new JsStatement();
		assertAndLog("$", jsStatement.$().render(false));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#self()}.
	 */
	public void testSelf() {
		assertAndLog("$(this);", 
				jsStatement.self().render());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.core.javascript.JsStatement#toggleClass(java.lang.String)}.
	 */
	public void testToggleClass() {
		assertAndLog("$('span').toggleClass('myClass');", 
				jsStatement.$(null, "span").toggleClass("myClass").render());
	}
}
