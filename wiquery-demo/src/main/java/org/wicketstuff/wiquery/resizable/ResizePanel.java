package org.wicketstuff.wiquery.resizable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.wicketstuff.wiquery.ui.resizable.ResizableStopAjaxBehavior;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * 
 */
public class ResizePanel extends Panel
{

	private static final long serialVersionUID = 1L;

	private String size = "";

	private Label label;

	/**
	 * 
	 * @param id
	 */
	public ResizePanel(String id)
	{
		super(id);

		WebMarkupContainer container = new WebMarkupContainer("resize");
		add(container);
		container.add(new ResizableStopAjaxBehavior()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void resizeTop(AjaxRequestTarget target, Component source,
				double resizeHeight, double resizeWidth)
			{
				size = "resizeHeight=" + resizeHeight + ", resizeWidth=" + resizeWidth;
				target.add(label);
			}
		});

		label = new Label("label", () -> "Size is: " + size);

		label.setOutputMarkupId(true);
		add(label);
	}
}
