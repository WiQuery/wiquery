package org.odlabs.wiquery.core.commons.listener;

import org.apache.wicket.Application;
import org.odlabs.wiquery.core.commons.IWiQueryInitializer;
import org.odlabs.wiquery.core.commons.WiQueryRenderingListener;
import org.odlabs.wiquery.core.commons.WiQuerySettings;

public class WiQueryCoreInitializer implements IWiQueryInitializer {

	private static final long serialVersionUID = 1L;

	public void init(Application application, WiQuerySettings wiQuerySettings) {
		// add WiQueryRenderingListener to add the all knowing WiQueryCoreHeaderContributor
		application.getComponentPostOnBeforeRenderListeners().add(new WiQueryRenderingListener());
		
		//add JQueryCoreRenderingListener which will automatically add the jquery core to the page
		//duplicates will be ignored.
		wiQuerySettings.addListener(JQueryCoreRenderingListener.getInstance());
	}

}
