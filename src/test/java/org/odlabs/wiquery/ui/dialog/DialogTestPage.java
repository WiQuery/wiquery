package org.odlabs.wiquery.ui.dialog;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.commons.WiqueryTestPage;

/**
 * Test page for the dialog component
 * @author Julien Roche
 *
 */
public class DialogTestPage extends WiqueryTestPage {
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.commons.WiqueryTestPage#getTestedComponent()
	 */
	@Override
	public WebMarkupContainer getTestedComponent() {
		return new Dialog("dialogDiv");
	}
}
