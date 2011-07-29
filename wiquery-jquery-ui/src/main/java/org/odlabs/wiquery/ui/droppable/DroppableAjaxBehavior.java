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
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.core.util.MarkupIdVisitor;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.droppable.DroppableBehavior.ToleranceEnum;

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
 * @param <E> Type of component to find
 * @author Lionel Armanet
 * @since 1.0
 */
public abstract class DroppableAjaxBehavior<E extends Component> extends AbstractDefaultAjaxBehavior {
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
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.droppable.DroppableBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
		 */
		@Override
		public void renderHead(Component component, IHeaderResponse response) {
			super.renderHead(component, response);
			DroppableAjaxBehavior.this.renderHead(component, response);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.droppable.DroppableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the DroppableAjaxBehavior");
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.droppable.DroppableBehavior#setDropEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
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

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.droppable.DroppableBehavior#statement()
		 */
		@Override
		public JsStatement statement() {
			droppableBehavior.setInnerDropEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;

				/**
				 * {@inheritDoc}
				 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {
					// we delegate URL generation to outer behavior.
					// that way all logic regarding AjaxCallDecorators will be added 
					// by AbstractDefaultAjaxBehavior.generateCallbackScript method.
					scopeContext.append(DroppableAjaxBehavior.this.getCallbackScript());
				}

			});
			
			return super.statement();
		}
	}

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
	 * 	We override super method to add droppedId parameter to the URL. Otherwise we use standard 
	 *  AbstractDefaultAjaxBehavior machinery to generate script: what way all the logic 
	 *  regarding IAjaxCallDecorator or indicatorId will be added to the generated script. 
	 *  This makes droppable behavior more compatible with standard Wicket's AJAX call-backs.
	 * 
	 * (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackUrl()
	 */
	@Override
	protected CharSequence getCallbackScript()
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl() 
				+ "&droppedId='+" + DroppableBehavior.UI_DRAGGABLE
				+ "[0].id");
	}

	/**
	 * @return the standard droppable JavaScript behavior
	 */
	public DroppableBehavior getDroppableBehavior() {
		return droppableBehavior;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(droppableBehavior);
	}

	/**
	 * For framework internal use only.
	 */
	@SuppressWarnings("unchecked")
	public final void onDrop(AjaxRequestTarget target) {
		// getting dropped element id to retrieve the Wicket component
		String input = this.getComponent().getRequest().getQueryParameters().getParameterValue(
				"droppedId").toString();
		MarkupIdVisitor visitor = new MarkupIdVisitor(input);
		this.getComponent().getPage().visitChildren(visitor);
		onDrop((E) visitor.getFoundComponent(), target);
	}

	/**
	 * onDrop is called back when the drop event has been fired.
	 * 
	 * @param droppedComponent
	 *            the dropped {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	public abstract void onDrop(E droppedComponent,
			AjaxRequestTarget ajaxRequestTarget);

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		onDrop(target);
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.IWiQueryPlugin#statement()
	 */
	protected JsStatement statement() {
		return droppableBehavior.statement();
	}
	
	////////////////////////////////////////////////////////////////////////////
	////							SHORTCUTS								////
	////////////////////////////////////////////////////////////////////////////
	/*---- Options section ---*/
	/**
	 * All draggables that match the selector will be accepted. If a function is
	 * specified, the function will be called for each draggable on the page
	 * (passed as the first argument to the function), to provide a custom
	 * filter. The function should return true if the draggable should be
	 * accepted.
	 * 
	 * @param accept
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setAccept(DroppableAccept accept) {
		droppableBehavior.setAccept(accept);
		return this;
	}

	/**
	 * @return the accept option
	 */
	public DroppableAccept getAccept() {
		return droppableBehavior.getAccept();
	}

	/**
	 * If specified, the class will be added to the droppable while an
	 * acceptable draggable is being dragged.
	 * 
	 * @param activeClass
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setActiveClass(String activeClass) {
		droppableBehavior.setActiveClass(activeClass);
		return this;
	}

	/**
	 * @return the activeClass option
	 */
	public String getActiveClass() {
		return droppableBehavior.getActiveClass();
	}

	/**
	 * If true, will prevent event propagation on nested droppables.
	 * 
	 * @param addClasses
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setAddClasses(boolean addClasses) {
		droppableBehavior.setAddClasses(addClasses);
		return this;
	}

	/**
	 * @return the addClasses option
	 */
	public boolean isAddClasses() {
		return droppableBehavior.isAddClasses();
	}
	
	/**Disables (true) or enables (false) the droppable. Can be set when 
	 * initialising (first creating) the droppable.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setDisabled(boolean disabled) {
		droppableBehavior.setDisabled(disabled);
		return this;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		return droppableBehavior.isDisabled();
	}

	/**
	 * If true, will prevent event propagation on nested droppables.
	 * 
	 * @param greedy
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setGreedy(boolean greedy) {
		droppableBehavior.setGreedy(greedy);
		return this;
	}

	/**
	 * @return the greedy option
	 */
	public boolean isGreedy() {
		return droppableBehavior.isGreedy();
	}

	/**
	 * If specified, the class will be added to the droppable while an
	 * acceptable draggable is being hovered.
	 * 
	 * @param hoverClass
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setHoverClass(String hoverClass) {
		droppableBehavior.setHoverClass(hoverClass);
		return this;
	}

	/**
	 * @return the hoverClass option
	 */
	public String getHoverClass() {
		return droppableBehavior.getHoverClass();
	}

	/**
	 * Used to group sets of draggable and droppable items, in addition to
	 * droppable's accept option. A draggable with the same scope value as a
	 * droppable will be accepted.
	 * 
	 * @param scope
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setScope(String scope) {
		droppableBehavior.setScope(scope);
		return this;
	}

	/**
	 * @return the scope option
	 */
	public String getScope() {
		return droppableBehavior.getScope();
	}

	/**
	 * Set's the mode to use for testing whether a draggable is 'over' a
	 * droppable. Possible values: 'fit', 'intersect', 'pointer', 'touch'.
	 * <ul>
	 * <li><b>fit</b>: draggable overlaps the droppable entirely</li>
	 * <li><b>intersect</b>: draggable overlaps the droppable at least 50%</li>
	 * <li><b>pointer</b>: mouse pointer overlaps the droppable</li>
	 * <li><b>touch</b>: draggable overlaps the droppable any amount</li>
	 * </ul>
	 * 
	 * @param tolerance
	 * @return instance of the current behavior
	 */
	public DroppableAjaxBehavior<E> setTolerance(ToleranceEnum tolerance) {
		droppableBehavior.setTolerance(tolerance);
		return this;
	}

	/**
	 * @return the tolerance option enum
	 */
	public ToleranceEnum getTolerance() {
		return droppableBehavior.getTolerance();
	}

	/*---- Methods section ---*/

	/**
	 * Method to destroy the droppable This will return the element back to its
	 * pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return droppableBehavior.destroy();
	}

	/**
	 * Method to destroy the droppable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		droppableBehavior.destroy(ajaxRequestTarget);
	}

	/**
	 * Method to disable the droppable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return droppableBehavior.disable();
	}

	/**
	 * Method to disable the droppable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		droppableBehavior.disable(ajaxRequestTarget);
	}

	/**
	 * Method to enable the droppable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return droppableBehavior.enable();
	}

	/**
	 * Method to enable the droppable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		droppableBehavior.enable(ajaxRequestTarget);
	}
	
	/**Method to returns the .ui-droppable  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return droppableBehavior.widget();
	}

	/**Method to returns the .ui-droppable  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		droppableBehavior.widget(ajaxRequestTarget);
	}
}
