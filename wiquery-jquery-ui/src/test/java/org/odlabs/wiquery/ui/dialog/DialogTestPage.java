/**
 * 
 */
package org.odlabs.wiquery.ui.dialog;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.ResourceModel;

/**
 * @author Ernesto Reinaldo
 */
public class DialogTestPage extends WebPage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dialog dialog;

	/**
	 * 
	 */
	public DialogTestPage()
	{
		dialog = new Dialog("dialog");
		dialog.setTitle(new ResourceModel("title"));
		add(dialog);
	}

	public Dialog getDialog()
	{
		return dialog;
	}
}
