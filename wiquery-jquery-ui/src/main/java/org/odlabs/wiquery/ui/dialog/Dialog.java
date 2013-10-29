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
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractAjaxBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ArrayItemOptions;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.mouse.MouseJavaScriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionAlignmentOptions;
import org.odlabs.wiquery.ui.position.PositionJavaScriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.ui.position.PositionRelation;
import org.odlabs.wiquery.ui.resizable.ResizableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavaScriptResourceReference;

/**
 * <p>
 * Displays a window wrapping this {@link WebMarkupContainer} markup.
 * </p>
 * <p>
 * This UI component is built from this {@link WebMarkupContainer}'s HTML markup. The
 * correct markup should be a <code>div</code> HTML element wrapping the contents to
 * display in this window.
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
public class Dialog extends WebMarkupContainer
{
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * This class is only need to make public the method generateCallbackScript.
	 * 
	 * @author Ernesto Reinaldo Barreiro
	 */
	private static class DialogAjaxBehavior extends WiQueryAbstractAjaxBehavior
	{
		private static final long serialVersionUID = 1L;

		public DialogAjaxBehavior()
		{
		}
	}

	/**
	 * Some options can be set on a {@link Dialog}.
	 */
	private Options options;

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
	public Dialog(String id)
	{
		super(id);
		setOutputMarkupId(true);
		add(ajaxBehavior = new DialogAjaxBehavior());
		options = new Options(this);
		// default settings
		this.setAutoOpen(false);
	}

	public static boolean isEmpty(String str)
	{
		return (str == null || str.trim().length() == 0);
	}

	@Override
	protected void detachModel()
	{
		super.detachModel();
		options.detach();
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.render(JavaScriptHeaderItem.forReference(WidgetJavaScriptResourceReference.get()));
		response.render(JavaScriptHeaderItem.forReference(MouseJavaScriptResourceReference.get()));
		response
			.render(JavaScriptHeaderItem.forReference(PositionJavaScriptResourceReference.get()));
		response.render(JavaScriptHeaderItem.forReference(DialogJavaScriptResourceReference.get()));
		response.render(JavaScriptHeaderItem.forReference(DraggableJavaScriptResourceReference
			.get()));
		response.render(JavaScriptHeaderItem.forReference(ResizableJavaScriptResourceReference
			.get()));

		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
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
	
	public JsStatement statement()
	{
		return new JsQuery(this).$().chain("dialog", options.getJavaScriptOptions());
	}

	/*---- Options section ---*/

	/**
	 * Which element the dialog (and overlay, if modal) should be appended to.
	 * 
	 * @param appendTo
	 * @return instance of the current component
	 */
	public Dialog setAppendTo(String appendTo)
	{
		this.options.putLiteral("appendTo", appendTo);
		return this;
	}

	/**
	 * @return the appendTo option value
	 */
	public String getAppendTo()
	{
		String appendTo = this.options.getLiteral("appendTo");
		return appendTo == null ? "body" : appendTo;
	}

	/**
	 * Sets if this window opens autmatically after the page is loaded.
	 * 
	 * @param autoOpen
	 *            true if the window auto opens, false otherwise
	 * @return instance of the current component
	 */
	public Dialog setAutoOpen(boolean autoOpen)
	{
		options.put("autoOpen", autoOpen);
		return this;
	}

	/**
	 * @return if this window auto opens on page loading.
	 */
	public boolean isAutoOpen()
	{
		if (this.options.containsKey("autoOpen"))
		{
			return options.getBoolean("autoOpen");
		}

		return true;
	}
	
	/**
	 * Sets if this window is modal or not.
	 * 
	 * @param modal
	 *            true if the window is modal, false otherwise
	 * @return instance of the current component
	 */
	public Dialog setModal(boolean modal)
	{
		options.put("modal", modal);
		return this;
	}

	/**
	 * @return if this window is modal.
	 */
	public boolean isModal()
	{
		if (this.options.containsKey("modal"))
		{
			return options.getBoolean("modal");
		}

		return false;
	}

	/**
	 * Sets the window's width.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setWidth(int width)
	{
		options.put("width", width);
		return this;
	}

	/**
	 * Returns the dialog's width.
	 */
	public int getWidth()
	{
		if (this.options.containsKey("width"))
		{
			return options.getInt("width");
		}

		return 300;
	}

	/**
	 * Sets the window's height.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setHeight(int height)
	{
		options.put("height", height);
		return this;
	}

	/**
	 * Returns the window's height.
	 */
	public int getHeight()
	{
		if (this.options.containsKey("height"))
		{
			return options.getInt("height");
		}

		return 0;
	}

	/**
	 * Sets the window's position.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setPosition(PositionOptions position)
	{
		options.put("position", position);
		return this;
	}

	/**
	 * Returns the {@link PositionOptions}.
	 */
	public PositionOptions getPosition()
	{
		IComplexOption position = options.getComplexOption("position");
		
		if (position instanceof PositionOptions)
		{
			return (PositionOptions) position;
		}
		
		return new PositionOptions()
				.setMy(new PositionAlignmentOptions(PositionRelation.CENTER))
				.setAt(new PositionAlignmentOptions(PositionRelation.CENTER))
				.setOf("window");
	}

	/**
	 * Sets a the text for the close button
	 * 
	 * @return instance of the current component
	 */
	public Dialog setCloseText(String closeText)
	{
		options.putLiteral("closeText", closeText);
		return this;
	}

	/**
	 * @return the closeText option
	 */
	public String getCloseText()
	{
		String closeText = options.getLiteral("closeText");
		return closeText == null ? "close" : closeText;
	}

	/**
	 * If and how to animate the hiding of the dialog.
	 * 
	 * @param hideOptions
	 * @return instance of the current component
	 */
	public Dialog setHide(DialogAnimateOption hideOptions)
	{
		this.options.put("hide", hideOptions);
		return this;
	}
	
	/**
	 * @return the hide option value
	 */
	public DialogAnimateOption getHide()
	{
		IComplexOption hideOptions = this.options.getComplexOption("hide");
		
		if (hideOptions instanceof DialogAnimateOption)
		{
			return (DialogAnimateOption) hideOptions;
		}
		
		return null;
	}

	/**
	 * If and how to animate the showing of the dialog.
	 * 
	 * @param showOptions
	 * @return instance of the current component
	 */
	public Dialog setShow(DialogAnimateOption showOptions)
	{
		this.options.put("show", showOptions);
		return this;
	}
	
	/**
	 * @return the show option value
	 */
	public DialogAnimateOption getShow()
	{
		IComplexOption showOptions = this.options.getComplexOption("show");
		
		if (showOptions instanceof DialogAnimateOption)
		{
			return (DialogAnimateOption) showOptions;
		}
		
		return null;
	}

	/**
	 * Sets the window's max height.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setMaxHeight(int maxHeight)
	{
		options.put("maxHeight", maxHeight);
		return this;
	}

	/**
	 * Returns the window's max height.
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
	 * @return instance of the current component
	 */
	public Dialog setMaxWidth(int maxWidth)
	{
		options.put("maxWidth", maxWidth);
		return this;
	}

	/**
	 * Returns the window's max width.
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
	 * Sets the window's min height.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setMinHeight(int minHeight)
	{
		options.put("minHeight", minHeight);
		return this;
	}

	/**
	 * Returns the window's min height.
	 */
	public int getMinHeight()
	{
		if (this.options.containsKey("minHeight"))
		{
			return options.getInt("minHeight");
		}

		return 150;
	}

	/**
	 * Sets the window's min width.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setMinWidth(int minWidth)
	{
		options.put("minWidth", minWidth);
		return this;
	}

	/**
	 * Returns the window's max width.
	 */
	public int getMinWidth()
	{
		if (this.options.containsKey("minWidth"))
		{
			return options.getInt("minWidth");
		}

		return 150;
	}

	/**
	 * Sets if this window is resizable or not.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setResizable(boolean resizable)
	{
		options.put("resizable", resizable);
		return this;
	}

	/**
	 * Returns <code>true</code> if this window is resizable.
	 */
	public boolean isResizable()
	{
		if (this.options.containsKey("resizable"))
		{
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
	 * 
	 * @return instance of the current component
	 */
	public Dialog setTitle(String title)
	{
		options.putLiteral("title", title);
		return this;
	}

	/**
	 * Sets the window's title.
	 * <p>
	 * <strong>Note:</strong> the title can be automatically sets when the HTML
	 * <code>title</code> attribute is set.
	 * </p>
	 * 
	 * @return instance of the current component
	 */
	public Dialog setTitle(IModel<String> title)
	{
		options.putLiteral("title", title);
		return this;
	}

	/**
	 * Returns the window's title.
	 * 
	 * @return a non null {@link String} containing the window's title.
	 */
	public String getTitle()
	{
		if (this.options.containsKey("title"))
		{
			return this.options.getLiteral("title");
		}
		return "";
	}

	/**
	 * Set's the close on escape keyboard shortcut
	 * 
	 * @param closeOnEscape
	 * @return instance of the current component
	 */
	public Dialog setCloseOnEscape(boolean closeOnEscape)
	{
		this.options.put("closeOnEscape", closeOnEscape);
		return this;
	}

	/**
	 * @returns <code>true</code> if the close on escape shortcut is enable
	 */
	public boolean isCloseOnEscape()
	{
		if (this.options.containsKey("closeOnEscape"))
		{
			return this.options.getBoolean("closeOnEscape");
		}

		return true;
	}
	
	/**
	 * Set's the bgiframe plugin. When true, the bgiframe plugin will be used,
	 * to fix the issue in IE6 where select boxes show on top of other elements,
	 * regardless of zIndex. Requires including the bgiframe plugin. Future
	 * versions may not require a separate plugin.
	 * 
	 * @param bgiframe
	 * @return instance of the current component
	 */
	@Deprecated
	public Dialog setBgiframe(boolean bgiframe)
	{
		this.options.put("bgiframe", bgiframe);
		return this;
	}

	/**
	 * @returns <code>true</code> if the bgiframe plugin will be used
	 */
	public boolean isBgiframe()
	{
		if (this.options.containsKey("bgiframe")) {
			return this.options.getBoolean("bgiframe");
		}

		return false;
	}

	/**
	 * The specified class name(s) will be added to the dialog, for additional theming.
	 * 
	 * @return instance of the current component
	 */
	public Dialog setDialogClass(String dialogClass)
	{
		options.putLiteral("dialogClass", dialogClass);
		return this;
	}

	/**
	 * @return the dialogClass option
	 */
	public String getDialogClass()
	{
		if (this.options.containsKey("dialogClass"))
		{
			return this.options.getLiteral("dialogClass");
		}
		return "";
	}

	/**
	 * Disables (true) or enables (false) the dialog. Can be set when initializing (first
	 * creating) the dialog.
	 * 
	 * @param disabled
	 * @return instance of the current behavior
	 */
	public Dialog setDisabled(boolean disabled)
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
	 * Enable or disable the draggable event
	 * 
	 * @param draggable
	 * @return instance of the current component
	 */
	public Dialog setDraggable(boolean draggable)
	{
		this.options.put("draggable", draggable);
		return this;
	}

	/**
	 * @returns <code>true</code> if the dialog is draggable
	 */
	public boolean isDraggable()
	{
		if (this.options.containsKey("draggable"))
		{
			return this.options.getBoolean("draggable");
		}

		return true;
	}

	/**
	 * Set's a list of dialog button
	 * 
	 * @param buttons
	 * @return instance of the current component
	 */
	public Dialog setButtons(ArrayItemOptions<DialogButton> buttons)
	{
		for (DialogButton button : buttons)
		{
			if (button instanceof AjaxDialogButton)
			{
				((AjaxDialogButton) button).activateCallback(ajaxBehavior);
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
			ArrayItemOptions<DialogButton> buttons2 = new ArrayItemOptions<DialogButton>();
			for (DialogButton button : buttons)
			{
				if (button instanceof AjaxDialogButton)
				{
					((AjaxDialogButton) button).activateCallback(ajaxBehavior);
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
	public ArrayItemOptions<DialogButton> getButtons()
	{
		if (this.options.containsKey("buttons"))
		{
			return (ArrayItemOptions<DialogButton>) this.options.getCollectionItemOptions("buttons");
		}

		return null;
	}
	
	/*---- Events section ---*/

	/**
	 * Set's the callback before the dialog is closing. If the beforeclose event handler
	 * (callback function) returns false, the close will be prevented
	 * 
	 * @param beforeclose
	 * @return instance of the current component
	 */
	public Dialog setBeforeCloseEvent(JsScopeUiEvent beforeclose)
	{
		this.options.put("beforeClose", beforeclose);
		return this;
	}

	/**
	 * Set's the callback before the dialog is closed.
	 * 
	 * @param close
	 * @return instance of the current component
	 */
	public Dialog setCloseEvent(JsScopeUiEvent close)
	{
		this.options.put("close", close);
		return this;
	}

	/**
	 * Set's the callback when the dialog is created. .
	 *
	 * @param create
	 * @return instance of the current component
	 */
	public Dialog setCreateEvent(JsScopeUiEvent create)
	{
		this.options.put("create", create);
		return this;
	}

	/**
	 * Set's the callback when the dialog is dragged.
	 * 
	 * @param drag
	 * @return instance of the current component
	 */
	public Dialog setDragEvent(JsScopeUiEvent drag)
	{
		this.options.put("drag", drag);
		return this;
	}

	/**
	 * Set's the callback when the dialog is being dragged.
	 * 
	 * @param dragStart
	 * @return instance of the current component
	 */
	public Dialog setDragStartEvent(JsScopeUiEvent dragStart)
	{
		this.options.put("dragStart", dragStart);
		return this;
	}

	/**
	 * Set's the callback when the dialog has been dragged.
	 * 
	 * @param dragStop
	 * @return instance of the current component
	 */
	public Dialog setDragStopEvent(JsScopeUiEvent dragStop)
	{
		this.options.put("dragStop", dragStop);
		return this;
	}

	/**
	 * Set's the callback when the dialog gains focus.
	 * 
	 * @param focus
	 * @return instance of the current component
	 */
	public Dialog setFocusEvent(JsScopeUiEvent focus)
	{
		this.options.put("focus", focus);
		return this;
	}

	/**
	 * Set's the callback before the dialog is opening.
	 * 
	 * @param open
	 * @return instance of the current component
	 */
	public Dialog setOpenEvent(JsScopeUiEvent open)
	{
		this.options.put("open", open);
		return this;
	}

	/**
	 * Set's the callback when the dialog is resized.
	 * 
	 * @param resize
	 * @return instance of the current component
	 */
	public Dialog setResizeEvent(JsScopeUiEvent resize)
	{
		this.options.put("resize", resize);
		return this;
	}

	/**
	 * Set's the callback when the dialog is being resized.
	 * 
	 * @param resizeStart
	 * @return instance of the current component
	 */
	public Dialog setResizeStartEvent(JsScopeUiEvent resizeStart)
	{
		this.options.put("resizeStart", resizeStart);
		return this;
	}

	/**
	 * Set's the callback when the dialog has been resized.
	 * 
	 * @param resizeStop
	 * @return instance of the current component
	 */
	public Dialog setResizeStopEvent(JsScopeUiEvent resizeStop)
	{
		this.options.put("resizeStop", resizeStop);
		return this;
	}
	
	/*---- Methods section ---*/
	
	/**
	 * Method to open the dialog
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement open()
	{
		return new JsQuery(this).$().chain("dialog", "'open'");
	}
	
	/**
	 * Method to open the dialog within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void open(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.open().render().toString());
	}

	/**
	 * Method to close the dialog
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement close()
	{
		return new JsQuery(this).$().chain("dialog", "'close'");
	}

	/**
	 * Method to close the dialog within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void close(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.close().render().toString());
	}

	/**
	 * Method to destroy the dialog This will return the element back to its pre-init
	 * state.
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement destroy()
	{
		return new JsQuery(this).$().chain("dialog", "'destroy'");
	}

	/**
	 * Method to destroy the dialog within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}

	/**
	 * Method returning true if the dialog is currently open
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement isOpen()
	{
		return new JsQuery(this).$().chain("dialog", "'isOpen'");
	}

	/**
	 * Method to move to top the dialog
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement moveToTop()
	{
		return new JsQuery(this).$().chain("dialog", "'moveToTop'");
	}

	/**
	 * Method to move to top the dialog within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void moveToTop(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.moveToTop().render().toString());
	}

	/**
	 * Method to returns the .ui-dialog element
	 * 
	 * @return the associated JsStatement
	 */
	public JsStatement widget()
	{
		return new JsQuery(this).$().chain("dialog", "'widget'");
	}

	/**
	 * Method to returns the .ui-dialog element within the ajax request
	 * 
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget)
	{
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}

	public DialogAjaxBehavior getAjaxBehavior()
	{
		return ajaxBehavior;
	}
}
