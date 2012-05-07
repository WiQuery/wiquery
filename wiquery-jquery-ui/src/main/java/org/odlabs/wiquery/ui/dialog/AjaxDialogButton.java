package org.odlabs.wiquery.ui.dialog;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.behavior.AbstractAjaxEventCallback;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;

/**
 * A version of DialogButton that integrates with wicket AJAX.
 * 
 * @author Ernesto Reinaldo Barreiro
 */
public abstract class AjaxDialogButton extends DialogButton
{

	private static final long serialVersionUID = 1L;

	private class AjaxDialogButtonCallback extends AbstractAjaxEventCallback
	{
		private static final long serialVersionUID = 1L;

		public AjaxDialogButtonCallback()
		{
			super(getTitle());
		}

		@Override
		public void call(AjaxRequestTarget target, Component source)
		{
			onButtonClicked(target);
		}
	}

	/**
	 * Constructor.
	 */
	public AjaxDialogButton(String title)
	{
		super(title, null);
	}

	public void activateCallback(WiQueryAbstractAjaxBehavior ajaxBehavior)
	{
		AjaxDialogButtonCallback callback = new AjaxDialogButtonCallback();
		callback.setBehavior(ajaxBehavior);
		setCallback(callback);
	}

	protected abstract void onButtonClicked(AjaxRequestTarget target);

}
