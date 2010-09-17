package org.odlabs.wiquery.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.HeaderContributor;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryCoreHeaderContributor;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;

// abstract entry point for all wiquery behaviors
public abstract class WiQueryAbstractBehavior extends AbstractBehavior implements IWiQueryPlugin {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 6498661892490365888L;
	
	// Properties
	private Component component;

	/* (non-Javadoc)
	 * @see org.apache.wicket.behavior.AbstractBehavior#bind(org.apache.wicket.Component)
	 */
	@Override
	public void bind(Component component) {
		this.component = component;
		component.add(new HeaderContributor(new WiQueryCoreHeaderContributor()));
		super.bind(component);
	}

	/**
	 * @return the binded component
	 */
	public Component getComponent() {
		return component;
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public abstract JsStatement statement();
	
}
