package org.odlabs.wiquery.core.commons;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.application.IComponentOnBeforeRenderListener;
import org.apache.wicket.behavior.IBehavior;

/**
 * 
 * WiQuery rendering listener which adds the WiQueryCoreHeaderContributor to components that require it.
 * 
 * @author Richard Wilkinson
 * @author Hielke Hoeve
 *
 */
class WiQueryRenderingListener implements IComponentOnBeforeRenderListener {

	public static final MetaDataKey<Boolean> WI_QUERY_RENDERED = new MetaDataKey<Boolean>() {
		private static final long serialVersionUID = 1L;
	};
	
	WiQueryRenderingListener() {
	}
	
	public void onBeforeRender(Component component) {
		Boolean wiQueryRendered = component.getMetaData(WI_QUERY_RENDERED);
		if (wiQueryRendered == null || !wiQueryRendered) {
			if (component instanceof IWiQueryPlugin) {
				// binding component as a plugin
				component.add(new WiQueryCoreHeaderContributor(component));
			}
			else{
				for (IBehavior curBehavior : component.getBehaviors()) {
					if (curBehavior instanceof IWiQueryPlugin) {
						component.add(new WiQueryCoreHeaderContributor(component));
						break;
					}
				}
			}
		}
	}

}
