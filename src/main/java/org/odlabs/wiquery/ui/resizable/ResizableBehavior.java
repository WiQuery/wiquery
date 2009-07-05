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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.IntegerItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;

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
public class ResizableBehavior extends WiQueryAbstractBehavior {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 4155106232676863149L;
	
	/** Properties on the ui parameter (use it into callback functions) : a jQuery 
	 * object containing the helper element */
	public static final String UI_HELPER = "ui.helper";
	/** Properties on the ui parameter (use it into callback functions) : {top, left} 
	 * before resizing started */
	public static final String UI_ORIGIGNALPOSITION = "ui.originalPosition";
	/** Properties on the ui parameter (use it into callback functions) : {width, height} 
	 * before resizing started */
	public static final String UI_ORIGINALSIZE = "ui.originalSize";
	/** Properties on the ui parameter (use it into callback functions) : {top, left} 
	 * current position */
	public static final String UI_POSITION = "ui.position ";
	/** Properties on the ui parameter (use it into callback functions) : {width, height}
	 * current size */
	public static final String UI_SIZE = "ui.size";

	// Properties
	private Options options = new Options();

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager
				.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager
				.addJavaScriptResource(ResizableJavaScriptResourceReference.get());
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(this.getComponent()).$().chain("resizable",
				this.options.getJavaScriptOptions());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	/*---- Options section ---*/
	
	/**Resize these elements synchronous when resizing.
	 * @param cssSelector
	 */
	public void setAlsoResize(String cssSelector) {
		this.options.putLiteral("alsoResize", cssSelector);
	}

	/**
	 * @return the alsoResize option
	 */
	public String getAlsoResize() {
		return this.options.getLiteral("alsoResize");
	}

	/**Animates to the final size after resizing.
	 * @param animate
	 */
	public void setAnimate(boolean animate) {
		this.options.put("animate", animate);
	}

	/**
	 * @return the animate option
	 */
	public boolean isAnimate() {
		return this.options.getBoolean("alsoResize");
	}
	
	/**
	 * Sets the duration time for animating, in milliseconds. 
	 * Other possible values: 'slow', 'normal', 'fast'.
	 */
	public void setAnimeDuration(ResizableAnimeDuration animeDuration) {
		this.options.put("animeDuration", animeDuration);
	}
	
	/**
	 * @return the animeDuration option
	 */
	public ResizableAnimeDuration getAnimeDuration() {
		IComplexOption animeDuration = this.options.getComplexOption("animeDuration");
		if(animeDuration != null && animeDuration instanceof ResizableAnimeDuration){
			return (ResizableAnimeDuration) animeDuration;
		}
		
		return null;
	}

	/**Sets the easing effect for animating.
	 * @param easing
	 */
	public void setAnimateEasing(String easing) {
		options.put("animateEasing", easing);
	}

	/**
	 * @return the animateEasing option
	 */
	public String getAnimateEasing() {
		return options.get("animateEasing");
	}
	
	/**
	 * If set to true, resizing is constrained by the original aspect ratio. 
	 * Otherwise a custom aspect ratio can be specified, such as 9 / 16, or 0.5.
	 * @param aspectRatio
	 */
	public void setAspectRatio(ResizableAspectRatio aspectRatio) {
		this.options.put("aspectRatio", aspectRatio);
	}
	
	/**
	 * @return the aspectRatio option
	 */
	public ResizableAspectRatio getAspectRatio() {
		IComplexOption aspectRatio = this.options.getComplexOption("aspectRatio");
		if(aspectRatio != null && aspectRatio instanceof ResizableAspectRatio){
			return (ResizableAspectRatio) aspectRatio;
		}
		
		return null;
	}
	
	/**If set to true, automatically hides the handles except when the mouse 
	 * hovers over the element.
	 * @param autoHide
	 */
	public void setAutoHide(boolean autoHide) {
		options.put("autoHide", autoHide);
	}
	
	/**
	 * @return the autoHide option
	 */
	public boolean isAutoHide() {
		return options.getBoolean("autoHide");
	}
	
	/**Prevents resizing if you start on elements matching the selector.
	 * @param cancel
	 */
	public void setCancel(String cancel) {
		options.putLiteral("cancel", cancel);
	}
	
	/**
	 * @return the cancel option
	 */
	public String getCancel() {
		return options.getLiteral("cancel");
	}
	
	/**
	 * Sets the constrains resizing to within the bounds of the specified element. 
	 * Possible values: 'parent', 'document', a DOMElement, or a Selector.
	 * @param containment
	 */
	public void setContainment(ResizableContainment containment) {
		this.options.put("containment", containment);
	}
	
	/**
	 * @return the containment option
	 */
	public ResizableContainment getContainment() {
		IComplexOption containment = this.options.getComplexOption("containment");
		if(containment != null && containment instanceof ResizableContainment){
			return (ResizableContainment) containment;
		}
		
		return null;
	}
	
	/**Sets the tolerance, in milliseconds, for when resizing should start. If 
	 * specified, resizing will not start until after mouse is moved beyond duration. 
	 * This can help prevent unintended resizing when clicking on an element.
	 * @param delay
	 */
	public void setDelay(int delay) {
		options.put("delay", delay);
	}
	
