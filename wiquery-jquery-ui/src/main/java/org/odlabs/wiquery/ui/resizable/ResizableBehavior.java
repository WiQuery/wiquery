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
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.mouse.MouseJavaScriptResourceReference;
import org.odlabs.wiquery.ui.resizable.ResizableAnimeDuration.DurationEnum;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Sets the attached component resizable.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
@WiQueryUIPlugin
public class ResizableBehavior extends WiQueryAbstractBehavior
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 4155106232676863150L;

	/**
	 * Properties on the ui parameter (use it into callback functions) : a jQuery object
	 * containing the helper element
	 */
	public static final String UI_HELPER = "ui.helper";

	/**
	 * Properties on the ui parameter (use it into callback functions) : {top, left}
	 * before resizing started
	 */
	public static final String UI_ORIGIGNALPOSITION = "ui.originalPosition";

	/**
	 * Properties on the ui parameter (use it into callback functions) : {width, height}
	 * before resizing started
	 */
	public static final String UI_ORIGINALSIZE = "ui.originalSize";

	/**
	 * Properties on the ui parameter (use it into callback functions) : {top, left}
	 * current position
	 */
	public static final String UI_POSITION = "ui.position ";

	/**
	 * Properties on the ui parameter (use it into callback functions) : {width, height}
	 * current size
	 */
	public static final String UI_SIZE = "ui.size";

	// Properties
	private Options options = new Options();

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
		response.renderJavaScriptReference(WidgetJavaScriptResourceReference.get());
		response.renderJavaScriptReference(MouseJavaScriptResourceReference.get());
		response.renderJavaScriptReference(ResizableJavaScriptResourceReference.get());
	}

	@Override
	public JsStatement statement()
	{
		return new JsQuery(this.getComponent()).$().chain("resizable",
			this.options.getJavaScriptOptions());
	}

	/**
	 * Method retrieving the options of the component
	 * 
	 * @return the options
	 */
	protected Options getOptions()
	{
		return options;
	}

	/*---- Options section ---*/

	/**
	 * Resize these elements synchronous when resizing.
	 * 
	 * @param cssSelector
	 * @return instance of the current behavior
	 * @deprecated will be removed in 1.2
	 */
	@Deprecated
	public ResizableBehavior setAlsoResize(String cssSelector)
	{
		this.options.putLiteral("alsoResize", cssSelector);
		return this;
	}

	/**
	 * @return the alsoResize option
	 * @deprecated will be changed in 1.2 to return a {@link ResizableAlsoResize}
	 */
	@Deprecated
	public String getAlsoResize()
	{
		return this.options.getLiteral("alsoResize");
	}

	/**
	 * Resize these elements synchronous when resizing.
	 * 
	 * @param alsoResize
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setAlsoResize(ResizableAlsoResize alsoResize)
	{
		this.options.put("alsoResize", alsoResize);
		return this;
	}

	/**
	 * @return the alsoResize option
	 */
	public ResizableAlsoResize getAlsoResizeComplex()
	{
		if (this.options.getComplexOption("alsoResize") instanceof ResizableAlsoResize)
		{
			return (ResizableAlsoResize) this.options.getComplexOption("alsoResize");
		}

		return null;
	}

	/**
	 * Animates to the final size after resizing.
	 * 
	 * @param animate
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setAnimate(boolean animate)
	{
		this.options.put("animate", animate);
		return this;
	}

	/**
	 * @return the animate option
	 */
	public boolean isAnimate()
	{
		if (this.options.containsKey("animate"))
		{
			return this.options.getBoolean("animate");
		}

		return false;
	}

	/**
	 * Sets the duration time for animating, in milliseconds. Other possible values:
	 * 'slow', 'normal', 'fast'.
	 * 
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setAnimateDuration(ResizableAnimeDuration animeDuration)
	{
		this.options.put("animateDuration", animeDuration);
		return this;
	}

	/**
	 * @return the animeDuration option
	 */
	public ResizableAnimeDuration getAnimateDuration()
	{
		IComplexOption animeDuration = this.options.getComplexOption("animateDuration");
		if (animeDuration != null && animeDuration instanceof ResizableAnimeDuration)
		{
			return (ResizableAnimeDuration) animeDuration;
		}

		return new ResizableAnimeDuration(DurationEnum.SLOW);
	}

	/**
	 * Sets the easing effect for animating.
	 * 
	 * @param easing
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setAnimateEasing(String easing)
	{
		options.putLiteral("animateEasing", easing);
		return this;
	}

	/**
	 * @return the animateEasing option
	 */
	public String getAnimateEasing()
	{
		String animateEasing = options.getLiteral("animateEasing");
		return animateEasing == null ? "swing" : animateEasing;
	}

	/**
	 * If set to true, resizing is constrained by the original aspect ratio. Otherwise a
	 * custom aspect ratio can be specified, such as 9 / 16, or 0.5.
	 * 
	 * @param aspectRatio
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setAspectRatio(ResizableAspectRatio aspectRatio)
	{
		this.options.put("aspectRatio", aspectRatio);
		return this;
	}

	/**
	 * @return the aspectRatio option
	 */
	public ResizableAspectRatio getAspectRatio()
	{
		IComplexOption aspectRatio = this.options.getComplexOption("aspectRatio");
		if (aspectRatio != null && aspectRatio instanceof ResizableAspectRatio)
		{
			return (ResizableAspectRatio) aspectRatio;
		}

		return null;
	}

	/**
	 * If set to true, automatically hides the handles except when the mouse hovers over
	 * the element.
	 * 
	 * @param autoHide
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setAutoHide(boolean autoHide)
	{
		options.put("autoHide", autoHide);
		return this;
	}

	/**
	 * @return the autoHide option
	 */
	public boolean isAutoHide()
	{
		if (this.options.containsKey("autoHide"))
		{
			return this.options.getBoolean("autoHide");
		}

		return false;
	}

	/**
	 * Prevents resizing if you start on elements matching the selector.
	 * 
	 * @param cancel
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setCancel(String cancel)
	{
		options.putLiteral("cancel", cancel);
		return this;
	}

	/**
	 * @return the cancel option
	 */
	public String getCancel()
	{
		String cancel = options.getLiteral("cancel");
		return cancel == null ? "input,option" : cancel;
	}

	/**
	 * Sets the constrains resizing to within the bounds of the specified element.
	 * Possible values: 'parent', 'document', a DOMElement, or a Selector.
	 * 
	 * @param containment
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setContainment(ResizableContainment containment)
	{
		this.options.put("containment", containment);
		return this;
	}

	/**
	 * @return the containment option
	 */
	public ResizableContainment getContainment()
	{
		IComplexOption containment = this.options.getComplexOption("containment");
		if (containment != null && containment instanceof ResizableContainment)
		{
			return (ResizableContainment) containment;
		}

		return null;
	}

	/**
	 * Sets the tolerance, in milliseconds, for when resizing should start. If specified,
	 * resizing will not start until after mouse is moved beyond duration. This can help
	 * prevent unintended resizing when clicking on an element.
	 * 
	 * @param delay
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setDelay(int delay)
	{
		options.put("delay", delay);
		return this;
	}

	/**
	 * @return the distance option
	 */
	public int getDelay()
	{
		if (this.options.containsKey("delay"))
		{
			return options.getInt("delay");
		}

		return 0;
	}

	/**
	 * Sets the tolerance, in pixels, for when resizing should start. If specified,
	 * resizing will not start until after mouse is moved beyond distance. This can help
	 * prevent unintended resizing when clicking on an element.
	 * 
	 * @param distance
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setDistance(int distance)
	{
		options.put("distance", distance);
		return this;
	}

	/**
	 * @return the distance option
	 */
	public int getDistance()
	{
		if (this.options.containsKey("distance"))
		{
			return options.getInt("distance");
		}

		return 1;
	}

	/**
	 * Disables (true) or enables (false) the resizable. Can be set when initialising
	 * (first creating) the resizable.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setDisabled(boolean disabled)
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
	 * Set to true, a semi-transparent helper element is shown for resizing.
	 * 
	 * @param ghost
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setGhost(boolean ghost)
	{
		options.put("ghost", ghost);
		return this;
	}

	/**
	 * @return the ghost option
	 */
	public boolean isGhost()
	{
		if (this.options.containsKey("ghost"))
		{
			return options.getBoolean("ghost");
		}

		return false;
	}

	/**
	 * Snaps the resizing element to a grid, every x and y pixels. Array values: [x, y]
	 * Array values: [x, y]
	 * 
	 * @param x
	 * @param y
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setGrid(int x, int y)
	{
		ArrayItemOptions<IntegerItemOptions> grids = new ArrayItemOptions<IntegerItemOptions>();
		grids.add(new IntegerItemOptions(x));
		grids.add(new IntegerItemOptions(y));
		this.options.put("grid", grids);
		return this;
	}

	/**
	 * @return the grid option value
	 */
	public ICollectionItemOptions getGrid()
	{
		return this.options.getListItemOptions("grid");
	}

	/**
	 * If specified as a string, should be a comma-split list of any of the following: 'n,
	 * e, s, w, ne, se, sw, nw, all'. The necessary handles will be auto-generated by the
	 * plugin.
	 * 
	 * If specified as an object, the following keys are supported: { n, e, s, w, ne, se,
	 * sw, nw }. The value of any specified should be a jQuery selector matching the child
	 * element of the resizable to use as that handle. If the handle is not a child of the
	 * resizable, you can pass in the DOMElement or a valid jQuery object directly.
	 * 
	 * @param handles
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setHandles(ResizableHandles handles)
	{
		this.options.put("handles", handles);
		return this;
	}

	/**
	 * @return the handles option
	 */
	public ResizableHandles getHandles()
	{
		IComplexOption handles = this.options.getComplexOption("handles");
		if (handles != null && handles instanceof ResizableHandles)
		{
			return (ResizableHandles) handles;
		}

		return new ResizableHandles(new LiteralOption("e,s,se"));
	}

	/**
	 * Sets the css class that will be added to a proxy element to outline the resize
	 * during the drag of the resize handle. Once the resize is complete, the original
	 * element is sized.
	 * 
	 * @param helper
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setHelper(String helper)
	{
		options.putLiteral("helper", helper);
		return this;
	}

	/**
	 * @return the helper option
	 */
	public String getHelper()
	{
		return options.getLiteral("helper");
	}

	/**
	 * Sets the component's max height.
	 * 
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setMaxHeight(int maxHeight)
	{
		options.put("maxHeight", maxHeight);
		return this;
	}

	/**
	 * Returns the component's max height.
	 */
	public int getMaxHeight()
	{
		if (this.options.containsKey("maxHeight"))
		{
			return options.getInt("maxHeight");
		}

		return 0;
	}

	/**
	 * Sets the window's max width.
	 * 
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setMaxWidth(int maxWidth)
	{
		options.put("maxWidth", maxWidth);
		return this;
	}

	/**
	 * Returns the component's max width.
	 */
	public int getMaxWidth()
	{
		if (this.options.containsKey("maxWidth"))
		{
			return options.getInt("maxWidth");
		}

		return 0;
	}

	/**
	 * Sets the component's min height.
	 * 
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setMinHeight(int minHeight)
	{
		options.put("minHeight", minHeight);
		return this;
	}

	/**
	 * Returns the component's min height.
	 */
	public int getMinHeight()
	{
		if (this.options.containsKey("minHeight"))
		{
			return options.getInt("minHeight");
		}

		return 10;
	}

	/**
	 * Sets the component's min width.
	 * 
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setMinWidth(int minWidth)
	{
		options.put("minWidth", minWidth);
		return this;
	}

	/**
	 * Returns the component's max width.
	 */
	public int getMinWidth()
	{
		if (this.options.containsKey("minWidth"))
		{
			return options.getInt("minWidth");
		}

		return 10;
	}

	/*---- Events section ---*/

	/**
	 * Set's the callback when the event is triggered during the resize, on the drag of
	 * the resize handler.
	 * 
	 * @param resize
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setResizeEvent(JsScopeUiEvent resize)
	{
		this.options.put("resize", resize);
		return this;
	}

	/**
	 * Set's the callback when the event is triggered at the start of a resize operation.
	 * 
	 * @param start
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setStartEvent(JsScopeUiEvent start)
	{
		this.options.put("start", start);
		return this;
	}

	/**
	 * Set's the callback when the event is triggered at the end of a resize operation.
	 * 
	 * @param stop
	 * @return instance of the current behavior
	 */
	public ResizableBehavior setStopEvent(JsScopeUiEvent stop)
	{
		this.options.put("stop", stop);
		return this;
	}

	/*---- Methods section ----*/

	/**
	 * Method to destroy This will return the element back to its pre-init state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(getComponent()).$().chain("resizable", "'destroy'");
	}

	/**
	 * Method to destroy within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method to disable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement disable()
	{
		return new JsQuery(getComponent()).$().chain("resizable", "'disable'");
	}

	/**
	 * Method to disable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
	}

	/**
	 * Method to enable
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement enable()
	{
		return new JsQuery(getComponent()).$().chain("resizable", "'enable'");
	}

	/**
	 * Method to enable within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}

	/**
	 * Method to returns the .ui-resizable element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(getComponent()).$().chain("resizable", "'widget'");
	}

	/**
	 * Method to returns the .ui-resizable element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}
}
