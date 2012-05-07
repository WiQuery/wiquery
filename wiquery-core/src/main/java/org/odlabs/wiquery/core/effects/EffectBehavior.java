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
package org.odlabs.wiquery.core.effects;

import org.odlabs.wiquery.core.behavior.AbstractAjaxEventCallback;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id: EffectBehavior.java 1714 2011-09-22 20:38:30Z hielke.hoeve $
 * <p>
 * Attach an effect to the given component.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 * @see Effect
 * @see EffectSpeed
 */
public class EffectBehavior extends WiQueryAbstractAjaxBehavior
{
	public abstract static class AjaxEffectCallback extends AbstractAjaxEventCallback
	{
		private static final long serialVersionUID = 1L;

		public AjaxEffectCallback()
		{
			super("callback");
		}
	}

	private static final long serialVersionUID = 3597955113451275208L;

	/**
	 * The attached effect.
	 */
	private Effect effect;

	/**
	 * Builds a new instance of {@link EffectBehavior}.
	 */
	public EffectBehavior(Effect effect)
	{
		super();
		this.effect = effect;
	}

	@Deprecated
	@Override
	public JsStatement statement()
	{
		JsQuery query = new JsQuery(getComponent());
		query.$().chain(this.effect);
		return query.getStatement();
	}

	public void setCallback(AjaxEffectCallback callback)
	{
		setEventListener(callback);
	}
}
