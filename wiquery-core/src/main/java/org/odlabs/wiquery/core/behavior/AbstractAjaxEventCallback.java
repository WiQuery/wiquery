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
