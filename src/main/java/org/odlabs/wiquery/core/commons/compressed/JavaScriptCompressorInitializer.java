package org.odlabs.wiquery.core.commons.compressed;

import org.apache.wicket.Application;
import org.odlabs.wiquery.core.commons.IWiQueryInitializer;
import org.odlabs.wiquery.core.commons.WiQuerySettings;

public class JavaScriptCompressorInitializer implements IWiQueryInitializer {

	private static final long serialVersionUID = 1L;

	public void init(Application application, WiQuerySettings wiQuerySettings) {
		if (Application.DEPLOYMENT.equalsIgnoreCase(application
				.getConfigurationType())) {
			application.getResourceSettings().setJavascriptCompressor(
					new YUIJavaScriptCompressor());
		}
	}

}
