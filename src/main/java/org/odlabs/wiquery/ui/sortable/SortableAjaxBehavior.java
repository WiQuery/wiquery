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
package org.odlabs.wiquery.ui.sortable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.core.util.MarkupIdVisitor;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.AxisEnum;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.CursorAtEnum;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.ToleranceEnum;

/**
 * $Id: SortableAjaxBehavior
 * <p>
 * Sets the attached component sortable behavior. When the user finished to
 * sort some childs components,{@link #onReceive(Component, int, Component, AjaxRequestTarget)},
 * or / and {@link #onRemove(Component, AjaxRequestTarget)} or / and 
 * {@link #onUpdate(Component, int, AjaxRequestTarget)} is / are called via Ajax.
 * </p>
 * 
 * <p>
 * This behavior contains a {@link SortableBehavior} which is used to control 
 * the options of the sortable, including all the options and event of the
 * behavior. Example:
 * <pre>
 * SortableAjaxBehavior sortable = new SortableAjaxBehavior(SortedEvent.RECEIVE) {
 * 		public void onReceive(Component sortedComponent, int index, 
			Component parentSortedComponent, AjaxRequestTarget ajaxRequestTarget) {
 * 			// Only this method will be called
 * 			...
 * 		}
 * 
 * 		public void onRemove(Component sortedComponent, AjaxRequestTarget ajaxRequestTarget) {
 * 			...
 * 		}
 * 
 * 		public void onUpdate(Component sortedComponent, int index, AjaxRequestTarget ajaxRequestTarget) {
 * 			...
 * 		}
 * }
 * SortableBehavior sb = sortable.getSortableBehavior();
 * sb.setConnectWith("#anotherSortableList");
 * sb.setRevert(true);
 * add(sortable);
 * </pre>
 * </p>
 * 
 * @param <E> Type of component to find
 * @author Julien Roche
 * @author Ernesto Reinaldo Barreiro
 * @since 1.0
 */
public abstract class SortableAjaxBehavior<E extends Component> extends AbstractDefaultAjaxBehavior {
	/**
	 * We override the behavior to deny the access of critical methods
	 * 
	 * @author Julien Roche
	 * 
	 */
	private class InnerSortableBehavior extends SortableBehavior {
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = 5587258236214715234L;
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
		 */
		@Override
		public void contribute(WiQueryResourceManager wiQueryResourceManager) {
			super.contribute(wiQueryResourceManager);
			SortableAjaxBehavior.this.contribute(wiQueryResourceManager);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the DroppableAjaxBehavior");
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerReceiveEvent(JsScopeUiEvent receive) {
			super.setReceiveEvent(receive);
		}
		
		/**
		 * For framework internal use only.
		 */
		private void setInnerRemoveEvent(JsScopeUiEvent remove) {
			super.setRemoveEvent(remove);
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerUpdateEvent(JsScopeUiEvent update) {
			super.setUpdateEvent(update);
		}
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableBehavior#setReceiveEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public SortableBehavior setReceiveEvent(JsScopeUiEvent receive) {
			if(callbacks.contains(SortedEvent.RECEIVE)){
				throw new UnsupportedOperationException(
				"You can't call this method into the SortableAjaxBehavior");
			}
			
			return super.setReceiveEvent(receive);
		}
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableBehavior#setRemoveEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public SortableBehavior setRemoveEvent(JsScopeUiEvent remove) {
			if(callbacks.contains(SortedEvent.REMOVE)){
				throw new UnsupportedOperationException(
				"You can't call this method into the SortableAjaxBehavior");
			}
			
			return super.setRemoveEvent(remove);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableBehavior#setUpdateEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public SortableBehavior setUpdateEvent(JsScopeUiEvent update) {
			if(callbacks.contains(SortedEvent.UPDATE)){
				throw new UnsupportedOperationException(
				"You can't call this method into the SortableAjaxBehavior");
			}
			
			return super.setUpdateEvent(update);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.sortable.SortableBehavior#statement()
		 */
		@Override
		public JsStatement statement() {
			if(callbacks.contains(SortedEvent.RECEIVE)){
				sortableBehavior.setInnerReceiveEvent(new JsScopeUiEvent() {
					private static final long serialVersionUID = 1L;
		
					/**
					 * {@inheritDoc}
					 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
					 */
					@Override
					protected void execute(JsScopeContext scopeContext) {				
						scopeContext.append(SortableAjaxBehavior.this.getCallbackScriptReceive(true));	
					}
		
				});
			}
			
			if(callbacks.contains(SortedEvent.REMOVE)){
				sortableBehavior.setInnerRemoveEvent(new JsScopeUiEvent() {
					private static final long serialVersionUID = 1L;
		
					/**
					 * {@inheritDoc}
					 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
					 */
					@Override
					protected void execute(JsScopeContext scopeContext) {
						scopeContext.append(SortableAjaxBehavior.this.getCallbackScriptRemove(true));
					}
		
				});
			}
			
			if(callbacks.contains(SortedEvent.UPDATE)){
				sortableBehavior.setInnerUpdateEvent(new JsScopeUiEvent() {
					private static final long serialVersionUID = 1L;
		
					/**
					 * {@inheritDoc}
					 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
					 */
					@Override
					protected void execute(JsScopeContext scopeContext) {
						scopeContext.append(SortableAjaxBehavior.this.getCallbackScriptUpdate(true));
					}
		
				});
			}
			
			return super.statement();
		}
	}
	
