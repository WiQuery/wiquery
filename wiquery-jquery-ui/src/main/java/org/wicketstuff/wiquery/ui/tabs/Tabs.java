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
package org.wicketstuff.wiquery.ui.tabs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.cycle.RequestCycle;
import org.wicketstuff.wiquery.core.events.MouseEvent;
import org.wicketstuff.wiquery.core.javascript.JsQuery;
import org.wicketstuff.wiquery.core.javascript.JsScopeContext;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.core.options.ArrayItemOptions;
import org.wicketstuff.wiquery.core.options.EventLabelOptions;
import org.wicketstuff.wiquery.core.options.ICollectionItemOptions;
import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.core.options.IntegerItemOptions;
import org.wicketstuff.wiquery.core.options.Options;
import org.wicketstuff.wiquery.ui.core.JsScopeUiEvent;
import org.wicketstuff.wiquery.ui.options.HeightStyleEnum;
import org.wicketstuff.wiquery.ui.widget.WidgetJavaScriptResourceReference;

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
	public static final String UI_TAB = "ui.newTab";

	/**
	 * Properties on the ui parameter (use it into callback functions) : element, that
	 * contains the selected tab contents
	 */
	public static final String UI_PANEL = "ui.newPanel";

	/**
	 * Properties on the ui parameter (use it into callback functions) : zero-based index
	 * of the selected tab
	 */
	public static final String UI_INDEX = "ui.newTab.index()";
	
	/**
	 * Options are used to customize this component.
	 */
	private Options options;

	/**
	 * 	 The tab event.
	 */
	public static enum TabEvent
	{
		activate,
		beforeActivate,
		beforeLoad,
		load
	}

	/**
	 * This class is only needed to make public the method generateCallbackScript.
	 * 
	 * @author Ernesto Reinaldo Barreiro
	 */
	private abstract static class TabsAjaxBehavior extends AbstractDefaultAjaxBehavior
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
	 * The tab event.
	 */
	public static final String TAB_EVENT = "tabEvent";

	public static final String TAB_INDEX = "tabIndex";

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
					TAB_INDEX, UI_INDEX)));

				scopeContext.append(
				// delegating call-back generation to AJAX behavior
				// so that we don't miss 'decorator' related functionality.
				tabs.tabsAjaxBehavior.getCallbackScript());

		}
	}

	/**
	 * Specific function for beforeActivate event.
	 * 
	 * @author reiern70
	 */
	public static class TabsAjaxBeforeActivateJsScopeUiEvent extends TabsAjaxJsScopeUiEvent
	{
		private static final long serialVersionUID = 1L;

		private boolean cancelSelect = false;

		public TabsAjaxBeforeActivateJsScopeUiEvent(Tabs tabs, TabEvent event, boolean cancelSelect)
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
	public interface ITabsAjaxEvent extends Serializable
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
		void onEvent(AjaxRequestTarget target, Tabs tabs, int index);
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
		return this.options.getCollectionItemOptions("disabled");
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

		if (event instanceof EventLabelOptions)
		{
			return (EventLabelOptions) event;
		}

		return new EventLabelOptions(MouseEvent.CLICK);
	}

	/**
	 * If and how to animate the hiding of the panel.
	 * 
	 * @param hideOptions
	 * @return instance of the current component
	 */
	public Tabs setHide(TabsAnimateOption hideOptions)
	{
		this.options.put("hide", hideOptions);
		return this;
	}
	
	/**
	 * @return the hide option value
	 */
	public TabsAnimateOption getHide()
	{
		IComplexOption hideOptions = this.options.getComplexOption("hide");
		if (hideOptions instanceof TabsAnimateOption)
		{
			return (TabsAnimateOption) hideOptions;
		}
		
		return null;
	}
	
	/**
	 * If and how to animate the showing of the panel.
	 * 
	 * @param showOptions
	 * @return instance of the current component
	 */
	public Tabs setShow(TabsAnimateOption showOptions)
	{
		this.options.put("show", showOptions);
		return this;
	}
	
	/**
	 * @return the show option value
	 */
	public TabsAnimateOption getShow()
	{
		IComplexOption showOptions = this.options.getComplexOption("show");
		if (showOptions instanceof TabsAnimateOption)
		{
			return (TabsAnimateOption) showOptions;
		}
		
		return null;
	}

	/**
	 * @return the active option value
	 */
	public int getActive()
	{
		Integer index = this.options.getInt("active");
		if (index != null)
		{
			return index;
		}
		
		return 0;
	}
	
	/**
	 * The zero-based index of the panel that is active (open).
	 * A negative value selects panels going backward from the last panel.
	 * 
	 * @param index
	 * @return instance of the current component
	 */
	public Tabs setActive(int index)
	{
		this.options.put("active", index);
		return this;
	}
	
	/**
	 * Setting active to false will collapse all panels.
	 * This requires the collapsible option to be true.
	 * 
	 * @param isActive
	 * @return instance of the current component
	 */
	public Tabs setActive(boolean isActive)
	{
		this.options.put("active", isActive);
		return this;
	}
	
	/**
	 * @return the heightStyle option value
	 */
	public HeightStyleEnum getHeightStyle()
	{
		String literal = this.options.getLiteral("heightStyle");
		return literal == null ? HeightStyleEnum.CONTENT : HeightStyleEnum.valueOf(literal.toUpperCase());
	}
	
	/**
	 * Controls the height of the tabs widget and each panel. Possible values:
	 * <ul>
	 * 	<li>AUTO: All panels will be set to the height of the tallest panel.</li>
	 * 	<li>FILL: Expand to the available height based on the tabs' parent height.</li>
	 * 	<li>CONTENT: Each panel will be only as tall as its content.</li>
	 * </ul>
	 * @param heightStyle
	 * @return
	 */
	public Tabs setHeightStyle(HeightStyleEnum heightStyle)
	{
		this.options.putLiteral("heightStyle", heightStyle.name().toLowerCase());
		return this;
	}
	
	/*---- Events section ---*/

	/**
	 * Set the callback when the content of a remote tab is about to load.
	 * 
	 * @param beforeLoad
	 * @return instance of the current component
	 */
	public Tabs setBeforeLoadEvent(JsScopeUiEvent beforeLoad)
	{
		this.options.put("beforeLoad", beforeLoad);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX beforeLoad event.
	 * 
	 * @param beforeLoadEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxBeforeLoadEvent(ITabsAjaxEvent beforeLoadEvent)
	{
		this.ajaxEvents.put(TabEvent.beforeLoad, beforeLoadEvent);
		setBeforeLoadEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.beforeLoad));
		return this;
	}
	
	/**
	 * Set the callback when the content of a remote tab has been loaded.
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
	 * Set the callback when the user is clicking the tab
	 * 
	 * @param beforeActivate
	 * @return instance of the current component
	 */
	public Tabs setBeforeActivateEvent(JsScopeUiEvent beforeActivate)
	{
		this.options.put("beforeActivate", beforeActivate);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX beforeActivate event.
	 * 
	 * @param beforeActivateEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxBeforeActivateEvent(ITabsAjaxEvent beforeActivateEvent)
	{
		this.ajaxEvents.put(TabEvent.beforeActivate, beforeActivateEvent);
		setBeforeActivateEvent(new TabsAjaxBeforeActivateJsScopeUiEvent(this, TabEvent.beforeActivate, false));
		return this;
	}

	/**
	 * Sets the call-back for the AJAX beforeActivate event.
	 * 
	 * @param beforeActivateEvent
	 *            The ITabsAjaxEvent.
	 * @param cancelSelect
	 *            If select should be cancelled.
	 */
	public Tabs setAjaxBeforeActivateEvent(ITabsAjaxEvent beforeActivateEvent, boolean cancelSelect)
	{
		this.ajaxEvents.put(TabEvent.beforeActivate, beforeActivateEvent);
		setBeforeActivateEvent(new TabsAjaxBeforeActivateJsScopeUiEvent(this, TabEvent.beforeActivate, cancelSelect));
		return this;
	}

	/**
	 * Set the callback when a tab is activated.
	 * 
	 * @param activate
	 * @return instance of the current component
	 */
	public Tabs setActivateEvent(JsScopeUiEvent activate)
	{
		this.options.put("activate", activate);
		return this;
	}

	/**
	 * Sets the call-back for the AJAX activate event.
	 * 
	 * @param activateEvent
	 *            The ITabsAjaxEvent.
	 */
	public Tabs setAjaxActivateEvent(ITabsAjaxEvent activateEvent)
	{
		this.ajaxEvents.put(TabEvent.activate, activateEvent);
		setActivateEvent(new TabsAjaxJsScopeUiEvent(this, TabEvent.activate));
		return this;
	}

	/*---- Methods section ---*/

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
	 * Returns the {@link JsStatement} to refresh tabs.
	 * 
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement refresh()
	{
		return new JsQuery(this).$().chain("tabs", "'refresh'");
	}

	/**
	 * Method to refresh tabs within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.refresh().render().toString());
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
