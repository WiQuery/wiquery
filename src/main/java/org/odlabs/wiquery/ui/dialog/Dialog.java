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

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.button.ButtonJavascriptResourceReference;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.dialog.AjaxDialogButton.AjaxDialogScope;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.mouse.MouseJavascriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionJavascriptResourceReference;
import org.odlabs.wiquery.ui.resizable.ResizableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

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
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @since 0.5
 */
@WiQueryUIPlugin
public class Dialog extends WebMarkupContainer implements IWiQueryPlugin {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Eumeration of possible window position
	 * @author Lionel Armanet
	 *
	 */
	public static enum WindowPosition {
		TOP, BOTTOM, CENTER, LEFT, RIGHT
	};
	
	/**
	 * Some options can be set on a {@link Dialog}.
	 */
	private Options options;
	
	public static final String BUTTON_ID = "BUTTON_ID";
	
	/**
	 * This class is only need to make public the method generateCallbackScript.
	 * 
	 * @author Ernesto Reinaldo Barreiro
	 */
	public static abstract class DialogAjaxBehavior extends AbstractDefaultAjaxBehavior
	{

		private static final long serialVersionUID = 1L;

		public DialogAjaxBehavior()
		{
		}

		@Override
		public CharSequence generateCallbackScript(CharSequence partialCall)
		{
			return super.generateCallbackScript(partialCall);
		}
	}
	
	/**
	 * Ajax behavior used to handle buttons events.
	 */
	private DialogAjaxBehavior ajaxBehavior;

