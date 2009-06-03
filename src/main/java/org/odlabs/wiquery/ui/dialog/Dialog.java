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
package org.odlabs.wiquery.ui.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceLocator;
import org.odlabs.wiquery.ui.resizable.ResizableJavaScriptResourceReference;

/**
 * $Id$
 * <p>
 * Displays a window wrapping this {@link WebMarkupContainer} markup.
 * </p>
 * <p>
 * This UI component is built from this {@link WebMarkupContainer}'s HTML
 * markup. The correct markup should be a <code>div</code> HTML element
 * wrapping the contents to display in this window.
 * </p>
 * <p>
 * Example: <code>
 *  <pre>
 *   &lt;div wicket:id=&quot;id&quot; title=&quot;The window title&quot;&gt;
 *     The wrapped content
 *   &lt;/div&gt;
 * </pre>
 *  </code>
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
@WiQueryUIPlugin
public class Dialog extends WebMarkupContainer implements IWiQueryPlugin {

	private static final long serialVersionUID = 1L;

	public static enum WindowPosition {
		TOP, BOTTOM, CENTER, LEFT, RIGHT
	};

	/**
	 * Some options can be set on a {@link Dialog}.
	 */
	private Options options;

	/**
	 * Builds a new instance of {@link Dialog} for the given wicket id.
	 * 
	 * @param id
	 *            the given wicket id.
	 */
	public Dialog(String id) {
		super(id);
		options = new Options();
		// default settings
		this.setAutoOpen(false);
		this.setPosition(WindowPosition.CENTER);
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(Dialog.class,
				"ui.dialog.js");
		// TODO USE DEPENDENCIES MANGEMENT
		wiQueryResourceManager
				.addJavaScriptResource(new DraggableJavaScriptResourceLocator());
		wiQueryResourceManager
				.addJavaScriptResource(new ResizableJavaScriptResourceReference());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.objetdirect.wickext.core.commons.JavaScriptCallable#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("dialog",
				options.getJavaScriptOptions());
	}

	// TODO
	public JsStatement open() {
		return new JsQuery(this).$().chain("dialog", "'open'");
	}

	// TODO
	public JsStatement close() {
		return new JsQuery(this).$().chain("dialog", "'close'");
	}

	public void open(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.open().render().toString());
	}

	public void close(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.close().render().toString());
	}

	/**
	 * Sets if this window opens autmatically after the page is loaded.
	 * 
	 * @param autoOpen
	 *            true if the window auto opens, false otherwise
	 */
	public void setAutoOpen(boolean autoOpen) {
		options.put("autoOpen", autoOpen);
	}

	/**
	 * Sets if this window is modal or not.
	 * 
	 * @param modal
	 *            true if the window is modal, false otherwise
	 */
	public void setModal(boolean modal) {
		options.put("modal", modal);
	}

	/**
	 * Sets the overlay under the window. This parameter will take effect only
	 * if the {@link #setModal(boolean)} method is call with true.
	 * 
	 * @param ratio
	 *            a float value between 0 and 1 (1 is 100% black overlay)
	 */
	public void setOverlayRatio(float ratio) {
		// TODO nested options !
		options.put("overlay", "{opacity: " + ratio + ", background: 'black'}");
	}

	/**
	 * @return if this window auto opens on page loading.
	 */
	public boolean isAutoOpen() {
		return options.getBoolean("autoOpen");
	}

	/**
	 * @return if this window is modal.
	 */
	public boolean isModal() {
		return options.getBoolean("modal");
	}

	/**
	 * Sets the window's width.
	 */
	public void setWidth(int width) {
		options.put("width", width);
	}

	/**
	 * Returns the dialog's width.
	 */
	public int getWidth() {
		return options.getInt("width");
	}

	/**
	 * Sets the window's height.
	 */
	public void setHeight(int height) {
		options.put("height", height);
	}

	/**
	 * Returns the window's height.
	 */
	public int getHeight() {
		return options.getInt("height");
	}

	/**
	 * @deprecated
	 */
	public void positionTop() {
		options.put("position", "'top'");
	}

	/**
	 * Sets the window's position.
	 */
	public void setPosition(WindowPosition windowPosition) {
		options.putLiteral("position", windowPosition.name().toLowerCase());
	}

	/**
	 * Returns the {@link WindowPosition}.
	 */
	public WindowPosition getPosition() {
		String literal = options.getLiteral("position");
		return WindowPosition.valueOf(literal.toUpperCase());
	}

	/**
	 * Sets a css class to customize the window's display.
	 */
	public void setCssClass(String cssClass) {
		options.putLiteral("dialogClass", cssClass);
	}

	/**
	 * Returns the css class applied to customize this window.
	 */
	public String getCssClass() {
		return options.get("dialogClass");
	}

	/**
	 * Sets the effect used when the window closes.
	 * 
	 * @param a
	 *            {@link String} with the given effect's name.
	 */
	public void setHideEffect(String hideEffect) {
		options.putLiteral("hide", hideEffect);
	}

	/**
	 * Sets the effect used when the window shows itself.
	 * 
	 * @param a
	 *            {@link String} with the given effect's name.
	 */
	public void setShowEffect(String hideEffect) {
		options.putLiteral("show", hideEffect);
	}

	/**
	 * Sets the window's max height.
	 */
	public void setMaxHeight(int maxHeight) {
		options.put("maxHeight", maxHeight);
	}

	/**
	 * Returns the window's max height.
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
	 * Returns the window's max width.
	 */
	public int getMaxWidth() {
		return options.getInt("maxWidth");
	}

	/**
	 * Sets the window's min height.
	 */
	public void setMinHeight(int minHeight) {
		options.put("minHeight", minHeight);
	}

	/**
	 * Returns the window's min height.
	 */
	public int getMinHeight() {
		return options.getInt("minHeight");
	}

	/**
	 * Sets the window's min width.
	 */
	public void setMinWidth(int minWidth) {
		options.put("minWidth", minWidth);
	}

	/**
	 * Returns the window's max width.
	 */
	public int getMinWidth() {
		return options.getInt("minWidth");
	}

	/**
	 * Sets if this window is resizable or not.
	 */
	public void setResizable(boolean resizable) {
		options.put("resizable", resizable);
	}

	/**
	 * Returns <code>true</code> if this window is resizable.
	 */
	public boolean isResizable() {
		return options.getBoolean("resizable");
	}

	/**
	 * Sets the window's title.
	 * <p>
	 * <strong>Note:</strong> the title can be automatically sets when the HTML
	 * <code>title</code> attribute is set.
	 * </p>
	 */
	public void setTitle(String title) {
		options.putLiteral("title", title);
	}

	/**
	 * Returns the window's title.
	 * 
	 * @return a non null {@link String} containing the window's title.
	 */
	public String getTitle() {
		if (this.options.containsKey("title")) {
			return this.options.get("title");
		}
		return "";
	}

	/**
	 * Set's the close on escape keyboard shortcut
	 * @param closeOnEscape
	 */
	public void setCloseOnEscape(boolean closeOnEscape) {
		this.options.put("closeOnEscape", closeOnEscape);
	}

	/**
	 * @returns <code>true</code> if the close on escape shortcut is enable
	 */
	public boolean isCloseOnEscape() {
		if(this.options.containsKey("closeOnEscape")){
			return this.options.getBoolean("closeOnEscape");
		}
		
		return true;
	}
	
	/**
	 * Set's the bgiframe plugin.
	 * When true, the bgiframe plugin will be used, to fix the issue in IE6 where
	 * select boxes show on top of other elements, regardless of zIndex. Requires
	 * including the bgiframe plugin. Future versions may not require a separate 
	 * plugin.
	 * @param bgiframe
	 */
	public void setBgiframe(boolean bgiframe) {
		this.options.put("bgiframe", bgiframe);
	}

	/**
	 * @returns <code>true</code> if the bgiframe plugin will be used
	 */
	public boolean isBgiframe() {
		if(this.options.containsKey("bgiframe")){
			return this.options.getBoolean("bgiframe");
		}
		
		return true;
	}
	
	/**
	 * Enable or disable the draggable event
	 * @param draggable
	 */
	public void setDraggable(boolean draggable) {
		this.options.put("draggable", draggable);
	}

	/**
	 * @returns <code>true</code> if the dialog is draggable
	 */
	public boolean isDraggrable() {
		if(this.options.containsKey("draggable")){
			return this.options.getBoolean("draggable");
		}
		
		return true;
	}
	
	/**
	 * Specifies whether the dialog will stack on top of other dialogs. This 
	 * will cause the dialog to move to the front of other dialogs when it gains
	 * focus.
	 * @param stack
	 */
	public void setStack(boolean stack) {
		this.options.put("stack", stack);
	}

	/**
	 * @returns <code>true</code> if the dialog will be stack
	 */
	public boolean isStack() {
		if(this.options.containsKey("stack")){
			return this.options.getBoolean("stack");
		}
		
		return true;
	}
	
	/**Set's the starting z-index
	 * @param zIndex
	 */
	public void setZIndex(int zIndex) {
		this.options.put("zIndex", zIndex);
	}
	
	/**
	 * @return the starting z-index (default : 1000)
	 */
	public int getZIndex() {
		if(this.options.containsKey("zIndex")){
			return this.options.getInt("zIndex");
		}
		
		return 1000;
	}
	
	/**Set's a list of dialog button
	 * @param buttons
	 */
	public void setButtons(ListItemOptions<DialogButton> buttons) {
		this.options.put("buttons", buttons);
	}
	
	/**
	 * @return the list of buttons
	 */
	@SuppressWarnings("unchecked")
	public ListItemOptions<DialogButton> getButtons() {
		if(this.options.containsKey("buttons")){
			return (ListItemOptions<DialogButton>) this.options.getListItemOptions("buttons");
		}
		
		return null;
	}
	
	/**Set's the callback before the dialog is closing.
	 * If the beforeclose event handler (callback function) returns false, the 
	 * close will be prevented
	 * @param beforeclose
	 */
	public void setBeforeCloseEvent(JsScopeDialogEvent beforeclose) {
		this.options.put("beforeclose", beforeclose);
	}
	
	/**Set's the callback before the dialog is closed.
	 * @param close
	 */
	public void setCloseEvent(JsScopeDialogEvent close) {
		this.options.put("close", close);
	}
	
	/**Set's the callback when the dialog is dragged.
	 * @param drag
	 */
	public void setDragEvent(JsScopeDialogEvent drag) {
		this.options.put("drag", drag);
	}
	
	/**Set's the callback when the dialog is being dragged.
	 * @param dragStart
	 */
	public void setDragStartEvent(JsScopeDialogEvent dragStart) {
		this.options.put("dragStart", dragStart);
	}
	
	/**Set's the callback when the dialog has been dragged.
	 * @param dragStop
	 */
	public void setDragStopEvent(JsScopeDialogEvent dragStop) {
		this.options.put("dragStop", dragStop);
	}
	
	/**Set's the callback when the dialog gains focus.
	 * @param focus
	 */
	public void setFocusEvent(JsScopeDialogEvent focus) {
		this.options.put("focus", focus);
	}
	
	/**Set's the callback before the dialog is opening.
	 * @param open
	 */
	public void setOpenEvent(JsScopeDialogEvent open) {
		this.options.put("open", open);
	}
	
	/**Set's the callback when the dialog is resized.
	 * @param resize
	 */
	public void setResizeEvent(JsScopeDialogEvent resize) {
		this.options.put("resize", resize);
	}
	
	/**Set's the callback when the dialog is being resized.
	 * @param resizeStart
	 */
	public void setResizeStartEvent(JsScopeDialogEvent resizeStart) {
		this.options.put("resizeStart", resizeStart);
	}
	
	/**Set's the callback when the dialog has been resized.
	 * @param resizeStop
	 */
	public void setResizeStopEvent(JsScopeDialogEvent resizeStop) {
		this.options.put("resizeStop", resizeStop);
	}
	
	/**Method to destroy the dialog
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("dialog", "'destroy'");
	}

	/**Method to destroy the dialog within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**Method to disable the dialog
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(this).$().chain("dialog", "'disable'");
	}

	/**Method to disable the dialog within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.disable().render().toString());
	}
	
	/**Method to enable the dialog
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(this).$().chain("dialog", "'enable'");
	}

	/**Method to enable the dialog within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.enable().render().toString());
	}
	
	/**Method to move to top the dialog
	 * @return the associated JsStatement
	 */
	public JsStatement moveToTop() {
		return new JsQuery(this).$().chain("dialog", "'moveToTop'");
	}

	/**Method to move to top the dialog within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void moveToTop(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.moveToTop().render().toString());
	}
}
