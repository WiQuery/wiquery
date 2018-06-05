package org.wicketstuff.wiquery.core.javascript.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Unit test on the {@link ManipulatingHelper}
 * 
 * @author Julien Roche
 */
public class ManipulatingHelperTestCase extends WiQueryTestCase
{

	protected static final Logger log = LoggerFactory.getLogger(ManipulatingHelperTestCase.class);

	/**
	 * Test {@link ManipulatingHelper#after(CharSequence)}
	 */
	@Test
	public void testAfter()
	{
		String expectedJavascript = "$('div').after('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div")
			.chain(ManipulatingHelper.after("<div>a<div>"))
			.render()
			.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link ManipulatingHelper#before(CharSequence)}
	 */
	@Test
	public void testBefore()
	{
		String expectedJavascript = "$('div').before('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div")
			.chain(ManipulatingHelper.before("<div>a<div>"))
			.render()
			.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link ManipulatingHelper#insertAfter(String)}
	 */
	@Test
	public void testInsertAfter()
	{
		String expectedJavascript = "$('div').insertAfter('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div")
			.chain(ManipulatingHelper.insertAfter("<div>a<div>"))
			.render()
			.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	/**
	 * Test {@link ManipulatingHelper#insertBefore(String)}
	 */
	@Test
	public void testInsertBefore()
	{
		String expectedJavascript = "$('div').insertBefore('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div")
			.chain(ManipulatingHelper.insertBefore("<div>a<div>"))
			.render()
			.toString();

		log.info(expectedJavascript);
		log.info(generatedJavascript);

		assertEquals(generatedJavascript, expectedJavascript);
	}

	@Override
	protected Logger getLog()
	{
		return log;
	}
}
