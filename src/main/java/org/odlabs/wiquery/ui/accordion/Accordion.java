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
package org.odlabs.wiquery.ui.accordion;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates an accordion UI component from this {@link WebMarkupContainer}'s
 * HTML markup.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
@WiQueryUIPlugin
public class Accordion extends WebMarkupContainer implements IWiQueryPlugin {

	private static final long serialVersionUID = 2L;

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
	private Options options;

	public Accordion(String id) {
		super(id);
		options = new Options();
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(AccordionJavaScriptResourceReference.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("accordion",
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
	 * Sets the effect to apply when the accordion's content is switched.
	 * 
	 * @param animationEffect
	 *            the effect name to apply. Set it empty if you don't want to
	 *            apply any effect.
	 * @return instance of the current component
	 */
	public Accordion setAnimated(AccordionAnimated animationEffect) {
		this.options.put("animated", animationEffect);
		return this;
	}
	
	/**
	 * @return the animated option value
	 */
	public AccordionAnimated getAnimated() {
		IComplexOption animated = this.options.getComplexOption("animated");
		if(animated != null && animated instanceof AccordionAnimated){
			return (AccordionAnimated) animated;
		}
		
		return new AccordionAnimated("slide");
	}

	/**
	 * Sets the effect to apply when the accordion's content is switched.
	 * 
	 * @param animationEffect
	 *            the effect name to apply. Set it empty if you don't want to
	 *            apply any effect.
	 * @return instance of the current component
	 * @deprecated will be removed in 1.2
	 * @see Accordion#setAnimated(AccordionAnimated)
	 */
	@Deprecated
	public Accordion setAnimationEffect(AccordionAnimated animationEffect) {
		this.options.put("animated", animationEffect);
		return this;
	}
	
	/**
	 * @return the animated option value
	 * @deprecated will be removed in 1.2
	 * @see Accordion#getAnimated()
	 */
	@Deprecated
	public AccordionAnimated getAnimationEffect() {
		IComplexOption animated = this.options.getComplexOption("animated");
		if(animated != null && animated instanceof AccordionAnimated){
			return (AccordionAnimated) animated;
		}
		
		return new AccordionAnimated("slide");
	}

	/**
	 * Sets if the accordion's height is fixed to the highest content part.
	 * 
	 * @param autoHeight
	 *            true if this accordion's height is the highest content's one.
	 * @return instance of the current component
	 */
	public Accordion setAutoHeight(boolean autoHeight) {
		this.options.put("autoHeight", autoHeight);
		return this;
	}

	/**
	 * Returns true if this accordion is auto height.
	 * 
	 * @see #setAutoHeight(boolean)
	 */
	public boolean isAutoHeight() {
		if(this.options.containsKey("autoHeight")){
			return this.options.getBoolean("autoHeight");
		}
		
		return true;
	}

	/**
	 * Sets the {@link AccordionTriggerEvent} to use to open content.
	 * @deprecated will be removed in 1.2
	 * @return instance of the current component
	 */
	@Deprecated
	public Accordion setTriggerEvent(AccordionTriggerEvent accordionTriggerEvent) {
		return setEvent(accordionTriggerEvent);
	}

	/**
	 * Returns the {@link AccordionTriggerEvent}.
	 * @deprecated will be removed in 1.2
	 * @see #setTriggerEvent(org.objetdirect.wickext.ui.accordion.Accordion.AccordionTriggerEvent)
	 */
	@Deprecated
	public AccordionTriggerEvent getTriggerEvent() {
		return getEvent();
	}
	
	/**
	 * Sets the {@link AccordionTriggerEvent} to use to open content.
	 * @return instance of the current component
	 */
	public Accordion setEvent(AccordionTriggerEvent accordionTriggerEvent) {
		this.options.putLiteral("event", accordionTriggerEvent.name()
				.toLowerCase());
		return this;
	}

	/**
	 * Returns the {@link AccordionTriggerEvent}.
	 * 
	 * @see #setTriggerEvent(org.objetdirect.wickext.ui.accordion.Accordion.AccordionTriggerEvent)
	 */
	public AccordionTriggerEvent getEvent() {
		String literal = this.options.getLiteral("event");
		return literal == null ? AccordionTriggerEvent.CLICK : 
			AccordionTriggerEvent.valueOf(literal.toUpperCase());
	}

	/**
	 * Makes this accordion's height to the maximum size possible in this'
	 * parent container.
	 * <p>
	 * <em>Overrides {@link #setAutoHeight(boolean)} behavior</em>
	 * </p>
	 * @return instance of the current component
	 */
	public Accordion setFillSpace(boolean fillSpace) {
		this.options.put("fillSpace", fillSpace);
		return this;
	}

	/**
	 * Returns if this accordion fill space.
	 * 
	 * @see #setFillSpace(boolean)
	 */
	public boolean isFillSpace() {
		if(this.options.containsKey("fillSpace")){
			return this.options.getBoolean("fillSpace");
		}
		
		return false;
	}
	
	/**Disables (true) or enables (false) the accordion. Can be set when 
	 * initialising (first creating) the accordion.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Accordion setDisabled(boolean disabled) {
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

	/**
	 * Sets the CSS selector used to defined a header in this accordion.
	 * @return instance of the current component
	 */
	public Accordion setHeader(AccordionHeader header) {
		this.options.put("header", header);
		return this;
	}
	
	/**
	 * @return the header option value
	 */
	public AccordionHeader getHeader() {
		IComplexOption header = this.options.getComplexOption("header");
		if(header != null && header instanceof AccordionHeader){
			return (AccordionHeader) header;
		}
		
		return new AccordionHeader(new LiteralOption("> li> :first-child, > :not(li):even"));
	}

	/**
	 * If set, clears height and overflow styles after finishing animations. 
	 * This enables accordions to work with dynamic content. <b>Won't work together
	 * with autoHeight.</b>
	 * @param clearStyle
	 * @return instance of the current component
	 */
	public Accordion setClearStyle(boolean clearStyle) {
		this.options.put("clearStyle", clearStyle);
		return this;
	}

	/**
	 * @see #setClearStyle(boolean)
	 */
	public boolean isClearStyle() {
		if(this.options.containsKey("clearStyle")){
			return this.options.getBoolean("clearStyle");
		}
		
		return false;
	}
	
	/**
	 * Whether all the sections can be closed at once. Allows collapsing the 
	 * active section by the triggering event (click is the default).
	 * @param collapsible
	 */
	public Accordion setCollapsible(boolean collapsible) {
		this.options.put("collapsible", collapsible);
		return this;
	}

	/**
	 * @see #setCollapsible(boolean)
	 */
	public boolean isCollapsible() {
		if(this.options.containsKey("collapsible")){
			return this.options.getBoolean("collapsible");
		}
		
		return false;
	}
	
	/**
	 * If set, looks for the anchor that matches location.href and activates it.
	 * Great for href-based state-saving. Use navigationFilter to implement 
	 * your own matcher.
	 * @param navigation
	 * @return instance of the current component
	 */
	public Accordion setNavigation(boolean navigation) {
		this.options.put("navigation", navigation);
		return this;
	}

	/**
	 * @see #setNavigation(boolean)
	 */
	public boolean isNavigation() {
		if(this.options.containsKey("navigation")){
			return this.options.getBoolean("navigation");
		}
		
		return false;
	}
	
	/**
	 * Overwrite the default location.href-matching with your own matcher.
	 * @param navigationFilter
	 * @return instance of the current component
	 */
	public Accordion setNavigationFilter(JsScope navigationFilter) {
		this.options.put("navigationFilter", navigationFilter);
		return this;
	}
	
	/**
	 * @see #setIcon(AccordionIcon)
	 */
	public AccordionIcon getIcons() {
		IComplexOption icons = this.options.getComplexOption("icons");
		if(icons != null && icons instanceof AccordionIcon){
			return (AccordionIcon) icons;
		}
		
		return new AccordionIcon("ui-icon-triangle-1-e", "ui-icon-triangle-1-s");
	}
	
	/**
	 * Icons to use for headers. Icons may be specified for 'header' and 'headerSelected',
	 * and we recommend using the icons native to the jQuery UI CSS Framework 
	 * manipulated by jQuery UI ThemeRoller
	 * Default: { 'header': 'ui-icon-triangle-1-e', 'headerSelected': 'ui-icon-triangle-1-s' }
	 * @param icon
	 * @return instance of the current component
	 */
	public Accordion setIcons(AccordionIcon icons) {	
		this.options.put("icons", icons);
		return this;
	}
	
	/**
	 * @see #setActive(AccordionActive)
	 */
	public AccordionActive getActive() {
		IComplexOption active = this.options.getComplexOption("active");
		if(active != null && active instanceof AccordionActive){
			return (AccordionActive) active;
		}
		
		return null;
	}
	
	/**
	 * Selector for the active element. Set to false to display none at start. 
	 * Needs collapsible: true.
	 * 
	 * Type of element : Selector, Element, jQuery, Boolean, Number
	 * 
	 * Default: first child
	 * 
	 * @param active
	 * @return instance of the current component
	 */
	public Accordion setActive(AccordionActive active) {	
		this.options.put("active", active);
		return this;
	}
	
	/*---- Events section ---*/
	
	/**Set's the callback when the accordion changes. If the accordion is animated,
	 * the event will be triggered upon completion of the animation; otherwise,
	 * it is triggered immediately. 
	 * @param change
	 * @return instance of the current component
	 */
	public Accordion setChangeEvent(JsScopeUiEvent change) {
		this.options.put("change", change);
		return this;
	}
	
	/**Set's the callback when the accordion starts to change.  
	 * @param changestart
	 * @return instance of the current component
	 */
	public Accordion setChangeStartEvent(JsScopeUiEvent changestart) {
		this.options.put("changestart", changestart);
		return this;
	}
	
	/*---- Methods section ---*/
	
	/**Method to activate a content part of the Accordion programmatically. 
	 * The index can be a zero-indexed number to match the position of the header
	 * to close or a Selector matching an element. Pass false to close all 
	 * (only possible with collapsible:true).
	 * This will return the element back to its pre-init state.
	 * @param index
	 * @return the associated JsStatement
	 */
	public JsStatement activate(int index) {
		return new JsQuery(this).$().chain("accordion", "'activate'", Integer.toString(index));
	}

	/**Method to destroy the accordion within the ajax request
	 * @param ajaxRequestTarget
	 * @param index
	 */
	public void activate(AjaxRequestTarget ajaxRequestTarget, int index) {
		ajaxRequestTarget.appendJavascript(this.activate(index).render().toString());
	}
	
	/**Method to destroy the accordion
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("accordion", "'destroy'");
	}

	/**Method to destroy the accordion within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable the accordion
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(this).$().chain("accordion", "'disable'");
	}

	/**Method to disable the accordion within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable the accordion
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(this).$().chain("accordion", "'enable'");
	}

	/**Method to enable the accordion within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method to returns the .ui-accordion  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(this).$().chain("accordion", "'widget'");
	}

	/**Method to returns the .ui-accordion  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.widget().render().toString());
	}
}