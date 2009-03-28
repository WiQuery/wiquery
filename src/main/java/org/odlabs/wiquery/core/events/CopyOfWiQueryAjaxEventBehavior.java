/*
 * Copyright (c) 2008 Objet Direct
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
package org.odlabs.wiquery.core.events;

import org.apache.wicket.Application;
import org.apache.wicket.RequestContext;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.WicketAjaxReference;
import org.apache.wicket.behavior.IBehaviorListener;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WicketEventReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IDebugSettings;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * $Id$
 * <p>
 * Binds the given {@link Event} to an Ajax request.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.5
 */
public abstract class CopyOfWiQueryAjaxEventBehavior extends
		WiQueryAbstractBehavior implements IBehaviorListener {

	protected static final Logger log = LoggerFactory
	.getLogger(CopyOfWiQueryAjaxEventBehavior.class);

	/** reference to the default ajax debug support javascript file. */
	private static final ResourceReference JAVASCRIPT_DEBUG = new JavascriptResourceReference(
		AbstractDefaultAjaxBehavior.class, "wicket-ajax-debug.js");
	
	/**
	 * The event tiggering the Ajax call.
	 */
	private EventLabel[] events;

	public CopyOfWiQueryAjaxEventBehavior(EventLabel...events) {
		super();
		this.events = events;
	}
	
	/**
	 * @see org.apache.wicket.behavior.AbstractAjaxBehavior#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
	 */
	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);

		final IDebugSettings debugSettings = Application.get().getDebugSettings();

		response.renderJavascriptReference(WicketEventReference.INSTANCE);
		response.renderJavascriptReference(WicketAjaxReference.INSTANCE);

		if (debugSettings.isAjaxDebugModeEnabled())
		{
			response.renderJavascriptReference(JAVASCRIPT_DEBUG);
			response.renderJavascript("wicketAjaxDebugEnable=true;", "wicket-ajax-debug-enable");
		}

		RequestContext context = RequestContext.get();
		if (context.isPortletRequest())
		{
			response.renderJavascript("Wicket.portlet=true", "wicket-ajax-portlet-flag");
		}
	}
	
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain(new Event(events) {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public JsScope callback() {
				// generating handler URL. This URL will be used to call
				// a server side method to handle the Ajax Request
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("var wcall=wicketAjaxGet(\"");
				CharSequence url = CopyOfWiQueryAjaxEventBehavior.this.getComponent()
					.urlFor(
							CopyOfWiQueryAjaxEventBehavior.this,
							IBehaviorListener.INTERFACE);
				stringBuilder.append(url);
				stringBuilder.append("\")");
				
				return JsScope.quickScope(stringBuilder);
			}
			
		});
	}

	public void onRequest() {
		log.info("grrrr");
		WebApplication app = (WebApplication)getComponent().getApplication();
		AjaxRequestTarget target = app.newAjaxRequestTarget(getComponent().getPage());
		RequestCycle.get().setRequestTarget(target);
		this.onEvent(target);
	}

	protected abstract void onEvent(AjaxRequestTarget target);
	
}
