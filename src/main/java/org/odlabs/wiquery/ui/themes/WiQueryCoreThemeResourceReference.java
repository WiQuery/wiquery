package org.odlabs.wiquery.ui.themes;

import org.apache.wicket.ResourceReference;

public class WiQueryCoreThemeResourceReference extends ResourceReference {

	private static final long serialVersionUID = 6795863553105608280L;

	public WiQueryCoreThemeResourceReference(String theme) {
		super(WiQueryCoreThemeResourceReference.class, theme + "/ui.all.css");
	}

}
