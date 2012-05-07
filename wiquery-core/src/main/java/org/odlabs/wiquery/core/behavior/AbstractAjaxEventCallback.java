package org.odlabs.wiquery.core.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
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
		StringBuilder callback = new StringBuilder();
		callback.append("function (event, ui) {\n");
		Map<String, String> extraParameters = getExtraParameters();
		extraParameters.put("eventName", "'" + event + "'");
		List<String> names = new ArrayList<String>(extraParameters.keySet());
		for (Entry<String, String> curParameter : extraParameters.entrySet())
		{
			callback.append("var ").append(curParameter.getKey()).append(" = ")
				.append(curParameter.getValue()).append(";\n");
		}
		callback.append(behavior.getCallbackFunctionBody(names.toArray(new String[names.size()])));
		callback.append("}\n");
		return callback;
	}

	protected Component findComponentById(String id)
	{
		if (id == null)
			return null;

		MarkupIdVisitor visitor = new MarkupIdVisitor(id);
		getBehavior().getBehaviorComponent().getPage().visitChildren(visitor);
		return visitor.getFoundComponent();
	}

	protected Map<String, String> getExtraParameters()
	{
		return new HashMap<String, String>();
	}

	public abstract void call(AjaxRequestTarget target, Component source);
}
