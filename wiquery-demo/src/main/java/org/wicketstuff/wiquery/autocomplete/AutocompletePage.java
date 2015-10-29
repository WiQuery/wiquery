package org.wicketstuff.wiquery.autocomplete;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class AutocompletePage extends MainTemplate {

	private static final long serialVersionUID = 1L;

	public AutocompletePage(final PageParameters parameters) {
		super(parameters);

		add(new AutocompletePanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return AutocompletePage.class;
	}
}
