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

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.cycle.RequestCycle;
import org.odlabs.wiquery.core.ajax.JQueryAjaxOption;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
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
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Create a tab panel.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.9
 */
public class Tabs extends WebMarkupContainer
{
	// Constantes
	/** Constant of serialization */
	private static final long serialVersionUID = 2L;

	/**
	 * Properties on the ui parameter (use it into callback functions) : anchor element of
	 * the selected tab
	 */
	public static final String UI_TAB = "ui.tab";

	/**
	 * Properties on the ui parameter (use it into callback functions) : element, that
	 * contains the selected tab contents
	 */
	public static final String UI_PANEL = "ui.panel";

	/**
	 * Properties on the ui parameter (use it into callback functions) : zero-based index
	 * of the selected tab
	 */
	public static final String UI_INDEX = "ui.index";

	/**
	 * Options are used to customize this component.
	 */
	private Options options;

	/**
	 * 	 The tab event.
	 */
	public static enum TabEvent
	{
		add,
		enable,
		disable,
		show,
		select,
		remove,
		load,
	}

	/**
	 * This class is only needed to make public the method generateCallbackScript.
	 * 
	 * @author Ernesto Reinaldo Barreiro
	 */
	private static abstract class TabsAjaxBehavior extends AbstractDefaultAjaxBehavior
	{

		private static final long serialVersionUID = 1L;
		
		private List<String> extraDynParams;
		

		public TabsAjaxBehavior()
		{
		}
		

		@Override
		protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
		{
			if (extraDynParams != null)
			{
				attributes.getDynamicExtraParameters().addAll(extraDynParams);
			}
		}
		
		protected void setDynParams(List<String> list)
		{
			this.extraDynParams = list;
		}		
	}

	/*
	 * AJAX behavior needed for AJAX call-backs.
	 */
	private TabsAjaxBehavior tabsAjaxBehavior;

	/*
	 * The slider event.
	 */
	public static final String TAB_EVENT = "tabEvent";

	public static final String TAB_INDEX = "tabIndex";

	public static final String UI_TAB_INDEX = "ui.index";

	/**
	 * Utility class for handling tabs AJAX events.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 */
	private static class TabsAjaxJsScopeUiEvent extends JsScopeUiEvent
	{

		private static final long serialVersionUID = 1L;

		private TabEvent event;

		private Tabs tabs;

		public TabsAjaxJsScopeUiEvent(Tabs tabs, TabEvent event)
		{
			super();
			this.tabs = tabs;
			this.event = event;
		}

		@Override
		protected void execute(JsScopeContext scopeContext)
		{
			tabs.tabsAjaxBehavior.setDynParams(Arrays.asList(String.format(
					"return {'%s': '%s', '%s': %s}", TAB_EVENT, event.name(),
					TAB_INDEX, UI_TAB_INDEX)));

				scopeContext.append(
				// delegating call-back generation to AJAX behavior
				// so that we don't miss 'decorator' related functionality.
				tabs.tabsAjaxBehavior.getCallbackScript());

		}
	}

	/**
	 * Specific function for select event.
	 * 
	 * @author reiern70
	 */
	public static class TabsAjaxSelectJsScopeUiEvent extends TabsAjaxJsScopeUiEvent
	{
		private static final long serialVersionUID = 1L;

		private boolean cancelSelect = false;

		public TabsAjaxSelectJsScopeUiEvent(Tabs tabs, TabEvent event, boolean cancelSelect)
		{
			super(tabs, event);
			this.cancelSelect = cancelSelect;
		}

		@Override
		protected void execute(JsScopeContext scopeContext)
		{
			super.execute(scopeContext);
			scopeContext.append("return " + !cancelSelect);
		}
	}

	/**
	 * Call back interface for AJAX Events.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 * 
	 */
	public static interface ITabsAjaxEvent extends Serializable
	{

