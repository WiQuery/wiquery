package org.wicketstuff.wiquery;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.wiquery.accordion.AccordionPage;
import org.wicketstuff.wiquery.autocomplete.AutocompletePage;
import org.wicketstuff.wiquery.button.ButtonPage;
import org.wicketstuff.wiquery.datepicker.DatePickerPage;
import org.wicketstuff.wiquery.dialog.DialogPage;
import org.wicketstuff.wiquery.home.HomePage;
import org.wicketstuff.wiquery.progressbar.ProgressBarPage;
import org.wicketstuff.wiquery.resizable.ResizePage;
import org.wicketstuff.wiquery.slider.SliderPage;
import org.wicketstuff.wiquery.sortable.SortablePage;
import org.wicketstuff.wiquery.tab.TabsPage;

/**
 * Application object for the wiQuery-demo web application. If you want to run this
 * application without deploying, run the WiqueryDemo class.
 * 
 */
public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
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
		mountPage("/button/", ButtonPage.class);
		mountPage("/datePicker/", DatePickerPage.class);
		mountPage("/dialog/", DialogPage.class);
		mountPage("/progressBar/", ProgressBarPage.class);
		mountPage("/resize/", ResizePage.class);
		mountPage("/slider/", SliderPage.class);
		mountPage("/tabs/", TabsPage.class);
	}
}
