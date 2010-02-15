package org.odlabs.wiquery.ui.autocomplete;

import java.util.List;

import org.apache.wicket.Response;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

@WiQueryUIPlugin
public abstract class AutocompleteBehavior<T, E> extends AbstractDefaultAjaxBehavior implements
		IWiQueryPlugin {

	private static final long serialVersionUID = 1L;

	private Options options = new Options();
	
	public AutocompleteBehavior() {
		super();
	}

	public IHeaderContributor getHeaderContribution() {
		return new IHeaderContributor() {

			private static final long serialVersionUID = 1L;

			public void renderHead(IHeaderResponse response) {
				response
						.renderJavascriptReference(new JavascriptResourceReference(
								AutocompleteBehavior.class, "ui.autocomplete.js"));
			}

		};
	}

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this.getComponent()).$().chain("autocomplete", options.getJavaScriptOptions());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#onBind()
	 */
	@Override
	protected void onBind() {
		options.putLiteral("url", this.getCallbackUrl().toString());
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#respond(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		// response: outputing the list of results as a js map
		List<AutocompleteValue<T, E>> results = autocomplete();
		Response response = this.getComponent().getRequestCycle().getResponse();
		response.write("{");
		for (AutocompleteValue<T, E> autocompleteValue : results) {
			autocompleteValue.write(response);
		}
		response.write("}");
	}

	public abstract List<AutocompleteValue<T, E>> autocomplete();
	
}
