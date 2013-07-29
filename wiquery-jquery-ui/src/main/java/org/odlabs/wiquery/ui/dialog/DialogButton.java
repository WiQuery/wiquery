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
package org.odlabs.wiquery.ui.dialog;

import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IListItemOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.button.ButtonIcon;
import org.odlabs.wiquery.ui.themes.UiIcon;

/**
 * $Id: DialogButton.java
 * <p>
 * Bean to represent a dialog button
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class DialogButton extends Object implements IListItemOption
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -1683433456056445578L;

	// Properties
	private Options options;
	
	private IComplexOption callback;

	/**
	 * Default constructor.
	 */
	public DialogButton()
	{
		super();
	}

	/**
	 * Build a new instance of a dialog button with text.
	 * 
	 * @param text
	 *            Text of the button
	 * @param jsScope
	 *            JsScope of the button
	 */
	public DialogButton(String text, JsScope jsScope)
	{
		super();
		options = new Options();
		setText(text);
		setClick(jsScope);
	}
	
	@Override
	public CharSequence getJavascriptOption()
	{
		return options.getJavaScriptOptions();
	}

	/**
	 * Method retrieving the options
	 * 
	 * @return the options
	 */
	protected Options getOptions()
	{
		return options;
	}

	/**
	 * Text to show on the button. When not specified (null), the element's html content
	 * is used, or its value attribute when it's an input element of type submit or reset;
	 * or the html content of the associated label element if its an input of type radio
	 * or checkbox
	 * 
	 * @param text
	 * @return the button
	 */
	public DialogButton setText(String text)
	{
		options.putLiteral("text", text);
		return this;
	}

	/**
	 * Text to show on the button. When not specified (null), the element's html content
	 * is used, or its value attribute when it's an input element of type submit or reset;
	 * or the html content of the associated label element if its an input of type radio
	 * or checkbox
	 * 
	 * @param text
	 * @return the button
	 */
	public DialogButton setText(IModel<String> text)
	{
		options.putLiteral("text", text);
		return this;
	}

	/**
	 * @return the text value option
	 */
	public String getText()
	{
		return options.getLiteral("text");
	}

	/**
	 * Whether to show any text - when set to false (display no text), icons (see icons
	 * option) must be enabled, otherwise it'll be ignored.
	 * 
	 * @param showText
	 * @return the button
	 */
	public DialogButton setShowText(boolean showText)
	{
		options.put("showText", showText);
		return this;
	}

	/**
	 * @return the text option value
	 */
	public boolean isShowText()
	{
		if (options.containsKey("showText"))
		{
			return options.getBoolean("showText");
		}
		return true;
	}

	/**
	 * Icons to display, with or without text (see text option). The primary icon is
	 * displayed on the left of the label text, the secondary on the right. Value for the
	 * primary and secondary properties must be a classname (String), eg. "ui-icon-gear".
	 * For using only a primary icon: icons: {primary:'ui-icon-locked'}. For using both
	 * primary and secondary icon: icons:
	 * {primary:'ui-icon-gear',secondary:'ui-icon-triangle-1-s'}
	 * 
	 * @param icons
	 * @return the button
	 */
	public DialogButton setIcons(ButtonIcon icons)
	{
		options.put("icons", icons);
		return this;
	}

	/**
	 * * Icons to display, with or without text (see text option). The primary icon is
	 * displayed on the left of the label text, the secondary on the right. Value for the
	 * primary and secondary properties must be a classname (String), eg. "ui-icon-gear".
	 * For using only a primary icon: icons: {primary:'ui-icon-locked'}. For using both
	 * primary and secondary icon: icons:
	 * {primary:'ui-icon-gear',secondary:'ui-icon-triangle-1-s'}
	 * 
	 * @param primary
	 *            The primary icon (should be non-null)
	 * @param secondary
	 *            The secondary icon (might be null).
	 * @return the button
	 */
	public DialogButton setIcons(UiIcon primary, UiIcon secondary)
	{
		options.put("icons", new ButtonIcon(primary, secondary));
		return this;
	}

	/**
	 * @return the icons value option
	 */
	public ButtonIcon getIcons()
	{
		return (ButtonIcon) options.getComplexOption("icons");
	}

	/**
	 * Method setting the click option of the button as a JsScope.
	 * 
	 * @param click
	 * @return the button
	 */
	public DialogButton setClick(JsScope click)
	{
		options.put("click", click);
		return this;
	}

	/**
	 * @return the click value option
	 */
	public JsScope getClick()
	{
		return options.getJsScope("click");
	}
	
	/**
	 * Method setting the click option of the button as a callback (AJAX).
	 * 
	 * @param callback
	 * @return the button
	 */
	public DialogButton setCallback(IComplexOption callback) {
		options.put("click", callback);
		return this;
	}

	/**
	 * @return the callback property
	 */
	public IComplexOption getCallback() {
		return callback;
	}
}
