package org.odlabs.wiquery;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.odlabs.wiquery.slider.SliderPanel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	super(parameters);

    	add(new SliderPanel("panel"));

    }
}
