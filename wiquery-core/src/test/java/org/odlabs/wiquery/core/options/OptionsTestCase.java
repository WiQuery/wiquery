package org.odlabs.wiquery.core.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test on the {@link ListItemOptions}
 * 
 * @author Julien Roche
 */
public class OptionsTestCase extends WiQueryTestCase {
	protected static final Logger log = LoggerFactory
			.getLogger(OptionsTestCase.class);

	public static class WithMemoryModel<T extends Serializable> extends
			Model<T> {

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

	@Test
	public void testGetBoolean() {
		Options options = new Options();
		options.put("keyBoolean", true);

		assertFalse(options.isEmpty());
		assertTrue(options.getBoolean("keyBoolean"));
	}

	@Test
	public void testGetIComplexOption() {
		DefaultComplexOptionImpl impl = new DefaultComplexOptionImpl();

		Options options = new Options();
		options.put("keyComplexOption", impl);

		assertFalse(options.isEmpty());

		IComplexOption complexOption = options
				.getComplexOption("keyComplexOption");
		assertNotNull(complexOption);
		assertEquals(impl, complexOption);
	}

	@Test
	public void testOptionsDetachment() {
		Options options = new Options();
		WithMemoryModel<Float> iRemeberFloat = new WithMemoryModel<Float>(
				new Model<Float>(1F));
		options.putFloat("keyFloat", iRemeberFloat);
		assertFalse(options.isEmpty());

		WithMemoryModel<Short> iRemeberShort = new WithMemoryModel<Short>(
				new Model<Short>((short) 1));
		options.putShort("keyShort", iRemeberShort);

		WithMemoryModel<Integer> iRemeberInteger = new WithMemoryModel<Integer>(
				new Model<Integer>(1));
		options.putInteger("keyInteger", iRemeberInteger);

		WithMemoryModel<Double> iRemeberDouble = new WithMemoryModel<Double>(
				new Model<Double>(1D));
		options.putDouble("keyDouble", iRemeberDouble);

		WithMemoryModel<String> iRemeberString = new WithMemoryModel<String>(
				new Model<String>("String"));
		options.putString("keyString", iRemeberString);

		WithMemoryModel<String> iRemeberLiteral = new WithMemoryModel<String>(
				new Model<String>("String"));
		options.putString("keyLiteral", iRemeberLiteral);

		WithMemoryModel<Boolean> iRemeberBoolean = new WithMemoryModel<Boolean>(
				new Model<Boolean>(true));
		options.putBoolean("keyBoolean", iRemeberBoolean);

		assertFalse(options.isEmpty());
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
		page = (OptionsTestPage) tester.startPage(page);
		String expectedResult = "Test";
		// result should has been read from resources.
		String result = options.get("test");
		log.info("result=" + result);
		log.info("expectedResult=" + expectedResult);
		assertEquals(expectedResult, result);
		assertEquals("Test1", options.get("test1"));
		assertEquals(false, options.getBoolean("test2").booleanValue());
		tester.assertNoErrorMessage();
	}

	@Test
	public void testGetFloat() {
		Options options = new Options();
		options.put("keyFloat", 1F);

		assertFalse(options.isEmpty());
		assertEquals((Object) 1F, (Object) options.getFloat("keyFloat"));
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

		assertFalse(options.isEmpty());

		ICollectionItemOptions temp = options.getListItemOptions("keyOptions");
		assertNotNull(temp);
		assertEquals(2, temp.values().length);
	}

	@Test
	public void testGetInt() {
		Options options = new Options();
		options.put("keyInt", 1);

		assertFalse(options.isEmpty());
		assertEquals(1, options.getInt("keyInt").intValue());
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

		assertTrue(generatedJavascript.startsWith("{"));
		assertTrue(generatedJavascript.endsWith("}"));

		generatedJavascript = generatedJavascript.substring(1,
				generatedJavascript.length() - 1);
		List<String> opts = Arrays.asList(generatedJavascript.split(", "));

		assertEquals(opts.size(), 13);
		assertTrue(opts.contains("keyString: string"));
		assertTrue(opts.contains("keyStringModel: string1"));
		assertTrue(opts.contains("keyBoolean: true"));
		assertTrue(opts.contains("keyBooleanModel: false"));

		assertTrue(opts.contains("keyInt: 1"));
		assertTrue(opts.contains("keyIntModel: 2"));
		assertTrue(opts.contains("keyComplexOption: alert('complex option');"));
		assertTrue(opts.contains("keyFloat: 1.0"));
		assertTrue(opts.contains("keyFloatModel: 2.0"));
		assertTrue(opts.contains("keyOptions: [5,23]"));
		assertTrue(opts.contains("keyLiteral: 'literal'"));
		assertTrue(opts.contains("keyLiteralModel: 'literal1'"));
		assertTrue(opts.contains("keyScope: function() {\n\talert('test');\n}"));
	}

	@Test
	public void testGetLiteral() {
		Options options = new Options();
		options.putLiteral("keyLiteral", "literal");
		options.putLiteral("keyLiteralModel", new Model<String>("literal1"));

		assertFalse(options.isEmpty());
		assertEquals("literal", options.getLiteral("keyLiteral"));
		assertEquals("literal1", options.getLiteral("keyLiteralModel"));
	}

	@Test
	public void testGetString() {
		Options options = new Options();
		options.put("keyString", "string");
		options.putString("keyStringModel", new Model<String>("string"));

		assertFalse(options.isEmpty());
		assertEquals("string", options.get("keyString"));
		assertEquals("string", options.get("keyStringModel"));
	}

	@Test
	public void testPutBoolean() {
		Options options = new Options();
		options.put("keyBoolean", true);
		options.putBoolean("keyBooleanModel", new Model<Boolean>(true));

		assertFalse(options.isEmpty());
		assertTrue(options.containsKey("keyBoolean"));
		assertTrue(options.containsKey("keyBooleanModel"));
	}

	@Test
	public void testPutComplexOption() {
		Options options = new Options();
		options.put("keyComplexOption", new DefaultComplexOptionImpl());

		assertFalse(options.isEmpty());
		assertTrue(options.containsKey("keyComplexOption"));
	}

	@Test
	public void testPutFloat() {
		Options options = new Options();
		options.put("keyFloat", 1F);
		options.putFloat("keyFloatModel", new Model<Float>(1F));

		assertFalse(options.isEmpty());
		assertTrue(options.containsKey("keyFloat"));
		assertTrue(options.containsKey("keyFloatModel"));

	}

	@Test
	public void testPutICollectionItemOptions() {
		Options options = new Options();
		options.put("keyOptions", new ArrayItemOptions<IntegerItemOptions>());

		assertFalse(options.isEmpty());
		assertTrue(options.containsKey("keyOptions"));
	}

	@Test
	public void testPutInt() {
		Options options = new Options();
		options.put("keyInt", 1);

		assertFalse(options.isEmpty());
		assertTrue(options.containsKey("keyInt"));
	}

	@Test
	public void testPutLiterral() {
		Options options = new Options();
		options.putLiteral("keyLiteral", "literal");

		assertFalse(options.isEmpty());
		assertTrue(options.containsKey("keyLiteral"));
	}

	@Test
	public void testPutString() {
		Options options = new Options();
		options.put("keyString", "string");

		assertFalse(options.isEmpty());
		assertTrue(options.containsKey("keyString"));
	}

	private class DefaultComplexOptionImpl implements IComplexOption {
		private static final long serialVersionUID = 1L;

		public CharSequence getJavascriptOption() {
			return "alert('complex option');";
		}

	}

	@Override
	protected Logger getLog() {
		return log;
	}
}
