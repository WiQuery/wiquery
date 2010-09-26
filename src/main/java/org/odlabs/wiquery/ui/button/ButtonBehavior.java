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
package org.odlabs.wiquery.ui.button;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates a jQuery UI button behavior to decorate a link or a button
 * HTML markup.
 * </p>
 * 
 * <p>The click event is not a part of the jQuery UI framework</p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
@WiQueryUIPlugin
public class ButtonBehavior extends WiQueryAbstractBehavior {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -4079980500720298690L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public ButtonBehavior() {
		super();
		options = new Options();
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(ButtonJavascriptResourceReference.get());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("button", options.getJavaScriptOptions());
	}
	
	/*---- Options section ---*/
	
	/**Disables (true) or enables (false) the button. Can be set when 
	 * initialising (first creating) the button.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public ButtonBehavior setDisabled(boolean disabled) {
		this.options.put("disabled", disabled);
		return this;
	}
	
	/**
	 * @return the disabled option
	 */
	public Boolean isDisabled() {
		if(this.options.containsKey("disabled")){
			return this.options.getBoolean("disabled");
		}
		
		return null;
	}
	
	/**
	 * Whether to show any text - when set to false (display no text), icons 
	 * (see icons option) must be enabled, otherwise it'll be ignored.
	 * @param text
	 * @return the Button
	 */
	public ButtonBehavior setText(boolean text) {
		options.put("text", text);
		return this;
	}
	
	/**
	 * @return the text option value
	 */
	public boolean isText() {
		if(options.containsKey("text")){
			return options.getBoolean("text");
		}
		
		return true;
	}
	
	/**
	 * Icons to display, with or without text (see text option). The primary icon 
	 * is displayed on the left of the label text, the secondary on the right. 
	 * Value for the primary and secondary properties must be a classname (String), 
	 * eg. "ui-icon-gear". For using only a primary icon: icons: {primary:'ui-icon-locked'}. 
	 * For using both primary and secondary icon: 
	 * icons: {primary:'ui-icon-gear',secondary:'ui-icon-triangle-1-s'}
	 * @param icons
	 * @return the button
	 */
	public ButtonBehavior setIcons(ButtonIcon icons) {
		options.put("icons", icons);
		return this;
	}
	
	/**
	 * @return the icons value option
	 */
	public ButtonIcon getIcons() {
		return (ButtonIcon) options.getComplexOption("icons");
	}
	
	/**
	 * Text to show on the button. When not specified (null), the element's html 
	 * content is used, or its value attribute when it's an input element of type 
	 * submit or reset; or the html content of the associated label element if 
	 * its an input of type radio or checkbox
	 * @param label
	 * @return the button
	 */
	public ButtonBehavior setLabel(String label) {
		options.putLiteral("label", label);
		return this;
	}
	
	/**
	 * @return the label value option
	 */
	public String getLabel() {
		return options.getLiteral("label");
	}
	
	/*---- Events section ---*/
	
	/*---- Methods section ---*/
	
	/**Method to destroy the button
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(getComponent()).$().chain("button", "'destroy'");
	}

	/**Method to destroy the button within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable the button
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(getComponent()).$().chain("button", "'disable'");
	}

	/**Method to disable the button within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable the button
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(getComponent()).$().chain("button", "'enable'");
	}

	/**Method to enable the button within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method to returns the .ui-autocomplete  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(getComponent()).$().chain("button", "'widget'");
	}

	/**Method to returns the .ui-autocomplete  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.widget().render().toString());
	}
}
