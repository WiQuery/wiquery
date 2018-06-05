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
package org.wicketstuff.wiquery.ui.themes;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.wiquery.core.javascript.JsQuery;
import org.wicketstuff.wiquery.core.javascript.JsStatement;

/**
 * $Id$
 * <p>
 * Helper to decorate your Wicket component with the theme of the jQuery UI framework
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public abstract class ThemeUiHelper
{
	/**
	 * Method to display into your component an error text
	 * 
	 * @param component
	 *            Wicket component
	 */
	public static void errorText(Component component)
	{
		component.add(AttributeModifier.append("class", "ui-state-error ui-corner-all"));
	}

	/**
	 * Method to decorate your button
	 * 
	 * @param component
	 *            Wicket component
	 */
	public static void buttonRounded(Component component)
	{
		component.add(AttributeModifier.append("class", "ui-state-default ui-corner-all"));
	}

	/**
	 * Method to decorate your button
	 * 
	 * @param component
	 *            Wicket component
	 */
	public static void buttonRoundedFocused(Component component)
	{
		component.add(
			AttributeModifier.append("class", "ui-state-default ui-corner-all ui-state-focus"));
	}

	/**
	 * Method to decorate your container (div, span ...)
	 * 
	 * @param component
	 *            Wicket component
	 */
	public static void componentRounded(Component component)
	{
		component
			.add(AttributeModifier.append("class", "ui-widget ui-widget-content ui-corner-all"));
	}

	/**
	 * Method to display into your component a highlighted text
	 * 
	 * @param component
	 *            Wicket component
	 */
	public static void highlightedText(Component component)
	{
		component.add(AttributeModifier.append("class", "ui-state-highlight ui-corner-all"));
	}

	/**
	 * Method to get the {@link JsStatement} to insert the hover style on your {@link Component}
	 * 
	 * @param component
	 *            Wicket component
	 * @return the JsStatement
	 */
	public static JsStatement hover(Component component)
	{
		component.setOutputMarkupId(true);
		return new JsQuery(component).$().chain("hover",
			"function() { $(this).addClass('ui-state-hover'); }",
			"function() { $(this).removeClass('ui-state-hover'); }");
	}

	/**
	 * Method to insert the the hover style into the Ajax transaction
	 * 
	 * @param component
	 *            Wicket component
	 * @param ajaxRequestTarget
	 *            Ajax transaction
	 */
	public static void hover(Component component, AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(hover(component).render().toString());
	}

	/**
	 * Method to display your composant as an icon
	 * 
	 * @param component
	 *            Wicket component
	 * @param icon
	 *            Icon to display
	 */
	public static void iconComponent(Component component, UiIcon icon)
	{
		component.add(AttributeModifier.append("class", "ui-icon " + icon.getCssClass()));
	}

	/**
	 * Method to display a component as an overlay
	 * 
	 * @param component
	 *            Wicket component
	 */
	public static void overlayComponent(Component component)
	{
		component.add(AttributeModifier.append("class",
			"ui-overlay ui-widget-overlay ui-widget-shadow ui-corner-all"));
	}

	/**
	 * Method to display a component as a title
	 * 
	 * @param component
	 *            Wicket component
	 */
	public static void titleComponent(Component component)
	{
		component.add(AttributeModifier.append("class",
			"ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"));
	}

	/**
	 * Styles the given {@link Component} as a jQuery UI header.
	 */
	public static void headerComponent(Component component)
	{
		component.add(AttributeModifier.append("class", "ui-widget-header"));
	}

	/**
	 * Styles the given {@link Component} as a jQuery UI shadow.
	 */
	public static void shadowComponent(Component component)
	{
		component.add(AttributeModifier.append("class", "ui-widget-shadow"));
	}
}
