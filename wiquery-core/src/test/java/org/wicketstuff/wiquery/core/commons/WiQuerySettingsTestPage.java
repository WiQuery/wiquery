package org.wicketstuff.wiquery.core.commons;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class WiQuerySettingsTestPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.render(JavaScriptHeaderItem.forReference(WiQueryTestResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript("alert('test');"));
	}
}
