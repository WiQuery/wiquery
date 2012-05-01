package org.odlabs.wiquery.core.behavior;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public interface IWiqueryEventListener extends Serializable
{
	public void onEvent(AjaxRequestTarget target, Component source);
}
