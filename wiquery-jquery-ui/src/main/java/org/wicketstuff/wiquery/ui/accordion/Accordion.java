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
package org.wicketstuff.wiquery.ui.accordion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.wicketstuff.wiquery.core.javascript.JsQuery;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.core.options.LiteralOption;
import org.wicketstuff.wiquery.core.options.Options;
import org.wicketstuff.wiquery.ui.JQueryUIJavaScriptResourceReference;
import org.wicketstuff.wiquery.ui.core.JsScopeUiEvent;
import org.wicketstuff.wiquery.ui.options.ClassesOption;
import org.wicketstuff.wiquery.ui.options.HeightStyleEnum;
import org.wicketstuff.wiquery.ui.themes.UiIcon;

/**
 * $Id$
 * <p>
 * Creates an accordion UI component from this {@link WebMarkupContainer}'s HTML markup.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
public class Accordion extends WebMarkupContainer
{

	private static final long serialVersionUID = -3832846396234668551L;

	/**
	 * $Id$
	 * <p>
	 * Defines the possible events to trigger the accordion's content switching.
	 * </p>
	 * 
	 * @author Lionel Armanet
	 * @since 0.6
	 */
	public static enum AccordionTriggerEvent {
		CLICK, MOUSEOVER
	}

	/**
	 * The options set for this component.
	 */
	private final Options options;

	public Accordion(String id)
	{
		super(id);
		options = new Options(this);
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
		response
			.render(JavaScriptHeaderItem.forReference(JQueryUIJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
	}

	public JsStatement statement()
	{
		return new JsQuery(this).$().chain("accordion", options.getJavaScriptOptions());
	}

	/**
	 * Method retrieving the options of the component
	 * 
	 * @return the options
	 */
	public Options getOptions()
	{
		return options;
	}

	/*---- Options section ---*/
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
	 * The zero-based index of the panel that is active (open). A negative value selects panels
	 * going backward from the last panel.
	 * 
	 * @param index
	 * @return instance of the current component
	 */
	public Accordion setActive(int index)
	{
		this.options.put("active", index);
		return this;
	}

	/**
	 * Setting active to false will collapse all panels. This requires the collapsible option to be
	 * true.
	 * 
	 * @param isActive
	 * @return instance of the current component
	 */
	public Accordion setActive(boolean isActive)
	{
		this.options.put("active", isActive);
		return this;
	}

	/**
	 * @return the animate option value
	 */
	public AccordionAnimateOption getAnimate()
	{
		IComplexOption animate = this.options.getComplexOption("animate");
		if (animate instanceof AccordionAnimateOption)
		{
			return (AccordionAnimateOption)animate;
		}

		return new AccordionAnimateOption(new AccordionEffectOptionObject());
	}

	/**
	 * If and how to animate changing panels.
	 * 
	 * @param animate
	 *            see {@link AccordionAnimateOption}
	 * @return instance of the current component
	 */
	public Accordion setAnimate(AccordionAnimateOption animate)
	{
		this.options.put("animate", animate);
		return this;
	}

	public ClassesOption getClasses()
	{
		IComplexOption animate = this.options.getComplexOption("classes");
		if (animate instanceof ClassesOption)
		{
			return (ClassesOption)animate;
		}

		return new ClassesOption();
	}

	public Accordion setClasses(ClassesOption classes)
	{
		this.options.put("classes", classes);
		return this;
	}

	/**
	 * @see #setCollapsible(boolean)
	 */
	public boolean isCollapsible()
	{
		if (this.options.containsKey("collapsible"))
		{
			return this.options.getBoolean("collapsible");
		}

		return false;
	}

	/**
	 * Whether all the sections can be closed at once. Allows collapsing the active section by the
	 * triggering event (click is the default).
	 * 
	 * @param collapsible
	 */
	public Accordion setCollapsible(boolean collapsible)
	{
		this.options.put("collapsible", collapsible);
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
	 * Disables (true) or enables (false) the accordion. Can be set when initialising (first
	 * creating) the accordion.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Accordion setDisabled(boolean disabled)
	{
		this.options.put("disabled", disabled);
		return this;
	}

	/**
	 * Returns the {@link AccordionTriggerEvent}.
	 * 
	 * @see #setTriggerEvent(AccordionTriggerEvent)
	 */
	public AccordionTriggerEvent getEvent()
	{
		String literal = this.options.getLiteral("event");
		return literal == null ? AccordionTriggerEvent.CLICK
			: AccordionTriggerEvent.valueOf(literal.toUpperCase());
	}

	/**
	 * Sets the {@link AccordionTriggerEvent} to use to open content.
	 * 
	 * @return instance of the current component
	 */
	public Accordion setEvent(AccordionTriggerEvent accordionTriggerEvent)
	{
		this.options.putLiteral("event", accordionTriggerEvent.name().toLowerCase());
		return this;
	}

	/**
	 * @return the header option value
	 */
	public AccordionHeader getHeader()
	{
		IComplexOption header = this.options.getComplexOption("header");
		if (header != null && header instanceof AccordionHeader)
		{
			return (AccordionHeader)header;
		}

		return new AccordionHeader(new LiteralOption("> li> :first-child, > :not(li):even"));
	}

	/**
	 * Sets the CSS selector used to defined a header in this accordion.
	 * 
	 * @return instance of the current component
	 */
	public Accordion setHeader(AccordionHeader header)
	{
		this.options.put("header", header);
		return this;
	}

	/**
	 * @return the heightStyle option value
	 */
	public HeightStyleEnum getHeightStyle()
	{
		String literal = this.options.getLiteral("heightStyle");
		return literal == null ? HeightStyleEnum.CONTENT
			: HeightStyleEnum.valueOf(literal.toUpperCase());
	}

	/**
	 * Controls the height of the accordion and each panel. Possible values:
	 * <ul>
	 * <li>AUTO: All panels will be set to the height of the tallest panel.</li>
	 * <li>FILL: Expand to the available height based on the accordion's parent height.</li>
	 * <li>CONTENT: Each panel will be only as tall as its content.</li>
	 * </ul>
	 * 
	 * @param heightStyle
	 */
	public Accordion setHeightStyle(HeightStyleEnum heightStyle)
	{
		this.options.putLiteral("heightStyle", heightStyle.name().toLowerCase());
		return this;
	}


	/**
	 * @see #setIcons(AccordionIcon)
	 */
	public AccordionIcon getIcons()
	{
		IComplexOption icons = this.options.getComplexOption("icons");
		if (icons != null && icons instanceof AccordionIcon)
		{
			return (AccordionIcon)icons;
		}

		return new AccordionIcon("ui-icon-triangle-1-e", "ui-icon-triangle-1-s");
	}

	/**
	 * Icons to use for headers. Icons may be specified for 'header' and 'headerSelected', and we
	 * recommend using the icons native to the jQuery UI CSS Framework manipulated by jQuery UI
	 * ThemeRoller Default: { 'header': 'ui-icon-triangle-1-e', 'headerSelected':
	 * 'ui-icon-triangle-1-s' }
	 * 
	 * @param icons
	 * @return instance of the current component
	 */
	public Accordion setIcons(AccordionIcon icons)
	{
		if (icons != null)
			this.options.put("icons", icons);
		else
			this.options.removeOption("icons");
		return this;
	}

	/**
	 * Icons to use for headers.
	 * 
	 * @param header
	 * @param headerSelected
	 * @return instance of the current component
	 */
	public Accordion setIcons(UiIcon header, UiIcon headerSelected)
	{
		setIcons(new AccordionIcon(header, headerSelected));
		return this;
	}

	/**
	 * Allows to hide the icons.
	 * 
	 * @return instance of the current component
	 */
	public Accordion hideIcons()
	{
		setIcons(new AccordionIcon(false));
		return this;
	}


	/*---- Events section ---*/

	/**
	 * Set's the callback triggered after a panel has been activated (after animation completes).
	 * 
	 * @param activate
	 * @return instance of the current component
	 */
	public Accordion setActivateEvent(JsScopeUiEvent activate)
	{
		this.options.put("activate", activate);
		return this;
	}

	/**
	 * Set's the callback triggered directly before a panel is activated.
	 * 
	 * @param beforeActivate
	 * @return instance of the current component
	 */
	public Accordion setBeforeActivateEvent(JsScopeUiEvent beforeActivate)
	{
		this.options.put("beforeActivate", beforeActivate);
		return this;
	}

	public Accordion setCreateEvent(JsScopeUiEvent create)
	{
		this.options.put("create", create);
		return this;
	}

	/*---- Methods section ---*/

	/**
	 * Method to destroy the accordion This will return the element back to its pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(this).$().chain("accordion", "'destroy'");
	}

	/**
	 * Method to destroy the accordion within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method to disable the accordion
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return new JsQuery(this).$().chain("accordion", "'disable'");
	}

	/**
	 * Method to disable the accordion within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
	}

	/**
	 * Method to enable the accordion
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return new JsQuery(this).$().chain("accordion", "'enable'");
	}

	/**
	 * Method to enable the accordion within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}

	/**
	 * Method to refresh the accordion
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement refresh()
	{
		return new JsQuery(this).$().chain("accordion", "'refresh'");
	}

	/**
	 * Method to refresh the accordion within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.refresh().render().toString());
	}

	/**
	 * Method to returns the .ui-accordion element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(this).$().chain("accordion", "'widget'");
	}

	/**
	 * Method to returns the .ui-accordion element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}
}