package org.odlabs.wiquery.core.options;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test on the {@link ListItemOptions}
 * @author Julien Roche
 *
 */
public class OptionsTestCase extends TestCase{
	protected static final Logger log = LoggerFactory.getLogger(
			OptionsTestCase.class);
	
	public static class WithMemoryModel<T extends Serializable> extends Model<T> {
		
		private static final long serialVersionUID = 1L;
		
		private boolean detached = false;
		
		private IModel<T> model;
		
		public WithMemoryModel(IModel<T> model) {
			super();
			this.model = model;
		}
		
		@Override
		public void detach() {
			super.detach();
			model.detach();
			detached = true;
		}

		public boolean isDetached() {
			return detached;
		}
	}
	
	private WicketTester wicketTester;

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new WebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return OptionsTestPage.class;
			}
		});
	}
	
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
	public void testOptionsDetachment() {
		Options options = new Options();
		WithMemoryModel<Float> iRemeberFloat = new WithMemoryModel<Float>(new Model<Float>(1F));
		options.putFloat("keyFloat", iRemeberFloat);
		Assert.assertFalse(options.isEmpty());
		
		WithMemoryModel<Short> iRemeberShort = new WithMemoryModel<Short>(new Model<Short>((short)1));
		options.putShort("keyShort", iRemeberShort);
		
		WithMemoryModel<Integer> iRemeberInteger = new WithMemoryModel<Integer>(new Model<Integer>(1));
		options.putInteger("keyInteger", iRemeberInteger);
		
		WithMemoryModel<Double> iRemeberDouble = new WithMemoryModel<Double>(new Model<Double>(1D));
		options.putDouble("keyDouble", iRemeberDouble);
		
		WithMemoryModel<String> iRemeberString = new WithMemoryModel<String>(new Model<String>("String"));
		options.putString("keyString", iRemeberString);
		
		WithMemoryModel<String> iRemeberLiteral = new WithMemoryModel<String>(new Model<String>("String"));
		options.putString("keyLiteral", iRemeberLiteral);
		
		WithMemoryModel<Boolean> iRemeberBoolean = new WithMemoryModel<Boolean>(new Model<Boolean>(true));
		options.putBoolean("keyBoolean", iRemeberBoolean);
		
		Assert.assertFalse(options.isEmpty());
		options.detach();
		
		assertTrue(iRemeberShort.isDetached());
		assertTrue(iRemeberFloat.isDetached());
		assertTrue(iRemeberInteger.isDetached());
		assertTrue(iRemeberDouble.isDetached());
		assertTrue(iRemeberString.isDetached());
		assertTrue(iRemeberLiteral.isDetached());
		assertTrue(iRemeberBoolean.isDetached());
		
	}
	
	@Test
	public void testOptionsWrappedModels() {
		// this method test the use of wrapped models in options
		OptionsTestPanel panel = new OptionsTestPanel("panel");
		Options options = panel.getOptions();
		// put an IComponentAssignedModel 
		options.putString("test", new ResourceModel("key"));
		options.putString("test1", new Model<String>("Test1"));		
		options.put("test2", false);
		OptionsTestPage page = new OptionsTestPage(panel);
		page = (OptionsTestPage)wicketTester.startPage(page);
		String expectedResult = "Test";
		// result should has been read from resources.
		String result = options.get("test");
		log.info("result="+result);
		log.info("expectedResult="+expectedResult);
		assertEquals(expectedResult, result);
		assertEquals("Test1",  options.get("test1"));		
		assertEquals(false,  options.getBoolean("test2").booleanValue());		
		wicketTester.assertNoErrorMessage();
	}
	
	@Test
	public void testGetFloat() {
		Options options = new Options();
		options.put("keyFloat", 1F);
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertEquals((Object) 1F, (Object) options.getFloat("keyFloat"));
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
		Assert.assertEquals(1, options.getInt("keyInt").intValue());
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
		options.putBoolean("keyBooleanModel", new Model<Boolean>(false));
		options.put("keyFloat", 1F);
		options.putFloat("keyFloatModel", new Model<Float>(2F));
		options.put("keyInt", 1);
		options.putInteger("keyIntModel", new Model<Integer>(2));
		options.putLiteral("keyLiteral", "literal");
		options.putLiteral("keyLiteralModel", new Model<String>("literal1"));		
		options.put("keyString", "string");
		options.putString("keyStringModel", new Model<String>("string1"));		
		options.put("keyOptions", array);
		options.put("keyScope", jsScope);
		options.put("keyComplexOption", impl);
		
		String generatedJavascript = options.getJavaScriptOptions().toString();
		log.info(generatedJavascript);
		
		Assert.assertTrue(generatedJavascript.startsWith("{"));
		Assert.assertTrue(generatedJavascript.endsWith("}"));
		
		generatedJavascript = generatedJavascript.substring(1, generatedJavascript.length() - 1);
		List<String> opts = Arrays.asList(generatedJavascript.split(", "));
		
		Assert.assertEquals(opts.size(), 13);
		Assert.assertTrue(opts.contains("keyString: string"));
		Assert.assertTrue(opts.contains("keyStringModel: string1"));
		Assert.assertTrue(opts.contains("keyBoolean: true"));
		Assert.assertTrue(opts.contains("keyBooleanModel: false"));
		
		Assert.assertTrue(opts.contains("keyInt: 1"));
		Assert.assertTrue(opts.contains("keyIntModel: 2"));
		Assert.assertTrue(opts.contains("keyComplexOption: alert('complex option');"));
		Assert.assertTrue(opts.contains("keyFloat: 1.0"));
		Assert.assertTrue(opts.contains("keyFloatModel: 2.0"));
		Assert.assertTrue(opts.contains("keyOptions: [5,23]"));
		Assert.assertTrue(opts.contains("keyLiteral: 'literal'"));
		Assert.assertTrue(opts.contains("keyLiteralModel: 'literal1'"));
		Assert.assertTrue(opts.contains("keyScope: function() {\n\talert('test');\n}"));
	}
	
	@Test
	public void testGetLiteral() {
		Options options = new Options();
		options.putLiteral("keyLiteral", "literal");
		options.putLiteral("keyLiteralModel", new Model<String>("literal1"));
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertEquals("literal", options.getLiteral("keyLiteral"));
		Assert.assertEquals("literal1", options.getLiteral("keyLiteralModel"));
	}
	
	@Test
	public void testGetString() {
		Options options = new Options();
		options.put("keyString", "string");
		options.putString("keyStringModel", new Model<String>("string"));
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertEquals("string", options.get("keyString"));
		Assert.assertEquals("string", options.get("keyStringModel"));	
	}
	
	@Test
	public void testPutBoolean() {
		Options options = new Options();
		options.put("keyBoolean", true);
		options.putBoolean("keyBooleanModel", new Model<Boolean>(true));
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyBoolean"));
		Assert.assertTrue(options.containsKey("keyBooleanModel"));
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
		options.putFloat("keyFloatModel", new Model<Float>(1F));
		
		Assert.assertFalse(options.isEmpty());
		Assert.assertTrue(options.containsKey("keyFloat"));
		Assert.assertTrue(options.containsKey("keyFloatModel"));
		
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
