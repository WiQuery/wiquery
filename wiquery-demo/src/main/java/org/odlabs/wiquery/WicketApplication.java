package org.odlabs.wiquery;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.odlabs.wiquery.accordion.AccordionPage;
import org.odlabs.wiquery.autocomplete.AutocompletePage;
import org.odlabs.wiquery.datepicker.DatePickerPage;
import org.odlabs.wiquery.resizable.ResizePage;
import org.odlabs.wiquery.slider.SliderPage;
import org.odlabs.wiquery.sortable.SortablePage;
import org.odlabs.wiquery.tab.TabsPage;

/**
 * Application object for the wiQuery-demo web application. If you want to run this
 * application without deploying, run the WiqueryDemo class.
 * 
 * @see org.odlabs.wiquery.WiqueryDemo#main(String[])
 */
public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends WebPage> getHomePage() {
		return DatePickerPage.class;
	}

	@Override
	public void init() {
		super.init();
		
		getMarkupSettings().setStripWicketTags(true);
		
		// Interactions
		mountPage("/sortable/", SortablePage.class);
		
		// Widgets
		mountPage("/accordion/", AccordionPage.class);
		mountPage("/autocomplete/", AutocompletePage.class);
		mountPage("/datePicker/", DatePickerPage.class);
		mountPage("/resize/", ResizePage.class);
		mountPage("/slider/", SliderPage.class);
		mountPage("/tabs/", TabsPage.class);
	}
}
