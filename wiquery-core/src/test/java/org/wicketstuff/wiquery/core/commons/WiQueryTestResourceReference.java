package org.wicketstuff.wiquery.core.commons;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.wicketstuff.wiquery.core.resources.JavaScriptHeaderItems;
import org.wicketstuff.wiquery.core.util.WiQueryUtil;

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
	public List<HeaderItem> getDependencies()
	{
		return JavaScriptHeaderItems.forReferences(WiQueryUtil.getJQueryResourceReference());
	}
}
