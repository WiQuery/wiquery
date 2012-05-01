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
package org.odlabs.wiquery.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.string.Strings;
import org.odlabs.wiquery.core.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id: WiQueryAbstractAjaxBehavior.java 1143 2011-07-29 11:51:49Z hielke.hoeve@gmail.com
 * $
 * <p>
 * Link between {@link IWiQueryPlugin} and {@link AbstractDefaultAjaxBehavior}
 * </p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
@SuppressWarnings("deprecation")
public abstract class WiQueryAbstractAjaxBehavior extends AbstractDefaultAjaxBehavior implements
		IWiQueryPlugin
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6498661892490365888L;

	protected Options options = new Options();

	public final Options getOptions()
	{
		return options;
	}

	/**
	 * <p>
	 * Since wicket 6.0 {@link #statement()} is no longer needed, nearly all of WiQuery
	 * core's inner workings have been ported to Wicket 6.0. Use
	 * {@link #renderHead(Component, IHeaderResponse)} to render your statement.
	 * </p>
	 * <p>
	 * For backward compatibility we render the output of this function in an
	 * {@link OnDomReadyHeaderItem} if it is not empty. For your convenience this abstract
	 * class returns null so that nothing is rendered.
	 * <p>
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);

		JsStatement statement = statement();
		if (statement != null)
		{
			String statementString = statement.render().toString();
			if (!Strings.isEmpty(statementString))
			{
				response.render(OnDomReadyHeaderItem.forScript(statementString));
			}
		}
	}

	/**
	 * <p>
	 * Since wicket 6.0 this function is no longer needed, nearly all of WiQuery core's
	 * inner workings have been ported to Wicket 6.0. Use
	 * {@link #renderHead(Component, IHeaderResponse)} to render your statement.
	 * </p>
	 * <p>
	 * For backward compatibility we render the output of this function in an
	 * {@link OnDomReadyHeaderItem} if it is not empty. For your convenience this abstract
	 * class returns null so that nothing is rendered.
	 * <p>
	 * <p>
	 * If you decide to use this class and to override this function then make sure you do
	 * call this class' {@link #renderHead(Component, IHeaderResponse)} otherwise no
	 * statement will be rendered.
	 * <p>
	 * 
	 * @return The {@link JsStatement} corresponding to this component.
	 * @deprecated use {@link #renderHead(Component, IHeaderResponse)} to render your
	 *             statement.
	 */
	@Deprecated
	@Override
	public JsStatement statement()
	{
		return null;
	}

	@Override
	public CharSequence getCallbackFunctionBody(String... extraParameters)
	{
		return super.getCallbackFunctionBody(extraParameters);
	}

	public void setEventListener(String event, IWiqueryEventListener listener)
	{
		options.put(event, new AjaxEventCallback(this, event, listener));
	}

	@Override
	protected void respond(AjaxRequestTarget target)
	{
		IRequestParameters req = RequestCycle.get().getRequest().getRequestParameters();
		String eventName = req.getParameterValue("eventName").toString();
		IComplexOption callback = options.getComplexOption(eventName);
		if (callback instanceof AjaxEventCallback)
		{
			((AjaxEventCallback) callback).call(target, getComponent());
		}
	}
}