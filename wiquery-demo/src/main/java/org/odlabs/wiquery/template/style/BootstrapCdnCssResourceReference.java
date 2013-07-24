package org.odlabs.wiquery.template.style;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

public final class BootstrapCdnCssResourceReference extends UrlResourceReference {

	private static final long serialVersionUID = -6024518060296236148L;

	private static final BootstrapCdnCssResourceReference INSTANCE = new BootstrapCdnCssResourceReference();

	private BootstrapCdnCssResourceReference() {
		super(Url.parse("//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css"));
	}
	
	public static BootstrapCdnCssResourceReference get() {
		return INSTANCE;
	}
}
