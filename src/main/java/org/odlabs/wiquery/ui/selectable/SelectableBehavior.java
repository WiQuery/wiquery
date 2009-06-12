/*
 * Copyright (c) 2008 Objet Direct
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
package org.odlabs.wiquery.ui.selectable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * 	Wicket behabior to use the JQuery UI Selectable behavior
 * </p>
 * 
 *  * 
 * Example : 
 * Java code:
 * <code>
 * 	    List<String> values = Arrays.asList(
	    		"Value 1", 
	    		"Value 2",
	    		"Value 3",
	    		"Value 4",
	    		"Value 5");
	    
	    ListView<String> listView = new ListView<String>("listView", values) {
			@Override
			protected void populateItem(ListItem<String> item) {
				item.add(new Label("item", item.getModel()));
			}
	    };
	    
	    WebMarkupContainer selectableWicket = new WebMarkupContainer("selectableWicket");
	    selectableWicket.add(new SelectableBehavior());
	    selectableWicket.add(listView);
	    add(selectableWicket);
 * </code>
 * 
 * HTML code:
 * <code>
 *		<ol wicket:id="selectableWicket">
			<li wicket:id="listView"><span wicket:id="item"></span></li>
		</ol>
 * </code>
 * 
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class SelectableBehavior extends WiQueryAbstractBehavior {
	/**
	 * Enumeration for the tolerance option
	 * @author Julien Roche
	 *
	 */
	public enum ToleranceEnum {
		TOUCH,
		FIT;
	}
	
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 1L;

	//Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public SelectableBehavior() {
		super();
		 options = new Options();
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager
				.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager
				.addJavaScriptResource(new SelectableJavaScriptResourceReference());
	}

	/**
	 * @return the cancel option value
	 */
	public String getCancel() {
		return this.options.getLiteral("cancel");
	}

	/**
	 * @return the delay option value
	 */
	public int getDelay() {
		return this.options.getInt("delay");
	}

	/**
	 * @return the distance option value
	 */
	public int getDistance() {
		return this.options.getInt("distance");
	}

	/**
	 * @return the cancel option value
	 */
	public String getFilter() {
		return this.options.getLiteral("filter");
	}

	/** Method retrieving the options of the behavior
	 * @return the options
	 */
	public Options getOptions() {
		return options;
	}

	/**
	 * @return the tolerance option enum
	 */
	public ToleranceEnum getTolerance() {
		return ToleranceEnum.valueOf(this.options.getLiteral("tolerance").toUpperCase());
	}

	/**
	 * @return the autoRefresh option enum
	 */
	public boolean isAutoRefresh() {
		return this.options.getBoolean("autoRefresh");
	}

	/** This determines whether to refresh (recalculate) the position and size 
	 * of each selected at the beginning of each select operation. If you have 
	 * many many items, you may want to set this to false and call the 
	 * refresh method manually.
	 * @param autoRefresh
	 */
	public void setAutoRefresh(boolean autoRefresh) {
		this.options.put("autoRefresh", autoRefresh);
	}

	/** Set's the prevent selecting if you start on elements matching the selector
	 * @param cancel Selector (default : ':input,option')
	 */
	public void setCancel(String cancel) {
		this.options.putLiteral("cancel", cancel);
	}

	/** Set's the delay (in milliseconds) to define when the selecting should start
	 * @param delay
	 */
	public void setDelay(int delay) {
		this.options.put("delay", delay);
	}

	/** Set's the tolerance in pixels
	 * @param distance
	 */
	public void setDistance(int distance) {
		this.options.put("distance", distance);
	}

	/** Set's the matching child to be selectable
	 * @param filter Selector (default : '*')
	 */
	public void setFilter(String filter) {
		this.options.putLiteral("filter", filter);
	}

	/** Set's the options
	 * @param options
	 */
	public void setOptions(Options options) {
		this.options = options;
	}

	/** Set's the tolerance
	 * <ul>
	 * 	<li><b>fit</b>: draggable overlaps the droppable entirely</li>
	 * 	<li><b>touch</b>: draggable overlaps the droppable any amount</li>
	 * </ul>
	 * @param tolerance
	 */
	public void setTolerance(ToleranceEnum tolerance) {
		this.options.putLiteral("tolerance", tolerance.toString().toLowerCase());
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(this.getComponent()).$().chain("selectable",
				this.options.getJavaScriptOptions());
	}
	
	/*---- Events section ----*/
	/** Set's the selected event
	 * This event is triggered at the end of the select operation, on each 
	 * element added to the selection.
	 * @param selected Associated JsScopeUiEvent
	 */
	public void setSelectedEvent(JsScopeUiEvent selected) {
		this.options.put("selected", selected);
	}
	
	/** Set's the selecting event
	 * This event is triggered during the select operation, on each element 
	 * added to the selection.
	 * @param selecting Associated JsScopeUiEvent
	 */
	public void setSelectingEvent(JsScopeUiEvent selecting) {
		this.options.put("selecting", selecting);
	}
	
	/** Set's the start event
	 * This event is triggered at the beginning of the select operation.
	 * @param start Associated JsScopeUiEvent
	 */
	public void setStartEvent(JsScopeUiEvent start) {
		this.options.put("start", start);
	}
	
	/** Set's the stop event
	 * This event is triggered at the end of the select operation.
	 * @param stop Associated JsScopeUiEvent
	 */
	public void setStopEvent(JsScopeUiEvent stop) {
		this.options.put("stop", stop);
	}
	
	/** Set's the unselected event
	 * This event is triggered at the end of the select operation, on each element
	 * removed from the selection.
	 * @param unselected Associated JsScopeUiEvent
	 */
	public void setUnselectedEvent(JsScopeUiEvent unselected) {
		this.options.put("unselected", unselected);
	}
	
	/** Set's the unselecting event
	 * This event is triggered during the select operation, on each element 
	 * removed from the selection.
	 * @param unselecting Associated JsScopeUiEvent
	 */
	public void setUnselectingEvent(JsScopeUiEvent unselecting) {
		this.options.put("unselecting", unselecting);
	}
	
	/*---- Methods section ----*/
	/**Method to destroy
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(getComponent()).$().chain("selectable", "'destroy'");
	}

	/**Method to destroy within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(getComponent()).$().chain("selectable", "'disable'");
	}

	/**Method to disable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(getComponent()).$().chain("selectable", "'enable'");
	}

	/**Method to enable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method to refresh
	 * @return the associated JsStatement
	 */
	public JsStatement refresh() {
		return new JsQuery(getComponent()).$().chain("selectable", "'refresh'");
	}

	/**Method to refresh within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.refresh().render().toString());
	}
}
