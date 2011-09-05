package org.odlabs.wiquery.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.odlabs.wiquery.core.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsStatement;

// abstract entry point for all wiquery behaviors
public abstract class WiQueryAbstractBehavior extends Behavior implements IWiQueryPlugin
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 6498661892490365888L;

	// Properties
	private Component component;

	@Override
	public void bind(Component component)
	{
		this.component = component;
		super.bind(component);
	}

	/**
	 * @return the binded component
	 */
	public Component getComponent()
	{
		return component;
	}

	public abstract JsStatement statement();
}