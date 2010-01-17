package org.odlabs.wiquery.core.options;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

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
	public void testGetIComplexOption() {
		DefaultComplexOptionImpl impl = new DefaultComplexOptionImpl();
		
		Options options = new Options();
		options.put("keyComplexOption", impl);
		
		Assert.assertFalse(options.isEmpty());
		
		IComplexOption complexOption = options.getComplexOption("keyComplexOption");
		Assert.assertNotNull(complexOption);
		Assert.assertEquals(impl, complexOption);
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
		
		DefaultComplexOptionImpl impl = new DefaultComplexOptionImpl();
		
		JsScope jsScope = JsScope.quickScope("alert('test');");
		
		Options options = new Options();
		options.put("keyBoolean", true);
		options.put("keyFloat", 1F);
		options.put("keyInt", 1);
		options.putLiteral("keyLiteral", "literal");
		options.put("keyString", "string");
		options.put("keyOptions", array);
		options.put("keyScope", jsScope);
		options.put("keyComplexOption", impl);
		
		String generatedJavascript = options.getJavaScriptOptions().toString();
		log.info(generatedJavascript);
		
		Assert.assertTrue(generatedJavascript.startsWith("{"));
		Assert.assertTrue(generatedJavascript.endsWith("}"));
		
		generatedJavascript = generatedJavascript.substring(1, generatedJavascript.length() - 1);
		List<String> opts = Arrays.asList(generatedJavascript.split(", "));
		
		Assert.assertEquals(opts.size(), 8);
		Assert.assertTrue(opts.contains("keyString: string"));
		Assert.assertTrue(opts.contains("keyBoolean: true"));
		Assert.assertTrue(opts.contains("keyInt: 1"));
		Assert.assertTrue(opts.contains("keyComplexOption: alert('complex option');"));
		Assert.assertTrue(opts.contains("keyFloat: 1.0"));
		Assert.assertTrue(opts.contains("keyOptions: [5,23]"));
		Assert.assertTrue(opts.contains("keyLiteral: 'literal'"));
		Assert.assertTrue(opts.contains("keyScope: function() {\n\talert('test');\n}"));
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
	public void testPutComplexOption() {
		Options options = new Options();
		options.put("keyComplexOption", new DefaultComplexOptionImpl());
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyComplexOption"));
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
	
	private class DefaultComplexOptionImpl implements IComplexOption {
		private static final long serialVersionUID = 1L;

		public CharSequence getJavascriptOption() {
			return "alert('complex option');";
		}
		
	}
}
