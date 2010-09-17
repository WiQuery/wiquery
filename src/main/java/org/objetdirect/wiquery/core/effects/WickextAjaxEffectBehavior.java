package org.objetdirect.wiquery.core.effects;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.objetdirect.wiquery.core.commons.CoreJavaScriptHeaderContributor;
import org.objetdirect.wiquery.core.events.Event;
import org.objetdirect.wiquery.core.javascript.JsQuery;
import org.objetdirect.wiquery.core.javascript.JsScope;
import org.objetdirect.wiquery.core.javascript.JsScopeContext;

public abstract class WickextAjaxEffectBehavior extends AbstractDefaultAjaxBehavior {

	private Effect effect;
	
	public WickextAjaxEffectBehavior(Effect effect) {
		super();
		this.effect = effect;
	}

	@Override
	protected void onBind() {
		this.getComponent().getPage().add(
				new HeaderContributor(new CoreJavaScriptHeaderContributor()));
	}
	
	@Override
	protected void respond(AjaxRequestTarget target) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackScript()
	 */
	@Override
	protected CharSequence getCallbackScript() {
		// binds the ajax call to the wrapped event
		JsQuery query = new JsQuery(this.getComponent());
		effect.setCallback(JsScope.quickScope(getHandlerScript()));
		return query.$().chain(effect).render();
	}

	protected CharSequence getHandlerScript() {
		return super.getCallbackScript();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getPreconditionScript()
	 */
	@Override
	protected CharSequence getPreconditionScript() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		// renders the JavaScript statement
		JsQuery query = new JsQuery(this.getComponent());
		effect.setCallback(JsScope.quickScope(getHandlerScript()));
		query.$().chain(effect).render();
		query.renderHead(response);
	}
}
