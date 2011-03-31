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
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ICollectionItemOptions;
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
	/**
	 * We override the behavior to deny the access of critical methods 
	 * 
	 * @author Julien Roche
	 * 
	 */
	private class InnerResizableBehavior extends ResizableBehavior {
		// Constants
		/** Constant of serialization */
		private static final long serialVersionUID = 5587258236214715234L;
		
		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.resizable.ResizableBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
		 */
		@Override
		public void contribute(WiQueryResourceManager wiQueryResourceManager) {
			super.contribute(wiQueryResourceManager);
			ResizableAjaxBehavior.this.contribute(wiQueryResourceManager);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.resizable.ResizableBehavior#getOptions()
		 */
		@Override
		protected Options getOptions() {
			throw new UnsupportedOperationException(
					"You can't call this method into the ResizableAjaxBehavior");
		}

		/**
		 * For framework internal use only.
		 */
		private void setInnerStopEvent(JsScopeUiEvent stop) {
			super.setStopEvent(stop);
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.resizable.ResizableBehavior#setStopEvent(org.odlabs.wiquery.ui.core.JsScopeUiEvent)
		 */
		@Override
		public ResizableBehavior setStopEvent(JsScopeUiEvent stop) {
			throw new UnsupportedOperationException(
					"You can't call this method into the ResizableAjaxBehavior");
		}

		/**
		 * {@inheritDoc}
		 * @see org.odlabs.wiquery.ui.resizable.ResizableBehavior#statement()
		 */
		@Override
		public JsStatement statement() {
			resizableBehavior.setInnerStopEvent(new JsScopeUiEvent() {
				private static final long serialVersionUID = 1L;

				/**
				 * {@inheritDoc}
				 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
				 */
				@Override
				protected void execute(JsScopeContext scopeContext) {					
					scopeContext.append(ResizableAjaxBehavior.this.getCallbackScript(true));
				}

			});
			
			return super.statement();
		}
	}

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Adding the standard resizable JavaScript behavior
	 */
	private InnerResizableBehavior resizableBehavior;
	
	/** Height into the request */
	private static final String RESIZED_HEIGHT = "resizedHeight";

	/** Width into the request */
	private static final String RESIZED_WIDTH = "resizedWidth";
	
	/**
	 * Default constructor
	 */
	public ResizableAjaxBehavior() {
		super();
		resizableBehavior = new InnerResizableBehavior();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.resizable.ResizableBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 * Override this method for additional resources
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		// To override
	}

	/**
	 * @return the standard resizable JavaScript behavior
	 */
	public ResizableBehavior getResizableBehavior() {
		return resizableBehavior;
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		getComponent().add(resizableBehavior);
	}
	
	/**
	 * For framework internal use only.
	 */
	public final void onResize(AjaxRequestTarget target) {
		int height = (int) this.getComponent().getRequest().getQueryParameters().getParameterValue(
				RESIZED_HEIGHT).toDouble();
		int width = (int) this.getComponent().getRequest().getQueryParameters().getParameterValue(
				RESIZED_WIDTH).toDouble();
		onResize(height, width, target);
	}

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
	 * onDrop is called back when the drop event has been fired.
	 * 
	 * @param droppedComponent
	 *            the dropped {@link Component}
	 * @param ajaxRequestTarget
	 *            the Ajax target
	 */
	
	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		onResize(target);
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	protected JsStatement statement() {
		return resizableBehavior.statement();
	}
	
	
	/**
	 * 	We override super method to add height and width parameters to the URL. 
	 * 	Otherwise we use standard AbstractDefaultAjaxBehavior machinery to generate script: what way all the logic 
	 *  regarding IAjaxCallDecorator or indicatorId will be added to the generated script. 
	 *  This makes resizable AJAX behavior compatible with standard Wicket's AJAX call-backs.
	 * 
	 * (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackUrl()
	 */
	@Override
	protected CharSequence getCallbackScript()
	{
		return generateCallbackScript("wicketAjaxGet('" + getCallbackUrl() 
				+ "&" + RESIZED_HEIGHT + "='+" + ResizableBehavior.UI_SIZE + ".height+'"
				+ "&" + RESIZED_WIDTH + "='+" + ResizableBehavior.UI_SIZE + ".width");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////							SHORTCUTS								////
	////////////////////////////////////////////////////////////////////////////
	
	/**Resize these elements synchronous when resizing.
	 * @param alsoResize
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setAlsoResize(ResizableAlsoResize alsoResize) {
		resizableBehavior.setAlsoResize(alsoResize);
		return this;
	}

	/**
	 * @return the alsoResize option
	 */
	public ResizableAlsoResize getAlsoResizeComplex() {
		return resizableBehavior.getAlsoResizeComplex();
	}

	/**Animates to the final size after resizing.
	 * @param animate
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setAnimate(boolean animate) {
		resizableBehavior.setAnimate(animate);
		return this;
	}

	/**
	 * @return the animate option
	 */
	public boolean isAnimate() {
		return resizableBehavior.isAnimate();
	}
	
	/**
	 * Sets the duration time for animating, in milliseconds. 
	 * Other possible values: 'slow', 'normal', 'fast'.
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setAnimateDuration(ResizableAnimeDuration animeDuration) {
		resizableBehavior.setAnimateDuration(animeDuration);
		return this;
	}
	
	/**
	 * @return the animeDuration option
	 */
	public ResizableAnimeDuration getAnimateDuration() {
		return resizableBehavior.getAnimateDuration();
	}

	/**Sets the easing effect for animating.
	 * @param easing
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setAnimateEasing(String easing) {
		resizableBehavior.setAnimateEasing(easing);
		return this;
	}

	/**
	 * @return the animateEasing option
	 */
	public String getAnimateEasing() {
		return resizableBehavior.getAnimateEasing();
	}
	
	/**
	 * If set to true, resizing is constrained by the original aspect ratio. 
	 * Otherwise a custom aspect ratio can be specified, such as 9 / 16, or 0.5.
	 * @param aspectRatio
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setAspectRatio(ResizableAspectRatio aspectRatio) {
		resizableBehavior.setAspectRatio(aspectRatio);
		return this;
	}
	
	/**
	 * @return the aspectRatio option
	 */
	public ResizableAspectRatio getAspectRatio() {
		return resizableBehavior.getAspectRatio();
	}
	
	/**If set to true, automatically hides the handles except when the mouse 
	 * hovers over the element.
	 * @param autoHide
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setAutoHide(boolean autoHide) {
		resizableBehavior.setAutoHide(autoHide);
		return this;
	}
	
	/**
	 * @return the autoHide option
	 */
	public boolean isAutoHide() {
		return resizableBehavior.isAutoHide();
	}
	
	/**Prevents resizing if you start on elements matching the selector.
	 * @param cancel
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setCancel(String cancel) {
		resizableBehavior.setCancel(cancel);
		return this;
	}
	
	/**
	 * @return the cancel option
	 */
	public String getCancel() {
		return resizableBehavior.getCancel();
	}
	
	/**
	 * Sets the constrains resizing to within the bounds of the specified element. 
	 * Possible values: 'parent', 'document', a DOMElement, or a Selector.
	 * @param containment
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setContainment(ResizableContainment containment) {
		resizableBehavior.setContainment(containment);
		return this;
	}
	
	/**
	 * @return the containment option
	 */
	public ResizableContainment getContainment() {
		return resizableBehavior.getContainment();
	}
	
	/**Sets the tolerance, in milliseconds, for when resizing should start. If 
	 * specified, resizing will not start until after mouse is moved beyond duration. 
	 * This can help prevent unintended resizing when clicking on an element.
	 * @param delay
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setDelay(int delay) {
		resizableBehavior.setDelay(delay);
		return this;
	}
	
	/**
	 * @return the distance option
	 */
	public int getDelay() {
		return resizableBehavior.getDelay();
	}
	
	/**Sets the tolerance, in pixels, for when resizing should start. If specified,
	 * resizing will not start until after mouse is moved beyond distance. This 
	 * can help prevent unintended resizing when clicking on an element.
	 * @param distance
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setDistance(int distance) {
		resizableBehavior.setDistance(distance);
		return this;
	}
	
	/**
	 * @return the distance option
	 */
	public int getDistance() {
		return resizableBehavior.getDistance();
	}
	
	/**Disables (true) or enables (false) the resizable. Can be set when 
	 * initialising (first creating) the resizable.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setDisabled(boolean disabled) {
		resizableBehavior.setDisabled(disabled);
		return this;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		return resizableBehavior.isDisabled();
	}
	
	/**Set to true, a semi-transparent helper element is shown for resizing.
	 * @param ghost
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setGhost(boolean ghost) {
		resizableBehavior.setGhost(ghost);
		return this;
	}
	
	/**
	 * @return the ghost option
	 */
	public boolean isGhost() {
		return resizableBehavior.isGhost();
	}
	
	/**Snaps the resizing element to a grid, every x and y pixels. Array values: [x, y] 
	 * Array values: [x, y]
	 * @param x
	 * @param y
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setGrid(int x, int y) {
		resizableBehavior.setGrid(x, y);
		return this;
	}
	
	/**
	 * @return the grid option value
	 */
	public ICollectionItemOptions getGrid() {
		return resizableBehavior.getGrid();
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
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setHandles(ResizableHandles handles) {
		resizableBehavior.setHandles(handles);
		return this;
	}
	
	/**
	 * @return the handles option
	 */
	public ResizableHandles getHandles() {
		return resizableBehavior.getHandles();
	}
	
	/**Sets the css class that will be added to a proxy element to outline the 
	 * resize during the drag of the resize handle. Once the resize is complete, 
	 * the original element is sized.
	 * @param helper
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setHelper(String helper) {
		resizableBehavior.setHelper(helper);
		return this;
	}
	
	/**
	 * @return the helper option
	 */
	public String getHelper() {
		return resizableBehavior.getHelper();
	}
	
	/**
	 * Sets the component's max height.
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setMaxHeight(int maxHeight) {
		resizableBehavior.setMaxHeight(maxHeight);
		return this;
	}

	/**
	 * Returns the component's max height.
	 */
	public int getMaxHeight() {
		return resizableBehavior.getMaxHeight();
	}

	/**
	 * Sets the window's max width.
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setMaxWidth(int maxWidth) {
		resizableBehavior.setMaxWidth(maxWidth);
		return this;
	}

	/**
	 * Returns the component's max width.
	 */
	public int getMaxWidth() {
		return resizableBehavior.getMaxWidth();
	}

	/**
	 * Sets the component's min height.
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setMinHeight(int minHeight) {
		resizableBehavior.setMinHeight(minHeight);
		return this;
	}

	/**
	 * Returns the component's min height.
	 */
	public int getMinHeight() {
		return resizableBehavior.getMinHeight();
	}

	/**
	 * Sets the component's min width.
	 * @return instance of the current behavior
	 */
	public ResizableAjaxBehavior setMinWidth(int minWidth) {
		resizableBehavior.setMinWidth(minWidth);
		return this;
	}

	/**
	 * Returns the component's max width.
	 */
	public int getMinWidth() {
		return resizableBehavior.getMinWidth();
	}

	/*---- Methods section ----*/	
	
	/**Method to destroy
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return resizableBehavior.destroy();
	}

	/**Method to destroy within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		resizableBehavior.destroy(ajaxRequestTarget);
	}
	
	/**Method to disable
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return resizableBehavior.disable();
	}

	/**Method to disable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		resizableBehavior.disable(ajaxRequestTarget);
	}
	
	/**Method to enable
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return resizableBehavior.enable();
	}

	/**Method to enable within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		resizableBehavior.enable(ajaxRequestTarget);
	}
	
	/**Method to returns the .ui-resizable  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return resizableBehavior.widget();
	}

	/**Method to returns the .ui-resizable  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		resizableBehavior.widget(ajaxRequestTarget);
	}
}
