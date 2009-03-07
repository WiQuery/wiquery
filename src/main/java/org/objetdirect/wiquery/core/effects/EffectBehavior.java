/*
 * Copyright (c) 2008 Objet Direct
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
package org.objetdirect.wiquery.core.effects;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.objetdirect.wiquery.core.commons.CoreJavaScriptHeaderContributor;
import org.objetdirect.wiquery.core.javascript.JsQuery;

/**
 * $Id$
 * <p>
 * Attach an effect to the given component.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 * @see Effect
 * @see EffectSpeed
 */
public class EffectBehavior extends AbstractBehavior {

	private static final long serialVersionUID = 3597955113451275208L;

	/**
	 * The attached effect.
	 */
	private Effect effect;

	/**
	 * The bind component.
	 */
	private Component component;

	/**
	 * Builds a new instance of {@link EffectBehavior}.
	 */
	public EffectBehavior(Effect effect) {
		super();
		this.effect = effect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.behavior.AbstractBehavior#bind(
	 *      org.apache.wicket.Component)
	 */
	@Override
	public void bind(Component component) {
		super.bind(component);
		this.component = component;
		component.add(new HeaderContributor(
				new CoreJavaScriptHeaderContributor()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.behavior.AbstractBehavior#renderHead(
	 *      org.apache.wicket.markup.html.IHeaderResponse)
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		// renders the JavaScript statement to bind this effect
		JsQuery query = new JsQuery(this.component);
		query.$().chain(this.effect);
		query.contribute(this.component);
	}

}
