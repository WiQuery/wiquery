package org.odlabs.wiquery.ui.tabs;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

@WiQueryUIPlugin
public class Tabs extends WebMarkupContainer implements IWiQueryPlugin {

	private static final long serialVersionUID = 1L;

	/**
	 * Options are used to customize this component.
	 */
	private Options options;
	
	/**
	 * Builds a new tabs container with the given wicket id.
	 */
	public Tabs(String id) {
		super(id);
		options = new Options();
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(Tabs.class, "ui.tabs.js");
	}
	
	/* (non-Javadoc)
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("tabs", options.getJavaScriptOptions());
	}
	
	/**
	 * Returns the {@link JsStatement} to add the given component in the tab
	 * panel.
	 * 
	 * @param index the insertion index.
	 * @param title the tab title.
	 * @param contentToAdd the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(int index, String title, Component contentToAdd) {
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'", "'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'", "" + index);
	}
	
	/**
	 * Returns the {@link JsStatement} to add the given component at the end of
	 * the tab panel.
	 * 
	 * @param title the tab title.
	 * @param contentToAdd the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(String title, Component contentToAdd) {
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'", "'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'");
	}
	
	/**
	 * Returns the {@link JsStatement} to remove the tab at the given index.
	 * 
	 * @param index the remove index.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement remove(int index) {
		return new JsQuery(this).$().chain("tabs", "'remove'", "" + index);
	}	
	
	/**
	 * Sets which tab is displayed.
	 */
	public void setDefaultSelectedTabIndex(int selectedTabIndex) {
		this.options.put("selected", selectedTabIndex);
	}
	
	/**
	 * Returns the which tab is selected by default.
	 */
	public int getDefaultSelectedTabIndex() {
		return this.options.getInt("selected");
	}

}
