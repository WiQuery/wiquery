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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.commons.DivTestPanel;
import org.odlabs.wiquery.core.javascript.helper.CssHelper;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test {@link JsStatement}
 * 
 * @author Julien Roche
 */
public class JsStatementTestCase extends WiQueryTestCase {
	// Constants
	/** Logger */
	protected static final Logger log = LoggerFactory
			.getLogger(JsStatementTestCase.class);

	// Properties
	private JsStatement jsStatement;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		jsStatement = new JsStatement();
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#$()}.
	 */
	@Test
	public void test$() {
		assertAndLog("$;", jsStatement.$().render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#$(org.apache.wicket.Component)}
	 * .
	 */
	@Test
	public void test$Component() {
		final WebMarkupContainer component = new WebMarkupContainer("anId");
		tester.startPanel(new ITestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				Panel panel = new DivTestPanel(panelId);
				component.setMarkupId("anId");
				panel.add(component);
				return panel;
			}
		});
		assertAndLog("$('#anId');", jsStatement.$(component).render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#$(org.apache.wicket.Component, java.lang.String)}
	 * .
	 */
	@Test
	public void test$ComponentString() {
		assertAndLog("$('span');", jsStatement.$(null, "span").render());
		jsStatement = new JsStatement();

		final WebMarkupContainer component = new WebMarkupContainer("anId");
		tester.startPanel(new ITestPanelSource() {
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId) {
				Panel panel = new DivTestPanel(panelId);
				component.setMarkupId("anId");
				panel.add(component);
				return panel;
			}
		});
		assertAndLog("$('#anId span');", jsStatement.$(component, "span")
				.render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#addClass(java.lang.String)}
	 * .
	 */
	@Test
	public void testAddClass() {
		assertAndLog("$('span').addClass('myClass');",
				jsStatement.$(null, "span").addClass("myClass").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#after(java.lang.CharSequence)}
	 * .
	 */
	@Test
	public void testAfter() {
		assertAndLog("$('span').after('<div>some text</div>');",
				jsStatement.$(null, "span").after("<div>some text</div>")
						.render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#append(java.lang.CharSequence)}
	 * .
	 */
	@Test
	public void testAppend() {
		assertAndLog("$('div').click();", jsStatement
				.append("$('div').click()").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#attr(java.lang.String, org.odlabs.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testAttrStringJsScope() {
		assertAndLog(
				"$('span').attr('click', function() {\n\talert('click done');\n});",
				jsStatement
						.$(null, "span")
						.attr("click",
								JsScope.quickScope("alert('click done');"))
						.render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#attr(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAttrStringString() {
		assertAndLog("$('span').attr('title', 'a title');",
				jsStatement.$(null, "span").attr("title", "a title").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#before(java.lang.CharSequence)}
	 * .
	 */
	@Test
	public void testBefore() {
		assertAndLog("$('span').before('<div>some text</div>');", jsStatement
				.$(null, "span").before("<div>some text</div>").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#chain(org.odlabs.wiquery.core.javascript.ChainableStatement)}
	 * .
	 */
	@Test
	public void testChainChainableStatement() {
		assertAndLog(
				"$('span').css('font-weight', 'bold');",
				jsStatement.$(null, "span")
						.chain(CssHelper.css("font-weight", "bold")).render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#chain(java.lang.CharSequence, java.lang.CharSequence[])}
	 * .
	 */
	@Test
	public void testChainCharSequenceCharSequenceArray() {
		assertAndLog("$('span').a(b, c);",
				jsStatement.$(null, "span").chain("a", "b", "c").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#css(org.odlabs.wiquery.core.options.Options)}
	 * .
	 */
	@Test
	public void testCssOptions() {
		Options options = new Options();
		options.putLiteral("font-weight", "bold");
		assertAndLog("$('span').css({font-weight: 'bold'});",
				jsStatement.$(null, "span").css(options).render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#css(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCssStringString() {
		assertAndLog("$('span').css('font-weight', 'bold');",
				jsStatement.$(null, "span").css("font-weight", "bold").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#document()}.
	 */
	@Test
	public void testDocument() {
		assertAndLog("$(document);", jsStatement.document().render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#each(org.odlabs.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testEach() {
		assertAndLog(
				"$('span').each(function() {\n\talert('done');\n});",
				jsStatement.$(null, "span")
						.each(JsScope.quickScope("alert('done');")).render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#html(java.lang.CharSequence)}
	 * .
	 */
	@Test
	public void testHtml() {
		assertAndLog("$('span').html('some text');", jsStatement
				.$(null, "span").html("some text").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#insertAfter(java.lang.String)}
	 * .
	 */
	@Test
	public void testInsertAfter() {
		assertAndLog("$('span').insertAfter('<div>some text</div>');",
				jsStatement.$(null, "span").insertAfter("<div>some text</div>")
						.render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#insertBefore(java.lang.String)}
	 * .
	 */
	@Test
	public void testInsertBefore() {
		assertAndLog("$('span').insertBefore('<div>some text</div>');",
				jsStatement.$(null, "span")
						.insertBefore("<div>some text</div>").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#ready(org.odlabs.wiquery.core.javascript.JsScope)}
	 * .
	 */
	@Test
	public void testReady() {
		assertAndLog(
				"$('span').ready(function() {\n\talert('done');\n});",
				jsStatement.$(null, "span")
						.ready(JsScope.quickScope("alert('done');")).render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#removeAttr(java.lang.String)}
	 * .
	 */
	@Test
	public void testRemoveAttr() {
		assertAndLog("$('span').removeAttr('title');",
				jsStatement.$(null, "span").removeAttr("title").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#removeClass(java.lang.String)}
	 * .
	 */
	@Test
	public void testRemoveClass() {
		assertAndLog("$('span').removeClass('myClass');",
				jsStatement.$(null, "span").removeClass("myClass").render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#render()}.
	 */
	@Test
	public void testRender() {
		assertAndLog("$;", jsStatement.$().render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#render(boolean)}.
	 */
	@Test
	public void testRenderBoolean() {
		assertAndLog("$;", jsStatement.$().render(true));
		jsStatement = new JsStatement();
		assertAndLog("$", jsStatement.$().render(false));
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#self()}.
	 */
	@Test
	public void testSelf() {
		assertAndLog("$(this);", jsStatement.self().render());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.core.javascript.JsStatement#toggleClass(java.lang.String)}
	 * .
	 */
	@Test
	public void testToggleClass() {
		assertAndLog("$('span').toggleClass('myClass');",
				jsStatement.$(null, "span").toggleClass("myClass").render());
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}
