package org.odlabs.wiquery.ui.listener;

import org.apache.wicket.Application;
import org.odlabs.wiquery.core.commons.IWiQueryInitializer;
import org.odlabs.wiquery.core.commons.WiQuerySettings;
import org.odlabs.wiquery.core.commons.listener.JQueryCoreRenderingListener;

public class WiQueryUIInitializer implements IWiQueryInitializer {

	private static final long serialVersionUID = 1L;

	public void init(Application application, WiQuerySettings wiQuerySettings) {
		//make sure that the core listener is registered before us! duplicates will be ignored.
		wiQuerySettings.addListener(JQueryCoreRenderingListener.getInstance());
		wiQuerySettings.addListener(JQueryUICoreRenderingListener.getInstance());
	}
}
