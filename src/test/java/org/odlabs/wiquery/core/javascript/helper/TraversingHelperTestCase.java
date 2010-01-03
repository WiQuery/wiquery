package org.odlabs.wiquery.core.javascript.helper;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test on the {@link TraversingHelper}
 * @author Julien Roche
 *
 */
public class TraversingHelperTestCase extends TestCase {

	protected static final Logger log = LoggerFactory.getLogger(
			TraversingHelperTestCase.class);
	
	/**
	 * Test {@link TraversingHelper#add(String)}
	 */
	@Test
	public void testAdd() {
		String expectedJavascript = "$('div').add('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.add("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#children(String)}
	 */
	@Test
	public void testChildren() {
		String expectedJavascript = "$('div').children('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.children("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#contents(String)}
	 */
	@Test
	public void testContents() {
		String expectedJavascript = "$('div').contents('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.contents("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#eq(int)}
	 */
	@Test
	public void testEq() {
		String expectedJavascript = "$('div').eq(1);";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.eq(1)).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#filter(String)}
	 */
	@Test
	public void testFilter() {
		String expectedJavascript = "$('div').filter('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.filter("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#find(String)}
	 */
	@Test
	public void testFind() {
		String expectedJavascript = "$('div').find('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.find("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#next(String)}
	 */
	@Test
	public void testNext() {
		String expectedJavascript = "$('div').next('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.next("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#nextAll(String)}
	 */
	@Test
	public void testNextAll() {
		String expectedJavascript = "$('div').nextAll('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.nextAll("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#not(String)}
	 */
	@Test
	public void testNot() {
		String expectedJavascript = "$('div').not('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.not("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#parent(String)}
	 */
	@Test
	public void testParent() {
		String expectedJavascript = "$('div').parent('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.parent("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#parents(String)}
	 */
	@Test
	public void testParents() {
		String expectedJavascript = "$('div').parents('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.parents("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#prev(String)}
	 */
	@Test
	public void testPrev() {
		String expectedJavascript = "$('div').prev('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.prev("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#prevAll(String)}
	 */
	@Test
	public void testPrevAll() {
		String expectedJavascript = "$('div').prevAll('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.prevAll("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#siblings(String)}
	 */
	@Test
	public void testSiblings() {
		String expectedJavascript = "$('div').siblings('span');";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.siblings("span")).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#slice(int)}
	 */
	@Test
	public void testSlice() {
		String expectedJavascript = "$('div').slice(1);";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.slice(1)).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	/**
	 * Test {@link TraversingHelper#slice(int, int)}
	 */
	@Test
	public void testSlice2() {
		String expectedJavascript = "$('div').slice(1, 3);";
		String generatedJavascript = new JsStatement().$(null, "div").chain(
				TraversingHelper.slice(1, 3)).render().toString();
		
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
}
