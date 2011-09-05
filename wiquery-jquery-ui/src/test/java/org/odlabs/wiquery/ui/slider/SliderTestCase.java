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

import static org.junit.Assert.*;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.junit.Before;
import org.junit.Test;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.tester.WiQueryTestCase;
import org.odlabs.wiquery.ui.DivTestPanel;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.slider.Slider.Orientation;
import org.odlabs.wiquery.ui.slider.SliderAnimate.AnimateEnum;
import org.odlabs.wiquery.ui.slider.SliderRange.RangeEnum;

/**
 * Test on {@link Slider}
 * 
 * @author Julien Roche
 */
public class SliderTestCase extends WiQueryTestCase
{
	// Properties
	private Slider slider;

	@Override
	@Before
	public void setUp()
	{
		super.setUp();

		tester.startPanel(new ITestPanelSource()
		{
			private static final long serialVersionUID = 1L;

			public Panel getTestPanel(String panelId)
			{
				Panel panel = new DivTestPanel(panelId);
				slider = new Slider("anId", 5, 10);
				slider.setMarkupId(slider.getId());
				panel.add(slider);
				return panel;
			}
		});
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#destroy()}.
	 */
	@Test
	public void testDestroy()
	{
		assertNotNull(slider.destroy());
		assertEquals(slider.destroy().render().toString(), "$('#anId').slider('destroy');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#disable()}.
	 */
	@Test
	public void testDisable()
	{
		assertNotNull(slider.disable());
		assertEquals(slider.disable().render().toString(), "$('#anId').slider('disable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#enable()}.
	 */
	@Test
	public void testEnable()
	{
		assertNotNull(slider.enable());
		assertEquals(slider.enable().render().toString(), "$('#anId').slider('enable');");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getMax()}.
	 */
	@Test
	public void testGetMax()
	{
		assertEquals(slider.getMax().intValue(), 10);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getMin()}.
	 */
	@Test
	public void testGetMin()
	{
		assertEquals(slider.getMin().intValue(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getOptions()}.
	 */
	@Test
	public void testGetOptions()
	{
		assertNotNull(slider.getOptions());
		assertEquals(slider.getOptions().getJavaScriptOptions().toString(), "{min: 5.0, max: 10.0}");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getOrientation()}.
	 */
	@Test
	public void testGetOrientation()
	{
		assertNotNull(slider.getOrientation());
		assertEquals(slider.getOrientation(), Orientation.HORIZONTAL);
		slider.setOrientation(Orientation.VERTICAL);
		assertEquals(slider.getOrientation(), Orientation.VERTICAL);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getRange()}.
	 */
	@Test
	public void testGetRange()
	{
		assertNotNull(slider.getRange());
		assertEquals(slider.getRange().getJavascriptOption().toString(), "false");
		slider.setRange(new SliderRange(RangeEnum.MAX));
		assertEquals(slider.getRange().getJavascriptOption().toString(), "'max'");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getStep()}.
	 */
	@Test
	public void testGetStep()
	{
		assertEquals(slider.getStep(), 1);
		slider.setStep(5);
		assertEquals(slider.getStep(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getValue()}.
	 */
	@Test
	public void testGetValue()
	{
		assertEquals(slider.getValue(), 0);
		slider.setValue(5);
		assertEquals(slider.getValue(), 5);
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#getValues()}.
	 */
	@Test
	public void testGetValues()
	{
		assertNull(slider.getValues());
		ArrayItemOptions<IntegerItemOptions> array = new ArrayItemOptions<IntegerItemOptions>();
		array.add(new IntegerItemOptions(5));
		slider.setValues(array);
		assertNotNull(slider.getValues());
		assertEquals(slider.getValues().getJavascriptOption().toString(), "[5]");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#isAnimate()}.
	 */
	@Test
	public void testIsAnimate()
	{
		assertFalse(slider.isAnimate());
		slider.setAnimate(true);
		assertEquals(slider.isAnimate(), true);
		slider.setAnimate(1000);
		assertEquals(slider.getAnimate().getJavascriptOption(), "1000");
		slider.setAnimate(-1000);
		assertEquals(slider.getAnimate().getJavascriptOption(), "1000");
		slider.unsetAnimate();
		assertFalse(slider.isAnimate());

		slider.setAnimate(AnimateEnum.FAST);
		assertTrue(slider.isAnimate());
		assertEquals(slider.getAnimate().getJavascriptOption(), "'fast'");

		slider.setAnimate(AnimateEnum.SLOW);
		assertTrue(slider.isAnimate());
		assertEquals(slider.getAnimate().getJavascriptOption(), "'slow'");

		slider.setAnimate(AnimateEnum.NORMAL);
		assertTrue(slider.isAnimate());
		assertEquals(slider.getAnimate().getJavascriptOption(), "'normal'");

	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#isDisabled()}.
	 */
	@Test
	public void testIsDisabled()
	{
		assertFalse(slider.isDisabled());
		slider.setDisabled(true);
		assertTrue(slider.isDisabled());
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.slider.Slider#setChangeEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetChangeEvent()
	{
		assertEquals(slider.statement().render().toString(),
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setChangeEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertTrue(slider.statement().render().toString()
			.contains("change: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.slider.Slider#setSlideEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetSlideEvent()
	{
		assertEquals(slider.statement().render().toString(),
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setSlideEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertTrue(slider.statement().render().toString()
			.contains("slide: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.slider.Slider#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStartEvent()
	{
		assertEquals(slider.statement().render().toString(),
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setStartEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertTrue(slider.statement().render().toString()
			.contains("start: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for
	 * {@link org.odlabs.wiquery.ui.slider.Slider#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)}
	 * .
	 */
	@Test
	public void testSetStopEvent()
	{
		assertEquals(slider.statement().render().toString(),
			"$('#anId').slider({min: 5.0, max: 10.0});");
		slider.setStopEvent(JsScopeUiEvent.quickScope("alert('event');"));
		assertTrue(slider.statement().render().toString()
			.contains("stop: function(event, ui) {\n\talert('event');\n}"));
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#statement()}.
	 */
	@Test
	public void testStatement()
	{
		assertNotNull(slider.statement());
		assertEquals(slider.statement().render().toString(),
			"$('#anId').slider({min: 5.0, max: 10.0});");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#value(int)}.
	 */
	@Test
	public void testValueInt()
	{
		assertNotNull(slider.value(6));
		assertEquals(slider.value(6).render().toString(), "$('#anId').slider('value', 6);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#values(int, int)}.
	 */
	@Test
	public void testValuesIntInt()
	{
		assertNotNull(slider.values(6, 8));
		assertEquals(slider.values(6, 8).render().toString(), "$('#anId').slider('values', 6, 8);");
	}

	/**
	 * Test method for {@link org.odlabs.wiquery.ui.slider.Slider#widget()}.
	 */
	@Test
	public void testWidget()
	{
		assertNotNull(slider.widget());
		assertEquals(slider.widget().render().toString(), "$('#anId').slider('widget');");
	}
}
