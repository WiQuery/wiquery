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
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.position.PositionJavascriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates an autocomplete UI component
 * </p>
 * @author Julien Roche
 * @param <T> The model object type
 * @since 1.1
 */
@WiQueryUIPlugin
public class Autocomplete<T> extends TextField<T> implements IWiQueryPlugin {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = -28740287216028869L;
	
	// Properties
	private Options options;

	/**
	 * Constructor
	 * @param id Wicket identifiant
	 */
	public Autocomplete(String id) {
		super(id);
		options = new Options();
	}

	/**
	 * Constructor
	 * @param id Wicket identifiant
	 * @param model Model
	 */
	public Autocomplete(String id, IModel<T> model) {
		super(id, model);
		options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(PositionJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(AutocompleteJavascriptResourceReference.get());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("autocomplete", options.getJavaScriptOptions());
	}
	
	/*---- Options section ---*/
	/** The delay in milliseconds the autocomplete waits after a keystroke to 
	 * activate itself. A zero-delay makes sense for local data (more responsive), 
	 * but can produce a lot of load for remote data, while being less responsive.
	 * @param delay
	 * @return instance of the current component
	 */
	public Autocomplete<T> setDelay(int delay) {
		this.options.put("delay", delay);
		return this;
	}
	
	/**
	 * @return the delay option value
	 */
	public int getDelay() {
		if(this.options.containsKey("delay")){
			return this.options.getInt("delay");
		}
		
		return 300;
	}
	
	/** The minimum number of characters a user has to type before the 
	 * autocomplete activates. Zero is useful for local data with just a few items. 
	 * Should be increased when there are a lot of items, where a single character 
	 * would match a few thousand items.
	 * @param minLength
	 * @return instance of the current component
	 */
	public Autocomplete<T> setMinLength(int minLength) {
		this.options.put("minLength", minLength);
		return this;
	}
	
	/**
	 * @return the delay option value
	 */
	public int getMinLength() {
		if(this.options.containsKey("minLength")){
			return this.options.getInt("minLength");
		}
		
		return 1;
	}
	
	/**
	 * Defines the data to use, must be specified. There are three variations:
	 * <ul>
     * 	<li>an Array with local data</li>
     * 	<li>a String, specifying a URL</li>
     * 	<li>a Callback</li>
     * </ul>
     * 
     * The local data can be a simple Array of Strings, or it contains Objects 
     * for each item in the array, with a label or value property or both. The 
     * label property is displayed in the suggestion menu, the value will be 
     * inserted into the input element after the user selected something from 
     * the menu. If just one is specified, it will be used for both, eg. if you 
     * provide only value-properties, they will also be used as the label.
	 *
	 * When a String is used, the Autocomplete plugin expects that string to 
	 * point to a resource to return JSON data. It can be on the same host or on 
	 * a different one (must provide JSONP). The data itself can be in the same 
	 * format as the local data described above.
	 * 
	 * The third variation, the callback, provides the most flexibility, and can 
	 * be used to connect any datasource to the Autocomplete. The callback gets 
	 * two arguments:
	 * <ul>
     * 	<li>A request object, with a single property called "term", which refers to the 
     * value currently in the text input. For example, when the user entered "new yo" 
     * in a city autocomplete, term equals "new yo".</li>
     * 	<li>A response callback, which expects a single argument to contain the data 
     * to suggest to the user. This data should be filtered based on the provided 
     * term, and can be in any of the formats described above for simple local data 
     * (String-Array or Object-Array with label/value/both properties).</li>
     * </ul>
     * 
	 * @param source
	 * @return instance of the current component
	 */
	public Autocomplete<T> setSource(AutocompleteSource source) {
		this.options.put("source", source);
		return this;
	}
	
	/**
	 * @return the source option value
	 */
	public AutocompleteSource getSource() {
		IComplexOption source = options.getComplexOption("source");
		
		if(source instanceof AutocompleteSource){
			return (AutocompleteSource) source;
		}
		
		return null;
	}
	
	/**Disables (true) or enables (false) the autocomplete. Can be set when 
	 * initialising (first creating) the autcomplete.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Autocomplete<T> setDisabled(boolean disabled) {
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
	
	/*---- Events section ---*/
	
	/**
	 * After an item was selected; ui.item refers to the selected item. Always 
	 * triggered after the close event.
	 * @param change
	 * @return instance of the current component
	 */
	public Autocomplete<T> setChangeEvent(JsScopeUiEvent change) {
		this.options.put("change", change);
		return this;
	}
	
	/**
	 * When the list is hidden - doesn't have to occur together with a change.
	 * @param close
	 * @return instance of the current component
	 */
	public Autocomplete<T> setCloseEvent(JsScopeUiEvent close) {
		this.options.put("close", close);
		return this;
	}
	
	/**
	 * Before focus is moved to an item (not selecting), ui.item refers to the 
	 * focused item. The default action of focus is to replace the text field's 
	 * value with the value of the focused item. Cancelling this event prevents 
	 * the value from being updated, but does not prevent the menu item from being focused.
	 * @param focus
	 * @return instance of the current component
	 */
	public Autocomplete<T> setFocusEvent(JsScopeUiEvent focus) {
		this.options.put("focus", focus);
		return this;
	}
	
	/**
	 * After a request with the data ready, before it is actually displayed; also 
	 * indicates the suggestion menu will be opened.
	 * @param open
	 * @return instance of the current component
	 */
	public Autocomplete<T> setOpenEvent(JsScopeUiEvent open) {
		this.options.put("open", open);
		return this;
	}
	
	/**
	 * Before a request (source-option) is started, after minLength and delay are met. 
	 * Can be cancelled (return false), then no request will be started and no items suggested.
	 * @param search
	 * @return instance of the current component
	 */
	public Autocomplete<T> setSearchEvent(JsScopeUiEvent search) {
		this.options.put("search", search);
		return this;
	}
	
	/**
	 * Triggered when an item is selected from the menu; ui.item refers to the 
	 * selected item. The default action of select is to replace the text field's 
	 * value with the value of the selected item. Cancelling this event prevents 
	 * the value from being updated, but does not prevent the menu from closing.
	 * @param select
	 * @return instance of the current component
	 */
	public Autocomplete<T> setSelectEvent(JsScopeUiEvent select) {
		this.options.put("select", select);
		return this;
	}
	
	/*---- Methods section ---*/
	/**Method to close the autocomplete
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement close() {
		return new JsQuery(this).$().chain("autocomplete", "'close'");
	}

	/**Method to close the autocomplete within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void close(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.close().render().toString());
	}
	
	/**Method to destroy the autocomplete
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("autocomplete", "'destroy'");
	}

	/**Method to destroy the autocomplete within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable the autocomplete
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(this).$().chain("autocomplete", "'disable'");
	}

	/**Method to disable the button within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable the autocomplete
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(this).$().chain("autocomplete", "'enable'");
	}

	/**Method to enable the autocomplete within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method to search the autocomplete
	 * @return the associated JsStatement
	 */
	public JsStatement search() {
		return new JsQuery(this).$().chain("autocomplete", "'search'");
	}

	/**Method to search the autocomplete within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void search(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.search().render().toString());
	}
	
	/**Method to search the autocomplete
	 * @param value String
	 * @return the associated JsStatement
	 */
	public JsStatement search(String value) {
		return new JsQuery(this).$().chain("autocomplete", "'search'", JsUtils.quotes(value));
	}

	/**Method to search the autocomplete within the ajax request
	 * @param ajaxRequestTarget
	 * @param value String
	 */
	public void search(AjaxRequestTarget ajaxRequestTarget, String value) {
		ajaxRequestTarget.appendJavascript(this.search(value).render().toString());
	}
	
	/**Method to returns the .ui-autocomplete  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(this).$().chain("autocomplete", "'widget'");
	}

	/**Method to returns the .ui-autocomplete  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.widget().render().toString());
	}
}
