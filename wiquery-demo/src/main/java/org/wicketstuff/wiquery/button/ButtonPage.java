package org.wicketstuff.wiquery.button;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class ButtonPage extends MainTemplate {

	private static final long serialVersionUID = 1L;

	public ButtonPage(final PageParameters parameters) {
		super(parameters);

		add(new ButtonPanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return ButtonPage.class;
	}
}
