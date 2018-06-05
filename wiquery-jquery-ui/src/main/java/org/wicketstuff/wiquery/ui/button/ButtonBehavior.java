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
package org.wicketstuff.wiquery.ui.button;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.wicketstuff.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.wicketstuff.wiquery.core.javascript.JsQuery;
import org.wicketstuff.wiquery.core.javascript.JsStatement;
import org.wicketstuff.wiquery.core.options.IComplexOption;
import org.wicketstuff.wiquery.ui.JQueryUIJavaScriptResourceReference;
import org.wicketstuff.wiquery.ui.core.JsScopeUiEvent;
import org.wicketstuff.wiquery.ui.options.ClassesOption;
import org.wicketstuff.wiquery.ui.themes.UiIcon;

/**
 * $Id$
 * <p>
 * Creates a jQuery UI button behavior to decorate a link or a button HTML markup.
 * </p>
 * 
 * <p>
 * The click event is not a part of the jQuery UI framework
 * </p>
 * 
 * @author Julien Roche
 * @author Ernesto Reinaldo
 * @since 1.1
 */
public class ButtonBehavior extends WiQueryAbstractAjaxBehavior
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -4079980500720298690L;

	/**
	 * Default constructor
	 */
	public ButtonBehavior()
	{
		super();
	}

	@Override
	public void onBind()
	{
		super.onBind();
		options.setOwner(getComponent());
	}

	@Override
	public void detach(Component component)
	{
		super.detach(component);
		options.detach();
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		response
			.render(JavaScriptHeaderItem.forReference(JQueryUIJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(new JsQuery(getComponent()).$()
			.chain("button", options.getJavaScriptOptions())
			.render()));
	}

	@Override
	public void onComponentTag(ComponentTag tag)
	{
		String tagname = tag.getName();

		if (!tagname.equalsIgnoreCase("input") && !tagname.equalsIgnoreCase("button") &&
			!tagname.equalsIgnoreCase("submit") && !tagname.equalsIgnoreCase("reset") &&
			!tagname.equalsIgnoreCase("a"))
		{
			throw new WicketRuntimeException("Component " + getComponent().getId() +
				" must be applied to a tag of type 'input', 'button' or 'a', not " +
				tag.toUserDebugString());
		}

		super.onComponentTag(tag);
	}

	/*---- Options section ---*/

	public ClassesOption getClasses()
	{
		IComplexOption animate = this.options.getComplexOption("classes");
		if (animate instanceof ClassesOption)
		{
			return (ClassesOption)animate;
		}

		return new ClassesOption();
	}

	public ButtonBehavior setClasses(ClassesOption classes)
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
	public ButtonBehavior setDisabled(boolean disabled)
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
	public ButtonBehavior setShowLabel(boolean showLabel)
	{
		options.put("showLabel", showLabel);
		return this;
	}

	/**
	 * @return the icon value option
	 */
	public UiIcon getIcon()
	{
		return UiIcon.forCssClass(options.getLiteral("icon"));
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
	public ButtonBehavior setIcon(UiIcon icon)
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
	public ButtonBehavior setIconPosition(String iconPosition)
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
	public ButtonBehavior setLabel(String label)
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
	public ButtonBehavior setLabel(IModel<String> label)
	{
		options.putLiteral("label", label);
		return this;
	}

	/*---- Events section ---*/

	public ButtonBehavior setCreateEvent(JsScopeUiEvent create)
	{
		this.options.put("create", create);
		return this;
	}

	/*---- Methods section ---*/

	/**
	 * Method to destroy the button This will return the element back to its pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(getComponent()).$().chain("button", "'destroy'");
	}

	/**
	 * Method to destroy the button within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method to disable the button
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return new JsQuery(getComponent()).$().chain("button", "'disable'");
	}

	/**
	 * Method to disable the button within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
	}

	/**
	 * Method to enable the button
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return new JsQuery(getComponent()).$().chain("button", "'enable'");
	}

	/**
	 * Method to enable the button within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}

	/**
	 * Returns the {@link JsStatement} to refresh the button.
	 * 
	 * @return a non null {@link JsStatement}.
	 */
	public JsStatement refresh()
	{
		return new JsQuery(getComponent()).$().chain("button", "'refresh'");
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
	 * Method to returns the .ui-autocomplete element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(getComponent()).$().chain("button", "'widget'");
	}

	/**
	 * Method to returns the .ui-autocomplete element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}
}
