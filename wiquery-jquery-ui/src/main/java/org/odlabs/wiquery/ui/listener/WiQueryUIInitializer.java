package org.odlabs.wiquery.ui.listener;

import org.apache.wicket.Application;
import org.odlabs.wiquery.core.commons.IWiQueryInitializer;
import org.odlabs.wiquery.core.commons.WiQuerySettings;

public class WiQueryUIInitializer implements IWiQueryInitializer {

	private static final long serialVersionUID = 1L;

	public void init(Application application, WiQuerySettings wiQuerySettings) {
		wiQuerySettings.addListener(new JQueryUICoreRenderingListener());
	}
}
