package org.odlabs.wiquery;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.odlabs.wiquery.resizable.ResizePanel;

public class ResizePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public ResizePage(final PageParameters parameters) {
		super(parameters);

		add(new ResizePanel("panel"));

	}
}
