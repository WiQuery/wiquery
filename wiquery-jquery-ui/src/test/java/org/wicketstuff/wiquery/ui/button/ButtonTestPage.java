/**
 * 
 */
package org.wicketstuff.wiquery.ui.button;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.ResourceModel;
import org.wicketstuff.wiquery.ui.button.ButtonBehavior;

/**
 * @author Ernesto Reinaldo Barreiro
 */
public class ButtonTestPage extends WebPage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ButtonBehavior behavior;
	
	private boolean addBefore = false;

	/**
	 * Construtor.
	 */
	public ButtonTestPage()
	{
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		behavior = new ButtonBehavior();
		if (addBefore)
			behavior.setLabel(new ResourceModel("key"));
		WebMarkupContainer link = new WebMarkupContainer("link");
		link.add(behavior);
		if (!addBefore)
			behavior.setLabel(new ResourceModel("key"));
		add(link);
	}

	public ButtonBehavior getBehavior()
	{
		return behavior;
	}

	public boolean isAddBefore() {
		return addBefore;
	}

	public void setAddBefore(boolean addBefore) {
		this.addBefore = addBefore;
	}
}
