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
package org.odlabs.wiquery.ui.autocomplete;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.position.PositionAlignmentOptions;
import org.odlabs.wiquery.ui.position.PositionCollision;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.ui.position.PositionRelation;

/**
 * $Id$
 * <p>
 * Creates an autocomplete UI component
 * </p>
 * 
 * @author Julien Roche
 * @param <T>
 *            The model object type
 * @since 1.1
 */
public class Autocomplete<T> extends TextField<T>
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -28740287216028869L;

	// Properties
	private Options options;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifier
	 */
	public Autocomplete(String id)
	{
		super(id);
		options = new Options(this);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Wicket identifier
	 * @param model
	 *            Model
	 */
	public Autocomplete(String id, IModel<T> model)
	{
		super(id, model);
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
		response.render(JavaScriptHeaderItem.forReference(AutocompleteJavaScriptResourceReference
			.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
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

	public JsStatement statement()
	{
		return new JsQuery(this).$().chain("autocomplete", options.getJavaScriptOptions());
	}

	/*---- Options section ---*/
	/**
	 * The element passed to or selected by the appendTo option will be used as the
	 * container for the suggested values
	 * 
	 * @param appendTo
	 * @return instance of the current component
	 */
	public Autocomplete<T> setAppendTo(String appendTo)
	{
		this.options.putLiteral("appendTo", appendTo);
		return this;
	}

	/**
	 * @return the appendTo option value
	 */
	public String getAppendTo()
	{
		String appendTo = this.options.getLiteral("appendTo");
		return appendTo == null ? "body" : appendTo;
	}

	/**
	 * The delay in milliseconds the autocomplete waits after a keystroke to activate
	 * itself. A zero-delay makes sense for local data (more responsive), but can produce
	 * a lot of load for remote data, while being less responsive.
	 * 
	 * @param delay
	 * @return instance of the current component
	 */
	public Autocomplete<T> setDelay(int delay)
	{
		this.options.put("delay", delay);
		return this;
	}

	/**
	 * @return the delay option value
	 */
	public int getDelay()
	{
		if (this.options.containsKey("delay"))
		{
			return this.options.getInt("delay");
		}

		return 300;
	}

	/**
	 * The minimum number of characters a user has to type before the autocomplete
	 * activates. Zero is useful for local data with just a few items. Should be increased
	 * when there are a lot of items, where a single character would match a few thousand
	 * items.
	 * 
	 * @param minLength
	 * @return instance of the current component
	 */
	public Autocomplete<T> setMinLength(int minLength)
	{
		this.options.put("minLength", minLength);
		return this;
	}

	/**
	 * @return the delay option value
	 */
	public int getMinLength()
	{
		if (this.options.containsKey("minLength"))
		{
			return this.options.getInt("minLength");
		}

		return 1;
	}

	/**
	 * Position of the component of the suggested values with the input field
	 * 
	 * @param position
	 * @return instance of the current component
	 */
	public Autocomplete<T> setPosition(PositionOptions position)
	{
		this.options.put("position", position);
		return this;
	}

	/**
	 * @return the position option value
	 */
	public PositionOptions getPosition()
	{
		Object position = this.options.getComplexOption("position");

		if (position != null && position instanceof PositionOptions)
		{
			return (PositionOptions) position;
		}

		PositionOptions pos = new PositionOptions()
				.setMy(new PositionAlignmentOptions(PositionRelation.LEFT, PositionRelation.TOP))
				.setAt(new PositionAlignmentOptions(PositionRelation.LEFT, PositionRelation.BOTTOM))
				.setCollision(PositionCollision.NONE);
		return pos;
	}

	/**
	 * Defines the data to use, must be specified. There are three variations:
	 * <ul>
	 * <li>an Array with local data</li>
	 * <li>a String, specifying a URL</li>
	 * <li>a Callback</li>
	 * </ul>
	 * 
	 * The local data can be a simple Array of Strings, or it contains Objects for each
	 * item in the array, with a label or value property or both. The label property is
	 * displayed in the suggestion menu, the value will be inserted into the input element
	 * after the user selected something from the menu. If just one is specified, it will
	 * be used for both, eg. if you provide only value-properties, they will also be used
	 * as the label.
	 * 
	 * When a String is used, the Autocomplete plugin expects that string to point to a
	 * resource to return JSON data. It can be on the same host or on a different one
	 * (must provide JSONP). The data itself can be in the same format as the local data
	 * described above.
	 * 
	 * The third variation, the callback, provides the most flexibility, and can be used
	 * to connect any datasource to the Autocomplete. The callback gets two arguments:
	 * <ul>
	 * <li>A request object, with a single property called "term", which refers to the
	 * value currently in the text input. For example, when the user entered "new yo" in a
	 * city autocomplete, term equals "new yo".</li>
	 * <li>A response callback, which expects a single argument to contain the data to
	 * suggest to the user. This data should be filtered based on the provided term, and
	 * can be in any of the formats described above for simple local data (String-Array or
	 * Object-Array with label/value/both properties).</li>
	 * </ul>
	 * 
	 * @param source
	 * @return instance of the current component
	 */
	public Autocomplete<T> setSource(AutocompleteSource source)
	{
		this.options.put("source", source);
		return this;
	}

	/**
	 * @return the source option value
	 */
	public AutocompleteSource getSource()
	{
		IComplexOption source = options.getComplexOption("source");

		if (source instanceof AutocompleteSource)
		{
			return (AutocompleteSource) source;
		}

		return null;
	}

	/**
	 * Disables (true) or enables (false) the autocomplete. Can be set when initialising
	 * (first creating) the autcomplete.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Autocomplete<T> setDisabled(boolean disabled)
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
	 * If set to true the first item will automatically be focused when the menu is shown.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Autocomplete<T> setAutoFocus(boolean autoFocus)
	{
		this.options.put("autoFocus", autoFocus);
		return this;
	}

	/**
	 * @return the autoFocus option
	 */
	public boolean isAutoFocus()
	{
		if (this.options.containsKey("autoFocus"))
		{
			return this.options.getBoolean("autoFocus");
		}

		return false;
	}

	/*---- Events section ---*/

	/**
	 * After an item was selected; ui.item refers to the selected item. Always triggered
	 * after the close event.
	 * 
	 * @param change
	 * @return instance of the current component
	 */
	public Autocomplete<T> setChangeEvent(JsScopeUiEvent change)
	{
		this.options.put("change", change);
		return this;
	}

	/**
	 * When the list is hidden - doesn't have to occur together with a change.
	 * 
	 * @param close
	 * @return instance of the current component
	 */
	public Autocomplete<T> setCloseEvent(JsScopeUiEvent close)
	{
		this.options.put("close", close);
		return this;
	}

	/**
	 * Before focus is moved to an item (not selecting), ui.item refers to the focused
	 * item. The default action of focus is to replace the text field's value with the
	 * value of the focused item. Cancelling this event prevents the value from being
	 * updated, but does not prevent the menu item from being focused.
	 * 
	 * @param focus
	 * @return instance of the current component
	 */
	public Autocomplete<T> setFocusEvent(JsScopeUiEvent focus)
	{
		this.options.put("focus", focus);
		return this;
	}

	/**
	 * After a request with the data ready, before it is actually displayed; also
	 * indicates the suggestion menu will be opened.
	 * 
	 * @param open
	 * @return instance of the current component
	 */
	public Autocomplete<T> setOpenEvent(JsScopeUiEvent open)
	{
		this.options.put("open", open);
		return this;
	}
	
	/**
	 * After a search completes, before the menu is shown.
	 * 
	 * @param response
	 * @return instance of the current component
	 */
	public Autocomplete<T> setResponseEvent(JsScopeUiEvent response)
	{
		this.options.put("response", response);
		return this;
	}

	/**
	 * Before a request (source-option) is started, after minLength and delay are met. Can
	 * be cancelled (return false), then no request will be started and no items
	 * suggested.
	 * 
	 * @param search
	 * @return instance of the current component
	 */
	public Autocomplete<T> setSearchEvent(JsScopeUiEvent search)
	{
		this.options.put("search", search);
		return this;
	}

	/**
	 * Triggered when an item is selected from the menu; ui.item refers to the selected
	 * item. The default action of select is to replace the text field's value with the
	 * value of the selected item. Cancelling this event prevents the value from being
	 * updated, but does not prevent the menu from closing.
	 * 
	 * @param select
	 * @return instance of the current component
	 */
	public Autocomplete<T> setSelectEvent(JsScopeUiEvent select)
	{
		this.options.put("select", select);
		return this;
	}

	/*---- Methods section ---*/
	/**
	 * Method to close the autocomplete This will return the element back to its pre-init
	 * state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement close()
	{
		return new JsQuery(this).$().chain("autocomplete", "'close'");
	}

	/**
	 * Method to close the autocomplete within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void close(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.close().render().toString());
	}

	/**
	 * Method to destroy the autocomplete This will return the element back to its
	 * pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(this).$().chain("autocomplete", "'destroy'");
	}

	/**
	 * Method to destroy the autocomplete within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method to disable the autocomplete
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return new JsQuery(this).$().chain("autocomplete", "'disable'");
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
	 * Method to enable the autocomplete
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return new JsQuery(this).$().chain("autocomplete", "'enable'");
	}

	/**
	 * Method to enable the autocomplete within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}

	/**
	 * Method to search the autocomplete
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement search()
	{
		return new JsQuery(this).$().chain("autocomplete", "'search'");
	}

	/**
	 * Method to search the autocomplete within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void search(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.search().render().toString());
	}

	/**
	 * Method to search the autocomplete
	 * 
	 * @param value
	 *            String
	 * @return the associated JsStatement
	 */
	public JsStatement search(String value)
	{
		return new JsQuery(this).$().chain("autocomplete", "'search'", JsUtils.quotes(value));
	}

	/**
	 * Method to search the autocomplete within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 * @param value
	 *            String
	 */
	public void search(AjaxRequestTarget ajaxRequestTarget, String value)
	{
		ajaxRequestTarget.appendJavaScript(this.search(value).render().toString());
	}

	/**
	 * Method to returns the .ui-autocomplete element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(this).$().chain("autocomplete", "'widget'");
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
