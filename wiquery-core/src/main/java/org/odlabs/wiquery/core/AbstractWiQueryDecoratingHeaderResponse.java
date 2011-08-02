package org.odlabs.wiquery.core;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.aggregation.AbstractDependencyRespectingResourceAggregatingHeaderResponse;
import org.apache.wicket.resource.aggregation.ResourceReferenceAndStringData;
import org.apache.wicket.resource.aggregation.ResourceReferenceCollection;
import org.odlabs.wiquery.core.resources.CoreJavaScriptResourceReference;

public abstract class AbstractWiQueryDecoratingHeaderResponse
		extends
		AbstractDependencyRespectingResourceAggregatingHeaderResponse<ResourceReferenceCollection, String>
{
	protected WiQuerySettings settings = WiQuerySettings.get();

	public AbstractWiQueryDecoratingHeaderResponse(IHeaderResponse real)
	{
		super(real);
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
	protected String newGroupingKey(ResourceReferenceAndStringData ref)
	{
		return ref.getReference().getScope().getPackage().getName();
	}

	/**
	 * @return true when reference is not null, wiquery resource manager is enabled,
	 */
	private boolean isReferenceAllowed(ResourceReference reference)
	{
		if (reference == null)
			throw new IllegalStateException("your ResourceReference can not be null here");
		else if (!settings.isEnableWiqueryResourceManagement())
			return false;
		else if (reference.equals(CoreJavaScriptResourceReference.get())
			&& !settings.isAutoImportJQueryResource())
		{
			return false;
		}

		return true;
	}
}