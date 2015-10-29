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
package org.wicketstuff.wiquery.core.effects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.wicketstuff.wiquery.core.effects.Effect;
import org.wicketstuff.wiquery.core.effects.EffectSpeed;
import org.wicketstuff.wiquery.core.javascript.JsScope;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.tester.WiQueryTestCase;

/**
 * Test on {@link EffectTest}
 * 
 * @author Julien Roche
 */
public class EffectTestCase extends WiQueryTestCase
{
	/**
	 * Test method for {@link org.wicketstuff.wiquery.core.effects.Effect#statementArgs()}.
	 */
	@Test
	public void testStatementArgs()
	{
		// Test with no parameters
		assertEquals(new JsStatement().$(null, "#aComponent").chain(new EffectTest()).render()
			.toString(), "$('#aComponent').anEffect();");

		// Test with a parameter
		assertEquals(new JsStatement().$(null, "#aComponent").chain(new EffectTest("'aaa'"))
			.render().toString(), "$('#aComponent').anEffect('aaa');");

		// Test with a speed and a parameter
		assertEquals(
			new JsStatement().$(null, "#aComponent")
				.chain(new EffectTest(EffectSpeed.SLOW, "'aaa'")).render().toString(),
			"$('#aComponent').anEffect('slow', 'aaa');");

		// Test with a speed, a parameter and a callback
		assertEquals(
			new JsStatement()
				.$(null, "#aComponent")
				.chain(
					new EffectTest(EffectSpeed.SLOW, JsScope.quickScope("alert('test');"), "'aaa'"))
				.render().toString(),
			"$('#aComponent').anEffect('slow', 'aaa', function() {\n\talert('test');\n});");
	}

	/**
	 * @author Julien Roche
	 */
	private class EffectTest extends Effect
	{
		private static final long serialVersionUID = 1L;

		public EffectTest(CharSequence... parameters)
		{
			super(parameters);
		}

		public EffectTest(EffectSpeed effectSpeed, CharSequence... parameters)
		{
			super(effectSpeed, parameters);
		}

		public EffectTest(EffectSpeed effectSpeed, JsScope callback, CharSequence... parameters)
		{
			super(effectSpeed, callback, parameters);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String chainLabel()
		{
			return "anEffect";
		}
	}
}
