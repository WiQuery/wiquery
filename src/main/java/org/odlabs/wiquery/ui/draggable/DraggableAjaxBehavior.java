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
package org.odlabs.wiquery.ui.draggable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.Request;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * <p>
 * Sets the attached component draggable and receives ajax event when 
 * dragging has stopped.
 * </p>
 * 
 * <li>When the drag stops, {@link #onStop(Component, AjaxRequestTarget)} is
 * called by an Ajax request.</li>
 * <li>When the drag starts, {@link #onStart(Component, AjaxRequestTarget)} is
 * called by an Ajax request.</li>
 * <li>During the drag, when mouse movement occurs, {@link #onDrag(Component, AjaxRequestTarget)} is
 * called by an Ajax request. Be careful with this as it could cause a lot of ajax requests.</li>
 * <ul>
 * </p>
 * 
 * @author Tauren Mills
 * @since 1.0
 */
public abstract class DraggableAjaxBehavior extends AbstractDefaultAjaxBehavior {
	public enum DraggableEvent {
		DRAG,
		START,
		STOP;
	}
	
	/**
	 * Scope for the revert option
	 * @author Julien Roche
	 *
	 */
	private class DraggableRevertScope extends JsScope {
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = -4247629626060847839L;
		
		// Properties
		private CharSequence javascript;
		
		/**
		 * Default constructor
		 */
		public DraggableRevertScope(CharSequence javascript) {
			super("dropped");
			this.javascript = javascript;
		}

		/* (non-Javadoc)
		 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
		 */
		@Override
		protected void execute(JsScopeContext scopeContext) {
			scopeContext.append(javascript);
		}		
	}
	
	/**
	 * We override the behavior to deny the access of critical methods (example,
	 * we don't want that the end user specify a drag event, because the
	 * {@link DraggableAjaxBehavior} has got his own !!)
	 * 
	 */
	private class InnerDraggableBehavior extends DraggableBehavior {
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = 5587258236214715234L;
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.draggable.DraggableBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
		 */
		@Override
		public void contribute(WiQueryResourceManager wiQueryResourceManager) {
			super.contribute(wiQueryResourceManager);
			wiQueryResourceManager.addJavaScriptResource(wiQueryDraggableJs);
			DraggableAjaxBehavior.this.contribute(wiQueryResourceManager);
		}

		/**
		 * For framework internal use only.
		 */
		private Options getInnerOptions() {
			return super.getOptions();
		}
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.draggable.DraggableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.draggable.DraggableBehavior#setDragEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public DraggableBehavior setDragEvent(JsScopeUiEvent drag) {
			if(callbacks.contains(DraggableEvent.DRAG)){
				throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
			}
			
			return super.setDragEvent(drag);
		}
		
		/**
		 * For framework internal use only.
		 */
		private DraggableBehavior setInnerDragEvent(JsScopeUiEvent drag) {
			return super.setDragEvent(drag);
		}

		/**
		 * For framework internal use only.
		 */
		private DraggableBehavior setInnerStartEvent(JsScopeUiEvent start) {
			return super.setStartEvent(start);
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerStopEvent(JsScopeUiEvent drop) {
			super.setStopEvent(drop);
		}
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.draggable.DraggableBehavior#setRevert(org.odlabs.wiquery.ui.draggable.DraggableRevert)
		 */
		@Override
		public DraggableBehavior setRevert(DraggableRevert revert) {
			getInnerOptions().put("wiRevert", revert);
			return this;
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.draggable.DraggableBehavior#setStartEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public DraggableBehavior setStartEvent(JsScopeUiEvent start) {
			if(callbacks.contains(DraggableEvent.START)){
				throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
			}
			
			return super.setStartEvent(start);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.draggable.DraggableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public DraggableBehavior setStopEvent(JsScopeUiEvent drop) {
			if(callbacks.contains(DraggableEvent.STOP)){
				throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
			}
			
			return super.setStopEvent(drop);
		}
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3L;
	
	/** ResourceReference for the wiQuery Draggable javascript */
	public static final JavascriptResourceReference wiQueryDraggableJs = 
		new JavascriptResourceReference(
				DraggableJavaScriptResourceReference.class, 
				"wiquery-draggable.min.js");

	/** Drag type into the request */
	protected static final String DRAG_TYPE = "dragType";
	
	/** Drag status (valid / invalid) */
	protected static final String DRAG_STATUS = "dragStatus";
	
	// Properties
	/**
	 * Adding the standard draggable JavaScript behavior
	 */
	private InnerDraggableBehavior draggableBehavior;

	/**
	 * If true, when a drag is invalid (see {@link DraggableBehavior#setRevert(DraggableRevert)}), 
	 * an ajax request will be send
	 */
	private boolean enableAjaxOnInvalid;
	
	/**
	 * Required callbacks
	 */
	private Set<DraggableEvent> callbacks;
	
	/**
	 * Default constructor
	 */
	public DraggableAjaxBehavior() {
		this(true, DraggableEvent.values());
	}
	
	/**
	 * Constructor
	 * @param enableAjaxOnInvalid If true, when a drag is invalid 
	 * an ajax request will be send. Otherwise, {@link #onStop(Component, AjaxRequestTarget)},
	 * {@link #onValid(Component, AjaxRequestTarget)} and 
	 * {@link #onInvalid(Component, AjaxRequestTarget)} will be never called.
	 */
	public DraggableAjaxBehavior(boolean enableAjaxOnInvalid) {
		this(enableAjaxOnInvalid, DraggableEvent.values());
	}

	/**
	 * Constructor
	 * @param enableAjaxOnInvalid If true, when a drag is invalid 
	 * an ajax request will be send. Otherwise, {@link #onStop(Component, AjaxRequestTarget)},
	 * {@link #onValid(Component, AjaxRequestTarget)} and 
	 * {@link #onInvalid(Component, AjaxRequestTarget)} will be never called.
	 * @param callbacks Ajax callbacks to enable
	 */
	public DraggableAjaxBehavior(boolean enableAjaxOnInvalid,
			DraggableEvent... callbacks) {
		super();
		this.enableAjaxOnInvalid = enableAjaxOnInvalid;
		draggableBehavior = new InnerDraggableBehavior();
		this.callbacks = new HashSet<DraggableEvent>(Arrays.asList(callbacks));
	}

	/**
	 * Constructor
	 * @param callbacks Ajax callbacks to enable
	 */
	public DraggableAjaxBehavior(DraggableEvent... callbacks) {
		this(true, callbacks);
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.draggable.DraggableBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 * Override this method to add additionnal resources
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		// To override
	}

	/**
	 * @return the drag callback
	 */
	private String dragCallback(DraggableEvent event) {
		return "wicketAjaxGet('" + getCallbackUrl(true)
				+ "&" + DRAG_TYPE + "=" + event.toString().toLowerCase()
				+ "',null,null, function() {return true;})";
	}

	/**
	 * @return the standard draggable JavaScript behavior
	 */
	public DraggableBehavior getDraggableBehavior() {
		return draggableBehavior;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(draggableBehavior);
		
		if(callbacks.contains(DraggableEvent.STOP)){
			draggableBehavior.setInnerStopEvent(new JsScopeUiEvent() {
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
					StringBuffer javascript = new StringBuffer();
					javascript.append("var isInvalid = $.ui.draggable._dragElementDroppedWasInvalid(this);");
					
					if(!enableAjaxOnInvalid){
						// We must insert a test to detect the invalid state
						javascript.append("if(!isInvalid){");
					}
					
					javascript.append("wicketAjaxGet('" + getCallbackUrl(true));
					javascript.append("&" + DRAG_TYPE + "=" + DraggableEvent.STOP.toString().toLowerCase());
					javascript.append("&" + DRAG_STATUS + "='+isInvalid");
					javascript.append(",null,null, function() {return true;})");
					
					if(!enableAjaxOnInvalid){
						javascript.append("}");
					}
					
					scopeContext.append(javascript);
				}
	
			});
		}
		
		if(callbacks.contains(DraggableEvent.START)){
			draggableBehavior.setInnerStartEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;
				
				 /* (non-Javadoc)
				 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {
					scopeContext.append(dragCallback(DraggableEvent.START));
				}
			});
		}
		
		if(callbacks.contains(DraggableEvent.DRAG)){
			draggableBehavior.setInnerDragEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;
				
				/* (non-Javadoc)
				 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {
					scopeContext.append(dragCallback(DraggableEvent.DRAG));
				}
			});
		}
		
		if(enableAjaxOnInvalid){
			draggableBehavior.getInnerOptions().put(
					"revert", 
					new DraggableRevertScope(
							"return $.ui.draggable._dragElementWasDropped(this, dropped);"));
		}
	}
	
	/**
	 * For framework internal use only.
	 */
	public final void onDrag(AjaxRequestTarget target) {
		Request request = this.getComponent().getRequest();
		DraggableEvent dragEvent =  DraggableEvent.valueOf(
				request.getParameter(DRAG_TYPE).toUpperCase());
		
		switch(dragEvent){
			case DRAG:
				onDrag(getComponent(), target);
				break;
				
			case START:
				onStart(getComponent(), target);
				break;
				
			case STOP:
				onStop(getComponent(), target);
				
				if(Boolean.valueOf(request.getParameter(DRAG_STATUS)) 
						&& enableAjaxOnInvalid){
					onInvalid(getComponent(), target);
					
				} else {
					onValid(getComponent(), target);
				}
				
				break;
		}
	}
	
	/**
	 * onDrag is called back when the drag event has been fired.
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onDrag(Component component, AjaxRequestTarget ajaxRequestTarget);

	/**
	 * onInvalid is called back when the drag stop event has been fired and if
	 * the drag was invalid.
	 * 
	 * The event {@link #onStop(Component, AjaxRequestTarget)} is called before
	 * this event.
	 * 
	 * Override this method to listen the invalid event
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public void onInvalid(Component component, AjaxRequestTarget ajaxRequestTarget) {
		// To override
	}
	
	/**
	 * onStart is called back when the drag start event has been fired.
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onStart(Component component, AjaxRequestTarget ajaxRequestTarget);
	
	/**
	 * onStop is called back when the drag stop event has been fired.
	 * 
	 * This event is called before {@link #onValid(Component, AjaxRequestTarget)}
	 * and before {@link #onInvalid(Component, AjaxRequestTarget)}
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onStop(Component draggedComponent,
			AjaxRequestTarget ajaxRequestTarget);

	/**
	 * onInvalid is called back when the drag stop event has been fired and if
	 * the drag was valid.
	 * 
	 * The event {@link #onStop(Component, AjaxRequestTarget)} is called before
	 * this event.
	 * 
	 * Override this method to listen the invalid event
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public void onValid(Component component, AjaxRequestTarget ajaxRequestTarget) {
		// To override
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		onDrag(target);
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	protected JsStatement statement() {
		return draggableBehavior.statement();
	}
}
