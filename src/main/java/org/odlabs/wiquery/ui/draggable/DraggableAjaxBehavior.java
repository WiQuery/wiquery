package org.odlabs.wiquery.ui.draggable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
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
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2L;
	
	/** Drag type into the request */
	private static final String DRAG_TYPE = "dragType";

	// Properties
	/**
	 * Adding the standard draggable JavaScript behavior
	 */
	private InnerDraggableBehavior draggableBehavior;
	
	/**
	 * Required callbacks
	 */
	private Set<DraggableEvent> callbacks;

	/**
	 * Default constructor
	 */
	public DraggableAjaxBehavior() {
		this(DraggableEvent.values());
	}
	
	/**
	 * Constructor
	 * @param callbacks Ajax callbacks to enable
	 */
	public DraggableAjaxBehavior(DraggableEvent... callbacks) {
		super();
		draggableBehavior = new InnerDraggableBehavior();
		this.callbacks = new HashSet<DraggableEvent>(Arrays.asList(callbacks));
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
					scopeContext.append("wicketAjaxGet('" + getCallbackUrl(true)
							+ "&" + DRAG_TYPE + "=" + DraggableEvent.STOP.toString().toLowerCase()
							+ "',null,null, function() {return true;})");
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
					scopeContext.append("wicketAjaxGet('" + getCallbackUrl(true)
							+ "&" + DRAG_TYPE + "=" + DraggableEvent.START.toString().toLowerCase()
							+ "',null,null, function() {return true;})");
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
					scopeContext.append("wicketAjaxGet('" + getCallbackUrl(true)
							+ "&" + DRAG_TYPE + "=" + DraggableEvent.DRAG.toString().toLowerCase()
							+ "',null,null, function() {return true;})");
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache
	 * .wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		onDrag(target);
	}

	/**
	 * For framework internal use only.
	 */
	public final void onDrag(AjaxRequestTarget target) {
		DraggableEvent dragEvent =  DraggableEvent.valueOf(
				this.getComponent().getRequest().getParameter(DRAG_TYPE).toUpperCase());
		
		switch(dragEvent){
			case DRAG:
				onDrag(getComponent(), target);
				break;
				
			case START:
				onStart(getComponent(), target);
				break;
				
			case STOP:
				onStop(getComponent(), target);
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
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onStop(Component draggedComponent,
			AjaxRequestTarget ajaxRequestTarget);

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.odlabs.wiquery.ui.droppable.DraggableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
		}

		/* (non-Javadoc)
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

		/* (non-Javadoc)
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
		 * For framework internal use only.
		 */
		private DraggableBehavior setInnerStartEvent(JsScopeUiEvent start) {
			return super.setStartEvent(start);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.odlabs.wiquery.ui.droppable.DraggableBehavior#setStopEvent(org
		 * .odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public DraggableBehavior setStopEvent(JsScopeUiEvent drop) {
			if(callbacks.contains(DraggableEvent.STOP)){
				throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
			}
			
			return super.setStopEvent(drop);
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerStopEvent(JsScopeUiEvent drop) {
			super.setStopEvent(drop);
		}
	}
}
