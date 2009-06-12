package org.odlabs.wiquery.ui.core.options;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import junit.framework.TestCase;

/**
 * Unit test on the {@link ListItemOptions}
 * @author Julien Roche
 *
 */
public class OptionsTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			OptionsTestCase.class);
	
	@Test
	public void testGetBoolean() {
		Options options = new Options();
		options.put("keyBoolean", true);
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.getBoolean("keyBoolean"));
	}
	
	@Test
	public void testGetFloat() {
		Options options = new Options();
		options.put("keyFloat", 1F);
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertEquals(1F, options.getFloat("keyFloat"));
	}
	
	@Test
	public void testGetICollectionItemOptions() {
		ArrayItemOptions<IntegerItemOptions> array = new ArrayItemOptions<IntegerItemOptions>();
		IntegerItemOptions o1 = new IntegerItemOptions(5);
		IntegerItemOptions o2 = new IntegerItemOptions(23);
		array.add(o1);
		array.add(o2);
		
		Options options = new Options();
		options.put("keyOptions", array);
		
		Assert.assertFalse(options.isEmpty());
		
		ICollectionItemOptions temp = options.getListItemOptions("keyOptions");
		Assert.assertNotNull(temp);
		Assert.assertEquals(2, temp.values().length);
	}
	
	@Test
	public void testGetInt() {
		Options options = new Options();
		options.put("keyInt", 1);
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertEquals(1, options.getInt("keyInt"));
	}
	
	@Test
	public void testGetJavaScriptOptions() {
		ArrayItemOptions<IntegerItemOptions> array = new ArrayItemOptions<IntegerItemOptions>();
		IntegerItemOptions o1 = new IntegerItemOptions(5);
		IntegerItemOptions o2 = new IntegerItemOptions(23);
		array.add(o1);
		array.add(o2);
		
		JsScope jsScope = JsScope.quickScope("alert('test');");
		
		Options options = new Options();
		options.put("keyBoolean", true);
		options.put("keyFloat", 1F);
		options.put("keyInt", 1);
		options.putLiteral("keyLiteral", "literal");
		options.put("keyString", "string");
		options.put("keyOptions", array);
		options.put("keyScope", jsScope);
		
		String expectedJavascript = "{keyString: string, keyBoolean: true, " +
				"keyInt: 1, keyFloat: 1.0, keyScope: function() {\n\talert('test');\n}," +
				" keyOptions: [5,23], keyLiteral: 'literal'}";
		String generatedJavascript = options.getJavaScriptOptions().toString();
		log.info(expectedJavascript);
		log.info(generatedJavascript);
		Assert.assertEquals(generatedJavascript, expectedJavascript);
	}
	
	@Test
	public void testGetLiteral() {
		Options options = new Options();
		options.putLiteral("keyLiteral", "literal");
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertEquals("literal", options.getLiteral("keyLiteral"));
	}
	
	@Test
	public void testGetString() {
		Options options = new Options();
		options.put("keyString", "string");
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertEquals("string", options.get("keyString"));
	}
	
	@Test
	public void testPutBoolean() {
		Options options = new Options();
		options.put("keyBoolean", true);
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyBoolean"));
	}
	
	@Test
	public void testPutFloat() {
		Options options = new Options();
		options.put("keyFloat", 1F);
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyFloat"));
	}
	
	@Test
	public void testPutICollectionItemOptions() {
		Options options = new Options();
		options.put("keyOptions", new ArrayItemOptions<IntegerItemOptions>());
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyOptions"));
	}
	
	@Test
	public void testPutInt() {
		Options options = new Options();
		options.put("keyInt", 1);
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyInt"));
	}
	
	@Test
	public void testPutLiterral() {
		Options options = new Options();
		options.putLiteral("keyLiteral", "literal");
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyLiteral"));
	}
	
	@Test
	public void testPutString() {
		Options options = new Options();
		options.put("keyString", "string");
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyString"));
	}
}
