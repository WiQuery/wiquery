package org.wicketstuff.wiquery.home;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class HomePage extends MainTemplate {

	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return HomePage.class;
	}
}
