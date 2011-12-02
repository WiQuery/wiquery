package org.odlabs.wiquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;

/**
 * A version of DialogButton that integrates with wicket AJAX.
 * 
 * @author Ernesto Reinaldo Barreiro
 */
public abstract class AjaxDialogButton extends DialogButton
{

	private static final long serialVersionUID = 1L;

	public static class AjaxDialogScope extends JsScope
	{

		private static final long serialVersionUID = 1L;

		private Dialog dialog;

		private String title;

		public AjaxDialogScope(String title, Dialog dialog)
		{
			this.title = title;
			this.dialog = dialog;
		}

		@Override
		protected void execute(JsScopeContext scopeContext)
		{
			scopeContext.append(new StringBuffer().append("var url = '")
				.append(getDialog().getAjaxBehavior().getCallbackUrl()).append("&")
				.append(Dialog.BUTTON_ID).append("=").append(title)
				.append("';")
				// delegating call-back generation to AJAX behavior
				// so that we don't miss 'decorator' related functionality.
				.append(getDialog().getAjaxBehavior().generateCallbackScript("wicketAjaxGet(url"))
				.toString());

		}

		public Dialog getDialog()
		{
			return dialog;
		}

		public void setDialog(Dialog dialog)
		{
			this.dialog = dialog;
		}
	}

	/**
	 * Constructor.
	 */
	public AjaxDialogButton(String title)
	{
		super(title, null);
	}

	protected abstract void onButtonClicked(AjaxRequestTarget target);

}
