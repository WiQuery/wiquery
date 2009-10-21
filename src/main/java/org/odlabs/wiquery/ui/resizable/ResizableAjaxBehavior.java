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
package org.odlabs.wiquery.ui.resizable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id: ResizableAjaxBehavior 
 * <p>
 * Sets the attached component resizable. When the resized of the component is
 * done, {@link #onResize(int, int, AjaxRequestTarget)} is called with Ajax.
 * </p>
 * 
 * <p>
 * This behavior contains a {@link ResizableBehavior} which is used to control 
 * the options of the resizable, including all the options and event of the
 * behavior. Example:
 * <pre>
 * ResizableAjaxBehavior resizable = new ResizableAjaxBehavior() {
 * 		public void onResize(int height, int width, AjaxRequestTarget ajaxRequestTarget) {
 * 			...
 * 		}
 * }
 * ResizableBehavior rb = resizable.getResizableBehavior();
 * rb.setAnimeDuration(new ResizableAnimeDuration(500));
 * rb.setGhost(true);
 * add(resizable);
 * </pre>
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public abstract class ResizableAjaxBehavior extends AbstractDefaultAjaxBehavior {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Adding the standard resizable JavaScript behavior
	 */
	private InnerDroppableBehavior resizableBehavior;
	
	/** Height into the request */
	private static final String RESIZED_HEIGHT = "resizedHeight";
	
	/** Width into the request */
	private static final String RESIZED_WIDTH = "resizedWidth";

	/**
	 * Default constructor
	 */
	public ResizableAjaxBehavior() {
		super();
		resizableBehavior = new InnerDroppableBehavior();
	}

	/**
	 * @return the standard resizable JavaScript behavior
	 */
	public ResizableBehavior getResizableBehavior() {
		return resizableBehavior;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(resizableBehavior);
		resizableBehavior.setInnerStopEvent(new JsScopeUiEvent() {
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
						+ "&" + RESIZED_HEIGHT + "='+" + ResizableBehavior.UI_SIZE + ".height+'"
						+ "&" + RESIZED_WIDTH + "='+" + ResizableBehavior.UI_SIZE + ".width"
						+", null,null, function() {return true;})");
			}

		});
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
		onResize(target);
	}

	/**
	 * For framework internal use only.
	 */
	public final void onResize(AjaxRequestTarget target) {
		int height = Integer.parseInt(this.getComponent().getRequest().getParameter(
				RESIZED_HEIGHT));
		int width = Integer.parseInt(this.getComponent().getRequest().getParameter(
				RESIZED_WIDTH));
		onResize(height, width, target);
	}

	/**
	 * onDrop is called back when the drop event has been fired.
	 * 
	 * @param droppedComponent
	 *            the dropped {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	
	/**
	 * onResize is triggered at the end of a resize operation. 
	 * 
	 * @param height Height of the resized {@link Component}
	 * @param width Width of the resized {@link Component}
	 * @param ajaxRequestTarget the Ajax target
	 */
	public abstract void onResize(int height, int width,
			AjaxRequestTarget ajaxRequestTarget);

	/**
	 * We override the behavior to deny the access of critical methods 
	 * 
	 * @author Julien Roche
	 * 
	 */
	private class InnerDroppableBehavior extends ResizableBehavior {
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = 5587258236214715234L;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.odlabs.wiquery.ui.droppable.DroppableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the ResizableAjaxBehavior");
		}

		/* (non-Javadoc)
		 * @see org.odlabs.wiquery.ui.resizable.ResizableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public ResizableBehavior setStopEvent(JsScopeUiEvent stop) {
			throw new UnsupportedOperationException(
					"You can't call this method into the ResizableAjaxBehavior");
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerStopEvent(JsScopeUiEvent stop) {
			super.setStopEvent(stop);
		}
	}
}
