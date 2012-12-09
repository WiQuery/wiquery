package org.odlabs.wiquery;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.odlabs.wiquery.tab.TabsPanel;

public class TabsPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public TabsPage(final PageParameters parameters) {
		super(parameters);

		add(new TabsPanel("panel"));

	}
}
