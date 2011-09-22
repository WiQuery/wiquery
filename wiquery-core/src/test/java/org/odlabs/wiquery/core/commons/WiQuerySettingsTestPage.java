package org.odlabs.wiquery.core.commons;

import org.apache.wicket.markup.html.WebPage;
import org.odlabs.wiquery.core.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsStatement;

public class WiQuerySettingsTestPage extends WebPage implements IWiQueryPlugin
{

	private static final long serialVersionUID = 1L;

	public JsStatement statement()
	{
		return new JsStatement();
	}

}