		/**
		 * Call-back method for slider AJAX events.
		 * 
		 * @param target
		 *            The AjaxRequestTarget.
		 * @param tabs
		 *            The tabs to which the event is attached.
		 * @param index
		 *            Of the tab event
		 */
		public void onEvent(AjaxRequestTarget target, Tabs tabs, int index);
	}

	/*
	 * Map of AJAX events.
	 */
	private Map<TabEvent, ITabsAjaxEvent> ajaxEvents = new HashMap<TabEvent, ITabsAjaxEvent>();

	/**
	 * Builds a new tabs container with the given wicket id.
	 */
	public Tabs(String id)
	{
		super(id);
		options = new Options(this);
		tabsAjaxBehavior = new TabsAjaxBehavior()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void respond(AjaxRequestTarget target)
			{
				String tabEvent =
					RequestCycle.get().getRequest().getQueryParameters()
						.getParameterValue(TAB_EVENT).toString();
				// if we have an event execute it.
				if (!isEmpty(tabEvent))
				{
					// calculate the index
					int index =
						parseInteger(RequestCycle.get().getRequest().getQueryParameters()
							.getParameterValue(TAB_INDEX).toString(), 0);

					ITabsAjaxEvent ajaxEvent = ajaxEvents.get(TabEvent.valueOf(tabEvent));
					if (ajaxEvent != null)
					{
						ajaxEvent.onEvent(target, Tabs.this, index);
					}
				}
			}
		};
		add(tabsAjaxBehavior);
	}

	/**
	 * Parses an integer.
	 * 
	 * @param value
	 *            The value to parse.
	 * @return The parsed integer.
	 */
	private int parseInteger(String value, int defaultValue)
	{
		try
		{
			return Integer.parseInt(value);
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	public static boolean isEmpty(String str)
	{
		return (str == null || str.trim().length() == 0);
	}

	@Override
	protected void detachModel()
	{
		super.detachModel();
		options.detach();
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.render(JavaScriptHeaderItem.forReference(WidgetJavaScriptResourceReference.get()));
		response.render(JavaScriptHeaderItem.forReference(TabsJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
	}

	public JsStatement statement()
	{
		return new JsQuery(this).$().chain("tabs", options.getJavaScriptOptions());
	}

	/**
	 * Method retrieving the options of the component
	 * 
	 * @return the options
	 */
	protected Options getOptions()
	{
		return options;
	}

	/*---- Options section ---*/

	/**
	 * Additional Ajax options to consider when loading tab content (see $.ajax).
	 * 
	 * @param ajaxOptions
	 */
	public void setAjaxOptions(JQueryAjaxOption ajaxOptions)
	{
		this.options.put("ajaxOptions", ajaxOptions);
	}

	/**
	 * @return the ajaxOptions option value
	 */
	public JQueryAjaxOption getAjaxOptions()
	{
		IComplexOption ajaxOptions = this.options.getComplexOption("ajaxOptions");

		if (ajaxOptions != null && ajaxOptions instanceof JQueryAjaxOption)
		{
			return (JQueryAjaxOption) ajaxOptions;
		}

		return null;
	}

	/**
	 * Method to store the latest selected tab in a cookie. The cookie is then used to
	 * determine the initially selected tab if the selected option is not defined.
	 * Requires cookie plugin. The object needs to have key/value pairs of the form the
	 * cookie plugin expects as options. Available options (example): { expires: 7, path:
	 * '/', domain: 'jquery.com', secure: true }.
	 * 
	 * Since jQuery UI 1.7 it is also possible to define the cookie name being used via
	 * name property.
	 * 
	 * @param cookie
	 */
	public void setCookie(JQueryCookieOption cookie)
	{
		this.options.put("cookie", cookie);
	}

	/**
	 * @return the cookie option value
	 */
	public JQueryCookieOption getCookie()
	{
		IComplexOption cookie = this.options.getComplexOption("cookie");

		if (cookie != null && cookie instanceof JQueryCookieOption)
		{
			return (JQueryCookieOption) cookie;
		}

		return null;
	}

	/**
	 * Whether or not to cache remote tabs content, e.g. load only once or with every
	 * click. Cached content is being lazy loaded, e.g once and only once for the first
	 * click. Note that to prevent the actual Ajax requests from being cached by the
	 * browser you need to provide an extra cache: false flag to ajaxOptions.
	 * 
	 * @param cache
	 * @return instance of the current component
	 */
	public Tabs setCache(boolean cache)
	{
		options.put("cache", cache);
		return this;
	}

	/**
	 * @return the cache option value
	 */
	public boolean isCache()
	{
		if (this.options.containsKey("cache"))
		{
			return options.getBoolean("cache");
		}

		return false;
	}

	/**
	 * Set to true to allow an already selected tab to become unselected again upon
	 * reselection. (Old version of this option : deselectable)
	 * 
	 * @param collapsible
	 * @return instance of the current component
	 */
	public Tabs setCollapsible(boolean collapsible)
	{
		options.put("collapsible", collapsible);
		return this;
	}

	/**
	 * @return the collapsible option value
	 */
	public boolean isCollapsible()
	{
		if (this.options.containsKey("collapsible"))
		{
			return options.getBoolean("collapsible");
		}

		return false;
	}

	/**
	 * Sets which tab is displayed.
	 * 
	 * @return instance of the current component
	 */
	public Tabs setDefaultSelectedTabIndex(int selectedTabIndex)
	{
		this.options.put("selected", selectedTabIndex);
		return this;
	}

	/**
	 * Returns the which tab is selected by default.
	 */
	public int getDefaultSelectedTabIndex()
	{
		if (this.options.containsKey("selected"))
		{
			return this.options.getInt("selected");
		}

		return 0;
	}

	/**
	 * Disables (true) or enables (false) the tabs. Can be set when initialising (first
	 * creating) the tabs.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Tabs setDisabled(boolean disabled)
	{
		this.options.put("disabled", disabled);
		return this;
	}

	/**
	 * @return the disabled option
	 */
	public boolean isDisabled()
	{
		if (this.options.containsKey("disabled"))
		{
			return this.options.getBoolean("disabled");
		}

		return false;
	}

	/**
	 * Set an array containing the position of the tabs (zero-based index) that should be
	 * disabled on initialization
	 * 
	 * @param disabled
	 * @return instance of the current component
	 */
	public Tabs setDisabled(ArrayItemOptions<IntegerItemOptions> disabled)
	{
		this.options.put("disabled", disabled);
		return this;
	}

	/**
	 * @return the disabled option value
	 */
	public ICollectionItemOptions getDisabled()
	{
		return this.options.getListItemOptions("disabled");
	}

	/**
	 * Set the type of event to be used for selecting a tab
	 * 
	 * @param event
	 * @return instance of the current component
	 */
	public Tabs setEvent(EventLabelOptions event)
	{
		this.options.put("event", event);
		return this;
	}

	/**
	 * @return the event option value
	 */
	public EventLabelOptions getEvent()
	{
		IComplexOption event = this.options.getComplexOption("event");

		if (event != null && event instanceof EventLabelOptions)
		{
			return (EventLabelOptions) event;
		}

		return new EventLabelOptions(MouseEvent.CLICK);
	}

	/**
	 * Enable animations for hiding and showing tab panels. The duration option can be a
	 * string representing one of the three predefined speeds ("slow", "normal", "fast")
	 * or the duration in milliseconds to run an animation (default is "normal").
	 * 
	 * @param fx
	 * @return instance of the current component
	 */
	public Tabs setFx(ListItemOptions<IListItemOption> fx)
	{
		this.options.put("fx", fx);
		return this;
		// TODO change this method
	}

	/**
	 * @return the fx option value
	 */
	public ICollectionItemOptions getFx()
	{
		return this.options.getListItemOptions("fx");
	}

	/**
	 * If the remote tab, its anchor element that is, has no title attribute to generate
	 * an id from, an id/fragment identifier is created from this prefix and a unique id
	 * returned by $.data(el), for example "ui-tabs-54".
	 * 
	 * @param idPrefix
	 * @return instance of the current component
	 */
	public Tabs setIdPrefix(String idPrefix)
	{
		this.options.putLiteral("idPrefix", idPrefix);
		return this;
	}

	/**
	 * @return the idPrefix value
	 */
	public String getIdPrefix()
	{
		if (this.options.containsKey("idPrefix"))
		{
			return this.options.getLiteral("idPrefix");
		}

		return "ui-tabs-";
	}

	/**
	 * Set the HTML template from which a new tab panel is created in case of adding a tab
	 * with the add method or when creating a panel for a remote tab on the fly.
	 * 
	 * @param panelTemplate
	 * @return instance of the current component
	 */
	public Tabs setPanelTemplate(String panelTemplate)
	{
		this.options.putLiteral("panelTemplate", panelTemplate);
		return this;
	}

	/**
	 * @return the panelTemplate value
	 */
	public String getPanelTemplate()
	{
		if (this.options.containsKey("panelTemplate"))
		{
			return this.options.getLiteral("panelTemplate");
		}

		return "<div></div>";
	}

	/**
	 * Set the HTML content of this string is shown in a tab title while remote content is
	 * loading. Pass in empty string to deactivate that behavior.
	 * 
	 * @param spinner
	 * @return instance of the current component
	 */
	public Tabs setSpinner(String spinner)
	{
		this.options.putLiteral("spinner", spinner);
		return this;
	}

	/**
	 * @return the spinner value
	 */
	public String getSpinner()
	{
		if (this.options.containsKey("spinner"))
		{
			return this.options.getLiteral("spinner");
		}

		return "<em>Loading&#8230;</em>";
	}

	/**
	 * Set the HTML template from which a new tab is created and added. The placeholders
	 * #{href} and #{label} are replaced with the url and tab label that are passed as
	 * arguments to the add method.
	 * 
	 * @param tabTemplate
	 * @return instance of the current component
	 */
	public Tabs setTabTemplate(String tabTemplate)
	{
		this.options.putLiteral("tabTemplate", tabTemplate);
		return this;
	}

	/**
	 * @return the tabTemplate value
	 */
	public String getTabTemplate()
	{
		if (this.options.containsKey("tabTemplate"))
		{
			return this.options.getLiteral("tabTemplate");
		}

		return "<li><a href=\"#{href}\"><span>#{label}</span></a></li>";
	}

	/*---- Events section ---*/

	/**
	 * Set the callback when a tab is added
	 * 
	 * @param add
	 * @return instance of the current component
	 */
	public Tabs setAddEvent(JsScopeUiEvent add)
	{
		this.options.put("add", add);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX add event.
	 * 
	 * @param addEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxAddEvent(ITabsAjaxEvent addEvent)
	{
		this.ajaxEvents.put(TabEvent.add, addEvent);
		setAddEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.add));
		return this;
	}

	/**
	 * Set the callback when a tab is disabled
	 * 
	 * @param disable
	 * @return instance of the current component
	 */
	public Tabs setDisableEvent(JsScopeUiEvent disable)
	{
		this.options.put("disable", disable);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX disable event.
	 * 
	 * @param disableEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxDisableEvent(ITabsAjaxEvent disableEvent)
	{
		this.ajaxEvents.put(TabEvent.disable, disableEvent);
		setDisableEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.disable));
		return this;
	}

	/**
	 * Set the callback when a tab is enabled
	 * 
	 * @param enable
	 * @return instance of the current component
	 */
	public Tabs setEnableEvent(JsScopeUiEvent enable)
	{
		this.options.put("enable", enable);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX enable event.
	 * 
	 * @param enableEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxEnableEvent(ITabsAjaxEvent enableEvent)
	{
		this.ajaxEvents.put(TabEvent.enable, enableEvent);
		setEnableEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.enable));
		return this;
	}

	/**
	 * Set the callback when the content of a remote tab has been loaded
	 * 
	 * @param load
	 * @return instance of the current component
	 */
	public Tabs setLoadEvent(JsScopeUiEvent load)
	{
		this.options.put("load", load);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX Load event.
	 * 
	 * @param loadEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxLoadEvent(ITabsAjaxEvent loadEvent)
	{
		this.ajaxEvents.put(TabEvent.load, loadEvent);
		setLoadEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.load));
		return this;
	}

	/**
	 * Set the callback when a tab is removed
	 * 
	 * @param remove
	 * @return instance of the current component
	 */
	public Tabs setRemoveEvent(JsScopeUiEvent remove)
	{
		this.options.put("remove", remove);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX remove event.
	 * 
	 * @param removeEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxRemoveEvent(ITabsAjaxEvent removeEvent)
	{
		this.ajaxEvents.put(TabEvent.remove, removeEvent);
		setRemoveEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.remove));
		return this;
	}

	/**
	 * Set the callback when the user is clicking the tab
	 * 
	 * @param select
	 * @return instance of the current component
	 */
	public Tabs setSelectEvent(JsScopeUiEvent select)
	{
		this.options.put("select", select);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX select.
	 * 
	 * @param selectEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxSelectEvent(ITabsAjaxEvent selectEvent)
	{
		this.ajaxEvents.put(TabEvent.select, selectEvent);
		setSelectEvent(new TabsAjaxSelectJsScopeUiEvent(this, TabEvent.select, false));
		return this;
	}

	/**
	 * Sets the call-back for the AJAX select.
	 * 
	 * @param selectEvent
	 *            The ITabsAjaxEvent.
	 * @param cancelSelect
	 *            If select should be cancelled.
	 */
	public Tabs setAjaxSelectEvent(ITabsAjaxEvent selectEvent, boolean cancelSelect)
	{
		this.ajaxEvents.put(TabEvent.select, selectEvent);
		setSelectEvent(new TabsAjaxSelectJsScopeUiEvent(this, TabEvent.select, cancelSelect));
		return this;
	}

	/**
	 * Set the callback when a tab is shown
	 * 
	 * @param show
	 * @return instance of the current component
	 */
	public Tabs setShowEvent(JsScopeUiEvent show)
	{
		this.options.put("show", show);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX show event.
	 * 
	 * @param showEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxShowEvent(ITabsAjaxEvent showEvent)
	{
		this.ajaxEvents.put(TabEvent.show, showEvent);
		setShowEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.show));
		return this;
	}

	/*---- Methods section ---*/

	/**
	 * Returns the {@link JsStatement} to add the given component in the tab panel.
	 * 
	 * @param index
	 *            the insertion index.
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(int index, String title, Component contentToAdd)
	{
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'",
			"'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'", "" + index);
	}

	/**
	 * Add the given component in the tab panel.
	 * 
	 * @param ajaxRequestTarget
	 * @param index
	 *            the insertion index.
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, int index, String title,
			Component contentToAdd)
	{
		ajaxRequestTarget.appendJavaScript(add(index, title, contentToAdd).render().toString());
	}

	/**
	 * Returns the {@link JsStatement} to add the given component at the end of the tab
	 * panel.
	 * 
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement add(String title, Component contentToAdd)
	{
		contentToAdd.setOutputMarkupId(true);
		return new JsQuery(this).$().chain("tabs", "'add'",
			"'#" + contentToAdd.getMarkupId() + "'", "'" + title + "'");
	}

	/**
	 * Add the given component in the tab panel.
	 * 
	 * @param ajaxRequestTarget
	 * @param title
	 *            the tab title.
	 * @param contentToAdd
	 *            the {@link Component} to add.
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, String title, Component contentToAdd)
	{
		ajaxRequestTarget.appendJavaScript(add(title, contentToAdd).render().toString());
	}

	/**
	 * Method to add a new tab This will return the element back to its pre-init state.
	 * 
	 * @param url
	 *            URL
	 * @param label
	 *            Label of the tab
	 * @return the associated JsStatement
	 */
	public JsStatement add(String url, String label)
	{
		return new JsQuery(this).$().chain("tabs", "'add'", new LiteralOption(url).toString(),
			new LiteralOption(label).toString());
	}

	/**
	 * Method to add a new tab within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 * @param url
	 *            URL
	 * @param label
	 *            Label of the tab
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, String url, String label)
	{
		ajaxRequestTarget.appendJavaScript(this.add(url, label).render().toString());
	}

	/**
	 * Method to add a new tab This will return the element back to its pre-init state.
	 * 
	 * @param url
	 *            URL
	 * @param label
	 *            Label of the tab
	 * @param index
	 *            Index of insertion
	 * @return the associated JsStatement
	 */
	public JsStatement add(String url, String label, int index)
	{
		return new JsQuery(this).$().chain("tabs", "'add'", new LiteralOption(url).toString(),
			new LiteralOption(label).toString(), Integer.toString(index));
	}

	/**
	 * Method to add a new tab within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 * @param url
	 *            URL
	 * @param label
	 *            Label of the tab
	 * @param index
	 *            Index of insertion
	 */
	public void add(AjaxRequestTarget ajaxRequestTarget, String url, String label, int index)
	{
		ajaxRequestTarget.appendJavaScript(this.add(url, label, index).render().toString());
	}

	/**
	 * Method to terminate all running tab ajax requests and animations This will return
	 * the element back to its pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement abort()
	{
		return new JsQuery(this).$().chain("tabs", "'abort'");
	}

	/**
	 * Method to terminate all running tab ajax requests and animations within the ajax
	 * request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void abort(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.abort().render().toString());
	}

	/**
	 * Method to destroy the tabs This will return the element back to its pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(this).$().chain("tabs", "'destroy'");
	}

	/**
	 * Method to destroy the tabs within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method to disable the tabs
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return new JsQuery(this).$().chain("tabs", "'disable'");
	}

	/**
	 * Method to disable the tabs within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
	}

	/**
	 * Method to disable a tab
	 * 
	 * @param index
	 *            Index tab to disable
	 * @return the associated JsStatement
	 */
	public JsStatement disable(int index)
	{
		return new JsQuery(this).$().chain("tabs", "'disable'", Integer.toString(index));
	}

	/**
	 * Method to disable a tab within the ajax request
	 * 
	 * @param index
	 *            Index tab to disable
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget, int index)
	{
		ajaxRequestTarget.appendJavaScript(this.disable(index).render().toString());
	}

	/**
	 * Method to enable the tabs
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return new JsQuery(this).$().chain("tabs", "'enable'");
	}

	/**
	 * Method to enable the tabs within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}

	/**
	 * Method to enable a tab
	 * 
	 * @param index
	 *            Index tab to enable
	 * @return the associated JsStatement
	 */
	public JsStatement enable(int index)
	{
		return new JsQuery(this).$().chain("tabs", "'enable'", Integer.toString(index));
	}

	/**
	 * Method to enable a tab within the ajax request
	 * 
	 * @param index
	 *            Index tab to enable
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget, int index)
	{
		ajaxRequestTarget.appendJavaScript(this.enable(index).render().toString());
	}

	/**
	 * Method retrieving the number of tabs of the first matched tab pane
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement length()
	{
		return new JsQuery(this).$().chain("tabs", "'length'");
	}

	/**
	 * Method to reload the content of an Ajax tab programmatically
	 * 
	 * @param index
	 *            Index tab to select
	 * @return the associated JsStatement
	 */
	public JsStatement load(int index)
	{
		return new JsQuery(this).$().chain("tabs", "'load'", Integer.toString(index));
	}

	/**
	 * Method to reload the content of an Ajax tab programmatically within the ajax
	 * request
	 * 
	 * @param index
	 *            Index tab to select
	 * @param ajaxRequestTarget
	 */
	public void load(AjaxRequestTarget ajaxRequestTarget, int index)
	{
		ajaxRequestTarget.appendJavaScript(this.load(index).render().toString());
	}

	/**
	 * Returns the {@link JsStatement} to remove the tab at the given index.
	 * 
	 * @param index
	 *            the remove index.
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement remove(int index)
	{
		return new JsQuery(this).$().chain("tabs", "'remove'", "" + index);
	}

	/**
	 * Method to remove a tab within the ajax request
	 * 
	 * @param index
	 *            the remove index
	 * @param ajaxRequestTarget
	 */
	public void remove(AjaxRequestTarget ajaxRequestTarget, int index)
	{
		ajaxRequestTarget.appendJavaScript(this.remove(index).render().toString());
	}

	/**
	 * Method to set up an automatic rotation through tabs of a tab pane
	 * 
	 * @param ms
	 *            Amount of time in milliseconds
	 * @return the associated JsStatement
	 */
	public JsStatement rotate(int ms)
	{
		return new JsQuery(this).$().chain("tabs", "'rotate'", Integer.toString(ms));
	}

	/**
	 * Method to set up an automatic rotation through tabs of a tab pane within the ajax
	 * request
	 * 
	 * @param ms
	 *            Amount of time in milliseconds
	 * @param ajaxRequestTarget
	 */
	public void rotate(AjaxRequestTarget ajaxRequestTarget, int ms)
	{
		ajaxRequestTarget.appendJavaScript(this.rotate(ms).render().toString());
	}

	/**
	 * Method to set up an automatic rotation through tabs of a tab pane
	 * 
	 * @param ms
	 *            Amount of time in milliseconds
	 * @param continuing
	 *            Continue the rotation after a tab has been selected by a user
	 * @return the associated JsStatement
	 */
	public JsStatement rotate(int ms, boolean continuing)
	{
		return new JsQuery(this).$().chain("tabs", "'rotate'", Integer.toString(ms),
			Boolean.toString(continuing));
	}

	/**
	 * Method to set up an automatic rotation through tabs of a tab pane within the ajax
	 * request
	 * 
	 * @param ms
	 *            Amount of time in milliseconds
	 * @param continuing
	 *            Continue the rotation after a tab has been selected by a user
	 * @param ajaxRequestTarget
	 */
	public void rotate(AjaxRequestTarget ajaxRequestTarget, int ms, boolean continuing)
	{
		ajaxRequestTarget.appendJavaScript(this.rotate(ms).render().toString());
	}

	/**
	 * Method to select a tab
	 * 
	 * @param index
	 *            Index tab to select
	 * @return the associated JsStatement
	 */
	public JsStatement select(int index)
	{
		return new JsQuery(this).$().chain("tabs", "'select'", Integer.toString(index));
	}

	/**
	 * Method to select a tab within the ajax request
	 * 
	 * @param index
	 *            Index tab to select
	 * @param ajaxRequestTarget
	 */
	public void select(AjaxRequestTarget ajaxRequestTarget, int index)
	{
		ajaxRequestTarget.appendJavaScript(this.select(index).render().toString());
	}

	/**
	 * Method to change the url from which an Ajax (remote) tab will be loaded
	 * 
	 * @param index
	 *            Index tab to select
	 * @param url
	 *            URL
	 * @return the associated JsStatement
	 */
	public JsStatement url(int index, String url)
	{
		return new JsQuery(this).$().chain("tabs", "'url'", Integer.toString(index),
			new LiteralOption(url).toString());
	}

	/**
	 * Method to change the url from which an Ajax (remote) tab will be loaded within the
	 * ajax request
	 * 
	 * @param index
	 *            Index tab to select
	 * @param url
	 *            URL
	 * @param ajaxRequestTarget
	 */
	public void url(AjaxRequestTarget ajaxRequestTarget, int index, String url)
	{
		ajaxRequestTarget.appendJavaScript(this.url(index, url).render().toString());
	}

	/**
	 * Method to returns the .ui-slider element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(this).$().chain("tabs", "'widget'");
	}

	/**
	 * Method to returns the .ui-slider element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}
}
