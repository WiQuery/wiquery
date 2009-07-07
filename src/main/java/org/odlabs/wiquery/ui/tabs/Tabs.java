/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.ui.tabs;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.EventLabelOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IListItemOption;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * Create a tab panel.
 * </p>
 * 
 * * Missing functionalities
 * 	<ul>
 * 		<li>Option : ajaxOptions</li>
 * 		<li>Option : cookie</li>
 * 		<li>Method : length</li>
 * 	</ul>
 * 
 * @author Lionel Armanet
 * @since 0.9
 */
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

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(TabsJavaScriptResourceReference.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("tabs",
				options.getJavaScriptOptions());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}
	
	/*---- Options section ---*/
	
//	/**
//	 * Additional Ajax options to consider when loading tab content (see $.ajax).
//	 * @param ajaxOptions
//	 */
//	public void setAjaxOptions(JQueryAjaxOptions ajaxOptions) {
//		this.options.put("ajaxOptions", ajaxOptions);
//	}
//	
//	/**
//	 * @return the ajaxOptions option value
//	 */
//	public JQueryAjaxOptions getAjaxOptions() {
//		IComplexOption ajaxOptions = this.options.getComplexOption("ajaxOptions");
//		
//		if(ajaxOptions != null && ajaxOptions instanceof JQueryAjaxOptions) {
//			return (JQueryAjaxOptions)ajaxOptions;
//		}
//		
//		return null;
//	}
	
	/**Whether or not to cache remote tabs content, e.g. load only once or with 
	 * every click. Cached content is being lazy loaded, e.g once and only once 
	 * for the first click. Note that to prevent the actual Ajax requests from 
	 * being cached by the browser you need to provide an extra cache: false 
	 * flag to ajaxOptions.
	 * @param cache
	 */
	public void setCache(boolean cache) {
		options.put("cache", cache);
	}

	/**
	 * @return the cache option value
	 */
	public boolean isCache() {
		return options.getBoolean("cache");
	}
	
	/**Set to true to allow an already selected tab to become unselected again 
	 * upon reselection.
	 * @param collapsible
	 */
	public void setCollapsible(boolean collapsible) {
		options.put("collapsible", collapsible);
	}

	/**
	 * @return the collapsible option value
	 */
	public boolean isCollapsible() {
		return options.getBoolean("collapsible");
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
	
	/**Set an array containing the position of the tabs (zero-based index) that 
	 * should be disabled on initialization
	 * @param disabled
	 */
	public void setDisabled(ArrayItemOptions<IntegerItemOptions> disabled) {
		this.options.put("disabled", disabled);
	}
	
	/**
	 * @return the disabled option value
	 */
	public ICollectionItemOptions getDisabled() {		
		return this.options.getListItemOptions("disabled");
	}
	
	/**Set the type of event to be used for selecting a tab
	 * @param event
	 */
	public void setEvent(EventLabelOptions event) {
		this.options.put("event", event);
	}
	
	/**
	 * @return the event option value
	 */
	public EventLabelOptions getEvent() {
		IComplexOption event = this.options.getComplexOption("event");
		
		if(event != null && event instanceof EventLabelOptions){
			return (EventLabelOptions)event;
		}
		
		return null;
	}
	
	/**Enable animations for hiding and showing tab panels. The duration option 
	 * can be a string representing one of the three predefined speeds 
	 * ("slow", "normal", "fast") or the duration in milliseconds to run an 
	 * animation (default is "normal").
	 * @param fx
	 */
	public void setFx(ListItemOptions<IListItemOption> fx) {
		this.options.put("fx", fx);
	}
	
	/**
	 * @return the fx option value
	 */
	public ICollectionItemOptions getFx() {
		return this.options.getListItemOptions("fx");
	}
	
	/**
	 * If the remote tab, its anchor element that is, has no title attribute to 
	 * generate an id from, an id/fragment identifier is created from this 
	 * prefix and a unique id returned by $.data(el), for example "ui-tabs-54".
	 * @param idPrefix
	 */
	public void setIdPrefix(String idPrefix) {
		this.options.put("idPrefix", idPrefix);
	}

	/**
	 * @return the idPrefix value
	 */
	public String getIdPrefix() {
		if(this.options.containsKey("idPrefix")){
			return this.options.getLiteral("idPrefix");
		}
		
		return "ui-tabs-";
	}
	
	/**
	 * Set the HTML template from which a new tab panel is created in case of 
	 * adding a tab with the add method or when creating a panel for a remote 
	 * tab on the fly.
	 * @param panelTemplate
	 */
	public void setPanelTemplate(String panelTemplate) {
		this.options.put("panelTemplate", panelTemplate);
	}

	/**
	 * @return the panelTemplate value
	 */
	public String getPanelTemplate() {
		if(this.options.containsKey("panelTemplate")){
			return this.options.getLiteral("panelTemplate");
		}
		
		return "<div></div>";
	}
	
	/**
	 * Set the HTML content of this string is shown in a tab title while remote 
	 * content is loading. Pass in empty string to deactivate that behavior.
	 * @param spinner
	 */
	public void setSpinner(String spinner) {
		this.options.put("spinner", spinner);
	}

	/**
	 * @return the spinner value
	 */
	public String getSpinner() {
		if(this.options.containsKey("spinner")){
			return this.options.getLiteral("spinner");
		}
		
		return "<em>Loading&#8230;</em>";
	}
	
	/**
	 * Set the HTML template from which a new tab is created and added. The 
	 * placeholders #{href} and #{label} are replaced with the url and tab label 
	 * that are passed as arguments to the add method.
	 * @param tabTemplate
	 */
	public void setTabTemplate(String tabTemplate) {
		this.options.put("tabTemplate", tabTemplate);
	}

	/**
	 * @return the tabTemplate value
	 */
	public String getTabTemplate() {
		if(this.options.containsKey("tabTemplate")){
			return this.options.getLiteral("tabTemplate");
		}
		
		return "<li><a href=\"#{href}\"><span>#{label}</span></a></li>";
	}
	
	/*---- Events section ---*/
	
	/**Set the callback when a tab is added
	 * @param add
	 */
	public void setAddEvent(JsScopeUiEvent add) {
		this.options.put("add", add);
	}
	
	/**Set the callback when a tab is disabled
	 * @param disable
	 */
	public void setDisableEvent(JsScopeUiEvent disable) {
		this.options.put("disable", disable);
	}
	
	/**Set the callback when a tab is enabled
	 * @param enable
	 */
	public void setEnableEvent(JsScopeUiEvent enable) {
		this.options.put("enable", enable);
	}
	
	/**Set the callback when the content of a remote tab has been loaded
	 * @param load
	 */
	public void setLoadEvent(JsScopeUiEvent load) {
		this.options.put("load", load);
	}
	
	/**Set the callback when a tab is removed
	 * @param remove
	 */
	public void setRemoveEvent(JsScopeUiEvent remove) {
		this.options.put("remove", remove);
	}
	
	/**Set the callback when the user is clicking the tab
	 * @param select
	 */
	public void setSelectEvent(JsScopeUiEvent select) {
		this.options.put("select", select);
	}
	
	/**Set the callback when a tab is shown
	 * @param show
	 */
	public void setShowEvent(JsScopeUiEvent show) {
		this.options.put("show", show);
	}

	/*---- Methods section ---*/
	
	/**
	 * Returns the {@link JsStatement} to add the given component in the tab
	 * panel.
	 * 
	 * @param index
	 *            the insertion index.
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(int index, String title, Component contentToAdd) {
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'",
				"'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'",
				"" + index);
	}

	/**
	 * Returns the {@link JsStatement} to add the given component at the end of
	 * the tab panel.
	 * 
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(String title, Component contentToAdd) {
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'",
				"'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'");
	}
	
	/**Method to add a new tab
	 * This will return the element back to its pre-init state.
	 * @param url URL
	 * @param label Label of the tab
	 * @return the associated JsStatement
	 */
	public JsStatement add(String url, String label) {
		return new JsQuery(this).$().chain("tabs", "'add'", new LiteralOption(url).toString(),
				new LiteralOption(label).toString());
	}

	/**Method to add a new tab within the ajax request
	 * @param ajaxRequestTarget
	 * @param url URL
	 * @param label Label of the tab
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, String url, String label) {
		ajaxRequestTarget.appendJavascript(this.add(url, label).render().toString());
	}
	
	/**Method to add a new tab
	 * This will return the element back to its pre-init state.
	 * @param url URL
	 * @param label Label of the tab
	 * @param index Index of insertion
	 * @return the associated JsStatement
	 */
	public JsStatement add(String url, String label, int index) {
		return new JsQuery(this).$().chain("tabs", "'add'", new LiteralOption(url).toString(),
				new LiteralOption(label).toString(), Integer.toString(index));
	}

	/**Method to add a new tab within the ajax request
	 * @param ajaxRequestTarget
	 * @param url URL
	 * @param label Label of the tab
	 * @param index Index of insertion
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, String url, String label,
			int index) {
		ajaxRequestTarget.appendJavascript(this.add(url, label, index).render().toString());
	}
	
	/**Method to terminate all running tab ajax requests and animations
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement abort() {
		return new JsQuery(this).$().chain("tabs", "'abort'");
	}

	/**Method to terminate all running tab ajax requests and animations within 
	 * the ajax request
	 * @param ajaxRequestTarget
	 */
	public void abort(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.abort().render().toString());
	}
	
	/**Method to destroy the tabs
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("tabs", "'destroy'");
	}

	/**Method to destroy the tabs within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable the tabs
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(this).$().chain("tabs", "'disable'");
	}

	/**Method to disable the tabs within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to disable a tab
	 * @param index Index tab to disable
	 * @return the associated JsStatement
	 */
	public JsStatement disable(int index) {
		return new JsQuery(this).$().chain("tabs", "'disable'", Integer.toString(index));
	}

	/**Method to disable a tab within the ajax request
	 * @param index Index tab to disable
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget, int index) {
		ajaxRequestTarget.appendJavascript(this.disable(index).render().toString());
	}
	
	/**Method to enable the tabs
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(this).$().chain("tabs", "'enable'");
	}

	/**Method to enable the tabs within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method to enable a tab
	 * @param index Index tab to enable
	 * @return the associated JsStatement
	 */
	public JsStatement enable(int index) {
		return new JsQuery(this).$().chain("tabs", "'enable'", Integer.toString(index));
	}

	/**Method to enable a tab within the ajax request
	 * @param index Index tab to enable
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget, int index) {
		ajaxRequestTarget.appendJavascript(this.enable(index).render().toString());
	}
	
	/**Method to reload the content of an Ajax tab programmatically
	 * @param index Index tab to select
	 * @return the associated JsStatement
	 */
	public JsStatement load(int index) {
		return new JsQuery(this).$().chain("tabs", "'load'", Integer.toString(index));
	}

	/**Method to reload the content of an Ajax tab programmatically within the ajax request
	 * @param index Index tab to select
	 * @param ajaxRequestTarget
	 */
	public void load(AjaxRequestTarget ajaxRequestTarget, int index) {
		ajaxRequestTarget.appendJavascript(this.load(index).render().toString());
	}
	
	/**
	 * Returns the {@link JsStatement} to remove the tab at the given index.
	 * 
	 * @param index
	 *            the remove index.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement remove(int index) {
		return new JsQuery(this).$().chain("tabs", "'remove'", "" + index);
	}
	
	/**Method to remove a tab within the ajax request
	 * @param index the remove index
	 * @param ajaxRequestTarget
	 */
	public void remove(AjaxRequestTarget ajaxRequestTarget, int index) {
		ajaxRequestTarget.appendJavascript(this.rotate(index).render().toString());
	}
	
	/**Method to set up an automatic rotation through tabs of a tab pane
	 * @param ms Amount of time in milliseconds
	 * @return the associated JsStatement
	 */
	public JsStatement rotate(int ms) {
		return new JsQuery(this).$().chain("tabs", "'rotate'", Integer.toString(ms));
	}

	/**Method to set up an automatic rotation through tabs of a tab pane within 
	 * the ajax request
	 * @param ms Amount of time in milliseconds
	 * @param ajaxRequestTarget
	 */
	public void rotate(AjaxRequestTarget ajaxRequestTarget, int ms) {
		ajaxRequestTarget.appendJavascript(this.rotate(ms).render().toString());
	}
	
	/**Method to set up an automatic rotation through tabs of a tab pane
	 * @param ms Amount of time in milliseconds
	 * @param continuing Continue the rotation after a tab has been selected by a user
	 * @return the associated JsStatement
	 */
	public JsStatement rotate(int ms, boolean continuing) {
		return new JsQuery(this).$().chain("tabs", "'rotate'", Integer.toString(ms),
				Boolean.toString(continuing));
	}

	/**Method to set up an automatic rotation through tabs of a tab pane within 
	 * the ajax request
	 * @param ms Amount of time in milliseconds
	 * @param continuing Continue the rotation after a tab has been selected by a user
	 * @param ajaxRequestTarget
	 */
	public void rotate(AjaxRequestTarget ajaxRequestTarget, int ms, boolean continuing) {
		ajaxRequestTarget.appendJavascript(this.rotate(ms).render().toString());
	}
	
	/**Method to select a tab
	 * @param index Index tab to select
	 * @return the associated JsStatement
	 */
	public JsStatement select(int index) {
		return new JsQuery(this).$().chain("tabs", "'select'", Integer.toString(index));
	}

	/**Method to select a tab within the ajax request
	 * @param index Index tab to select
	 * @param ajaxRequestTarget
	 */
	public void select(AjaxRequestTarget ajaxRequestTarget, int index) {
		ajaxRequestTarget.appendJavascript(this.select(index).render().toString());
	}
	
	/**Method to change the url from which an Ajax (remote) tab will be loaded
	 * @param index Index tab to select
	 * @param url URL
	 * @return the associated JsStatement
	 */
	public JsStatement url(int index, String url) {
		return new JsQuery(this).$().chain("tabs", "'url'", Integer.toString(index),
				new LiteralOption(url).toString());
	}

	/**Method to change the url from which an Ajax (remote) tab will be loaded 
	 * within the ajax request
	 * @param index Index tab to select
	 * @param url URL
	 * @param ajaxRequestTarget
	 */
	public void url(AjaxRequestTarget ajaxRequestTarget, int index, String url) {
		ajaxRequestTarget.appendJavascript(this.url(index, url).render().toString());
	}
}
