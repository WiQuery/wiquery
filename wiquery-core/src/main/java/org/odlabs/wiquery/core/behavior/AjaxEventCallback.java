package org.odlabs.wiquery.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.options.IComplexOption;

public class AjaxEventCallback implements IComplexOption
{
	private static final long serialVersionUID = 1L;

	private IWiqueryEventListener listener;

	private String event;

	private WiQueryAbstractAjaxBehavior behavior;

	public AjaxEventCallback(WiQueryAbstractAjaxBehavior behavior, String event,
			IWiqueryEventListener listener)
	{
		this.behavior = behavior;
		this.event = event;
		this.listener = listener;
	}

	@Override
	public CharSequence getJavascriptOption()
	{
		StringBuilder callback = new StringBuilder();
		callback.append("function (eventObj, ui) {\n");
		callback.append("var eventName = '").append(event).append("';\n");
		callback.append("var event = window.JSON.stringify(eventObj);\n");
		callback.append(behavior.getCallbackFunctionBody("eventName", "event"));
		callback.append("}\n");
		return callback;
	}

	public void call(AjaxRequestTarget target, Component source)
	{
		listener.onEvent(target, source);
	}
}
