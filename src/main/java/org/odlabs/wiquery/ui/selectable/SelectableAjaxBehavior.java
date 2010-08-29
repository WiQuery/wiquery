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
package org.odlabs.wiquery.ui.selectable;

import java.util.StringTokenizer;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.core.util.MarkupIdVisitor;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.selectable.SelectableBehavior.ToleranceEnum;

/**
 * $Id: SelectableAjaxBehavior 
 * <p>
 * Sets the attached component selectable behavior. When the user finished to
 * select some childs components,{@link #onSelection(Component[], AjaxRequestTarget)} 
 * is called via Ajax.
 * </p>
 * 
 * <p>
 * This behavior contains a {@link SelectableBehavior} which is used to control 
 * the options of the selectable, including all the options and event of the
 * behavior. Example:
 * <pre>
 * SelectableAjaxBehavior selectable = new SelectableAjaxBehavior() {
 * 		public void onSelection(Component[] components, AjaxRequestTarget ajaxRequestTarget) {
 * 			...
 * 		}
 * }
 * SelectableBehavior sb = selectable.getSelectableBehavior();
 * sb.setTolerance(ToleranceEnum.FIT);
 * add(selectable);
 * </pre>
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public abstract class SelectableAjaxBehavior extends AbstractDefaultAjaxBehavior {
	/**
	 * We override the behavior to deny the access of critical methods 
	 * 
	 * @author Julien Roche
	 * 
	 */
	private class InnerDroppableBehavior extends SelectableBehavior {
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = 5587258236214715234L;
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.selectable.SelectableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the SelectableAjaxBehavior");
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerStopEvent(JsScopeUiEvent stop) {
			super.setStopEvent(stop);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.selectable.SelectableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public SelectableBehavior setStopEvent(JsScopeUiEvent stop) {
			throw new UnsupportedOperationException(
					"You can't call this method into the SelectableAjaxBehavior");
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.selectable.SelectableBehavior#statement()
		 */
		@Override
		public JsStatement statement() {
			selectableBehavior.setInnerStopEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;

				/**
				 * {@inheritDoc}
				 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {
					scopeContext.append("var selected = new Array();"
							+ "jQuery.each($('#" + getComponent().getMarkupId(true) +"')" +
									".children(\"*[class*='ui-selected']\"), function(){" +
									"selected.push($(this).attr('id'));});"
							+ SelectableAjaxBehavior.this.getCallbackScript(true));
				}

			});
			
			return super.statement();
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Adding the standard selectable JavaScript behavior
	 */
	private InnerDroppableBehavior selectableBehavior;

	/** Selection into the request */
	private static final String SELECTED_ARRAY = "selectedArray";

	/**
	 * Default constructor
	 */
	public SelectableAjaxBehavior() {
		super();
		selectableBehavior = new InnerDroppableBehavior();
	}

	/**
	 * 	We override super method to add the selected array to the URL. 
	 * 	Otherwise we use standard AbstractDefaultAjaxBehavior machinery to generate script: what way all the logic 
	 *  regarding IAjaxCallDecorator or indicatorId will be added to the generated script. 
	 *  This makes selectable AJAX behavior compatible with standard Wicket's AJAX call-backs.
	 * 
	 * (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackScript(boolean)
	 */
	@Override
	protected CharSequence getCallbackScript(boolean onlyTargetActivePage)
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl(onlyTargetActivePage) 
				+ "&" + SELECTED_ARRAY + "='+ jQuery.unique(selected).toString()");
	}

	/**
	 * @return the standard Selectable JavaScript behavior
	 */
	public SelectableBehavior getSelectableBehavior() {
		return selectableBehavior;
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(selectableBehavior);
	}

	/**
	 * For framework internal use only.
	 */
	public final void onSelection(AjaxRequestTarget target) {
		String selected = this.getComponent().getRequest().getParameter(SELECTED_ARRAY);
		
		StringTokenizer tokenized = new StringTokenizer(selected, ",");
		Component[] components = new Component[tokenized.countTokens()];
		
		Integer index = 0;
		MarkupIdVisitor visitorParent = null;
		
		while(tokenized.hasMoreTokens()){
			visitorParent = new MarkupIdVisitor(tokenized.nextToken().trim());
			this.getComponent().getPage().visitChildren(visitorParent);
			components[index] = visitorParent.getFoundComponent();
			index++;
		}
		
		onSelection(components, target);
	}
	
	/**
	 * onSelection is triggered at the end of a selection operation. 
	 * 
	 * @param components List of components selected
	 * @param ajaxRequestTarget the Ajax target
	 */
	public abstract void onSelection(Component[] components,
			AjaxRequestTarget ajaxRequestTarget);

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		onSelection(target);
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	protected JsStatement statement() {
		return selectableBehavior.statement();
	}
	
	////////////////////////////////////////////////////////////////////////////
	////							SHORTCUTS								////
	////////////////////////////////////////////////////////////////////////////
	/**
	 * @return the cancel option value
	 */
	public String getCancel() {
		return selectableBehavior.getCancel();
	}

	/**
	 * @return the delay option value
	 */
	public int getDelay() {
		return selectableBehavior.getDelay();
	}

	/**
	 * @return the distance option value
	 */
	public int getDistance() {
		return selectableBehavior.getDistance();
	}

	/**
	 * @return the cancel option value
	 */
	public String getFilter() {
		return selectableBehavior.getFilter();
	}

	/**
	 * @return the tolerance option enum
	 */
	public ToleranceEnum getTolerance() {
		return selectableBehavior.getTolerance();
	}

	/**
	 * @return the autoRefresh option enum
	 */
	public boolean isAutoRefresh() {
		return selectableBehavior.isAutoRefresh();
	}

	/** This determines whether to refresh (recalculate) the position and size 
	 * of each selected at the beginning of each select operation. If you have 
	 * many many items, you may want to set this to false and call the 
	 * refresh method manually.
	 * @param autoRefresh
	 * @return instance of the current behavior
	 */
	public SelectableBehavior setAutoRefresh(boolean autoRefresh) {
		return selectableBehavior.setAutoRefresh(autoRefresh);
	}
	
	/**Disables (true) or enables (false) the selectable. Can be set when 
	 * initialising (first creating) the selectable.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public SelectableBehavior setDisabled(boolean disabled) {
		return selectableBehavior.setDisabled(disabled);
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		return selectableBehavior.isDisabled();
	}

	/** Set's the prevent selecting if you start on elements matching the selector
	 * @param cancel Selector (default : ':input,option')
	 * @return instance of the current behavior
	 */
	public SelectableBehavior setCancel(String cancel) {
		return selectableBehavior.setCancel(cancel);
	}

	/** Set's the delay (in milliseconds) to define when the selecting should start
	 * @param delay
	 * @return instance of the current behavior
	 */
	public SelectableBehavior setDelay(int delay) {
		return selectableBehavior.setDelay(delay);
	}

	/** Set's the tolerance in pixels
	 * @param distance
	 * @return instance of the current behavior
	 */
	public SelectableBehavior setDistance(int distance) {
		return selectableBehavior.setDistance(distance);
	}

	/** Set's the matching child to be selectable
	 * @param filter Selector (default : '*')
	 * @return instance of the current behavior
	 */
	public SelectableBehavior setFilter(String filter) {
		return selectableBehavior.setFilter(filter);
	}

	/** Set's the tolerance
	 * <ul>
	 * 	<li><b>fit</b>: draggable overlaps the droppable entirely</li>
	 * 	<li><b>touch</b>: draggable overlaps the droppable any amount</li>
	 * </ul>
	 * @param tolerance
	 * @return instance of the current behavior
	 */
	public SelectableBehavior setTolerance(ToleranceEnum tolerance) {
		return selectableBehavior.setTolerance(tolerance);
	}
	
	/*---- Methods section ----*/
	/**Method to destroy
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return selectableBehavior.destroy();
	}

	/**Method to destroy within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		selectableBehavior.destroy(ajaxRequestTarget);
	}
	
	/**Method to disable
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return selectableBehavior.disable();
	}

	/**Method to disable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		selectableBehavior.disable(ajaxRequestTarget);
	}
	
	/**Method to enable
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return selectableBehavior.enable();
	}

	/**Method to enable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		selectableBehavior.enable(ajaxRequestTarget);
	}
	
	/**Method to refresh
	 * @return the associated JsStatement
	 */
	public JsStatement refresh() {
		return selectableBehavior.refresh();
	}

	/**Method to refresh within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget) {
		selectableBehavior.refresh();
	}
	
	/**Method to returns the .ui-selectable  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return selectableBehavior.widget();
	}

	/**Method to returns the .ui-selectable  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		selectableBehavior.widget(ajaxRequestTarget);
	}
}
