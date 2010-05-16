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
	 * @return the standard Selectable JavaScript behavior
	 */
	public SelectableBehavior getSelectableBehavior() {
		return selectableBehavior;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(selectableBehavior);
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
		onSelection(target);
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
	 * onDrop is called back when the drop event has been fired.
	 * 
	 * @param droppedComponent
	 *            the dropped {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	
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
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	protected JsStatement statement() {
		return selectableBehavior.statement();
	}

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.odlabs.wiquery.ui.droppable.DroppableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the SelectableAjaxBehavior");
		}

		/* (non-Javadoc)
		 * @see org.odlabs.wiquery.ui.selectable.SelectableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public SelectableBehavior setStopEvent(JsScopeUiEvent stop) {
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
		 * @see org.odlabs.wiquery.ui.selectable.SelectableBehavior#statement()
		 */
		@Override
		public JsStatement statement() {
			selectableBehavior.setInnerStopEvent(new JsScopeUiEvent() {
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
					scopeContext.append("var selected = new Array();"
							+ "jQuery.each($('#" + getComponent().getMarkupId(true) +"')" +
									".children(\"*[class*='ui-selected']\"), function(){" +
									"selected.push($(this).attr('id'));});"
							+ "wicketAjaxGet('" + getCallbackUrl(true)
							+ "&" + SELECTED_ARRAY + "='+ jQuery.unique(selected).toString()"
							+ ", null,null, function() {return true;})");
				}

			});
			
			return super.statement();
		}
	}
}
