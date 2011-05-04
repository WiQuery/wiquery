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
import org.odlabs.wiquery.core.ajax.JQueryAjaxOption;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption;
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
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Create a tab panel.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.9
 */
@WiQueryUIPlugin
public class Tabs extends WebMarkupContainer implements IWiQueryPlugin {
	// Constantes
	/** Constant of serialization */
	private static final long serialVersionUID = 2L;
	
	/** Properties on the ui parameter (use it into callback functions) : 
	 * anchor element of the selected tab */
	public static final String UI_TAB = "ui.tab";
	
	/** Properties on the ui parameter (use it into callback functions) : 
	 * element, that contains the selected tab contents */
	public static final String UI_PANEL = "ui.panel";
	
	/** Properties on the ui parameter (use it into callback functions) : 
	 * zero-based index of the selected tab */
	public static final String UI_INDEX = "ui.index";

	/**
	 * Options are used to customize this component.
	 */
	private Options options;

	/**
	 * Builds a new tabs container with the given wicket id.
	 */
	public Tabs(String id) {
		super(id);
		options = new Options(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.wicket.Component#detachModel()
	 */
	@Override
	protected void detachModel() {
		super.detachModel();
		options.detach();		
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
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
	
	/**
	 * Additional Ajax options to consider when loading tab content (see $.ajax).
	 * @param ajaxOptions
	 */
	public void setAjaxOptions(JQueryAjaxOption ajaxOptions) {
		this.options.put("ajaxOptions", ajaxOptions);
	}
	
	/**
	 * @return the ajaxOptions option value
	 */
	public JQueryAjaxOption getAjaxOptions() {
		IComplexOption ajaxOptions = this.options.getComplexOption("ajaxOptions");
		
		if(ajaxOptions != null && ajaxOptions instanceof JQueryAjaxOption) {
			return (JQueryAjaxOption)ajaxOptions;
		}
		
		return null;
	}
	
	/**
	 * Method to store the latest selected tab in a cookie. The cookie is 
	 * then used to determine the initially selected tab if the 
	 * selected option is not defined. Requires cookie plugin. 
	 * The object needs to have key/value pairs of the form the 
	 * cookie plugin expects as options. Available options (example): 
	 * { expires: 7, path: '/', domain: 'jquery.com', secure: true }. 
	 * 
	 * Since jQuery UI 1.7 it is also possible to define the cookie name 
	 * being used via name property.
	 * 
	 * @param cookie
	 */
	public void setCookie(JQueryCookieOption cookie) {
		this.options.put("cookie", cookie);
	}
	
	/**
	 * @return the cookie option value
	 */
	public JQueryCookieOption getCookie() {
		IComplexOption cookie = this.options.getComplexOption("cookie");
		
		if(cookie != null && cookie instanceof JQueryCookieOption) {
			return (JQueryCookieOption) cookie;
		}
		
		return null;
	}
	
	/**Whether or not to cache remote tabs content, e.g. load only once or with 
	 * every click. Cached content is being lazy loaded, e.g once and only once 
	 * for the first click. Note that to prevent the actual Ajax requests from 
	 * being cached by the browser you need to provide an extra cache: false 
	 * flag to ajaxOptions.
	 * @param cache
	 * @return instance of the current component
	 */
	public Tabs setCache(boolean cache) {
		options.put("cache", cache);
		return this;
	}

	/**
	 * @return the cache option value
	 */
	public boolean isCache() {
		if(this.options.containsKey("cache")){
			return options.getBoolean("cache");
		}
		
		return false;
	}
	
	/**Set to true to allow an already selected tab to become unselected again 
	 * upon reselection. (Old version of this option : deselectable)
	 * @param collapsible
	 * @return instance of the current component
	 */
	public Tabs setCollapsible(boolean collapsible) {
		options.put("collapsible", collapsible);
		return this;
	}

	/**
	 * @return the collapsible option value
	 */
	public boolean isCollapsible() {
		if(this.options.containsKey("collapsible")){
			return options.getBoolean("collapsible");
		}
		
		return false;
	}

	/**
	 * Sets which tab is displayed.
	 * @return instance of the current component
	 */
	public Tabs setDefaultSelectedTabIndex(int selectedTabIndex) {
		this.options.put("selected", selectedTabIndex);
		return this;
	}

	/**
	 * Returns the which tab is selected by default.
	 */
	public int getDefaultSelectedTabIndex() {
		if(this.options.containsKey("selected")){
			return this.options.getInt("selected");
		}
		
		return 0;
	}
	
	/**Disables (true) or enables (false) the tabs. Can be set when 
	 * initialising (first creating) the tabs.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Tabs setDisabled(boolean disabled) {
		this.options.put("disabled", disabled);
		return this;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		if(this.options.containsKey("disabled")){
			return this.options.getBoolean("disabled");
		}
		
		return false;
	}
	
	/**Set an array containing the position of the tabs (zero-based index) that 
	 * should be disabled on initialization
	 * @param disabled
	 * @return instance of the current component
	 */
	public Tabs setDisabled(ArrayItemOptions<IntegerItemOptions> disabled) {
		this.options.put("disabled", disabled);
		return this;
	}
	
	/**
	 * @return the disabled option value
	 */
	public ICollectionItemOptions getDisabled() {		
		return this.options.getListItemOptions("disabled");
	}
	
	/**Set the type of event to be used for selecting a tab
	 * @param event
	 * @return instance of the current component
	 */
	public Tabs setEvent(EventLabelOptions event) {
		this.options.put("event", event);
		return this;
	}
	
	/**
	 * @return the event option value
	 */
	public EventLabelOptions getEvent() {
		IComplexOption event = this.options.getComplexOption("event");
		
		if(event != null && event instanceof EventLabelOptions){
			return (EventLabelOptions)event;
		}
		
		return new EventLabelOptions(MouseEvent.CLICK);
	}
	
	/**Enable animations for hiding and showing tab panels. The duration option 
	 * can be a string representing one of the three predefined speeds 
	 * ("slow", "normal", "fast") or the duration in milliseconds to run an 
	 * animation (default is "normal").
	 * @param fx
	 * @return instance of the current component
	 */
	public Tabs setFx(ListItemOptions<IListItemOption> fx) {
		this.options.put("fx", fx);
		return this;
		// TODO change this method
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
	 * @return instance of the current component
	 */
	public Tabs setIdPrefix(String idPrefix) {
		this.options.putLiteral("idPrefix", idPrefix);
		return this;
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
	 * @return instance of the current component
	 */
	public Tabs setPanelTemplate(String panelTemplate) {
		this.options.putLiteral("panelTemplate", panelTemplate);
		return this;
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
	 * @return instance of the current component
	 */
	public Tabs setSpinner(String spinner) {
		this.options.putLiteral("spinner", spinner);
		return this;
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
	 * @return instance of the current component
	 */
	public Tabs setTabTemplate(String tabTemplate) {
		this.options.putLiteral("tabTemplate", tabTemplate);
		return this;
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
	 * @return instance of the current component
	 */
	public Tabs setAddEvent(JsScopeUiEvent add) {
		this.options.put("add", add);
		return this;
	}
	
	/**Set the callback when a tab is disabled
	 * @param disable
	 * @return instance of the current component
	 */
	public Tabs setDisableEvent(JsScopeUiEvent disable) {
		this.options.put("disable", disable);
		return this;
	}
	
	/**Set the callback when a tab is enabled
	 * @param enable
	 * @return instance of the current component
	 */
	public Tabs setEnableEvent(JsScopeUiEvent enable) {
		this.options.put("enable", enable);
		return this;
	}
	
	/**Set the callback when the content of a remote tab has been loaded
	 * @param load
	 * @return instance of the current component
	 */
	public Tabs setLoadEvent(JsScopeUiEvent load) {
		this.options.put("load", load);
		return this;
	}
	
	/**Set the callback when a tab is removed
	 * @param remove
	 * @return instance of the current component
	 */
	public Tabs setRemoveEvent(JsScopeUiEvent remove) {
		this.options.put("remove", remove);
		return this;
	}
	
	/**Set the callback when the user is clicking the tab
	 * @param select
	 * @return instance of the current component
	 */
	public Tabs setSelectEvent(JsScopeUiEvent select) {
		this.options.put("select", select);
		return this;
	}
	
	/**Set the callback when a tab is shown
	 * @param show
	 * @return instance of the current component
	 */
	public Tabs setShowEvent(JsScopeUiEvent show) {
		this.options.put("show", show);
		return this;
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
	 * Add the given component in the tab
	 * panel.
	 * 
	 * @param ajaxRequestTarget
	 * @param index
	 *            the insertion index.
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, int index, 
			String title, Component contentToAdd) {
		ajaxRequestTarget.appendJavaScript(add(index, title, contentToAdd).render().toString());
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
	
	/**
	 * Add the given component in the tab
	 * panel.
	 * 
	 * @param ajaxRequestTarget
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, 
			String title, Component contentToAdd) {
		ajaxRequestTarget.appendJavaScript(add(title, contentToAdd).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.add(url, label).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.add(url, label, index).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.abort().render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.disable(index).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.enable(index).render().toString());
	}
	
	/**Method retrieving the number of tabs of the first matched tab pane
	 * @return the associated JsStatement
	 */
	public JsStatement length() {
		return new JsQuery(this).$().chain("tabs", "'length'");
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
		ajaxRequestTarget.appendJavaScript(this.load(index).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.remove(index).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.rotate(ms).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.rotate(ms).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.select(index).render().toString());
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
		ajaxRequestTarget.appendJavaScript(this.url(index, url).render().toString());
	}
	
	/**Method to returns the .ui-slider  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(this).$().chain("tabs", "'widget'");
	}

	/**Method to returns the .ui-slider element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}
}
