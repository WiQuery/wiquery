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
package org.odlabs.wiquery.ui.accordion;

import java.io.IOException;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.ser.ToStringSerializer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.effects.CoreEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * <p>
 * Creates an accordion UI component from this {@link WebMarkupContainer}'s
 * HTML markup.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.6
 */
@WiQueryUIPlugin
public class Accordion extends WebMarkupContainer implements IWiQueryPlugin {

	private static final long serialVersionUID = 2L;

	@JsonSerialize(using=ToStringSerializer.class)
	public static enum AccordionTriggerEvent {
		CLICK, MOUSEOVER
	}

	private AccordionOptions options;

	public Accordion(String id) {
		super(id);
		options = new AccordionOptions();
	}
	
	@Override
	protected void detachModel() {
		super.detachModel();
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(AccordionJavaScriptResourceReference.get());
	}

	public JsStatement statement() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		String optionsStr = "{}";
		try
		{
			optionsStr = mapper.writeValueAsString(getOptions());
		}
		catch (JsonGenerationException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
				
		return new JsQuery(this).$().chain("accordion",
				optionsStr);
	}
	
	protected AccordionOptions getOptions() {
		return options;
	}
	
	/*---- Methods section ---*/
	
	/**Method to activate a content part of the Accordion programmatically. 
	 * The index can be a zero-indexed number to match the position of the header
	 * to close or a Selector matching an element. Pass false to close all 
	 * (only possible with collapsible:true).
	 * This will return the element back to its pre-init state.
	 * @param index
	 * @return the associated JsStatement
	 */
	public JsStatement activate(int index) {
		return new JsQuery(this).$().chain("accordion", "'activate'", Integer.toString(index));
	}

	/**Method to destroy the accordion within the ajax request
	 * @param ajaxRequestTarget
	 * @param index
	 */
	public void activate(AjaxRequestTarget ajaxRequestTarget, int index) {
		ajaxRequestTarget.appendJavaScript(this.activate(index).render().toString());
	}
	
	/**Method to destroy the accordion
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("accordion", "'destroy'");
	}

	/**Method to destroy the accordion within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavaScript(this.destroy().render().toString());
	}
	
	/**Method to disable the accordion
	 * @return the associated JsStatement
	 */
	public JsStatement disable() {
		return new JsQuery(this).$().chain("accordion", "'disable'");
	}

	/**Method to disable the accordion within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void disable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavaScript(this.disable().render().toString());
	}
	
	/**Method to enable the accordion
	 * @return the associated JsStatement
	 */
	public JsStatement enable() {
		return new JsQuery(this).$().chain("accordion", "'enable'");
	}

	/**Method to enable the accordion within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void enable(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavaScript(this.enable().render().toString());
	}
	
	/**Method to returns the .ui-accordion  element
	 * @return the associated JsStatement
	 */
	public JsStatement widget() {
		return new JsQuery(this).$().chain("accordion", "'widget'");
	}

	/**Method to returns the .ui-accordion  element within the ajax request
	 * @param ajaxRequestTarget
	 */
	public void widget(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavaScript(this.widget().render().toString());
	}
}