package org.odlabs.wiquery.commons;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

/**
 * Wicket abstract page to esaily test the insertion of wiQuery behaviors & 
 * components
 * @author Julien Roche
 *
 */
public abstract class WiqueryTestPage extends WebPage {
	// Constants
	public static final String MARKUP_ID = "testedWiquery";
	
	/**
	 * Default constructor
	 */
	public WiqueryTestPage() {
		super();
		
		WebMarkupContainer weMarkupContainer = new WebMarkupContainer(MARKUP_ID);
		weMarkupContainer.setOutputMarkupId(true);
		weMarkupContainer.setOutputMarkupPlaceholderTag(true);
		
		weMarkupContainer.add(getTestedComponent());		
		add(weMarkupContainer);
	}
	
	/**Method retrieving the wicket component where some wiQuery behaviors & 
	 * components were inserted
	 * @return the wicket component
	 */
	public abstract WebMarkupContainer getTestedComponent();
}
