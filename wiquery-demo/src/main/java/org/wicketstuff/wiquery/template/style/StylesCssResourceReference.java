package org.wicketstuff.wiquery.template.style;

import org.apache.wicket.request.resource.CssResourceReference;

public final class StylesCssResourceReference extends CssResourceReference {

	private static final long serialVersionUID = -1937380425048540927L;
	
	private static final StylesCssResourceReference INSTANCE = new StylesCssResourceReference();

	private StylesCssResourceReference() {
		super(StylesCssResourceReference.class, "styles.css");
	}
	
	public static StylesCssResourceReference get() {
		return INSTANCE;
	}
}
