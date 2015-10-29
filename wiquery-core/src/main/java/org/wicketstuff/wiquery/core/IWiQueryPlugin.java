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
package org.wicketstuff.wiquery.core;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.wicketstuff.wiquery.core.javascript.JsStatement;

/**
 * <p>
 * Since wicket 6.0 this interface is no longer needed, nearly all of WiQuery core's inner
 * workings have been ported to Wicket 6.0.
 * </p>
 * 
 * <p>
 * You can use an OnDomReadyHeaderItem to render the CharSequence that statement()
 * returns.
 * </p>
 * 
 * <p>
 * <b> This class will be removed in the next version. </b>
 * </p>
 * 
 * <strike>
 * <p>
 * The {@link IWiQueryPlugin} interface defines all WiQuery plugins, e.g. all
 * components/behaviors outputting jQuery statements to function.
 * </p>
 * <p>
 * WiQuery plugins are components or behaviors generating JavaScript jQuery code. WiQuery
 * manages the component or behavior, outputs the JavaScript needed to function and
 * imports all JavaScript resources needed by the component or behavior.
 * </p>
 * <p>
 * The contribute method is not needed since wicket 1.5 as Component and or Behaviors
 * themselves are contributers. Simply override
 * {@link Component#renderHead(IHeaderResponse)} or
 * {@link Behavior#renderHead(Component, IHeaderResponse)}).
 * </p>
 * </strike>
 * 
 * @author Lionel Armanet
 * @since 0.5
 * @see JsStatement
 * @deprecated Since wicket 6.0 this interface is no longer needed, nearly all of WiQuery
 *             core's inner workings have been ported to Wicket 6.0. Use
 *             {@link Component#renderHead(IHeaderResponse)} to render your statement.
 */
@Deprecated
public interface IWiQueryPlugin
{

	/**
	 * <strike>Returns the main {@link JsStatement} used to create the plugin.</strike>
	 * <p>
	 * Since wicket 6.0 this interface is no longer needed, nearly all of WiQuery core's
	 * inner workings have been ported to Wicket 6.0. Use
	 * {@link Component#renderHead(IHeaderResponse)} to render your statement.
	 * </p>
	 * 
	 * @return The {@link JsStatement} corresponding to this component.
	 * @deprecated use {@link Component#renderHead(IHeaderResponse)} to render your
	 *             statement.
	 */
	@Deprecated
	JsStatement statement();

}
