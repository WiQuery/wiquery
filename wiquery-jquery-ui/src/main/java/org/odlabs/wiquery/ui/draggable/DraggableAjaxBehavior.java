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
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.Request;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.core.resources.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.AxisEnum;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior.SnapModeEnum;

/**
 * <p>
 * Sets the attached component draggable and receives ajax event when dragging has
 * stopped.
 * </p>
 * 
 * <li>When the drag stops, {@link #onStop(Component, AjaxRequestTarget)} is called by an
 * Ajax request.</li> <li>When the drag starts,
 * {@link #onStart(Component, AjaxRequestTarget)} is called by an Ajax request.</li> <li>
 * During the drag, when mouse movement occurs,
 * {@link #onDrag(Component, AjaxRequestTarget)} is called by an Ajax request. Be careful
 * with this as it could cause a lot of ajax requests.</li>
 * <ul>
 * </p>
 * 
 * @author Tauren Mills
 * @author Julien Roche
 * @author Ernesto Reinaldo Barreiro
 * @since 1.0
 */
public abstract class DraggableAjaxBehavior extends WiQueryAbstractAjaxBehavior
{
	public enum DraggableEvent
	{
		DRAG,
		START,
		STOP;
	}

	/**
	 * Scope for the revert option
	 * 
	 * @author Julien Roche
	 * 
	 */
	private class DraggableRevertScope extends JsScope
	{
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = -4247629626060847839L;

		// Properties
		private CharSequence javascript;

		/**
		 * Default constructor
		 */
		public DraggableRevertScope(CharSequence javascript)
		{
			super("dropped");
			this.javascript = javascript;
		}

		@Override
		protected void execute(JsScopeContext scopeContext)
		{
			scopeContext.append(javascript);
		}
	}

	/**
	 * We override the behavior to deny the access of critical methods (example, we don't
	 * want that the end user specify a drag event, because the
	 * {@link DraggableAjaxBehavior} has got his own !!)
	 * 
	 */
	private class InnerDraggableBehavior extends DraggableBehavior
	{
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = 5587258236214715234L;

		@Override
		public void renderHead(Component component, IHeaderResponse response)
		{
			super.renderHead(component, response);
			response.renderJavaScriptReference(wiQueryDraggableJs);
			DraggableAjaxBehavior.this.renderHead(component, response);
		}

		/**
		 * For framework internal use only.
		 */
		private Options getInnerOptions()
		{
			return super.getOptions();
		}

		@Override
		protected Options getOptions()
		{
			throw new UnsupportedOperationException(
				"You can't call this method into the DraggableAjaxBehavior");
		}

		@Override
		public DraggableBehavior setDragEvent(JsScopeUiEvent drag)
		{
			if (callbacks.contains(DraggableEvent.DRAG))
			{
				throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
			}

			return super.setDragEvent(drag);
		}

		/**
		 * For framework internal use only.
		 */
		private DraggableBehavior setInnerDragEvent(JsScopeUiEvent drag)
		{
			return super.setDragEvent(drag);
		}

