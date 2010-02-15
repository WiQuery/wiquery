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
package org.odlabs.wiquery.ui.droppable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.core.util.MarkupIdVisitor;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * Sets the attached component droppable, e.g. it can accept draggable elements.
 * When the drop is done, {@link #onDrop(Component, AjaxRequestTarget)} is
 * called by an Ajax request.
 * </p>
 * 
 * <p>
 * Contains a {@link DroppableBehavior} which is used to control the options of
 * the droppable, including accept, activeClass, addClasses, greedy, hoverClass,
 * scope, and tolerance.  For example:
 * <pre>
 * DroppableAjaxBehavior droppable = new DroppableAjaxBehavior() {
 *      public void onDrop(Component droppedComponent,
 *              AjaxRequestTarget ajaxRequestTarget) {
 *                      ...
 *      }
 * };
 * DroppableBehavior dp = droppable.getDroppableBehavior();
 * dp.setAccept(new DroppableAccept(".fruit"));
 * dp.setHoverClass("drophover");
 * dp.setScope("candy");
 * add(droppable);
 * </pre>
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public abstract class DroppableAjaxBehavior extends AbstractDefaultAjaxBehavior {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2L;

	/**
	 * Adding the standard droppable JavaScript behavior
	 */
	private InnerDroppableBehavior droppableBehavior;

	/**
	 * Default constructor
	 */
	public DroppableAjaxBehavior() {
		super();
		droppableBehavior = new InnerDroppableBehavior();
	}

	/**
	 * @return the standard droppable JavaScript behavior
	 */
	public DroppableBehavior getDroppableBehavior() {
		return droppableBehavior;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(droppableBehavior);
		droppableBehavior.setInnerDropEvent(new JsScopeUiEvent() {
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
						+ "&droppedId='+" + DroppableBehavior.UI_DRAGGABLE
						+ "[0].id, null,null, function() {return true;})");
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
		onDrop(target);
	}

	/**
	 * For framework internal use only.
	 */
	public final void onDrop(AjaxRequestTarget target) {
		// getting dropped element id to retrieve the Wicket component
		String input = this.getComponent().getRequest().getParameter(
				"droppedId");
		MarkupIdVisitor visitor = new MarkupIdVisitor(input);
		this.getComponent().getPage().visitChildren(visitor);
		onDrop(visitor.getFoundComponent(), target);
	}

	/**
	 * onDrop is called back when the drop event has been fired.
	 * 
	 * @param droppedComponent
	 *            the dropped {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onDrop(Component droppedComponent,
			AjaxRequestTarget ajaxRequestTarget);

	/**
	 * We override the behavior to deny the access of critical methods (example,
	 * we don't want that the end user specify a drop event, because the
	 * {@link DroppableAjaxBehavior} has got his own !!)
	 * 
	 * @author Julien Roche
	 * 
	 */
	private class InnerDroppableBehavior extends DroppableBehavior {
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
					"You can't call this method into the DroppableAjaxBehavior");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.odlabs.wiquery.ui.droppable.DroppableBehavior#setDropEvent(org
		 * .odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public DroppableBehavior setDropEvent(JsScopeUiEvent drop) {
			throw new UnsupportedOperationException(
					"You can't call this method into the DroppableAjaxBehavior");
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerDropEvent(JsScopeUiEvent drop) {
			super.setDropEvent(drop);
		}
	}
}
