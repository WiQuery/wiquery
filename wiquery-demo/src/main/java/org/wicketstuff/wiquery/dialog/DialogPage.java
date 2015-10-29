package org.wicketstuff.wiquery.dialog;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class DialogPage extends MainTemplate {

	private static final long serialVersionUID = 1L;

	public DialogPage(final PageParameters parameters) {
		super(parameters);

		add(new DialogPanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return DialogPage.class;
	}
}
