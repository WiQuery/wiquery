package org.wicketstuff.wiquery.tab;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.wicketstuff.wiquery.ui.JQueryUIJavaScriptResourceReference;
import org.wicketstuff.wiquery.ui.options.EffectOptionObject;
import org.wicketstuff.wiquery.ui.tabs.Tabs;
import org.wicketstuff.wiquery.ui.tabs.TabsAnimateOption;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * 
 */
public class TabsPanel extends Panel
{

	private static final long serialVersionUID = 7431432083554551959L;

	private int index = -1;

	private Label indexLabel;

	public TabsPanel(String id)
	{
		super(id);
		Tabs tabs = new Tabs("tabs");
		tabs.setAjaxBeforeActivateEvent(new Tabs.ITabsAjaxEvent()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(AjaxRequestTarget target, Tabs tabs, int index)
			{
				TabsPanel.this.index = index;
				target.add(indexLabel);
			}
		});
		tabs.setCollapsible(true);
		tabs.setActive(false);
		tabs.setHide(
			new TabsAnimateOption(new EffectOptionObject().setEffect("drop").setDuration(200)));
		tabs.setShow(
			new TabsAnimateOption(new EffectOptionObject().setEffect("slide").setDuration(200)));
		add(tabs);

		indexLabel = new Label("indexLabel", () -> "Selected index is: " + index);
		indexLabel.setOutputMarkupId(true);
		add(indexLabel);
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		response
			.render(JavaScriptHeaderItem.forReference(JQueryUIJavaScriptResourceReference.get()));
	}
}
