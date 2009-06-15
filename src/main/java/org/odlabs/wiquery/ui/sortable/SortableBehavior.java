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
package org.odlabs.wiquery.ui.sortable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * 	Wicket behabior to use the JQuery UI Sortable behavior
 * </p>
 * <p>
 * 	Missing functionalities
 * 	<ul>
 * 		<li>Method : serialize</li>
 * 	</ul>
 * </p>
 * 
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
	    
	    WebMarkupContainer sortableWicket = new WebMarkupContainer("sortableWicket");
	    sortableWicket.add(new SortableBehavior());
	    sortableWicket.add(listView);
	    add(sortableWicket);
 * </code>
 * 
 * HTML code:
 * <code>
 *		<ul wicket:id="sortableWicket">
			<li wicket:id="listView"><span wicket:id="item"></span></li>
		</ul>
 * </code>
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class SortableBehavior extends WiQueryAbstractBehavior {
	/**
	 * Enumeration for the axis option
	 * @author Julien Roche
	 *
	 */
	public enum AxisEnum {
		X,
		Y;
	}
	
	/**
	 * Enumeration for the cursorAt option
	 * @author Julien Roche
	 *
	 */
	public enum CursorAtEnum {
		TOP,
		TOP_LEFT,
		TOP_RIGHT,
		LEFT,
		RIGHT,
		BOTTOM,
		BOTTOM_LEFT,
		BOTTOM_RIGHT;
	}
	
	/**
	 * Enumeration for the tolerance option
	 * @author Julien Roche
	 *
	 */
	public enum ToleranceEnum {
		INTERSECT,
		POINTER;
	}
	
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 1L;

	//Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public SortableBehavior() {
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
				.addJavaScriptResource(new SortableJavaScriptResourceReference());
	}
	
	/**
	 * @return the appendTo option value
	 */
	public String getAppendTo() {
		return this.options.getLiteral("appendTo");
	}
	
	/**
	 * @return the axis option value
	 */
	public AxisEnum getAxis() {
		return AxisEnum.valueOf(this.options.getLiteral("axis").toUpperCase());
	}
	
	/**
	 * @return the cancel option value
	 */
	public String getCancel() {
		return this.options.getLiteral("cancel");
	}
	
	/**
	 * @return the connectWith option value
	 */
	public String getConnectWith() {
		return this.options.getLiteral("connectWith");
	}
	
	/**
	 * @return the containment option value
	 */
	public String getContainment() {
		return this.options.getLiteral("containment");
	}
	
	/**
	 * @return the cursor option value
	 */
	public String getCursor() {
		return this.options.getLiteral("cursor");
	}
	
	/**
	 * @return the cursorAt option value
	 */
	public CursorAtEnum getCursorAt() {
		return CursorAtEnum.valueOf(this.options.getLiteral("cursorAt").toUpperCase()
				.replace(' ', '_'));
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
	 * @return the dropOnEmpty option value
	 */
	public boolean getDropOnEmpty() {
		return this.options.getBoolean("dropOnEmpty");
	}
	
	/**
	 * @return the forceHelperSize option value
	 */
	public boolean getForceHelperSize() {
		return this.options.getBoolean("forceHelperSize");
	}
	
	/**
	 * @return the forcePlaceholderSize option value
	 */
	public boolean getForcePlaceholderSize() {
		return this.options.getBoolean("forcePlaceholderSize");
	}
	
	/**
	 * @return the grid option value
	 */
	public ICollectionItemOptions getGrid() {
		return this.options.getListItemOptions("grid");
	}
	
	/**
	 * @return the handle option value
	 */
	public String getHandle() {
		return this.options.getLiteral("handler");
	}
	
	/**
	 * @return the helper option value
	 */
	public String getHelper() {
		return this.options.getLiteral("helper");
	}
	
	/**
	 * @return the items option value
	 */
	public String getItems() {
		return this.options.getLiteral("items");
	}
	
	/**
	 * @return the opacity option value
	 */
	public float getOpacity() {
		return this.options.getFloat("opacity");
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}
	
	/**
	 * @return the placeHolder option value
	 */
	public String getPlaceHolder() {
		return this.options.getLiteral("placeHolder");
	}
	
	/**
	 * @return the revert option value
	 */
	public boolean getRevert() {
		return this.options.getBoolean("revert");
	}
	
	/**
	 * @return the scroll option value
	 */
	public boolean getScroll() {
		return this.options.getBoolean("scroll");
	}
	
	/**
	 * @return the scrollSensitivity option value
	 */
	public int getScrollSensitivity() {
		return this.options.getInt("scrollSensitivity");
	}
	
	/**
	 * @return the scrollSpeed option value
	 */
	public int getScrollSpeed() {
		return this.options.getInt("scrollSpeed");
	}
	
	/**
	 * @return the tolerance option value
	 */
	public ToleranceEnum getTolerance() {
		return ToleranceEnum.valueOf(this.options.getLiteral("tolerance").toUpperCase());
	}
	
	/**
	 * @return the zIndex option value
	 */
	public int getZIndex() {
		return this.options.getInt("zIndex");
	}
	
	/**Defines where the helper that moves with the mouse is being appended to 
	 * during the drag (for example, to resolve overlap/zIndex issues).
	 * @param appendTo
	 */
	public void setAppendTo(String appendTo) {
		this.options.putLiteral("appendTo", appendTo);
	}
	
	/**If defined, the items can be dragged only horizontally or vertically. 
	 * Possible values:'x', 'y'.
	 * @param axis
	 */
	public void setAxis(AxisEnum axis) {
		this.options.putLiteral("axis", axis.toString().toLowerCase());
	}
	
	/** Set's the prevent selecting if you start on elements matching the selector
	 * @param cancel Selector (default : ':input,option')
	 */
	public void setCancel(String cancel) {
		this.options.putLiteral("cancel", cancel);
	}
	
	/**Set a jQuery selector with items that also have sortables applied. 
	 * If used, the sortable is now connected to the other one-way, so you can 
	 * drag from this sortable to the other.
	 * @param connectWith Selector
	 */
	public void setConnectWith(String connectWith) {
		this.options.putLiteral("connectWith", connectWith);
	}
	
	/**Constrains dragging to within the bounds of the specified element - 
	 * can be a DOM element, 'parent', 'document', 'window', or a jQuery selector.
	 * @param containment
	 */
	public void setContainment(String containment) {
		this.options.putLiteral("containment", containment);
	}
	
	/**Set the cursor that is being shown while sorting
	 * @param cursor
	 */
	public void setCursor(String cursor) {
		this.options.putLiteral("cursor", cursor);
	}
	
	/**Moves the sorting element or helper so the cursor always appears to drag 
	 * from the same position. Coordinates can be given as a hash using a 
	 * combination of one or two keys: { top, left, right, bottom }
	 * @param cusorAt
	 */
	public void setCursorAt(CursorAtEnum cusorAt) {
		this.options.putLiteral("cusorAt", cusorAt.toString().toLowerCase()
				.replace('_', ' '));
	}
	
	/** Set's the delay (in milliseconds) to define when the sorting should start
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
	
	/**If empty allows for an item to be dropped from a linked selectable.
	 * @param dropOnEmpty
	 */
	public void setDropOnEmpty(boolean dropOnEmpty) {
		this.options.put("dropOnEmpty", dropOnEmpty);
	}
	
	/**If true, forces the helper to have a size.
	 * @param forceHelperSize
	 */
	public void setForceHelperSize(boolean forceHelperSize) {
		this.options.put("forceHelperSize", forceHelperSize);
	}
	
	/**If true, forces the placeholder to have a size.
	 * @param forcePlaceholderSize
	 */
	public void setForcePlaceholderSize(boolean forcePlaceholderSize) {
		this.options.put("forcePlaceholderSize", forcePlaceholderSize);
	}
	
	/**Snaps the sorting element or helper to a grid, every x and y pixels. 
	 * Array values: [x, y]
	 * @param x
	 * @param y
	 */
	public void setGrid(int x, int y) {
		ArrayItemOptions<IntegerItemOptions> grids = new ArrayItemOptions<IntegerItemOptions>();
		grids.add(new IntegerItemOptions(x));
		grids.add(new IntegerItemOptions(y));
		this.options.put("grid", grids);
	}
	
	/**Restricts sort start click to the specified element.
	 * @param handle
	 */
	public void setHandle(String handle) {
		this.options.putLiteral("handle", handle);
	}
	
	/**Allows for a helper element to be used for dragging display. The supplied
	 * function receives the event and the element being sorted, and should 
	 * return a DOMElement to be used as a custom proxy helper. Possible 
	 * values: 'original', 'clone'
	 * @param helper
	 */
	public void setHelper(String helper) {
		this.options.putLiteral("helper", helper);
	}
	
	/**Specifies which items inside the element should be sortable.
	 * @param items Cursor (default : '> *')
	 */
	public void setItems(String items) {
		this.options.putLiteral("items", items);
	}
	
	/**Set the opacity of the helper while sorting. From 0.01 to 1
	 * @param opacity
	 */
	public void setOpacity(float opacity) {
		this.options.put("opacity", opacity);
	}
	
	/**Set's the class that gets applied to the otherwise white space.
	 * @param placeholder
	 */
	public void setPlaceholder(String placeholder) {
		this.options.putLiteral("placeholder", placeholder);
	}
	
	/**If set to true, the item will be reverted to its new DOM position with 
	 * a smooth animation.
	 * @param revert
	 */
	public void setRevert(boolean revert) {
		this.options.put("revert", revert);
	}
	
	/**If set to true, the page scrolls when coming to an edge.
	 * @param scroll
	 */
	public void setScroll(boolean scroll) {
		this.options.put("scroll", scroll);
	}
	
	/**Defines how near the mouse must be to an edge to start scrolling.
	 * @param scrollSensitivity
	 */
	public void setScrollSensitivity(int scrollSensitivity) {
		this.options.put("scrollSensitivity", scrollSensitivity);
	}
	
	/**The speed at which the window should scroll once the mouse pointer gets 
	 * within the scrollSensitivity distance.
	 * @param scrollSpeed
	 */
	public void setScrollSpeed(int scrollSpeed) {
		this.options.put("scrollSpeed", scrollSpeed);
	}
	
	/** Set's the tolerance
	 * <ul>
	 * 	<li><b>intersect</b>: draggable overlaps the droppable at least 50%</li>
	 * 	<li><b>pointer</b>: mouse pointer overlaps the droppable</li>
	 * </ul>
	 * @param tolerance
	 */
	public void setTolerance(ToleranceEnum tolerance) {
		this.options.putLiteral("tolerance", tolerance.toString().toLowerCase());
	}
	
	/**Set's Z-index for element/helper while being sorted.
	 * @param zIndex
	 */
	public void setZIndex(int zIndex) {
		this.options.put("zIndex", zIndex);
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(this.getComponent()).$().chain("sortable",
				this.options.getJavaScriptOptions());
	}
	
	/*---- Events section ---*/
	
	/**Set's the callback when using connected lists, every connected list on 
	 * drag start receives it.
	 * @param activate
	 */
	public void setActivateEvent(JsScopeUiEvent activate) {
		this.options.put("activate", activate);
	}
	
	/**Set's the callback when sorting stops, but when the placeholder/helper is 
	 * still available.
	 * @param beforeStop
	 */
	public void setBeforeStopEvent(JsScopeUiEvent beforeStop) {
		this.options.put("beforeStop", beforeStop);
	}
	
	/**Set's the callback during sorting, but only when the DOM position has changed.
	 * @param change
	 */
	public void setChangeEvent(JsScopeUiEvent change) {
		this.options.put("change", change);
	}
	
	/**Set's the callback when sorting was stopped, is propagated to all possible
	 * connected lists.
	 * @param deactivate
	 */
	public void setDeactivateEvent(JsScopeUiEvent deactivate) {
		this.options.put("deactivate", deactivate);
	}
	
	/**Set's the callback when a sortable item is moved away from a connected list.
	 * @param out
	 */
	public void setOutEvent(JsScopeUiEvent out) {
		this.options.put("out", out);
	}
	
	/**Set's the callback when a sortable item is moved into a connected list.
	 * @param over
	 */
	public void setOverEvent(JsScopeUiEvent over) {
		this.options.put("over", over);
	}
	
	/**Set's the callback when a connected sortable list has received an item 
	 * from another list.
	 * @param receive
	 */
	public void setReceiveEvent(JsScopeUiEvent receive) {
		this.options.put("receive", receive);
	}
	
	/**Set's the callback when a sortable item has been dragged out from the 
	 * list and into another.
	 * @param remove
	 */
	public void setRemoveEvent(JsScopeUiEvent remove) {
		this.options.put("remove", remove);
	}
	
	/**Set's the callback during sorting
	 * @param sort
	 */
	public void setSortEvent(JsScopeUiEvent sort) {
		this.options.put("sort", sort);
	}
	
	/**Set's the callback when sorting starts
	 * @param start
	 */
	public void setStartEvent(JsScopeUiEvent start) {
		this.options.put("start", start);
	}
	
	/**Set's the callback when sorting has stopped.
	 * @param stop
	 */
	public void setStopEvent(JsScopeUiEvent stop) {
		this.options.put("stop", stop);
	}
	
	/**Set's the callback when the user stopped sorting and the DOM position has changed.
	 * @param update
	 */
	public void setUpdateEvent(JsScopeUiEvent update) {
		this.options.put("update", update);
	}
	
	/*---- Methods section ----*/
	
	/**Method to cancel
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement cancel() {
		return new JsQuery(getComponent()).$().chain("sortable", "'cancel'");
	}

	/**Method to cancel within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void cancel(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.cancel().render().toString());
	}
	
	/**Method to destroy
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(getComponent()).$().chain("sortable", "'destroy'");
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
		return new JsQuery(getComponent()).$().chain("sortable", "'disable'");
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
		return new JsQuery(getComponent()).$().chain("sortable", "'enable'");
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
		return new JsQuery(getComponent()).$().chain("sortable", "'refresh'");
	}

	/**Method to refresh within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.refresh().render().toString());
	}
	
	/**Method to refresh positions
	 * @return the associated JsStatement
	 */
	public JsStatement refreshPositions() {
		return new JsQuery(getComponent()).$().chain("sortable", "'refreshPositions'");
	}

	/**Method to refresh positions within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void refreshPositions(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.refreshPositions().render().toString());
	}
	
	/**Method to serializes the sortable's item id's into an array of string
	 * @return the associated JsStatement
	 */
	public JsStatement toArray() {
		return new JsQuery(getComponent()).$().chain("sortable", "'toArray'");
	}
}
