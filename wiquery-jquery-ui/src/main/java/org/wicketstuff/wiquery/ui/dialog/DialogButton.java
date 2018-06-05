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
package org.wicketstuff.wiquery.ui.dialog;

import org.apache.wicket.model.IModel;
import org.wicketstuff.wiquery.core.javascript.JsScope;
import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.core.options.IListItemOption;
import org.wicketstuff.wiquery.core.options.Options;
import org.wicketstuff.wiquery.ui.core.JsScopeUiEvent;
import org.wicketstuff.wiquery.ui.options.ClassesOption;
import org.wicketstuff.wiquery.ui.themes.UiIcon;

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
		setLabel(text);
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

	public ClassesOption getClasses()
	{
		IComplexOption animate = this.options.getComplexOption("classes");
		if (animate instanceof ClassesOption)
		{
			return (ClassesOption)animate;
		}

		return new ClassesOption();
	}

	public DialogButton setClasses(ClassesOption classes)
	{
		this.options.put("classes", classes);
		return this;
	}

	/**
	 * @return the disabled option
	 */
	public Boolean isDisabled()
	{
		if (this.options.containsKey("disabled"))
		{
			return this.options.getBoolean("disabled");
		}

		return null;
	}


	/**
	 * Disables (true) or enables (false) the button. Can be set when initialising (first creating)
	 * the button.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public DialogButton setDisabled(boolean disabled)
	{
		this.options.put("disabled", disabled);
		return this;
	}

	/**
	 * @return the showLabel option value
	 */
	public boolean isShowLabel()
	{
		if (options.containsKey("showLabel"))
		{
			return options.getBoolean("showLabel");
		}
		return true;
	}

	/**
	 * Whether to show any text - when set to false (display no text), icons (see icons option) must
	 * be enabled, otherwise it'll be ignored.
	 * 
	 * @param text
	 * @return the Button
	 */
	public DialogButton setShowLabel(boolean showLabel)
	{
		options.put("showLabel", showLabel);
		return this;
	}

	/**
	 * @return the icon value option
	 */
	public UiIcon getIcon()
	{
		return UiIcon.forCssClass(options.get("icon"));
	}

	/**
	 * Icon to display, with or without text (see showLabel option). By default, the icon is
	 * displayed on the left of the label text. The positioning can be controlled using the
	 * iconPosition option.
	 * 
	 * The value for this option must match an icon class name, e.g., "ui-icon-gear".
	 * 
	 * When using an input of type button, submit or reset, icons are not supported.
	 * 
	 * @param icon
	 * @return the button
	 */
	public DialogButton setIcon(UiIcon icon)
	{
		options.putLiteral("icon", icon.getCssClass());
		return this;
	}

	/**
	 * @return the iconPosition value option
	 */
	public String getIconPosition()
	{
		return options.get("iconPosition");
	}

	/**
	 * Where to display the icon: Valid values are "beginning", "end", "top" and "bottom". In a
	 * left-to-right (LTR) display, "beginning" refers to the left, in a right-to-left (RTL, e.g. in
	 * Hebrew or Arabic), it refers to the right.
	 * 
	 * @param icon
	 * @return the button
	 */
	public DialogButton setIconPosition(String iconPosition)
	{
		options.putLiteral("iconPosition", iconPosition);
		return this;
	}

	/**
	 * @return the label value option
	 */
	public String getLabel()
	{
		return options.getLiteral("label");
	}

	/**
	 * Text to show on the button. When not specified (null), the element's html content is used, or
	 * its value attribute when it's an input element of type submit or reset; or the html content
	 * of the associated label element if its an input of type radio or checkbox
	 * 
	 * @param label
	 * @return the button
	 */
	public DialogButton setLabel(String label)
	{
		options.putLiteral("label", label);
		return this;
	}

	/**
	 * Text to show on the button. When not specified (null), the element's html content is used, or
	 * its value attribute when it's an input element of type submit or reset; or the html content
	 * of the associated label element if its an input of type radio or checkbox
	 * 
	 * @param label
	 * @return the button
	 */
	public DialogButton setLabel(IModel<String> label)
	{
		options.putLiteral("label", label);
		return this;
	}

	/*---- Events section ---*/

	public DialogButton setCreateEvent(JsScopeUiEvent create)
	{
		this.options.put("create", create);
		return this;
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
	public DialogButton setCallback(IComplexOption callback)
	{
		options.put("click", callback);
		this.callback = callback;
		return this;
	}

	/**
	 * @return the callback property
	 */
	public IComplexOption getCallback()
	{
		return callback;
	}
}
