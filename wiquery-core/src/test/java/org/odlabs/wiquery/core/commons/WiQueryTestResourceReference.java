package org.odlabs.wiquery.core.commons;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.odlabs.wiquery.core.resources.JavaScriptHeaderItems;
import org.odlabs.wiquery.core.util.WiQueryUtil;

public class WiQueryTestResourceReference extends JavaScriptResourceReference
{
	private static final long serialVersionUID = -4771815414204892357L;

	private static WiQueryTestResourceReference INSTANCE = new WiQueryTestResourceReference();

	private WiQueryTestResourceReference()
	{
		super(WiQueryTestResourceReference.class, "jquery.wiquery.test.js");
	}

	public static WiQueryTestResourceReference get()
	{
		return INSTANCE;
	}

	@Override
	public Iterable< ? extends HeaderItem> getDependencies()
	{
		return JavaScriptHeaderItems.forReferences(WiQueryUtil.getJQueryResourceReference());
	}
}
