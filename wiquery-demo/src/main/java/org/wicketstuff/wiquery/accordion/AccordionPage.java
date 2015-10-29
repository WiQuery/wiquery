package org.wicketstuff.wiquery.accordion;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class AccordionPage extends MainTemplate {

	private static final long serialVersionUID = 1L;

	public AccordionPage(final PageParameters parameters) {
		super(parameters);

		add(new AccordionPanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return AccordionPage.class;
	}
}
