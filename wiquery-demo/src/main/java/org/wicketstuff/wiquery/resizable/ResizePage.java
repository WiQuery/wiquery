package org.wicketstuff.wiquery.resizable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class ResizePage extends MainTemplate {

	private static final long serialVersionUID = 1L;

	public ResizePage(final PageParameters parameters) {
		super(parameters);

		add(new ResizePanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return ResizePage.class;
	}
}
