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
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.mouse.MouseJavascriptResourceReference;
import org.odlabs.wiquery.ui.sortable.SortableHelper.HelperEnum;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * 	Wicket behabior to use the JQuery UI Sortable behavior
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
	private static final long serialVersionUID = 2L;
	
	/** Properties on the ui parameter (use it into callback functions) : The
	 *  current helper element (most often a clone of the item) */
	public static final String UI_HELPER = "ui.helper";
	/** Properties on the ui parameter (use it into callback functions) : The 
	 * current position of the helper */
	public static final String UI_POSITION = "ui.position";
	/** Properties on the ui parameter (use it into callback functions) : The 
	 * current absolute position of the helper */
	public static final String UI_OFFSET = "ui.offset";
	/** Properties on the ui parameter (use it into callback functions) : The 
	 * current dragged element */
	public static final String UI_ITEM = "ui.item";
	/** Properties on the ui parameter (use it into callback functions) : The 
	 * placeholder (if you defined one) */
	public static final String UI_PLACEHOLDER= "ui.placeholder";
	/** Properties on the ui parameter (use it into callback functions) : The 
	 * sortable where the item comes from 
	 * (only exists if you move from one connected list to another) */
	public static final String UI_SENDER = "ui.sender";

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
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(MouseJavascriptResourceReference.get());
		wiQueryResourceManager
				.addJavaScriptResource(SortableJavaScriptResourceReference.get());
	}
	
	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(this.getComponent()).$().chain("sortable",
				this.options.getJavaScriptOptions());
	}
	
	/*---- Options section ---*/
	
	/**
	 * @return the appendTo option value
	 */
	public String getAppendTo() {
		String appendTo = this.options.getLiteral("appendTo");
		return appendTo == null ? "parent" : appendTo;
	}
	
	/**
	 * @return the axis option value
	 */
	public AxisEnum getAxis() {
		String axis = this.options.getLiteral("axis");
		return axis == null ? null : AxisEnum.valueOf(axis.toUpperCase());
	}
	
	/**
	 * @return the cancel option value
	 */
	public String getCancel() {
		String cancel = this.options.getLiteral("cancel");
		return cancel == null ? "input,button" : cancel;
	}
	
	/**
	 * @return the connectWith option value
	 */
	public String getConnectWith() {
		return this.options.getLiteral("connectWith");
	}
	
	/**
	 * @return the containment option value
	 * @deprecated will be changed in 1.2 to return a {@link SortableContainment}
	 */
	@Deprecated
	public String getContainment() {
		return this.options.getLiteral("containment");
	}
	
	/**
	 * @return the containment option value
	 */
	public SortableContainment getContainmentComplex() {
		if(this.options.getComplexOption("containment") instanceof SortableContainment){
			return (SortableContainment) this.options.getComplexOption("containment");
		}
		
		return null;
	}
	
	/**
	 * @return the cursor option value
	 */
	public String getCursor() {
		String cursor = this.options.getLiteral("cursor");
		return cursor == null ? "auto" : cursor;
	}
	
	/**
	 * @return the cursorAt option value
	 */
	public CursorAtEnum getCursorAt() {
		String cursorAt = this.options.getLiteral("cursorAt");
		return cursorAt == null ? null : CursorAtEnum.valueOf(cursorAt.toUpperCase()
				.replace(' ', '_'));
	}
	
	/**
	 * @return the delay option value
	 */
	public int getDelay() {
		if(this.options.containsKey("delay")){
			return this.options.getInt("delay");
		}
		
		return 0;
	}
	
	/**
	 * @return the distance option value
	 */
	public int getDistance() {
		if(this.options.containsKey("distance")){
			return this.options.getInt("distance");
		}
		
		return 1;
	}
	
	/**
	 * @return the dropOnEmpty option value
	 * @deprecated will be removed in 1.2
	 */
	@Deprecated
	public boolean getDropOnEmpty() {
		return isDropOnEmpty();
	}
	
	/**
	 * @return the forceHelperSize option value
	 * @deprecated will be removed in 1.2
	 */
	@Deprecated
	public boolean getForceHelperSize() {
		return isForceHelperSize();
	}
	
	/**
	 * @return the forcePlaceholderSize option value
	 * @deprecated will be removed in 1.2
	 */
	@Deprecated
	public boolean getForcePlaceholderSize() {
		return isForcePlaceholderSize();
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
		return this.options.getLiteral("handle");
	}
	
	/**
	 * @return the helper option value
	 * @deprecated will be changed in 1.2 to return a {@link SortableHelper}
	 */
	@Deprecated
	public String getHelper() {
		return this.options.getLiteral("helper");
	}
	
	/**
	 * @return the helper option value
	 */
	public SortableHelper getHelperComplex() {
		if(this.options.getComplexOption("helper") instanceof SortableHelper){
			return (SortableHelper) this.options.getComplexOption("helper");
		}
		
		return new SortableHelper(HelperEnum.ORIGINAL);
	}
	
	/**
	 * @return the items option value
	 */
	public String getItems() {
		String items = this.options.getLiteral("items");
		return items == null ? "> *" : items;
	}
	
	/**
	 * @return the opacity option value
	 */
	public float getOpacity() {
		if(this.options.containsKey("opacity")){
			return this.options.getFloat("opacity");
		}
		
		return 0F;
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
		return this.options.getLiteral("placeholder");
	}
	
	/**
	 * @return the revert option value
	 */
	public SortableRevert getRevert() {
		if(this.options.getComplexOption("revert") instanceof SortableRevert){
			return (SortableRevert) this.options.getComplexOption("revert");
		}
		
		return new SortableRevert(false);
	}
	
	/**
	 * @return the scrollSensitivity option value
	 */
	public int getScrollSensitivity() {
		if(this.options.containsKey("scrollSensitivity")){
			return this.options.getInt("scrollSensitivity");
		}
		
		return 20;
	}
	
	/**
	 * @return the scrollSpeed option value
	 */
	public int getScrollSpeed() {
		if(this.options.containsKey("scrollSpeed")){
			return this.options.getInt("scrollSpeed");
		}
		
		return 20;
	}
	
	/**
	 * @return the tolerance option value
	 */
	public ToleranceEnum getTolerance() {
		String tolerance = this.options.getLiteral("tolerance");
		return tolerance == null ? ToleranceEnum.INTERSECT : ToleranceEnum.valueOf(tolerance.toUpperCase());
	}
	
	/**
	 * @return the zIndex option value
	 */
	public int getZIndex() {
		if(this.options.containsKey("zIndex")){
			return this.options.getInt("zIndex");
		}
		
		return 1000;
		
	}
	
	/**Disables (true) or enables (false) the sortable. Can be set when 
	 * initialising (first creating) the sortable.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public SortableBehavior setDisabled(boolean disabled) {
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
	
	/**
	 * @return the dropOnEmpty option value
	 */
	public boolean isDropOnEmpty() {
		if(this.options.containsKey("dropOnEmpty")){
			return this.options.getBoolean("dropOnEmpty");
		}
		
		return true;
	}
	
	/**
	 * @return the forceHelperSize option value
	 */
	public boolean isForceHelperSize() {
		if(this.options.containsKey("forceHelperSize")){
			return this.options.getBoolean("forceHelperSize");
		}
		
		return false;
	}
	
	/**
	 * @return the forcePlaceholderSize option value
	 */
	public boolean isForcePlaceholderSize() {
		if(this.options.containsKey("forcePlaceholderSize")){
			return this.options.getBoolean("forcePlaceholderSize");
		}
		
		return false;
	}
	
	/**
	 * @return the revert option value
	 * @deprecated will be changed in 1.2 to return a {@link SortableRevert}
	 */
	@Deprecated
	public boolean isRevert() {
		if(this.options.containsKey("revert")){
			return this.options.getBoolean("revert");
		}
		
		return false;
	}
	
	/**
	 * @return the scroll option value
	 */
	public boolean isScroll() {
		if(this.options.containsKey("scroll")){
			return this.options.getBoolean("scroll");
		}
		
		return true;
	}
	
	/**Defines where the helper that moves with the mouse is being appended to 
	 * during the drag (for example, to resolve overlap/zIndex issues).
	 * @param appendTo
	 * @return instance of the current behavior
	 */
	public SortableBehavior setAppendTo(String appendTo) {
		this.options.putLiteral("appendTo", appendTo);
		return this;
	}
	
	/**If defined, the items can be dragged only horizontally or vertically. 
	 * Possible values:'x', 'y'.
	 * @param axis
	 * @return instance of the current behavior
	 */
	public SortableBehavior setAxis(AxisEnum axis) {
		this.options.putLiteral("axis", axis.toString().toLowerCase());
		return this;
	}
	
	/** Set's the prevent selecting if you start on elements matching the selector
	 * @param cancel Selector (default : ':input,option')
	 * @return instance of the current behavior
	 */
	public SortableBehavior setCancel(String cancel) {
		this.options.putLiteral("cancel", cancel);
		return this;
	}
	
	/**Set a jQuery selector with items that also have sortables applied. 
	 * If used, the sortable is now connected to the other one-way, so you can 
	 * drag from this sortable to the other.
	 * @param connectWith Selector
	 * @return instance of the current behavior
	 */
	public SortableBehavior setConnectWith(String connectWith) {
		this.options.putLiteral("connectWith", connectWith);
		return this;
	}
	
	/**Constrains dragging to within the bounds of the specified element - 
	 * can be a DOM element, 'parent', 'document', 'window', or a jQuery selector.
	 * @param containment
	 * @return instance of the current behavior
	 * @deprecated will be removed in 1.2
	 */
	@Deprecated
	public SortableBehavior setContainment(String containment) {
		this.options.putLiteral("containment", containment);
		return this;
	}
	
	/**Constrains dragging to within the bounds of the specified element - 
	 * can be a DOM element, 'parent', 'document', 'window', or a jQuery selector.
	 * @param containment
	 * @return instance of the current behavior
	 */
	public SortableBehavior setContainment(SortableContainment containment) {
		this.options.put("containment", containment);
		return this;
	}
	
	/**Set the cursor that is being shown while sorting
	 * @param cursor
	 * @return instance of the current behavior
	 */
	public SortableBehavior setCursor(String cursor) {
		this.options.putLiteral("cursor", cursor);
		return this;
	}
	
	/**Moves the sorting element or helper so the cursor always appears to drag 
	 * from the same position. Coordinates can be given as a hash using a 
	 * combination of one or two keys: { top, left, right, bottom }
	 * @param cusorAt
	 * @return instance of the current behavior
	 */
	public SortableBehavior setCursorAt(CursorAtEnum cusorAt) {
		this.options.putLiteral("cursorAt", cusorAt.toString().toLowerCase()
				.replace('_', ' '));
		return this;
	}
	
	/** Set's the delay (in milliseconds) to define when the sorting should start
	 * @param delay
	 * @return instance of the current behavior
	 */
	public SortableBehavior setDelay(int delay) {
		this.options.put("delay", delay);
		return this;
	}

	/** Set's the tolerance in pixels
	 * @param distance
	 * @return instance of the current behavior
	 */
	public SortableBehavior setDistance(int distance) {
		this.options.put("distance", distance);
		return this;
	}
	
	/**If empty allows for an item to be dropped from a linked selectable.
	 * @param dropOnEmpty
	 * @return instance of the current behavior
	 */
	public SortableBehavior setDropOnEmpty(boolean dropOnEmpty) {
		this.options.put("dropOnEmpty", dropOnEmpty);
		return this;
	}
	
	/**If true, forces the helper to have a size.
	 * @param forceHelperSize
	 * @return instance of the current behavior
	 */
	public SortableBehavior setForceHelperSize(boolean forceHelperSize) {
		this.options.put("forceHelperSize", forceHelperSize);
		return this;
	}
	
	/**If true, forces the placeholder to have a size.
	 * @param forcePlaceholderSize
	 * @return instance of the current behavior
	 */
	public SortableBehavior setForcePlaceholderSize(boolean forcePlaceholderSize) {
		this.options.put("forcePlaceholderSize", forcePlaceholderSize);
		return this;
	}
	
	/**Snaps the sorting element or helper to a grid, every x and y pixels. 
	 * Array values: [x, y]
	 * @param x
	 * @param y
	 * @return instance of the current behavior
	 */
	public SortableBehavior setGrid(int x, int y) {
		ArrayItemOptions<IntegerItemOptions> grids = new ArrayItemOptions<IntegerItemOptions>();
		grids.add(new IntegerItemOptions(x));
		grids.add(new IntegerItemOptions(y));
		this.options.put("grid", grids);
		return this;
	}
	
	/**Restricts sort start click to the specified element.
	 * @param handle
	 * @return instance of the current behavior
	 */
	public SortableBehavior setHandle(String handle) {
		this.options.putLiteral("handle", handle);
		return this;
	}
	
	/**Allows for a helper element to be used for dragging display. The supplied
	 * function receives the event and the element being sorted, and should 
	 * return a DOMElement to be used as a custom proxy helper. Possible 
	 * values: 'original', 'clone'
	 * @param helper
	 * @return instance of the current behavior
	 * @deprecated will be removed in 1.2
	 */
	@Deprecated
	public SortableBehavior setHelper(String helper) {
		this.options.putLiteral("helper", helper);
		return this;
	}
	
	/**Allows for a helper element to be used for dragging display. The supplied
	 * function receives the event and the element being sorted, and should 
	 * return a DOMElement to be used as a custom proxy helper. Possible 
	 * values: 'original', 'clone'
	 * @param helper
	 * @return instance of the current behavior
	 */
	public SortableBehavior setHelper(SortableHelper helper) {
		this.options.put("helper", helper);
		return this;
	}
	
	/**Specifies which items inside the element should be sortable.
	 * @param items Cursor (default : '> *')
	 * @return instance of the current behavior
	 */
	public SortableBehavior setItems(String items) {
		this.options.putLiteral("items", items);
		return this;
	}
	
	/**Set the opacity of the helper while sorting. From 0.01 to 1
	 * @param opacity
	 * @return instance of the current behavior
	 */
	public SortableBehavior setOpacity(float opacity) {
		this.options.put("opacity", opacity);
		return this;
	}
	
	/**Set's the class that gets applied to the otherwise white space.
	 * @param placeholder
	 * @return instance of the current behavior
	 */
	public SortableBehavior setPlaceholder(String placeholder) {
		this.options.putLiteral("placeholder", placeholder);
		return this;
	}
	
	/**If set to true, the item will be reverted to its new DOM position with 
	 * a smooth animation.
	 * @param revert
	 * @return instance of the current behavior
	 * @deprecated will be removed in 1.2
	 */
	@Deprecated
	public SortableBehavior setRevert(boolean revert) {
		this.options.put("revert", revert);
		return this;
	}
	
	/**If set to true, the item will be reverted to its new DOM position with 
	 * a smooth animation.
	 * @param revert
	 * @return instance of the current behavior
	 */
	public SortableBehavior setRevert(SortableRevert revert) {
		this.options.put("revert", revert);
		return this;
	}
	
	/**If set to true, the page scrolls when coming to an edge.
	 * @param scroll
	 * @return instance of the current behavior
	 */
	public SortableBehavior setScroll(boolean scroll) {
		this.options.put("scroll", scroll);
		return this;
	}

	/**Defines how near the mouse must be to an edge to start scrolling.
	 * @param scrollSensitivity
	 * @return instance of the current behavior
	 */
	public SortableBehavior setScrollSensitivity(int scrollSensitivity) {
		this.options.put("scrollSensitivity", scrollSensitivity);
		return this;
	}
	
	/**The speed at which the window should scroll once the mouse pointer gets 
	 * within the scrollSensitivity distance.
	 * @param scrollSpeed
	 * @return instance of the current behavior
	 */
	public SortableBehavior setScrollSpeed(int scrollSpeed) {
		this.options.put("scrollSpeed", scrollSpeed);
		return this;
	}

	/** Set's the tolerance
	 * <ul>
	 * 	<li><b>intersect</b>: draggable overlaps the droppable at least 50%</li>
	 * 	<li><b>pointer</b>: mouse pointer overlaps the droppable</li>
	 * </ul>
	 * @param tolerance
	 * @return instance of the current behavior
	 */
	public SortableBehavior setTolerance(ToleranceEnum tolerance) {
		this.options.putLiteral("tolerance", tolerance.toString().toLowerCase());
		return this;
	}

	/**Set's Z-index for element/helper while being sorted.
	 * @param zIndex
	 * @return instance of the current behavior
	 */
	public SortableBehavior setZIndex(int zIndex) {
		this.options.put("zIndex", zIndex);
		return this;
	}
	
	/*---- Events section ---*/
	/**Set's the callback when using connected lists, every connected list on 
	 * drag start receives it.
	 * @param activate
	 * @return instance of the current behavior
	 */
	public SortableBehavior setActivateEvent(JsScopeUiEvent activate) {
		this.options.put("activate", activate);
		return this;
	}
	
	/**Set's the callback when sorting stops, but when the placeholder/helper is 
	 * still available.
	 * @param beforeStop
	 * @return instance of the current behavior
	 */
	public SortableBehavior setBeforeStopEvent(JsScopeUiEvent beforeStop) {
		this.options.put("beforeStop", beforeStop);
		return this;
	}
	
	/**Set's the callback during sorting, but only when the DOM position has changed.
	 * @param change
	 * @return instance of the current behavior
	 */
	public SortableBehavior setChangeEvent(JsScopeUiEvent change) {
		this.options.put("change", change);
		return this;
	}
	
	/**Set's the callback when sorting was stopped, is propagated to all possible
	 * connected lists.
	 * @param deactivate
	 * @return instance of the current behavior
	 */
	public SortableBehavior setDeactivateEvent(JsScopeUiEvent deactivate) {
		this.options.put("deactivate", deactivate);
		return this;
	}
	
	/**Set's the callback when a sortable item is moved away from a connected list.
	 * @param out
	 * @return instance of the current behavior
	 */
	public SortableBehavior setOutEvent(JsScopeUiEvent out) {
		this.options.put("out", out);
		return this;
	}
	
	/**Set's the callback when a sortable item is moved into a connected list.
	 * @param over
	 * @return instance of the current behavior
	 */
	public SortableBehavior setOverEvent(JsScopeUiEvent over) {
		this.options.put("over", over);
		return this;
	}
	
	/**Set's the callback when a connected sortable list has received an item 
	 * from another list.
	 * @param receive
	 * @return instance of the current behavior
	 */
	public SortableBehavior setReceiveEvent(JsScopeUiEvent receive) {
		this.options.put("receive", receive);
		return this;
	}
	
	/**Set's the callback when a sortable item has been dragged out from the 
	 * list and into another.
	 * @param remove
	 * @return instance of the current behavior
	 */
	public SortableBehavior setRemoveEvent(JsScopeUiEvent remove) {
		this.options.put("remove", remove);
		return this;
	}
	
	/**Set's the callback during sorting
	 * @param sort
	 * @return instance of the current behavior
	 */
	public SortableBehavior setSortEvent(JsScopeUiEvent sort) {
		this.options.put("sort", sort);
		return this;
	}
	
	/**Set's the callback when sorting starts
	 * @param start
	 * @return instance of the current behavior
	 */
	public SortableBehavior setStartEvent(JsScopeUiEvent start) {
		this.options.put("start", start);
		return this;
	}

	/**Set's the callback when sorting has stopped.
	 * @param stop
	 * @return instance of the current behavior
	 */
	public SortableBehavior setStopEvent(JsScopeUiEvent stop) {
		this.options.put("stop", stop);
		return this;
	}
	
	/**Set's the callback when the user stopped sorting and the DOM position has changed.
	 * @param update
	 * @return instance of the current behavior
	 */
	public SortableBehavior setUpdateEvent(JsScopeUiEvent update) {
		this.options.put("update", update);
		return this;
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
	
	/**Method to serialize (in default mode)
	 * @return the associated JsStatement
	 */
	public JsStatement serialize() {
		return new JsQuery(getComponent()).$().chain("sortable", "'serialize'");
	}
	
	/**Method to serialize (in default mode) within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void serialize(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.serialize().render().toString());
	}
	
	/**Method to serializes the sortable's item id's into an array of string
	 * @return the associated JsStatement
	 */
	public JsStatement toArray() {
		return new JsQuery(getComponent()).$().chain("sortable", "'toArray'");
	}
	
	/**Method to returns the .ui-sortable  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(getComponent()).$().chain("sortable", "'widget'");
	}

	/**Method to returns the .ui-sortable  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.widget().render().toString());
	}
}
