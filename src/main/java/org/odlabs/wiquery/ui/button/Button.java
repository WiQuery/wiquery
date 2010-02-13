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
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.helper.EventsHelper;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates a button UI component from this {@link WebMarkupContainer}'s
 * HTML markup.
 * </p>
 * 
 * <p>The click event is not a part of the jQuery UI framework</p>
 * 
 * @author Julien Roche
 * @since 1.1
 */
@WiQueryUIPlugin
public class Button extends WebMarkupContainer implements
		IWiQueryPlugin {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -5696879997749115456L;
	
	// Properties
	private ButtonBehavior buttonBehavior;
	private JsScopeUiEvent clickEvent;

	/**
	 * Constructor
	 * @param id Wicket identifiant
	 */
	public Button(String id) {
		super(id);
		setOutputMarkupId(true);
		buttonBehavior = new ButtonBehavior();
		add(buttonBehavior);
	}
	
	/**
	 * Constructor
	 * @param id Wicket identifiant
	 * @param label Button label
	 */
	public Button(String id, String label) {
		this(id);
		setLabel(label);
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return buttonBehavior.getOptions();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(ButtonJavascriptResourceReference.get());
	}
	
	/**
	 * @see org.apache.wicket.Component#onComponentTagBody(org.apache.wicket.markup.MarkupStream,
	 *      org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	protected void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag)	{
		String tagname = openTag.getName();
		
		if(!tagname.equalsIgnoreCase("input") 
				&& !tagname.equalsIgnoreCase("button")
				&& !tagname.equalsIgnoreCase("submit")
				&& !tagname.equalsIgnoreCase("reset")
				&& !tagname.equalsIgnoreCase("a")){
			findMarkupStream().throwMarkupException(
					"Component " 
					+ getId() 
					+ " must be applied to a tag of type 'input', 'button' or 'a', not "
					+ openTag.toUserDebugString());
		}
		
		replaceComponentTagBody(markupStream, openTag, getDefaultModelObjectAsString());
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		JsStatement jquery = buttonBehavior.statement();
		
		if(clickEvent != null) {
			jquery.chain(EventsHelper.click(clickEvent));
		}
		
		return jquery;
	}
	
	/*---- Options section ---*/
	
	/**
	 * Whether to show any text - when set to false (display no text), icons 
	 * (see icons option) must be enabled, otherwise it'll be ignored.
	 * @param text
	 * @return the Button
	 */
	public Button setText(boolean text) {
		buttonBehavior.setText(text);
		return this;
	}
	
	/**
	 * @return the text option value
	 */
	public boolean isText() {
		return buttonBehavior.isText();
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
	public Button setIcons(ButtonIcon icons) {
		buttonBehavior.setIcons(icons);
		return this;
	}
	
	/**
	 * @return the icons value option
	 */
	public ButtonIcon getIcons() {
		return buttonBehavior.getIcons();
	}
	
	/**
	 * Text to show on the button. When not specified (null), the element's html 
	 * content is used, or its value attribute when it's an input element of type 
	 * submit or reset; or the html content of the associated label element if 
	 * its an input of type radio or checkbox
	 * @param label
	 * @return the button
	 */
	public Button setLabel(String label) {
		buttonBehavior.setLabel(label);
		return this;
	}
	
	/**
	 * @return the label value option
	 */
	public String getLabel() {
		return buttonBehavior.getLabel();
	}
	
	/*---- Events section ---*/
	
	/**
	 * Usually the native click event, with the exception of keyboard-triggered 
	 * click with the space key on anchors and generally on checkboxes and radio buttons.
	 * 
	 * This is not a part of jQuery UI
	 * 
	 * @param change
	 * @return instance of the current component
	 */
	public Button setClickEvent(JsScopeUiEvent click) {
		this.clickEvent = click;
		return this;
	}
	
	/*---- Methods section ---*/
	
	/**Method to destroy the button
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return buttonBehavior.destroy();
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
		return buttonBehavior.disable();
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
		return buttonBehavior.enable();
	}

	/**Method to enable the button within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
}
