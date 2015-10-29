/**
 * 
 */
package org.wicketstuff.wiquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.wiquery.ui.dialog.AjaxDialogButton;
import org.wicketstuff.wiquery.ui.dialog.Dialog;

/**
 * @author Ernesto Reinaldo
 */
public class AjaxDialogTestPage extends WebPage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dialog dialog;

	private AjaxDialogButton ok;

	private AjaxDialogButton cancel;

	/**
	 * 
	 */
	public AjaxDialogTestPage()
	{
		ok = new AjaxDialogButton("Ok")
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void onButtonClicked(AjaxRequestTarget target)
			{
				// TODO Auto-generated method stub

			}
		};
		cancel = new AjaxDialogButton("Ok")
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void onButtonClicked(AjaxRequestTarget target)
			{
				// TODO Auto-generated method stub

			}
		};
		dialog = new Dialog("dialog");
		dialog.setTitle("Test");
		add(dialog);
		dialog.setButtons(ok, cancel);
	}

	public Dialog getDialog()
	{
		return dialog;
	}

	public AjaxDialogButton getOk()
	{
		return ok;
	}

	public AjaxDialogButton getCancel()
	{
		return cancel;
	}
}
