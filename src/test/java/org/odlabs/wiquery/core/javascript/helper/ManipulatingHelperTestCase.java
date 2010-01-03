package org.odlabs.wiquery.core.javascript.helper;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link ManipulatingHelper}
 * @author Julien Roche
 *
 */
public class ManipulatingHelperTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			ManipulatingHelperTestCase.class);
	
	/**
	 * Test {@link ManipulatingHelper#after(CharSequence)}
	 */
	@Test
	public void testAfter() {
		String expectedJavascript = "$('div').after('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				ManipulatingHelper.after("<div>a<div>")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link ManipulatingHelper#before(CharSequence)}
	 */
	@Test
	public void testBefore() {
		String expectedJavascript = "$('div').before('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				ManipulatingHelper.before("<div>a<div>")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link ManipulatingHelper#insertAfter(CharSequence)}
	 */
	@Test
	public void testInsertAfter() {
		String expectedJavascript = "$('div').insertAfter('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				ManipulatingHelper.insertAfter("<div>a<div>")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link ManipulatingHelper#insertBefore(CharSequence)}
	 */
	@Test
	public void testInsertBefore() {
		String expectedJavascript = "$('div').insertBefore('<div>a<div>');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				ManipulatingHelper.insertBefore("<div>a<div>")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}
