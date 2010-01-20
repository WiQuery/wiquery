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
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.core.util.MarkupIdVisitor;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

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
 * @author Julien Roche
 * @since 1.0
 */
public abstract class SortableAjaxBehavior extends AbstractDefaultAjaxBehavior {
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(sortableBehavior);
		if(callbacks.contains(SortedEvent.RECEIVE)){
			sortableBehavior.setInnerReceiveEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;
	
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs
				 * .wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {				
					scopeContext.append("wicketAjaxGet('" + getCallbackUrl(true)
							+ "&" + SORTED_TYPE + "=" + SortedEvent.RECEIVE.toString().toLowerCase() 
							+ "&" + SORTED_INDEX + "='+$(this).find(':data(sortable-item)').index(" + SortableBehavior.UI_ITEM + ")+'"
							+ "&" + SORTED_ID + "='+ $(" + SortableBehavior.UI_ITEM + ").attr('id')" 
							+ "+'&" + SORTED_PARENT_ID + "='+ $(" + SortableBehavior.UI_SENDER + ").attr('id')" 
							+ ", null,null, function() {return true;});");
				}
	
			});
		}
		
		if(callbacks.contains(SortedEvent.REMOVE)){
			sortableBehavior.setInnerRemoveEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;
	
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs
				 * .wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {
					scopeContext.append("wicketAjaxGet('" + getCallbackUrl(true)
							+ "&" + SORTED_TYPE + "=" + SortedEvent.REMOVE.toString().toLowerCase() 
							+ "&" + SORTED_ID + "='+ $(" + SortableBehavior.UI_ITEM + ").attr('id')" 
							+ ", null,null, function() {return true;})");
				}
	
			});
		}
		
		if(callbacks.contains(SortedEvent.UPDATE)){
			sortableBehavior.setInnerUpdateEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;
	
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs
				 * .wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {
					scopeContext.append("wicketAjaxGet('" + getCallbackUrl(true)
							+ "&" + SORTED_TYPE + "=" + SortedEvent.UPDATE.toString().toLowerCase() 
							+ "&" + SORTED_INDEX + "='+$(this).find(':data(sortable-item)').index(" + SortableBehavior.UI_ITEM + ")+'"
							+ "&" + SORTED_ID + "='+ $(" + SortableBehavior.UI_ITEM + ").attr('id')" 
							+ ", null,null, function() {return true;})");
				}
	
			});
		}
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
	public abstract void onReceive(Component sortedComponent, int index, 
			Component parentSortedComponent, AjaxRequestTarget ajaxRequestTarget);

	/**
	 * OnRemove is called back when a sortable item has been dragged out from the 
	 * list and into another.
	 * @param sortedComponent the sorted {@link Component}
	 * @param ajaxRequestTarget the Ajax target
	 */
	public abstract void onRemove(Component sortedComponent,
			AjaxRequestTarget ajaxRequestTarget);

	/**
	 * For framework internal use only.
	 */
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
		Component sortedComponent = visitor.getFoundComponent();
		
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
	public abstract void onUpdate(Component sortedComponent, int index,
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
}