	/**
	 * @return the distance option
	 */
	public int getDelay() {
		return options.getInt("delay");
	}
	
	/**Sets the tolerance, in pixels, for when resizing should start. If specified,
	 * resizing will not start until after mouse is moved beyond distance. This 
	 * can help prevent unintended resizing when clicking on an element.
	 * @param distance
	 */
	public void setDistance(int distance) {
		options.put("distance", distance);
	}
	
	/**
	 * @return the distance option
	 */
	public int getDistance() {
		return options.getInt("distance");
	}
	
	/**Set to true, a semi-transparent helper element is shown for resizing.
	 * @param ghost
	 */
	public void setGhost(boolean ghost) {
		options.put("ghost", ghost);
	}
	
	/**
	 * @return the ghost option
	 */
	public boolean isGhost() {
		return options.getBoolean("ghost");
	}
	
	/**Snaps the resizing element to a grid, every x and y pixels. Array values: [x, y] 
	 * Array values: [x, y]
	 * @param x
	 * @param y
	 */
	public void setGrid(int x, int y) {
		ArrayItemOptions<IntegerItemOptions> grids = new ArrayItemOptions<IntegerItemOptions>();
		grids.add(new IntegerItemOptions(x));
		grids.add(new IntegerItemOptions(y));
		this.options.put("grid", grids);
	}
	
	/**
	 * @return the grid option value
	 */
	public ICollectionItemOptions getGrid() {
		return this.options.getListItemOptions("grid");
	}
	
	/**
	 * If specified as a string, should be a comma-split list of any of the following: 
	 * 'n, e, s, w, ne, se, sw, nw, all'. 
	 * The necessary handles will be auto-generated by the plugin.
	 * 
	 * If specified as an object, the following keys are supported: { n, e, s, w, ne, se, sw, nw }. 
	 * The value of any specified should be a jQuery selector matching the child 
	 * element of the resizable to use as that handle. If the handle is not a 
	 * child of the resizable, you can pass in the DOMElement or a valid jQuery 
	 * object directly.
	 * @param handles
	 */
	public void setHandles(ResizableHandles handles) {
		this.options.put("handles", handles);
	}
	
	/**
	 * @return the handles option
	 */
	public ResizableHandles getHandles() {
		IComplexOption handles = this.options.getComplexOption("handles");
		if(handles != null && handles instanceof ResizableHandles){
			return (ResizableHandles) handles;
		}
		
		return null;
	}
	
	/**Sets the css class that will be added to a proxy element to outline the 
	 * resize during the drag of the resize handle. Once the resize is complete, 
	 * the original element is sized.
	 * @param helper
	 */
	public void setHelper(String helper) {
		options.putLiteral("helper", helper);
	}
	
	/**
	 * @return the helper option
	 */
	public String getHelper() {
		return options.getLiteral("helper");
	}
	
	/**
	 * Sets the component's max height.
	 */
	public void setMaxHeight(int maxHeight) {
		options.put("maxHeight", maxHeight);
	}

	/**
	 * Returns the component's max height.
	 */
	public int getMaxHeight() {
		return options.getInt("maxHeight");
	}

	/**
	 * Sets the window's max width.
	 */
	public void setMaxWidth(int maxWidth) {
		options.put("maxWidth", maxWidth);
	}

	/**
	 * Returns the component's max width.
	 */
	public int getMaxWidth() {
		return options.getInt("maxWidth");
	}

	/**
	 * Sets the component's min height.
	 */
	public void setMinHeight(int minHeight) {
		options.put("minHeight", minHeight);
	}

	/**
	 * Returns the component's min height.
	 */
	public int getMinHeight() {
		return options.getInt("minHeight");
	}

	/**
	 * Sets the component's min width.
	 */
	public void setMinWidth(int minWidth) {
		options.put("minWidth", minWidth);
	}

	/**
	 * Returns the component's max width.
	 */
	public int getMinWidth() {
		return options.getInt("minWidth");
	}
	
	/*---- Events section ---*/
	
	/**Set's the callback when the event is triggered during the resize, on the 
	 * drag of the resize handler. 
	 * @param resize
	 */
	public void setResizeEvent(JsScopeUiEvent resize) {
		this.options.put("resize", resize);
	}
	
	/**Set's the callback when the event is triggered at the start of a resize 
	 * operation.
	 * @param start
	 */
	public void setStartEvent(JsScopeUiEvent start) {
		this.options.put("start", start);
	}
	
	/**Set's the callback when the event is triggered at the end of a resize 
	 * operation.
	 * @param stop
	 */
	public void setStopEvent(JsScopeUiEvent stop) {
		this.options.put("stop", stop);
	}

	/*---- Methods section ----*/	
	
	/**Method to destroy
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(getComponent()).$().chain("resizable", "'destroy'");
	}

	/**Method to destroy within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(getComponent()).$().chain("resizable", "'disable'");
	}

	/**Method to disable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(getComponent()).$().chain("resizable", "'enable'");
	}

	/**Method to enable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
}