		/**
		 * For framework internal use only.
		 */
		private DraggableBehavior setInnerStartEvent(JsScopeUiEvent start)
		{
			return super.setStartEvent(start);
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerStopEvent(JsScopeUiEvent drop)
		{
			super.setStopEvent(drop);
		}

		@Override
		public DraggableBehavior setRevert(DraggableRevert revert)
		{
			getInnerOptions().put("wiRevert", revert);
			return this;
		}

		@Override
		public DraggableBehavior setStartEvent(JsScopeUiEvent start)
		{
			if (callbacks.contains(DraggableEvent.START))
			{
				throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
			}

			return super.setStartEvent(start);
		}

		@Override
		public DraggableBehavior setStopEvent(JsScopeUiEvent drop)
		{
			if (callbacks.contains(DraggableEvent.STOP))
			{
				throw new UnsupportedOperationException(
					"You can't call this method into the DraggableAjaxBehavior");
			}

			return super.setStopEvent(drop);
		}

		@Override
		public JsStatement statement()
		{
			if (callbacks.contains(DraggableEvent.STOP))
			{
				draggableBehavior.setInnerStopEvent(new JsScopeUiEvent()
				{
					private static final long serialVersionUID = 1L;

					@Override
					protected void execute(JsScopeContext scopeContext)
					{
						StringBuffer javascript = new StringBuffer();
						javascript
							.append("var isInvalid = $.ui.draggable._dragElementDroppedWasInvalid(this);");

						if (!enableAjaxOnInvalid)
						{
							// We must insert a test to detect the invalid state
							javascript.append("if(!isInvalid){");
						}
						// use parent machinery for building call-back URL.
						javascript.append(getCallbackStopEventScript());

						if (!enableAjaxOnInvalid)
						{
							javascript.append("}");
						}

						scopeContext.append(javascript);
					}

				});
			}

			if (callbacks.contains(DraggableEvent.START))
			{
				draggableBehavior.setInnerStartEvent(new JsScopeUiEvent()
				{
					private static final long serialVersionUID = 1L;

					@Override
					protected void execute(JsScopeContext scopeContext)
					{
						scopeContext.append(getCallbackScript(DraggableEvent.START.toString()
							.toLowerCase()));
					}
				});
			}

			if (callbacks.contains(DraggableEvent.DRAG))
			{
				draggableBehavior.setInnerDragEvent(new JsScopeUiEvent()
				{
					private static final long serialVersionUID = 1L;

					@Override
					protected void execute(JsScopeContext scopeContext)
					{
						scopeContext.append(getCallbackScript(DraggableEvent.DRAG.toString()
							.toLowerCase()));
					}
				});
			}

			if (enableAjaxOnInvalid)
			{
				draggableBehavior.getInnerOptions().put(
					"revert",
					new DraggableRevertScope(
						"return $.ui.draggable._dragElementWasDropped(this, dropped);"));
			}

			return super.statement();
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 3L;

	/** ResourceReference for the wiQuery Draggable javascript */
	public static final WiQueryJavaScriptResourceReference wiQueryDraggableJs =
		new WiQueryJavaScriptResourceReference(DraggableJavaScriptResourceReference.class,
			"wiquery-draggable.js");

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
	 * If true, when a drag is invalid (see
	 * {@link DraggableBehavior#setRevert(DraggableRevert)}), an ajax request will be send
	 */
	private boolean enableAjaxOnInvalid;

	/**
	 * Required callbacks
	 */
	private Set<DraggableEvent> callbacks;

	/**
	 * Default constructor
	 */
	public DraggableAjaxBehavior()
	{
		this(true, DraggableEvent.values());
	}

	/**
	 * Constructor
	 * 
	 * @param enableAjaxOnInvalid
	 *            If true, when a drag is invalid an ajax request will be send. Otherwise,
	 *            {@link #onStop(Component, AjaxRequestTarget)},
	 *            {@link #onValid(Component, AjaxRequestTarget)} and
	 *            {@link #onInvalid(Component, AjaxRequestTarget)} will be never called.
	 */
	public DraggableAjaxBehavior(boolean enableAjaxOnInvalid)
	{
		this(enableAjaxOnInvalid, DraggableEvent.values());
	}

	/**
	 * Constructor
	 * 
	 * @param enableAjaxOnInvalid
	 *            If true, when a drag is invalid an ajax request will be send. Otherwise,
	 *            {@link #onStop(Component, AjaxRequestTarget)},
	 *            {@link #onValid(Component, AjaxRequestTarget)} and
	 *            {@link #onInvalid(Component, AjaxRequestTarget)} will be never called.
	 * @param callbacks
	 *            Ajax callbacks to enable
	 */
	public DraggableAjaxBehavior(boolean enableAjaxOnInvalid, DraggableEvent... callbacks)
	{
		super();
		this.enableAjaxOnInvalid = enableAjaxOnInvalid;
		draggableBehavior = new InnerDraggableBehavior();
		this.callbacks = new HashSet<DraggableEvent>(Arrays.asList(callbacks));
	}

	/**
	 * Constructor
	 * 
	 * @param callbacks
	 *            Ajax callbacks to enable
	 */
	public DraggableAjaxBehavior(DraggableEvent... callbacks)
	{
		this(true, callbacks);
	}

	/**
	 * @return the standard draggable JavaScript behavior
	 */
	public DraggableBehavior getDraggableBehavior()
	{
		return draggableBehavior;
	}

	@Override
	protected void onBind()
	{
		super.onBind();
		getComponent().add(draggableBehavior);
	}

	/**
	 * For framework internal use only.
	 */
	public final void onDrag(AjaxRequestTarget target)
	{
		Request request = this.getComponent().getRequest();
		DraggableEvent dragEvent =
			DraggableEvent.valueOf(request.getQueryParameters().getParameterValue(DRAG_TYPE)
				.toString().toUpperCase());

		switch (dragEvent)
		{
			case DRAG:
				onDrag(getComponent(), target);
				break;

			case START:
				onStart(getComponent(), target);
				break;

			case STOP:
				onStop(getComponent(), target);

				if (Boolean.valueOf(request.getQueryParameters().getParameterValue(DRAG_STATUS)
					.toString())
					&& enableAjaxOnInvalid)
				{
					onInvalid(getComponent(), target);

				}
				else
				{
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
	public abstract void onDrag(Component draggedComponent, AjaxRequestTarget ajaxRequestTarget);

	/**
	 * onInvalid is called back when the drag stop event has been fired and if the drag
	 * was invalid.
	 * 
	 * The event {@link #onStop(Component, AjaxRequestTarget)} is called before this
	 * event.
	 * 
	 * Override this method to listen the invalid event
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public void onInvalid(Component draggedComponent, AjaxRequestTarget ajaxRequestTarget)
	{
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
	public abstract void onStart(Component draggedComponent, AjaxRequestTarget ajaxRequestTarget);

	/**
	 * onStop is called back when the drag stop event has been fired.
	 * 
	 * This event is called before {@link #onValid(Component, AjaxRequestTarget)} and
	 * before {@link #onInvalid(Component, AjaxRequestTarget)}
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onStop(Component draggedComponent, AjaxRequestTarget ajaxRequestTarget);

	/**
	 * onInvalid is called back when the drag stop event has been fired and if the drag
	 * was valid.
	 * 
	 * The event {@link #onStop(Component, AjaxRequestTarget)} is called before this
	 * event.
	 * 
	 * Override this method to listen the invalid event
	 * 
	 * @param draggedComponent
	 *            the dragged {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public void onValid(Component draggedComponent, AjaxRequestTarget ajaxRequestTarget)
	{
		// To override
	}

	@Override
	protected void respond(AjaxRequestTarget target)
	{
		onDrag(target);
	}

	@Override
	public JsStatement statement()
	{
		return draggableBehavior.statement();
	}

	/**
	 * We use standard AbstractDefaultAjaxBehavior machinery to generate script: what way
	 * all the logic regarding IAjaxCallDecorator or indicatorId will be added to the
	 * generated script. This makes draggable behavior more compatible with standard
	 * Wicket's AJAX call-backs.
	 * 
	 * @return
	 */
	protected CharSequence getCallbackStopEventScript()
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl() + "&" + DRAG_TYPE + "="
			+ DraggableEvent.STOP.toString().toLowerCase() + "&" + DRAG_STATUS + "='+isInvalid");
	}

	/**
	 * We use standard AbstractDefaultAjaxBehavior machinery to generate script: what way
	 * all the logic regarding IAjaxCallDecorator or indicatorId will be added to the
	 * generated script. This makes draggable behavior more compatible with standard
	 * Wicket's AJAX call-backs.
	 * 
	 * @param dragType
	 * @return
	 */
	protected CharSequence getCallbackScript(String dragType)
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl() + "&" + DRAG_TYPE + "="
			+ dragType + "'");
	}

	// //////////////////////////////////////////////////////////////////////////
	// // SHORTCUTS ////
	// //////////////////////////////////////////////////////////////////////////
	/**
	 * If set to false, will prevent the ui-draggable class from being added. This may be
	 * desired as a performance optimization when calling .draggable() init on many
	 * hundreds of elements.
	 * 
	 * @param addClasses
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setAddClasses(boolean addClasses)
	{
		draggableBehavior.setAddClasses(addClasses);
		return this;
	}

	/**
	 * @return the addClasses option
	 */
	public boolean isAddClasses()
	{
		return draggableBehavior.isAddClasses();
	}

	/**
	 * The element passed to or selected by the appendTo option will be used as the
	 * draggable helper's container during dragging. By default, the helper is appended to
	 * the same container as the draggable.
	 * 
	 * @param appendTo
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setAppendTo(String appendTo)
	{
		draggableBehavior.setAppendTo(appendTo);
		return this;
	}

	/**
	 * @return the appendTo option value
	 */
	public String getAppendTo()
	{
		return draggableBehavior.getAppendTo();
	}

	/**
	 * Constrains dragging to either the horizontal (x) or vertical (y) axis. Possible
	 * values: 'x', 'y'.
	 * 
	 * @param axis
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setAxis(AxisEnum axis)
	{
		draggableBehavior.setAxis(axis);
		return this;
	}

	/**
	 * @return the axis option value
	 */
	public AxisEnum getAxis()
	{
		return draggableBehavior.getAxis();
	}

	/**
	 * Set's the prevent selecting if you start on elements matching the selector
	 * 
	 * @param cancel
	 *            Selector (default : ':input,option')
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setCancel(String cancel)
	{
		draggableBehavior.setCancel(cancel);
		return this;
	}

	/**
	 * @return the cancel option value
	 */
	public String getCancel()
	{
		return draggableBehavior.getCancel();
	}

	/**
	 * Allows the draggable to be dropped onto the specified sortables. If this option is
	 * used (helper must be set to 'clone' in order to work flawlessly), a draggable can
	 * be dropped onto a sortable list and then becomes part of it.
	 * 
	 * @param connectToSortable
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setConnectToSortable(String connectToSortable)
	{
		draggableBehavior.setConnectToSortable(connectToSortable);
		return this;
	}

	/**
	 * @return the connectToSortable option value
	 */
	public String getConnectToSortable()
	{
		return draggableBehavior.getConnectToSortable();
	}

	/**
	 * Set's the constrains dragging to within the bounds of the specified element or
	 * region. Possible string values: 'parent', 'document', 'window', [x1, y1, x2, y2]
	 * 
	 * @param containment
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setContainment(DraggableContainment containment)
	{
		draggableBehavior.setContainment(containment);
		return this;
	}

	/**
	 * @return the containment option
	 */
	public DraggableContainment getContainment()
	{
		return draggableBehavior.getContainment();
	}

	/**
	 * Set the css cursor during the drag operation.
	 * 
	 * @param cursor
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setCursor(String cursor)
	{
		draggableBehavior.setCursor(cursor);
		return this;
	}

	/**
	 * @return the cursor option value
	 */
	public String getCursor()
	{
		return draggableBehavior.getCursor();
	}

	/**
	 * Moves the dragging helper so the cursor always appears to drag from the same
	 * position. Coordinates can be given as a hash using a combination of one or two
	 * keys: { top, left, right, bottom }.
	 * 
	 * @param cusorAt
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setCursorAt(ListItemOptions<DraggableCursorAt> cusorAt)
	{
		draggableBehavior.setCursorAt(cusorAt);
		return this;
	}

	/**
	 * @return the cursorAt option value
	 */
	public ListItemOptions<DraggableCursorAt> getCursorAtComplex()
	{
		return draggableBehavior.getCursorAtComplex();
	}

	/**
	 * Time in milliseconds after mousedown until dragging should start. This option can
	 * be used to prevent unwanted drags when clicking on an element.
	 * 
	 * @param delay
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setDelay(int delay)
	{
		draggableBehavior.setDelay(delay);
		return this;
	}

	/**
	 * @return the delay option value
	 */
	public int getDelay()
	{
		return draggableBehavior.getDelay();
	}

	/**
	 * Disables (true) or enables (false) the draggable. Can be set when initialising
	 * (first creating) the draggable.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setDisabled(boolean disabled)
	{
		draggableBehavior.setDisabled(disabled);
		return this;
	}

	/**
	 * @return the disabled option
	 */
	public boolean isDisabled()
	{
		return draggableBehavior.isDisabled();
	}

	/**
	 * Set's the distance in pixels after mousedown the mouse must move before dragging
	 * should start. This option can be used to prevent unwanted drags when clicking on an
	 * element.
	 * 
	 * @param distance
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setDistance(int distance)
	{
		draggableBehavior.setDistance(distance);
		return this;
	}

	/**
	 * @return the distance option value
	 */
	public int getDistance()
	{
		return draggableBehavior.getDistance();
	}

	/**
	 * Snaps the dragging helper to a grid, every x and y pixels. Array values: [x, y]
	 * 
	 * @param x
	 * @param y
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setGrid(int x, int y)
	{
		draggableBehavior.setGrid(x, y);
		return this;
	}

	/**
	 * @return the grid option value
	 */
	public ICollectionItemOptions getGrid()
	{
		return draggableBehavior.getGrid();
	}

	/**
	 * Restricts sort start click to the specified element.
	 * 
	 * @param handle
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setHandle(String handle)
	{
		draggableBehavior.setHandle(handle);
		return this;
	}

	/**
	 * @return the handle option value
	 */
	public String getHandle()
	{
		return draggableBehavior.getHandle();
	}

	/**
	 * Allows for a helper element to be used for dragging display. Possible values:
	 * 'original', 'clone', Function. If a function is specified, it must return a
	 * DOMElement.
	 * 
	 * @param helper
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setHelper(DraggableHelper helper)
	{
		draggableBehavior.setHelper(helper);
		return this;
	}

	/**
	 * @return the helper option
	 */
	public DraggableHelper getHelper()
	{
		return draggableBehavior.getHelper();
	}

	/**
	 * Prevent iframes from capturing the mousemove events during a drag. Useful in
	 * combination with cursorAt, or in any case, if the mouse cursor is not over the
	 * helper. If set to true, transparent overlays will be placed over all iframes on the
	 * page. If a selector is supplied, the matched iframes will have an overlay placed
	 * over them.
	 * 
	 * @param iframeFix
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setIframeFix(DraggableIframeFix iframeFix)
	{
		draggableBehavior.setIframeFix(iframeFix);
		return this;
	}

	/**
	 * @return the iframeFix option
	 */
	public DraggableIframeFix getIframeFix()
	{
		return draggableBehavior.getIframeFix();
	}

	/**
	 * Set's the opacity for the helper while being dragged.
	 * 
	 * @param opacity
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setOpacity(float opacity)
	{
		draggableBehavior.setOpacity(opacity);
		return this;
	}

	/**
	 * @return the opacity option
	 */
	public float getOpacity()
	{
		return draggableBehavior.getOpacity();
	}

	/**
	 * If set to true, all droppable positions are calculated on every mousemove. Caution:
	 * This solves issues on highly dynamic pages, but dramatically decreases performance.
	 * 
	 * @param refreshPositions
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setRefreshPositions(boolean refreshPositions)
	{
		draggableBehavior.setRefreshPositions(refreshPositions);
		return this;
	}

	/**
	 * @return the refreshPositions option
	 */
	public boolean isRefreshPositions()
	{
		return draggableBehavior.isRefreshPositions();
	}

	/**
	 * If set to true, the element will return to its start position when dragging stops.
	 * Possible string values: 'valid', 'invalid'. If set to invalid, revert will only
	 * occur if the draggable has not been dropped on a droppable. For valid, it's the
	 * other way around.
	 * 
	 * @param revert
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setRevert(DraggableRevert revert)
	{
		draggableBehavior.setRevert(revert);
		return this;
	}

	/**
	 * @return the revert option
	 */
	public DraggableRevert getRevert()
	{
		return draggableBehavior.getRevert();
	}

	/**
	 * Set's the duration of the revert animation, in milliseconds. Ignored if revert is
	 * false.
	 * 
	 * @param revertDuration
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setRevertDuration(int revertDuration)
	{
		draggableBehavior.setRevertDuration(revertDuration);
		return this;
	}

	/**
	 * @return the revertDuration option
	 */
	public int getRevertDuration()
	{
		return draggableBehavior.getRevertDuration();
	}

	/**
	 * Used to group sets of draggable and droppable items, in addition to droppable's
	 * accept option. A draggable with the same scope value as a droppable will be
	 * accepted by the droppable.
	 * 
	 * @param scope
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setScope(String scope)
	{
		draggableBehavior.setScope(scope);
		return this;
	}

	/**
	 * @return the scope option
	 */
	public String getScope()
	{
		return draggableBehavior.getScope();
	}

	/**
	 * If set to true, container auto-scrolls while dragging.
	 * 
	 * @param scroll
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setScroll(boolean scroll)
	{
		draggableBehavior.setScroll(scroll);
		return this;
	}

	/**
	 * @return the scroll option
	 */
	public boolean isScroll()
	{
		return draggableBehavior.isScroll();
	}

	/**
	 * Set's the distance in pixels from the edge of the viewport after which the viewport
	 * should scroll. Distance is relative to pointer, not the draggable.
	 * 
	 * @param scrollSensitivity
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setScrollSensitivity(int scrollSensitivity)
	{
		draggableBehavior.setScrollSensitivity(scrollSensitivity);
		return this;
	}

	/**
	 * @return the scrollSensitivity option
	 */
	public int getScrollSensitivity()
	{
		return draggableBehavior.getScrollSensitivity();
	}

	/**
	 * Set's speed at which the window should scroll once the mouse pointer gets within
	 * the scrollSensitivity distance.
	 * 
	 * @param scrollSpeed
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setScrollSpeed(int scrollSpeed)
	{
		draggableBehavior.setScrollSpeed(scrollSpeed);
		return this;
	}

	/**
	 * @return the scrollSpeed option
	 */
	public int getScrollSpeed()
	{
		return draggableBehavior.getScrollSpeed();
	}

	/**
	 * If set to a selector or to true (equivalent to '.ui-draggable'), the draggable will
	 * snap to the edges of the selected elements when near an edge of the element.
	 * 
	 * @param snap
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setSnap(DraggableSnap snap)
	{
		draggableBehavior.setSnap(snap);
		return this;
	}

	/**
	 * @return the snap option
	 */
	public DraggableSnap getSnap()
	{
		return draggableBehavior.getSnap();
	}

	/**
	 * Sets the edges of snap elements the draggable will snap to. Ignored if snap is
	 * false. Possible values: 'inner', 'outer', 'both'
	 * 
	 * @param snapMode
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setSnapMode(SnapModeEnum snapMode)
	{
		draggableBehavior.setSnapMode(snapMode);
		return this;
	}

	/**
	 * Returns the snapMode option
	 */
	public SnapModeEnum getSnapMode()
	{
		return draggableBehavior.getSnapMode();
	}

	/**
	 * Set's distance in pixels from the snap element edges at which snapping should
	 * occur. Ignored if snap is false.
	 * 
	 * @param snapTolerance
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setSnapTolerance(int snapTolerance)
	{
		draggableBehavior.setSnapTolerance(snapTolerance);
		return this;
	}

	/**
	 * @return the snapTolerance option
	 */
	public int getSnapTolerance()
	{
		return draggableBehavior.getSnapTolerance();
	}

	/**
	 * Controls the z-Index of the defined group (key 'group' in the hash, accepts jQuery
	 * selector) automatically, always brings to front the dragged item. Very useful in
	 * things like window managers. Optionally, a 'min' key can be set, so the zIndex
	 * cannot go below that value.
	 * 
	 * @param stack
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setStack(String stack)
	{
		draggableBehavior.setStack(stack);
		return this;
	}

	/**
	 * @returns the stack option
	 */
	public String getStack()
	{
		return draggableBehavior.getStack();
	}

	/**
	 * Set's the starting z-index
	 * 
	 * @param zIndex
	 * @return instance of the current behavior
	 */
	public DraggableAjaxBehavior setZIndex(int zIndex)
	{
		draggableBehavior.setZIndex(zIndex);
		return this;
	}

	/**
	 * @return the starting z-index
	 */
	public int getZIndex()
	{
		return draggableBehavior.getZIndex();
	}

	/*---- Methods section ---*/

	/**
	 * Method to destroy the draggable This will return the element back to its pre-init
	 * state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return draggableBehavior.destroy();
	}

	/**
	 * Method to destroy the draggable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		draggableBehavior.destroy(ajaxRequestTarget);
	}

	/**
	 * Method to disable the draggable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return draggableBehavior.disable();
	}

	/**
	 * Method to disable the draggable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget)
	{
		draggableBehavior.disable(ajaxRequestTarget);
	}

	/**
	 * Method to enable the draggable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return draggableBehavior.enable();
	}

	/**
	 * Method to enable the draggable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		draggableBehavior.enable(ajaxRequestTarget);
	}

	/**
	 * Method to returns the .ui-draggable element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return draggableBehavior.widget();
	}

	/**
	 * Method to returns the .ui-draggable element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		draggableBehavior.widget(ajaxRequestTarget);
	}
}