	/**
	 * Builds a new instance of {@link Dialog} for the given wicket id.
	 * 
	 * @param id
	 *            the given wicket id.
	 */
	public Dialog(String id) {
		super(id);
		setOutputMarkupId(true);
		add(ajaxBehavior = new DialogAjaxBehavior()
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void respond(AjaxRequestTarget target)
			{
				String buttonTitle = WebRequestCycle.get().getRequest().getParameter(BUTTON_ID).toString();
				// if an AJAX button was clicked we find it and delegate execution.
				if (!isEmpty(buttonTitle))
				{
					ListItemOptions<DialogButton> buttons = getButtons();
					if (buttons != null)
					{
						for (DialogButton button : buttons)
						{
							if (button.getTitle().equals(buttonTitle)
								&& button instanceof AjaxDialogButton)
							{
								AjaxDialogButton ajaxDialogButton = (AjaxDialogButton) button;
								// delegate handling of event to button class.
								ajaxDialogButton.onButtonClicked(target);
							}
						}
					}
				}
			}
		});
		options = new Options(this);
		// default settings
		this.setAutoOpen(false);
		this.setPosition(WindowPosition.CENTER);
	}

	public static boolean isEmpty(String str)
	{
		return (str == null || str.trim().length() == 0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.wicket.Component#detachModel()
	 */
	@Override
	protected void detachModel() {
		super.detachModel();
		options.detach();		
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(MouseJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(PositionJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(DialogJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(ButtonJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(DraggableJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(ResizableJavaScriptResourceReference.get());
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
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	/**Method to open the dialog
	 * @return the associated JsStatement
	 */
	public JsStatement open() {
		return new JsQuery(this).$().chain("dialog", "'open'");
	}

	
	/**Method to close the dialog
	 * @return the associated JsStatement
	 */
	public JsStatement close() {
		return new JsQuery(this).$().chain("dialog", "'close'");
	}

	/**Method to open the dialog within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void open(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.open().render().toString());
	}

	/**Method to close the dialog within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void close(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.close().render().toString());
	}

	/**
	 * Sets if this window opens autmatically after the page is loaded.
	 * 
	 * @param autoOpen
	 *            true if the window auto opens, false otherwise
	 * @return instance of the current component
	 */
	public Dialog setAutoOpen(boolean autoOpen) {
		options.put("autoOpen", autoOpen);
		return this;
	}

	/**
	 * Sets if this window is modal or not.
	 * 
	 * @param modal
	 *            true if the window is modal, false otherwise
	 * @return instance of the current component
	 */
	public Dialog setModal(boolean modal) {
		options.put("modal", modal);
		return this;
	}

	/**
	 * Sets the overlay under the window. This parameter will take effect only
	 * if the {@link #setModal(boolean)} method is call with true.
	 * 
	 * @param ratio
	 *            a float value between 0 and 1 (1 is 100% black overlay)
	 * @return instance of the current component
	 * @deprecated will be removed in 1.3
	 */
	public Dialog setOverlayRatio(float ratio) {
		// TODO nested options !
		options.put("overlay", "{opacity: " + ratio + ", background: 'black'}");
		return this;
	}

	/**
	 * @return if this window auto opens on page loading.
	 */
	public boolean isAutoOpen() {
		if(this.options.containsKey("autoOpen")){
			return options.getBoolean("autoOpen");
		}
		
		return true;
	}

	/**
	 * @return if this window is modal.
	 */
	public boolean isModal() {
		if(this.options.containsKey("modal")){
			return options.getBoolean("modal");
		}
		
		return false;
	}

	/**
	 * Sets the window's width.
	 * @return instance of the current component
	 */
	public Dialog setWidth(int width) {
		options.put("width", width);
		return this;
	}

	/**
	 * Returns the dialog's width.
	 */
	public int getWidth() {
		if(this.options.containsKey("width")){
			return options.getInt("width");
		}
		
		return 300;
	}

	/**
	 * Sets the window's height.
	 * @return instance of the current component
	 */
	public Dialog setHeight(int height) {
		options.put("height", height);
		return this;
	}

	/**
	 * Returns the window's height.
	 */
	public int getHeight() {
		if(this.options.containsKey("height")){
			return options.getInt("height");
		}
		
		return 0;
	}

	/**
	 * Sets the window's position.
	 * @return instance of the current component
	 */
	public Dialog setPosition(WindowPosition windowPosition) {
		options.putLiteral("position", windowPosition.name().toLowerCase());
		return this;
		// TODO change the parameter of this method
	}

	/**
	 * Returns the {@link WindowPosition}.
	 */
	public WindowPosition getPosition() {
		String literal = options.getLiteral("position");
		return literal == null ? WindowPosition.CENTER : WindowPosition.valueOf(literal.toUpperCase());
	}
	
	/**
	 * Sets a the text for the close button
	 * @return instance of the current component
	 */
	public Dialog setCloseText(String closeText) {
		options.putLiteral("closeText", closeText);
		return this;
	}

	/**
	 * @return the closeText option
	 */
	public String getCloseText() {
		String closeText = options.getLiteral("closeText");
		return closeText == null ? "close" : closeText;
	}

	/**
	 * Sets a css class to customize the window's display.
	 * @return instance of the current component
	 */
	public Dialog setCssClass(String cssClass) {
		options.putLiteral("dialogClass", cssClass);
		return this;
	}

	/**
	 * Returns the css class applied to customize this window.
	 */
	public String getCssClass() {
		String dialogClass = options.getLiteral("dialogClass");
		return dialogClass == null ? "*" : dialogClass;
	}

	/**
	 * Sets the effect used when the window closes.
	 * 
	 * @param a
	 *            {@link String} with the given effect's name.
	 * @return instance of the current component
	 */
	public Dialog setHideEffect(String hideEffect) {
		options.putLiteral("hide", hideEffect);
		return this;
	}
	
	/**
	 * @return the hide option value
	 */
	public String getHideEffect() {
		return this.options.getLiteral("hide");
	}

	/**
	 * Sets the effect used when the window shows itself.
	 * 
	 * @param a
	 *            {@link String} with the given effect's name.
	 * @return instance of the current component
	 */
	public Dialog setShowEffect(String hideEffect) {
		options.putLiteral("show", hideEffect);
		return this;
	}
	
	/**
	 * @return the show option value
	 */
	public String getShowEffect() {
		return this.options.getLiteral("show");
	}

	/**
	 * Sets the window's max height.
	 * @return instance of the current component
	 */
	public Dialog setMaxHeight(int maxHeight) {
		options.put("maxHeight", maxHeight);
		return this;
	}

	/**
	 * Returns the window's max height.
	 */
	public int getMaxHeight() {
		if(this.options.containsKey("maxHeight")){
			return options.getInt("maxHeight");
		}
		
		return 0;
	}

	/**
	 * Sets the window's max width.
	 * @return instance of the current component
	 */
	public Dialog setMaxWidth(int maxWidth) {
		options.put("maxWidth", maxWidth);
		return this;
	}

	/**
	 * Returns the window's max width.
	 */
	public int getMaxWidth() {
		if(this.options.containsKey("maxWidth")){
			return options.getInt("maxWidth");
		}
		
		return 0;
	}

	/**
	 * Sets the window's min height.
	 * @return instance of the current component
	 */
	public Dialog setMinHeight(int minHeight) {
		options.put("minHeight", minHeight);
		return this;
	}

	/**
	 * Returns the window's min height.
	 */
	public int getMinHeight() {
		if(this.options.containsKey("minHeight")){
			return options.getInt("minHeight");
		}
		
		return 150;
	}

	/**
	 * Sets the window's min width.
	 * @return instance of the current component
	 */
	public Dialog setMinWidth(int minWidth) {
		options.put("minWidth", minWidth);
		return this;
	}

	/**
	 * Returns the window's max width.
	 */
	public int getMinWidth() {
		if(this.options.containsKey("minWidth")){
			return options.getInt("minWidth");
		}
		
		return 150;
	}

	/**
	 * Sets if this window is resizable or not.
	 * @return instance of the current component
	 */
	public Dialog setResizable(boolean resizable) {
		options.put("resizable", resizable);
		return this;
	}

	/**
	 * Returns <code>true</code> if this window is resizable.
	 */
	public boolean isResizable() {
		if(this.options.containsKey("resizable")){
			return options.getBoolean("resizable");
		}
		
		return true;
		
	}

	/**
	 * Sets the window's title.
	 * <p>
	 * <strong>Note:</strong> the title can be automatically sets when the HTML
	 * <code>title</code> attribute is set.
	 * </p>
	 * @return instance of the current component
	 */
	public Dialog setTitle(String title) {
		options.putLiteral("title", title);
		return this;
	}
	
	/**
	 * Sets the window's title.
	 * <p>
	 * <strong>Note:</strong> the title can be automatically sets when the HTML
	 * <code>title</code> attribute is set.
	 * </p>
	 * @return instance of the current component
	 */
	public Dialog setTitle(IModel<String> title) {
		options.putLiteral("title", title);
		return this;
	}

	/**
	 * Returns the window's title.
	 * 
	 * @return a non null {@link String} containing the window's title.
	 */
	public String getTitle() {
		if (this.options.containsKey("title")) {
			return this.options.getLiteral("title");
		}
		return "";
	}

	/**
	 * Set's the close on escape keyboard shortcut
	 * @param closeOnEscape
	 * @return instance of the current component
	 */
	public Dialog setCloseOnEscape(boolean closeOnEscape) {
		this.options.put("closeOnEscape", closeOnEscape);
		return this;
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
	 * @return instance of the current component
	 * @deprecated will be removed in 1.3
	 */
	public Dialog setBgiframe(boolean bgiframe) {
		this.options.put("bgiframe", bgiframe);
		return this;
	}

	/**
	 * @deprecated will be removed in 1.3
	 * @returns <code>true</code> if the bgiframe plugin will be used
	 */
	public boolean isBgiframe() {
		if(this.options.containsKey("bgiframe")){
			return this.options.getBoolean("bgiframe");
		}
		
		return false;
	}
	
	/**
	 * The specified class name(s) will be added to the dialog, for additional theming.
	 * @return instance of the current component
	 */
	public Dialog setDialogClass(String dialogClass) {
		options.putLiteral("dialogClass", dialogClass);
		return this;
	}

	/**
	 * @return the dialogClass option
	 */
	public String getDialogClass() {
		if (this.options.containsKey("dialogClass")) {
			return this.options.getLiteral("dialogClass");
		}
		return "";
	}
	
	/**Disables (true) or enables (false) the dialog. Can be set when 
	 * initialising (first creating) the dialog.
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Dialog setDisabled(boolean disabled) {
		this.options.put("disabled", disabled);
		return this;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		if(this.options.containsKey("disabled")){
			return this.options.getBoolean("disabled");
		}
		
		return false;
	}
	
	/**
	 * Enable or disable the draggable event
	 * @param draggable
	 * @return instance of the current component
	 */
	public Dialog setDraggable(boolean draggable) {
		this.options.put("draggable", draggable);
		return this;
	}
	
	/**
	 * @returns <code>true</code> if the dialog is draggable
	 */
	public boolean isDraggable() {
		if(this.options.containsKey("draggable")){
			return this.options.getBoolean("draggable");
		}
		
		return true;
	}

	/**
	 * @returns <code>true</code> if the dialog is draggable
	 * @deprecated will be removed is 1.2
	 */
	@Deprecated
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
	 * @return instance of the current component
	 */
	public Dialog setStack(boolean stack) {
		this.options.put("stack", stack);
		return this;
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
	 * @return instance of the current component
	 */
	public Dialog setZIndex(int zIndex) {
		this.options.put("zIndex", zIndex);
		return this;
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
	
	/**
	 * Set's a list of dialog button
	 * 
	 * @param buttons
	 * @return instance of the current component
	 */
	public Dialog setButtons(ListItemOptions<DialogButton> buttons)
	{
		for (DialogButton button : buttons)
		{
			if (button instanceof AjaxDialogButton)
			{
				button.setJsScope(new AjaxDialogScope(button.getTitle(), this));
			}
		}
		this.options.put("buttons", buttons);
		return this;
	}

	/**
	 * Set's a list of dialog button
	 * 
	 * @param buttons
	 * @return instance of the current component
	 */
	public Dialog setButtons(DialogButton... buttons)
	{
		if (buttons != null && buttons.length > 0)
		{
			ListItemOptions<DialogButton> buttons2 = new ListItemOptions<DialogButton>();
			for (DialogButton button : buttons)
			{
				if (button instanceof AjaxDialogButton)
				{
					button.setJsScope(new AjaxDialogScope(button.getTitle(), this));
				}
				buttons2.add(button);
			}
			this.options.put("buttons", buttons2);
		}
		return this;
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
	 * @return instance of the current component
	 * @deprecated will be removed when we will used jquery ui 1.9 (see ticket http://dev.jqueryui.com/ticket/4669)
	 */
	@Deprecated
	public Dialog setBeforeCloseEvent(JsScopeUiEvent beforeclose) {
		this.options.put("beforeClose", beforeclose);
		return this;
	}
	
	/**Set's the callback before the dialog is closed.
	 * @param close
	 * @return instance of the current component
	 */
	public Dialog setCloseEvent(JsScopeUiEvent close) {
		this.options.put("close", close);
		return this;
	}
	
	/**Set's the callback when the dialog is dragged.
	 * @param drag
	 * @return instance of the current component
	 */
	public Dialog setDragEvent(JsScopeUiEvent drag) {
		this.options.put("drag", drag);
		return this;
	}
	
	/**Set's the callback when the dialog is being dragged.
	 * @param dragStart
	 * @return instance of the current component
	 */
	public Dialog setDragStartEvent(JsScopeUiEvent dragStart) {
		this.options.put("dragStart", dragStart);
		return this;
	}
	
	/**Set's the callback when the dialog has been dragged.
	 * @param dragStop
	 * @return instance of the current component
	 */
	public Dialog setDragStopEvent(JsScopeUiEvent dragStop) {
		this.options.put("dragStop", dragStop);
		return this;
	}
	
	/**Set's the callback when the dialog gains focus.
	 * @param focus
	 * @return instance of the current component
	 */
	public Dialog setFocusEvent(JsScopeUiEvent focus) {
		this.options.put("focus", focus);
		return this;
	}
	
	/**Set's the callback before the dialog is opening.
	 * @param open
	 * @return instance of the current component
	 */
	public Dialog setOpenEvent(JsScopeUiEvent open) {
		this.options.put("open", open);
		return this;
	}
	
	/**Set's the callback when the dialog is resized.
	 * @param resize
	 * @return instance of the current component
	 */
	public Dialog setResizeEvent(JsScopeUiEvent resize) {
		this.options.put("resize", resize);
		return this;
	}
	
	/**Set's the callback when the dialog is being resized.
	 * @param resizeStart
	 * @return instance of the current component
	 */
	public Dialog setResizeStartEvent(JsScopeUiEvent resizeStart) {
		this.options.put("resizeStart", resizeStart);
		return this;
	}
	
	/**Set's the callback when the dialog has been resized.
	 * @param resizeStop
	 * @return instance of the current component
	 */
	public Dialog setResizeStopEvent(JsScopeUiEvent resizeStop) {
		this.options.put("resizeStop", resizeStop);
		return this;
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
	
	/**Method returning true if the dialog is currently open
	 * @return the associated JsStatement
	 */
	public JsStatement isOpen() {
		return new JsQuery(this).$().chain("dialog", "'isOpen'");
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
	
	/**Method to returns the .ui-dialog  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(this).$().chain("dialog", "'widget'");
	}

	/**Method to returns the .ui-dialog element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.widget().render().toString());
	}

	public DialogAjaxBehavior getAjaxBehavior() {
		return ajaxBehavior;
	}
}
