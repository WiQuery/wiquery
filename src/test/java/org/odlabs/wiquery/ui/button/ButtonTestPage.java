/**
 * 
 */
package org.odlabs.wiquery.ui.button;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.ResourceModel;

/**
 * @author Ernesto Reinaldo Barreiro
 */
public class ButtonTestPage extends WebPage {

	private ButtonBehavior behavior;

	/**
	 * Construtor.
	 */
	public ButtonTestPage(boolean addBefore) {
		behavior = new ButtonBehavior();
		if (addBefore)
			behavior.setLabel(new ResourceModel("key"));
		WebMarkupContainer link = new WebMarkupContainer("link");
		link.add(behavior);
		if (!addBefore)
			behavior.setLabel(new ResourceModel("key"));
		add(link);
	}

	public ButtonBehavior getBehavior() {
		return behavior;
	}
}
