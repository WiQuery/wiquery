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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.CallbackParameter;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.util.MarkupIdVisitor;

public abstract class AbstractAjaxEventCallback implements IComplexOption
{
	private static final long serialVersionUID = 1L;

	private String event;

	private WiQueryAbstractAjaxBehavior behavior;

	public AbstractAjaxEventCallback(String event)
	{
		this.event = event;
	}

	public String getEvent()
	{
		return event;
	}

	public WiQueryAbstractAjaxBehavior getBehavior()
	{
		return behavior;
	}

	public void setBehavior(WiQueryAbstractAjaxBehavior behavior)
	{
		this.behavior = behavior;
	}

	@Override
	public CharSequence getJavascriptOption()
	{
		List<CallbackParameter> extraParameters = getExtraParameters();
		return behavior.getCallbackFunction(extraParameters
			.toArray(new CallbackParameter[extraParameters.size()]));
	}

	protected Component findComponentById(String id)
	{
		if (id == null)
			return null;

		MarkupIdVisitor visitor = new MarkupIdVisitor(id);
		getBehavior().getBehaviorComponent().getPage().visitChildren(visitor);
		return visitor.getFoundComponent();
	}

	protected List<CallbackParameter> getExtraParameters()
	{
		List<CallbackParameter> ret = new ArrayList<CallbackParameter>();
		ret.add(CallbackParameter.context("event"));
		ret.add(CallbackParameter.context("ui"));
		ret.add(CallbackParameter.resolved("eventName", "'" + event + "'"));
		return ret;
	}

	public abstract void call(AjaxRequestTarget target, Component source);
}
