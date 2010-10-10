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
package org.odlabs.wiquery.ui.slider;

import junit.framework.TestCase;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.slider.Slider.Orientation;
import org.odlabs.wiquery.ui.slider.SliderAnimate.AnimateEnum;
import org.odlabs.wiquery.ui.slider.SliderRange.RangeEnum;
import org.testng.Assert;
import org.testng.annotations.Test;

/**Test on {@link Slider}
 * @author Julien Roche
 *
 */
public class SliderTestCase extends TestCase {
	// Properties
	private Slider slider;

	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		new WicketTester(new WebApplication() {
			@Override
			public Class<? extends Page> getHomePage() {
				return null;
			}
		});
		
		slider = new Slider("anId", 5, 10);
		slider.setMarkupId(slider.getId());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#destroy()}.
	 */
	@Test
	public void testDestroy() {
		Assert.assertNotNull(slider.destroy());
		Assert.assertEquals(slider.destroy().render().toString(), 
				"$('#anId').slider('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#disable()}.
	 */
	@Test
	public void testDisable() {
		Assert.assertNotNull(slider.disable());
		Assert.assertEquals(slider.disable().render().toString(), 
				"$('#anId').slider('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#enable()}.
	 */
	@Test
	public void testEnable() {
		Assert.assertNotNull(slider.enable());
		Assert.assertEquals(slider.enable().render().toString(), 
				"$('#anId').slider('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getMax()}.
	 */
	@Test
	public void testGetMax() {
		Assert.assertEquals(slider.getMax().intValue(), 10);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getMin()}.
	 */
	@Test
	public void testGetMin() {
		Assert.assertEquals(slider.getMin().intValue(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		Assert.assertNotNull(slider.getOptions());
		Assert.assertEquals(slider.getOptions().getJavaScriptOptions().toString(), "{min: 5.0, max: 10.0}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getOrientation()}.
	 */
	@Test
	public void testGetOrientation() {
		Assert.assertNotNull(slider.getOrientation());
		Assert.assertEquals(slider.getOrientation(), Orientation.HORIZONTAL);
		slider.setOrientation(Orientation.VERTICAL);
		Assert.assertEquals(slider.getOrientation(), Orientation.VERTICAL);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getRange()}.
	 */
	@Test
	public void testGetRange() {
		Assert.assertNotNull(slider.getRange());
		Assert.assertEquals(slider.getRange().getJavascriptOption().toString(), "false");
		slider.setRange(new SliderRange(RangeEnum.MAX));
		Assert.assertEquals(slider.getRange().getJavascriptOption().toString(), "'max'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getStep()}.
	 */
	@Test
	public void testGetStep() {
		Assert.assertEquals(slider.getStep(), 1);
		slider.setStep(5);
		Assert.assertEquals(slider.getStep(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getValue()}.
	 */
	@Test
	public void testGetValue() {
		Assert.assertEquals(slider.getValue(), 0);
		slider.setValue(5);
		Assert.assertEquals(slider.getValue(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getValues()}.
	 */
	@Test
	public void testGetValues() {
		Assert.assertNull(slider.getValues());
		ArrayItemOptions<IntegerItemOptions> array = new ArrayItemOptions<IntegerItemOptions>();
		array.add(new IntegerItemOptions(5));
		slider.setValues(array);
		Assert.assertNotNull(slider.getValues());
		Assert.assertEquals(slider.getValues().getJavascriptOption().toString(), "[5]");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#isAnimate()}.
	 */
	@Test
	public void testIsAnimate() {
		Assert.assertFalse(slider.isAnimate());
		slider.setAnimate(true);
		Assert.assertEquals(slider.isAnimate(), true);		
		slider.setAnimate(1000);
		Assert.assertEquals(slider.getAnimate().getJavascriptOption(), "1000");
		slider.setAnimate(-1000);
		Assert.assertEquals(slider.getAnimate().getJavascriptOption(), "1000");
		slider.unsetAnimate();
		Assert.assertFalse(slider.isAnimate());
		
		slider.setAnimate(AnimateEnum.FAST);
		Assert.assertTrue(slider.isAnimate());
		Assert.assertEquals(slider.getAnimate().getJavascriptOption(), "'fast'");
		
		slider.setAnimate(AnimateEnum.SLOW);
		Assert.assertTrue(slider.isAnimate());
		Assert.assertEquals(slider.getAnimate().getJavascriptOption(), "'slow'");
		
		slider.setAnimate(AnimateEnum.NORMAL);
		Assert.assertTrue(slider.isAnimate());
		Assert.assertEquals(slider.getAnimate().getJavascriptOption(), "'normal'");
		
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#isDisabled()}.
	 */
	@Test
	public void testIsDisabled() {
		Assert.assertFalse(slider.isDisabled());
		slider.setDisabled(true);
		Assert.assertTrue(slider.isDisabled());
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetChangeEvent() {
		Assert.assertEquals(slider.statement().render().toString(), 
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(slider.statement().render().toString().contains("change: function(event, ui) {\n\talert('event');\n}")); 
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#setSlideEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetSlideEvent() {
		Assert.assertEquals(slider.statement().render().toString(), 
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setSlideEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(slider.statement().render().toString().contains("slide: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStartEvent() {
		Assert.assertEquals(slider.statement().render().toString(), 
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(slider.statement().render().toString().contains("start: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}.
	 */
	@Test
	public void testSetStopEvent() {
		Assert.assertEquals(slider.statement().render().toString(), 
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		Assert.assertTrue(slider.statement().render().toString().contains("stop: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#statement()}.
	 */
	@Test
	public void testStatement() {
		Assert.assertNotNull(slider.statement());
		Assert.assertEquals(slider.statement().render().toString(), 
				"$('#anId').slider({min: 5.0, max: 10.0});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#value(int)}.
	 */
	@Test
	public void testValueInt() {
		Assert.assertNotNull(slider.value(6));
		Assert.assertEquals(slider.value(6).render().toString(), 
				"$('#anId').slider('value', 6);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#values(int, int)}.
	 */
	@Test
	public void testValuesIntInt() {
		Assert.assertNotNull(slider.values(6, 8));
		Assert.assertEquals(slider.values(6, 8).render().toString(), 
				"$('#anId').slider('values', 6, 8);");
	}
	
	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#widget()}.
	 */
	@Test
	public void testWidget() {
		Assert.assertNotNull(slider.widget());
		Assert.assertEquals(slider.widget().render().toString(), 
				"$('#anId').slider('widget');");
	}
}
