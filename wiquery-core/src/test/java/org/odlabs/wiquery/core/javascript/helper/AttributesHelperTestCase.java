package org.odlabs.wiquery.core.javascript.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link AttributesHelper}
 * 
 * @author Julien Roche
 */
public class AttributesHelperTestCase extends WiQueryTestCase {

	protected static final Logger log = LoggerFactory
			.getLogger(AttributesHelperTestCase.class);

	/**
	 * Test {@link AttributesHelper#addClass(String)}
	 */
	@Test
	public void testAddClass() {
		String expectedJavascript = "$('div').addClass('myClass');";
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(AttributesHelper.addClass("myClass")).render()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link AttributesHelper#attr(String, String)}
	 */
	@Test
	public void testAttr() {
		String expectedJavascript = "$('div').attr('title', 'a title');";
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(AttributesHelper.attr("title", "a title")).render()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test
	 * {@link AttributesHelper#attr(String, org.odlabs.wiquery.core.javascript.JsScope)}
	 */
	@Test
	public void testAttrScope() {
		String expectedJavascript = "$('div').attr('click', function() {\n\talert('click done');\n});";
		String generatedJavascript = new JsStatement()
				.$(null, "div")
				.chain(AttributesHelper.attr("click",
						JsScope.quickScope("alert('click done');"))).render()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link AttributesHelper#html(CharSequence)}
	 */
	@Test
	public void testHtml() {
		String expectedJavascript = "$('div').html('some text');";
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(AttributesHelper.html("some text")).render().toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link AttributesHelper#removeAttr(String)}
	 */
	@Test
	public void testRemoveAttr() {
		String expectedJavascript = "$('div').removeAttr('title');";
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(AttributesHelper.removeAttr("title")).render()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link AttributesHelper#removeClass(String)}
	 */
	@Test
	public void testRemoveClass() {
		String expectedJavascript = "$('div').removeClass('myClass');";
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(AttributesHelper.removeClass("myClass")).render()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link AttributesHelper#toggleClass(String)}
	 */
	@Test
	public void testToggleClass() {
		String expectedJavascript = "$('div').toggleClass('myClass');";
		String generatedJavascript = new JsStatement().$(null, "div")
				.chain(AttributesHelper.toggleClass("myClass")).render()
				.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Override
	protected Logger getLog() {
		return log;
	}
}
