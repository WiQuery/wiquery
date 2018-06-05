package org.wicketstuff.wiquery.progressbar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.wicketstuff.wiquery.ui.core.JsScopeUiEvent;
import org.wicketstuff.wiquery.ui.progressbar.ProgressBar;

/**
 * @author Stephane Gleizes
 */
public class ProgressBarPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public ProgressBarPanel(String id)
	{
		super(id);

		// Indeterminate progress bar
		final ProgressBar indeterminateProgressBar = new ProgressBar("indeterminateProgressBar");
		indeterminateProgressBar.setValue(false);
		indeterminateProgressBar.setMax(50);
		add(indeterminateProgressBar);

		// Classic progress bar
		final ProgressBar classicProgressBar = new ProgressBar("classicProgressBar");
		classicProgressBar.setValue(10);
		classicProgressBar.setMax(20);
		classicProgressBar
			.setChangeEvent(JsScopeUiEvent.quickScope(indeterminateProgressBar.increment()));
		classicProgressBar
			.setCompleteEvent(JsScopeUiEvent.quickScope(indeterminateProgressBar.disable()));
		add(classicProgressBar);

		// Increment link
		final AjaxLink<Void> incrementLink = new AjaxLink<Void>("incrementLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				target.appendJavaScript(classicProgressBar.increment().render());
			}
		};
		add(incrementLink);

		// Decrement link
		final AjaxLink<Void> decrementLink = new AjaxLink<Void>("decrementLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				target.appendJavaScript(classicProgressBar.decrement().render());
			}
		};
		add(decrementLink);
	}
}