	/**
	 * 	We use standard AbstractDefaultAjaxBehavior machinery to generate script: what way all the logic 
	 *  regarding IAjaxCallDecorator or indicatorId will be added to the generated script. 
	 *  This makes sortable AJAX behavior compatible with standard Wicket's AJAX call-backs.
	 * 
	 * (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackScript(boolean)
	 */
	protected CharSequence getCallbackScriptReceive(boolean onlyTargetActivePage)
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl(onlyTargetActivePage) 
				+ "&" + SORTED_TYPE + "=" + SortedEvent.RECEIVE.toString().toLowerCase() 
				+ "&" + SORTED_INDEX + "='+$(this).find(':data(sortable-item)').index(" + SortableBehavior.UI_ITEM + ")+'"
				+ "&" + SORTED_ID + "='+ $(" + SortableBehavior.UI_ITEM + ").attr('id')" 
				+ "+'&" + SORTED_PARENT_ID + "='+ $(" + SortableBehavior.UI_SENDER + ").attr('id')");
	}
	
	/**
	 * 	We use standard AbstractDefaultAjaxBehavior machinery to generate script: what way all the logic 
	 *  regarding IAjaxCallDecorator or indicatorId will be added to the generated script. 
	 *  This makes sortable AJAX behavior compatible with standard Wicket's AJAX call-backs.
	 * 
	 * (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackScript(boolean)
	 */
	protected CharSequence getCallbackScriptRemove(boolean onlyTargetActivePage)
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl(onlyTargetActivePage) 
				+ "&" + SORTED_TYPE + "=" + SortedEvent.REMOVE.toString().toLowerCase() 
				+ "&" + SORTED_ID + "='+ $(" + SortableBehavior.UI_ITEM + ").attr('id')");
	}
	
	/**
	 * 	We use standard AbstractDefaultAjaxBehavior machinery to generate script: what way all the logic 
	 *  regarding IAjaxCallDecorator or indicatorId will be added to the generated script. 
	 *  This makes sortable AJAX behavior compatible with standard Wicket's AJAX call-backs.
	 * 
	 * (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackScript(boolean)
	 */
	protected CharSequence getCallbackScriptUpdate(boolean onlyTargetActivePage)
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl(onlyTargetActivePage) 
				+ "&" + SORTED_TYPE + "=" + SortedEvent.UPDATE.toString().toLowerCase() 
				+ "&" + SORTED_INDEX + "='+$(this).find(':data(sortable-item)').index(" + SortableBehavior.UI_ITEM + ")+'"
				+ "&" + SORTED_ID + "='+ $(" + SortableBehavior.UI_ITEM + ").attr('id')");
	}
	
	/**
	 * Enumeration of sorted ajax callback
	 * @author Julien Roche
	 *
	 */
	public enum SortedEvent {
		RECEIVE,
		REMOVE,
		UPDATE;
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	/** Sorted identifiant into the request */
	private static final String SORTED_ID = "sortedId";
	
	/** Sorted index into the request */
	private static final String SORTED_INDEX = "sortedIndex";
	
	/** Sorted parent identifiant into the request */
	private static final String SORTED_PARENT_ID = "sortedParentId";
	
	/** Sorted type into the request */
	private static final String SORTED_TYPE = "sortedType";
	
	// Properties
	/**
	 * Adding the standard sortable JavaScript behavior
	 */
	private InnerSortableBehavior sortableBehavior;

	/**
	 * Required callbacks
	 */
	private Set<SortedEvent> callbacks;
	
	/**
	 * Default constructor
	 */
	public SortableAjaxBehavior() {
		this(SortedEvent.values());
	}
	
	/**
	 * Constructor
	 * @param callbacks Ajax callbacks to enable
	 */
	public SortableAjaxBehavior(SortedEvent... callbacks) {
		super();
		sortableBehavior = new InnerSortableBehavior();
		this.callbacks = new HashSet<SortedEvent>(Arrays.asList(callbacks));
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.sortable.SortableBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 * Override this method for additional resources
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		// To override
	}

	/**
	 * @return the standard Sortable JavaScript behavior
	 */
	public SortableBehavior getSortableBehavior() {
		return sortableBehavior;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(sortableBehavior);
	}
	
	/**
	 * onReceive is called back when a connected sortable list has received an item 
	 * from another list. 
	 * 
	 * @param sortedComponent
	 *            the sorted {@link Component}
	 * @param parentSortedComponent 
	 * 			  the parent of the sorted {@link Component}
	 * @param index Index of the sorted {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onReceive(E sortedComponent, int index, 
			Component parentSortedComponent, AjaxRequestTarget ajaxRequestTarget);

	/**
	 * OnRemove is called back when a sortable item has been dragged out from the 
	 * list and into another.
	 * @param sortedComponent the sorted {@link Component}
	 * @param ajaxRequestTarget the Ajax target
	 */
	public abstract void onRemove(E sortedComponent,
			AjaxRequestTarget ajaxRequestTarget);

	/**
	 * For framework internal use only.
	 */
	@SuppressWarnings("unchecked")
	public final void onSort(AjaxRequestTarget target) {
		// getting sorted element id to retrieve the Wicket component
		String input = this.getComponent().getRequest().getParameter(
				SORTED_ID);
		
		String indexStr = this.getComponent().getRequest().getParameter(
				SORTED_INDEX);
		int index = indexStr == null ? -1 : Integer.valueOf(indexStr);
		
		SortedEvent sortedEvent =  SortedEvent.valueOf(this.getComponent().getRequest().getParameter(
				SORTED_TYPE).toUpperCase());
			
		MarkupIdVisitor visitor = new MarkupIdVisitor(input);
		this.getComponent().getPage().visitChildren(visitor);
		E sortedComponent = (E) visitor.getFoundComponent();
		
		switch(sortedEvent){
		case RECEIVE:
			String parent = this.getComponent().getRequest().getParameter(
					SORTED_PARENT_ID);
			MarkupIdVisitor visitorParent = new MarkupIdVisitor(parent);
			this.getComponent().getPage().visitChildren(visitorParent);
			
			onReceive(sortedComponent, index, visitorParent.getFoundComponent(), target);
			break;
		case REMOVE:
			onRemove(sortedComponent, target);
			break;
		case UPDATE:
			onUpdate(sortedComponent, index, target);
			break;
		}
	}
	
	/**
	 * onUpdate is called back when the user stopped sorting and the DOM 
	 * position has changed.
	 * 
	 * @param sortedComponent
	 *            the sorted {@link Component}
	 *            @param index Index of the sorted {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onUpdate(E sortedComponent, int index,
			AjaxRequestTarget ajaxRequestTarget);
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		onSort(target);
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	protected JsStatement statement() {
		return sortableBehavior.statement();
	}
	
	////////////////////////////////////////////////////////////////////////////
	////							SHORTCUTS								////
	////////////////////////////////////////////////////////////////////////////
	/*---- Options section ---*/
	
	/**
	 * @return the appendTo option value
	 */
	public String getAppendTo() {
		return sortableBehavior.getAppendTo();
	}
	
	/**
	 * @return the axis option value
	 */
	public AxisEnum getAxis() {
		return sortableBehavior.getAxis();
	}
	
	/**
	 * @return the cancel option value
	 */
	public String getCancel() {
		return sortableBehavior.getCancel();
	}
	
	/**
	 * @return the connectWith option value
	 */
	public String getConnectWith() {
		return sortableBehavior.getConnectWith();
	}
	
	/**
	 * @return the containment option value
	 */
	public SortableContainment getContainmentComplex() {
		return sortableBehavior.getContainmentComplex();
	}
	
	/**
	 * @return the cursor option value
	 */
	public String getCursor() {
		return sortableBehavior.getCursor();
	}
	
	/**
	 * @return the cursorAt option value
	 */
	public CursorAtEnum getCursorAt() {
		return sortableBehavior.getCursorAt();
	}
	
	/**
	 * @return the delay option value
	 */
	public int getDelay() {
		return sortableBehavior.getDelay();
	}
	
	/**
	 * @return the distance option value
	 */
	public int getDistance() {
		return sortableBehavior.getDistance();
	}
	
	/**
	 * @return the grid option value
	 */
	public ICollectionItemOptions getGrid() {
		return sortableBehavior.getGrid();
	}
	
	/**
	 * @return the handle option value
	 */
	public String getHandle() {
		return sortableBehavior.getHandle();
	}
	
	/**
	 * @return the helper option value
	 */
	public SortableHelper getHelperComplex() {
		return sortableBehavior.getHelperComplex();
	}
	
	/**
	 * @return the items option value
	 */
	public String getItems() {
		return sortableBehavior.getItems();
	}
	
	/**
	 * @return the opacity option value
	 */
	public float getOpacity() {
		return sortableBehavior.getOpacity();
	}
	
	/**
	 * @return the placeHolder option value
	 */
	public String getPlaceHolder() {
		return sortableBehavior.getPlaceHolder();
	}
	
	/**
	 * @return the revert option value
	 */
	public SortableRevert getRevert() {
		return sortableBehavior.getRevert();
	}
	
	/**
	 * @return the scrollSensitivity option value
	 */
	public int getScrollSensitivity() {
		return sortableBehavior.getScrollSensitivity();
	}
	
	/**
	 * @return the scrollSpeed option value
	 */
	public int getScrollSpeed() {
		return sortableBehavior.getScrollSpeed();
	}
	
	/**
	 * @return the tolerance option value
	 */
	public ToleranceEnum getTolerance() {
		return sortableBehavior.getTolerance();
	}
	
	/**
	 * @return the zIndex option value
	 */
	public int getZIndex() {
		return sortableBehavior.getZIndex();
		
	}
	
	/**Disables (true) or enables (false) the sortable. Can be set when 
	 * initialising (first creating) the sortable.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E> setDisabled(boolean disabled) {
		sortableBehavior.setDisabled(disabled);
		return this;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		return sortableBehavior.isDisabled();
	}
	
	/**
	 * @return the dropOnEmpty option value
	 */
	public boolean isDropOnEmpty() {
		return sortableBehavior.isDropOnEmpty();
	}
	
	/**
	 * @return the forceHelperSize option value
	 */
	public boolean isForceHelperSize() {
		return sortableBehavior.isForceHelperSize();
	}
	
	/**
	 * @return the forcePlaceholderSize option value
	 */
	public boolean isForcePlaceholderSize() {
		return sortableBehavior.isForcePlaceholderSize();
	}
	
	/**
	 * @return the scroll option value
	 */
	public boolean isScroll() {
		return sortableBehavior.isScroll();
	}
	
	/**Defines where the helper that moves with the mouse is being appended to 
	 * during the drag (for example, to resolve overlap/zIndex issues).
	 * @param appendTo
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setAppendTo(String appendTo) {
		sortableBehavior.setAppendTo(appendTo);
		return this;
	}
	
	/**If defined, the items can be dragged only horizontally or vertically. 
	 * Possible values:'x', 'y'.
	 * @param axis
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setAxis(AxisEnum axis) {
		sortableBehavior.setAxis(axis);
		return this;
	}
	
	/** Set's the prevent selecting if you start on elements matching the selector
	 * @param cancel Selector (default : ':input,option')
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setCancel(String cancel) {
		sortableBehavior.setCancel(cancel);
		return this;
	}
	
	/**Set a jQuery selector with items that also have sortables applied. 
	 * If used, the sortable is now connected to the other one-way, so you can 
	 * drag from this sortable to the other.
	 * @param connectWith Selector
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setConnectWith(String connectWith) {
		sortableBehavior.setConnectWith(connectWith);
		return this;
	}
	
	/**Constrains dragging to within the bounds of the specified element - 
	 * can be a DOM element, 'parent', 'document', 'window', or a jQuery selector.
	 * @param containment
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setContainment(SortableContainment containment) {
		sortableBehavior.setContainment(containment);
		return this;
	}
	
	/**Set the cursor that is being shown while sorting
	 * @param cursor
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setCursor(String cursor) {
		sortableBehavior.setCursor(cursor);
		return this;
	}
	
	/**Moves the sorting element or helper so the cursor always appears to drag 
	 * from the same position. Coordinates can be given as a hash using a 
	 * combination of one or two keys: { top, left, right, bottom }
	 * @param cusorAt
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setCursorAt(CursorAtEnum cusorAt) {
		sortableBehavior.setCursorAt(cusorAt);
		return this;
	}
	
	/** Set's the delay (in milliseconds) to define when the sorting should start
	 * @param delay
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setDelay(int delay) {
		sortableBehavior.setDelay(delay);
		return this;
	}

	/** Set's the tolerance in pixels
	 * @param distance
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setDistance(int distance) {
		sortableBehavior.setDistance(distance);
		return this;
	}
	
	/**If empty allows for an item to be dropped from a linked selectable.
	 * @param dropOnEmpty
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setDropOnEmpty(boolean dropOnEmpty) {
		sortableBehavior.setDropOnEmpty(dropOnEmpty);
		return this;
	}
	
	/**If true, forces the helper to have a size.
	 * @param forceHelperSize
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setForceHelperSize(boolean forceHelperSize) {
		sortableBehavior.setForceHelperSize(forceHelperSize);
		return this;
	}
	
	/**If true, forces the placeholder to have a size.
	 * @param forcePlaceholderSize
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setForcePlaceholderSize(boolean forcePlaceholderSize) {
		sortableBehavior.setForcePlaceholderSize(forcePlaceholderSize);
		return this;
	}
	
	/**Snaps the sorting element or helper to a grid, every x and y pixels. 
	 * Array values: [x, y]
	 * @param x
	 * @param y
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setGrid(int x, int y) {
		sortableBehavior.setGrid(x, y);
		return this;
	}
	
	/**Restricts sort start click to the specified element.
	 * @param handle
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setHandle(String handle) {
		sortableBehavior.setHandle(handle);
		return this;
	}
	
	/**Allows for a helper element to be used for dragging display. The supplied
	 * function receives the event and the element being sorted, and should 
	 * return a DOMElement to be used as a custom proxy helper. Possible 
	 * values: 'original', 'clone'
	 * @param helper
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setHelper(SortableHelper helper) {
		sortableBehavior.setHelper(helper);
		return this;
	}
	
	/**Specifies which items inside the element should be sortable.
	 * @param items Cursor (default : '> *')
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setItems(String items) {
		sortableBehavior.setItems(items);
		return this;
	}
	
	/**Set the opacity of the helper while sorting. From 0.01 to 1
	 * @param opacity
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setOpacity(float opacity) {
		sortableBehavior.setOpacity(opacity);
		return this;
	}
	
	/**Set's the class that gets applied to the otherwise white space.
	 * @param placeholder
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setPlaceholder(String placeholder) {
		sortableBehavior.setPlaceholder(placeholder);
		return this;
	}
	
	/**If set to true, the item will be reverted to its new DOM position with 
	 * a smooth animation.
	 * @param revert
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setRevert(SortableRevert revert) {
		sortableBehavior.setRevert(revert);
		return this;
	}
	
	/**If set to true, the page scrolls when coming to an edge.
	 * @param scroll
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setScroll(boolean scroll) {
		sortableBehavior.setScroll(scroll);
		return this;
	}

	/**Defines how near the mouse must be to an edge to start scrolling.
	 * @param scrollSensitivity
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setScrollSensitivity(int scrollSensitivity) {
		sortableBehavior.setScrollSensitivity(scrollSensitivity);
		return this;
	}
	
	/**The speed at which the window should scroll once the mouse pointer gets 
	 * within the scrollSensitivity distance.
	 * @param scrollSpeed
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setScrollSpeed(int scrollSpeed) {
		sortableBehavior.setScrollSpeed(scrollSpeed);
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
	public SortableAjaxBehavior<E>  setTolerance(ToleranceEnum tolerance) {
		sortableBehavior.setTolerance(tolerance);
		return this;
	}

	/**Set's Z-index for element/helper while being sorted.
	 * @param zIndex
	 * @return instance of the current behavior
	 */
	public SortableAjaxBehavior<E>  setZIndex(int zIndex) {
		sortableBehavior.setZIndex(zIndex);
		return this;
	}
	
	/*---- Methods section ----*/
	/**Method to cancel
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement cancel() {
		return sortableBehavior.cancel();
	}

   /**Method to cancel within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void cancel(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.cancel(ajaxRequestTarget);
	}
	
	/**Method to destroy
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return sortableBehavior.destroy();
	}
	
	/**Method to destroy within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.destroy(ajaxRequestTarget);
	}
	
	/**Method to disable
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return sortableBehavior.disable();
	}
	
	/**Method to disable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.disable(ajaxRequestTarget);
	}
	
	/**Method to enable
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return sortableBehavior.enable();
	}
	
	/**Method to enable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.enable(ajaxRequestTarget);
	}
	
	/**Method to refresh
	 * @return the associated JsStatement
	 */
	public JsStatement refresh() {
		return sortableBehavior.refresh();
	}
	
	/**Method to refresh within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.refresh(ajaxRequestTarget);
	}
	
	/**Method to refresh positions
	 * @return the associated JsStatement
	 */
	public JsStatement refreshPositions() {
		return sortableBehavior.refreshPositions();
	}
	
	/**Method to refresh positions within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void refreshPositions(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.refreshPositions();
	}
	
	/**Method to serialize (in default mode)
	 * @return the associated JsStatement
	 */
	public JsStatement serialize() {
		return sortableBehavior.serialize();
	}
	
	/**Method to serialize (in default mode) within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void serialize(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.serialize(ajaxRequestTarget);
	}
	
	/**Method to serializes the sortable's item id's into an array of string
	 * @return the associated JsStatement
	 */
	public JsStatement toArray() {
		return sortableBehavior.toArray();
	}
	
	/**Method to returns the .ui-sortable  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return sortableBehavior.widget();
	}

	/**Method to returns the .ui-sortable  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		sortableBehavior.widget(ajaxRequestTarget);
	}
}
