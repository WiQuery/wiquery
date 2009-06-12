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
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * Sets the attached component droppable, e.g. it can accept draggable elements.
 * When the drop is done, {@link #onDrop(Component, AjaxRequestTarget)} is
 * called by an Ajax request.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public abstract class DroppableAjaxBehavior extends AbstractDefaultAjaxBehavior {

	private static final long serialVersionUID = 1L;

	/**
	 * Adding the standard droppable JavaScript behavior
	 */
	private DroppableBehavior droppableBehavior = new DroppableBehavior();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(droppableBehavior);
		droppableBehavior.setOnDrop(new JsScopeUiEvent() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext
						.append("wicketAjaxGet('"
								+ getCallbackUrl(true)
								+ "&droppedId='+ui.draggable[0].id, null,null, function() {return true;})");
			}

		});
	}

	/**
	 * Sets the acceptance.
	 * 
	 * @param acceptance
	 *            CSS rule, e.g. which components can be dropped in
	 */
	public void setAccept(String accept) {
		droppableBehavior.setAccept(accept);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		onDrop(target);
	}

	/**
	 * For framework internal use only.
	 */
	public final void onDrop(AjaxRequestTarget target) {
		// getting dropped element id to retreive the wicket component
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

	// TODO refactor -> create separated file
	private static class MarkupIdVisitor implements IVisitor<Component> {
		private final String id;
		private Component found;

		public MarkupIdVisitor(String id) {
			this.id = id;
		}

		public Object component(Component component) {
			if (component.getMarkupId().equals(id)) {
				this.found = component;
				return IVisitor.STOP_TRAVERSAL;
			}
			if (component instanceof MarkupContainer) {
				return ((MarkupContainer) component).visitChildren(this);
			}
			return IVisitor.CONTINUE_TRAVERSAL;
		}

		public Component getFoundComponent() {
			return found;
		}
	}
}
