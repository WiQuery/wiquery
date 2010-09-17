package org.objetdirect.wiquery.ui.accordion;

import org.objetdirect.wiquery.commons.WickextTestPage;
import org.objetdirect.wiquery.core.commons.WickextPlugin;
import org.objetdirect.wiquery.ui.accordion.Accordion;

public class AccordionTestCasePage extends WickextTestPage {

	private Accordion accordion;
	
	public AccordionTestCasePage() {
		super();
		accordion = new Accordion("accordion");
		add(accordion);
	}

	@Override
	public WickextPlugin getPluginToTest() {
		return accordion;
	}

}
