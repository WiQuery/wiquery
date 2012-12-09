package org.odlabs.wiquery.tab;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.odlabs.wiquery.ui.tabs.Tabs;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * 
 */
public class TabsPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private int index = 0;
	
	private Label label;

	/**
	 * 
	 * @param id
	 */
	public TabsPanel(String id) {
		super(id);
		Tabs tabs = new Tabs("tabs");
		tabs.setAjaxSelectEvent(new Tabs.ITabsAjaxEvent() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(AjaxRequestTarget target, Tabs tabs, int index) {
				TabsPanel.this.index = index;
				target.add(label);
			}
		});
		add(tabs);
		
		label = new Label("label", new AbstractReadOnlyModel<String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "Selected index is: " + index;
			}

		});
		
		label.setOutputMarkupId(true);
		add(label);
	}
}
