package org.wicketstuff.wiquery.slider;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class SliderPage extends MainTemplate
{

	private static final long serialVersionUID = 1L;

	public SliderPage(final PageParameters parameters)
	{
		super(parameters);

		add(new SliderPanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage()
	{
		return SliderPage.class;
	}
}
