package org.objetdirect.wiquery.commons;

import org.apache.wicket.markup.html.WebPage;
import org.objetdirect.wiquery.core.commons.WickextPlugin;

public abstract class WickextTestPage extends WebPage {

	public abstract WickextPlugin getPluginToTest();
	
}
