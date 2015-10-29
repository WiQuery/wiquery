package org.wicketstuff.wiquery.datepicker;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.wiquery.template.MainTemplate;

public class DatePickerPage extends MainTemplate {

	private static final long serialVersionUID = 1L;

	public DatePickerPage(final PageParameters parameters) {
		super(parameters);

		add(new DatePickerPanel("panel"));
	}

	@Override
	protected Class<? extends WebPage> getMenuPage() {
		return DatePickerPage.class;
	}
}
