package org.wicketstuff.wiquery.progressbar;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class ProgressBarPage extends MainTemplate
{

	private static final long serialVersionUID = 1L;

	public ProgressBarPage(final PageParameters parameters)
	{
		super(parameters);

		add(new ProgressBarPanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage()
	{
		return ProgressBarPage.class;
	}
}
