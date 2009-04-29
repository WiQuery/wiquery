package org.odlabs.wiquery.ui.themes;

import org.apache.wicket.ResourceReference;

public class WiQueryCoreThemeResourceReference extends ResourceReference {

	private static final long serialVersionUID = 6795863553105608280L;

	public WiQueryCoreThemeResourceReference(String theme) {
		super(WiQueryCoreThemeResourceReference.class, theme + "/jquery-ui-1.7.1.custom.css");
	}

}
