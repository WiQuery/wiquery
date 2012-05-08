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

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.CallbackParameter;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.odlabs.wiquery.core.behavior.AbstractAjaxEventCallback;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

/**
 * $Id$
 * <p>
 * Sets the attached component droppable, e.g. it can accept draggable elements.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public class DroppableBehavior extends WiQueryAbstractAjaxBehavior
{
	/**
	 * Enumeration for the tolerance option
	 * 
	 * @author Julien Roche
	 * 
	 */
	public enum ToleranceEnum
	{
		FIT,
		INTERSECT,
		POINTER,
		TOUCH;
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 2L;

	/**
	 * Properties on the ui parameter (use it into callback functions) : current draggable
	 * element, a jQuery object.
	 */
	public static final String UI_DRAGGABLE = "ui.draggable";

	/**
	 * Properties on the ui parameter (use it into callback functions) : current draggable
	 * helper, a jQuery object
	 */
	public static final String UI_HELPER = "ui.helper";

	/**
	 * Properties on the ui parameter (use it into callback functions) : current position
	 * of the draggable helper { top: , left: }
	 */
	public static final String UI_POSITION = "ui.position";

	/**
	 * Properties on the ui parameter (use it into callback functions) : current absolute
	 * position of the draggable helper { top: , left: }
	 */
	public static final String UI_OFFSET = "ui.offset";

	public abstract static class AjaxDropCallback extends AbstractAjaxEventCallback
	{
		private static final long serialVersionUID = 1L;

		public AjaxDropCallback()
		{
			super("drop");
		}

		@Override
		protected List<CallbackParameter> getExtraParameters()
		{
			List<CallbackParameter> ret = super.getExtraParameters();
			ret.add(CallbackParameter.resolved("droppedId", "$(" + DroppableBehavior.UI_DRAGGABLE
				+ ").attr('id')"));
			return ret;
		}

		@Override
		public final void call(AjaxRequestTarget target, Component source)
		{
			IRequestParameters req = RequestCycle.get().getRequest().getRequestParameters();

			Component dropped = findComponentById(req.getParameterValue("droppedId").toString());
			drop(target, source, dropped);
		}

		protected abstract void drop(AjaxRequestTarget target, Component source, Component dropped);
	}

	@Override
	public void onBind()
	{
		super.onBind();
		options.setOwner(getComponent());
	}

	@Override
	public void detach(Component component)
	{
		super.detach(component);
		options.detach();
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forReference(DroppableJavaScriptResourceReference
			.get()));
		response.render(OnDomReadyHeaderItem.forScript(new JsQuery(getComponent()).$()
			.chain("droppable", this.options.getJavaScriptOptions()).render()));
	}

	/*---- Options section ---*/

	/**
	 * All draggables that match the selector will be accepted. If a function is
	 * specified, the function will be called for each draggable on the page (passed as
	 * the first argument to the function), to provide a custom filter. The function
	 * should return true if the draggable should be accepted.
	 * 
	 * @param accept
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setAccept(DroppableAccept accept)
	{
		this.options.put("accept", accept);
		return this;
	}

	/**
	 * @return the accept option
	 */
	public DroppableAccept getAccept()
	{
		IComplexOption accept = this.options.getComplexOption("accept");
		if (accept instanceof DroppableAccept)
		{
			return (DroppableAccept) accept;
		}

		return new DroppableAccept("*");
	}

	/**
	 * If specified, the class will be added to the droppable while an acceptable
	 * draggable is being dragged.
	 * 
	 * @param activeClass
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setActiveClass(String activeClass)
	{
		this.options.putLiteral("activeClass", activeClass);
		return this;
	}

	/**
	 * @return the activeClass option
	 */
	public String getActiveClass()
	{
		return this.options.getLiteral("activeClass");
	}

	/**
	 * If true, will prevent event propagation on nested droppables.
	 * 
	 * @param addClasses
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setAddClasses(boolean addClasses)
	{
		this.options.put("addClasses", addClasses);
		return this;
	}

	/**
	 * @return the addClasses option
	 */
	public boolean isAddClasses()
	{
		if (this.options.containsKey("addClasses"))
		{
			return this.options.getBoolean("addClasses");
		}

		return true;
	}

	/**
	 * Disables (true) or enables (false) the droppable. Can be set when initialising
	 * (first creating) the droppable.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setDisabled(boolean disabled)
	{
		this.options.put("disabled", disabled);
		return this;
	}

	/**
	 * @return the disabled option
	 */
	public boolean isDisabled()
	{
		if (this.options.containsKey("disabled"))
		{
			return this.options.getBoolean("disabled");
		}

		return false;
	}

	/**
	 * If true, will prevent event propagation on nested droppables.
	 * 
	 * @param greedy
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setGreedy(boolean greedy)
	{
		this.options.put("greedy", greedy);
		return this;
	}

	/**
	 * @return the greedy option
	 */
	public boolean isGreedy()
	{
		if (this.options.containsKey("greedy"))
		{
			return this.options.getBoolean("greedy");
		}

		return false;
	}

	/**
	 * If specified, the class will be added to the droppable while an acceptable
	 * draggable is being hovered.
	 * 
	 * @param hoverClass
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setHoverClass(String hoverClass)
	{
		this.options.putLiteral("hoverClass", hoverClass);
		return this;
	}

	/**
	 * @return the hoverClass option
	 */
	public String getHoverClass()
	{
		return this.options.getLiteral("hoverClass");
	}

	/**
	 * Used to group sets of draggable and droppable items, in addition to droppable's
	 * accept option. A draggable with the same scope value as a droppable will be
	 * accepted.
	 * 
	 * @param scope
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setScope(String scope)
	{
		this.options.putLiteral("scope", scope);
		return this;
	}

	/**
	 * @return the scope option
	 */
	public String getScope()
	{
		String scope = this.options.getLiteral("scope");

		return scope == null ? "default" : scope;
	}

	/**
	 * Set's the mode to use for testing whether a draggable is 'over' a droppable.
	 * Possible values: 'fit', 'intersect', 'pointer', 'touch'.
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
	public DroppableBehavior setTolerance(ToleranceEnum tolerance)
	{
		this.options.putLiteral("tolerance", tolerance.toString().toLowerCase());
		return this;
	}

	/**
	 * @return the tolerance option enum
	 */
	public ToleranceEnum getTolerance()
	{
		String tolerance = this.options.getLiteral("tolerance");
		return tolerance == null ? ToleranceEnum.INTERSECT : ToleranceEnum.valueOf(tolerance
			.toUpperCase());
	}

	/*---- Events section ---*/

	/**
	 * Set's the callback when an accepted draggable starts dragging. This can be useful
	 * if you want to make the droppable 'light up' when it can be dropped on.
	 * 
	 * @param activate
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setActivateEvent(JsScopeUiEvent activate)
	{
		this.options.put("activate", activate);
		return this;
	}

	/**
	 * Set's the callback when an accepted draggable stops dragging.
	 * 
	 * @param deactivate
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setDeactivateEvent(JsScopeUiEvent deactivate)
	{
		this.options.put("deactivate", deactivate);
		return this;
	}

	/**
	 * Set's the callback when an accepted draggable is dropped 'over' (within the
	 * tolerance of) this droppable. In the callback, $(this) represents the droppable the
	 * draggable is dropped on. ui.draggable represents the draggable.
	 * 
	 * @param drop
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setDropEvent(JsScopeUiEvent drop)
	{
		this.options.put("drop", drop);
		return this;
	}

	public DroppableBehavior setDropEvent(AjaxDropCallback callback)
	{
		setEventListener(callback);
		return this;
	}

	/**
	 * Set's the callback when an accepted draggable is dragged out (within the tolerance
	 * of) this droppable.
	 * 
	 * @param out
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setOutEvent(JsScopeUiEvent out)
	{
		this.options.put("out", out);
		return this;
	}

	/**
	 * Set's the callback when an accepted draggable is dragged 'over' (within the
	 * tolerance of) this droppable.
	 * 
	 * @param over
	 * @return instance of the current behavior
	 */
	public DroppableBehavior setOverEvent(JsScopeUiEvent over)
	{
		this.options.put("over", over);
		return this;
	}

	/*---- Methods section ---*/

	/**
	 * Method to destroy the droppable This will return the element back to its pre-init
	 * state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(getComponent()).$().chain("droppable", "'destroy'");
	}

	/**
	 * Method to destroy the droppable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method to disable the droppable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return new JsQuery(getComponent()).$().chain("droppable", "'disable'");
	}

	/**
	 * Method to disable the droppable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
	}

	/**
	 * Method to enable the droppable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return new JsQuery(getComponent()).$().chain("droppable", "'enable'");
	}

	/**
	 * Method to enable the droppable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}

	/**
	 * Method to returns the .ui-droppable element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(getComponent()).$().chain("droppable", "'widget'");
	}

	/**
	 * Method to returns the .ui-droppable element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}
}
