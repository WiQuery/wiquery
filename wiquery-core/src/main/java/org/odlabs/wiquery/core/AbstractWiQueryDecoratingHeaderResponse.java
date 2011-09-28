package org.odlabs.wiquery.core;

import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WicketEventReference;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.aggregation.AbstractDependencyRespectingResourceAggregatingHeaderResponse;
import org.apache.wicket.resource.aggregation.ResourceReferenceAndStringData;
import org.apache.wicket.resource.aggregation.ResourceReferenceCollection;
import org.apache.wicket.resource.dependencies.AbstractResourceDependentResourceReference;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.CssUtils;
import org.apache.wicket.util.string.JavaScriptUtils;
import org.odlabs.wiquery.core.resources.CoreJavaScriptResourceReference;
import org.odlabs.wiquery.core.resources.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.core.resources.WiQueryStyleSheetResourceReference;
import org.odlabs.wiquery.core.ui.ICoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.core.ui.IWiQueryCoreThemeResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * An implementation of AbstractDependencyRespectingResourceAggregatingHeaderResponse that
 * renders references in the correct order if they are
 * {@link AbstractResourceDependentResourceReference} references, ensuring that
 * dependencies are rendered in the proper order according to the
 * {@link WiQuerySettings#getResourceGroupingKeys()} and before their parent (even if they
 * do not appear in the same group as the parent of the dependencies).
 * </p>
 * 
 * <p>
 * The {@link AbstractResourceDependentResourceReference} are grouped by package name and
 * according to the order defined by {@link WiQuerySettings#getResourceGroupingKeys()}.
 * </p>
 * 
 * @see WiQuerySettings#getResourceGroupingKeys()
 * @see WiQuerySettings#findResourceGroupingKey(String)
 * 
 * @author Hielke Hoeve
 */
public abstract class AbstractWiQueryDecoratingHeaderResponse
		extends
		AbstractDependencyRespectingResourceAggregatingHeaderResponse<ResourceReferenceCollection, String>
{
	private static final Logger log = LoggerFactory
		.getLogger(AbstractWiQueryDecoratingHeaderResponse.class);

	protected WiQuerySettings settings = WiQuerySettings.get();

	private final Deque<AbstractToken> thingsToBeRendered =
		new LinkedBlockingDeque<AbstractToken>();

	public AbstractWiQueryDecoratingHeaderResponse(IHeaderResponse real)
	{
		super(real);
	}

	public void addThingToBeRendered(AbstractToken token)
	{
		thingsToBeRendered.add(token);
	}

	@Override
	public void renderCSS(CharSequence css, String id)
	{
		Args.notNull(css, "css");

		AbstractToken token = new CssToken(css, id);
		addThingToBeRendered(token);
	}

	@Override
	public void renderCSSReference(ResourceReference reference)
	{
		if (isReferenceAllowed(reference))
			super.renderCSSReference(reference);
	}

	@Override
	public void renderCSSReference(ResourceReference reference, String media)
	{
		if (isReferenceAllowed(reference))
			super.renderCSSReference(reference, media);
	}

	@Override
	public void renderCSSReference(ResourceReference reference, PageParameters pageParameters,
			String media)
	{
		if (isReferenceAllowed(reference))
			super.renderCSSReference(reference, pageParameters, media);
	}

	@Override
	public void renderCSSReference(ResourceReference reference, PageParameters pageParameters,
			String media, String condition)
	{
		if (isReferenceAllowed(reference))
			super.renderCSSReference(reference, pageParameters, media, condition);
	}

	@Override
	public void renderCSSReference(String url)
	{
		super.renderCSSReference(url);
	}

	@Override
	public void renderCSSReference(String url, String media)
	{
		super.renderCSSReference(url, media);
	}

	@Override
	public void renderCSSReference(String url, String media, String condition)
	{
		super.renderCSSReference(url, media, condition);
	}

	@Override
	public void renderJavaScript(CharSequence javascript, String id)
	{
		Args.notNull(javascript, "javascript");

		AbstractToken token = new JavascriptToken(javascript, id);
		addThingToBeRendered(token);
	}

	@Override
	public void renderJavaScriptReference(ResourceReference reference)
	{
		if (isReferenceAllowed(reference))
			super.renderJavaScriptReference(reference);
	}

	@Override
	public void renderJavaScriptReference(ResourceReference reference, String id)
	{
		if (isReferenceAllowed(reference))
			super.renderJavaScriptReference(reference, id);
	}

	@Override
	public void renderJavaScriptReference(ResourceReference reference,
			PageParameters pageParameters, String id)
	{
		if (isReferenceAllowed(reference))
			super.renderJavaScriptReference(reference, pageParameters, id);
	}

	@Override
	public void renderJavaScriptReference(ResourceReference reference,
			PageParameters pageParameters, String id, boolean defer)
	{
		if (isReferenceAllowed(reference))
			super.renderJavaScriptReference(reference, pageParameters, id, defer);
	}

	@Override
	public void renderJavaScriptReference(ResourceReference reference,
			PageParameters pageParameters, String id, boolean defer, String charset)
	{
		if (isReferenceAllowed(reference))
			super.renderJavaScriptReference(reference, pageParameters, id, defer, charset);
	}

	@Override
	public void renderJavaScriptReference(String url)
	{
		super.renderJavaScriptReference(url);
	}

	@Override
	public void renderJavaScriptReference(String url, String id)
	{
		super.renderJavaScriptReference(url, id);
	}

	@Override
	public void renderJavaScriptReference(String url, String id, boolean defer)
	{
		super.renderJavaScriptReference(url, id, defer);
	}

	@Override
	public void renderJavaScriptReference(String url, String id, boolean defer, String charset)
	{
		super.renderJavaScriptReference(url, id, defer, charset);
	}

	@Override
	public void renderString(CharSequence string)
	{
		Args.notNull(string, "string");

		AbstractToken token = new StringToken(string);
		addThingToBeRendered(token);
	}

	@Override
	public void renderOnDomReadyJavaScript(String javascript)
	{
		renderOnEventJavaScript("window", "domready", javascript);
	}

	@Override
	public void renderOnLoadJavaScript(String javascript)
	{
		renderOnEventJavaScript("window", "load", javascript);
	}

	@Override
	public void renderOnEventJavaScript(String target, String event, String javascript)
	{
		Args.notNull(target, "target");
		Args.notNull(event, "event");
		Args.notNull(javascript, "javascript");

		AbstractToken token =
			new JavascriptToken("Wicket.Event.add(" + target + ", \"" + event
				+ "\", function(event) { " + javascript + ";});", Integer.toString(javascript
				.hashCode()));
		addThingToBeRendered(token);

		renderJavaScriptReference(WicketEventReference.INSTANCE);
	}

	/**
	 * Contribute all plain javascripts, plain css and strings.
	 */
	@Override
	protected void onAllCollectionsRendered(
			List<ResourceReferenceAndStringData> allTopLevelReferences)
	{
		long startTime = System.currentTimeMillis();
		log.debug("WiQuery plain javascripts, plain css and strings contribution starts!");

		if (isClosed())
		{
			log.error("WiQuery plain javascripts, plain css and strings contribution could not finish as response is closed! Done in "
				+ (System.currentTimeMillis() - startTime) + "ms!");
			return;
		}

		Iterator<AbstractToken> jsIter = thingsToBeRendered.iterator();
		while (jsIter.hasNext())
		{
			AbstractToken token = jsIter.next();
			{
				if (wasRendered(token) == false)
				{
					token.render(getResponse());
					markRendered(token);
				}
			}
		}

		log.debug("WiQuery plain javascripts, plain css and strings contribution finished in "
			+ (System.currentTimeMillis() - startTime) + "ms!");
	}

	@Override
	protected String newGroupingKey(ResourceReferenceAndStringData ref)
	{
		String packageName = ref.getReference().getScope().getPackage().getName();
		String preconfiguredKey = WiQuerySettings.get().findResourceGroupingKey(packageName);
		if (preconfiguredKey != null)
			return preconfiguredKey;

		String[] packageNameSplit = packageName.split("\\.");
		if (packageNameSplit.length > 3)
			return packageName.substring(0, packageName.indexOf(packageNameSplit[3]) - 1);
		else
			return packageName;
	}

	@Override
	protected Comparator<String> getGroupingKeyComparator()
	{
		return new Comparator<String>()
		{
			private WiQuerySettings settings = WiQuerySettings.get();

			public int compare(String o1, String o2)
			{
				int o1index = settings.getResourceGroupingKeys().indexOf(o1);
				int o2index = settings.getResourceGroupingKeys().indexOf(o2);

				if (o1index < 0 && o2index < 0)
					return o1.compareToIgnoreCase(o2);
				else if (o1index < 0)
					o1index = Integer.MAX_VALUE;
				else if (o2index < 0)
					o2index = Integer.MAX_VALUE;

				return o1index - o2index;
			}
		};
	}

	/**
	 * @return true when reference is not null and the corresponding wiquery resource
	 *         management is enabled.
	 */
	protected boolean isReferenceAllowed(ResourceReference reference)
	{
		if (reference == null)
		{
			throw new IllegalStateException("ResourceReference can not be null");
		}
		else if (!settings.isEnableWiqueryResourceManagement()
			&& ((reference instanceof WiQueryJavaScriptResourceReference && ((WiQueryJavaScriptResourceReference) reference)
				.isWiQuery()) || (reference instanceof WiQueryStyleSheetResourceReference && ((WiQueryStyleSheetResourceReference) reference)
				.isWiQuery())))
		{
			return false;
		}
		else if (reference instanceof CoreJavaScriptResourceReference
			&& !settings.isAutoImportJQueryResource())
		{
			return false;
		}
		else if (reference instanceof ICoreUIJavaScriptResourceReference
			&& !settings.isAutoImportJQueryUIJavaScriptResource())
		{
			return false;
		}
		else if (reference instanceof IWiQueryCoreThemeResourceReference
			&& !settings.isAutoImportJQueryUIStyleSheetResource())
		{
			return false;
		}

		return true;
	}

	protected abstract class AbstractToken
	{
		private CharSequence value;

		private String id;

		/**
		 * @param value
		 * @param id
		 *            when null or empty a substring of value is used to generate an id,
		 *            if all fails "-1" is used.
		 */
		public AbstractToken(CharSequence value, String id)
		{
			this.value = value;
			this.id = id;
		}

		public CharSequence getValue()
		{
			return value;
		}

		public String getId()
		{
			return id;
		}

		@Override
		public int hashCode()
		{
			return value.hashCode();
		}

		@Override
		public boolean equals(Object obj)
		{
			if (obj instanceof AbstractToken)
			{
				return ((AbstractToken) obj).getValue().equals(value)
					&& getClass().equals(obj.getClass());
			}
			return false;
		}

		/**
		 * Renders the value and id in the correct form to the Response.
		 */
		public abstract void render(Response response);
	}

	public final class StringToken extends AbstractToken
	{
		public StringToken(CharSequence value)
		{
			super(value, null);
		}

		@Override
		public void render(Response response)
		{
			getResponse().write(getValue());
		}
	}

	public final class JavascriptToken extends AbstractToken
	{
		public JavascriptToken(CharSequence value, String id)
		{
			super(value, id);
		}

		@Override
		public void render(Response response)
		{
			JavaScriptUtils.writeJavaScript(getResponse(), getValue(), getId());
		}
	}

	public final class CssToken extends AbstractToken
	{
		public CssToken(CharSequence value, String id)
		{
			super(value, id);
		}

		@Override
		public void render(Response response)
		{
			getResponse().write(CssUtils.INLINE_OPEN_TAG + getValue() + CssUtils.INLINE_CLOSE_TAG);
		}
	}
}