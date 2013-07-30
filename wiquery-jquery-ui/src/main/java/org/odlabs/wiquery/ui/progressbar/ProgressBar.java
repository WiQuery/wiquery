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
package org.odlabs.wiquery.ui.progressbar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.options.UiOptionsRenderer;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates a progressBar UI component from this {@link WebMarkupContainer}'s HTML markup.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public class ProgressBar extends WebMarkupContainer
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 8268721447610956664L;

	// Properties
	private Options options;

	/**
	 * Builds a new progress bar.
	 */
	public ProgressBar(String id)
	{
		super(id);
		this.options = new Options(this);
		this.options.setRenderer(new UiOptionsRenderer("progressbar", this));
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
		response.render(JavaScriptHeaderItem.forReference(ProgressBarJavaScriptResourceReference
			.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
	}

	public JsStatement statement()
	{
		JsStatement componentStatement = new JsQuery(this).$().chain("progressbar");
		JsStatement wholeStatement = new JsStatement();
		wholeStatement.append(componentStatement.render());
		wholeStatement.append(options.getJavaScriptOptions());
		return wholeStatement;
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
	 * Disables (true) or enables (false) the progressBar. Can be set when initialising
	 * (first creating) the progressBar.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public ProgressBar setDisabled(boolean disabled)
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
	 * Sets the progressBar as determinate/indeterminate
	 * 
	 * @param determinate
	 * @return instance of the current component
	 */
	public ProgressBar setValue(boolean determinate)
	{
		this.options.put("value", determinate);
		return this;
	}

	/**
	 * Sets the current value of the progressBar
	 * 
	 * @param value
	 * @return instance of the current component
	 */
	public ProgressBar setValue(int value)
	{
		this.options.put("value", value);
		return this;
	}

	/**
	 * @return the current value of the progressBar
	 */
	public int getValue()
	{
		Integer value = this.options.getInt("value");
		if (value != null)
		{
			return value;
		}
		
		return 0;
	}
	
	/**
	 * Sets the maximum value of the progressBar
	 * 
	 * @param max
	 * @return instance of the current component
	 */
	public ProgressBar setMax(int max)
	{
		this.options.put("max", max);
		return this;
	}

	/**
	 * @return the maximum value of the progressBar
	 */
	public int getMax()
	{
		Integer value = this.options.getInt("max");
		if (value != null)
		{
			return value;
		}
		
		return 100;
	}

	/*---- Events section ---*/

	/**
	 * Set's the callback when the value of the progressBar changes.
	 * 
	 * @param change
	 * @return instance of the current component
	 */
	public ProgressBar setChangeEvent(JsScopeUiEvent change)
	{
		this.options.put("change", change);
		return this;
	}
	
	/**
	 * Set's the callback when the value of the progressBar reaches the maximum value.
	 * 
	 * @param complete
	 * @return instance of the current component
	 */
	public ProgressBar setCompleteEvent(JsScopeUiEvent complete)
	{
		this.options.put("complete", complete);
		return this;
	}

	/*---- Methods section ---*/

	/**
	 * Method to destroy the progressBar This will return the element back to its pre-init
	 * state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(this).$().chain("progressbar", "'destroy'");
	}

	/**
	 * Method to destroy the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method to disable the progressBar
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return new JsQuery(this).$().chain("progressbar", "'disable'");
	}

	/**
	 * Method to disable the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
	}

	/**
	 * Method to enable the progressBar
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return new JsQuery(this).$().chain("progressbar", "'enable'");
	}

	/**
	 * Method to enable the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}

	/**
	 * Method to get the current value of the progressBar
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement value()
	{
		return new JsQuery(this).$().chain("progressbar", "'value'");
	}

	/**
	 * Method to set the current value of the progressBar
	 * 
	 * @param value
	 * @return the associated JsStatement
	 */
	public JsStatement value(int value)
	{
		return new JsQuery(this).$().chain("progressbar", "'value'", Integer.toString(value));
	}

	/**
	 * Method to set the current value of the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 * @param value
	 */
	public void value(AjaxRequestTarget ajaxRequestTarget, int value)
	{
		ajaxRequestTarget.appendJavaScript(this.value(value).render().toString());
	}
	
	/**
	 * Method to returns the .ui-progressbar element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(this).$().chain("progressbar", "'widget'");
	}

	/**
	 * Method to returns the .ui-progressbar element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}

	/*---- wiQuery Methods section ---*/

	/**
	 * Method to increment the value of the progressBar
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement increment()
	{
		return increment(1);
	}

	/**
	 * Method to increment the value of the progressBar
	 * 
	 * @param increment
	 *            The increment to add to the current value
	 * @return the associated JsStatement
	 */
	public JsStatement increment(int increment)
	{
		JsStatement statement = new JsStatement();
		statement.append(new JsQuery(this)
			.$()
			.chain(
				"progressbar",
				"'value'",
				new JsQuery(this).$().chain("progressbar", "'value'").render(false) + " + "
					+ increment).render());

		return statement;
	}

	/**
	 * Method to increment the value of the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void increment(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.increment().render().toString());
	}

	/**
	 * Method to increment the value of the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 * @param increment
	 *            The increment to add to the current value
	 */
	public void increment(AjaxRequestTarget ajaxRequestTarget, int increment)
	{
		ajaxRequestTarget.appendJavaScript(this.increment(increment).render().toString());
	}

	/**
	 * Method to decrement the value of the progressBar
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement decrement()
	{
		return decrement(1);
	}

	/**
	 * Method to decrement the value of the progressBar
	 * 
	 * @param decrement
	 *            The decrement to add to the current value
	 * @return the associated JsStatement
	 */
	public JsStatement decrement(int decrement)
	{
		JsStatement statement = new JsStatement();
		statement.append(new JsQuery(this)
			.$()
			.chain(
				"progressbar",
				"'value'",
				new JsQuery(this).$().chain("progressbar", "'value'").render(false) + " - "
					+ decrement).render());

		return statement;
	}

	/**
	 * Method to decrement the value of the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void decrement(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.decrement().render().toString());
	}

	/**
	 * Method to decrement the value of the progressBar within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 * @param decrement
	 *            The decrement to add to the current value
	 */
	public void decrement(AjaxRequestTarget ajaxRequestTarget, int decrement)
	{
		ajaxRequestTarget.appendJavaScript(this.decrement(decrement).render().toString());
	}
	
	public JsStatement update()
	{
		JsStatement wholeStatement = new JsStatement();
		wholeStatement.append(options.getJavaScriptOptions());
		return wholeStatement;
	}
}
