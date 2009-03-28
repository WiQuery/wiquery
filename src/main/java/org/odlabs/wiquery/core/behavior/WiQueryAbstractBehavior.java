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

	private Component component;

	@Override
	public void bind(Component component) {
		this.component = component;
		WiQueryCoreHeaderContributor contributor 
			= new WiQueryCoreHeaderContributor();
		contributor.addPlugin(this);
		component.add(new HeaderContributor(contributor));
		super.bind(component);
	}

	public Component getComponent() {
		return component;
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		
	}

	public abstract JsStatement statement();
	
}
